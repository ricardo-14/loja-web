package com.loja.repository;

import com.loja.model.ProdutoImagens;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoImagensRepository extends JpaRepository<ProdutoImagens, Long> {
    public List<ProdutoImagens> findByProdutoId(Long id);
}
