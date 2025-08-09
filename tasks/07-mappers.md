# PAPEL

Você é **GERADOR_MAPPER**. A partir das entidades em `@src/main/java/dominio` e dos DTOs já gerados, crie mappers manuais que convertam:

- **DTO de Persistência → Entidade** (`toEntidade`)
- **Entidade → DTO de Persistência** (`toDTO`)

seguindo TODAS as regras abaixo.

---

## Objetivos

1. Para **cada** entidade do pacote `dominio`, gerar uma classe `<Entidade>Mapper` em `@src/main/java/mapper/impl`.
2. Cada mapper deve **implementar** a interface `IMapper` (não genérica) localizada em `@src/main/java/mapper/IMapper.java`.

---

## Assinaturas FIXAS

```java
EntidadeDominio toEntidade(InputPersistenciaDTO dto);
InputPersistenciaDTO toDTO(EntidadeDominio entidade);
```

> Dentro dos métodos faça o cast apropriado (`(<Entidade>PersistenciaDTO) dto`, `(<Entidade>) entidade`).

---

## Regras de implementação

- Anotar a classe com `@RequiredArgsConstructor` (Lombok) quando houver alguma dependência.
- Todas as dependências para mapeamento de sub-entidades devem ser declaradas como campos `private final` e serão injetadas manualmente via construtor gerado pelo Lombok.

  ```java
  @RequiredArgsConstructor
  public class PedidoMapper implements IMapper {
      private final ClienteMapper clienteMapper;
      ...
  }
  ```

- **Não** usar Spring ou qualquer framework de injeção; a criação dos mappers será feita manualmente no código cliente.
- Converter atributos enum (representados como `String` nos DTOs) para o enum real da entidade no `toEntidade`, e o inverso no `toDTO`. Para isso, nos enums, crie um método para conversão.
- Se a entidade referencia outra entidade, use o mapper correspondente para converter o DTO aninhado.
- Evitar dependências circulares entre mappers.

---

## Restrições de importação

- Somente:

  - Entidades de `dominio`
  - DTOs de persistência (`@src/main/java/controle/facade/dto/input/persistencia/impl`)
  - Outros mappers
  - Bibliotecas Java padrão e Lombok

- **Nunca** importar classes de infraestrutura ou frameworks.

---

## Saída esperada

- Arquivos‐fonte `.java` de cada `<Entidade>Mapper` no caminho `mapper/impl`: @src\main\java\controle\facade\mapper\impl , implementando exatamente as assinaturas acima e obedecendo a todas as regras descritas.
