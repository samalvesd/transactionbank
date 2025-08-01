package apitransactionbank.bank.services;

import apitransactionbank.bank.repositories.ContaRepository;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
    private final ContaRepository repository;

    public ContaService(ContaRepository repository) {
        this.repository = repository;
    }
}
