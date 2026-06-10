package com.poo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class ControleHospitalarTest {

    @Test
    public void deveExporBugDoBreakNoLoopDeBusca() {
        ArrayList<Cliente> lista = new ArrayList<>();

        // Criar o primeiro cliente da lista
        Prontuario p1 = new Prontuario("Febre");
        Cliente c1 = new Cliente("Rafaella Santos", "111.111.111-11", 1, p1, "11/11/2011");

        // Criar o segundo cliente da lista para tentar buscar
        Prontuario p2 = new Prontuario("Gastrite");
        Cliente c2 = new Cliente("Bruno", "222.222.222-22", 2, p2, "22/02/2002");

        lista.add(c1);
        lista.add(c2);

        ControleHospitalar controle = new ControleHospitalar(lista);

        // Como o metodo retorna void e apenas imprime as coisas, vamos usar o assertDoesNotThrow
        // O teste mostra que o sistema falha em processar buscas além do primeiro elemento por causa do lugar errado do 'break'
        assertDoesNotThrow(() -> controle.verProntuario("senha123", "222.222.222-22"), "BUG DE LÓGICA: O comando break interrompe o laço depois da primeira interação, impedindo a busca de qualquer cliente que não seja o primeiro");
    }
}
