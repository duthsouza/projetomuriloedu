package com.aulaspring.projetoaula.model;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroService {

    @Autowired
    RegistroDAO registroDAO;

    public void inserirRegistro(Registro r) {
        registroDAO.inserirRegistro(r);
    }

    public ArrayList<Registro> listarRegistros() {
        return registroDAO.listar();
    }

    public Registro obterRegistro(int id) {
        return registroDAO.obterRegistro(id);
    }

    public void atualizarRegistro(int id, Registro registro) {
        registroDAO.atualizarRegistro(id, registro);
    }

    public void deletarRegistro(int id) {
        registroDAO.deletarRegistro(id);
    }
}
