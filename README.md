# TodoList em Java Spring Boot e H2 Hibernate

Este projeto é uma API desenvolvida durante o curso de Java da Rocketseat. A API está atualmente hospedada em [https://todolist-java-qw3x.onrender.com](https://todolist-java-qw3x.onrender.com). Abaixo estão listados os métodos disponíveis:

Exemplo de uso do url de uma requisição em uma API client (Postman, Insomnia, API Dog):

**GET** `https://todolist-java-qw3x.onrender.com/tasks/`

## Métodos Disponíveis

###  POST /users/

- **Descrição**: Cria um novo usuário
- **Corpo da solicitação**:
    ```
    {
        "name": "Gabrielli Lima",
        "username": "gabilima",
        "password": "1234"
    }
    ```
- **Tratamento de erros**:
    - Usuário já existe

---

### POST /tasks/

- **Descrição**: Cria uma nova tarefa
- **Autorização**: É necessário utilizar o Basic Auth com as credenciais do usuário criado anteriormente
- **Corpo da solicitação**:
    ```
    {
         "description": "Fazer compras no mercado",
         "title": "Lista de compras",
         "priority": "MÉDIA",
         "startAt": "2023-10-20T10:00:00",
         "endAt": "2023-10-20T14:30:00",
    }
    ```
- **Tratamento de erros**:
    - A data de início deve ser anterior à data de término
    - A data de início / data de término deve ser posterior à data atual

---

### GET /tasks/

- **Descrição**: Lista todas as tarefas geradas pelo usuário
- **Autorização**: É necessário utilizar o Basic Auth com as credenciais do usuário criado no primeiro passo
- **Exemplo de resposta**:
    ```json
    [
        {
          "id": "d31cbf3c-9e31-4e92-a975-308d67803148",
          "description": "Fazer compras no mercado",
          "title": "Lista de compras",
          "startAt": "2023-10-20T10:00:00",
          "endAt": "2023-10-20T14:30:00",
          "priority": "MÉDIA",
          "createdAt": "2023-10-16T21:21:55.613888",
          "idUser": "88adcfa5-5160-4dac-902f-c103c154349c"
        }
    ]
    ```
    
---

### PUT /tasks/{id}

- **Descrição**: Atualiza uma tarefa específica
- **Autorização**: É necessário utilizar o Basic Auth com as credenciais do usuário criado anteriormente
- **Parâmetro de caminho**: {id} - é o id da tarefa
- **Exemplo do corpo da solicitação**:
    ```
    {
       "title": "Lista de compras atualizada",
       "priority": "BAIXA"
    }
    ```
- **Tratamento de erros**:
    - Tarefa não encontrada
    - O usuário não tem permissão para alterar esta tarefa
    - O campo 'title' deve conter no máximo 50 caracteres
