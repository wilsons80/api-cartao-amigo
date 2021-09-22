package br.com.cartaoamigo.service.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cartaoamigo.cmd.GetUsuarioLogadoCmd;
import br.com.cartaoamigo.dao.repository.LogErroRepository;
import br.com.cartaoamigo.entity.LogErro;

@Component
public class HandleExceptionCmd {

	@Autowired
	private LogErroRepository logErroDAO;

	@Autowired
	private GetUsuarioLogadoCmd getUsuarioLogadoCmd;

	@Transactional(value = TxType.REQUIRES_NEW)
	public Integer handle(Throwable throwable) {
		LogErro logErro = new LogErro();
		logErro.setDataRegistro(LocalDateTime.now());
		logErro.setMensagemErro(getStackTraceAsString(throwable));
		logErro.setLogin(getUsuarioLogadoCmd.getUsuarioLogado().getUsername());

		logErroDAO.save(logErro);

		return logErro.getId();
	}

	private String getStackTraceAsString(final Throwable throwable) {
		final StringWriter sw = new StringWriter();
		throwable.printStackTrace(new PrintWriter(sw));

		return sw.toString();
	}

}
