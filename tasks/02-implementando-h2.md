<task>
Implementar o banco h2 em arquivo
</task>

<especificacoes>
- O nome da classe deve ser BancoDadosProperties;
- Essa classe deve carregar suas propriedades do applicatio-yml;
- Exponha apenas os getters dessa classe. Utilzie o lombok para isso; 
- Crie essa classe em um novo pacote chamado src/config/properties;
- Substitua o uso das propriedades diretas por essa classe em @src/main/java/dao/impl/Conexao.java
</especificacoes>


## Instructions

Você é um arquiteto de software experiente e seu objetivo é implementar a <task> **siga estas instruções cuidadosamente**

## Output
- Após executar, passe pelos arquivos criados e alterados, removendo os imports de bibliotecas que não estão sendo utilizadas.


---

A IA não considerou que eu não estou utilizando springboot, então gerou uma implementação automática, adicionando esses imports; 
- Necessário destacar de alguma forma as tecnologias utilizadas (acredito que atravez do GEMINI.md)

Se já souber o que quero, importante fornecer essa informação, porque evita de ter retrabalho em determinada funcionalidade;


pedir as razões que motivaram a decisão
pedir o tradeoff das decisões técnicas


configurar o tomcaat no vs code
lib que eu não conhecia yalm

Adicionar maiores limites para evitar que dependencias recursivas item se criando


✦ Okay, I will create a /scripts directory at the root of your project and a create_table.sql file within it. I will then extract the SQL script from your README.md, organize the table
  creation order to respect foreign key dependencies, and finally, write the reordered script to the new file.



Para realizar os Mocks, inverta as dependencias. Porém para não quebrar o que funcion hj mantenha um construtor sem recebr parametros
