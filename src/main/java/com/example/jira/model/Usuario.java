package com.example.jira.model;

import com.example.jira.enums.Time;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @Column(name = "time")
    @NotNull(message = "Definir time é obrigatório")
    private Time time;

    public Usuario(String nome, String matricula, Time time) {
        this.nome = nome;
        this.time = time;
    }

}
