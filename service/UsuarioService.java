package br.com.fiap.fintech.service;

import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    // injeção pelo construtor (sem Lombok, pra ficar simples)
    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    // LISTAR todos
    public List<Usuario> listar() {
        return repo.findAll();
    }

    // OBTER por id
    public Optional<Usuario> obter(Long id) {
        return repo.findById(id);
    }

    // CRIAR novo
    public Usuario criar(Usuario u) {
        // regra simples: id vem nulo e o banco gera
        return repo.save(u);
    }

    // ATUALIZAR existente
    public Optional<Usuario> atualizar(Long id, Usuario dados) {
        return repo.findById(id).map(existing -> {
            // atualiza só campos relevantes (simples)
            if (dados.getNome() != null)   existing.setNome(dados.getNome());
            if (dados.getEmail() != null)  existing.setEmail(dados.getEmail());
            if (dados.getSenha() != null)  existing.setSenha(dados.getSenha());
            return repo.save(existing);
        });
    }

    // REMOVER por id
    public void remover(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }
        // se não existir, só não faz nada (controller decide o status)
    }
}
