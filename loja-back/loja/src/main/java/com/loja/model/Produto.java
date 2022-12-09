package com.loja.model;

import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @Column(length = 50)
    private String imagem;

    @Column
    private Integer classificacao;

    @NotNull
    @Column
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "ID_MARCA")
    private Marca marca;

    public Produto(){}

}
