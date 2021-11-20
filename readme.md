
# Global Impact 2021 FIAP | Alere

## Alere
O Alere é um sistema de cadastro de alimentos para doação, muitos
estabelecimentos, como supermercados, hortifruti, restaurantes etc.,
descartam alimentos que ainda estão próprios para consumo, seja porque
esses alimentos já não estão mais em estado de vender ou porque estão
próximo de vencer. Existem ONGs que recolhem esse tipo de alimento e os
utilizam para alimentar pessoas em situação de vulnerabilidade, a ideia é
que o Alere junte esses dois grupos e torne esse processo mais simples, as
intuições e pessoas poderão se cadastrar e cadastrar esses alimentos que
desejam doar, enquanto outras pessoas poderão se cadastrar e solicitar o
recebimento desses alimentos. Dessa forma o Alere espera reduzir a fome
e o desperdício juntando esses dois grupos em apenas um lugar e
permitindo essas transações. A aplicação foi desenvolvida utilizando a
linguagem de programação Java junto dos frameworks Spring, Hibernate e
Thymeleaf, utilizando banco de dados Relacional PostgreSQL para
ambientes de PROD/DEV e H2 Database para ambiente de TESTE, no fim a
aplicação foi deployada no Heroku

### OBJETIVO DO PROJETO
- Facilitar o processo de doação de alimentos.
- Reduzir o desperdicio de alimentos por meio das doação.
- Reduzir a fome apartir da doação de alimentos
- Reduzir o desperdício de alimentos notificando o usuário de produtos
  próximos ao vencimento.


## Integrantes
Nome | RM
----|-----
Enrique Oliveira Caribé | RM85584
Jacó Magalhães I Sem Chen | RM84717
João Victor Pessoa Queiroz | RM85568
Victor Yafusso Sunahara | RM84095

## Principais Funcionalidades Implementadas
### Cadastro de Usuário
Cadastro de novos usuários com validação de campos, validação de email único, criptografia de senhas utilizando BCrypt e salvando as informações em banco de dados, novos usuários cadastrados já recebem a role de USER para que possam utilizar os outros recursos da aplicação e mais as roles de DOADOR e/ou RECEPTOR a depender da escolha no registro 
![Documentacao Cadastro Usuarip](https://media.discordapp.net/attachments/905952462400262159/911412775597772861/unknown.png?width=1317&height=670)

### Login de Usuário
Login de usuários com validação de campos, processo de validação de senha criptografa para autenticação e autorização de usuários, com autorização de endpoints por roles de usuário.
![Documentacao Login de Usuarios](https://media.discordapp.net/attachments/905952462400262159/911413508145573918/unknown.png?width=1440&height=663)

### Cadastro de alimentos
Cadastro de alimentos para doação informando os dados respectivos ao alimento que será doado
![Documentacao cadastro de dispositivo](https://media.discordapp.net/attachments/905952462400262159/911414229364535336/unknown.png?width=1421&height=670)

### Alimentos
Página para busca de alimentos
![Documentacao endpoints CRUD Produtos](https://media.discordapp.net/attachments/905952462400262159/911414579282714674/unknown.png?width=1146&height=670)


### Apresentação da aplicação + How To Deploy Heroku
https://youtu.be/rpUzX5c6CZI

## Spring Profiles
Profile | Banco de dados
---|---
test | H2 Database (In Memory)
dev | PostgreSQL (local)
prod | PostgreSQL (Heroku Postgres)

OBS: No caso de rodar a aplicação localmente verificar se está no profile de dev ou test, caso queira rodar local com o profile de prod, configuras as properties de conexão com o banco, alterar no arquivo application.yaml a propriedade spring profiles para a opção desejada

OBS: No caso do profile de test ele instância alguns dados na base

## DDL para geração das tabelas
Arquivo DDL com para criação das tabelas caso deseje usar outro banco
https://github.com/RANGO-Corp/DBE/blob/master/data.sql

## Tecnologias
- Java
- Maven
- SpringBoot
- Hibernate
- PostgreSQL
- H2
- Heroku
- Git

## Dependencias
- Java
- Maven
- GIT

## HOW TO RUN
    git clone https://github.com/RANGO-Corp/DBE.git

    cd DBE/

    mvn clean install
    mvn spring-boot:run


## Link do Projeto
https://github.com/RANGO-Corp/DBE
