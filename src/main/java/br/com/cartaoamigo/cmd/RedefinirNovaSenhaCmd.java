package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.AcessoDao;
import br.com.cartaoamigo.dao.repository.RedefinirSenhaRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.RedefinirSenha;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.exception.RedefinirSenhaPeriodoExpiradoException;
import br.com.cartaoamigo.rule.ValidarSenhaInformadaRule;
import br.com.cartaoamigo.security.CustomPasswordEncoder;
import br.com.cartaoamigo.to.RedefinirNovaSenhaTO;

@Component
public class RedefinirNovaSenhaCmd {
	
	@Autowired private RedefinirSenhaRepository repository;
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private ValidarSenhaInformadaRule validarRedefinirNovaSenhaRule;
	@Autowired private CustomPasswordEncoder customPasswordEncoder;
	@Autowired private AcessoDao acessoDao;
	
	public void trocarSenha(RedefinirNovaSenhaTO to) {
		Optional<RedefinirSenha> entity = repository.findById(to.getIdRedefinirSenha());
		if(entity.isPresent()) {
			if(entity.get().getDataValidade().isBefore(LocalDateTime.now())) {
				throw new RedefinirSenhaPeriodoExpiradoException("O período para redefinir essa senha já expirou.");
			}
			
			validarRedefinirNovaSenhaRule.validar(to.getSenha1(), to.getSenha2());
			
			// Recupera o usuario
			Optional<List<Usuarios>> usuarios = usuarioRepository.findByEmail(entity.get().getEmail().toUpperCase());
			if(usuarios.isPresent()) {
				usuarios.get().forEach(usuario -> {
					String senhaEncode = customPasswordEncoder.encode(to.getSenha1());
					acessoDao.trocarSenha(usuario.getUsername(), senhaEncode);
				});
			}

			// Apago o registro que identifica o solicitação de redefinição de senha
			repository.deleteById(entity.get().getId());
		}
	}
	
}
