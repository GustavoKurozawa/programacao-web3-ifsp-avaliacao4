# Avaliação 4 - Programação Web 3 (IFSP)
### API de Gerenciamento de Consertos de Oficina Mecânica

Projeto desenvolvido para a disciplina de **Programação Web 3 (PRW3)** do **IFSP**, referente à **Avaliação 4**.

Esta versão é uma evolução da Avaliação 3, implementando controle de acesso e autenticação stateless com **Spring Security** e **JSON Web Tokens (JWT)**, além da adoção de **UUID** na entidade `Conserto`.

---

## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot** (Web MVC, Data JPA, Security, Validation)
* **Flyway Migration** (controle de versão do banco de dados)
* **Banco H2** (armazenamento em arquivo local `./DATA/consertodb`)
* **Auth0 java-jwt** (geração e validação de tokens JWT)
* **Lombok**

---

## O que foi implementado nesta avaliação

1. **Identificador Único (UUID):**
   - Migration `V4` adicionando a coluna `uuid` única na tabela `consertos`.
   - Ajuste na entidade `Conserto` para geração automática de UUID e uso no `equals` e `hashCode`.

2. **Modelagem de Usuários e Criptografia:**
   - Migration `V5` criando a tabela `usuarios` (`id`, `login`, `senha`).
   - Migration `V6` inserindo o usuário inicial de teste com senha encriptada usando **BCrypt**.
   - Entidade `Usuario` implementando `UserDetails`.

3. **Autenticação Stateless com JWT:**
   - Serviço `PW3TokenService` para emissão de tokens assinados (HMAC256) com validade de 2 horas e extração do subject.
   - Endpoint `POST /login` para autenticação com retorno do token JWT.

4. **Filtro de Segurança (`SecurityFilter`):**
   - Interceptação de requisições via `OncePerRequestFilter`, validação do token presente no cabeçalho `Authorization: Bearer <token>` e autenticação no contexto do Spring.
   - Configuração de segurança liberando apenas a rota `/login` e exigindo autenticação para todas as demais.

---

## Credenciais para Teste

O banco de dados já possui um usuário cadastrado via migration para testes:

* **Login:** `professor@gmail.com`
* **Senha:** `123456`

---

## Principais Endpoints

| Método | Endpoint | Protegido? | Descrição |
| :--- | :--- | :---: | :--- |
| `POST` | `/login` | Não | Autentica o usuário e retorna o token JWT |
| `POST` | `/consertos` | Sim | Cadastra um novo conserto |
| `GET` | `/consertos` | Sim | Lista todos os consertos (paginado) |
| `GET` | `/consertos/algunsdados` | Sim | Lista consertos ativos com dados parciais (paginado) |
| `GET` | `/consertos/{id}` | Sim | Detalha um conserto por ID |
| `PUT` | `/consertos` | Sim | Atualiza dados de um conserto |
| `DELETE`| `/consertos/{id}` | Sim | Exclusão lógica do conserto (`ativo = false`) |

> **Nota:** Para requisições protegidas, inclua o cabeçalho:  
> `Authorization: Bearer <SEU_TOKEN_AQUI>`

---

## Como executar o projeto

1. **Clonar o repositório:**
   ```bash
   git clone git@github.com:GustavoKurozawa/programacao-web3-ifsp-avaliacao4.git
   ```

2. **Executar a aplicação:**
   * No Windows:
     ```bash
     .\mvnw.cmd spring-boot:run
     ```
   * No Linux/Mac:
     ```bash
     ./mvnw spring-boot:run
     ```

3. **Acesso:**
   * A API subirá por padrão na porta `8080` (`http://localhost:8080`).
   * O console do banco H2 fica disponível em `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:file:./DATA/consertodb`, usuário: `sa`, sem senha).
