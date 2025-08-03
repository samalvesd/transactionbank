# API de Transação Bancária

Uma API RESTful que simula um sistema de transações bancárias. Ela permite a criação e consulta de contas, a realização de transações financeiras e a listagem do histórico de todas as transações realizadas.

### **Funcionalidades**

- **Criação de Conta**: Crie uma nova conta com saldo inicial.
- **Consulta de Saldo**: Obtém o saldo de uma conta específica pelo seu número.
- **Transações Financeiras**: Realiza transações de débito, crédito ou PIX, debitando valores das contas.
- **Histórico de Transações**: Lista todas as transações registradas no sistema.

---

### **Tecnologias Utilizadas**

O projeto foi construído utilizando as seguintes tecnologias:

- **Linguagem**: Java 21
- **Framework**: Spring Boot
- **Banco de Dados**: H2 (banco de dados em memória)
- **Gerenciador de Dependências**: Gradle

---

### **Como Executar a Aplicação**

Siga os passos abaixo para ter a API rodando em sua máquina.

#### **Pré-requisitos**

Antes de começar, certifique-se de que você tem instalado:

- **Java 21 Oracle OpenJDK** (ou uma versão compatível).
- **Gradle** (para construir e executar o projeto).

A aplicação utiliza o banco de dados **H2**, então não é necessária nenhuma instalação ou configuração de banco de dados externa.

#### **Passo a Passo**

1.  **Clone o Repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd nome-do-seu-repositorio
    ```

2.  **Construa o Projeto:**
    Utilize o Gradle para construir o projeto e resolver as dependências.
    ```bash
    ./gradlew build
    ```

3.  **Inicie a Aplicação:**
    Após a construção bem-sucedida, você pode iniciar a API.
    ```bash
    ./gradlew bootRun
    ```
    A aplicação estará disponível em `http://localhost:8080`.

---

### **Documentação da API**

Os endpoints da API podem ser acessados na porta `8080`.

#### **1. Criar uma Nova Conta**

- **`POST`** `/conta`
- **Descrição**: Cria uma nova conta.
- **Parâmetros**: Um JSON no corpo da requisição com os campos `numero_conta` (Long) e `saldo` (float).
- **Exemplo de Requisição**:
```
  {
    "numero_conta": 234,
    "saldo": 180.37
  }
```
- **Respostas Esperadas**:
  ✅ `201 Created`
  ❌ `400 Bad Request`

#### **2. Realizar uma Transação**

- **`POST`** `/transacao`
- **Descrição**: Realiza uma transação, debitando o valor da conta com base na forma de pagamento.
- **Parâmetros**: Um JSON no corpo da requisição com os campos `forma_pagamento` (String), `numero_conta` (Long) e `valor` (float).
- **Exemplo de Requisição**:
```
  {
  "forma_pagamento": "C",
  "numero_conta": 234,
  "valor": 10.50
}
```
- **Respostas Esperadas**:
  ✅ `201 Created`
- Se a conta náo existir -
  ❌ `404 Not Found`: Conta não encontrada para este número de conta informado.
- Se o valor informado for maior que o saldo da conta -
  ❌ `404 Not Found`: Saldo insuficiente para efetuar a transação.
- Caso seja informado uma Forma de Pagamento não cadastrada -
  ❌ `500 Internal Server Error`: Forma de pagamento inválida.
- **Exemplo de Resposta**:
```
{
    "numero_conta": 234,
    "saldo": 159.37
}
```

#### **3. Buscar Conta**

- **`GET`** `/conta?numero_conta={numero}`
- **Descrição**: Busca o saldo de uma conta específica usando o número da conta como parâmetro.
- **Parâmetros**: Query Params com a Key: `numero_conta` e Value: {numero}.
- **Exemplo de Requisição**: `GET http://localhost:8080/conta?numero_conta=234`
- **Respostas Esperadas**:
  ✅ `200 OK`
  ❌ `404 Not Found`
- **Exemplo de Resposta**:
```
{
    "numero_conta": 234,
    "saldo": 159.37
}
```
#### **4. Listar Transações**

- **`GET`** `/transacao`
- **Descrição**: Lista todas as transações persistidas no banco de dados.
- **Parâmetros**: Não espera nenhum parâmetro.
- **Exemplo de Requisição**: `GET http://localhost:8080/transacao`
- **Respostas Esperadas**:
  ✅ `200 OK`: Retorna uma lista de objetos JSON com as transações. Se nenhuma transação foi feita, retorna uma lista vazia [].
- **Exemplo de Resposta**:
```
[
  {
    "forma_pagamento": "P",
    "numero_conta": 101,
    "valor": 1.0,
    "id": 1
  },
  {
    "forma_pagamento": "C",
    "numero_conta": 234,
    "valor": 10.5,
    "id": 2
  }
]
```
