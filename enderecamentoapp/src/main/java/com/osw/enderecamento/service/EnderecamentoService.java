package com.osw.enderecamento.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.osw.enderecamento.model.Enderecamento;
import com.osw.enderecamento.util.EnderecamentoParseUtil;

/**
 * This class performs the search service of the ZIP code and neighborhood
 * 
 * @author OswaldoJr
 *
 */
// The @Stateless annotation eliminates the need for manual transaction
// demarcation
@Stateless
public class EnderecamentoService {

	/**
	 * URL service
	 */
	private static final String URL = "http://www.buscacep.correios.com.br/sistemas/buscacep/resultadoBuscaCepEndereco.cfm";

	/**
	 * Variable for utils call
	 */
	@Inject
	EnderecamentoParseUtil enderecamentoParse;

	/**
	 * Search for zip code or street
	 * 
	 * @param zip
	 *            code or street
	 * @return addresses
	 */
	public List<Enderecamento> buscaCepLogradouro(String cepLogradouro) {
		try {
			HttpClient cliente = new DefaultHttpClient();
			HttpPost post = new HttpPost(URL);

			List<NameValuePair> parametros = new ArrayList<NameValuePair>();
			parametros.add(new BasicNameValuePair("relaxation", cepLogradouro));
			parametros.add(new BasicNameValuePair("tipoCEP", "ALL"));
			parametros.add(new BasicNameValuePair("semelhante", "N"));

			post.setEntity(new UrlEncodedFormEntity(parametros));

			HttpResponse response = cliente.execute(post);

			String htmlStr = enderecamentoParse.converteHttpResponseParaString(response);

			return enderecamentoParse.parseEnderecamento(htmlStr);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
