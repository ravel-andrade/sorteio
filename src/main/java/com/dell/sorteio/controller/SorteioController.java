package com.dell.sorteio.controller;

import com.dell.sorteio.dto.Resultado;
import com.dell.sorteio.model.Sorteio;
import com.dell.sorteio.service.ApostadorService;
import com.dell.sorteio.service.SorteioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("v1/sorteio")
public class SorteioController {

    SorteioService service;
    ApostadorService apostadorService;

    @PostMapping(path ="/abre")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity abreSorteio(@RequestParam("nome") String nome) {
        if(nome.isEmpty()){
            //exception
        }
        service.abreSorteio(nome);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path ="/aberto")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Sorteio> abreSorteio() {
        return ResponseEntity.ok(service.getSorteioAberto());
    }

    @PostMapping(path ="/fecha")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity fechaSorteio() {
        service.fechaSorteio();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Resultado> sortear() {
        Resultado resultado = service.sortear();
        service.fechaSorteio();
        return ResponseEntity.ok(resultado);
    }
}
