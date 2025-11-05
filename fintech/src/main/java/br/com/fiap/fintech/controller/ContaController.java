package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
public class ContaController {

    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    // POST /usuarios/{usuarioId}/contas -> 201
    @PostMapping(path = "/usuarios/{usuarioId}/contas", consumes = "application/json")
    public ResponseEntity<Conta> criar(@PathVariable Long usuarioId, @RequestBody Conta conta) {
        Conta salvo = service.criar(usuarioId, conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /usuarios/{usuarioId}/contas -> 200
    @GetMapping("/usuarios/{usuarioId}/contas")
    public ResponseEntity<List<Conta>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    // GET /contas/{id} -> 200
    @GetMapping("/contas/{id}")
    public ResponseEntity<Conta> obter(@PathVariable Long id) {
        return ResponseEntity.ok(service.obter(id));
    }

    // PUT /contas/{id} -> 200
    @PutMapping(path = "/contas/{id}", consumes = "application/json")
    public ResponseEntity<Conta> atualizar(@PathVariable Long id, @RequestBody Conta in) {
        return ResponseEntity.ok(service.atualizar(id, in));
    }

    // DELETE /contas/{id} -> 204
    @DeleteMapping("/contas/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
