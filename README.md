<h1 align="center" style="font-weight: bold;">FlickFind 🎞</h1>

<p align="center">
 <a href="#tech">Tecnologias</a> • 
 <a href="#practices">Práticas adotadas</a> •
 <a href="#started">Começando</a> • 
  <a href="#routes">API Endpoints</a>
</p>

<p align="center">
    <b>API de Recomendação de filmes com base na preferência do usuário.</b>
</p>

<h2 id="technologies">💻 Tecnologias</h2>

- Java;
- PostgreSQL;
- RabbitMQ.

<h2 id="practices">🧭 Práticas adotadas</h2>

- API Rest;
- SOLID;
- Consultas com Spring Data JPA;
- Tratamento de Exceções;
- Microsservices via Mensageria.

<h2 id="started">🚀 Iniciando</h2>

O front-end desta aplicação será desenvolvido em breve.

<h3>Pré-requisitos</h3>

- [Java](https://www.java.com/pt-BR/)
- [PostgreSQL](https://www.postgresql.org)

<h3>Clonando</h3>

```bash
git clone git@github.com:joaovictornovais/flickfind.git
```

<h3>Configurando variáveis de ambiente</h2>

<p>⚠<b>IMPORTANTE: As variáveis de ambiente não são globais, configure cada pasta.</b></p>
<p>TODOS os serviços possuem as seguintes variáveis de ambiente:</p>

```yaml
DB_URL={url_do_banco_de_dados}
DB_USERNAME={usuario_do_banco_de_dados}
DB_PASSWORD={senha_do_banco_de_dados}

RABBITMQ_ADDRESS={url_do_rabbitmq}
```

<h4>🍃AuthApplication</h4>

```yaml
JWT_SECRET={secret}
```

<h4>🍃EmailApplication</h4>

```yaml
MAIL_USERNAME={seu_email}
MAIL_PASSWORD={senha_de_app_do_seu_email}
```

<p>Para descobrir a senha de app do seu email, acesse o artigo: https://support.google.com/accounts/answer/185833?hl=pt-BR </p>

<h4>🍃ProfileApplication</h4>

```yaml
TOKEN_VALIDATION_URL={url_de_validacao_de_token}
```

<h4>🍃ProfileApplication</h4>

```yaml
TOKEN_VALIDATION_URL={url_de_validacao_de_token}
PROFILES_URL={url_de_perfis}
```

<h4>🍃CatalogApplication</h4>

```yaml
TMDB_KEY={key_da_api_do_tmbd}
TOKEN_VALIDATION_URL={url_de_validacao_de_token}
PROFILES_URL={url_de_perfis}
```

<h3>Iniciando</h3>

Você deve iniciar cada serviço (auth/email/profile/catalog) individualmente

```bash
$ cd flickfind
$ cd {serviço}
$ ./mvnw clean package
```

Executar a aplicação
```bash
$ java -jar target/{serviço}-0.0.1-SNAPSHOT.jar
```

<h2 id="routes">📍 API Endpoints</h2>

<h3>Auth</h3>

| Rota                 | Descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>POST /auth/register</kbd>     | registrar usuário
| <kbd>POST /auth/login</kbd>        | realizar login
| <kbd>POST /tokens</kdb>            | validar token

<h3>Profile</h3>

| Rota                 | Descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>PUT /profiles</kbd>     | atualizar informações adicionais de usuário
| <kbd>GET /profiles </kbd>    | retornar filtros do usuário
| <kbd>PATCH /profiles</kdb>   | atualizar algum filtro

<h3>Catalog</h3>

| Rota                 | Descrição                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /movies</kbd>              | retorna lista de recomendação de filmes 
| <kbd>GET /movies?page={1}</kbd>     | selecionar página
