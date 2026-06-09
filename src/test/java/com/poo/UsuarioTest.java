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
}
