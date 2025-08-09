<task>
Refatorar a camada de controle do sistema utilizando o padrão Front Controller + View Helper, conforme Core J2EE Patterns.
</task>

<context>
O sistema é legado, baseado em Java EE (servlets + JSP). O objetivo é evoluir a arquitetura para centralizar o fluxo de requisições e desacoplar parsing/formatação de dados na camada de controle, facilitando manutenção, validação e segurança.
</context>

<techspec>
- **Front Controller**: Criar uma servlet única responsável por receber todas as requisições web.
- **View Helper**: Implementar helpers para cada recurso do domínio (ex: UsuarioVH) responsáveis por:
    - Extrair dados da requisição (montar o domínio a partir de parâmetros/formulários).
    - Preparar a resposta (setar atributos na request/session, formatar dados para a view).
- **Commands**: Opcionalmente, estruturar comandos para desacoplar regras de negócio específicas por operação.
- **JSPs**: Todas as views devem ficar dentro de `WEB-INF/views` e nunca serem acessadas diretamente.
- **Navegação**: Usar forward para exibição de telas e PRG (Post/Redirect/Get) para gravações.
- **Validações**: Toda validação de entrada deve ser realizada na camada de serviço, nunca no View Helper.
- **Segurança**: Views e dados sensíveis nunca expostos diretamente via URL.
</techspec>

## Instructions

Você é um arquiteto Java sênior e seu objetivo é iniciar a refatoração da camada de controle, seguindo rigorosamente os princípios do padrão Front Controller + View Helper.

- **Analise o fluxo atual** e identifique pontos onde parsing/validação estão misturados à lógica de controle.
- **Proponha a extração dessas responsabilidades** para View Helpers.
- **Desenhe a estrutura inicial dos contratos (interfaces e exemplos) do Front Controller e View Helper**.
- **Não implemente lógica de negócio** nem views; foque apenas na estrutura da camada de controle.
- **Justifique suas escolhas brevemente**.

## Output

- Estrutura de arquivos sugerida (pacotes e classes principais)
- Interfaces e exemplos mínimos de Front Controller e View Helper (só a assinatura)
- Roteiro resumido do fluxo de requisição após refatoração
- Breve justificativa do padrão adotado
