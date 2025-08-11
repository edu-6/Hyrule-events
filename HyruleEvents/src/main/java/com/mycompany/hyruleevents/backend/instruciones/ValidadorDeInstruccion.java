/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hyruleevents.backend.instruciones;

import com.mycompany.hyruleevents.backend.instruciones.enums.Dependencia;
import com.mycompany.hyruleevents.backend.instruciones.enums.Parametro;

/**
 *
 * @author edu
 */
public abstract class ValidadorDeInstruccion {

    protected boolean todoValido;
    protected String logs;
    protected int primerCaracterRelevante;
    protected int cantidadDeParametros;
    protected Parametro[] tiposDeParametro;
    protected Dependencia[] tipoDeDependencia; //indica si un parametro es obligatorio o no 
    protected String nombreDeInstruccion;
    protected ValidadorDeFecha validadorFecha;
    

    public ValidadorDeInstruccion(int primerCaracterRelevante, int cantidadDeParametros, String nombreDeInstruccion) {

        this.primerCaracterRelevante = primerCaracterRelevante;
        this.cantidadDeParametros = cantidadDeParametros;
        this.nombreDeInstruccion = nombreDeInstruccion;

        this.logs = "";
        this.validadorFecha = new ValidadorDeFecha();

        // se inician los tipo de parametros enum
        this.inicializarTiposDeParametro();
        this.indicarParametrosObligatorios();
    }

    /**
     * Crea un array con los indices de parametros que no se pueden dejar en
     * blanco
     */
    protected abstract void indicarParametrosObligatorios();

    protected abstract void inicializarTiposDeParametro();

    /**
     * Verifica parametros como tipo de participante,evento etc
     * @param parametros
     * @return 
     */
    protected abstract boolean parametrosEspecialesValidos(String[] parametros);

    public String [] verificarInstruccion(String instruccion) {
        String[] parametros;

        if (!sintaxisDeInstruccionCorrecta(instruccion)) {
            logs = ">>> Error de sintaxis! " + nombreDeInstruccion + "(parametros...); \n"
                    + "Introduzca el nombre de instrucicon seguido de los parentesis y terminando en  ';'";
            return null;
        }
        if (!parametrosCompletos(instruccion)) {
            logs = ">>> Los parametros para " + nombreDeInstruccion + " no están completos \n Se esperaban "
                    + cantidadDeParametros + " parametros";
            return null;
        }

        parametros = separarParametros(instruccion);

        if (!sintaxisDeParametrosValida(parametros)) {
            logs = ">>> Parametros mal introducidos\n" + " Todos los datos llevan comillas, exepto los numericos"
                    + "(sin comillas y mayores a 0)";
            return null;
        }

        parametros = limpiarParametrosString(parametros); // quitar comillas a los parametros String
        if (hayCamposVacios(parametros)) {
            logs = ">>> Hay campos vacios que son obligatorios para " + this.nombreDeInstruccion;
            return null;
        }

        if (!parametrosEspecialesValidos(parametros)) {
            return null;
        }

        return parametros; // devuelve los parametros limpios
    }

    protected boolean parametrosCompletos(String instruccion) {
        int cantidadComas = 0;
        char coma = ',';
        for (int i = primerCaracterRelevante; i < instruccion.length(); i++) {
            if (instruccion.charAt(i) == coma) {
                cantidadComas++;
            }
        }
        return cantidadComas + 1 == cantidadDeParametros;
    }

    /**
     * Verifica que un String se pueda convertir a int y ese int > 0
     *
     * @param numeroString
     * @return
     */
    protected boolean esEnteroValido(String numeroString) {
        try {
            int num = Integer.valueOf(numeroString);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected boolean esDecimalValido(String numeroString) {
        try {
            double decimal = Double.parseDouble(numeroString);
            return decimal > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    protected String[] separarParametros(String cadena) {
        String[] parametros = new String[cantidadDeParametros];
        int indice = 0;
        for (int i = primerCaracterRelevante; i < cadena.length() - 2; i++) {
            char caracter = cadena.charAt(i);
            if (caracter != ',') {
                parametros[indice] += caracter;
            } else {
                indice++;
            }
        }
        return parametros;
    }

    /**
     * Verifica que los parametros sean numericos o String, comparando si se
     * puede ocmvertir el texto a numero o si el String está rodeado de comillas
     *
     * @param parametros
     * @return
     */
    protected boolean sintaxisDeParametrosValida(String[] parametros) {
        boolean valido = true;
        for (int i = 0; i < parametros.length; i++) {
            switch (tiposDeParametro[i]) {
                case Parametro.STRING:
                    if (!parametroStringValido(parametros[i])) { // si el parametro no tiene "" comillas
                        valido = false;
                    }
                    break;
                case Parametro.ENTERO:
                    if (!esEnteroValido(parametros[i])) {
                        valido = false;
                    }
                    break;
                case Parametro.DECIMAL:
                    if (!esDecimalValido(parametros[i])) {
                        valido = false;
                    }
                    break;
            }
        }
        return valido;
    }

    /**
     * Verifica si hay parametros vacios y que son obligatorios
     *
     * @param parametros
     * @return
     */
    protected boolean hayCamposVacios(String[] parametros) {
        boolean vacios = false;
        for (int i = 0; i < parametros.length; i++) {
            if (tipoDeDependencia[i].equals(Dependencia.OBLIGATORIO) && parametros[i].isBlank()) {  // si el parametro es obligatorio y está en blanco
                return true;
            }
        }
        return vacios;
    }

    /**
     * verifica si un parametro String está rodeado de comillas
     *
     * @param parametro
     * @return
     */
    protected boolean parametroStringValido(String parametro) {
        char primerCaracter = parametro.charAt(0);
        char ultimoCaracter = parametro.charAt(parametro.length() - 1);
        return (primerCaracter == '"' && ultimoCaracter == '"');
    }

    protected String[] limpiarParametrosString(String[] parametros) {
        for (int i = 0; i < parametros.length; i++) {
            if (tiposDeParametro[i].equals(Parametro.STRING)) {
                parametros[i] = quitarComillas(parametros[i]);
            }
        }
        return parametros;
    }

    protected String quitarComillas(String parametro) {
        String limpio = "";
        for (int i = 1; i < parametro.length() - 1; i++) {
            limpio += parametro.charAt(i);
        }
        return limpio;
    }

    protected boolean sintaxisDeInstruccionCorrecta(String instruccion) {
        int indiceParentesisAbierto = this.primerCaracterRelevante - 1;
        boolean parentesisCompletos = (instruccion.charAt(indiceParentesisAbierto) == '(' && instruccion.charAt(instruccion.length() - 2) == ',');
        boolean hayPuntoYComa = instruccion.charAt(instruccion.length() - 1) == ';';
        return parentesisCompletos && hayPuntoYComa;
    }

    public String getLogs() {
        return logs;
    }

}
