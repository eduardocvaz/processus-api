## Processus API

**API REST para gerenciamento de processos jurídicos.**

![Diagrama de Arquitetura!](/docs/diagramas/Architecture-Diagram.jpg)
![Diagrama de Classes](/docs/diagramas/diagrama-de-classe.png)


**Funcionalidades:**

* Criar, editar, consultar e arquivar processos.
* Gerenciar partes envolvidas nos processos (autor, réu, advogado).
* Registrar ações processuais (petições, audiências, sentenças).
* Armazenar documentos relacionados às ações.
* Buscar processos por diversos critérios (ID, status, data de
  abertura, CPF/CNPJ das partes).

**Tecnologias:**

* Java
* Spring Boot
* Spring Data JPA
* PostgreSQL
* ModelMapper
* Swagger

**Endpoints:**

* **/api/v1/processos**: Gerencia os processos.
* **/api/v1/partes**: Gerencia as partes envolvidas nos processos.
* **/api/v1/acoes**: Gerencia as ações processuais.
* **/api/v1/documentos**: Gerencia os documentos relacionados às ações.

**Como acessar:**

* **Aplicação:**
  [https://www.processus.eduardovaz.dev/](https://www.processus.eduardovaz.dev/)
* **Documentação da API (Swagger):**
  [https://www.processus.eduardovaz.dev/swagger-ui/index.html#/](https://www.processus.eduardovaz.dev/swagger-ui/index.html#/)


