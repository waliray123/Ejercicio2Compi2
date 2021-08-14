/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Objetos.Instruccion;
import Objetos.Procedimiento;
import Objetos.Programa;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author user-ubunto
 */
public class ControlDot {
    private Programa programa;
    private String strDot;
    private int numNode;

    public ControlDot(Programa programa) {
        this.programa = programa;
        this.numNode = 0;
        strDot = "";
    }
    
    public void generarStrDot(){
        strDot = "digraph G { \n";
        strDot += "node" + 0 + " [label = \"" + this.programa.getNombre() + "\"];\n";
        List<Instruccion> instrucciones = this.programa.getInstrucciones();
        for (Instruccion instruccion : instrucciones) {
            if (instruccion.isEsAsgFun()) {
                insertarFuncion(instruccion.getFuncion(),0);
            }
        }
        strDot += "}\n";        
    }
    
    private void insertarFuncion(String nombre,int numPadre){
        this.numNode++;
        int numNodo = this.numNode;
        Procedimiento procedimiento = buscarFuncion(nombre);
        strDot += "node" + numNodo + " [label = \"" + procedimiento.getNombre() + "\"];\n";
        strDot += "node" + numPadre + " -> " + "node" + numNodo + ";\n";
        List<Instruccion> instrucciones = procedimiento.getInstrucciones();
        for (Instruccion instruccion : instrucciones) {
            if (instruccion.isEsAsgFun()) {
                insertarFuncion(instruccion.getFuncion(),numNodo);
            }
        }
    }
    
    private Procedimiento buscarFuncion(String nombre){
        List<Procedimiento> procedimientos = this.programa.getProcedimientos();
        for (Procedimiento procedimiento : procedimientos) {
            if (procedimiento.getNombre().equals(nombre)) {
                return procedimiento;
            }
        }
        return null;
    }
    
    public void escribirGraphviz() {
        try {
            File file = new File("src/main/resources/arbol.dot");
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(this.strDot);
            bw.close();
        } catch (Exception e) {
            System.out.println("Error al escribir");
            System.out.println(e);
        }
    }

    public void crearPngArbol() {
        try {
            String cmd = "dot -Tpng src/main/resources/arbol.dot -o src/main/resources/arbol.png"; 
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
