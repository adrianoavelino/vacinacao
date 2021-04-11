# vacinacao
Desafio prático da ZUP

# Começando
```bash
#clona o projeto
git clone https://github.com/adrianoavelino/vacinacao

#entra no diretório
cd vacinacao

#renomeia o arquivo com as variáveis de ambiente
mv .env-sample .env

#cria o jar da aplicação
./mvnw clean package -DskipTests

#inicia a aplicação
docker-compose up -d --build
```
> Para alterar as configurações do banco de dados e porta de aplicação altere o arquivo .env

Outras informações:
```bash
#acessa o postgresql
docker-compose run --rm db psql -U postgres -h db

#acessa o banco de dados
\c vacinacao

#lista as tabelas do banco de dados
\d

#visualiza a estrutura da tabela usuarios
\d+ usuarios

#visualiza o conteúdo das tabelas
select * from usuarios;
select * from vacinas;
select * from aplicacao_vacinas;
```

## Documentação da API
Com a aplicação iniciada acesse [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) para visualizar a documentação da API.
