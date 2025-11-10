package com.aulaspring.projetoaula;

import java.time.LocalDate;

public class Registro {
    private String nome;
    private String cpf;
    private double peso;
    private double altura;
    private LocalDate dataRegistro;

    public Registro() {
        this.dataRegistro = LocalDate.now();
    }

    public Registro(String nome, String cpf, double peso, double altura){
        this.nome = nome;
        this.cpf = cpf;
        this.altura = altura;
        this.peso = peso;
        this.dataRegistro = LocalDate.now();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

        public LocalDate getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDate dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public double getIMC() {
        CalculadoraIMC calc = new CalculadoraIMC(this.peso, this.altura);
        return calc.calcularIMC();
    }

    public String getClassificacao() {
        CalculadoraIMC calc = new CalculadoraIMC(this.peso, this.altura);
        return calc.getClassificacao();
    }

    

}
