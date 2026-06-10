package com.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteRepositoryTest {

    @Test
    public void deveMostrarOSilenciamentoDeErrosNoRepositorio() {
        // Criar um banco inexistente para forçar um erro de SQL aqui
        Database database = new Database("banco_inexistente.db");
        ClienteRepository repository = new ClienteRepository(database);

        // BUG DE ARQUITETURA:
        // O metodo loadFromId vai falhar porque o banco não tem nenhuma tabela
        // O certo seria o sistema lançar um erro, mas foi colocado um try-catch que manda um System.out.println(e). O sistema falha de forma silenciosa e retorna NULL
        assertDoesNotThrow(() -> repository.loadFromId(999), "BUG DE ARQUITETURA: Os repositórios pegam os erros de SQL e silenciam eles (System.out.println), impedindo o Front-end por exemplo de saber que o banco falhou");

        // Como falhou silenciosamente, o retorno é nulo!
        assertNull(repository.loadFromId(999));
    }
}