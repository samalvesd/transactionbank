package apitransactionbank.bank.services;

import apitransactionbank.bank.dto.ContaDto;
import apitransactionbank.bank.entities.Conta;
import apitransactionbank.bank.repositories.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    private final ContaRepository repository;

    public ContaService(final ContaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ContaDto criarConta(final ContaDto input) {
        boolean contaExistente = repository.findByNumeroConta(input.getNumeroConta()).isPresent();
        if (contaExistente)
            throw new IllegalArgumentException("Já existe uma conta cadastrada com este número.");

        try {
            repository.save(new Conta(input.getNumeroConta(), input.getSaldo()));
        } catch (RuntimeException returnException) {
            throw new RuntimeException("Erro ao criar conta. Message: " + returnException.getMessage());
        }

        return input;
    }

    public ContaDto buscarConta(final Long numeroConta) {
        Conta contaEncontrada = repository.findByNumeroConta(numeroConta).orElseThrow(
                RuntimeException::new
        );
        return new ContaDto(contaEncontrada.getNumeroConta(), contaEncontrada.getSaldoConta());
    }

}
