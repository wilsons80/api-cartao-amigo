package br.com.cartaoamigo.cmd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.builder.ModuloTOBuilder;
import br.com.cartaoamigo.dao.ModuloDao;
import br.com.cartaoamigo.dao.repository.ModuloRepository;
import br.com.cartaoamigo.entity.Modulo;
import br.com.cartaoamigo.enums.TipoUsuarioSistema;
import br.com.cartaoamigo.exception.ModuloNaoEncontradoException;
import br.com.cartaoamigo.to.ModuloTO;

@Component
public class GetModulosCmd {

	@Autowired private ModuloRepository repositoty;
	@Autowired private ModuloDao moduloDao;
	@Autowired private ModuloTOBuilder moduloTOBuilder;
	@Autowired private GetUsuarioLogadoCmd getUsuarioLogadoCmd;
	
	
	public List<ModuloTO> getAllModulo() {
		return moduloTOBuilder.buildAllDTO(moduloDao.getAllModulo());
	}
	
	
	public List<ModuloTO> getAllAdministrativoModuloFilhos() {
		ArrayList<Long> idsModulosPai = new ArrayList<>();
		
		if(getUsuarioLogadoCmd.getTipoUsuarioLogado().getTipo().equals(TipoUsuarioSistema.ROOT.getTipo()) ) {
			idsModulosPai.addAll(Arrays.asList(2L,3L,4L, 20L));
		}
		if(getUsuarioLogadoCmd.getTipoUsuarioLogado().getTipo().equals(TipoUsuarioSistema.ADMINISTRATIVO.getTipo()) ) {
			idsModulosPai.addAll(Arrays.asList(3L,4L));
		}
		if(getUsuarioLogadoCmd.getTipoUsuarioLogado().getTipo().equals(TipoUsuarioSistema.CLINICA.getTipo()) ) {
			idsModulosPai.addAll(Arrays.asList(20L));
		}
		
		return moduloTOBuilder.buildAllFilhoDTO(moduloDao.getAllModuloFilhos(idsModulosPai));
	}
	

	public List<ModuloTO> getAllModuloFilhos() {
		return moduloTOBuilder.buildAllDTO(moduloDao.getAllModuloFilhos());
	}
	
	public List<ModuloTO> getComAcesso(){
		Optional<List<Modulo>> modulosOptional = repositoty.getComAcesso(getUsuarioLogadoCmd.getUsuarioLogado().getIdUsuario());
		if(modulosOptional.isPresent()){
			return moduloTOBuilder.buildAll(modulosOptional.get());
		}
		return null;
	}

	
	public Modulo getById(Long id) {
		return repositoty.findById(id).orElseThrow(() -> new ModuloNaoEncontradoException("Módulo " + id + " não existe."));
	}
	
	public ModuloTO getTOById(Long id) {
		return moduloTOBuilder.buildTO(getById(id));
	}
	
	public Modulo getByNome(String nome) {
		return repositoty.findByNome(nome).orElseThrow(() -> new ModuloNaoEncontradoException("Módulo " + nome + " não existe."));
	}
	

}
