package com.example.ob03restdatajpa.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Laptops")
public class Laptop {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private LocalDate ultimaConexión;
    private Integer ubicaciones;
    private String procesador;
    private Boolean isActive;

    // constructores

    public Laptop() {
    }

    public Laptop(Long id, String usuario, LocalDate ultimaConexión, Integer ubicaciones, String procesador, Boolean isActive) {
        this.id = id;
        this.usuario = usuario;
        this.ultimaConexión = ultimaConexión;
        this.ubicaciones = ubicaciones;
        this.procesador = procesador;
        this.isActive = isActive;
    }

    // getter y setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public LocalDate getUltimaConexión() {
        return ultimaConexión;
    }

    public void setUltimaConexión(LocalDate ultimaConexión) {
        this.ultimaConexión = ultimaConexión;
    }

    public Integer getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(Integer ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    // toString CON


    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", ultimaConexión=" + ultimaConexión +
                ", ubicaciones=" + ubicaciones +
                ", procesador='" + procesador + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
