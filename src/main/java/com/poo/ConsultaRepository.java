package com.poo;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ConsultaRepository {
    private static Database database;
    private static Dao<Consulta, Integer> dao;
    
    private List<Consulta> loadedConsultas;
    private Consulta loadedConsulta; 
    
    public ConsultaRepository(Database database) {
        ConsultaRepository.setDatabase(database);
        this.loadedConsultas = new ArrayList<Consulta>();
    }
    
    public static void setDatabase(Database database) {
        ConsultaRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Consulta.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Consulta.class);
        }
        catch(SQLException e) {
            System.out.println("Erro ao inicializar o banco de Consultas: " + e);
        }            
    }
    
    public Consulta create(Consulta consulta) {
        try {
            int nrows = dao.create(consulta);
            if (nrows == 0) {
                throw new SQLException("Erro: O banco recusou o agendamento da consulta.");
            }
            this.loadedConsulta = consulta;
            this.loadedConsultas.add(consulta);
        } catch (SQLException e) {
            System.out.println("Erro ao salvar consulta: " + e);
        }
        return consulta;
    }    

    public Consulta loadFromId(int id) {
        try {
            this.loadedConsulta = dao.queryForId(id);
            if (this.loadedConsulta != null) {
                this.loadedConsultas.add(this.loadedConsulta);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar consulta por ID: " + e);
        }
        return this.loadedConsulta;
    }    
    
    public List<Consulta> loadAll() {
        try {
            this.loadedConsultas = dao.queryForAll();
            if (this.loadedConsultas.size() != 0) {
                this.loadedConsulta = this.loadedConsultas.get(0);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar todas as consultas: " + e);
        }
        return this.loadedConsultas;
    }

    public void update(Consulta consulta) {
        try {
            dao.update(consulta);
            System.out.println("Consulta reagendada/atualizada com sucesso no banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados da consulta: " + e);
        }
    }

    public void delete(Consulta consulta) {
        try {
            dao.delete(consulta);
            System.out.println("Consulta cancelada e removida do banco de dados.");
        } catch (SQLException e) {
            System.out.println("Erro ao deletar consulta: " + e);
        }
    }      
}
