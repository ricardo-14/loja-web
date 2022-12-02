package com.loja.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String nome;

    @NotNull
    @Column(length = 20)
    private String cpf;

    @NotNull
    @Column(length = 50)
    private String email;

    @NotNull
    @Column(length = 20)
    private String senha;

}
