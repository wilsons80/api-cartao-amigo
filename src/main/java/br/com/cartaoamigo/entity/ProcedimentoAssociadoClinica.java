package br.com.cartaoamigo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cartaoamigo.infra.constantes.Constantes;
import br.com.cartaoamigo.infra.dao.SimNaoConverter;

@Entity
@Table(name = "procedimento_associado_clinica")
public class ProcedimentoAssociadoClinica {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_procedimento_associado_clinica")
	@SequenceGenerator(name = "sq_id_procedimento_associado_clinica", sequenceName = "sq_id_procedimento_associado_clinica", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_procedimento_associado_clinica")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_clinica_tipo_especidades")
	private ClinicasTipoEspecialidades clinicaTipoEspecidades;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cartao")
	private Cartao cartao;
	
	@Column(name = "dt_procedimento")
	private LocalDateTime dataProcedimento;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "assinatura_ativa")
	private Boolean assinaturaAtiva;
	
	
	public ProcedimentoAssociadoClinica() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClinicasTipoEspecialidades getClinicaTipoEspecidades() {
		return clinicaTipoEspecidades;
	}

	public void setClinicaTipoEspecidades(ClinicasTipoEspecialidades clinicaTipoEspecidades) {
		this.clinicaTipoEspecidades = clinicaTipoEspecidades;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public LocalDateTime getDataProcedimento() {
		return dataProcedimento;
	}

	public void setDataProcedimento(LocalDateTime dataProcedimento) {
		this.dataProcedimento = dataProcedimento;
	}

	public Boolean getAssinaturaAtiva() {
		return assinaturaAtiva;
	}

	public void setAssinaturaAtiva(Boolean assinaturaAtiva) {
		this.assinaturaAtiva = assinaturaAtiva;
	}

	
}