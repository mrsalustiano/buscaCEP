package com.marcelo.buscaCEP.application.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.marcelo.buscaCEP.application.dto.CepSearchDto;
import com.marcelo.buscaCEP.application.service.CepService;
import com.marcelo.buscaCEP.domain.entity.CepEntity;
import com.marcelo.buscaCEP.ports.out.CepRepository;
import com.marcelo.buscaCEP.ports.out.ViaCepClient;

@ExtendWith(MockitoExtension.class)
class CepServiceImplTest {

	 @Mock
	    private ViaCepClient viaCepClient;

	    @Mock
	    private CepRepository cepRepository;

	    @InjectMocks
	    private CepServiceImpl cepService;

	    @Test
	    void buscarCepTest() {
	           CepSearchDto cepDTO = new CepSearchDto().builder()
	        		.cep("02611-001")
	        		.logradouro("Avenida Parada Pinto")
	        		.complemento("de 1502 ao fim - lado par")
	        		.bairro("Vila Nova Cachoeirinha")
	        		.localidade("São Paulo")
	        		.uf("SP")
	        		.estado("São Paulo")
	        		.ibge("3550308")
	        		.gia("1004")
	        		.ddd("11")
	        		.siafi("7107")
	        		.build();
	        		
	        		
	        when(viaCepClient.getCepInfo("02611-001")).thenReturn(cepDTO);

	        CepEntity cepEntity = new CepEntity().builder()
	        		.id(null)
	        		.cep("02611-001")
	        		.logradouro("Avenida Parada Pinto")
	        		.complemento("de 1502 ao fim - lado par")
	        		.bairro("Vila Nova Cachoeirinha")
	        		.localidade("São Paulo")
	        		.uf("SP")
	        		.estado("São Paulo")
	        		.ibge("3550308")
	        		.gia("1004")
	        		.ddd("11")
	        		.siafi("7107")
	        		.searchDate(LocalDateTime.now())
	        		.build();
	        		
	        		
	        
	        when(cepRepository.save(any(CepEntity.class))).thenReturn(cepEntity);

	        CepSearchDto resultado = cepService.buscarCep("02611-001");

	        assertNotNull(resultado);
	        assertEquals("02611-001", resultado.getCep());
	        verify(cepRepository, times(1)).save(any(CepEntity.class));
	    }
	
	
}
