package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios", produces = "application/json")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // POST /usuarios -> 201 + Location
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Usuario> criar(@RequestBody Usuario usuario) {
        Usuario salvo = service.criar(usuario);
        return ResponseEntity
                .created(URI.create("/usuarios/" + salvo.getId()))
                .body(salvo);
    }

    // GET /usuarios -> 200
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // GET /usuarios/{id} -> 200 ou 404
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obter(@PathVariable Long id) {
        Usuario u = service.obter(id);       // seu service retorna Usuario (ou null)
        return (u != null) ? ResponseEntity.ok(u)
                : ResponseEntity.notFound().build();
    }

    // PUT /usuarios/{id} -> 200 ou 404
    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario in) {
        Usuario u = service.atualizar(id, in); // retorne null no service quando não existir
        return (u != null) ? ResponseEntity.ok(u)
                : ResponseEntity.notFound().build();
    }

    // DELETE /usuarios/{id} -> 204 ou 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Usuario u = service.obter(id);
        if (u == null) {
            return ResponseEntity.notFound().build();
        }
        service.deletar(id);                  // **confira o nome do método**: deletar vs remover
        return ResponseEntity.noContent().build();
    }
}
