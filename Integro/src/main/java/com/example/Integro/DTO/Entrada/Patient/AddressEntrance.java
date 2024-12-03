package com.example.Integro.DTO.Entrada.Patient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressEntrance {

    @NotNull(message = "La calle no puede ser nula")
    @NotBlank(message = "Debe especificarse el nombre de la calle")
    private String street;

    @NotNull(message = "El numero no puede ser nulo")
    @Digits(integer = 8, fraction = 0, message = "El número debe tener como máximo 8 dígitos")
    private Integer number;

    @NotNull(message = "La localidad no puede ser nula")
    @NotBlank(message = "Debe especificarse la localidad")
    private String locality;

    @NotNull(message = "La provincia no puede ser nula")
    @NotBlank(message = "Debe especificarse el nombre de la provincia")
    private String province;


    public AddressEntrance() {
    }

    public AddressEntrance(String street, Integer number, String locality, String province) {
        this.street = street;
        this.number = number;
        this.locality = locality;
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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
