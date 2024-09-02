package jv.avaliacao1.gabriel_felix_faustina_prova1.dto;

import java.time.LocalDate;
import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ReservaEntity;
import jv.avaliacao1.gabriel_felix_faustina_prova1.enuns.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservaDto {
	private Long id;
	private LocalDate dataReserva;
	private Integer numeroPessoas;
	private Integer numeroMesa;
	private StatusEnum status;
	private Long idCliente;
	
	public ReservaDto(ReservaEntity entity) {
		this.id = entity.getId();
		this.dataReserva = entity.getDataReserva();
		this.numeroPessoas = entity.getNumeroPessoas();
		this.numeroMesa = entity.getNumeroMesa();
		this.status = entity.getStatus();
		this.idCliente = entity.getCliente().getId();
	}
}