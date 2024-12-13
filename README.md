# Microserviço: User

Este projeto é um microserviço do sistema. Ele utiliza Java com Spring Boot e Maven para gerenciar as dependências. Este guia explica como construir e rodar o microserviço em um container Docker.

## Pré-requisitos
- Docker instalado no seu sistema
- Maven (caso deseje rodar localmente antes de usar Docker)

## Passos para rodar o serviço

### 1. Construir o container Docker
Certifique-se de estar na pasta do serviço `user`. Use o seguinte comando para criar a imagem Docker:

```bash
docker build -t user-service .
