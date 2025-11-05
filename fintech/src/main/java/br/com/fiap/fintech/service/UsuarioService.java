package br.com.fiap.fintech.service;

import br.com.fiap.fintech.model.Usuario;
import br.com.fiap.fintech.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    // Regras: email único
    public Usuario criar(Usuario u) {
        if (repo.existsByEmail(u.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }
        return repo.save(u);
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Usuario obter(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    public Usuario atualizar(Long id, Usuario in) {
        Usuario u = obter(id);
        // se trocar email, validar duplicidade
        if (!u.getEmail().equalsIgnoreCase(in.getEmail()) && repo.existsByEmail(in.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já cadastrado");
        }
        u.setNome(in.getNome());
        u.setEmail(in.getEmail());
        u.setSenha(in.getSenha());
        return repo.save(u);
    }

    public void deletar(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        repo.deleteById(id);
    }
}
