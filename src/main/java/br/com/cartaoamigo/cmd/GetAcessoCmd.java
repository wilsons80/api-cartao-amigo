package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.AcessoTOBuilder;
import br.com.cartaoamigo.builder.DadosUsuarioComAcessoTOBuilder;
import br.com.cartaoamigo.dao.AcessoDao;
import br.com.cartaoamigo.dao.repository.ModuloRepository;
import br.com.cartaoamigo.dao.repository.PerfilAcessoRepository;
import br.com.cartaoamigo.entity.Modulo;
import br.com.cartaoamigo.entity.PerfilAcesso;
import br.com.cartaoamigo.exception.ModuloNaoEncontradoException;
import br.com.cartaoamigo.exception.ParametroNaoInformadoException;
import br.com.cartaoamigo.to.AcessoTO;
import br.com.cartaoamigo.to.DadosUsuarioComAcessoTO;
import br.com.cartaoamigo.to.UsuarioLogadoTO;

@Component
public class GetAcessoCmd {

	@Autowired private PerfilAcessoRepository perfilAcessoRepository;
	@Autowired private ModuloRepository moduloRepository;
	@Autowired private AcessoDao acessoDao;
	@Autowired private AcessoTOBuilder acessoTOBuilder;
	@Autowired private DadosUsuarioComAcessoTOBuilder dadosUsuarioComAcessoTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	public List<PerfilAcesso> getAllPerfilAcesso() {
		List<PerfilAcesso> entitys = perfilAcessoRepository.findAll();
		if (entitys == null || entitys.isEmpty()) {
			return new ArrayList<PerfilAcesso>();
		}
		return entitys;
	}

	public List<DadosUsuarioComAcessoTO> getPerfilAcessoDoUsuarioLogado(Long idUsuario) {
		return dadosUsuarioComAcessoTOBuilder.buildAll(acessoDao.getPerfilAcessoDoUsuario(idUsuario));
	}

	public List<AcessoTO> getPerfilAcesso(String nomeModulo) {
		Optional.ofNullable(nomeModulo).orElseThrow(() -> new ParametroNaoInformadoException("Erro ao recuperar os perfils do usuário."));
		
		Modulo modulo = moduloRepository.findByNome(nomeModulo).orElseThrow(() -> new ModuloNaoEncontradoException("Módulo "+nomeModulo+" não encontrado"));
		
		UsuarioLogadoTO usuarioLogado = getUsuarioLogadoCmd.getUsuarioLogado();
		
		return acessoTOBuilder.buildAll(acessoDao.getPerfilAcesso(usuarioLogado.getIdUsuario(), modulo.getId()));
	}


}
