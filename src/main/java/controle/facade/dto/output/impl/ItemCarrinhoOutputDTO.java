package controle.facade.dto.output.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrinhoOutputDTO extends ItemOutputDTO {
    private CarrinhoOutputDTO carrinho;
}
