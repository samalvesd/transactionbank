package apitransactionbank.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransacaoDto {
    @JsonProperty(value = "forma_pagamento")
    private final String formaPagamento;
    @JsonProperty(value = "numero_conta")
    private final Long numeroConta;
    private final BigDecimal valor;

    public TransacaoDto(String formaPagamento, Long numeroConta, BigDecimal valor, Long id) {
        this.formaPagamento = formaPagamento;
        this.numeroConta = numeroConta;
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public BigDecimal getValor() {
        return valor;
    }

}
