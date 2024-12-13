# Microserviço: User

Este repositório contém o microserviço `user`, desenvolvido com Java, Spring Boot e Maven. Ele é responsável pela gestão de usuários em um sistema baseado em microserviços.

## Pré-requisitos

Antes de rodar a aplicação, certifique-se de que possui os seguintes pré-requisitos instalados no seu sistema:

- **Java Development Kit (JDK)** 17 ou superior.
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
Este serviço requer um banco de dados configurado. O `docker-compose.yml` já está preparado para subir um container com o banco de dados, mas você pode configurar o banco localmente, se preferir.

### Arquivo de Configuração
Certifique-se de ajustar o arquivo `application.properties` (ou `application.yml`) localizado em `src/main/resources` para refletir as configurações do banco de dados. Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=root
spring.datasource.password=senha123
spring.jpa.hibernate.ddl-auto=update
````

mvn clean package
mvn spring-boot:run

### Rodando com Docker

docker build -t user-service .
docker run -p 8080:8080 user-service


### Usando Docker Compose

docker-compose up --build
