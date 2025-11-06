package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.model.Despesa;
import br.com.fiap.fintech.service.DespesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/despesas", produces = "application/json")
public class DespesaController {

    private final DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }

    // POST /usuarios/{usuarioId}/despesas -> 201 + Location
    @PostMapping(path = "/usuarios/{usuarioId}/despesas", consumes = "application/json")
    public ResponseEntity<Despesa> criar(@PathVariable Long usuarioId, @RequestBody Despesa despesa) {
        Despesa salvo = service.criar(usuarioId, despesa);
        return ResponseEntity
                .created(URI.create("/despesas/" + salvo.getId()))
                .body(salvo);
    }

    // GET /usuarios/{usuarioId}/despesas -> 200
    @GetMapping("/usuarios/{usuarioId}/despesas")
    public ResponseEntity<List<Despesa>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    // GET /despesas/{id} -> 200 ou 404
    @GetMapping("/{id}")
    public ResponseEntity<Despesa> obter(@PathVariable Long id) {
        Despesa d = service.obter(id);
        return (d != null) ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    // PUT /despesas/{id} -> 200 ou 404
    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Despesa> atualizar(@PathVariable Long id, @RequestBody Despesa in) {
        Despesa d = service.atualizar(id, in);
        return (d != null) ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }

    // DELETE /despesas/{id} -> 204 ou 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Despesa d = service.obter(id);
        if (d == null) return ResponseEntity.notFound().build();
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
