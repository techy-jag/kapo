package com.cocoe.spring;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cocoe.spring.user.model.Privilege;
import com.cocoe.spring.user.model.Role;
import com.cocoe.spring.user.model.User;
import com.cocoe.spring.user.repository.PrivilegeRepository;
import com.cocoe.spring.user.repository.RoleRepository;
import com.cocoe.spring.user.repository.UserRepository;

@Component
public class SetupDataLoader implements
  ApplicationListener<ContextRefreshedEvent> {
 
    boolean alreadySetup = false;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private RoleRepository roleRepository;
 
    @Autowired
    private PrivilegeRepository privilegeRepository;
  
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
 
        if (alreadySetup)
            return;
        Privilege admin
          = createPrivilegeIfNotFound("ADMIN");
        Privilege user
          = createPrivilegeIfNotFound("USER");
        Privilege seller
        = createPrivilegeIfNotFound("SELLER");
 
    
        createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(admin));
        createRoleIfNotFound("ROLE_USER", Arrays.asList(user));
        createRoleIfNotFound("ROLE_SELLER", Arrays.asList(seller));
 
		/*
		 * Role adminRole = roleRepository.findByName("ROLE_ADMIN"); User user = new
		 * User(); user.setFirstName("Test"); user.setLastName("Test");
		 * user.setPassword(passwordEncoder.encode("test"));
		 * user.setEmail("test@test.com"); user.setRoles(Arrays.asList(adminRole));
		 * user.setEnabled(true); userRepository.save(user);
		 */
        alreadySetup = true;
    }
 
    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {
 
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
 
    @Transactional
    Role createRoleIfNotFound(
      String name, Collection<Privilege> privileges) {
 
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}