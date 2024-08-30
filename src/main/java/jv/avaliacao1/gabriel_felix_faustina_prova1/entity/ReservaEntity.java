package jv.avaliacao1.gabriel_felix_faustina_prova1.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jv.avaliacao1.gabriel_felix_faustina_prova1.dto.ReservaDto;
import jv.avaliacao1.gabriel_felix_faustina_prova1.enuns.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "reserva")
@Getter
@Setter
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
	
	@OneToMany(mappedBy = "reserva", cascade = CascadeType.DETACH)
	private List<ClienteEntity> clientes;
	
	public ReservaEntity(ReservaDto dto) {
		this.id = dto.getId();
		this.dataReserva = dto.getDataReserva();
		this.numeroPessoas = dto.getNumeroPessoas();
		this.numeroMesa = dto.getNumeroMesa();
		this.status = dto.getStatus();
	}
}