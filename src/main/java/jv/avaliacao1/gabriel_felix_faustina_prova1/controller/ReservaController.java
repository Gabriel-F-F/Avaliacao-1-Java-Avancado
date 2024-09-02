package jv.avaliacao1.gabriel_felix_faustina_prova1.controller;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Object> postReserva(@RequestBody ReservaDto reservaRequest) {
		try {			
			return ResponseEntity.ok(reservaService.postReserva(reservaRequest));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{idReserva}")
	public void putStatusReserva(@PathVariable Long idReserva, @RequestBody ReservaDto reservaRequest) {
		reservaService.putStatusReserva(reservaRequest, idReserva);
	}

	@GetMapping("/{numeroMesa}/{data}")
	public String verificaMesaPorData(@PathVariable Integer numeroMesa, @PathVariable LocalDate data) {
		return reservaService.verificaMesaPorData(numeroMesa, data);
	}
}