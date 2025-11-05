package br.com.fiap.fintech.service;

import br.com.fiap.fintech.model.Despesa;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.repository.DespesaRepository;
import br.com.fiap.fintech.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@Service
public class DespesaService {

    private final DespesaRepository despesaRepo;
    private final UsuarioRepository usuarioRepo;

    public DespesaService(DespesaRepository despesaRepo, UsuarioRepository usuarioRepo) {
        this.despesaRepo = despesaRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public Despesa criar(Long usuarioId, Despesa d) {
        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        d.setUsuario(u);
        if (d.getDataDespesa() == null) d.setDataDespesa(new Date());
        return despesaRepo.save(d);
    }

    public List<Despesa> listarPorUsuario(Long usuarioId) {
        return despesaRepo.findByUsuarioId(usuarioId);
    }

    public List<Despesa> listarPorPeriodo(Long usuarioId, Date inicio, Date fim) {
        return despesaRepo.findByUsuarioIdAndDataDespesaBetween(usuarioId, inicio, fim);
    }

    public List<Despesa> buscarPorCategoria(Long usuarioId, String categoria) {
        return despesaRepo.findByUsuarioIdAndCategoriaContainingIgnoreCase(usuarioId, categoria);
    }

    public Despesa obter(Long id) {
        return despesaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada"));
    }

    public Despesa atualizar(Long id, Despesa in) {
        Despesa d = obter(id);
        if (in.getDescricao() != null) d.setDescricao(in.getDescricao());
        if (in.getValor() != null)     d.setValor(in.getValor());
        if (in.getCategoria() != null) d.setCategoria(in.getCategoria());
        if (in.getDataDespesa() != null) d.setDataDespesa(in.getDataDespesa());
        return despesaRepo.save(d);
    }

    public void deletar(Long id) {
        if (!despesaRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada");
        }
        despesaRepo.deleteById(id);
    }
}
