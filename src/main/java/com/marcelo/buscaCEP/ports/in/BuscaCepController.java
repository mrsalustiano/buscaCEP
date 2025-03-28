package com.marcelo.buscaCEP.ports.in;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.buscaCEP.application.dto.CepSearchDto;
import com.marcelo.buscaCEP.application.service.CepService;
import com.marcelo.buscaCEP.utils.validations.ValidCep;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cep")
@RequiredArgsConstructor
@Validated
public class BuscaCepController {

	  private final CepService cepService;

	    @GetMapping("/{cep}")
	    public CepSearchDto buscarCep(@Valid @ValidCep @PathVariable String cep) {
	        return cepService.buscarCep(cep);
	    }
}
