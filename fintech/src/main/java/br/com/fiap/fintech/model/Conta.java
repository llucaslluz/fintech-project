package br.com.fiap.fintech.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "CONTA")
@SequenceGenerator(name = "SEQ_CONTA", sequenceName = "SEQ_CONTA", allocationSize = 1)
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CONTA")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(length = 30) private String tipo;
    @Column(length = 20) private String agencia;
    @Column(length = 30) private String numero;

    @Column(precision = 15, scale = 2) private BigDecimal saldo = BigDecimal.ZERO;

    // GETTERS/SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getAgencia() { return agencia; }
    public void setAgencia(String agencia) { this.agencia = agencia; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public BigDecimal getSaldo() { return saldo; }
    public void setSaldo(BigDecimal saldo) { this.saldo = saldo; }
}
