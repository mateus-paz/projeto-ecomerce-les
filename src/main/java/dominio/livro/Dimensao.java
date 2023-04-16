package dominio.livro;

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
public class Dimensao extends EntidadeDominio {
    private double altura;
    private double largura;
    private double peso;
    private double profundidade;

}