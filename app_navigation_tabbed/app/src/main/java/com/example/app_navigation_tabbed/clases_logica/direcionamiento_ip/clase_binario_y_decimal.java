package com.example.app_navigation_tabbed.clases_logica.direcionamiento_ip;

import java.math.BigInteger;

/**
 * @author raul hacho cutipa
 */
public class clase_binario_y_decimal {

    private int arreglo_binario[] = new int[8];
    static int decimal;

    public clase_binario_y_decimal() {
        decimal = 0;
    }

    public int[] get_arreglo_decimal_a_binario(int numero) {

        int nu8 = 0, nu7 = 0, nu6 = 0, nu5 = 0, nu4 = 0, nu3 = 0, nu2 = 0, nu1 = 0;

//-----------------1
        if (numero >= 128) {
            arreglo_binario[0] = 1;
            nu8 = numero - 128;

        } else {
            arreglo_binario[0] = 0;
            nu8 = numero;

        }
        //2
        if (nu8 >= 64) {
            arreglo_binario[1] = 1;
            nu7 = nu8 - 64;

        } else {
            arreglo_binario[1] = 0;
            nu7 = nu8;

        }
        //3
        if (nu7 >= 32) {
            arreglo_binario[2] = 1;
            nu6 = nu7 - 32;

        } else {
            arreglo_binario[2] = 0;
            nu6 = nu7;

        }
        //4
        if (nu6 >= 16) {
            arreglo_binario[3] = 1;
            nu5 = nu6 - 16;

        } else {
            arreglo_binario[3] = 0;
            nu5 = nu6;

        }
        //-5--------
        if (nu5 >= 8) {
            arreglo_binario[4] = 1;
            nu4 = nu5 - 8;

        } else {
            arreglo_binario[4] = 0;
            nu4 = nu5;

        }
        //6------------
        if (nu4 >= 4) {
            arreglo_binario[5] = 1;
            nu3 = nu4 - 4;

        } else {
            arreglo_binario[5] = 0;
            nu3 = nu4;

        }
        //7------
        if (nu3 >= 2) {
            arreglo_binario[6] = 1;
            nu2 = nu3 - 2;

        } else {
            arreglo_binario[6] = 0;
            nu2 = nu3;

        }
        //---8
        if (nu2 >= 1) {
            arreglo_binario[7] = 1;
            nu1 = nu2 - 1;
        } else {
            arreglo_binario[7] = 0;
        }
        return arreglo_binario;
    }

    public static int get_arreglo_binario_a_decimal(int binario[]) {
        decimal = 0;
        //1
        if (binario[0] == 1) {
            decimal = decimal + 128;
        } else {
            decimal = decimal + 0;
        }
        //2
        if (binario[1] == 1) {
            decimal = decimal + 64;
        } else {
            decimal = decimal + 0;
        }
        //3
        if (binario[2] == 1) {
            decimal = decimal + 32;
        } else {
            decimal = decimal + 0;
        }
        //4
        if (binario[3] == 1) {
            decimal = decimal + 16;
        } else {
            decimal = decimal + 0;
        }
        //5
        if (binario[4] == 1) {
            decimal = decimal + 8;
        } else {
            decimal = decimal + 0;
        }
        //6
        if (binario[5] == 1) {
            decimal = decimal + 4;
        } else {
            decimal = decimal + 0;
        }
        //7
        if (binario[6] == 1) {
            decimal = decimal + 2;
        } else {
            decimal = decimal + 0;
        }
        //8
        if (binario[7] == 1) {
            decimal = decimal + 1;
        } else {
            decimal = decimal + 0;
        }
        return decimal;

    }

