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
@Table(name="usuarios")
public class Usuarios {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_id_usuario")
	@SequenceGenerator(name = "sq_id_usuario", sequenceName = "sq_id_usuario", schema = Constantes.SCHEMA_PUBLIC, initialValue = 1, allocationSize = 1)
	@Column(name="id_usuario")
	private Long id;

	@Column(name="nm_username")
	private String username;
	
	@Column(name="ds_senha")
	private String senha;

	@Column(name="dt_ultimo_acesso")
	private LocalDateTime dataUltimoAcesso;

	@Column(name="qtd_acesso_negado")
	private Long qtdAcessoNegado;

	@Column(name="st_ativo")
	@Convert(converter = SimNaoConverter.class)
	private Boolean stAtivo;

	@Column(name="st_troca_senha")
	@Convert(converter = SimNaoConverter.class)
	private Boolean stTrocaSenha;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pessoa_fisica")
	private PessoaFisica pessoaFisica;

	@Column(name = "id_usuario_apl")
	private Long usuarioAlteracao;
	
	public Usuarios() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public LocalDateTime getDataUltimoAcesso() {
		return dataUltimoAcesso;
	}

	public void setDataUltimoAcesso(LocalDateTime dataUltimoAcesso) {
		this.dataUltimoAcesso = dataUltimoAcesso;
	}

	public Long getQtdAcessoNegado() {
		return qtdAcessoNegado;
	}

	public void setQtdAcessoNegado(Long qtdAcessoNegado) {
		this.qtdAcessoNegado = qtdAcessoNegado;
	}

	public Boolean getStAtivo() {
		return stAtivo;
	}

	public void setStAtivo(Boolean stAtivo) {
		this.stAtivo = stAtivo;
	}

	public Boolean getStTrocaSenha() {
		return stTrocaSenha;
	}

	public void setStTrocaSenha(Boolean stTrocaSenha) {
		this.stTrocaSenha = stTrocaSenha;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public Long getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	public void setUsuarioAlteracao(Long usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}
	
}