package jv.avaliacao1.gabriel_felix_faustina_prova1.service.impl;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ReservaEntity;
import jv.avaliacao1.gabriel_felix_faustina_prova1.repository.ReservaRepository;
import jv.avaliacao1.gabriel_felix_faustina_prova1.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

	@Autowired
	private ReservaRepository reservaRepository;
	
	@Override
	public ReservaDto postReserva(ReservaDto reservaRequest) {
		if(reservaRequest.getDataReserva().isBefore(LocalDate.now())) {
			throw new DateTimeException("Data de Reserva Inválida!");
		}
		if(reservaRequest.getNumeroMesa() > 20 || reservaRequest.getNumeroMesa() < 1) {
			throw new IllegalArgumentException("Só é possível reservar a entre a Mesa 1 a 20!");
		}
		if(reservaRequest.getNumeroPessoas() > 10 || reservaRequest.getNumeroPessoas() < 1) {
			throw new IllegalArgumentException("É necessário entre 1 a 10 pessoas para reservar!");
		}
		ReservaEntity reservaAdicionada = reservaRepository.save(new ReservaEntity(reservaRequest));
		return new ReservaDto(reservaAdicionada);
	}
	
	@Override
	public ReservaDto putStatusReserva(ReservaDto reservaRequest, Long id) {
		if(reservaRequest.getDataReserva().isBefore(LocalDate.now().plusDays(1))) {
			throw new DateTimeException("Data de Cancelamento Inválida!");
		}
		Optional<ReservaEntity> statusReserva = reservaRepository.findById(id);
		if(statusReserva.isPresent()) {
			ReservaEntity reserva = statusReserva.get();
			reserva.setStatus(reservaRequest.getStatus());
			reservaRepository.save(reserva);
			return new ReservaDto(reserva);
		}
		return null;
	}
	
	@Override
	public String verificaMesaPorData(Integer numeroMesa, LocalDate data) {
		ReservaEntity reserva = reservaRepository.findReservaByNumeroMesa(numeroMesa);
			if(reserva.getDataReserva().equals(data)) {
				throw new IllegalArgumentException("A mesa já está reservada para esse dia!");
			}
		return "A mesa está disponível para esse dia!";
	}
}