package com.poo;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {

    @Test
    public void deveLancarUmaExcecaoSeOBancoDeDadosForNulo(){
        Database database = new Database(null);

        // Quando o banco for nulo, a conexão falha. Foi certo em deixar o SQLException estourar
        assertThrows(SQLException.class, database::getConnection, "O sistema deve lançar uma SQLException ao tentar conectar num banco nulo como esse.");
    }
}
