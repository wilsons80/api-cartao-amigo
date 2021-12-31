package br.com.cartaoamigo.cmd.gateway.pagarme.recorrencia;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.TitularRepository;
import br.com.cartaoamigo.entity.Titular;
import br.com.cartaoamigo.exception.PagarmeException;
import br.com.cartaoamigo.rule.CamposObrigatoriosClientePagarMeRule;
import br.com.cartaoamigo.ws.pagarme.cliente.ClienteRecorrenciaService;
import br.com.cartaoamigo.ws.pagarme.to.ClientePagarMeTO;
import br.com.cartaoamigo.ws.pagarme.to.EnderecoClientePagarMeTO;


@Component
public class SalvarClienteRecorrenciaPagarmeCmd {

	@Autowired private ClienteRecorrenciaService service;
	@Autowired private CamposObrigatoriosClientePagarMeRule camposObrigatoriosClientePagarMeRule;
	@Autowired private TitularRepository titularRepository; 
	
	
	public ClientePagarMeTO salvar(ClientePagarMeTO clienteTO) {
		try {
			camposObrigatoriosClientePagarMeRule.verificar(clienteTO);
			
			if(StringUtils.isEmpty( clienteTO.getId())) {
				return service.criar(clienteTO);
			}
			
			return service.editar(clienteTO);
			
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao salvar o cliente no PAGAR.ME: " + e.getMessage());
		}
	}
	
	
	public void salvarBase() {
		try {
			Optional<List<Titular>> titularesOptional = titularRepository.findAllTitularesSemCadastroPagarMe(PageRequest.of(0,1));
			
			if(titularesOptional.isPresent()) {
				titularesOptional.get().forEach((titular) -> {
					ClientePagarMeTO clienteTO = buildClientePagarMeTO(titular);
					clienteTO = salvar(clienteTO);
					
					titular.setIdClientePagarMe(clienteTO.getId());
					titularRepository.save(titular);
				});
			}
			
		} catch (Exception e) {
			throw new PagarmeException("Ocorreu um erro ao salvar os clientes no PAGAR.ME: " + e.getMessage());
		}
	}
	
	
	private ClientePagarMeTO buildClientePagarMeTO(Titular titular) {
		ClientePagarMeTO clienteTO = new ClientePagarMeTO();
		
		clienteTO.setCode(titular.getPessoaFisica().getId().toString());
		clienteTO.setDocument(titular.getPessoaFisica().getCpf());
		clienteTO.setDocument_type("CPF");
		clienteTO.setEmail(titular.getPessoaFisica().getEmail());
		clienteTO.setName(titular.getPessoaFisica().getNome());
		clienteTO.setType("individual");
		
		EnderecoClientePagarMeTO endereco = new EnderecoClientePagarMeTO();
		endereco.setCity(titular.getPessoaFisica().getCidade());
		endereco.setCountry("BR");
		endereco.setState(titular.getPessoaFisica().getUf());
		endereco.setZip_code(titular.getPessoaFisica().getCep().toString());
		endereco.setLine_1(titular.getPessoaFisica().getEndereco());
		endereco.setLine_2(titular.getPessoaFisica().getNumeroEndereco() + " - " + titular.getPessoaFisica().getComplemento());
		
		clienteTO.setAddress(endereco);
		
		return clienteTO;
	}

}
