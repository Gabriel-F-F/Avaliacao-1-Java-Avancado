package jv.avaliacao1.gabriel_felix_faustina_prova1.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ClienteDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ClienteEntity;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ReservaEntity;
import jv.avaliacao1.gabriel_felix_faustina_prova1.repository.ClienteRepository;
import jv.avaliacao1.gabriel_felix_faustina_prova1.repository.ReservaRepository;
import jv.avaliacao1.gabriel_felix_faustina_prova1.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public ClienteEntity findClienteById(Long id) {
		var cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return cliente.get();
		} else {
			throw new IllegalArgumentException("Cliente não Existe!");
		}
	}

	@Override
	public ClienteDto postCliente(ClienteDto clienteRequest) {
		ClienteEntity clienteAdicionado = clienteRepository.save(new ClienteEntity(clienteRequest));
		return new ClienteDto(clienteAdicionado);
	}

	@Override
	public void putCliente(Long id, ClienteDto clienteRequest) {
		Optional<ClienteEntity> clienteEncontrado = clienteRepository.findById(id);
		if (clienteEncontrado.isPresent()) {
			ClienteEntity cliente = clienteEncontrado.get();
			cliente.putCliente(clienteRequest);
			clienteRepository.save(cliente);
		}
	}

	@Override
	public List<ReservaDto> getReservas(Long id) {
		List<ReservaEntity> reservasCliente = reservaRepository.findAllReservaByClienteId(id);
		return reservasCliente.stream().map(ReservaDto::new).toList();
	}
}