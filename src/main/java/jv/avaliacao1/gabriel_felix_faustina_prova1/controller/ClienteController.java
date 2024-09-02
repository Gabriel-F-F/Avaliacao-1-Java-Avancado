package jv.avaliacao1.gabriel_felix_faustina_prova1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ClienteDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public void postCliente(@RequestBody ClienteDto clienteRequest) {
		clienteService.postCliente(clienteRequest);
	}
	
	@PutMapping("/{idCliente}")
	public void putCliente(@PathVariable Long idCliente, @RequestBody ClienteDto clienteRequest) {
		clienteService.putCliente(idCliente, clienteRequest);
	}
	
	@GetMapping("/{idCliente}")
	public List<ReservaDto> getReservas(@PathVariable Long idCliente) {
		return clienteService.getReservas(idCliente);
	}
}