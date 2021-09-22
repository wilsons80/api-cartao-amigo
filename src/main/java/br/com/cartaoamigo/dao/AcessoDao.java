package br.com.cartaoamigo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.base.BaseDao;
import br.com.cartaoamigo.dao.dto.AcessoDTO;
import br.com.cartaoamigo.dao.dto.UsuarioComAcessoDTO;
import br.com.cartaoamigo.dao.repository.TrocarSenhaRepository;

@Component
public class AcessoDao extends BaseDao{
	
	@Autowired 
	private TrocarSenhaRepository trocarSenhaRepository;
	
	public void trocarSenha(String username, String senha) {
		trocarSenhaRepository.updateSenha(username, senha);
	}
	
	public List<UsuarioComAcessoDTO> getPerfilAcessoDoUsuario(Long idUsuario) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("	select DISTINCT u.id_usuario,                                                          ");
		sql.append("	       u.nm_username,                                                                  ");
		sql.append("	       m.id_modulo,                                                                    ");
		sql.append("	       m.descricao,                                                                    ");
		sql.append("	       ga.id_grupo_acesso,                                                             ");
		sql.append("	       ga.ds_nome,                                                                     ");
		sql.append("	       ga.id_perfil_acesso                                                             ");
		sql.append("	  from modulos m                                                                       ");
		sql.append("	  inner join grupo_acesso_modulos gam on gam.id_modulo = m.id_modulo                   ");
		sql.append("	  inner join grupo_acesso ga on ga.id_grupo_acesso = gam.id_grupo_acesso               ");
		sql.append("	  inner join perfil_acesso_usuario pau on pau.id_grupo_acesso = ga.id_grupo_acesso     ");
		sql.append("	  inner join usuarios u on u.id_usuario = pau.id_usuario                               ");
		sql.append("	  inner join tipo_acesso_usuarios tau on tau.id_usuario = u.id_usuario                 ");
		sql.append("	  inner join tipo_usuario tu on tu.id_tipo_usuario = tau.id_tipo_usuario               ");
		sql.append("	where 1=1                                                                              ");
		
		if(Objects.nonNull(idUsuario)) {
			sql.append("	  and u.id_usuario = :idUsuario                                                    ");
		}
		
		sql.append("	order by ga.ds_nome                                                                    ");
		
		Query query = em.createNativeQuery(sql.toString());
		if(Objects.nonNull(idUsuario)) {
			query.setParameter("idUsuario", idUsuario);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<UsuarioComAcessoDTO> retorno = new ArrayList<UsuarioComAcessoDTO>();
		values.stream().forEach( colunas -> retorno.add(new UsuarioComAcessoDTO(colunas)));
		
		return retorno;
	}
	
	
	
	public List<AcessoDTO> getPerfilAcesso(Long idUsuario,Long idModulo) {
		StringBuilder sql = new StringBuilder();

		sql.append("	select m.id_modulo,                                                                     ");
		sql.append("	       m.nome,                                                                          ");
		sql.append("	       pa.cs_consulta,                                                                  ");
		sql.append("	       pa.cs_altera,                                                                    ");
		sql.append("	       pa.cs_deleta,                                                                    ");
		sql.append("	       pa.cs_insere                                                                     ");
		sql.append("	  from modulos m                                                                        ");
		sql.append("	  inner join grupo_acesso_modulos gam on gam.id_modulo = m.id_modulo                    ");
		sql.append("	  inner join grupo_acesso ga on ga.id_grupo_acesso = gam.id_grupo_acesso                ");
		sql.append("	  inner join perfil_acesso_usuario pau on pau.id_grupo_acesso = ga.id_grupo_acesso      ");
		sql.append("	  inner join usuarios u on u.id_usuario = pau.id_usuario                                ");
		sql.append("	  inner join tipo_acesso_usuarios tau on tau.id_usuario = u.id_usuario                  ");
		sql.append("	  inner join tipo_usuario tu on tu.id_tipo_usuario = tau.id_tipo_usuario                ");
		sql.append("	  inner join perfis_acessos pa on pa.id_perfil_acesso = ga.id_perfil_acesso             ");
		sql.append("	where 1=1                                                                               ");
		
		if(Objects.nonNull(idUsuario)) {
			sql.append("	  and u.id_usuario = :idUsuario                                                     ");
		}
		
		if(Objects.nonNull(idModulo)) {
			sql.append("	  and m.id_modulo  = :idModulo                                                      ");
		}
		
		sql.append("	order by m.nome                                                                         ");
		
		
		Query query = em.createNativeQuery(sql.toString());
		if(Objects.nonNull(idModulo)) {
			query.setParameter("idModulo",  idModulo);
		}
		
		if(Objects.nonNull(idUsuario)) {
			query.setParameter("idUsuario", idUsuario);
		}
		
		@SuppressWarnings("unchecked")
		List<Object[]> values = query.getResultList();
		
		List<AcessoDTO> retorno = new ArrayList<AcessoDTO>();
		values.stream().forEach( colunas -> retorno.add(new AcessoDTO(colunas)));
		
		return retorno;
		
	}

	
	
}
