package br.com.cartaoamigo.cmd;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.CartaoRepository;
import br.com.cartaoamigo.dao.repository.DependentesTitularRepository;
import br.com.cartaoamigo.dao.repository.EnvioEmailRepository;
import br.com.cartaoamigo.dao.repository.HistoricoPagamentoRepository;
import br.com.cartaoamigo.dao.repository.PerfilAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.dao.repository.TipoAcessoUsuarioRepository;
import br.com.cartaoamigo.dao.repository.TitularRepository;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.Cartao;
import br.com.cartaoamigo.entity.DependentesTitular;
import br.com.cartaoamigo.entity.EnvioEmail;
import br.com.cartaoamigo.entity.HistoricoPagamento;
import br.com.cartaoamigo.entity.PerfilAcessoUsuario;
import br.com.cartaoamigo.entity.TipoAcessoUsuario;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.AssociadoComPagamentoException;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;

@Component
public class ExcluirTitularCmd {

	@Autowired private TitularRepository repository;
	@Autowired private CartaoRepository cartaoRepository;
	@Autowired private PerfilAcessoUsuarioRepository perfilAcessoUsuarioRepository;
	@Autowired private UsuarioRepository usuarioRepository;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository;
	@Autowired private TipoAcessoUsuarioRepository tipoAcessoUsuarioRepository;
	@Autowired private DependentesTitularRepository dependentesTitularRepository;
	@Autowired private EnvioEmailRepository envioEmailRepository;
	@Autowired private GetHistoricoPagamentoCmd getHistoricoPagamentoCmd; 
	@Autowired private HistoricoPagamentoRepository historicoPagamentoRepository;
	
	public void excluirDadosSemPagamento (Long idTitular) {
		if(Objects.isNull(idTitular)) {
			throw new ParametroNaoInformadoException("Erro ao excluir o Titular. Parâmetro ausente.");
		}
		
		if(getHistoricoPagamentoCmd.isPossuiPagamentoAprovado(idTitular)) {
			throw new AssociadoComPagamentoException("Não é permitido excluir associado que já realizou pagamento.");
		}
		
		Optional<List<HistoricoPagamento>> pagamentos = historicoPagamentoRepository.findAllByIdTitular(idTitular);
		if(pagamentos.isPresent()) {
			historicoPagamentoRepository.deleteAll(pagamentos.get());
		}
		
		Optional<List<Cartao>> cartoes = cartaoRepository.findAllByTitular(idTitular);
		if(cartoes.isPresent()) {
			cartaoRepository.deleteAll(cartoes.get());
		}
	
		//Dependentes
		Optional<List<DependentesTitular>> dependentes = dependentesTitularRepository.findAllByTitularSemExclusaoLogica(idTitular);
		if(dependentes.isPresent()) {
			dependentesTitularRepository.deleteAll(dependentes.get());
			
			dependentes.get().stream().forEach(dependente -> {
				// Apaga dados de usuário do dependente
				apagarDadosUsuario(dependente.getPessoaFisica().getId(), TipoUsuarioSistema.ASSOCIADO_DEPENDENTE.getId());
				
				Optional<List<EnvioEmail>> emails = envioEmailRepository.findAllByPessoaFisica(dependente.getPessoaFisica().getId());
				if(emails.isPresent()) {
					envioEmailRepository.deleteAll(emails.get());
				}
			});			
		}
		
		//titular
		Optional<Titular> titular = repository.findById(idTitular);
		if(titular.isPresent()) {
			repository.delete(titular.get());
		}

		// Apaga dados de usuário do titular
		apagarDadosUsuario(titular.get().getPessoaFisica().getId(), TipoUsuarioSistema.ASSOCIADO_TITULAR.getId());
		Optional<List<EnvioEmail>> emails = envioEmailRepository.findAllByPessoaFisica(titular.get().getPessoaFisica().getId());
		if(emails.isPresent()) {
			envioEmailRepository.deleteAll(emails.get());
		}
	}
	
	
	@Transactional(value = TxType.REQUIRES_NEW)
	public void excluirSemPagamento (Long idTitular) {
		excluirDadosSemPagamento(idTitular);
	}
	
	private void apagarDadosUsuario(Long idPessoaFisica, Long idTipoUsuarioSistema) {
		Optional<Usuarios> usuarioTitular = usuarioRepository.findByIdPessoaFisica(idPessoaFisica, idTipoUsuarioSistema);
		
		//perfil acesso usuario
		if(usuarioTitular.isPresent()) {
			Optional<List<PerfilAcessoUsuario>> perfilAcessoUsuario = perfilAcessoUsuarioRepository.findAllByIdUsuario(usuarioTitular.get().getId());
			if(perfilAcessoUsuario.isPresent()) {
				perfilAcessoUsuarioRepository.deleteAll(perfilAcessoUsuario.get());
			}
			
			Optional<TipoAcessoUsuario> tipoAcessoUsuario = tipoAcessoUsuarioRepository.findByUsuario(usuarioTitular.get().getId());
			//tipos de acesso
			if(tipoAcessoUsuario.isPresent()) {
				tipoAcessoUsuarioRepository.delete(tipoAcessoUsuario.get());
			}
			
			// usuario
			usuarioRepository.delete(usuarioTitular.get());
		}
		
		try {
			//pessoa fisica
			pessoaFisicaRepository.deleteById(idPessoaFisica);
		} catch (Exception e) {}
	}
	
}
