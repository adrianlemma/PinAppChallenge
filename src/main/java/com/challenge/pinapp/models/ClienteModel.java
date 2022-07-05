package com.challenge.pinapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;

@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotNull(message = "Debe ingresar el campo nombre")
    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(nullable = false)
    private String nombre;

    @NotNull(message = "Debe ingresar el campo apellido")
    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(nullable = false)
    private String apellido;

    @NotNull(message = "Debe ingresar el campo edad")
    @Positive(message = "La edad debe ser mayor a cero")
    @Column(nullable = false)
    private int edad;

    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    @NotNull(message = "Debe ingresar el campo fecha_de_nacimiento")
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
