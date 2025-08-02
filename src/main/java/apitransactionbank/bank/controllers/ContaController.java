package apitransactionbank.bank.controllers;

import apitransactionbank.bank.dto.ContaDto;
import apitransactionbank.bank.services.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/conta")
public class ContaController {
    private final ContaService service;

    public ContaController(ContaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ContaDto> criarConta(@RequestBody ContaDto input) {
        ContaDto contaCriada = service.criarConta(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaCriada);
    }

    @GetMapping
    public ResponseEntity<ContaDto> buscarConta(@RequestParam("numero_conta") Long numeroConta) {
        ContaDto contaEncontrada;
        try {
            contaEncontrada = service.buscarConta(numeroConta);
        } catch (RuntimeException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(contaEncontrada);
    }
}
