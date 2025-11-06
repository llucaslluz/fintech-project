package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/contas", produces = "application/json")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    // POST /usuarios/{usuarioId}/contas  -> 201 + Location
    @PostMapping(path = "/usuarios/{usuarioId}/contas", consumes = "application/json")
    public ResponseEntity<Conta> criar(@PathVariable Long usuarioId, @RequestBody Conta conta) {
        Conta salvo = service.criar(usuarioId, conta);
        return ResponseEntity
                .created(URI.create("/contas/" + salvo.getId()))
                .body(salvo);
    }

    // GET /usuarios/{usuarioId}/contas  -> 200
    @GetMapping("/usuarios/{usuarioId}/contas")
    public ResponseEntity<List<Conta>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    // GET /contas/{id}  -> 200 ou 404
    @GetMapping("/{id}")
    public ResponseEntity<Conta> obter(@PathVariable Long id) {
        Conta c = service.obter(id);
        return (c != null) ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    // PUT /contas/{id}  -> 200 ou 404
    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @RequestBody Conta in) {
        Conta c = service.atualizar(id, in);
        return (c != null) ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
    }

    // DELETE /contas/{id} -> 204 ou 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Conta c = service.obter(id);
        if (c == null) return ResponseEntity.notFound().build();
        service.deletar(id); // se no seu service for "remover", troque aqui
        return ResponseEntity.noContent().build();
    }
}
