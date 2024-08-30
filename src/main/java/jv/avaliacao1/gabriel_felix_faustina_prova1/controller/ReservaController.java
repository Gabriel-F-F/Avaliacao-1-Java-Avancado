package jv.avaliacao1.gabriel_felix_faustina_prova1.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.service.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaService reservaService;
	
	@PostMapping
	public void postReserva(@RequestBody ReservaDto reservaRequest) {
		reservaService.postReserva(reservaRequest);
	}
	
	@PutMapping("/{idReserva}")
	public ReservaDto putStatusReserva(@RequestBody ReservaDto reservaRequest, @PathVariable Long idReserva) {
		return reservaService.putStatusReserva(reservaRequest, idReserva);
	}
	
	@GetMapping("/{numeroMesa}/{data}")
	public String verificaMesaPorData(@PathVariable Integer numeroMesa, @PathVariable LocalDate data) {
		return reservaService.verificaMesaPorData(numeroMesa, data);
	}
}