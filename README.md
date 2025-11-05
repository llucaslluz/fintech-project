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

*(mínimo exigido: 3 entidades – OK ✅)*

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

✍️ Desenvolvido por Lucas Luz

---

### ✅ Próximo passo agora:
📌 Criar o arquivo `backend/README.md` com esse conteúdo  
📌 Fazer commit e push:

```bash
git add backend/README.md
git commit -m "README backend finalizado"
git push
