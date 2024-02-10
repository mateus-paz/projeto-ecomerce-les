package dominio.cliente;

import dominio.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Telefone extends EntidadeDominio {
    private TipoTelefone tpTelefone;
    private String ddd;
    private String numero;

}