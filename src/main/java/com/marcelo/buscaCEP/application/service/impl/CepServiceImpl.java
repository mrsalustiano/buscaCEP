package com.marcelo.buscaCEP.application.service.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.marcelo.buscaCEP.application.dto.CepSearchDto;
import com.marcelo.buscaCEP.application.service.CepService;
import com.marcelo.buscaCEP.domain.entity.CepEntity;
import com.marcelo.buscaCEP.ports.out.CepRepository;
import com.marcelo.buscaCEP.ports.out.ViaCepClient;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CepServiceImpl implements CepService {

	private final ViaCepClient viaCepClient;
	private final CepRepository cepRepository;

	@Override
	@Transactional
	public CepSearchDto buscarCep(String cep) {
	
		  CepSearchDto cepDTO = viaCepClient.getCepInfo(cep);
		  
		  
	        CepEntity cepEntity = CepEntity.builder()
	        		.cep(cepDTO.getCep())
	        		.logradouro(cepDTO.getLogradouro())
	        		.complemento(cepDTO.getComplemento())
	        		.unidade(cepDTO.getUnidade())
	        		.bairro(cepDTO.getBairro())
	        		.localidade(cepDTO.getLocalidade())
	        		.uf(cepDTO.getUf())
	        		.estado(cepDTO.getEstado())
	        		.regiao(cepDTO.getRegiao())
	        		.ibge(cepDTO.getIbge())
	        		.gia(cepDTO.getGia())
	        		.ddd(cepDTO.getDdd())
	        		.siafi(cepDTO.getSiafi())
	        		.searchDate(LocalDateTime.now())
	        		.build();
	      
	        
	        if (cepEntity.getCep() == null || cepEntity.getCep().isBlank()) {
	        	 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o CEP informado.");
	        }
		  
	        
	        cepRepository.save(cepEntity);
	        return cepDTO;
	        
	}

}
