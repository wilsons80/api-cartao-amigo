package br.com.cartaoamigo.cmd;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.PessoaFisicaTOBuilder;
import br.com.cartaoamigo.builder.TipoUsuarioTOBuilder;
import br.com.cartaoamigo.builder.TitularTOBuilder;
import br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia.SalvarClienteRecorrenciaPagarmeCmd;
import br.com.cartaoamigo.dao.repository.DependentesTitularRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TipoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TitularRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.DependentesTitular;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.TipoUsuario;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.enums.TipoEmail;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.PessoaFisicaJaExisteException;
import br.com.cartaoamigo.exception.TitularJaExisteException;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.rule.CamposObrigatoriosPessoaFisicaRule;
import br.com.cartaoamigo.rule.DependenteLimitadorRule;
import br.com.cartaoamigo.rule.ValidarDependentesCPFDuplicadosRule;
import br.com.cartaoamigo.rule.ValidarSenhaInformadaRule;
import br.com.cartaoamigo.to.CartaoTO;
import br.com.cartaoamigo.to.EnvioEmailTO;
import br.com.cartaoamigo.to.TitularTO;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class CadastrarTitularCmd {
    
	@Autowired private TitularRepository titularRepository;
	@Autowired private TitularTOBuilder toBuilder;
	@Autowired private CamposObrigatoriosPessoaFisicaRule pessoaFisicaRule;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private DependenteLimitadorRule dependenteLimitadorRule; 
	@Autowired private CadastrarDependentesTitularCmd cadastrarDependentesTitularCmd;
	@Autowired private CadastrarCartaoCmd cadastrarCartaoCmd;
	@Autowired private GetCartaoCmd getCartaoCmd;
	@Autowired private PessoaFisicaTOBuilder pessoaFisicaTOBuilder;
	@Autowired private AtribuirPermissaoAcessoAssociadoTitularCmd atribuirPermissaoAcessoAssociadoTitularCmd;
	@Autowired private CadastrarUsuarioCmd cadastrarUsuarioCmd;
	@Autowired private TipoUsuarioRepository tipoUsuarioRepository; 
	@Autowired private TipoUsuarioTOBuilder tipoUsuarioTOBuilder;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	@Autowired private GravarEnvioEmailCmd gravarEnvioEmailCmd;
	@Autowired private ValidarSenhaInformadaRule validarSenhaInformadaRule ;
	@Autowired private DependentesTitularRepository dependentesTitularRepository;
	@Autowired private ExcluirUsuarioCmd excluirUsuarioCmd;
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private ValidarDependentesCPFDuplicadosRule validarDependentesCPFDuplicadosRule;
	@Autowired private SalvarClienteRecorrenciaPagarmeCmd salvarClienteRecorrenciaPagarmeCmd;
	
	public TitularTO cadastrar(TitularTO to) {
		try {
			dependenteLimitadorRule.validar(to.getDependentes());
			pessoaFisicaRule.verificar(to.getPessoaFisica());
			validarSenhaInformadaRule.validar(to.getSenha(), to.getSenhaConfirmada());
			
			List<String> listaCPFs = to.getDependentes().stream().map(d -> d.getPessoaFisica()).map(p -> p.getCpf()).collect(Collectors.toList());
			validarDependentesCPFDuplicadosRule.validar(listaCPFs);
			
			Optional<Titular> jaExisteTitular = titularRepository.findByCPF(to.getPessoaFisica().getCpf());
			if(jaExisteTitular.isPresent()) {
				throw new TitularJaExisteException("Já existe um titular com o CPF informado.");
			}
			
			Optional<Titular> email = titularRepository.findByEmail(to.getPessoaFisica().getEmail().toUpperCase());
			if(email.isPresent()) {
				throw new PessoaFisicaJaExisteException("Esse e-mail já está cadastrado para outro titular em nossa base de dados.");
			}
			
			Titular entity = toBuilder.build(to);
			entity.setDtCadastro(LocalDateTime.now());
			
			PessoaFisica pessoaFisica = entity.getPessoaFisica();			
			Optional<PessoaFisica> pessoaFisicaOptional = pessoaFisicaRepository.findByCpf(entity.getPessoaFisica().getCpf());
			if(pessoaFisicaOptional.isPresent()) {
				pessoaFisica = pessoaFisicaTOBuilder.buildNewEntity(to.getPessoaFisica(), pessoaFisicaOptional.get());
			} 

			pessoaFisica.setDataCadastro(LocalDateTime.now());
			pessoaFisica = pessoaFisicaRepository.save(pessoaFisica);
			
			//Exclui os dependentes com o mesmo CPF para que se torno um TITULAR
			Optional<List<DependentesTitular>> dependentes = dependentesTitularRepository.findAllDependentesAtivosByCPF(to.getPessoaFisica().getCpf());
			if(dependentes.isPresent()) {
				dependentes.get().forEach(dep -> {
					dep.setExclusaoLogica(true);
					
					Optional<Usuarios> usuarioAssociado = usuarioRepository.findByIdPessoaFisica(dep.getPessoaFisica().getId(), TipoUsuarioSistema.ASSOCIADO_DEPENDENTE.getId());
					if(usuarioAssociado.isPresent()) {
						excluirUsuarioCmd.excluirAcessoUsuario(usuarioAssociado.get().getId());
					}
				});
				
				dependentesTitularRepository.saveAll(dependentes.get());
			}
			
			entity.setPessoaFisica(pessoaFisica);
			Titular titular = titularRepository.save(entity);
			
			Optional<List<CartaoTO>> cartoes = Optional.ofNullable(getCartaoCmd.getAllComoTitular(NumeroUtil.somenteNumeros(pessoaFisica.getCpf())));
			if(!cartoes.isPresent()) {
				cadastrarCartaoCmd.gerarCartao(pessoaFisica, titular.getId(), true);
			}
			
			// Cadastra o usuario
			UsuariosTO usuarioTO = new UsuariosTO();
			
			usuarioTO.setId(null);
			usuarioTO.setDataUltimoAcesso(null);
			usuarioTO.setQtdAcessoNegado(0L);
			usuarioTO.setStAtivo(true);
			usuarioTO.setUsername(pessoaFisica.getCpf());
			usuarioTO.setSenha(to.getSenha());
			usuarioTO.setStTrocaSenha(false);
			usuarioTO.setPessoaFisica(pessoaFisicaTOBuilder.buildTO(pessoaFisica));
			
			Optional<TipoUsuario> tipoUsuario = tipoUsuarioRepository.findByDescricao("ASSOCIADO_TITULAR");
			usuarioTO.setTipoUsuario(tipoUsuarioTOBuilder.buildTO(tipoUsuario.get()));
			
			usuarioTO = cadastrarUsuarioCmd.cadastrar(usuarioTO);
			
			TipoAcessoUsuario tau = new TipoAcessoUsuario();
			tau.setIdTipoUsuario(TipoUsuarioSistema.ASSOCIADO_TITULAR.getId());
			tau.setIdUsuario(usuarioTO.getId());
			tipoAcessoUsuarioRepository.save(tau);		
			
			
			//Cadastra o associado na base do PAGAR.ME
			salvarClienteRecorrenciaPagarmeCmd.cadastrarAssociadoPagarMe(titular);
			
			/////////////////////////////////////////////////////////////////////////////////
			//Enviar email para o titular informando a senha provisória
	        /////////////////////////////////////////////////////////////////////////////////
			EnvioEmailTO envioEmailTO = new EnvioEmailTO();
			envioEmailTO.setIdTipoEmail(TipoEmail.CONTA_CRIADA.getId());
			envioEmailTO.setPessoaFisica(usuarioTO.getPessoaFisica());	
			envioEmailTO.setIsTitular(true);
			envioEmailTO.setUsername(usuarioTO.getUsername());
			gravarEnvioEmailCmd.gravarEnvioEmail(envioEmailTO);
			
			// Cadastra os dependentes
			cadastrarDependentesTitularCmd.cadastrarAll(titular, to.getDependentes() );
			
			TitularTO titularTO = toBuilder.buildTO( titularRepository.findById(titular.getId()).get() );
			
			// Atribui permissão no sistema para o titular
			atribuirPermissaoAcessoAssociadoTitularCmd.atribuir(titularTO, usuarioTO);
			
			return titularTO;
			
		} catch (Exception e) {
			throw new TitularJaExisteException(e.getMessage());
		}

	}

	
}
