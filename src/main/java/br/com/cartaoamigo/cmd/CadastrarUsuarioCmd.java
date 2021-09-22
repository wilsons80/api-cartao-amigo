package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.UsuariosTOBuilder;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.rule.CamposObrigatoriosUsuariosRule;
import br.com.cartaoamigo.security.CustomPasswordEncoder;
import br.com.cartaoamigo.to.UsuariosTO;


@Component
public class CadastrarUsuarioCmd {

	@Autowired private UsuarioRepository repository;
	@Autowired private CamposObrigatoriosUsuariosRule camposObrigatoriosRule;
	@Autowired private UsuariosTOBuilder toBuilder;
	@Autowired private CadastrarPessoaFisicaCmd cadastrarPessoaFisicaCmd;
	@Autowired private CustomPasswordEncoder customPasswordEncoder;
	
	
	public UsuariosTO cadastrar(UsuariosTO to) {
		camposObrigatoriosRule.verificar(to);
		to.setSenha(customPasswordEncoder.encode(to.getSenha().trim()));
		
		Usuarios usuarioSistema = toBuilder.build(to);
		if(Objects.isNull(to.getPessoaFisica().getId()) ) {
			usuarioSistema.setPessoaFisica(cadastrarPessoaFisicaCmd.cadastrar(to.getPessoaFisica()));
		}
		
		usuarioSistema.setStAtivo(true);
		Usuarios usuarioSalvo = repository.save(usuarioSistema);		
		
		return toBuilder.buildTO(usuarioSalvo);
	}
	
	
}
