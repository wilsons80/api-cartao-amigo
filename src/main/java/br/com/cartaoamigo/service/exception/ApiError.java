package br.com.cartaoamigo.service.exception;

import java.time.LocalDateTime;

public class ApiError{
	
	private Integer codigo;
	private String mensagem;	
	private LocalDateTime date;
	
	public ApiError() {}
	
	public ApiError(Integer code, String msg, LocalDateTime date) {
		super();
		this.date = date;
		this.codigo = code;
		this.mensagem = msg;
	}
	
	public ApiError(Integer code, String msg) {
		super();
		this.date = LocalDateTime.now();
		this.codigo = code;
		this.mensagem = msg;
	}	

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
