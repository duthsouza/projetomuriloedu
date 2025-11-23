package com.aulaspring.projetoaula.model;

import java.time.LocalDate;

public class Registro {

    private int id;
    private String nome;
    private String cpf;
    private double peso;
    private double altura;
    private LocalDate dataRegistro;

    public Registro() {
        this.dataRegistro = LocalDate.now();
    }

    public Registro(String nome, String cpf, double peso, double altura) {
        this.nome = nome;
        this.cpf = cpf;
        this.peso = peso;
        this.altura = altura;
        this.dataRegistro = LocalDate.now();
    }

    public Registro(int id, String nome, String cpf, double peso, double altura, LocalDate dataRegistro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.peso = peso;
        this.altura = altura;
        this.dataRegistro = dataRegistro;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public double getAltura() { return altura; }
    public void setAltura(double altura) { this.altura = altura; }

    public LocalDate getDataRegistro() { return dataRegistro; }
    public void setDataRegistro(LocalDate dataRegistro) { this.dataRegistro = dataRegistro; }

    public double getIMC() {
        return new CalculadoraIMC(peso, altura).calcularIMC();
    }

    public String getClassificacao() {
        return new CalculadoraIMC(peso, altura).getClassificacao();
    }
}
