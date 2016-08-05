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
package com.osw.enderecamento.test;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;

import com.jayway.restassured.RestAssured;

/**
 * This is service test class
 * @author OswaldoJr
 *
 */
public class EnderecamentoServiceTest {
	
	/**
	 * Constructor
	 */
	public EnderecamentoServiceTest() {
		RestAssured.baseURI = "http://localhost:8080/enderecamentoapp/rest/buscaCepLogradouro";
	}
	
	/**
	 * Method to assist research
	 * @param enderecoCEP
	 * @return addresses
	 */
	@SuppressWarnings("unchecked")
	public List<LinkedHashMap<String, String>> buscaEnderecoCEP(String enderecoCEP){
		List<LinkedHashMap<String, String>> enderecamentos = given().when().get("/" + enderecoCEP).as(List.class);
		return enderecamentos;
	}
	
	/**
	 * Test access to the service
	 */
	@Test
	public void testAcessoEnderecamento() {
		given().when().get("/38411449").then().statusCode(200);
	}
	
	/**
	 * Test the query stating the full zid code
	 */
	@Test
	public void testFindCepCompleto() {
		// Given
		String cep = "69027060";
		
		// When
		List<LinkedHashMap<String, String>> enderecamentos = buscaEnderecoCEP(cep);
		
		// Then
		assertTrue(enderecamentos.size() > 0);
		assertEquals("Rua Virgílio Ramos ", enderecamentos.get(0).getOrDefault("logradouro", ""));
		assertEquals("São Raimundo ", enderecamentos.get(0).getOrDefault("bairro", ""));
		assertEquals("Manaus/AM", enderecamentos.get(0).getOrDefault("cidade", ""));
		assertEquals("69027-060", enderecamentos.get(0).getOrDefault("cep", ""));
	}
	
	/**
	 * Test the query stating the partial zid code
	 */
    @Test
    public void testFindCepParcial() throws Exception {
    	// Given
		String cep = "69027";
		int qtdeResult = 50;
		
		// When
		List<LinkedHashMap<String, String>> enderecamentos = buscaEnderecoCEP(cep);
		
		// Then
		assertNotNull(enderecamentos);
        assertTrue(enderecamentos.size() == qtdeResult);
    }
    
    /**
	 * Test the query stating the full Street
	 */
    @Test
    public void testFindLogradouroCompleto() throws Exception {
    	// Given
		String endereco = "Rua Antônio Virgílio Ramos";
    			
		// When
		List<LinkedHashMap<String, String>> enderecamentos = buscaEnderecoCEP(endereco);
		
		// Then
        assertNotNull(enderecamentos);
		assertEquals("Rua Antônio Virgílio Ramos ", enderecamentos.get(0).getOrDefault("logradouro", ""));
		assertEquals("Caçapava/SP", enderecamentos.get(0).getOrDefault("cidade", ""));
        assertEquals("12284-500", enderecamentos.get(0).getOrDefault("cep", ""));
    }
    
    /**
	 * Test the query stating the partial Street
	 */
    @Test
    public void testFindLogradouroParcial() throws Exception {
    	// Given
		String endereco = "Rua Virgílio Ramos";
    	    			
		// When
		List<LinkedHashMap<String, String>> enderecamentos = buscaEnderecoCEP(endereco);
    			
		// Then
        assertNotNull(enderecamentos );
        assertTrue(enderecamentos.size() == 7);
    }
    
    /**
	 * Test the query stating nonexistent zip code
	 */
    @Test
    public void testCepInexistente() throws Exception {
    	// Given
		String cep = "69027aa";
		int qtdeResult = 0;
    			
		// When
		List<LinkedHashMap<String, String>> enderecamentos = buscaEnderecoCEP(cep);
    			
		// Then
        assertNotNull(enderecamentos);
        assertTrue(enderecamentos.size() == qtdeResult);
    }
    
    /**
	 * Test the query stating nonexistent Street
	 */
    @Test
    public void testLogradouroInexistente() throws Exception {
    	// Given
		String endereco = "Rua xyz";
		int qtdeResult = 0;
    	    			
		// When
		List<LinkedHashMap<String, String>> enderecamentos = buscaEnderecoCEP(endereco);
    	    			
		// Then
        assertNotNull(enderecamentos);
        assertTrue(enderecamentos.size() == qtdeResult);
    }

}
