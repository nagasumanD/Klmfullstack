package com.klm.client.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	Logger logger = org.slf4j.LoggerFactory.getLogger(GlobalException.class);

	

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type "
				+ ex.getRequiredType();

		final ExceptionResponse apiError = new ExceptionResponse(error, "Bad Request", 400);
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
			final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		final List<String> errors = new ArrayList<String>();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
		}
		final ExceptionResponse apiError = new ExceptionResponse(errors.toString(), "Bad Request", 400);
		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(FileNotFoundExceptionHandler.class)
	public ResponseEntity<ExceptionResponse> filenotfoundexception() {
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse("Something went wrong please try later", "File Not Found", 500),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(IOExceptionHandler.class)
	public ResponseEntity<ExceptionResponse> ioException() {
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse("Something went wrong please try later", "IOException", 500),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

		final ExceptionResponse apiError = new ExceptionResponse(builder.toString(), "Unsupported Media Type", 415);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status,
			final WebRequest request) {
		logger.info(ex.getClass().getName());
		//
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

		final ExceptionResponse apiError = new ExceptionResponse(builder.toString(), "Request Method Not allowed", 405);
		return new ResponseEntity<Object>(apiError, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info("No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL());
		final ExceptionResponse apiError = new ExceptionResponse("NOT_FOUND", "No Hanler found for this exception",
				500);
		return new ResponseEntity<Object>(apiError, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionResponse> globalexceptionHandler() {
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse("Something went wrong please try later", "Internal Server Error", 500),
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
