# Desenvolvimento

Software requerido:

- JDK 8
- Maven
- IDE Java (Eclipse ou IntelliJ)
- Node.js 9
- Yarn
- PostgreSQL 9.6 ou superior

# Configuração

Criar usuário com nome do projeto, e banco de dados para o projeto.

```postgresql
CREATE ROLE boot LOGIN SUPERUSER PASSWORD 'boot';
CREATE DATABASE boot OWNER boot;
CREATE DATABASE "boot-test" OWNER boot;
```

# Migrações de banco de dados

Criar arquivo SQL na pasta `src/main/resources/db/migration`, seguindo o seguinte padrão:

YYYYMMDDHHMM__descricao.sql

"YYYYMMDDHHMM" deve ser o ano, mês, data, hora e minuto de criação do arquivo. Por exemplo, no dia de hoje (7 de fevereiro
de 2018, 12:02), devemos colocar 201802071202.
Recomenda-se identificar na descrição o conteúdo do arquivo de migração.

Criar um novo arquivo de migração para cada conjunto de alterações no banco de dados.

# Frontend

O projeto contém um gerador de bindings do backend para o frontend. Todos os serviços anotados com `@RemoteProxy` e todas
as entidades anotadas com `@DataTransferObject` serão escaneados. Para cada serviço será gerado um serviço do Angular
correspondente, e para cada entidade será gerado uma definição de tipo no TypeScript.

O plugin responsável por isto está configurado para executar a cada compilação no Eclipse, e pode ser executado manualmente
pelo Maven:

`mvn compile`

## Desenvolvimento frontend

Utilize o yarn para todas as operações (adicionar dependências, rodar a aplicação). Todos os comandos devem ser executados
na pasta `src/main/ts`.

- Para instalar as dependências: `yarn install`
- Para executar o frontend em modo de desenvolvimento: `yarn start`
- Para compilar o frontend para que ele seja acessível pela porta 8080: `yarn run mavenbuild`

Não é possível fazer login pelo frontend em desenvolvimento. Você deve acessar a tela de login pela porta 8080.

Pacotes incluídos no metapacote `eits-ng-starter-2018-01`: https://github.com/eitsopensource/eits-ng-starter/blob/master/eits-ng-starter-2018-01/package.json