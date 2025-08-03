package apitransactionbank.bank.controllers;

import apitransactionbank.bank.dto.TransacaoDto;
import apitransactionbank.bank.services.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<TransacaoDto>>  listaTransacoes() {
        return ResponseEntity.ok().body(service.listaTransacoes());
    }

}
