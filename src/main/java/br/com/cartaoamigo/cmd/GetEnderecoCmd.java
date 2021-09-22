package br.com.cartaoamigo.cmd;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.GetEnderecoDao;
import br.com.cartaoamigo.dao.dto.CidadeDTO;

@Component
public class GetEnderecoCmd {

	@Autowired private GetEnderecoDao getEnderecoDao;
	
	
	public List<CidadeDTO> getAllBairrosPorUF(String uf) {
		List<CidadeDTO> lista = getEnderecoDao.getAllBairrosPorUF(uf);
		return lista;
	}
}
