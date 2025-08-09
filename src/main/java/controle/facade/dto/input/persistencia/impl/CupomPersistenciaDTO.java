package controle.facade.dto.input.persistencia.impl;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

import dominio.EntidadeDominio;
import dominio.venda.Cupom;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CupomPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private String codigo;
    private String tpCupom; // Representar enum como String
    private BigDecimal valor;
    private LocalDate validade;
    private PedidoPersistenciaDTO pedido;
    private ClientePersistenciaDTO cliente;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Cupom.class;
    }
}