package br.com.fiap.fintech.controller;

import br.com.fiap.fintech.model.Despesa;
import br.com.fiap.fintech.service.DespesaService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
public class DespesaController {

    private final DespesaService service;

    public DespesaController(DespesaService service) {
        this.service = service;
    }

    // POST /usuarios/{usuarioId}/despesas -> 201
    @PostMapping(path = "/usuarios/{usuarioId}/despesas", consumes = "application/json")
    public ResponseEntity<Despesa> criar(@PathVariable Long usuarioId, @RequestBody Despesa despesa) {
        Despesa salvo = service.criar(usuarioId, despesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // GET /usuarios/{usuarioId}/despesas?inicio=YYYY-MM-DD&fim=YYYY-MM-DD&categoria=...
    @GetMapping("/usuarios/{usuarioId}/despesas")
    public ResponseEntity<List<Despesa>> listar(
            @PathVariable Long usuarioId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
            @RequestParam(required = false) String categoria) {

        if (categoria != null && !categoria.isBlank()) {
            return ResponseEntity.ok(service.buscarPorCategoria(usuarioId, categoria));
        }
        if (inicio != null && fim != null) {
            Date di = Date.from(inicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date df = Date.from(fim.atStartOfDay(ZoneId.systemDefault()).toInstant());
            return ResponseEntity.ok(service.listarPorPeriodo(usuarioId, di, df));
        }
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    // GET /despesas/{id} -> 200
    @GetMapping("/despesas/{id}")
    public ResponseEntity<Despesa> obter(@PathVariable Long id) {
        return ResponseEntity.ok(service.obter(id));
    }

    // PUT /despesas/{id} -> 200
    @PutMapping(path = "/despesas/{id}", consumes = "application/json")
    public ResponseEntity<Despesa> atualizar(@PathVariable Long id, @RequestBody Despesa in) {
        return ResponseEntity.ok(service.atualizar(id, in));
    }

    // DELETE /despesas/{id} -> 204
    @DeleteMapping("/despesas/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
