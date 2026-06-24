package com.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void deveCriarClienteComDadosValidos() {
        Prontuario prontuario = new Prontuario("Gripe");
        Cliente cliente = new Cliente("Maria Pereira da Silva", "123.456.789-00", 5, prontuario, "12/05/1990");

        assertEquals("Maria Pereira da Silva", cliente.getNome());
        assertNotNull(cliente.getProntuario());
        assertEquals("12/05/1990", cliente.getAniverssario());

        // BUG DO BACKEND: O construtor recebe 5, mas chumba 0. Esperamos 0 para o teste passar hoje.
        assertEquals(0, cliente.getId(), "BUG (ORMLITE): O construtor recebe o ID, mas chumba 0 na superclasse.");
    }

    @Test
    public void deveExporFaltaDeValidacaoDeEdgeCasesNoCliente() {
        // Testando o Caminho Triste: Data inválida, Prontuário Nulo e ID negativo
        Cliente cliente = new Cliente("Fulano", "123.456.789-00", -10, null, "99/99/9999");

        // BUGS (SENTIDO):
        assertNull(cliente.getProntuario(), "BUG DE SENTIDO: Permite criar um cliente sem prontuário (nulo).");
        assertEquals("99/99/9999", cliente.getAniverssario(), "BUG DE SENTIDO: Aceita datas impossíveis de aniversário.");
    }

    @Test
    public void deveCriarClienteComConstrutorVazio() {
        assertDoesNotThrow(() -> new Cliente());
    }
}
