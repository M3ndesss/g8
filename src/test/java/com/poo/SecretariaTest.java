package com.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecretariaTest {

    @Test
    public void deveCriarSecretariaEValidarHeranca() {
        // AJUSTE DO QA: O construtor original não exige ID, o que é um erro crítico de herança.
        Secretaria sec = new Secretaria("Ana Clara Souza", "123.456.789-00", "senha123");

        assertEquals("Ana Clara Souza", sec.getNome());
        assertEquals("123.456.789-00", sec.getCPF());

        // BUG: Comentei a linha de baixo porque falta o metodo getSenha().
        // Se descomentar, o projeto não compila!!!
        // assertEquals("senha123", sec.getSenha(), "BUG: Metodo getSenha() inexistente");
    }

    @Test
    public void devePossuirConstrutorVazioParaO_ORMLite() {
        assertDoesNotThrow(() -> new Secretaria(), "BUG: Falta o construtor vazio exigido pelo banco ORMLite na classe Secretaria.");
    }
}
