package com.loja.api;

import com.loja.model.Pessoa;
import com.loja.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaRepository repository;

    @GetMapping
    public List<Pessoa> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(
            @PathVariable("id") Long id) {
        Optional<Pessoa> pessoa = repository.findById(id);
        if (pessoa.isPresent()) {
            return ResponseEntity.ok(pessoa.get());
        }
        return ResponseEntity.badRequest().body("Id not found");
    }

    @PostMapping
    public void save(@RequestBody Pessoa pessoa) {
        repository.save(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Pessoa pessoa,
                                 @PathVariable Long id) {
        repository.save(pessoa);

        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
