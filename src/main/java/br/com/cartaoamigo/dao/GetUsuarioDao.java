package br.com.cartaoamigo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.UsuarioComboDTO;
import br.com.cartaoamigo.dao.dto.UsuarioDTO;
import br.com.cartaoamigo.dao.repository.UsuarioRepository;
import br.com.cartaoamigo.entity.Usuarios;
import br.com.cartaoamigo.infra.util.Java8DateUtil;

@Component
public class GetUsuarioDao extends BaseDao {

	@Autowired private UsuarioRepository usuarioSistemaRepository;
	
	public Optional<Usuarios>  getUsuarioByUsername(String username) {
		Optional<Usuarios> usuarioSistema = usuarioSistemaRepository.findByUsername(username);
		return usuarioSistema;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioComboDTO> getAllCombo() {
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT                                                                         ");
		sql.append(" 		u.id_usuario,                                                           ");
		sql.append(" 		pf.nm_pessoa_fisica                                                     ");
		sql.append(" FROM usuarios u                                                                ");
		sql.append(" inner join pessoas_fisicas pf on pf.id_pessoa_fisica = u.id_pessoa_fisica      ");
		sql.append(" inner join tipo_acesso_usuarios tau on tau.id_usuario =  u.id_usuario          ");
		sql.append(" inner join tipo_usuario tu on tu.id_tipo_usuario = tau.id_tipo_usuario         ");
		sql.append(" WHERE 1 = 1                                                                    ");
		sql.append("  and tu.id_tipo_usuario in(3,4,5)                                                ");
		sql.append(" order by 2                                                                     ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		List<Object[]> values = query.getResultList();
		
		List<UsuarioComboDTO> retorno = new ArrayList<UsuarioComboDTO>();
		values.stream().forEach( colunas -> retorno.add(new UsuarioComboDTO(colunas)));
		
		return retorno;
	}
	
	@SuppressWarnings("unchecked")
	public List<UsuarioDTO> getAllFilterUsuario(Long idUsuario, 
												LocalDate dataInicioCadastro,
												LocalDate dataFimCadastro, 
												String ativo) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT DISTINCT u.id_usuario,                                                       ");
		sql.append("		        u.st_ativo,                                                          ");
		sql.append("		        pf.nm_pessoa_fisica,                                                 ");
		sql.append("		        pf.nr_cpf,                                                           ");
		sql.append("		        pf.ds_email,                                                         ");
		sql.append("		        pf.dt_cadastro,                                                      ");
		sql.append("		        tu.ds_tipo_usuario,                                                  "); 
		sql.append("		        tu.id_tipo_usuario                                                   ");
		sql.append("		FROM usuarios u                                                              ");
		sql.append("		inner join pessoas_fisicas pf on pf.id_pessoa_fisica = u.id_pessoa_fisica    ");
		sql.append("		inner join tipo_acesso_usuarios tau on tau.id_usuario =  u.id_usuario        ");
		sql.append("		inner join tipo_usuario tu on tu.id_tipo_usuario = tau.id_tipo_usuario       ");
		sql.append("		WHERE 1 = 1                                                                  ");
		sql.append("		  and tu.id_tipo_usuario in(3,4,5)                                             ");
		
		if(Objects.nonNull(idUsuario)) {
			sql.append("  and :p_usuario = u.id_usuario  ");
		}
		
		
		if(Objects.nonNull(dataInicioCadastro)) {
			sql.append("   AND DATE_TRUNC('DAY', pf.dt_cadastro) >= DATE_TRUNC('DAY', to_date( :p_dt_inicio_cadastro ,'dd/mm/yyyy') )  ");	
		}
		
		if(Objects.nonNull(dataFimCadastro)) {
			sql.append("  and DATE_TRUNC('DAY', pf.dt_cadastro) <= DATE_TRUNC('DAY', to_date( :p_dt_fim_cadastro ,'dd/mm/yyyy') )     ");
		}
		
		if(StringUtils.isNotEmpty(ativo)) {
			sql.append("  and :p_ativo = u.st_ativo  ");
		}
				
		sql.append(" order by 4 ");
		
		Query query = em.createNativeQuery(sql.toString());
		
		if(Objects.nonNull(idUsuario)) {
			query.setParameter("p_usuario", idUsuario);
		}

		if(StringUtils.isNotEmpty(ativo) ) {
			query.setParameter("p_ativo", ativo.toUpperCase());
		}

		if(Objects.nonNull(dataInicioCadastro)) {
			query.setParameter("p_dt_inicio_cadastro", Java8DateUtil.getLocalDateFormater(dataInicioCadastro));
		}
		
		if(Objects.nonNull(dataFimCadastro)) {
			query.setParameter("p_dt_fim_cadastro", Java8DateUtil.getLocalDateFormater(dataFimCadastro));
		}
		
		List<Object[]> values = query.getResultList();
		
		List<UsuarioDTO> retorno = new ArrayList<UsuarioDTO>();
		values.stream().forEach( colunas -> retorno.add(new UsuarioDTO(colunas)));
		
		return retorno;
	}
			
}



