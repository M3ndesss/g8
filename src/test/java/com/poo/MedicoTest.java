package com.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedicoTest {

    @Test
    public void deveCriarMedicoCompletoComHeranca() {
        Medico medico = new Medico("Dr. Carlos Alves", "123.456.789-00", "Neurologia", "senha123");

        assertEquals("Dr. Carlos Alves", medico.getNome());
        assertEquals("123.456.789-00", medico.getCPF());
        assertEquals("Neurologia", medico.getEspecialidade());
        assertEquals("senha123", medico.getSenha());

        // BUG: O backend também chumbou o ID como 0 no construtor do Médico!!!
        assertEquals(0, medico.getId(), "BUG: O construtor de Medico força o ID a ser 0, ignorando a geração do banco de dados.");
    }

    @Test
    public void deveCriarMedicoComConstrutorVazio() {
        assertDoesNotThrow(() -> new Medico());
    }

    @Test
    public void deveExporFaltaDeValidacaoParaCamposVazios() {
        Medico medico = new Medico("Dr. Fulano", "111.111.111-11", "", "");
        // BUG: O sistema permite criar um médico sem especialidade e sem senha, por exemplo.
        assertEquals("", medico.getEspecialidade(), "BUG DE SENTIDO: Aceita especialidade vazia.");
        assertEquals("", medico.getSenha(), "BUG DE SENTIDO: Aceita senha vazia.");
    }
}
