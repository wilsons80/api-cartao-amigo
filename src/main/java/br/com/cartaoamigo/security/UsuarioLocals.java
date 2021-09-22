package br.com.cartaoamigo.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import br.com.cartaoamigo.to.UsuarioLogadoTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

public class UsuarioLocals {

	private static HashMap<String, UsuarioLogadoTO> usuarios = new HashMap<>();

	
	/**
	 * Cria um atributo no contexto da thread. Se o atributo existir, o valor é substituído.
	 *
	 * @param key
	 *            nome do atributo/propriedade.
	 * @param val
	 *            valor a ser atribuído ao atributo/propriedade.
	 */
	public static void set(String key, UsuarioLogadoTO val) {
		Map<String, UsuarioLogadoTO> vals = getVals();
		vals.put(key, val);
	}

	public static HashMap<String, UsuarioLogadoTO> getVals() {
		HashMap<String,UsuarioLogadoTO> vals = usuarios;
		if (vals == null) {
			vals = new HashMap<String, UsuarioLogadoTO>();
			usuarios = vals;			
		}
		return vals;
	}

	/**
	 * Obtém o valor de um atributo/propriedade contida no contexto de Thread.
	 *
	 * @param <T>
	 *            O tipo do atributo/propriedade
	 * @param key
	 *            o nome do atributo/propriedade.
	 * @return o valor do atributo/propriedade.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(String key) {
		HashMap<String, UsuarioLogadoTO> vals = getVals();
		return (T) vals.get(key);
	}
	
	


	/**
	 * Destroi todos os atributos armazenados no contexto de Thread. Este método deve ser chamado principalmente ao final de cada request. Isto é importante porque, em um servidor
	 * de aplicações, é normal acontecer de o mesmo thread ser usado no processamento de requests de usuários diferentes. Se o método 'reset' não for invocado, pode acontecer de
	 * dados de uma request serem considerados em outras processadas pelo mesmo thread.
	 */
	public static void reset() {
		usuarios.clear();
	}
	
	
	public static void removerSessoesInvalidas() {
		ArrayList<String> sessoesInvalidas = new ArrayList<>();
		HashMap<String, UsuarioLogadoTO> sessoes = getVals();
        for (String key : sessoes.keySet()) {
        	UsuarioLogadoTO value = sessoes.get(key);
        	if(Objects.nonNull(value) && StringUtils.isNotEmpty(value.getToken())) {
        		Claims claims = null;
        		try {
        			claims = new JwtManager().validaToken(value.getToken());
				} catch (ExpiredJwtException e) {}
        		
        		if( claims == null ) {
        			sessoesInvalidas.add(key);
        		}
        	}
        }
        sessoesInvalidas.forEach(key -> sessoes.remove(key));		
	}
	
}
