package com.uniovi.sdi2425211spring.entities;

public class Professor {
    private Long id;

    private String dni;
    private String name;
    private String surname;

    private String category;


    public Professor(Long id, String dni, String firstName, String surname, String category) {
        this.id = id;
        this.dni = dni;
        this.name = firstName;
        this.surname = surname;
        this.category = category;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

}
