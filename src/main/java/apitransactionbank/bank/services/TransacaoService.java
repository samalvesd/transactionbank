package apitransactionbank.bank.services;

import apitransactionbank.bank.dto.ContaDto;
import apitransactionbank.bank.dto.TransacaoDto;
import apitransactionbank.bank.entities.Conta;
import apitransactionbank.bank.entities.Transacao;
import apitransactionbank.bank.repositories.ContaRepository;
import apitransactionbank.bank.repositories.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
        try {
            float valorTransacao = input.getValor();
            float valorComTaxa = getValorComTaxa(input, valorTransacao);
            Float saldoConta = contaEncontrada.getSaldoConta();
            if (valorComTaxa > saldoConta) {
                throw new IllegalArgumentException("Saldo insuficiente para efetuar a transação.");
            } else {
                contaEncontrada.setSaldoConta(saldoConta - valorComTaxa);
                contaRepository.save(contaEncontrada);
                transacaoRepository.save(new Transacao(input.getFormaPagamento(), valorComTaxa, input.getNumeroConta()));
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return new ContaDto(contaEncontrada.getNumeroConta(), contaEncontrada.getSaldoConta());
    }

    private Conta getContaEncontrada(TransacaoDto input) {
        return contaRepository.findByNumeroConta(input.getNumeroConta()).orElseThrow(
                () -> new IllegalArgumentException("Conta não encontrada para este número de conta informado.")
        );
    }

    private static float getValorComTaxa(TransacaoDto input, float valorTransacao) {
        return switch (input.getFormaPagamento().toUpperCase()) {
            case "P" -> valorTransacao;
            case "D" -> valorTransacao + valorTransacao * 0.03f;
            case "C" -> valorTransacao + valorTransacao * 0.05f;
            case null, default -> throw new RuntimeException("Forma de pagamento inválida.");
        };
    }

}
