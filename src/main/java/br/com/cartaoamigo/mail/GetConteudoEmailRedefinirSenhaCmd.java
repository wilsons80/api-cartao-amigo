package br.com.cartaoamigo.mail;

import org.springframework.stereotype.Component;

import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.to.EmailTO;


@Component
public class GetConteudoEmailRedefinirSenhaCmd {

	public StringBuilder getConteudoHtml(EmailTO emailTO) {
		StringBuilder sb = new StringBuilder();
		
	    sb.append(" <html>                                                                                                                                                                                                   ");
	    sb.append(" <body>                                                                                                                                                                                                   ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("  <div style=\"width: 400px;\" >                                                                                                                                                                            ");
	    sb.append("    <div style=\"text-align: center;\">                                                                                                                                                                     ");
	    sb.append("      <img style=\"width: 180px\" src='https://s3.amazonaws.com/www.cartaoamigo.com.br/imagens/LOGO+CART%C3%83O+AMIGO-V1.1.jpg'/>                                                                           ");
	    sb.append("    </div>                                                                                                                                                                                                ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("    <div style=\"font-weight: bold; padding-top: 40px; padding-left: 10px; \">                                                                                                                              ");
	    sb.append("        <span>Olá, " + StringUtil.initCap(emailTO.getPessoaFisica().getNome()) + "</span>                                                                                                                                                           ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("        <div style=\"padding-top: 40px;\">                                                                                                                                                                  ");
	    sb.append("          <span>Recebemos a sua solicitação para redefinição de senha.</span>                                                                                                                             ");
	    sb.append("        </div>                                                                                                                                                                                            ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("        <div style=\"padding-top: 40px; color: #fd6925\">                                                                                                                                                   ");
	    sb.append("          <span>Clique no link abaixo e cadastre uma nova senha. Se você não solicitou mudança de senha, ignore este e-mail.</span> ");
	    sb.append("        </div>                                                                                                                                                                                            ");
        sb.append("                                                                                                                                                                                                          ");
        sb.append("                                                                                                                                                                                                          ");
	    sb.append("        <div style=\"padding-top: 40px; color: #fd6925\">                                                                                                                                                 ");
	    sb.append("           <a href='" + emailTO.getLinkRedefinicaoSenha() + "'> "+ emailTO.getLinkRedefinicaoSenha() + " </a>                                                                                             ");
	    sb.append("        </div>                                                                                                                                                                                            ");
        sb.append("                                                                                                                                                                                                          ");
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
