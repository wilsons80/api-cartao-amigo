package br.com.cartaoamigo.cmd;

import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.UsuariosTOBuilder;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.rule.CamposObrigatoriosUsuariosRule;
import br.com.cartaoamigo.rule.EmailJaCadastradoRule;
import br.com.cartaoamigo.security.CustomPasswordEncoder;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class AlterarUsuarioCmd {

	@Autowired private UsuarioRepository repository;
	@Autowired private CamposObrigatoriosUsuariosRule camposObrigatoriosRule;
	@Autowired private UsuariosTOBuilder toBuilder;
	@Autowired private AlterarPessoaFisicaCmd alterarPessoaFisicaCmd;
	@Autowired private CustomPasswordEncoder customPasswordEncoder;
	@Autowired private AlterarListaPerfilAcessoUsuarioCmd alterarListaPerfilAcessoUsuarioCmd;
	@Autowired private GetUsuarioCmd getCmd;
	@Autowired private EmailJaCadastradoRule emailJaCadastradoRule;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	
	
	public UsuariosTO alterar(UsuariosTO to) {
		camposObrigatoriosRule.verificar(to);
		
		Usuarios usuarioSistema = repository.findById(to.getId()).orElseThrow((() -> new NotFoundException("Usuário informado não existe.")));
		
		// Usuário deve ter mudado a senha.
		if(StringUtils.isNotEmpty(to.getSenha()) && !to.getSenha().equals(usuarioSistema.getSenha())) {
			to.setSenha(customPasswordEncoder.encode(to.getSenha()));
		}
			
		usuarioSistema = toBuilder.build(to);
		
		PessoaFisica pessoaFisica = alterarPessoaFisicaCmd.alterar(to.getPessoaFisica());
		usuarioSistema.setPessoaFisica(pessoaFisica);
		
		emailJaCadastradoRule.verificar(pessoaFisica.getEmail(), pessoaFisica.getId());
			
		if(to.getStTrocaSenha()) {
			String novaSenhaEncode = customPasswordEncoder.encode( Objects.nonNull(to.getPessoaFisica().getCpf()) ? to.getPessoaFisica().getCpf().toString() : "123");
			usuarioSistema.setSenha( novaSenhaEncode );
			usuarioSistema.setQtdAcessoNegado(0L);
		}
		
		Usuarios usuarioSalvo = repository.save(usuarioSistema);
		
		
		Optional<TipoAcessoUsuario> tipoAcessoUsuario = tipoAcessoUsuarioRepository.findByUsuario(usuarioSalvo.getId());
		if(tipoAcessoUsuario.isPresent()) {
			TipoUsuarioSistema tipoUsuarioTO = TipoUsuarioSistema.getPorId(to.getTipoUsuario().getId());
			tipoAcessoUsuario.get().setIdTipoUsuario(tipoUsuarioTO.getId());
			tipoAcessoUsuarioRepository.save(tipoAcessoUsuario.get());
		}
		
		
		to.getGruposAcesso().forEach(p ->p.setIdUsuario(usuarioSalvo.getId()));	
		alterarListaPerfilAcessoUsuarioCmd.alterarAll(to.getGruposAcesso(), usuarioSalvo);
		
		return getCmd.getTOById(usuarioSalvo.getId());
	}
}