    public static String get_arreglo_binario32_a_decimalString(int arreglo_32[]) {
        int obteto_1 = 0, obteto_2 = 0, obteto_3 = 0, obteto_4 = 0;
        String resultado = "";
        int arreglo_1[] = new int[8];
        int arreglo_2[] = new int[8];
        int arreglo_3[] = new int[8];
        int arreglo_4[] = new int[8];

        //separamos por obtetos para convertirlos a decimal
        for (int g = 0; g < 8; g++) {
            arreglo_1[g] = arreglo_32[g];
        }
        int d2 = 0;
        for (int z = 8; z < 16; z++) {
            arreglo_2[d2] = arreglo_32[z];
            d2++;
        }
        int d3 = 0;
        for (int i = 16; i < 24; i++) {
            arreglo_3[d3] = arreglo_32[i];
            d3++;
        }
        int d4 = 0;
        for (int k = 24; k < 32; k++) {
            arreglo_4[d4] = arreglo_32[k];
            d4++;
        }

        obteto_1 = clase_binario_y_decimal.get_arreglo_binario_a_decimal(arreglo_1);
        obteto_2 = clase_binario_y_decimal.get_arreglo_binario_a_decimal(arreglo_2);
        obteto_3 = clase_binario_y_decimal.get_arreglo_binario_a_decimal(arreglo_3);
        obteto_4 = clase_binario_y_decimal.get_arreglo_binario_a_decimal(arreglo_4);
        //resultado
        resultado = obteto_1 + "." + obteto_2 + "." + obteto_3 + "." + obteto_4;

        return resultado;

    }

    public static String get_arreglo_binario32_a_binarioString(int arreglo_32[]) {
        String obteto_1 = "", obteto_2 = "", obteto_3 = "", obteto_4 = "";
        String resultado = "";

        //separamos por obtetos para convertirlos a decimal
        for (int g = 0; g < 8; g++) {
            obteto_1 = obteto_1 + arreglo_32[g];
        }

        for (int z = 8; z < 16; z++) {
            obteto_2 = obteto_2 + arreglo_32[z];
        }

        for (int i = 16; i < 24; i++) {
            obteto_3 = obteto_3 + arreglo_32[i];
        }

        for (int k = 24; k < 32; k++) {
            obteto_4 = obteto_4 + arreglo_32[k];
        }


        //resultado
        resultado = obteto_1 + "." + obteto_2 + "." + obteto_3 + "." + obteto_4;

        return resultado;

    }

    public static int[] get_decimal_A_Binario_numeros(int numero, int tamaño) {
        int arreglo_binario_numero[] = new int[tamaño];

        String deci_Bina = "";
        if (numero > 0) {

            while (numero > 0) {
                if (numero % 2 == 0) {
                    deci_Bina = "0" + deci_Bina;

                } else {
                    deci_Bina = "1" + deci_Bina;
                }
                numero = (int) numero / 2;
            }
        } else if (numero == 0) {
            deci_Bina = "0";
        }

        //introducir al arreglo para enviar
        for (int i = 0; i < tamaño; i++) {
            arreglo_binario_numero[i] = 0;
        }

        int m = deci_Bina.length();
        int n = m - 1;
        for (int t = tamaño - 1; t >= tamaño - deci_Bina.length(); t--) {

            arreglo_binario_numero[t] = Integer.parseInt(deci_Bina.substring(n, m));
            m--;
            n--;
        }

        return arreglo_binario_numero;
    }

    public static Double get_binario_a_decimal(Double binario) {

        Double digito;
        int exponente = 0;
        Double bina_deci = 0.0;
        while (binario > 0) {
            digito = binario % 10;
            bina_deci = bina_deci + digito * (int) Math.pow(2, exponente);
            binario /= 10;
            exponente++;
        }
        return bina_deci;
    }

    public static long convertirBinarioADecimalManual(String binario) {
        // A este número le vamos a sumar cada valor binario
        long decimal = 0;
        int posicion = 0;
        // Recorrer la cadena...
        for (int x = binario.length() - 1; x >= 0; x--) {
            // Saber si es 1 o 0; primero asumimos que es 1 y abajo comprobamos
            short digito = 1;
            if (binario.charAt(x) == '0') {
                digito = 0;
            }

            /*
          Se multiplica el dígito por 2 elevado a la potencia
          según la posición; comenzando en 0, luego 1 y así
          sucesivamente
             */
            double multiplicador = Math.pow(2, posicion);
            decimal += digito * multiplicador;
            posicion++;
        }
        return decimal;
    }
}