package com.marcelo.buscaCEP.ports.in;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.marcelo.buscaCEP.application.dto.CepSearchDto;
import com.marcelo.buscaCEP.application.service.impl.CepServiceImpl;


@WebMvcTest(BuscaCepController.class) 
class BuscaCepControllerTest {
	 
	
	@MockBean
    private CepServiceImpl cepService; 

    @Autowired
    private MockMvc mockMvc; 

	 

	    

	    @Test
	    void buscarCepTest() throws Exception {
	    	
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
		        		

	          when(cepService.buscarCep("02611-001")).thenReturn(cepDTO);

	          mockMvc.perform(get("/api/cep/02611-001"))
	                  .andExpect(status().isOk())  
	                  .andExpect(jsonPath("$.cep").value("02611-001"))  
	                  .andExpect(jsonPath("$.logradouro").value("Avenida Parada Pinto"));  

	          verify(cepService, times(1)).buscarCep("02611-001");
	    }
}
