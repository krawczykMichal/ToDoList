package org.example.todolist.api.controller;


import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.todolist.api.dto.TasksDTO;
import org.example.todolist.business.ProfilesService;
import org.example.todolist.business.TasksService;
import org.example.todolist.domain.Profiles;
import org.example.todolist.domain.Tasks;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class TasksController {

    private final TasksService tasksService;
    private final ProfilesService profilesService;


    @GetMapping(value = "/tasks-home")
    public String tasksHomePage(
    @ModelAttribute("tasksDTO")
    TasksDTO tasksDTO,
    Model model
            ) {
        model.addAttribute("tasksDTO", tasksDTO);
        return "tasks_home";
    }


    @GetMapping(value = "/tasks-create")
    public String tasksCreate(
            @ModelAttribute("tasksDTO")
            TasksDTO tasksDTO,
            Model model
    ) {
        model.addAttribute("tasksDTO", tasksDTO);
        return "tasks_create";
    }

    @PostMapping(value = "/tasks-create")
    public String tasksCreatePage(
            @ModelAttribute("tasksDTO")
            TasksDTO tasksDTO,
            Model model,
            HttpSession session
    ) {

        String username = session.getAttribute("username").toString();

        Object profile = session.getAttribute("profile");
        Profiles profilesByUsername = profilesService.findProfilesByUsername(username);
        tasksService.saveTasks(tasksDTO, profilesByUsername);
        model.addAttribute("tasksDTO", tasksDTO);
        return "redirect:/tasks-list";
    }




    @GetMapping(value = "/tasks-list")
    public String tasksListPage(
            @ModelAttribute("tasksDTO")
            TasksDTO tasksDTO,
            HttpSession httpSession,
            Model model
    ) {

        String username = httpSession.getAttribute("username").toString();
        List<Tasks> tasksList = tasksService.showTasksListForProfilesUsernameUndone(username);
        model.addAttribute("tasksList", tasksList);
        return "tasks_list";
    }


    @GetMapping(value = "/tasks/details/{tasksId}")
    public String tasksDetails(
            @PathVariable("tasksId")
            Integer tasksId,
            @ModelAttribute("tasksDTO")
            TasksDTO tasksDTO,
            HttpSession httpSession,
            Model model
    ) {
        Tasks tasks = tasksService.findById(tasksId);
        model.addAttribute("tasks", tasks);

        return "tasks_details";
    }

    @PatchMapping(value = "/tasks/details/{tasksId}")
    public String tasksDetailsPage(
            @RequestParam("tasksId")
            Integer tasksId,
            @ModelAttribute("tasksDTO")
            TasksDTO tasksDTO,
            HttpSession httpSession,
            Model model
    ) {

        Tasks tasks = tasksService.findById(tasksId);
        model.addAttribute("tasks", tasks);
        tasksService.markAsDone(tasksId);

        return "redirect:/tasks-list";
    }

    @GetMapping(value = "/tasks/history")
    public String tasksHistory(
            @ModelAttribute("tasksDTO")
            TasksDTO tasksDTO,
            HttpSession httpSession,
            Model model
    ) {
        String username = httpSession.getAttribute("username").toString();
        List<Tasks> tasksList = tasksService.showTasksListForProfilesUsernameDone(username);
        model.addAttribute("tasksList", tasksList);

        return "tasks_history";
    }



    @PatchMapping(value = "/tasks/{tasksId}/update")
    public String tasksUpdatePage(
            @PathVariable Integer tasksId,
            @ModelAttribute
            TasksDTO tasksDTO,
            Model model
    ) {
        tasksService.updateTasks(tasksId, tasksDTO);

        model.addAttribute("taskDTO", tasksDTO);

         return "redirect:/tasks-list";
    }

    @DeleteMapping(value = "/tasks/{tasksId}/delete")
    public String tasksDeletePage(
            @PathVariable Integer tasksId
    ) {
        tasksService.deleteById(tasksId);

        return "redirect:/tasks-list";
    }
}
