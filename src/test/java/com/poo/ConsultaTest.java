package com.poo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsultaTest {

    @Test
    public void deveCriarConsultaComDadosValidos() {
        Medico medico = new Medico("Dr. João Carlos", "111.111.111-11", "Cardiologia", "senha123");
        Cliente cliente = new Cliente("Ana Flávia", "222.222.222-22", 1, null, "01/01/2000");
        Consulta consulta = new Consulta(10, "23/08/2026", "14:30", medico, cliente);

        assertEquals("23/08/2026", consulta.getData());
        assertEquals("14:30", consulta.getHorario());

        // BUG DO BACKEND: Ignora o 10 e força 0. Esperamos 0 para o teste passar hoje.
        assertEquals(0, consulta.getId(), "BUG ORMLITE: O construtor ignora o parâmetro ID e chumba 0.");
    }

    @Test
    public void deveExporAgendamentoFantasmaEHorariosInvalidos() {
        // Testando o Caminho Triste: Consulta sem médico, sem cliente, horário impossível e ID NEGATIVO
        Consulta consulta = new Consulta(-5, "99/99/9999", "25:61", null, null);

        // BUGS (SENTIDO):
        assertNull(consulta.getMedico(), "BUG CRÍTICO: O sistema permite agendar consulta sem Médico.");
        assertNull(consulta.getCliente(), "BUG CRÍTICO: O sistema permite agendar consulta sem Cliente.");
        assertEquals("25:61", consulta.getHorario(), "BUG DE SENTIDO: Aceita horários impossíveis.");

        // BUG (VALIDAÇÃO): Mesmo sendo ignorado e transformado em 0 depois, o construtor aceita receber número negativo
        assertDoesNotThrow(() -> new Consulta(-99, "10/10/2026", "10:00", null, null), "BUG DE VALIDAÇÃO: A classe Consulta não bloqueia a entrada de IDs negativos.");
    }

    @Test
    public void deveCriarConsultaComConstrutorVazio() {
        assertDoesNotThrow(() -> new Consulta());
    }
}
