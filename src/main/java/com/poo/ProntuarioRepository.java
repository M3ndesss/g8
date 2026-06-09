package com.poo;

import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.table.TableUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ProntuarioRepository {
    private static Database database;
    private static Dao<Prontuario, Integer> prontuarioDao;
    private static Dao<ProntuarioMedico, Integer> ponteDao; 
    private List<Prontuario> loadedProntuarios;
    
    public ProntuarioRepository(Database database) {
        ProntuarioRepository.setDatabase(database);
        this.loadedProntuarios = new ArrayList<Prontuario>();
    }
    
    public static void setDatabase(Database database) {
        ProntuarioRepository.database = database;
        try {
            prontuarioDao = DaoManager.createDao(database.getConnection(), Prontuario.class);
            TableUtils.createTableIfNotExists(database.getConnection(), Prontuario.class);
            
            ponteDao = DaoManager.createDao(database.getConnection(), ProntuarioMedico.class);
            TableUtils.createTableIfNotExists(database.getConnection(), ProntuarioMedico.class);
        }
        catch(SQLException e) {
            System.out.println("Erro ao inicializar o repositório de Prontuário: " + e);
        }            
    }
    
    public Prontuario create(Prontuario prontuario) {
        try {
            prontuarioDao.create(prontuario);
            this.loadedProntuarios.add(prontuario);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return prontuario;
    }    

    // Método para salvar um médico no histórico do prontuário no banco de dados
    public void adicionarMedicoAoHistorico(Prontuario prontuario, Medico medico) {
        try {
            ProntuarioMedico ligacao = new ProntuarioMedico(prontuario, medico);
            ponteDao.create(ligacao);
            System.out.println("Médico adicionado ao histórico do prontuário no banco!");
        } catch (SQLException e) {
            System.out.println("Erro ao vincular médico ao prontuário: " + e);
        }
    }

    public Prontuario loadFromId(int id) {
        try {
            return prontuarioDao.queryForId(id);
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public void update(Prontuario prontuario) {
        try {
            prontuarioDao.update(prontuario);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(Prontuario prontuario) {
        try {
            prontuarioDao.delete(prontuario);
        } catch (SQLException e) {
            System.out.println(e);
        }
    } 
}
