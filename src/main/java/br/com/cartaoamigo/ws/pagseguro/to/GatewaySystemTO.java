package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

public class GatewaySystemTO {
	private List<String> type;
	private List<String> authorizationCode;
	private List<String> nsu;
	private List<String> tid;
	private List<String> establishmentCode;
	private List<String> acquirerName;
	
	public GatewaySystemTO() {
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getAuthorizationCode() {
		return authorizationCode;
	}

	public void setAuthorizationCode(List<String> authorizationCode) {
		this.authorizationCode = authorizationCode;
	}

	public List<String> getNsu() {
		return nsu;
	}

	public void setNsu(List<String> nsu) {
		this.nsu = nsu;
	}

	public List<String> getTid() {
		return tid;
	}

	public void setTid(List<String> tid) {
		this.tid = tid;
	}

	public List<String> getEstablishmentCode() {
		return establishmentCode;
	}

	public void setEstablishmentCode(List<String> establishmentCode) {
		this.establishmentCode = establishmentCode;
	}

	public List<String> getAcquirerName() {
		return acquirerName;
	}

	public void setAcquirerName(List<String> acquirerName) {
		this.acquirerName = acquirerName;
	}
	
}
