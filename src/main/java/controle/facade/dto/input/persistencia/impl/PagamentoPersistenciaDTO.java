package controle.facade.dto.input.persistencia.impl;

import java.math.BigDecimal;
import java.util.List;

import controle.facade.dto.input.persistencia.InputPersistenciaDTO;
import dominio.EntidadeDominio;
import dominio.venda.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class PagamentoPersistenciaDTO implements InputPersistenciaDTO {
    private Integer id;
    private List<CartaoCompraPersistenciaDTO> cartoes;
    private List<CupomPersistenciaDTO> cupons;
    private BigDecimal totalCompra;
    private BigDecimal totalAlocado;
    private CartaoCompraPersistenciaDTO novoCartao;
    private CupomPersistenciaDTO novoCupom;
    private CarrinhoPersistenciaDTO carrinho;
    private EnderecoEntregaPersistenciaDTO endereco;

    @Override
    public Class<? extends EntidadeDominio> getDominio() {
        return Pagamento.class;
    }
}