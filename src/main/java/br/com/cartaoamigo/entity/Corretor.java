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
@Table(name = "corretor")
public class Corretor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_corretor")
	@SequenceGenerator(name = "sq_id_corretor", sequenceName = "sq_id_corretor", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name = "id_corretor")
	private Long id;

	@Column(name = "public_key")
	private String publicKey;

	@Column(name = "nr_codigo")
	private String codigo;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "tp_porcentagem")
	private Boolean isPorcentagem;

	@Column(name = "vl_recebimento")
	private Double valorRecebimento;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pessoa_fisica")
	private PessoaFisica pessoaFisica;
	
	@Convert(converter = SimNaoConverter.class)
	@Column(name = "st_ativo")
	private Boolean ativo;	
	
	@Column(name = "dt_cadastro")
	private LocalDateTime dtCadastro;
	
	@Column(name = "LINK_PAGAMENTO")
	private String linkPagamento;	

	@Column(name = "TOKEN")
	private String token;
	
	public Corretor() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public LocalDateTime getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(LocalDateTime dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getIsPorcentagem() {
		return isPorcentagem;
	}

	public void setIsPorcentagem(Boolean isPorcentagem) {
		this.isPorcentagem = isPorcentagem;
	}

	public Double getValorRecebimento() {
		return valorRecebimento;
	}

	public void setValorRecebimento(Double valorRecebimento) {
		this.valorRecebimento = valorRecebimento;
	}

	public String getLinkPagamento() {
		return linkPagamento;
	}

	public void setLinkPagamento(String linkPagamento) {
		this.linkPagamento = linkPagamento;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
}
