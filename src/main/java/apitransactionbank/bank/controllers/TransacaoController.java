package apitransactionbank.bank.controllers;

import apitransactionbank.bank.dto.TransacaoDto;
import apitransactionbank.bank.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transacao")
public class TransacaoController {
    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> registraTransacao(@RequestBody TransacaoDto input) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.realizaTransacao(input));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

}
