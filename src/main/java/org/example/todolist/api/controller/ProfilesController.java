package org.example.todolist.api.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.todolist.api.dto.ProfilesDTO;
import org.example.todolist.business.ProfilesService;
import org.example.todolist.domain.Profiles;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@AllArgsConstructor
public class ProfilesController {

    private final ProfilesService profilesService;

    @GetMapping(value = "/profiles/register")
    public String profilesRegisterPage(
            @ModelAttribute("profilesDTO")
            ProfilesDTO profilesDTO,
            Model model) {
        model.addAttribute("profilesDTO", profilesDTO);
        return "profiles_register";
    }

    @PostMapping(value = "/profiles/register")
    public String profilesRegister(
            @ModelAttribute("profilesDTO")
            ProfilesDTO profilesDTO,
            HttpSession httpSession
    ) {
        profilesService.saveProfiles(profilesDTO);

        httpSession.setAttribute("profilesEmail", profilesDTO.getEmail());

        return "redirect:/profiles/home";
    }

    @GetMapping(value = "/profiles/home")
    public String profilesHomePage(
            Authentication authentication,
            Model model,
            HttpSession httpSession
    ) {

        authentication.isAuthenticated();
        log.debug("log authentication is: " + authentication);
        Object prin = authentication.getPrincipal();
        log.debug("log principal is instance of: " + prin.getClass());

        String username = getEmailFromAuthentication(authentication);

        Profiles profilesByUsername = profilesService.findProfilesByUsername(username);

        httpSession.setAttribute("username", username);
        httpSession.setAttribute("profile", profilesByUsername);
        model.addAttribute("profilesByUsername", profilesByUsername);

        return "profiles_home";
    }

    private String getEmailFromAuthentication(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        return ((UserDetails) principal).getUsername();
    }

    @GetMapping(value = "/profiles/{profilesId}/details")
    public String profilesDetailsPage(
            @PathVariable Integer profilesId,
            @ModelAttribute("profiles")
            Profiles profiles,
            Model model,
            HttpSession httpSession
    ) {

        String username = httpSession.getAttribute("username").toString();
        Profiles profilesByUsername = profilesService.findProfilesByUsername(username);

        Integer profilesId1 = profilesByUsername.getProfilesId();

        profilesId1 = profilesId;

        model.addAttribute("profiles", profilesByUsername);

        return "profiles_details";
    }

    @GetMapping(value = "/profiles/{profilesId}/update")
    public String profilesUpdatePage(
            @PathVariable Integer profilesId,
            @ModelAttribute("profiles")
            Profiles profiles,
            Model model,
            HttpSession httpSession
    ) {
        String username = httpSession.getAttribute("username").toString();
        Profiles profilesByUsername = profilesService.findProfilesByUsername(username);

//        ProfilesDTO profilesDTO1 = profilesService.mapProfile(profilesByUsername);

        Integer profilesId1 = profilesByUsername.getProfilesId();
//
        profilesId1 = profilesId;

        profilesService.findProfilesById(profilesId);

        model.addAttribute("profilesDTO", profilesByUsername);

        return "profiles_update";

    }

    @PatchMapping(value = "/profiles/{profilesId}/update")
    public String profilesUpdate(
            @PathVariable Integer profilesId,
            @ModelAttribute("profilesDTO")
            ProfilesDTO profilesDTO,
            Model model,
            HttpSession httpSession
    ) {

        String username = httpSession.getAttribute("username").toString();
        Profiles profilesByUsername = profilesService.findProfilesByUsername(username);

        Integer profilesId1 = profilesByUsername.getProfilesId();

        profilesId1 = profilesId;

        profilesService.updateProfiles(profilesId, profilesDTO);

        model.addAttribute("profilesDTO", profilesDTO);

        return "redirect:/profiles/{profilesId}/details";
    }

    @DeleteMapping(value = "/{profilesId}/delete")
    public String profilesDeletePage(
            @PathVariable Integer profilesId
    ) {
        profilesService.deleteById(profilesId);

        return "redirect:/home";
    }
}
