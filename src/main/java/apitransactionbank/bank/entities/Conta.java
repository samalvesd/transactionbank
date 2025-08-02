package apitransactionbank.bank.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "Conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numeroConta;
    private Float saldoConta;

    public Conta() {
    }

    public Conta(Long numeroConta, Float saldoConta) {
        this.numeroConta = numeroConta;
        this.saldoConta = saldoConta;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Float getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(Float saldoConta) {
        this.saldoConta = saldoConta;
    }

}
