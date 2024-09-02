package jv.avaliacao1.gabriel_felix_faustina_prova1.dto;

import jv.avaliacao1.gabriel_felix_faustina_prova1.entity.ClienteEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteDto {
	private Long id;
	private String nome;
	private String email;
	
	public ClienteDto(ClienteEntity entity) {
		this.id = entity.getId();
		this.nome = entity.getNome();
		this.email = entity.getEmail();
	}	
}