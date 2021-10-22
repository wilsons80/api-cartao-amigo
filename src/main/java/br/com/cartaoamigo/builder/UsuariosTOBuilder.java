package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetPerfilAcessoUsuarioCmd;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TipoUsuarioRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.TipoUsuario;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;
import br.com.cartaoamigo.to.UsuariosTO;


@Component
public class UsuariosTOBuilder {
	
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private TipoUsuarioTOBuilder tipoUsuarioTOBuilder;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	@Autowired private TipoUsuarioRepository tipoUsuarioRepository;
	@Autowired private GetPerfilAcessoUsuarioCmd getPerfilAcessoUsuarioCmd;
	
	public Usuarios build(UsuariosTO p) {
		Usuarios retorno = new Usuarios();
	
		BeanUtils.copyProperties(p,  retorno);
		
		if(StringUtils.isNotEmpty(p.getUsername())) {
			retorno.setUsername(p.getUsername().toLowerCase());
		}

		Optional.ofNullable(p.getStAtivo()).ifPresent(valor -> {
			retorno.setStAtivo(valor);
		});
		
		Optional.ofNullable(p.getStTrocaSenha()).ifPresent(valor -> {
			retorno.setStTrocaSenha(valor);
		});
		
		if(Objects.nonNull(p.getPessoaFisica()) && Objects.nonNull(p.getPessoaFisica().getId())) {
			Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(p.getPessoaFisica().getId());
			retorno.setPessoaFisica(pessoaFisica.get());
		}else {
			retorno.setPessoaFisica(pessoaFisicaTOBuilder.build(p.getPessoaFisica()));
		}
			

		return retorno;
	}

	public UsuariosTO buildTO(Usuarios p) {
		UsuariosTO retorno = new UsuariosTO();
		
		BeanUtils.copyProperties(p,  retorno);
		
		Optional.ofNullable(p.getStAtivo()).ifPresent(valor -> {
			retorno.setStAtivo(valor);
		});
		
		Optional.ofNullable(p.getStTrocaSenha()).ifPresent(valor -> {
			retorno.setStTrocaSenha(valor);
		});
			
		retorno.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(p.getPessoaFisica()));
		
		Optional<TipoAcessoUsuario> tipoAcessoUsuario = tipoAcessoUsuarioRepository.findByUsuario(p.getId());
		if(tipoAcessoUsuario.isPresent()) {
			Long idTipoUsuario = tipoAcessoUsuario.get().getIdTipoUsuario();
			
			Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(idTipoUsuario);
			if(tipoUsuario.isPresent()) {
				retorno.setTipoUsuario(tipoUsuarioTOBuilder.buildTO(tipoUsuario.get()));
			}
		}
		
		List<PerfilAcessoUsuarioTO> gruposAcesso = getPerfilAcessoUsuarioCmd.getAllTOByUsuario(p.getId());
		retorno.setGruposAcesso(gruposAcesso);
		
		return retorno;
	}
	

	public List<UsuariosTO> buildAll(List<Usuarios> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
}
