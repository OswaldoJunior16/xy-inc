package com.osw.enderecamento.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.osw.enderecamento.model.Enderecamento;
import com.osw.enderecamento.service.EnderecamentoService;

/**
 * This class produces a RESTful service to read/write the contents of the
 * members table.
 * 
 * @author OswaldoJr
 *
 */
@Path("/buscaCepLogradouro")
@RequestScoped
public class EnderecamentoResourceRESTService {

	/**
	 * Variable for service call
	 */
	@Inject
	EnderecamentoService registration;

	/**
	 * Receive the request
	 * 
	 * @param zip
	 *            code or street
	 * @return addresses
	 */
	@GET
	@Path("/{cepLogradouro}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Enderecamento> buscaCepLogradouro(@PathParam("cepLogradouro") String cepLogradouro) {
		return registration.buscaCepLogradouro(String.valueOf(cepLogradouro));
	}
}
