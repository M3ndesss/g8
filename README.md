# Back-end: Sistema de Agendamento Médico

Este documento apresenta a proposta inicial de arquitetura, decisões de projeto e a estrutura base desenvolvida para o sistema de agendamento médico em Java.


## 1. Visão Geral da Arquitetura & Padrões de Projeto

Para garantir que o sistema seja modular, escalável e de fácil manutenção, adotamos os seguintes padrões arquiteturais:

* **Padrão Creator / OOP Puro:** Classes de entidade bem definidas utilizando os pilares da Orientação a Objetos (Herança e Polimorfismo), mapeando a regra de negócio do hospital.
* **Data Access Object (DAO) / Repository:** Camada de persistência isolada da lógica de negócios. Cada entidade principal possui seu repositório dedicado, centralizando as operações de CRUD.
* **Mapeamento Objeto-Relacional (ORM):** Utilização da biblioteca **ORMLite** para ler os metadados das classes Java e traduzi-los de forma automatizada para o banco de dados.

---

## 2. Armazenamento de Dados

* **Tecnologia:** Banco de dados relacional local **SQLite** (arquivo físico `hospital.db`).
* **Estratégia de Relacionamentos:** * **Muitos-para-Um (Many-to-One):** Consultas vinculadas a Médicos e Clientes via Chaves Estrangeiras (`foreign = true`).
    * **Muitos-para-Muitos (Many-to-Many):** Histórico de atendimentos do Prontuário mapeado de forma persistente através de uma tabela associativa/ponte (`ProntuarioMedico`) e carregamento via coleções (`ForeignCollectionField`).

---

## 3. Estrutura de Classes Mapeadas

* `Usuario` (Classe Abstrata Mãe - ID Autoincremento)
    * `Cliente` (Herda de Usuario + Vínculo com Prontuário)
    * `Medico` (Herda de Usuario + Especialidade e Senha)
    * `Secretaria` (Herda de Usuario + Senha)
* `Prontuario` (Contém a coleção do histórico de conexões com Médicos)
* `ProntuarioMedico` (Tabela pivô de ligação Muitos-para-Muitos)
* `Consulta` (Une data, horário, médico e cliente)

---

## 4. Estrutura Base do Projeto (Camada de Repositórios)

As seguintes classes foram implementadas para gerenciar a persistência em disco:
* `Database.java` (Centraliza e gerencia a conexão JDBC com o SQLite)
* `ClienteRepository.java`
* `MedicoRepository.java`
* `SecretariaRepository.java`
* `ProntuarioRepository.java`
* `ConsultaRepository.java`

---

## 5. Codigo passado para o VS code

Com o intuito de facilitar o trabalho dos outros membros do grupo, o código fonte foi passado do BlueJ para o VS code.

*Notas de Brainstorm enviadas para a Branch de Back-end em junho de 2026.*