package apitransactionbank.bank.services;

import apitransactionbank.bank.repositories.TransacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {
    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }
}
