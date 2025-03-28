package com.marcelo.buscaCEP.domain.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CEP_SEARCH")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CepEntity {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

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
	    private LocalDateTime searchDate;
		
	

	    
	    
		
}
