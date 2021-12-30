package br.com.cartaoamigo.ws;

import java.io.StringReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.bind.JAXB;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRestUtil {

	@Inject private RestTemplate restTemplate;

	private static final HttpHeaders JSON_CONTENT_TYPE_HEADER      = jsonContentTypeHeader();
	private static final HttpHeaders XML_CONTENT_TYPE_HEADER       = xmlContentTypeHeader();
	private static final HttpHeaders WWW_FORM_CONTENT_TYPE_HEADER  = wwwFormContentTypeHeader();
	private static final HttpHeaders WWW_FORM_CONTENT_TYPE_HEADER_PAGSEGURO  = wwwFormContentTypeHeaderPagSeguro();
	
	private static HttpHeaders wwwFormContentTypeHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		return HttpHeaders.readOnlyHttpHeaders(header);
	}
	
	private static HttpHeaders wwwFormContentTypeHeaderPagSeguro() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		header.set("Accept", "application/vnd.pagseguro.com.br.v3+xml");
		return HttpHeaders.readOnlyHttpHeaders(header);
	}
	
	private static HttpHeaders xmlContentTypeHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_XML);
		header.set("charset", "ISO-8859-1");
		return HttpHeaders.readOnlyHttpHeaders(header);
	}
	
	
	private static HttpHeaders jsonContentTypeHeader() {
		HttpHeaders jsonContentType = new HttpHeaders();
		jsonContentType.setContentType(MediaType.APPLICATION_JSON);
		return HttpHeaders.readOnlyHttpHeaders(jsonContentType);
	}
	
	@SuppressWarnings("serial")
	private static HttpHeaders createHeaders(String username, String password){
	   return new HttpHeaders() {{
	         String auth = username + ":" + password;
	         byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
	         String authHeader = "Basic " + new String( encodedAuth );
	         set( "Authorization", authHeader );
	   }};
	}

	
	public <T> T get(String username, String password, String url, Class<T> responseType) {
		return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<T>(createHeaders(username, password)), responseType).getBody();
	}
	
	public <T> T get(String url, Class<T> responseType) {
		return restTemplate.getForObject(url, responseType);
	}
	
	public <T> T get(String url, Class<T> responseType, Map<String, String> headers) {
		HttpHeaders header = new HttpHeaders();
		
		for (Map.Entry<String, String> parametro : headers.entrySet()) {
			header.add(parametro.getKey(), parametro.getValue());
		}
		
	    HttpEntity<T> entity = new HttpEntity<T>(null, header);
    
	    return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
	}

	
	public <T> T post(String url, Class<T> responseType, Map<String, String> headers, MultiValueMap<String, Object> body) {
		HttpHeaders header = new HttpHeaders();
		
		for (Map.Entry<String, String> parametro : headers.entrySet()) {
			header.add(parametro.getKey(), parametro.getValue());
		}
		
	    HttpEntity<T> entity = new HttpEntity<T>(null, header);
    
	    return restTemplate.exchange(url, HttpMethod.GET, entity, responseType).getBody();
	}

	public <T> T getXmlParaObjeto(String url, Class<T> responseType, Map<String, String> headers) {
		HttpHeaders header = new HttpHeaders();
		
		for (Map.Entry<String, String> parametro : headers.entrySet()) {
			header.add(parametro.getKey(), parametro.getValue());
		}
		
	    HttpEntity<String> entity = new HttpEntity<String>(null, header);
    
	    String xml = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
		return JAXB.unmarshal(new StringReader(xml), responseType);
		
	}

	
	public <T> ResponseEntity<T> getForResponse(String url, Class<T> responseType) {
		return restTemplate.exchange(url, HttpMethod.GET, null, responseType);
	}

	public <T> List<T> getList(String url, Class<T> responseType) {
		Class<?> responseTypeArray = Array.newInstance(responseType, 0).getClass();

		@SuppressWarnings("unchecked")
		T[] respArray = (T[]) restTemplate.getForObject(url, responseTypeArray);

		return respArray == null ? Collections.emptyList() : Arrays.asList(respArray);
	}

	public void put(String url) {
		restTemplate.put(url, null);
	}
	
	
	public <O, T> T post(String url, O object, Class<T> responseType) {
		HttpEntity<O> httpEntity = new HttpEntity<>(object, JSON_CONTENT_TYPE_HEADER);
		return restTemplate.postForObject(url, httpEntity, responseType);
	}
	
	public <O, T> T postXML(String url, O object, Class<T> responseType) {
		HttpEntity<O> httpEntity = new HttpEntity<>(object, XML_CONTENT_TYPE_HEADER);
		String xml = restTemplate.postForObject(url, httpEntity, String.class);
		return JAXB.unmarshal(new StringReader(xml), responseType);
	}

	public <O, T> T postForm(String url, O object, Class<T> responseType) {
		HttpEntity<O> httpEntity = new HttpEntity<>(object, WWW_FORM_CONTENT_TYPE_HEADER);
		String xml = restTemplate.postForObject(url, httpEntity, String.class);
		return JAXB.unmarshal(new StringReader(xml), responseType);
	}

	public <O, T> T postFormPagSeguro(String url, O object, Class<T> responseType) {
		HttpEntity<O> httpEntity = new HttpEntity<>(object, WWW_FORM_CONTENT_TYPE_HEADER_PAGSEGURO);
		String xml = restTemplate.postForObject(url, httpEntity, String.class);
		return JAXB.unmarshal(new StringReader(xml), responseType);
	}
	

	
}
