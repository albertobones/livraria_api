# livraria_api
Sistema para cadastro de livros, autores e assuntos

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot 2.7.9**
- **Maven 3.6.3**
- **PostgreSQL 13.0**
- **MapStruct 1.5.5**
- **Lombok 1.18.34**
- **JasperReport 6.17.0**
- **Flyway 8.5.13**
- **Tomcat embutido do Springboot**

## Pré-requisitos

- [Java 11 JDK]
- [Maven 3.6.3]
- [PostgreSQL 13.0]

## Configuração do Ambiente

1. Clone o repositório:
	-git clone https://github.com/albertobones/livraria_api.git
	-entre na pasta do projeto
	-git checkout develop

2. Configuração do Banco de Dados:

	Existem dois profiles "test" e "dev"
	-Para a configuração do PostgreSQL utilize o profile dev, você pode alterar o profile no arquivo application.properties
	-Crie o banco de dados no PostgreSQL. As tabelas são criadas automaticamento pelo flyway ao executar o projeto.
	-Execute o comando CREATE DATABASE "Livraria"; 
	-Atualize o arquivo application-dev.properties com as configurações do seu banco de dados
		spring.datasource.url=jdbc:postgresql://localhost/Livraria
		spring.datasource.username=xxxxx
		spring.datasource.password=xxxxx

3. Construção do Projeto:
	mvn clean install
	
## Executar o projeto ##

	-Execute o comando para iniciar o servidor Spring Boot ou pode rodar o jar gerado
		mvn spring-boot:run
		java -jar target/livraria-api-0.0.1-SNAPSHOT.jar
	
	-Endereço da aplicação http://localhost:8080
	