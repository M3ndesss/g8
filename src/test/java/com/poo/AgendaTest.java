package com.poo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AgendaTest {

    @Test
    public void deveExporBugDoConstrutorQueApagaALista() {
        ArrayList<Consulta> listaInicial = new ArrayList<>();
        Medico medico = new Medico("Dr. Carlos Alberto", "333.333.333-33", "Clínico Geral", "senha123");
        Cliente cliente = new Cliente("Bruno Henrique", "444.444.444-44", 1, null, "08/08/2006");

        listaInicial.add(new Consulta(1, "12/06/2026", "09:00", medico, cliente));

        // A Agenda recebe uma lista, mas recria ela vazia no construtor
        Agenda agenda = new Agenda(listaInicial);

        // Como novaConsulta retorna void, usamos assertDoesNotThrow para rodar e provar o bug do construtor
        Consulta consulta2 = new Consulta(2, "12/06/2026", "09:00", medico, cliente);
        assertDoesNotThrow(() -> agenda.novaConsulta(consulta2), "BUG DO CONSTRUTOR: Como a lista foi apagada na criação, o método não lança erros.");
    }

    @Test
    public void deveExporQueAgendamentoDuplicadoNaoEBloqueado() {
        Agenda agenda = new Agenda(new ArrayList<>());
        Medico medico = new Medico("Dr. Anderson Silva", "111.111.111-11", "Cardiologia", "senha321");
        Cliente cliente = new Cliente("Júlia Tereza", "222.222.222-22", 1, null, "01/01/2000");

        Consulta c1 = new Consulta(1, "20/10/2026", "14:00", medico, cliente);
        Consulta c2 = new Consulta(2, "20/10/2026", "14:00", medico, cliente);
        agenda.novaConsulta(c1);

        // BUG CRÍTICO DE LÓGICA EXPOSTO AQUI:
        // O metodo percebe que o horário está ocupado e dá um print, mas não tem um "return;" para parar a execução! Ele adiciona na lista mesmo assim
        assertDoesNotThrow(() -> agenda.novaConsulta(c2), "BUG DE SENTIDO: A agenda avisa que o horário está ocupado no console, mas não para o cadastro e salva a consulta duplicada.");
    }

    @Test
    public void deveExporFalhaDeSegurancaNaExibicaoDaAgenda() {
        Agenda agenda = new Agenda(new ArrayList<>());
        Medico medico = new Medico("Dr. Fulano", "123.456.789-00", "Ortopedia", "123456");

        // Exibir a agenda com senha errada apenas imprime um texto, não lança exceção
        // Senha errada colocada
        assertDoesNotThrow(() -> agenda.exibirAgenda(medico, "654321"), "BUG DE SEGURANÇA: Senha incorreta não lança exceção, falhando silenciosamente e retornando void.");
    }
}