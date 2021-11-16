package com.rango.alere.config;

import com.rango.alere.entities.Role;
import com.rango.alere.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if (!roleRepository.existsByDescricaoIsLike("DOADOR")) {
            final Role role = new Role(null, "DOADOR", false);
            roleRepository.save(role);
        }

        if (!roleRepository.existsByDescricaoIsLike("RECEPTOR")) {
            final Role role = new Role(null, "RECEPTOR", false);
            roleRepository.save(role);
        }

        if (!roleRepository.existsByDescricaoIsLike("USER")) {
            final Role role = new Role(null, "USER", true);
            roleRepository.save(role);
        }

        if (!roleRepository.existsByDescricaoIsLike("ADMIN")) {
            final Role role = new Role(null, "ADMIN", false);
            roleRepository.save(role);
        }
    }
}
