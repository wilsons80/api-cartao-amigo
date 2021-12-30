package br.com.cartaoamigo.service.gateway.pagseguro.split;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cartaoamigo.cmd.pagseguro.split.GetCodigoAutorizacaoCorretorSplitCmd;
import br.com.cartaoamigo.dao.repository.CorretorRepository;
import br.com.cartaoamigo.dao.repository.PessoaFisicaRepository;
import br.com.cartaoamigo.entity.Corretor;
import br.com.cartaoamigo.entity.PessoaFisica;
import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.exception.PagSeguroException;
import br.com.cartaoamigo.exception.PessoaFisicaNaoEncontradaException;
import br.com.cartaoamigo.ws.pagseguro.to.CodigoAutorizacaoCorretorTO;
import br.com.cartaoamigo.ws.pagseguro.to.CodigoAutorizacaoTO;

@RestController
@RequestMapping(value = "pagseguro/split/corretor")
public class SalvarCodigoAutorizacaoCorretorSplitService {
	
	@Autowired private GetCodigoAutorizacaoCorretorSplitCmd getCodigoAutorizacaoCmd;
	@Autowired private PessoaFisicaRepository pessoaFisicaRepository ;
	@Autowired private CorretorRepository corretorRepository;
	
	
	@PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public void salvar(@RequestBody CodigoAutorizacaoCorretorTO dadosTO) {
		try {
			CodigoAutorizacaoTO codigoAutorizacaoTO = getCodigoAutorizacaoCmd.obter(dadosTO);
			if(Objects.isNull(codigoAutorizacaoTO)) {
				throw new NotFoundException("Código da notificação não encontrado.");
			}
			
			Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findByEmail(codigoAutorizacaoTO.getAuthorizerEmail());
			if(!pessoaFisica.isPresent()) {
				throw new PessoaFisicaNaoEncontradaException("Não existe corretor com o e-mail " + codigoAutorizacaoTO.getAuthorizerEmail() + " cadastrado em nossa base de dados.");
			}
			
			Optional<Corretor> corretor = corretorRepository.findByPessoaFisica(pessoaFisica.get().getId());
			if(!corretor.isPresent()) {
				throw new PessoaFisicaNaoEncontradaException("Corretor não encontrado em nossa base de dados.");
			}
			
			corretor.get().setPublicKey(codigoAutorizacaoTO.getAccount().getPublicKey());
			corretorRepository.save(corretor.get());
			
		} catch (Exception e) {
			throw new PagSeguroException(e.getMessage());
		}
	}

}
