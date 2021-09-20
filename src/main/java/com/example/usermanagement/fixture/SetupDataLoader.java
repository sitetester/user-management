package com.example.usermanagement.fixture;

import com.example.usermanagement.entity.Privilege;
import com.example.usermanagement.entity.Role;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.PrivilegeRepository;
import com.example.usermanagement.repository.RoleRepository;
import com.example.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (roleRepository.existsByName("ROLE_ADMIN")) {
            return;
        }

        Privilege read = createPrivilegeIfNotFound("READ");
        Privilege create = createPrivilegeIfNotFound("CREATE");
        Privilege edit = createPrivilegeIfNotFound("EDIT");
        Privilege delete = createPrivilegeIfNotFound("DELETE");

        List<Privilege> adminPrivileges = Arrays.asList(read, create, edit, delete);
        List<Privilege> editorPrivileges = Arrays.asList(read, edit);
        List<Privilege> userPrivileges = Arrays.asList(read);

        Role roleAdmin = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        Role roleEditor = createRoleIfNotFound("ROLE_EDITOR", editorPrivileges);
        Role roleUser = createRoleIfNotFound("ROLE_USER", userPrivileges);

        setupUser("test", "test", "demo", "admin@example.com", List.of(roleAdmin), true );
        setupUser("test", "test", "demo", "editor@example.com", List.of(roleEditor), true );
        setupUser("test", "test", "demo", "user@example.com", List.of(roleUser), true );
    }

    private void setupUser(String firstname, String lastname, String password, String email, Collection rolls, Boolean enabled) {
        User user = new User();
        user
                .setFirstName(firstname)
                .setLastName(lastname)
                .setPassword(passwordEncoder.encode(password))
                .setEmail(email)
                .setRoles(rolls)
                .setEnabled(enabled);

        userRepository.save(user);
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilegeRepository.findByName(name) == null) {
            privilege = new Privilege();
            privilege.setName(name);

            privilegeRepository.save(privilege);
        }

        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role
                    .setName(name)
                    .setPrivileges(privileges);

            roleRepository.save(role);
        }

        return role;
    }
}