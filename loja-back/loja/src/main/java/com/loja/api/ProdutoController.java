package com.loja.api;

import com.loja.model.Produto;
import com.loja.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(
            @PathVariable("id") Long id) {
        Optional<Produto> produto = repository.findById(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        }
        return ResponseEntity.badRequest().body("Id not found");
    }

    @PostMapping
    private void save(@RequestBody Produto produto) {
        repository.save(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Produto produto,
                                 @PathVariable Long id) {
        repository.save(produto);

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
