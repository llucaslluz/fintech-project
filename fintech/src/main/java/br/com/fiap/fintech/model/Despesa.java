package br.com.fiap.fintech.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "DESPESA")
@SequenceGenerator(name = "SEQ_DESPESA", sequenceName = "SEQ_DESPESA", allocationSize = 1)
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DESPESA")
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    private Usuario usuario;

    @Column(length = 200) private String descricao;
    @Column(precision = 15, scale = 2) private BigDecimal valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_DESPESA") private Date dataDespesa;

    @Column(length = 60) private String categoria;

    @PrePersist public void prePersist(){ if (dataDespesa == null) dataDespesa = new Date(); }

    // GETTERS/SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }

    public Date getDataDespesa() { return dataDespesa; }
    public void setDataDespesa(Date dataDespesa) { this.dataDespesa = dataDespesa; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
