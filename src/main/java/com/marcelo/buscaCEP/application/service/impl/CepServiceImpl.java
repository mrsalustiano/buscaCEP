package com.marcelo.buscaCEP.application.service.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.marcelo.buscaCEP.application.dto.CepSearchDto;
import com.marcelo.buscaCEP.application.service.CepService;
import com.marcelo.buscaCEP.domain.entity.CepEntity;
import com.marcelo.buscaCEP.ports.out.CepRepository;
import com.marcelo.buscaCEP.ports.out.ViaCepClient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CepServiceImpl implements CepService {

	private final ViaCepClient viaCepClient;
	private final CepRepository cepRepository;

	@Override
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
	        
	        cepRepository.save(cepEntity);
	        return cepDTO;
	        
	}

}
