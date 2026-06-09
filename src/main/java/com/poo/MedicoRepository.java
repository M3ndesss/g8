package com.poo;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class MedicoRepository {
    private static Database database;
    private static Dao<Medico, Integer> dao;
    
    private List<Medico> loadedMedicos;
    private Medico loadedMedico; 
    
    public MedicoRepository(Database database) {
        MedicoRepository.setDatabase(database);
        this.loadedMedicos = new ArrayList<Medico>();
    }
    
    public static void setDatabase(Database database) {
        MedicoRepository.database = database;
        try {
            dao = DaoManager.createDao(database.getConnection(), Medico.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Medico.class);
        }
        catch(SQLException e) {
            System.out.println("Erro ao inicializar o DAO de Medico: " + e);
        }            
    }
    
    public Medico create(Medico medico) {
        try {
            int nrows = dao.create(medico);
            if (nrows == 0) {
                throw new SQLException("Erro: O médico não foi salvo.");
            }
            this.loadedMedico = medico;
            this.loadedMedicos.add(medico);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return medico;
    }    

    public Medico loadFromId(int id) {
        try {
            this.loadedMedico = dao.queryForId(id);
            if (this.loadedMedico != null) {
                this.loadedMedicos.add(this.loadedMedico);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedMedico;
    }    
    
    public List<Medico> loadAll() {
        try {
            this.loadedMedicos = dao.queryForAll();
            if (this.loadedMedicos.size() != 0) {
                this.loadedMedico = this.loadedMedicos.get(0);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return this.loadedMedicos;
    }
    
    public void update(Medico medico) {
        try {
            dao.update(medico);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Medico medico) {
        try {
            dao.delete(medico);
        } catch (SQLException e) {
            System.out.println(e);
        }
    } 
}
