package br.com.cartaoamigo.builder;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetModulosCmd;
import br.com.cartaoamigo.dao.dto.ModuloDTO;
import br.com.cartaoamigo.dao.dto.ModuloFilhoDTO;
import br.com.cartaoamigo.entity.Modulo;
import br.com.cartaoamigo.to.AcessoModuloTO;
import br.com.cartaoamigo.to.ModuloTO;


@Component
public class ModuloTOBuilder {
	
	@Autowired private GetModulosCmd getModulosCmd;

	public ModuloTO buildTO(Modulo p) {
		ModuloTO to = new ModuloTO();
		BeanUtils.copyProperties(p, to);
		
		if(Objects.nonNull(p.getModuloPai())) {
			to.setModuloPai(buildTO(p.getModuloPai()));
		}
		return to;
	}

	
	public AcessoModuloTO buildAcessoModuloTO(Modulo p) {
		AcessoModuloTO to = new AcessoModuloTO();
		BeanUtils.copyProperties(p, to);
		return to;
	}

	
	public Modulo build(ModuloTO p) {
		Modulo to = new Modulo();
		
		if(Objects.isNull(p)) {
			return to;
		}
		
		BeanUtils.copyProperties(p, to);
		
		if(Objects.nonNull(p.getModuloPai())) {
			to.setModuloPai(build(p.getModuloPai()));
		}
		
		return to;
	}
	
	public List<ModuloTO> buildAll(List<Modulo> dtos){
		return dtos.stream().map(dto -> buildTO(dto)).collect(Collectors.toList());
	}
	
	
	public ModuloTO buildDTO(ModuloDTO dto) {
		ModuloTO to = new ModuloTO();
		BeanUtils.copyProperties(dto, to);
		
		to.setId(dto.getIdModulo());
		to.setDescricao(dto.getDescricao());
		to.setNome(dto.getNome());
		to.setAgrupador(StringUtils.isNotEmpty(dto.getAgrupador()) && "S".equals(dto.getAgrupador().toUpperCase()) ? true: false);
		
		if(Objects.nonNull(dto.getModuloPai())) {
			ModuloTO moduloPaiTO = getModulosCmd.getTOById(dto.getModuloPai());
			to.setModuloPai(moduloPaiTO);
		}
		
		return to;
	}
	
	public ModuloTO buildDTO(ModuloFilhoDTO dto) {
		ModuloTO to = new ModuloTO();
		BeanUtils.copyProperties(dto, to);
		
		to.setId(dto.getIdModulo());
		to.setDescricao(dto.getDescricao());
		to.setNome(dto.getNome());
		to.setAgrupador(StringUtils.isNotEmpty(dto.getAgrupador()) && "S".equals(dto.getAgrupador().toUpperCase()) ? true: false);
		
		if(Objects.nonNull(dto.getModuloPai())) {
			ModuloTO moduloPaiTO = getModulosCmd.getTOById(dto.getModuloPai());
			to.setModuloPai(moduloPaiTO);
		}
		
		return to;
	}
	
	public List<ModuloTO> buildAllDTO(List<ModuloDTO> dtos){
		return dtos.stream().map(dto -> buildDTO(dto)).collect(Collectors.toList());
	}

	public List<ModuloTO> buildAllFilhoDTO(List<ModuloFilhoDTO> dtos){
		return dtos.stream().map(dto -> buildDTO(dto)).collect(Collectors.toList());
	}

}
