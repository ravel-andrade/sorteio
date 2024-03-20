package com.dell.sorteio.controller;

import com.dell.sorteio.dto.ApostaDTO;
import com.dell.sorteio.model.Aposta;
import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.service.ApostaService;
import com.dell.sorteio.service.ApostadorService;
import com.dell.sorteio.utils.ToModel;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("v1/aposta")
public class ApostaController {

    ApostaService service;
    ApostadorService apostadorService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity apostar(@RequestBody ApostaDTO apostaDTO) {
        Apostador apostador = ToModel.getApostadorFromApostaDTO(apostaDTO);
        service.apostar(apostaDTO.getNumeros(), apostaDTO.getSurpresinha(), apostador);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path ="/fecha")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity fechaAposta() {
        service.fechaAposta();
        return ResponseEntity.ok().build();
    }

    @GetMapping(path="sorteio-aberto")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Aposta>> apostaSorteioAtual() {
        List<Aposta> apostas = service.getApostasSorteioAberto();
        return ResponseEntity.ok(apostas);
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Aposta>> todasApostas() {
        List<Aposta> apostas = service.getApostas();
        return ResponseEntity.ok(apostas);
    }

    @GetMapping(path="apostadores")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Apostador>> getApostadores() {
        List<Apostador> apostas = apostadorService.getApostadores();
        return ResponseEntity.ok(apostas);
    }
}
