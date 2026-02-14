package com.example.jira.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mensagem;

    private LocalDateTime dataHoraCriacao;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamado_id")
    private Chamado chamado;

    
    protected Comentario() {
    }

    public Comentario(String mensagem, Usuario autor, Chamado chamado) {
        this.mensagem = mensagem;
        this.autor = autor;
        this.chamado = chamado;
        this.dataHoraCriacao = LocalDateTime.now();
    }

    public Usuario getAutor(){
        return autor;
    }
    public Integer getId(){
        return id;
    }
    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHoraCriacao() {
        return dataHoraCriacao;
    }
    public void setChamado(Chamado chamado){
        this.chamado = chamado;
    }

    @Override
    public String toString() {
        return """
                comentário
                ├─ Autor         : %s
                    ├─ Mensagem  : %s
                    ├─ Data/Hora : %s
                """.formatted(
                this.autor.getMatricula(),
                this.getMensagem(),
                this.getDataHoraCriacao()
               );
    }

}
