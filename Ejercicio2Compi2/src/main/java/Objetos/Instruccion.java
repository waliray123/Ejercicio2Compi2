/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class Instruccion {
    private String tipo;
    private String variable;
    private boolean esAsgFun;
    private String funcion;
    private List<String> parametros;
    private List<String> valores;

    public Instruccion() {
        esAsgFun = false;
        this.valores = new ArrayList<>();
        this.parametros = new ArrayList<>();
    }    
    
    public List<String> getValores() {
        return valores;
    }

    public void setValores(List<String> valores) {
        this.valores = valores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public boolean isEsAsgFun() {
        return esAsgFun;
    }

    public void setEsAsgFun(boolean esAsgFun) {
        this.esAsgFun = esAsgFun;
    }

    public String getFuncion() {
        return funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public List<String> getParametros() {
        return parametros;
    }

    public void setParametros(List<String> parametros) {
        this.parametros = parametros;
    }
    
    public void insertarParametro(String parametro){
        this.parametros.add(parametro);
    }
    
    public void insertarValor(String valor){
        this.valores.add(valor);
    }
    
}
