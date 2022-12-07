package com.loja.api;

import com.loja.model.Carrinho;
import com.loja.model.Produto;
import com.loja.repository.CarrinhoRepository;
import com.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    @Autowired
    private CarrinhoRepository repository;

    @GetMapping
    public List<Carrinho> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(
            @PathVariable("id") Long id) {
        Optional<Carrinho> carrinho = repository.findById(id);
        if (carrinho.isPresent()) {
            return ResponseEntity.ok(carrinho.get());
        }
        return ResponseEntity.badRequest().body("Id not found");
    }

    @PostMapping
    private void save(@RequestBody Carrinho carrinho) {
        repository.save(carrinho);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Carrinho carrinho,
                                 @PathVariable Long id) {
        repository.save(carrinho);

        return ResponseEntity.ok(carrinho);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
