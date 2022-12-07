package com.loja.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Carrinho implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column
    private Double valorT;

    @NotNull
    @Column
    private Double quantidade;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTO")
    private Produto produto;

    public Carrinho(){}

}
