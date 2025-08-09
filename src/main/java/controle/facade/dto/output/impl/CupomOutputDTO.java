package controle.facade.dto.output.impl;

import java.math.BigDecimal;
import java.time.LocalDate;

import controle.facade.dto.output.OutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CupomOutputDTO implements OutputDTO {
    private Integer id;
    private String codigo;
    private String tpCupom;
    private BigDecimal valor;
    private LocalDate validade;
    private PedidoOutputDTO pedido;
    private ClienteOutputDTO cliente;
}
