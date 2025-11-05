package br.com.fiap.fintech.service;

import br.com.fiap.fintech.model.Conta;
import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.repository.ContaRepository;
import br.com.fiap.fintech.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepo;
    private final UsuarioRepository usuarioRepo;

    public ContaService(ContaRepository contaRepo, UsuarioRepository usuarioRepo) {
        this.contaRepo = contaRepo;
        this.usuarioRepo = usuarioRepo;
    }

    public Conta criar(Long usuarioId, Conta c) {
        Usuario u = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        c.setUsuario(u);
        if (c.getSaldo() == null) c.setSaldo(BigDecimal.ZERO);
        return contaRepo.save(c);
    }

    public List<Conta> listarPorUsuario(Long usuarioId) {
        return contaRepo.findByUsuarioId(usuarioId);
    }

    public Conta obter(Long id) {
        return contaRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }

    public Conta atualizar(Long id, Conta in) {
        Conta c = obter(id);
        if (in.getTipo() != null)    c.setTipo(in.getTipo());
        if (in.getAgencia() != null) c.setAgencia(in.getAgencia());
        if (in.getNumero() != null)  c.setNumero(in.getNumero());
        if (in.getSaldo() != null)   c.setSaldo(in.getSaldo());
        return contaRepo.save(c);
    }

    public void deletar(Long id) {
        if (!contaRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada");
        }
        contaRepo.deleteById(id);
    }
}
