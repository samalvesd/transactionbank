package apitransactionbank.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ContaDto {
    @JsonProperty(value = "numero_conta")
    private final Long numeroConta;
    private BigDecimal saldo;

    public ContaDto(Long numeroConta, BigDecimal saldo) {
        this.numeroConta = numeroConta;
        this.saldo = saldo;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

}
