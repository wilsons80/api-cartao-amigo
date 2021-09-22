package br.com.cartaoamigo.ws.pagseguro.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParcelasTO {
	
	private List<ParcelasBandeiraTO> visa;
	private List<ParcelasBandeiraTO> valecard;
	private List<ParcelasBandeiraTO> upbrasil;
	private List<ParcelasBandeiraTO> sorocred;
	private List<ParcelasBandeiraTO> personalcard;
	private List<ParcelasBandeiraTO> mastercard;
	private List<ParcelasBandeiraTO> mais;
	private List<ParcelasBandeiraTO> hipercard;
	private List<ParcelasBandeiraTO> grandcard;
	private List<ParcelasBandeiraTO> fortbrasil;
	private List<ParcelasBandeiraTO> elo;
	private List<ParcelasBandeiraTO> diners;
	private List<ParcelasBandeiraTO> cabal;
	private List<ParcelasBandeiraTO> brasilcard;
	private List<ParcelasBandeiraTO> banesecard;
	private List<ParcelasBandeiraTO> aura;
	private List<ParcelasBandeiraTO> amex;
	
	
	public ParcelasTO() {
	}

	public List<ParcelasBandeiraTO> getVisa() {
		return visa;
	}

	public void setVisa(List<ParcelasBandeiraTO> bandeira) {
		this.visa = bandeira;
	}

	public List<ParcelasBandeiraTO> getValecard() {
		return valecard;
	}

	public void setValecard(List<ParcelasBandeiraTO> valecard) {
		this.valecard = valecard;
	}

	public List<ParcelasBandeiraTO> getUpbrasil() {
		return upbrasil;
	}

	public void setUpbrasil(List<ParcelasBandeiraTO> upbrasil) {
		this.upbrasil = upbrasil;
	}

	public List<ParcelasBandeiraTO> getSorocred() {
		return sorocred;
	}

	public void setSorocred(List<ParcelasBandeiraTO> sorocred) {
		this.sorocred = sorocred;
	}

	public List<ParcelasBandeiraTO> getPersonalcard() {
		return personalcard;
	}

	public void setPersonalcard(List<ParcelasBandeiraTO> personalcard) {
		this.personalcard = personalcard;
	}

	public List<ParcelasBandeiraTO> getMastercard() {
		return mastercard;
	}

	public void setMastercard(List<ParcelasBandeiraTO> mastercard) {
		this.mastercard = mastercard;
	}

	public List<ParcelasBandeiraTO> getMais() {
		return mais;
	}

	public void setMais(List<ParcelasBandeiraTO> mais) {
		this.mais = mais;
	}

	public List<ParcelasBandeiraTO> getHipercard() {
		return hipercard;
	}

	public void setHipercard(List<ParcelasBandeiraTO> hipercard) {
		this.hipercard = hipercard;
	}

	public List<ParcelasBandeiraTO> getGrandcard() {
		return grandcard;
	}

	public void setGrandcard(List<ParcelasBandeiraTO> grandcard) {
		this.grandcard = grandcard;
	}

	public List<ParcelasBandeiraTO> getFortbrasil() {
		return fortbrasil;
	}

	public void setFortbrasil(List<ParcelasBandeiraTO> fortbrasil) {
		this.fortbrasil = fortbrasil;
	}

	public List<ParcelasBandeiraTO> getElo() {
		return elo;
	}

	public void setElo(List<ParcelasBandeiraTO> elo) {
		this.elo = elo;
	}

	public List<ParcelasBandeiraTO> getDiners() {
		return diners;
	}

	public void setDiners(List<ParcelasBandeiraTO> diners) {
		this.diners = diners;
	}

	public List<ParcelasBandeiraTO> getCabal() {
		return cabal;
	}

	public void setCabal(List<ParcelasBandeiraTO> cabal) {
		this.cabal = cabal;
	}

	public List<ParcelasBandeiraTO> getBrasilcard() {
		return brasilcard;
	}

	public void setBrasilcard(List<ParcelasBandeiraTO> brasilcard) {
		this.brasilcard = brasilcard;
	}

	public List<ParcelasBandeiraTO> getBanesecard() {
		return banesecard;
	}

	public void setBanesecard(List<ParcelasBandeiraTO> banesecard) {
		this.banesecard = banesecard;
	}

	public List<ParcelasBandeiraTO> getAura() {
		return aura;
	}

	public void setAura(List<ParcelasBandeiraTO> aura) {
		this.aura = aura;
	}

	public List<ParcelasBandeiraTO> getAmex() {
		return amex;
	}

	public void setAmex(List<ParcelasBandeiraTO> amex) {
		this.amex = amex;
	}
	
	

}
