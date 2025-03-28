package com.marcelo.buscaCEP.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CepSearchDto {

	   private String cep;
	    private String logradouro;
	    private String complemento;
	    private String unidade;
	    private String bairro;
	    private String localidade;
	    private String uf;
	    private String estado;
	    private String regiao;
	    private String ibge;
	    private String gia;
	    private String ddd;
	    private String siafi;

	    
	    
	  
}
