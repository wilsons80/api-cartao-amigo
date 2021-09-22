package br.com.cartaoamigo.cmd;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.UsuariosTOBuilder;
import br.com.cartaoamigo.dao.GetUsuarioDao;
import br.com.cartaoamigo.dao.dto.UsuarioComboDTO;
import br.com.cartaoamigo.dao.dto.UsuarioDTO;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.rule.UsuarioSistemaNaoEncontradoRule;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class GetUsuarioCmd {
	
	@Autowired private UsuarioRepository repository;
	@Autowired private UsuariosTOBuilder toBuilder;
	@Autowired private GetUsuarioDao getUsuarioSistemaDao;
	@Autowired private UsuarioSistemaNaoEncontradoRule usuarioSistemaNaoEncontradoRule;
	@Autowired private GetUsuarioDao getUsuarioDao;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public List<UsuariosTO> getAll() {
		List<Long> idsTiposUsuarios = Arrays.asList(TipoUsuarioSistema.ADMINISTRATIVO.getId(), TipoUsuarioSistema.CLINICA.getId());
		Optional<List<Usuarios>> usuarios = repository.findAllByTipoUsuario(idsTiposUsuarios);
		if(usuarios.isPresent()) {
			return toBuilder.buildAll(usuarios.get());
		}		
		return null;
	}
	
	public Usuarios get(String username) {
		Optional<Usuarios> usuarioSistema = getUsuarioSistemaDao.getUsuarioByUsername(username);
		usuarioSistemaNaoEncontradoRule.verificar(usuarioSistema);
		return usuarioSistema.get();
	}
	
	public UsuariosTO getTOById(Long id) {
		Usuarios entity = repository.findById(id).orElseThrow(() ->  new NotFoundException("Usuário não encontrado."));
		return toBuilder.buildTO(entity);
	}	

	public Usuarios getById(Long id) {
		return repository.findById(id).orElseGet(null);
	}	
	

	public List<UsuarioDTO> getAllFilterUsuario(Long idUsuario, LocalDate dataInicioCadastro, LocalDate dataFimCadastro,  String ativo) {
		List<UsuarioDTO> usuario = getUsuarioDao.getAllFilterUsuario(idUsuario, dataInicioCadastro, dataFimCadastro, ativo);
		if(usuario.isEmpty()) {return null;}
		
		
	 	UsuariosTO usuarioLogadoTO = getTOById(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
	 	if(!usuarioLogadoTO.getTipoUsuario().getId().equals(TipoUsuarioSistema.ROOT.getId())) {
	 		return usuario.stream().filter(u -> !u.getIdTipoUsuario().equals(TipoUsuarioSistema.ROOT.getId())).collect(Collectors.toList());
	 	}
		
		return usuario;
	}
	
	
	public List<UsuarioComboDTO> getAllCombo() {
		List<UsuarioComboDTO> usuarios = getUsuarioDao.getAllCombo();
		if(usuarios.isEmpty()) {return null;}
		return usuarios;
	}

}
