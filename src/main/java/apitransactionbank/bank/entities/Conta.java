package apitransactionbank.bank.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final Long numeroConta;
    private BigDecimal saldoConta;

    public Conta(Long numeroConta, BigDecimal saldoConta) {
        this.numeroConta = numeroConta;
        this.saldoConta = saldoConta;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public BigDecimal getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(BigDecimal saldoConta) {
        this.saldoConta = saldoConta;
    }

}
