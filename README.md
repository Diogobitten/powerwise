# ⚡ Powerwise – Monitoramento e Otimização de Consumo de Energia

Powerwise é uma API RESTful desenvolvida com Java e Spring Boot, com foco em eficiência energética e sustentabilidade (ESG). A aplicação permite o gerenciamento de registros de consumo elétrico de equipamentos, oferecendo recursos de análise, filtragem e segurança via autenticação básica.

## 📋 Requisitos Atendidos

- ✅ **Mínimo de 4 endpoints RESTful**
- ✅ **Validações e boas práticas com DTOs**
- ✅ **Tratamento de exceções**
- ✅ **Segurança com Spring Security**
- ✅ **Banco de dados Oracle**
- ✅ **Migrations com Flyway**
- ✅ **Containerização com Docker**
- ✅ **Arquivo de exportação Insomnia com todos os endpoints**

---

## 🚀 Endpoints Disponíveis

Base URL: `http://localhost:8080/api/consumos`

| Método | Rota                      | Descrição                                                        |
|--------|---------------------------|------------------------------------------------------------------|
| GET    | `/`                       | Lista todos os registros de consumo                              |
| POST   | `/`                       | Cadastra um novo consumo (requer JSON com os dados)              |
| GET    | `/{id}`                   | Busca um consumo pelo ID                                         |
| PUT    | `/{id}`                   | Atualiza um consumo pelo ID                                      |
| DELETE | `/{id}`                   | Remove um consumo pelo ID                                        |
| GET    | `/alto-consumo?limite=x`  | Lista registros acima de um determinado valor                    |
| GET    | `/equipamento?nome=x`     | Lista registros por nome de equipamento                          |
| GET    | `/periodo?inicio=...&fim=...` | Lista registros entre duas datas (formato: `yyyy-MM-dd`)     |
| GET    | `/media?equipamento=x`    | Calcula a média de consumo por equipamento                       |

---

## 🔐 Segurança (Spring Security)

A API está protegida por autenticação **HTTP Basic**.

**Credenciais padrão:**
- Usuário: `admin`
- Senha: `123456`

---

## 🗂️ Estrutura do Projeto

- `model`: entidade `Consumo`
- `repository`: interface com `JpaRepository`
- `service`: regras de negócio e cálculos
- `controller`: endpoints REST
- `dto`: transporte seguro de dados
- `config`: configuração do Spring Security

---

## 🐳 Docker e Docker Compose

### Build da imagem:

```bash
docker build -t powerwise-app .
