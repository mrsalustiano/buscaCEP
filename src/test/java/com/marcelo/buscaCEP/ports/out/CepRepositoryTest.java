package com.marcelo.buscaCEP.ports.out;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import com.marcelo.buscaCEP.domain.entity.CepEntity;



@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class CepRepositoryTest {


	  @Autowired
	    private CepRepository cepRepository;

	    @Test
	    void testSaveCepEntity() {
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
	        CepEntity savedEntity = cepRepository.save(cepEntity);

	        assertNotNull(savedEntity.getId());
	        assertEquals("02611-001", savedEntity.getCep());
	    }
	
	
	
}
