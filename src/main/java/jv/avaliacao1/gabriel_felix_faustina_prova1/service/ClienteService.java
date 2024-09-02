package jv.avaliacao1.gabriel_felix_faustina_prova1.service;

import java.util.List;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ClienteDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ClienteEntity;

public interface ClienteService {
	
	ClienteEntity findClienteById(Long id);
	
	ClienteDto postCliente(ClienteDto clienteRequest);
	
	void putCliente(Long id, ClienteDto clienteRequest);
	
	List<ReservaDto> getReservas(Long id);
}