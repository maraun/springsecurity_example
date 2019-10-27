package kz.bitlab.StartSpringR.controllers;

import kz.bitlab.StartSpringR.models.Profile;
import kz.bitlab.StartSpringR.models.Role;
import kz.bitlab.StartSpringR.models.User;
import kz.bitlab.StartSpringR.repositories.ProfileRepository;
import kz.bitlab.StartSpringR.repositories.RoleRepository;
import kz.bitlab.StartSpringR.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author Assylkhan
 * on 5.09.2019
 * @project StartSpringR
 */

@Controller
public class MainController {

    @Autowired
    @Qualifier("admin")
    private User admin;

    @Autowired
    @Qualifier("client")
    private User client;

    private UserRepository userRepository;
    private ProfileRepository profileRepository;
    private RoleRepository roleRepository;

    @Autowired
    public MainController(
            UserRepository userRepository,
            ProfileRepository profileRepository,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView index() {
        ModelAndView mw = new ModelAndView("index");
        mw.addObject("users", userRepository.findAll());
        mw.addObject("roles", roleRepository.findAll());
        return mw;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(@RequestParam String username, @RequestParam String password, @RequestParam String iin, @RequestParam List<Long> roleIds) {
        if (userRepository.getOneByUsername(username) != null) {
            return "redirect:/?error=1";
        }

        List<Role> selectedRoles = roleRepository.findAllByIdIn(roleIds);

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setBirthDate(new Date());
        user.setRoles(new HashSet<>());
        user.getRoles().addAll(selectedRoles);
        userRepository.save(user);

        Profile profile = new Profile();
        profile.setIin(iin);
        profile.setUser(user);
        profileRepository.save(profile);

        return "redirect:/";
    }

}
