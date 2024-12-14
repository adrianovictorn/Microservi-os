# Use a imagem base com JDK 21 (ou a versão usada no seu projeto)
FROM openjdk:21-jdk-slim

# Diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml e os arquivos de configuração para o container
COPY pom.xml /app
COPY src /app/src

# Baixar dependências do Maven
RUN apt-get update && apt-get install -y maven && mvn dependency:go-offline -B

# Compilar o projeto
RUN mvn clean package -DskipTests

# Expor a porta usada pelo Spring Boot (ajustar conforme necessário)
EXPOSE 8080

# Comando para executar o JAR gerado
CMD ["java", "-jar", "target/*.jar"]
