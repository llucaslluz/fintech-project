package br.com.fiap.fintech.repository;

import br.com.fiap.fintech.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    List<Despesa> findByUsuarioId(Long usuarioId);
    List<Despesa> findByUsuarioIdAndDataDespesaBetween(Long usuarioId, Date inicio, Date fim);
    List<Despesa> findByUsuarioIdAndCategoriaContainingIgnoreCase(Long usuarioId, String categoria);
}
