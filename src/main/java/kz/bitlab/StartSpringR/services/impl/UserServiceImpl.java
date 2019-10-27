package kz.bitlab.StartSpringR.services.impl;

import kz.bitlab.StartSpringR.models.Role;
import kz.bitlab.StartSpringR.models.User;
import kz.bitlab.StartSpringR.repositories.UserRepository;
import kz.bitlab.StartSpringR.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Assylkhan
 * on 12.09.2019
 * @project StartSpringR
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        kz.bitlab.StartSpringR.models.User user = userRepository.getOneByUsername(username);
        org.springframework.security.core.userdetails.User securedUser =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                        convertMyRolesToSpringRoles(user.getRoles()));
        return securedUser;
    }

    public List<GrantedAuthority> convertMyRolesToSpringRoles(Set<Role> roles) {
        List<GrantedAuthority> springRoles = new ArrayList<>();

        for (Role r : roles) {
            springRoles.add(new SimpleGrantedAuthority(r.getName()));
        }

        return springRoles;
    }
}
