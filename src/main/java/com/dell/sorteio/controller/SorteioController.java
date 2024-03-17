package com.dell.sorteio.controller;

import com.dell.sorteio.BO.SorteioBO;
import com.dell.sorteio.dto.ApostaDTO;
import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.service.ApostaService;
import com.dell.sorteio.service.SorteioService;
import com.dell.sorteio.utils.ToModel;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/sorteio")
public class SorteioController {

    SorteioService service;

    @PostMapping(path ="/abre", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity abreSorteio(@RequestParam("nome") String nome) {
        if(nome.isEmpty()){
            //exception
        }
        service.abreSorteio(nome);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path ="/fecha")
    public ResponseEntity fechaSorteio() {
        service.fechaSorteio();
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Apostador>> sortear() {
        List<Apostador> sorteio = service.sortear();
        return ResponseEntity.ok(sorteio);
    }
}
