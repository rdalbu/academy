Gerenciamento de Academia

# Software Necessário:

• Eclipse IDE for Java Developers
• pgAdmin 4

Instruções de Instalação e Configuração
1.Faça o download e instale o Eclipse IDE for Java Developers a partir do site oficial: Eclipse Downloads.

2.Faça o download e instale o pgAdmin 4 a partir do site oficial: pgAdmin Downloads.

3.Descompacte a pasta do projeto "academy" em uma localização de sua preferência.

4.Abra o Eclipse IDE e crie um novo projeto Java com o nome "academy".

5.Copie e cole a pasta descompactada do projeto "academy" para o diretório onde fica localizado seu workspace do Eclipse.

6.No pgAdmin 4, crie um novo banco de dados PostgreSQL com o nome "academy".

7.No pgAdmin 4, vá para "Query Tools" e abra o arquivo SQL fornecido com o projeto. Execute o script SQL no banco de dados "academy" recém-criado.

8.No Eclipse, navegue até o pacote do projeto "academy" e encontre a classe "TelaMenuPrincipal". Execute essa classe para iniciar o sistema de gerenciamento de academia.

# Possíveis Problemas e Soluções

• Erro ao Armazenar Informações no Banco de Dados: Se ocorrer um erro ao tentar armazenar informações no banco de dados, pode ser necessário configurar o Build Path.

o Clique com o botão direito no projeto "academy" no Eclipse.
o Vá para "Build Path" > "Configure Build Path".
o Na aba "Libraries", clique em "Add External JARs" ou  Baixe no link https://jdbc.postgresql.org/download/postgresql-42.7.3.jar .
o Navegue até a pasta do seu workspace do Eclipse e localize a pasta "lib".
o Dentro da pasta "lib", selecione o arquivo JAR do PostgreSQL e adicione-o ao Build Path.
o Reinicie o Eclipse e tente novamente.
