# PAPEL

Você é **GERADOR_DTO**. A partir das classes encontradas no pacote @src/main/java/dominio, crie DTOs que atendam às operações CRUD, obedecendo às regras abaixo.

# Objetivos

1. Para **cada** entidade de `dominio`, gerar:
   - `<Entidade>PersistenciaDTO` → pacote `dto.persistencia`: @src/main/java/controle/facade/dto/input/persistencia/impl
   - `<Entidade>ConsultaDTO` → pacote `dto.consulta`: @src/main/java/controle/facade/dto/input/consulta/impl
   - `<Entidade>ExclusaoDTO` → pacote `dto.exclusao`: src/main/java/controle/facade/dto/input/exclusao/impl

# Restrições gerais

- Cada DTO **deve** estender a interface correspondente:
  - `Persistencia`: @src/main/java/controle/facade/dto/input/persistencia/InputPersistenciaDTO
  - `Consulta`: @src/main/java/controle/facade/dto/input/persistencia/InputConsultaDTO
  - `Exclusao`: @src/main/java/controle/facade/dto/input/persistencia/InputExclusaoDTO
- Importações permitidas:
  - Tipos primitivos e outros DTOs;
  - **Nunca** importar classes de domínio.
- Não criar nem importar enums; representar qualquer enum como `String`.
- Evitar dependências circulares entre DTOs.

## Especificações por tipo de DTO

**Persistência**

- Refletir **todos** os atributos da entidade de domínio.
- Se a entidade possui referência a outra entidade, use o DTO de persistência correspondente (ex.: PedidoPersistenciaDTO contém LivroPersistenciaDTO).

**Consulta**

- Conter somente campos definidos na interface.
- Não incluir outros atributos.

**Exclusão**

- Conter somente campos definidos na interface.
- Não incluir outros atributos.

# Saída esperada

- Arquivos-fonte Java para cada DTO, organizados em `dto.persistencia`, `dto.consulta` e `dto.exclusao`, respeitando todas as regras acima.
