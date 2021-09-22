package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.PerfilAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TipoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.PerfilAcessoUsuario;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.TipoUsuario;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;
import br.com.cartaoamigo.exception.UsuarioAdminstradorException;

@Component
public class ExcluirUsuarioCmd {

	@Autowired private UsuarioRepository repository;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	@Autowired private TipoUsuarioRepository tipoUsuarioRepository;
	@Autowired private PerfilAcessoUsuarioRepository perfilAcessoUsuarioRepository;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	
	public void excluir(Long idUsuario) {
		Optional.ofNullable(idUsuario).orElseThrow(() -> new ParametroNaoInformadoException("Usuário não informado."));
		
		if(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario().equals(idUsuario)) {
			throw new UsuarioAdminstradorException("Não é possível excluir o próprio usuário.");
		}
		
		// Não permite excluir o usuário ROOT
		Optional<TipoAcessoUsuario> tipoAcessoUsuarioAcesso = tipoAcessoUsuarioRepository.findByUsuario(idUsuario);
		if(tipoAcessoUsuarioAcesso.isPresent()) {
			Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findById(tipoAcessoUsuarioAcesso.get().getIdTipoUsuario());
			if(tipoUsuario.isPresent()) {
				if(tipoUsuario.get().getDescricao().toUpperCase().equals(TipoUsuarioSistema.ROOT.getTipo())) {
					throw new UsuarioAdminstradorException("Não é possível excluir o usuário ROOT.");
				}
			}
		}
		
		excluirAcessoUsuario(idUsuario);
	}
	
	
	public void excluirAcessoUsuario(Long idUsuario) {
		Optional<TipoAcessoUsuario> tipoAcessoUsuarioAcesso = tipoAcessoUsuarioRepository.findByUsuario(idUsuario);
		
		//perfil acesso usuario
		Optional<List<PerfilAcessoUsuario>> perfilAcessoUsuario = perfilAcessoUsuarioRepository.findAllByIdUsuario(idUsuario);
		if(perfilAcessoUsuario.isPresent()) {
			perfilAcessoUsuarioRepository.deleteAll(perfilAcessoUsuario.get());
		}
		
		//tipos de acesso
		if(tipoAcessoUsuarioAcesso.isPresent()) {
			tipoAcessoUsuarioRepository.delete(tipoAcessoUsuarioAcesso.get());
		}
		
		repository.deleteById(idUsuario);		
	}
}
