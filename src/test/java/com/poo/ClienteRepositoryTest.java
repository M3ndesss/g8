package com.poo;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteRepositoryTest {

    @Test
    public void deveRetornarNuloQuandoClienteNaoExistir() throws SQLException {
        Database database = new Database("banco_inexistente.db");
        ClienteRepository repository = new ClienteRepository(database);

        // Como o banco é criado limpo pelo SQLite, buscar um ID inexistente retorna null (não dá erro)
        Cliente resultado = repository.loadFromId(999);
        assertNull(resultado, "O repositório tem que retornar null se o ID não existir no banco");
    }
}