package com.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProntuarioTest {

    @Test
    public void deveCriarProntuarioERecuperarDados() {
        Prontuario prontuario = new Prontuario("Febre");
        assertEquals("Febre", prontuario.getDoença());

        // O construtor original chumba 0, vamos testar se o Setter aqui funciona
        prontuario.setId(15);
        assertEquals(15, prontuario.getId());
    }

    @Test
    public void deveExporFaltaDeValidacaoAoSetarIdNegativoEDoencaVazia() {
        Prontuario prontuario = new Prontuario(""); // Doença vazia
        prontuario.setId(-5); // ID negativo

        // BUGS (SENTIDO):
        assertEquals(-5, prontuario.getId(), "BUG DE SENTIDO: O método setId() aceita números negativos.");
        assertEquals("", prontuario.getDoença(), "BUG DE SENTIDO: Permite criar prontuário sem especificar a doença.");
    }

    @Test
    public void deveRetornarListaVaziaSeHistoricoForNulo() {
        Prontuario prontuario = new Prontuario("Enxaqueca");
        // Nota do QA: O metodo getHistorico() é seguro e não quebra (NullPointerException)
        assertNotNull(prontuario.getHistorico());
        assertTrue(prontuario.getHistorico().isEmpty());
    }
}