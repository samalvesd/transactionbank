package apitransactionbank.bank.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransacaoDto {
    @JsonProperty(value = "forma_pagamento")
    private String formaPagamento;
    @JsonProperty(value = "numero_conta")
    private Long numeroConta;
    private Float valor;

    public TransacaoDto(String formaPagamento, Long numeroConta, Float valor) {
        this.formaPagamento = formaPagamento;
        this.numeroConta = numeroConta;
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
