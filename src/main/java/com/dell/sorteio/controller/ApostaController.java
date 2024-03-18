package com.dell.sorteio.controller;

import com.dell.sorteio.dto.ApostaDTO;
import com.dell.sorteio.model.Apostador;
import com.dell.sorteio.service.ApostaService;
import com.dell.sorteio.utils.ToModel;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("v1/aposta")
public class ApostaController {

    ApostaService service;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity apostar(@RequestBody ApostaDTO apostaDTO) {
        Apostador apostador = ToModel.getApostadorFromApostaDTO(apostaDTO);
        service.apostar(apostaDTO.getNumeros(), apostaDTO.getSurpresinha(), apostador);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path ="/fecha")
    public ResponseEntity fechaAposta() {
        service.fechaAposta();
        return ResponseEntity.ok().build();
    }
}
