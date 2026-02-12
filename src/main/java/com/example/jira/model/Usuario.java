package com.example.jira.model;

import com.example.jira.enums.Time;

import lombok.*;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    @NotNull(message = "Nome é obrigatório")
    private String nome;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Definir time é obrigatório")
    private Time time;

    @Column(name = "matricula", unique = true)
    private String matricula;

    public Usuario(String nome, Time time) {
        this.nome = nome;
        this.time = time;
    }
}

/*
    matricula é gerada diretamente no Service
*/
