package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.DependentesTitularTOBuilder;
import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.builder.UsuariosTOBuilder;
import br.com.cartaoamigo.dao.repository.DependentesTitularRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TitularRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.DependentesTitular;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.enums.TipoEmail;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.PessoaFisicaJaExisteException;
import br.com.cartaoamigo.exception.TitularJaExisteException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.rule.CamposObrigatoriosPessoaFisicaRule;
import br.com.cartaoamigo.to.DependentesTitularTO;
import br.com.cartaoamigo.to.EnvioEmailTO;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class CadastrarDependentesTitularCmd {

	@Autowired private DependentesTitularRepository dependentesTitularRepository;
	@Autowired private CadastrarPessoaFisicaCmd cadastrarPessoaFisicaCmd;
	@Autowired private CamposObrigatoriosPessoaFisicaRule camposObrigatoriosPessoaFisicaRule;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private DependentesTitularTOBuilder dependentesTitularTOBuilder;
	@Autowired private CadastrarCartaoCmd cadastrarCartaoCmd;
	@Autowired private AtribuirPermissaoAcessoAssociadoDependenteCmd atribuirPermissaoAcessoAssociadoDependenteCmd;
	@Autowired private CadastrarUsuarioCmd cadastrarUsuarioCmd;
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	@Autowired private UsuariosTOBuilder usuariosTOBuilder;
	@Autowired private GravarEnvioEmailCmd gravarEnvioEmailCmd;
	@Autowired private TitularRepository titularRepository;
	
	
	public List<DependentesTitularTO> cadastrarAll(Titular titular, List<DependentesTitularTO> listaTO) {
		Optional.ofNullable(listaTO).ifPresent(lista -> {
			lista.forEach(to -> cadastrar(to, titular));
		});
		return listaTO;
	}
	
	
	public void cadastrar(DependentesTitularTO to, Titular titular) {
		camposObrigatoriosPessoaFisicaRule.verificar(to.getPessoaFisica());
		
		Optional<PessoaFisica> email = pessoaFisicaRepository.findByEmail(to.getPessoaFisica().getEmail().toUpperCase());
		if(email.isPresent()) {
			throw new PessoaFisicaJaExisteException("O e-mail '" + to.getPessoaFisica().getEmail() +"' já está cadastrado para outro associado.");
		}
		
		Optional<Titular> jaExisteTitular = titularRepository.findByCPF(to.getPessoaFisica().getCpf());
		if(jaExisteTitular.isPresent()) {
			throw new TitularJaExisteException("Já existe um titular com o CPF: " + StringUtil.mascaraCPF(to.getPessoaFisica().getCpf() ));
		}
		
		// Verifica se já existe PESSOA_FISICA com o CPF do dependente
		PessoaFisica pessoaFisica = null;
		Optional<PessoaFisica> pessoaFisicaOptional = pessoaFisicaRepository.findByCpf(to.getPessoaFisica().getCpf());
		if(pessoaFisicaOptional.isPresent()) {
			pessoaFisica = pessoaFisicaTOBuilder.buildNewEntity(to.getPessoaFisica(), pessoaFisicaOptional.get());
		} else {
			pessoaFisica = cadastrarPessoaFisicaCmd.cadastrar(to.getPessoaFisica());
		}

		DependentesTitular entity = new DependentesTitular();;
		entity.setAtivo(to.getAtivo());
		entity.setPessoaFisica(pessoaFisica);
		entity.setIdTitular(titular.getId());
		entity.setDtCadastro(LocalDateTime.now());
		entity.setExclusaoLogica(false);
		
		// Salvo o dependente
		DependentesTitular dependenteTitular = dependentesTitularRepository.save(entity);		
		
		// Cadastra o cartão do dependente
		cadastrarCartaoCmd.gerarCartao(pessoaFisica, titular.getId(), false);
		
		// Criar o usuário para o dependente
		UsuariosTO usuarioTO = new UsuariosTO();	
		
		Optional<Usuarios> usuarioDependente = usuarioRepository.findByUsername(NumeroUtil.somenteNumeros(String.valueOf(pessoaFisica.getCpf())));
		if(!usuarioDependente.isPresent()) {
			usuarioTO.setId(null);
			usuarioTO.setDataUltimoAcesso(null);
			usuarioTO.setQtdAcessoNegado(0L);
			usuarioTO.setStAtivo(true);
			usuarioTO.setUsername(NumeroUtil.somenteNumeros(String.valueOf(pessoaFisica.getCpf())));
			usuarioTO.setSenha(NumeroUtil.somenteNumeros(String.valueOf(pessoaFisica.getCpf().hashCode())));
			usuarioTO.setStTrocaSenha(true);
			usuarioTO.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(pessoaFisica));
			
			usuarioTO = cadastrarUsuarioCmd.cadastrar(usuarioTO);
			
			TipoAcessoUsuario tau = new TipoAcessoUsuario();
			tau.setId(null);
			tau.setIdUsuario(usuarioTO.getId());
			tau.setIdTipoUsuario(TipoUsuarioSistema.ASSOCIADO_DEPENDENTE.getId());
			
			tipoAcessoUsuarioRepository.save(tau);
			
		} else {
			usuarioTO = usuariosTOBuilder.buildTO(usuarioDependente.get());			
			Optional<TipoAcessoUsuario> tipoAcessoUsuario = tipoAcessoUsuarioRepository.findByLoginAndTipoUsuario(pessoaFisica.getCpf(), TipoUsuarioSistema.ASSOCIADO_DEPENDENTE.getTipo());
			if(!tipoAcessoUsuario.isPresent()) {
				TipoAcessoUsuario tau = new TipoAcessoUsuario();
				tau.setId(null);
				tau.setIdUsuario(usuarioDependente.get().getId());
				tau.setIdTipoUsuario(TipoUsuarioSistema.ASSOCIADO_DEPENDENTE.getId());
				tipoAcessoUsuarioRepository.save(tau);
			}
		}			
		
		
		/////////////////////////////////////////////////////////////////////////////////
		//Enviar email para o dependente informando a senha provisória
        /////////////////////////////////////////////////////////////////////////////////
		EnvioEmailTO envioEmailTO = new EnvioEmailTO();
		envioEmailTO.setIdTipoEmail    (TipoEmail.CONTA_CRIADA.getId());
		envioEmailTO.setIsTitular      (false);
		envioEmailTO.setPessoaFisica   (usuarioTO.getPessoaFisica());	
		envioEmailTO.setSenhaProvisoria(NumeroUtil.somenteNumeros(String.valueOf(pessoaFisica.getCpf().hashCode())));
		envioEmailTO.setUsername       (usuarioTO.getUsername());
		gravarEnvioEmailCmd.gravarEnvioEmail(envioEmailTO);	
		
		
		// Atribui permissão no sistema para o titular
		atribuirPermissaoAcessoAssociadoDependenteCmd.atribuir(dependentesTitularTOBuilder.buildTO(dependenteTitular), usuarioTO);
		
	}
	
}
