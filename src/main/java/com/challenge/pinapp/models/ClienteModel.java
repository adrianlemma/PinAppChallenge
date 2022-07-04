package com.challenge.pinapp.models;

import com.challenge.pinapp.exceptions.ClienteException;

import javax.persistence.*;
import java.time.OffsetDateTime;

import static java.util.Objects.nonNull;

@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private int edad;

    @Column(nullable = false)
    private OffsetDateTime fechaDeNacimiento;

    private OffsetDateTime fechaProbableDeMuerte;

    //Constructors

    public ClienteModel() { }

    public ClienteModel(String nombre, String apellido, int edad, OffsetDateTime fechaDeNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    //Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public OffsetDateTime getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(OffsetDateTime fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public OffsetDateTime getFechaProbableDeMuerte() {
        return fechaProbableDeMuerte;
    }

    public void setFechaProbableDeMuerte(OffsetDateTime fechaProbableDeMuerte) {
        this.fechaProbableDeMuerte = fechaProbableDeMuerte;
    }
}
