package br.com.cartaoamigo.service.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorList extends ApiError {

	private List<String> errors;
	
	public ApiErrorList(int code, String msg, LocalDateTime date, List<String> errors) {
		super(code, msg, date);
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	
	
}
