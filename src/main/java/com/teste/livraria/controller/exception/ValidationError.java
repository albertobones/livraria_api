package com.teste.livraria.controller.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError extends StandartError {
	
	private List<FieldMessage> fieldErrors = new ArrayList<>();

	public void addError(String error, String message) {
		getFieldErrors().add(new FieldMessage(error, message));
	}

	public ValidationError(Long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	
}