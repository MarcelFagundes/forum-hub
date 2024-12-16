# Praticando Spring Framework: Challenge Fórum Hub

## Descrição do Projeto

Este projeto é uma API RESTful desenvolvida em Java utilizando o Spring Framework. Ela simula um sistema de fórum onde os usuários podem gerenciar tópicos, incluindo funcionalidades para criação, atualização, detalhamento, listagem e exclusão de tópicos.

---

## Funcionalidades

1. **Listagem de Tópicos**
    - Endpoint: `GET /topicos`
    - Retorna uma lista paginada de tópicos com informações como título, status, autor, data de criação e curso.

2. **Detalhamento de Tópico**
    - Endpoint: `GET /topicos/{id}`
    - Retorna os detalhes de um tópico específico baseado no ID fornecido.

3. **Criação de Tópico**
    - Endpoint: `POST /topicos`
    - Permite criar um novo tópico enviando os dados necessários no corpo da requisição.

4. **Atualização de Tópico**
    - Endpoint: `PUT /topicos/{id}`
    - Atualiza os dados de um tópico específico baseado no ID fornecido.

5. **Exclusão de Tópico**
    - Endpoint: `DELETE /topicos/{id}`
    - Remove um tópico específico baseado no ID fornecido.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA**
- **MySQL** (banco de dados para desenvolvimento e testes)
- **Maven** (gerenciador de dependências)

---

## Requisitos

- **Java 17**
- **Maven**
- Editor de código como IntelliJ IDEA ou Eclipse.

---

## Como Rodar o Projeto

1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/marcelfagundes/forum-hub.git
   cd forum-hub
   ```

2. **Configure o Ambiente:**
   Certifique-se de que o Java 17 e o Maven estão instalados corretamente.

3. **Execute a Aplicação:**
   ```bash
   mvn spring-boot:run
   ```

4. **Acesse a API:**
   A API estará disponível em `http://localhost:8080`.

---

## Estrutura do Projeto

- **Model:** Contém as classes que representam os dados (entidades).
- **Repository:** Interfaces para acesso ao banco de dados utilizando Spring Data JPA.
- **Service:** Contém a lógica de negócios.
- **Controller:** Gerencia as requisições HTTP e direciona as chamadas para os serviços apropriados.
- **DTO:** Classes para transferência de dados (entrada e saída).

---

## Exemplos de Requisição

### Listagem de Tópicos

**Requisição:**
```http
GET /topicos?page=0&size=10 HTTP/1.1
Host: localhost:8080
```

**Resposta:**
```json
{
  "content": [
    {
      "id": 1,
      "title": "Introdução ao Spring Boot",
      "message": "Este tópico aborda os fundamentos do Spring Boot.",
      "creationDate": "2024-12-10T10:00:00",
      "status": "CLOSED",
      "author": "Marcel",
      "course": "Java Development"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10
  },
  "totalElements": 1
}
```

---

## Melhorias Futuras

- Autenticação e autorização utilizando Spring Security.
- Implementação de testes unitários e de integração.
- Integração com banco de dados externo (MySQL ou PostgreSQL).
- Adicionar documentação da API com Swagger.

---

## Contribuição

1. Faça um fork do projeto.
2. Crie uma branch para sua funcionalidade:
   ```bash
   git checkout -b minha-funcionalidade
   ```
3. Commit suas mudanças:
   ```bash
   git commit -m 'Adicionei minha funcionalidade'
   ```
4. Envie suas mudanças:
   ```bash
   git push origin minha-funcionalidade
   ```
5. Abra um Pull Request no repositório original.

---

## Autor

**Marcel Fagundes Souza**  
Localização: Jundiaí, SP  
Email: marcel@example.com  
GitHub: [github.com/marcel-fagundes](https://github.com/marcel-fagundes)

---

## Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
