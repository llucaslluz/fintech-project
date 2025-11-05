package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios", produces = "application/json")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // POST /usuarios -> 201
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario salvo = service.criar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /usuarios -> 200
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // GET /usuarios/{id} -> 200 (ou 404 via Service)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obter(@PathVariable Long id) {
        return ResponseEntity.ok(service.obter(id));
    }

    // PUT /usuarios/{id} -> 200
    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario in) {
        return ResponseEntity.ok(service.atualizar(id, in));
    }

    // DELETE /usuarios/{id} -> 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
