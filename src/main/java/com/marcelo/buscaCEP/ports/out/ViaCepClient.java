package com.marcelo.buscaCEP.ports.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.marcelo.buscaCEP.application.dto.CepSearchDto;

@FeignClient(name = "viacep-client", url = "${viacep.url}")
public interface ViaCepClient {
	
	@GetMapping("{cep}/json/")
    CepSearchDto getCepInfo(@PathVariable("cep") String cep);

}
