package br.com.fiap.fintech.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USUARIO")
@SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    private Long id;

    @Column(nullable = false, length = 120) private String nome;
    @Column(nullable = false, unique = true, length = 160) private String email;
    @Column(nullable = false, length = 120) private String senha;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_CADASTRO") private Date dataCadastro;

    @PrePersist public void prePersist(){ if (dataCadastro == null) dataCadastro = new Date(); }

    // GETTERS/SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public Date getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(Date dataCadastro) { this.dataCadastro = dataCadastro; }
}
