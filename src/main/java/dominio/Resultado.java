package dominio;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Resultado {

	private String mensagemErro;
	private List<EntidadeDominio> entidades = new ArrayList<>();

	public void setEntidades(List<EntidadeDominio> entidades) {
		if (entidades == null)
			entidades = new ArrayList<>();

		this.entidades = entidades;
	}

	public void setEntidade(EntidadeDominio entidade) {
		if (entidades == null)
			entidades = new ArrayList<>();

		this.entidades = List.of(entidade);
	}

}
