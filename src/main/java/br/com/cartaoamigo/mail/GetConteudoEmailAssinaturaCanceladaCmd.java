package br.com.cartaoamigo.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetTipoPlanosCmd;
import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.to.EmailTO;
import br.com.cartaoamigo.to.TipoPlanoTO;

@Component
public class GetConteudoEmailAssinaturaCanceladaCmd {
	
	@Value("${sistema.url.front}")
	private String urlFront;
	
	@Autowired private GetTipoPlanosCmd getTipoPlanosCmd;
	

	public StringBuilder getConteudoHtml(EmailTO emailTO) {
		
		TipoPlanoTO             tipoPlanoTO  = getTipoPlanosCmd.findByIdTipoPlano(emailTO.getIdTipoPlano());
		
		StringBuilder sb = new StringBuilder();
		
	    sb.append(" <html>                                                                                                                                                                                                   ");
	    sb.append(" <body>                                                                                                                                                                                                   ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("  <div style=\"width: 400px;\" >                                                                                                                                                                            ");
	    sb.append("    <div style=\"text-align: center;\">                                                                                                                                                                     ");
	    sb.append("      <img style=\"width: 180px\" src='https://s3.amazonaws.com/cartaoamigo.com.br/imagens/LOGO+CART%C3%83O+AMIGO-V1.1.jpg'/>                                                                           ");
	    sb.append("    </div>                                                                                                                                                                                                ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("    <div style=\"font-weight: bold; padding-top: 40px; padding-left: 10px; \">                                                                                                                              ");
	    sb.append("        <span>Olá, " + StringUtil.initCap(emailTO.getPessoaFisica().getNome()) + "</span>                                                                                                                                                           ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("                                                                                                                                                                                                          ");
        sb.append("        <div style=\"font-weight: normal;\">                                                           ");
	    sb.append("            <div style=\"padding-top: 40px;\">                                                         ");
	    sb.append("              <span>Ficamos tristes com seu pedido de cancelamento do plano " + tipoPlanoTO.getDescricao().toUpperCase() + ".</span>                                        ");
	    sb.append("            </div>                                                                                     ");
	    sb.append("            <div>                                                                                      ");
	    sb.append("              <span>Desta forma, além de não ter mais acesso a todos os benefícios do</span>   ");
	    sb.append("            </div>                                                                                     ");
	    sb.append("            <div>                                                                                      ");
	    sb.append("              <span>produto, você também não irá mais desfrutar do Clube Cartão Amigo, nosso clube de</span>   ");
	    sb.append("            </div>                                                                                     ");
	    sb.append("            <div>                                                                                      ");
	    sb.append("              <span>vantagens que oferece descontos exclusivos e muito mais.</span>   ");
	    sb.append("            </div>                                                                                     ");
	    sb.append("            <div>                                                                                      ");
	    sb.append("              <span>Mas para reativar seu plano, é muito fácil. Basta entrar no sistema e renovar sua assinatura.</span>   ");
	    sb.append("            </div>                                                                                     ");
	    sb.append("                                                                                                       ");
	    sb.append("        </div>                                                                                         ");
	    sb.append("                                                                                                       ");
	    sb.append("                                                                                                       ");
	    sb.append("        <div style=\"padding-top: 40px; color: #fd6925\">                                              ");
	    sb.append("              <span>Para renovar sua assinatura, clique no link abaixo para ter acesso ao sistema</span>              ");
	    sb.append("        </div>                                                                                         ");
	    sb.append("                                                                                                       ");
	    sb.append("        <div style=\"padding-top: 40px; color: #fd6925\">                                              ");
	    sb.append("            <a href='"+urlFront+"'> "+urlFront+" </a>                                                  ");
	    sb.append("        </div>                                                                                         ");
	    sb.append("                                                                                                       ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("        <div style=\"padding-top: 40px;\">                                                                                                                                                                  ");
	    sb.append("          <span>Qualquer dúvida, estamos à disposição.</span>                                                                                                                                             ");
	    sb.append("        </div>                                                                                                                                                                                            ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("        <div style=\"padding-top: 40px; font-weight: normal;\">                                                                                                                                             ");
	    sb.append("          <label>Um abraço,</label>                                                                                                                                                                       ");
	    sb.append("        </div>                                                                                                                                                                                            ");
	    sb.append("                                                                                                                                                                                                          ");
	    sb.append("        <div>                                                                                                                                                                                             ");
	    sb.append("            <span style=\"font-weight: normal;\">Equipe Cartão Amigo</span>                                                                                                                                 ");
	    sb.append("        </div>                                                                                                                                                                                            ");
	    sb.append("                                                                                                                                                                                                          ");
	    sb.append("        <div>                                                                                                                                                                                             ");
	    sb.append("          <span><a href=\"https://www.cartaoamigo.com.br\">www.cartaoamigo.com.br</a></span>                                                                                                                        ");
	    sb.append("        </div>                                                                                                                                                                                            ");
	    sb.append("                                                                                                                                                                                                          ");
	    sb.append("                                                                                                                                                                                                          ");
	    sb.append("    </div>                                                                                                                                                                                                ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("  </div>                                                                                                                                                                                                  ");
	    sb.append("                                                                                                                                                                                                          ");
	    sb.append(" </body>                                                                                                                                                                                                  ");
	    sb.append(" </html>                                                                                                                                                                                                  ");
		
		return sb;
	}

}
