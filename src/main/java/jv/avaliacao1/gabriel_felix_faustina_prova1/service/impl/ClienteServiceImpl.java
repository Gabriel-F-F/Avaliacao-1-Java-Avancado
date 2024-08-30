package jv.avaliacao1.gabriel_felix_faustina_prova1.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ClienteDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ClienteEntity;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ReservaEntity;
import jv.avaliacao1.gabriel_felix_faustina_prova1.repository.ClienteRepository;
import jv.avaliacao1.gabriel_felix_faustina_prova1.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
//	@Override
//	public ClienteDto postCliente(ClienteDto clienteRequest) {
//		ReservaEntity reserva = clienteRepository.findReservaById(clienteRequest.getIdReserva());
//		ClienteEntity clienteAdicionado = clienteRepository.save(new ClienteEntity(clienteRequest, reserva));
//		return new ClienteDto(clienteAdicionado);
//	}
	
	@Override
	public ClienteDto postCliente(ClienteDto clienteRequest) {
		List<ReservaEntity> listaReservas = new ArrayList<>();
		if(listaReservas.contains(clienteRequest.getIdReserva())) {
			ReservaEntity reservaEncontrada = listaReservas.get(0);
		ClienteEntity clienteAdicionado = clienteRepository.save(new ClienteEntity(clienteRequest, reservaEncontrada));
		return new ClienteDto(clienteAdicionado);
		}
		return null;
	}
}