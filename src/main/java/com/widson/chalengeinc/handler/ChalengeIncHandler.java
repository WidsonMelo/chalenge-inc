package com.widson.chalengeinc.handler;

import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.widson.chalengeinc.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ChalengeIncHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsr = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());

		return handleExceptionInternal(ex, mensagemUsr, headers, HttpStatus.BAD_REQUEST, request);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String mensagemUsr = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());

		return handleExceptionInternal(ex, mensagemUsr, headers, HttpStatus.BAD_REQUEST, request);

	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String mensagemUsr = messageSource.getMessage("recurso.operacao-nao-permitida", null,
				LocaleContextHolder.getLocale());
		return handleExceptionInternal(ex, mensagemUsr, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ IllegalArgumentException.class })
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		String mensagemUsr = messageSource.getMessage("recurso.dados-incompletos", null,
				LocaleContextHolder.getLocale());
		return handleExceptionInternal(ex, mensagemUsr, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ NonUniqueResultException.class })
	public ResponseEntity<Object> handleNonUniqueResultException(NonUniqueResultException ex, WebRequest request) {
		String mensagemUsr = messageSource.getMessage("recurso.cadastro-replicado", null,
				LocaleContextHolder.getLocale());
		return handleExceptionInternal(ex, mensagemUsr, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ ObjectNotFoundException.class })
	public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException ex, WebRequest request) {
		String mensagemUsr = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
		return handleExceptionInternal(ex, mensagemUsr, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

}
