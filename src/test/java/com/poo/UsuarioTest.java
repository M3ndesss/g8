package com.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void deveCriarUsuarioComDadosValidos() {
        Usuario usuario = new Usuario("João da Silva", "123.456.789-00", 1);
        assertEquals("João da Silva", usuario.getNome());
        assertEquals("123.456.789-00", usuario.getCPF());
        assertEquals(1, usuario.getId());
    }

    @Test
    public void devePermitirUsuarioComNomeNuloECpfVazio() {
        Usuario usuario = new Usuario(null, "", 2);
        assertNull(usuario.getNome(), "Nota do QA: O sistema aceita nome nulo.");
        assertEquals("", usuario.getCPF(), "Nota do QA: O sistema aceita CPF vazio.");
    }

    @Test
    public void deveExporFaltaDeValidacaoParaIdNegativo() {
        Usuario usuario = new Usuario("Paciente Fulano", "000.000.000-00", -99);
        // BUG: O sistema aceita o ID negativo e guarda ele, em vez de dar um erro.
        assertEquals(-99, usuario.getId(), "BUG DE SENTIDO: O construtor aceita IDs negativos.");
    }

    @Test
    public void deveExporFaltaDeValidacaoDeFormatoCpf() {
        Usuario usuario = new Usuario("João", "123", 1);
        // BUG: O sistema aceita CPFs fora do padrão de 14 caracteres (XXX.XXX.XXX-XX)
        assertEquals("123", usuario.getCPF(), "BUG DE NEGÓCIO: O sistema não valida o formato nem o tamanho do CPF.");
    }
}
