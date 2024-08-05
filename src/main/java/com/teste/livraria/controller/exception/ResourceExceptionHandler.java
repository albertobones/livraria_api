package com.teste.livraria.controller.exception;

import javax.servlet.ServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.teste.livraria.service.exception.DataIntegrityViolationException;
import com.teste.livraria.service.exception.ObjectNotFoundException;


@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request) {
		StandartError erro = new StandartError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandartError> dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request) {
		StandartError erro = new StandartError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException e, ServletRequest request) {
		ValidationError erro = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos");
		e.getBindingResult().getFieldErrors().forEach(obj -> erro.addError(obj.getField(), obj.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationError> constraintViolationException(ConstraintViolationException e, ServletRequest request) {
        ValidationError erro = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos");
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            erro.addError(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
	
}