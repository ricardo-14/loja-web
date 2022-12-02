package com.loja.model;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String nome;

    @NotNull
    @Column
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA")
    private Marca marca;

    public Produto(){}

}
