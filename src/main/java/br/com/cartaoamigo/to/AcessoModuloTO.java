package br.com.cartaoamigo.to;

public class AcessoModuloTO {
	
	private Long id;
	private String descricao;
	private String nome;
	
	
	public AcessoModuloTO() {
	}
	
	public AcessoModuloTO(String descricao,Long id, String nome) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "AcessoUnidadeTO [id=" + id + ", descricao=" + descricao + ", nome=" + nome + "]";
	}

	
}
