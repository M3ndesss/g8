package com.poo;

import java.util.ArrayList;

public class ControleHospitalar{
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    
    public ControleHospitalar(ArrayList<Cliente> listaClientes){
        this.listaClientes = listaClientes;
    }
    
    public void cadastrarCliente(Cliente c) {
        this.listaClientes.add(c);
    }
    
    public void verProntuario(String senha, String CPF){
        for(Cliente c : listaClientes){
            if(c.getCPF().equals(CPF)){
                
                Prontuario requisitado = c.getProntuario();
                if(requisitado.verificarSenha(senha) == true){
                    System.out.printf("Solicitação aceita.");
                    requisitado.exibirProntuario();
                    
                }
            }
            break;
        }
    }
    
}