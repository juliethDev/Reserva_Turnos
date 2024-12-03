package com.example.Integro.DTO.Salida.Loggin;

public class UserExit {

    private Long id;
    private String name;
    private RoleExit roleExit;

    public UserExit() {
    }

    public UserExit(Long id, String name, RoleExit roleExit) {
        this.id = id;
        this.name = name;
        this.roleExit = roleExit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleExit getRoleExit() {
        return roleExit;
    }

    public void setRoleExit(RoleExit roleExit) {
        this.roleExit = roleExit;
    }
}
