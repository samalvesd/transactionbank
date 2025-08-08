package apitransactionbank.bank.services;

import apitransactionbank.bank.dto.ContaDto;
import apitransactionbank.bank.dto.TransacaoDto;
import apitransactionbank.bank.entities.Conta;
import apitransactionbank.bank.entities.Transacao;
import apitransactionbank.bank.repositories.ContaRepository;
import apitransactionbank.bank.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {
    private final TransacaoRepository transacaoRepository;
    private final ContaRepository contaRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, ContaRepository contaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.contaRepository = contaRepository;
    }

    @Transactional
    public ContaDto realizaTransacao(final TransacaoDto input) {
        Conta contaEncontrada = getContaEncontrada(input);

        BigDecimal valorTransacao = input.getValor();
        BigDecimal valorTaxado = getValorComTaxa(input, valorTransacao);
        BigDecimal saldoConta = contaEncontrada.getSaldoConta();

        if (valorTaxado.compareTo(saldoConta) > 0)
            throw new IllegalArgumentException("Saldo insuficiente para efetuar a transação.");

        BigDecimal novoSaldo = saldoConta.subtract(valorTaxado);
        contaEncontrada.setSaldoConta(novoSaldo.setScale(2, RoundingMode.HALF_UP));

        contaRepository.save(contaEncontrada);
        transacaoRepository.save(new Transacao(
                input.getFormaPagamento().toUpperCase(),
                valorTaxado,
                input.getNumeroConta()
        ));
        return new ContaDto(contaEncontrada.getNumeroConta(), contaEncontrada.getSaldoConta());
    }

    private Conta getContaEncontrada(TransacaoDto input) {
        return contaRepository.findByNumeroConta(input.getNumeroConta()).orElseThrow(
                () -> new IllegalArgumentException("Conta não encontrada para este número de conta informado.")
        );
    }

    private static BigDecimal getValorComTaxa(TransacaoDto input, BigDecimal valorTransacao) {
        BigDecimal valorComTaxa = switch (input.getFormaPagamento().toUpperCase()) {
            case "P" -> valorTransacao;
            case "D" -> valorTransacao.add(valorTransacao.multiply(new BigDecimal("0.03")));
            case "C" -> valorTransacao.add(valorTransacao.multiply(new BigDecimal("0.05")));
            case null, default -> throw new RuntimeException("Forma de pagamento inválida.");
        };
        return valorComTaxa.setScale(2, RoundingMode.HALF_UP);
    }

    public List<TransacaoDto> listaTransacoes() {
        return transacaoRepository.findAll().stream().map(
                cada -> new TransacaoDto(
                        cada.getFormaPagamento(),
                        cada.getConta(),
                        cada.getValor(),
                        cada.getId()
                )
        ).collect(Collectors.toList());
    }
}
