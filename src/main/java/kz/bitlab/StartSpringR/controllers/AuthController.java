package kz.bitlab.StartSpringR.controllers;

import kz.bitlab.StartSpringR.models.Profile;
import kz.bitlab.StartSpringR.models.Role;
import kz.bitlab.StartSpringR.models.User;
import kz.bitlab.StartSpringR.repositories.ProfileRepository;
import kz.bitlab.StartSpringR.repositories.RoleRepository;
import kz.bitlab.StartSpringR.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author Assylkhan
 * on 12.09.2019
 * @project StartSpringR
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private BCryptPasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private ProfileRepository profileRepository;
    private UserRepository userRepository;

    @Autowired
    public AuthController(BCryptPasswordEncoder passwordEncoder,
                          RoleRepository roleRepository,
                          ProfileRepository profileRepository,
                          UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    public String registerPage() {
        return "auth/register";
    }

    @GetMapping("/403")
    public String page403() {
        return "auth/403";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String iin) {

        List<Role> selectedRoles = roleRepository.findAllByIdIn(Arrays.asList(3L));
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setBirthDate(new Date());
        user.setRoles(new HashSet<>());
        user.getRoles().addAll(selectedRoles);
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setIin(iin);
        profile.setUser(user);
        profileRepository.save(profile);
        return "redirect:/auth/login";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, Authentication auth) {
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login";
    }

}