package com.example.Integro.Entity.Dentist;

import jakarta.persistence.*;

@Entity
@Table(name = "DENTIST", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tuition"})
})
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tuition;
    private String name;
    private String lastName;


    public Dentist() {
    }

    public Dentist(String tuition, String name, String lastName) {
        this.tuition = tuition;
        this.name = name;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTuition() {
        return tuition;
    }

    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "id=" + id +
                ", tuition='" + tuition + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
