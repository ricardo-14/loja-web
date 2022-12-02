package com.loja.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProdutoImagens {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    @Transient
    private byte[] arquivo;

}
