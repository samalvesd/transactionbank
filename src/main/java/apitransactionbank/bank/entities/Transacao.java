package apitransactionbank.bank.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String formaPagamento;
    private final BigDecimal valor;
    private final Long conta;

    public Transacao(String formaPagamento, BigDecimal valor, Long conta) {
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.conta = conta;
    }

    public Long getId() {
        return id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getConta() {
        return conta;
    }

}
