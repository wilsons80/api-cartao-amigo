package br.com.cartaoamigo.cmd;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.exception.NotFoundException;
import br.com.cartaoamigo.to.GrupoAcessoTO;
import br.com.cartaoamigo.to.PerfilAcessoUsuarioTO;
import br.com.cartaoamigo.to.TitularTO;
import br.com.cartaoamigo.to.UsuariosTO;

@Component
public class AtribuirPermissaoAcessoAssociadoTitularCmd {
    
	@Autowired private GetGrupoAcessoCmd               getGrupoAcessoCmd;
	@Autowired private CadastrarPerfilAcessoUsuarioCmd cadastrarPerfilAcessoUsuarioCmd;
	
	public void atribuir(TitularTO titularTO, UsuariosTO usuarioTO) {
		GrupoAcessoTO grupoAcessoTO = getGrupoAcessoCmd.getGrupoAssociadoTitular();
		if(Objects.isNull(grupoAcessoTO) || Objects.isNull(grupoAcessoTO.getId())) {
			throw new NotFoundException("Erro ao recuperar o grupo de acesso do titular.");
		}
		
		
		PerfilAcessoUsuarioTO to = new PerfilAcessoUsuarioTO();
		to.setId(null);
		to.setIdUsuario  (usuarioTO.getId());
		to.setGrupoAcesso(grupoAcessoTO);
		
		cadastrarPerfilAcessoUsuarioCmd.cadastrar(to, usuarioTO);
	}
	
	
}
