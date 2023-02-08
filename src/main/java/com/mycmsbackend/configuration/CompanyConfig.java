package com.mycmsbackend.configuration;


import com.mycmsbackend.domain.ERole;
import com.mycmsbackend.domain.Role;
import com.mycmsbackend.domain.User;
import com.mycmsbackend.repository.RoleRepository;
import com.mycmsbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
public class CompanyConfig {

    PasswordEncoder encoder;

    public CompanyConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository){
        return args -> {

            ERole eRole = null;
            Set<Role> setRoleUser = new HashSet<>();
            Set<Role> setRoleMod = new HashSet<>();
            Set<Role> setRoleAdmin = new HashSet<>();

            Role roleUser = new Role();
            roleUser.setId(1);
            roleUser.setName(eRole.ROLE_USER);
            setRoleUser.add(roleUser);

            Role roleModerator = new Role();
            roleModerator.setId(2);
            roleModerator.setName(eRole.ROLE_MODERATOR);
            setRoleMod.add(roleModerator);

            Role roleAdmin = new Role();
            roleAdmin.setId(3);
            roleAdmin.setName(ERole.ROLE_ADMIN);
            setRoleAdmin.add(roleAdmin);

            User user = new User(
                    1L,
                    "user",
                    "user@gmail.com",
                    encoder.encode("12345678"),
                    setRoleUser
            );

            User mod = new User(
                    2L,
                    "mod",
                    "mod@gmail.com",
                    encoder.encode("12345678"),
                    setRoleMod
            );

            User admin = new User(
                    3L,
                    "admin",
                    "admin@gmail.com",
                    encoder.encode("12345678"),
                    setRoleAdmin
            );

            userRepository.saveAll(List.of(user, mod, admin));
            roleRepository.saveAll(List.of(roleUser,roleModerator,roleAdmin));

        };

    }
}
