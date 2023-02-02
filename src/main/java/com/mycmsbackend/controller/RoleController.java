package com.mycmsbackend.controller;

import com.mycmsbackend.model.Role;
import com.mycmsbackend.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Get all Roles
    @GetMapping("/roles")
    public List<Role> getAllAccounts() {
        return roleService.getAllAccounts();
    }
}
