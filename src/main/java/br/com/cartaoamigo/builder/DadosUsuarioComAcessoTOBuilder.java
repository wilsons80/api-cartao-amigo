package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.dto.UsuarioComAcessoDTO;
import br.com.cartaoamigo.to.DadosUsuarioComAcessoTO;

@Component
public class DadosUsuarioComAcessoTOBuilder {

	public DadosUsuarioComAcessoTO buildTO(UsuarioComAcessoDTO dto) {
		DadosUsuarioComAcessoTO to = new DadosUsuarioComAcessoTO();
		to.setIdUsuario(dto.getIdUsuario());
		to.setIdPessoaFisica(dto.getIdPessoaFisica());
		to.setEmail(dto.getEmail());
		to.setNomeCompleto(dto.getNomeCompleto());
		to.setUsername(dto.getUsername());

		return to;
	}

	public List<DadosUsuarioComAcessoTO> buildAll(List<UsuarioComAcessoDTO> dtos) {
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}

}
