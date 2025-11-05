# Fintech Project (FIAP) – Backend

Projeto da disciplina integrando **Java + Spring Boot + Oracle** (Frontend React será adicionado depois).

## 🧩 Entidades implementadas
- **Usuário**
- **Conta**
- **Despesa**

## ▶️ Como rodar o Backend

**Pré-requisitos**
- JDK 21 instalado
- Maven instalado
- Acesso ao Oracle da FIAP

**Configure o banco no `src/main/resources/application.properties`**

spring.application.name=fintech

spring.datasource.url=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL

spring.datasource.username=RM562426

spring.datasource.password=********

spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true


## ✅ Códigos de status da API
| Operação | Método | Status retornado |
|----------|--------|------------------|
| Criar recurso | `POST` | **201 Created** |
| Listar / Buscar recurso | `GET` | **200 OK** |
| Atualizar recurso | `PUT` | **200 OK** |
| Deletar recurso | `DELETE` | **204 No Content** |
| Recurso não encontrado | - | **404 Not Found** |
| Conflito (email já cadastrado, etc) | - | **409 Conflict** |



**Execute**
```bash
mvn spring-boot:run
A API sobe em http://localhost:8080.

🔐 Usuário de teste
(definiremos no Frontend; por enquanto a API não exige login real)
Sugestão para testes de carga inicial:


email: teste@fiap.com
senha: 123456
🌐 Rotas principais (exemplos)
bash
Copiar código
POST   /usuarios
GET    /usuarios
GET    /usuarios/{id}
PUT    /usuarios/{id}
DELETE /usuarios/{id}

POST   /usuarios/{usuarioId}/contas
GET    /usuarios/{usuarioId}/contas
GET    /contas/{id}
PUT    /contas/{id}
DELETE /contas/{id}

POST   /usuarios/{usuarioId}/despesas
GET    /usuarios/{usuarioId}/despesas
GET    /despesas/{id}
PUT    /despesas/{id}
DELETE /despesas/{id}



