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

**Observações:**

* Substitua `[Image of Diagrama de Classes]` e `[Image of Diagrama de
  Arquitetura]` pelos nomes dos seus arquivos de imagem.
* Certifique-se de que os arquivos de imagem estejam no mesmo diretório
  do arquivo `README.md` ou em um subdiretório.
* Você pode adicionar mais informações ao README, como instruções de
  instalação, exemplos de uso, informações de contato, etc.


