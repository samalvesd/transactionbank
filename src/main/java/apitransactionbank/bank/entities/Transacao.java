package apitransactionbank.bank.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String formaPagamento;
    private Float valor;
    private Long conta;

    public Transacao() {
    }

    public Transacao(String formaPagamento, Float valor, Long conta) {
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Long getConta() {
        return conta;
    }

    public void setConta(Long conta) {
        this.conta = conta;
    }
}
