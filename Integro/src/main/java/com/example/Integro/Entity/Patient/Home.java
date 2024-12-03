package com.example.Integro.Entity.Patient;

import jakarta.persistence.*;

@Entity
@Table(name = "HOME", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int number;
    private String locality;
    private String province;


    public Home() {
    }

    public Home(String street, int number, String locality, String province) {
        this.street = street;
        this.number = number;
        this.locality = locality;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
