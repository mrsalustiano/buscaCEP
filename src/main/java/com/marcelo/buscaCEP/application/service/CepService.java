package com.marcelo.buscaCEP.application.service;

import com.marcelo.buscaCEP.application.dto.CepSearchDto;

public interface CepService {

	  CepSearchDto buscarCep(String cep);
}
