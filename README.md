# 💸 Fintech Project (FIAP) – Backend

Projeto desenvolvido para a disciplina de **Java + Spring Boot + Oracle**  
(O frontend React será implementado na segunda parte da entrega).

---

## ✅ Tecnologias utilizadas

| Stack | Tecnologia |
|--------|------------|
| Backend | Java 21, Spring Boot, Maven |
| Banco de Dados | Oracle FIAP |
| JPA / ORM | Hibernate |
| Cliente HTTP | Postman / Insomnia (para testes) |

---

## 🏦 Entidades implementadas

- `Usuario`
- `Conta`
- `Despesa`


---

## ▶️ Como rodar o Backend

### 🔧 Pré-requisitos

- JDK 21 instalado
- Maven instalado
- Acesso à instância Oracle da FIAP

### 1️⃣ Configure `src/main/resources/application.properties`

```properties
spring.application.name=fintech

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
spring.datasource.username=RM562426
spring.datasource.password=********
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
⚠️ Obs.: O ddl-auto=none garante que o banco não será recriado.
As tabelas devem ser criadas antes via script SQL (pasta /db).

2️⃣ Execute o projeto
mvn spring-boot:run
A API subirá em:
🔗 http://localhost:8080

👤 Usuário de teste
(por enquanto a API não exige login real – validação virá no frontend)

Campo	Valor
email	teste@fiap.com
senha	123456

📌 Rotas principais (REST)
🧍‍♂️ Usuários
POST   /usuarios
GET    /usuarios
GET    /usuarios/{id}
PUT    /usuarios/{id}
DELETE /usuarios/{id}

🏦 Contas por usuário
POST   /usuarios/{usuarioId}/contas
GET    /usuarios/{usuarioId}/contas
GET    /contas/{id}
PUT    /contas/{id}
DELETE /contas/{id}

💸 Despesas por usuário
POST   /usuarios/{usuarioId}/despesas
GET    /usuarios/{usuarioId}/despesas
GET    /despesas/{id}
PUT    /despesas/{id}
DELETE /despesas/{id}

✅ Códigos de status da API
Operação	Método	Status
Criar recurso	POST	201 Created
Buscar / listar	GET	200 OK
Atualizar recurso	PUT	200 OK
Remover recurso	DELETE	204 No Content
Não encontrado	-	404 Not Found
Conflito (duplicado)	-	409 Conflict

📂 Estrutura do repositório (backend)
backend/
 ├─ src/main/java/br/com/fiap/fintech/
 │   ├─ controller/
 │   ├─ service/
 │   ├─ repository/
 │   ├─ model/
 │   └─ FintechApplication.java
 ├─ src/main/resources/application.properties
 └─ pom.xml
🚧 Próximos passos (etapa frontend)
 Criar projeto React na pasta /frontend

 Implementar tela de login com consumo da API

 Listagem de contas e despesas

 Deploy do frontend e backend

 Atualizar README final com instruções do React

📜 Licença
Projeto acadêmico – FIAP 2025.
Uso permitido somente para fins educacionais.

---

API – Rotas & Exemplos

Base URL (local): http://localhost:8080
Formato: application/json
Auth: não há autenticação ainda (usuário de teste apenas)

CORS: já liberado para http://localhost:5173 (Vite) e http://localhost:3000 (CRA).

Sumário de Endpoints
Recurso	Método	Caminho	Descrição	Status
Usuário	POST	/usuarios	Criar usuário	201
Usuário	GET	/usuarios	Listar usuários	200
Usuário	GET	/usuarios/{id}	Obter usuário por id	200/404
Usuário	PUT	/usuarios/{id}	Atualizar usuário	200/404
Usuário	DELETE	/usuarios/{id}	Remover usuário	204/404
Conta	POST	/contas/usuarios/{usuarioId}/contas	Criar conta para um usuário	201
Conta	GET	/contas/usuarios/{usuarioId}/contas	Listar contas do usuário	200
Conta	GET	/contas/{id}	Obter conta por id	200/404
Conta	PUT	/contas/{id}	Atualizar conta	200/404
Conta	DELETE	/contas/{id}	Remover conta	204/404
Despesa	POST	/despesas/usuarios/{usuarioId}/despesas	Criar despesa para um usuário	201
Despesa	GET	/despesas/usuarios/{usuarioId}/despesas	Listar despesas do usuário	200
Despesa	GET	/despesas/{id}	Obter despesa por id	200/404
Despesa	PUT	/despesas/{id}	Atualizar despesa	200/404
Despesa	DELETE	/despesas/{id}	Remover despesa	204/404
Usuários
Criar usuário

POST /usuarios → 201 Created

Body

{
  "nome": "Usuário Teste",
  "email": "teste@fiap.com",
  "senha": "123456"
}


Resposta 201

{
  "id": 1,
  "nome": "Usuário Teste",
  "email": "teste@fiap.com",
  "senha": "123456",
  "dataCadastro": "2025-11-05T19:30:00Z"
}


curl

curl -i -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nome":"Usuário Teste","email":"teste@fiap.com","senha":"123456"}'

Listar usuários

GET /usuarios → 200 OK

curl

curl -s http://localhost:8080/usuarios | jq

Obter usuário

GET /usuarios/{id} → 200 OK | 404 Not Found

curl

curl -i http://localhost:8080/usuarios/1

Atualizar usuário

PUT /usuarios/{id} → 200 OK | 404 Not Found

Body (parcial ou completo)

{
  "nome": "Usuário Editado"
}


curl

curl -i -X PUT http://localhost:8080/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"Usuário Editado"}'

Remover usuário

DELETE /usuarios/{id} → 204 No Content | 404 Not Found

curl

curl -i -X DELETE http://localhost:8080/usuarios/1

Contas

Rotas subordinadas a um usuário para criação/listagem, e diretas por id para obter/editar/deletar.

Criar conta para usuário

POST /contas/usuarios/{usuarioId}/contas → 201 Created

Body

{
  "tipo": "corrente",
  "agencia": "0001",
  "numero": "12345-6",
  "saldo": 1500.00
}


curl

curl -i -X POST http://localhost:8080/contas/usuarios/1/contas \
  -H "Content-Type: application/json" \
  -d '{"tipo":"corrente","agencia":"0001","numero":"12345-6","saldo":1500.00}'

Listar contas de um usuário

GET /contas/usuarios/{usuarioId}/contas → 200 OK

curl

curl -s http://localhost:8080/contas/usuarios/1/contas | jq

Obter / Atualizar / Remover conta por id

GET /contas/{id} → 200/404
PUT /contas/{id} → 200/404
DELETE /contas/{id} → 204/404

Exemplo PUT

curl -i -X PUT http://localhost:8080/contas/10 \
  -H "Content-Type: application/json" \
  -d '{"saldo": 1999.90}'

Despesas
Criar despesa para usuário

POST /despesas/usuarios/{usuarioId}/despesas → 201 Created

Body

{
  "descricao": "Supermercado",
  "valor": 250.75,
  "categoria": "Alimentação"
}


curl

curl -i -X POST http://localhost:8080/despesas/usuarios/1/despesas \
  -H "Content-Type: application/json" \
  -d '{"descricao":"Supermercado","valor":250.75,"categoria":"Alimentação"}'

Listar despesas de um usuário

GET /despesas/usuarios/{usuarioId}/despesas → 200 OK

curl

curl -s http://localhost:8080/despesas/usuarios/1/despesas | jq

Obter / Atualizar / Remover despesa por id

GET /despesas/{id} → 200/404
PUT /despesas/{id} → 200/404
DELETE /despesas/{id} → 204/404

Exemplo PUT

curl -i -X PUT http://localhost:8080/despesas/5 \
  -H "Content-Type: application/json" \
  -d '{"descricao":"Internet Fibra 300Mb"}'

Códigos de Status (resumo)

200 OK: consulta/atualização bem-sucedida

201 Created: recurso criado (com Location no header)

204 No Content: remoção bem-sucedida

404 Not Found: recurso não encontrado

400 Bad Request: corpo inválido (se validações forem adicionadas)

Dicas para o Frontend (React)

Base da API: http://localhost:8080

Para o Vite: npm run dev → http://localhost:5173 (CORS já liberado)

Para CRA: npm start → http://localhost:3000 (CORS já liberado)

Exemplo de fetch:

async function listarUsuarios() {
  const res = await fetch('http://localhost:8080/usuarios');
  if (!res.ok) throw new Error('Falha ao listar usuários');
  return res.json();
}
