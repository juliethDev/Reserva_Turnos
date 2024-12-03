package com.example.Integro.Entity.Loggin;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USER",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
} )
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Roles_User",
            joinColumns = @JoinColumn(name = "User_id"),
            inverseJoinColumns = @JoinColumn(name = "Role_id")
    )
    private Set<Role> rol = new HashSet<>();


    public User() {
    }


    public User(String username, String password, Set<Role> rol) {
        this.username = username;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String nombreUsuario) {
        this.username = username;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String contraseña) {
        this.password = contraseña;
    }

    public Set<Role> getRol() {
        return rol;
    }

    public void setRol(Set<Role> rol) {
        this.rol = rol;
    }
}
