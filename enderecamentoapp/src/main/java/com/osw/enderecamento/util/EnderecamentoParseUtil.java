/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.osw.enderecamento.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;

import org.apache.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.osw.enderecamento.model.Enderecamento;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class EnderecamentoParseUtil {

	/**
	 * extract info about a address
	 * 
	 * @param html
	 * @return address list
	 */
	public List<Enderecamento> parseEnderecamento(String html) {

		Document docHtml = Jsoup.parse(html);
		Elements tagTr = docHtml.getElementsByTag("tr");
		List<Enderecamento> enderecamentos = new LinkedList<Enderecamento>();

		for (Element element : tagTr) {
			if (element.getElementsByTag("td").size() > 0) {
				Elements tahTd = element.getElementsByTag("td");
				Enderecamento enderecamento = new Enderecamento();
				enderecamento.setLogradouro(tahTd.get(0).text());
				enderecamento.setBairro(tahTd.get(1).text());
				enderecamento.setCidade(tahTd.get(2).text());
				enderecamento.setCep(tahTd.get(3).text());
				enderecamentos.add(enderecamento);
			}
		}
		return enderecamentos;

	}

	/**
	 * Convert HttpResponse for string
	 * 
	 * @param response
	 * @return html in string format
	 * @throws IOException
	 */
	public String converteHttpResponseParaString(HttpResponse response) throws IOException {

		String linhaStr = "";
		StringBuffer strBuffer = new StringBuffer();
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		while ((linhaStr = reader.readLine()) != null) {
			strBuffer.append(linhaStr);
		}
		return strBuffer.toString();
	}

}
