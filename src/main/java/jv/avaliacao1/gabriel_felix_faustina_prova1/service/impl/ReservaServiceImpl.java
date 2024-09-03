package jv.avaliacao1.gabriel_felix_faustina_prova1.service.impl;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ClienteEntity;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ReservaEntity;
import jv.avaliacao1.gabriel_felix_faustina_prova1.enuns.StatusEnum;
import jv.avaliacao1.gabriel_felix_faustina_prova1.repository.ReservaRepository;
import jv.avaliacao1.gabriel_felix_faustina_prova1.service.ClienteService;
import jv.avaliacao1.gabriel_felix_faustina_prova1.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Autowired
	private ClienteService clienteService;

	private void validaData(LocalDate dataReserva) {
		if (dataReserva.isBefore(LocalDate.now())) {
			throw new DateTimeException("Data de Reserva Inválida!");
		}
	}

	private void validaNumeroMesa(Integer numeroMesa) {
		if (numeroMesa > 20 || numeroMesa < 1) {
			throw new IllegalArgumentException("Só é possível reservar a entre a Mesa 1 a 20!");
		}
	}

	private void validaNumeroPessoas(Integer numeroPessoas) {
		if (numeroPessoas > 10 || numeroPessoas < 1) {
			throw new IllegalArgumentException("É necessário entre 1 a 10 pessoas para reservar!");
		}
	}

	private void validaCancelamento(ReservaDto dto, LocalDate dataCancelamento) {
		if (dto.getStatus().equals(StatusEnum.CANCELADA) && !dataCancelamento.isBefore(dto.getDataReserva())) {
			throw new DateTimeException("Data de Cancelamento Inválida!");
		}
	}
// TODO
	private void validaConcluido(ReservaDto dto, LocalDate dataConcluido) {
		if (dto.getStatus().equals(StatusEnum.CONCLUIDA) && (dataConcluido.isAfter(LocalDate.now()))) {
			throw new DateTimeException("Data de Conclusão Inválida!");
		}
	}
	
	@Override
	public String verificaMesaPorData(Integer numeroMesa, LocalDate data) {
		ReservaEntity reserva = reservaRepository.findReservaByNumeroMesa(numeroMesa);
		if (reserva.getDataReserva().equals(data)) {
			throw new IllegalArgumentException("A mesa já está reservada para esse dia!");
		}
		return "A mesa está disponível para esse dia!";
	}

	@Override
	public ReservaDto postReserva(ReservaDto reservaRequest) {
		validaData(reservaRequest.getDataReserva());
		validaNumeroMesa(reservaRequest.getNumeroMesa());
		validaNumeroPessoas(reservaRequest.getNumeroPessoas());
		// TODO verificar disponibilidade de mesa no dia do cadastro
		
		ClienteEntity clienteEntity = clienteService.findClienteById(reservaRequest.getIdCliente());

		ReservaEntity reservaAdicionada = reservaRepository.save(new ReservaEntity(reservaRequest, clienteEntity));
		return new ReservaDto(reservaAdicionada);
	}

	@Override
	public void putStatusReserva(ReservaDto reservaRequest, Long id) {
		Optional<ReservaEntity> statusReserva = reservaRepository.findById(id);
		if (statusReserva.isPresent()) {
			ReservaEntity reserva = statusReserva.get();
			validaCancelamento(reservaRequest, reserva.getDataReserva());
			validaConcluido(reservaRequest, reserva.getDataReserva());
			reserva.putStatus(reservaRequest);
			reservaRepository.save(reserva);
		}
	}
}