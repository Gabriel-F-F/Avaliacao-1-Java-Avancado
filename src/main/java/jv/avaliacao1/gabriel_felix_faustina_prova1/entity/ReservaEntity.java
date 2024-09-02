package jv.avaliacao1.gabriel_felix_faustina_prova1.entity;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.enuns.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserva")
@Getter
public class ReservaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate dataReserva;
	
	@Column(nullable = false)
	private Integer numeroPessoas;
	
	@Column(nullable = false)
	private Integer numeroMesa;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusEnum status;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinColumn(name = "cliente_id", nullable = false)
	private ClienteEntity cliente;
	
	public ReservaEntity(ReservaDto dto, ClienteEntity clienteEntity) {
		this.id = dto.getId();
		this.dataReserva = dto.getDataReserva();
		this.numeroPessoas = dto.getNumeroPessoas();
		this.numeroMesa = dto.getNumeroMesa();
		this.status = StatusEnum.FEITA;
		this.cliente = clienteEntity;
	}
	
	public void putStatus(ReservaDto dto) {
		this.status = dto.getStatus();
	}
}