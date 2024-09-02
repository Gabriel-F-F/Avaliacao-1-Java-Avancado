package jv.avaliacao1.gabriel_felix_faustina_prova1.service;

import java.time.LocalDate;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;

public interface ReservaService {
	
	ReservaDto postReserva(ReservaDto reservaRequest);
	
	void putStatusReserva(ReservaDto reservaRequest, Long id);
	
	String verificaMesaPorData(Integer numeroMesa, LocalDate data);
}