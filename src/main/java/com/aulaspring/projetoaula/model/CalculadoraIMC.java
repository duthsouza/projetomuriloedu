package com.aulaspring.projetoaula.model;

public class CalculadoraIMC {

    private double peso;
    private double altura;

    public CalculadoraIMC(double peso, double altura) {
        this.peso = peso;
        this.altura = altura;
    }

    public double getPeso() { return peso; }
    public double getAltura() { return altura; }

    public void setPeso(double peso) { this.peso = peso; }
    public void setAltura(double altura) { this.altura = altura; }

    public double calcularIMC() {
        double resultado = 0;
        if(verificacao())
        resultado = (peso / (altura * altura));
        return resultado;
    }
    

    public boolean verificacao(){
        if(getAltura() > 0 && getPeso() >0){
            return true;
        }else{
            return false;
        }
    }

    public String getClassificacao() {
        double imc = calcularIMC();

        if (imc < 18.5)
            return "Magreza (Abaixo do peso)";
        else if (imc < 24.9)
            return "Normal";
        else if (imc < 29.9)
            return "Sobrepeso";
        else if (imc < 34.9)
            return "Obesidade Grau I";
        else if (imc < 39.9)
            return "Obesidade Grau II";
        else
            return "Obesidade Grau III (Mórbida)";
    }
}
