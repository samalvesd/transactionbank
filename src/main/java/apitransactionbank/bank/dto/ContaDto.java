package apitransactionbank.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContaDto {
    @JsonProperty(value = "numero_conta")
    private Long numeroConta;
    private Float saldo;

    public ContaDto(Long numeroConta, Float saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
