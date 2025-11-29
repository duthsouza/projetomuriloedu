package com.aulaspring.projetoaula.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RegistroDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize(){
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserirRegistro(Registro registro){
        String sql = "INSERT INTO registros(nome, cpf, peso, altura, data_registro) VALUES (?, ?, ?, ?, ?)";
        Object[] obj = new Object[5];
        obj[0] = registro.getNome();
        obj[1] = registro.getCpf();
        obj[2] = registro.getPeso();
        obj[3] = registro.getAltura();
        obj[4] = Date.valueOf(registro.getDataRegistro());
        jdbc.update(sql, obj);
    }

    public ArrayList<Registro> listar(){
        String sql = "SELECT * FROM registros ORDER BY id";
        List<Map<String,Object>> mapa = jdbc.queryForList(sql);
        ArrayList<Registro> lista = new ArrayList<>();

        for(Map<String,Object> m : mapa){
            int id = (Integer) m.get("id");
            String nome = (String) m.get("nome");
            String cpf = (String) m.get("cpf");
            double peso = ((Number)m.get("peso")).doubleValue();
            double altura = ((Number)m.get("altura")).doubleValue();
            Date dataSql = (Date) m.get("data_registro");

            Registro r = new Registro(id, nome, cpf, peso, altura, dataSql.toLocalDate());
            lista.add(r);
        }
        return lista;
    }

    public Registro obterRegistro(int id){
        String sql = "SELECT * FROM registros WHERE id=?";
        Map<String,Object> m = jdbc.queryForMap(sql, id);

        String nome = (String) m.get("nome");
        String cpf = (String) m.get("cpf");
        double peso = ((Number)m.get("peso")).doubleValue();
        double altura = ((Number)m.get("altura")).doubleValue();
        Date dataSql = (Date) m.get("data_registro");

        return new Registro(id, nome, cpf, peso, altura, dataSql.toLocalDate());
    }

    public void atualizarRegistro(int id, Registro registro){
        String sql = "UPDATE registros SET nome=?, cpf=?, peso=?, altura=?, data_registro=? WHERE id=?";
        Object[] obj = new Object[6];
        obj[0] = registro.getNome();
        obj[1] = registro.getCpf();
        obj[2] = registro.getPeso();
        obj[3] = registro.getAltura();
        obj[4] = Date.valueOf(registro.getDataRegistro());
        obj[5] = id;
        jdbc.update(sql, obj);
    }

    public void deletarRegistro(int id){
        String sql = "DELETE FROM registros WHERE id=?";
        jdbc.update(sql, id);
    }
}
