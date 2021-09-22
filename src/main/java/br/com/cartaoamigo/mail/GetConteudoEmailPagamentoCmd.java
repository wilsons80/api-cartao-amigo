package br.com.cartaoamigo.mail;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.dao.repository.TipoPlanoRepository;
import br.com.cartaoamigo.entity.TipoPlano;
import br.com.cartaoamigo.infra.util.DataUtil;
import br.com.cartaoamigo.infra.util.NumeroUtil;
import br.com.cartaoamigo.infra.util.StringUtil;
import br.com.cartaoamigo.to.EmailTO;

@Component
public class GetConteudoEmailPagamentoCmd {
	
	@Autowired private TipoPlanoRepository planoRepository;

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
        sb.append("                                                                                                                                                                                                          ");
        
        Optional<TipoPlano> tipoPlano = planoRepository.findById(emailTO.getIdTipoPlano());
        if(tipoPlano.isPresent()) {
        	sb.append(" <div style=\"font-weight: normal;\">                                         ");
        	sb.append("     <div style=\"padding-top: 40px; color: #fd6925; font-weight: bold;\">    ");
        	sb.append("       <span>Resumo do contrato</span>                                      ");
        	sb.append("     </div>                                                                 ");
        	sb.append("                                                                            ");
        	sb.append("     <div>                                                                  ");
        	sb.append("       <span>Plano: " +  tipoPlano.get().getDescricao().toUpperCase() + "</span> ");
        	sb.append("     </div>                                                                 ");
        	sb.append("     <div>                                                                  ");
        	sb.append("       <span>Valor de R$ " + NumeroUtil.formataMoeda(tipoPlano.get().getValor()) + "</span>  ");
        	sb.append("     </div>                                                                 ");
        	sb.append("     <div>                                                                  ");
        	sb.append("       <span>Compra realizada no dia " + DataUtil.getHojeFormatado()+ "</span> ");
        	sb.append("     </div>                                                                 ");
        	sb.append(" </div>                                                                     ");
        }
        
        if(StringUtils.isNotEmpty(emailTO.getLinkPagamento())) {
        	sb.append("        <div style=\"padding-top: 40px; color: #fd6925\">                                                                                                                                                   ");
        	sb.append("           <a href='" + emailTO.getLinkPagamento() + "'> "+ emailTO.getLinkPagamento() + " </a>                                                                                             ");
        	sb.append("        </div>                                                                                                                                                                                            ");
        	sb.append("                                                                                                                                                                                                          ");
        }
        
        sb.append("        <div style=\"padding-top: 40px;\">                                                                                                                                                                  ");
        sb.append("          <span>Qualquer dúvida, estamos à disposição.</span>                                                                                                                                             ");
        sb.append("        </div>                                                                                                                                                                                            ");
	    sb.append("        <div style=\"padding-top: 40px; font-weight: normal;\">                                                                                                                                             ");
	    sb.append("          <label>Um abraço,</label>                                                                                                                                                                       ");
	    sb.append("        </div>                                                                                                                                                                                            ");
	    sb.append("        <div>                                                                                                                                                                                             ");
	    sb.append("            <span style=\"font-weight: normal;\">Equipe Cartão Amigo</span>                                                                                                                                 ");
	    sb.append("        </div>                                                                                                                                                                                            ");
	    sb.append("        <div>                                                                                                                                                                                             ");
	    sb.append("          <span><a href=\"https://www.cartaoamigo.com.br\">www.cartaoamigo.com.br</a></span>                                                                                                                        ");
	    sb.append("        </div>                                                                                                                                                                                            ");
	    sb.append("    </div>                                                                                                                                                                                                ");
	    sb.append("  </div>                                                                                                                                                                                                  ");
	    sb.append(" </body>                                                                                                                                                                                                  ");
	    sb.append(" </html>                                                                                                                                                                                                  ");
		
		return sb;
	}
	

}
