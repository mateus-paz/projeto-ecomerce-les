package dominio.venda;

import java.math.BigDecimal;

import dominio.EntidadeDominio;
import dominio.livro.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Item extends EntidadeDominio {
    private Livro livro;
    private int quantidade;
    private BigDecimal valorVenda;

}