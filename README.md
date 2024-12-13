# Microserviço: User

Este repositório contém o microserviço `user` e `order`, desenvolvido com Java, Spring Boot, JPA, RabbitMQ, H2Database e Maven. Ele é responsável pela gestão de usuários em um sistema baseado em microserviços. Onde um usuário faz a solicitação de um pedido e esse pedido fica vinculado ao usuário. O intuito do projeto é demonstrar a integração da Arquitetuva Microsserviços.

## Pré-requisitos

Antes de rodar a aplicação, certifique-se de que possui os seguintes pré-requisitos instalados no seu sistema:

- **Java Development Kit (JDK)** 21 ou superior.
- **Maven** 3.8 ou superior.
- **Docker** e **Docker Compose** (caso vá rodar a aplicação em containers).

---

## Estrutura do Projeto

- **src/**: Código-fonte do microserviço.
- **pom.xml**: Configuração de dependências do Maven.
- **docker-compose.yml**: Arquivo para orquestração de containers.
- **Dockerfile**: Arquivo de construção do container para o microserviço.

---

## Configuração do Ambiente

### Banco de Dados
Este serviço requer um banco de dados configurado. Há aplicação está preparada para subir um container com o banco de dados H2, mas você pode configurar o banco localmente, se preferir.

### Arquivo de Configuração
Certifique-se de ajustar o arquivo `application.properties` (ou `application.yml`) localizado em `src/main/resources` para refletir as configurações do banco de dados. Exemplo:

```properties
# Configurações do RabbitMQ
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest

# Configuração do H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

server.port=8083

````

mvn clean package
mvn spring-boot:run

### Rodando com Docker

docker build -t user-service .
docker run -p 8080:8080 user-service


### Usando Docker Compose

docker-compose up --build
