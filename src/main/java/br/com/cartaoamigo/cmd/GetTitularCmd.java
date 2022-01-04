package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.TitularTOBuilder;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TitularRepository;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PessoaFisicaNaoEncontradaException;
import br.com.cartaoamigo.to.TitularTO;

@Component
public class GetTitularCmd {

	@Autowired private TitularRepository repository;
	@Autowired private TitularTOBuilder toBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository; 
	
	
	public List<TitularTO> getAll() {
		List<Titular> titular = repository.findAll();
		return toBuilder.buildAll(titular);
	}
	
	public TitularTO getTOById(Long id) {
		Optional<Titular> entityOptional =  getById(id);
		return toBuilder.buildTO(entityOptional.get());
	}
	public Optional<Titular> getById(Long id) {
		Optional<Titular> entityOptional =  repository.findById(id);
		if(!entityOptional.isPresent()) {
			throw new NotFoundException("Titular informado não existe: " + id);
		}
		return entityOptional;
	}
	
	
	public Optional<Titular> getByPessoaFisica(Long idPessoaFisica) {
		return repository.findByPessoaFisica(idPessoaFisica);
	}
	public TitularTO getByTOPessoaFisica(Long idPessoaFisica) {
		Optional<Titular> entityOptional = getByPessoaFisica(idPessoaFisica);
		if(!entityOptional.isPresent()) {
			throw new PessoaFisicaNaoEncontradaException("Usuário logado não representa um associado titular.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
	
	public TitularTO getByIdUsuario(Long idUsuario) {
		Long idUsuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario();
		
		Optional<Titular> entityOptional = Optional.empty();
		
		Optional<TipoAcessoUsuario> tipoAcessoUsuario = tipoAcessoUsuarioRepository.findByUsuario(idUsuarioLogado);
		if(tipoAcessoUsuario.isPresent()) {
			if(tipoAcessoUsuario.get().getIdTipoUsuario().equals(TipoUsuarioSistema.ASSOCIADO_TITULAR.getId())) {
				entityOptional = repository.findTitularByIdUsuarioTitular(idUsuarioLogado);
			}
			if(tipoAcessoUsuario.get().getIdTipoUsuario().equals(TipoUsuarioSistema.ASSOCIADO_DEPENDENTE.getId())) {
				entityOptional = repository.findTitularByIdUsuarioDependente(idUsuarioLogado);
			}
		}
		
		if(!entityOptional.isPresent()) {
			throw new PessoaFisicaNaoEncontradaException("Usuário não representa um associado.");
		}
		return toBuilder.buildTO(entityOptional.get());
	}
	

	
}

