<div align="center">

# Jira Reference - Spring Boot 🚀

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Lombok](https://img.shields.io/badge/Lombok-BC0439?style=for-the-badge&logo=lombok&logoColor=white)](https://projectlombok.org/)


</div>

# 📌 Sobre o Projeto

O **Jira Reference** é uma API REST para gerenciamento de:

-   👤 Usuários\
-   🎫 Chamados (Tickets)\
-   💬 Comentários\

Projeto ideal para praticar:

-   Arquitetura em camadas (Controller → Service → Repository)
-   Enumeração e regras de negócio
-   Relacionamentos entre entidades
-   Padrão REST
-   Banco de dados H2

------------------------------------------------------------------------

# ⚙️ Tecnologias Utilizadas

-   Java 17+
-   Spring Boot
-   Spring Web
-   Spring Data JPA
-   Lombok
-   H2 Database
-   Maven

------------------------------------------------------------------------

# 🚀 Como Executar o Projeto

``` bash
git clone https://github.com/ranixx1/jira
cd jira-reference
./mvnw spring-boot:run
```

Aplicação disponível em:

http://localhost:8080

Console H2:

http://localhost:8080/h2-console

------------------------------------------------------------------------

# 📚 ENUMS DO SISTEMA

## 👥 Times

-   PAYMENTS
-   RH
-   TI

## 🎫 Tipos

-   WITHDRAWL\
-   DEPOSIT\
-   MY_ACCOUNT\
-   TECHINAL_ISSUES

## 🚦 Status

-   ABERTO\
-   EM_PROGRESSO\
-   AGUARDANDO_USUARIO\
-   FECHADO

## 🔥 Prioridades

-   BAIXA\
-   NORMAL\
-   ALTA

## 📌 Escopo

-   TODOS\
-   SOMENTE_EU

------------------------------------------------------------------------

# 👤 MÓDULO DE USUÁRIOS

## Criar Usuário

POST /usuarios

``` json
{
  "nome": "André",
  "cpf": "08703096489",
  "time": "TI"
}
```

## Buscar por ID

GET /usuarios/id/{ID}

## Buscar por CPF

GET /usuarios/cpf/{CPF}

## Listar Todos

GET /usuarios

## Listar por Time

GET /usuarios/time/{TIME}

## Atualizar Time

PUT /usuarios/{ID}/{TIME}

``` json
{
  "time": "TIME"
}
```

------------------------------------------------------------------------

# 🎫 MÓDULO DE CHAMADOS

## Criar Chamado

POST /chamados

``` json
{
  "tipo": "MY_ACCOUNT",
  "prioridade": "BAIXA",
  "usuario": { "id": 2 },
  "titulo": "Erro no sistema",
  "descricao": "Falha ao sincronizar dados",
  "escopo": "TODOS"
}
```

## Listar Todos

GET /chamados

## Buscar por ID

GET /chamados/id/{ID}

## Filtrar por Status

GET /chamados/status/{STATUS}

## Filtrar por Tipo

GET /chamados/tipo/{TIPO}

## Filtrar por Prioridade

GET /chamados/prioridade/{PRIORIDADE}


## Atualizar Status

PUT /chamados/{ID}/status

``` json
"EM_PROGRESSO"
```

## Adicionar Comentário

POST /chamados/{ID}/comentarios?autorId={ID}

``` json
"Comentário de teste."
```

------------------------------------------------------------------------

# 🧠 Estrutura

controller/
service/
repository/
model/

------------------------------------------------------------------------

# 📈 Melhorias Futuras ( Boa parte já no jira2.0

-   Spring Security
-   JWT
-   Paginação
-   Swagger
-   Docker
-   Front-end

