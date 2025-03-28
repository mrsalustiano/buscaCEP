package com.marcelo.buscaCEP.application.dto;

import com.marcelo.buscaCEP.utils.validations.ValidCep;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CepRequestDto {

	@NotNull
	@ValidCep
	private String cep;

}
