package com.example.app_navigation_tabbed.clases_logica;

import android.widget.Toast;

import com.example.app_navigation_tabbed.clases_logica.subredes.clase_subred;

public class resolucion_subred_2 {

    public resolucion_subred_2() {

    }

    //VARIABLES GLOBALES
    static int prefijo = 0, nsudred = 0;
    static int arreglo_ip[] = new int[6];
    static boolean verificacion_si_se_hallo = false;
    static int total_limite;
    //============ HALLAR EL DIVIDIDO DE SUBRED ============================================

    public static String hallar_subred(int ip_1, int ip_2, int ip_3, int ip_4, int prefijo_subred, String opcion, String formula) {
        String resultado = "";
        try {

            if (opcion.equals("usar un prefijo")) {
                prefijo = prefijo_subred;
                clase_subred hayar_2 = new clase_subred(ip_1, ip_2, ip_3, ip_4, prefijo);
                nsudred = Integer.parseInt(hayar_2.get_nro_subredes()); //NUMERO DE SUBREDES MAXIMOS PARA DIVIDIR LA RED en caso de que ingrese un prefijo
            }

            if (opcion.equals("usar N° de subred a dividir")) {
                prefijo = 0;
                nsudred = prefijo_subred;  //cuando el usuario ingresa una cantidad para ser dividida
            }

//=========================================================================================
            clase_subred hayar = new clase_subred(ip_1, ip_2, ip_3, ip_4, prefijo, nsudred, formula); //enviamos los datos a la clase para que lo halle

            //INTRODUCIENDO LAS IP AL ARREGLO PARA USARLOS DESPUES
            arreglo_ip[0] = ip_1;
            arreglo_ip[1] = ip_2;
            arreglo_ip[2] = ip_3;
            arreglo_ip[3] = ip_4;
            arreglo_ip[4] = prefijo;
            arreglo_ip[5] = nsudred;

            //RECIBIENDO RESULTADOS===================================================================================
            int subredes_disponibles, subred_disponibles;

            if (opcion.equals("usar un prefijo")) {
                subred_disponibles = nsudred + 1;
                subredes_disponibles = nsudred + 2;
            }

            if (opcion.equals("usar N° de subred a dividir")) {
                subredes_disponibles = hayar.get_nro_SubDeRedes_Disponibles() + 2;
                subred_disponibles = hayar.get_nro_SubDeRedes_Disponibles() + 1;
            }


            //INFORMACION DE LA FORMULA
            String formulaU = "", form_salto = "", form_maquinas = "", form_subred = "";
            int resul_formula[] = hayar.get_formula();
            int exponente_formula=hayar.get_expomemte_formula();

            if (formula.equals("2^n-2")) {

                formulaU = "(2" + "^" +exponente_formula  + ")-2" + "=" + resul_formula[1];
            }
            if (formula.equals("2^n")) {
                formulaU = 2 + "^" + exponente_formula + "=" + resul_formula[1];
            }

            form_salto="2^"+resul_formula[2]+"=";
            form_maquinas="(2^"+resul_formula[3]+")-2"+"=";
            form_subred="(2^"+resul_formula[4]+")-2"+"=";

            //==========================================================

            //IMFORMACION PARA EL USUARIO=============================
            if (opcion.equals("usar un prefijo")) {

                resultado = "DIRECION DE RED:   " + hayar.get_direccion_de_red_principal() + "/" + hayar.get_prefijo() + "\n" +
                        "CLASE DE RED:   " + hayar.get_clase_red() + "\n" +
                        "MASCARA MODIFICADA:   " + hayar.get_mascara_modificada() + "\n" +
                        "MASCARA MODIFICADA BINARIA:   " + hayar.get_mascara_modificada_binario() + "\n" +
                        "SUBRED LIMITE:   " + (Integer.parseInt(hayar.get_nro_subredes()) + 1) + "\n" +
                        "NUMERO DE SUDREDES SEGUN LA MASCARA MODIFICADA:   " + form_subred+hayar.get_nro_subredes() + "\n" +
                        "NUMERO DE MAQUINAS SEGUN LA MASCARA MODIFICADA:   " +form_maquinas+ hayar.get_nro_maquinas() + "\n" +
                        "EL SALTO FUE CON UN INTERVALO DE:   " +form_salto+ hayar.get_salto_obteto();

                total_limite = Integer.parseInt(hayar.get_nro_subredes()) + 1;
                verificacion_si_se_hallo = true;
            }

            if (opcion.equals("usar N° de subred a dividir")) {

                resultado = "DIRECION DE RED:   " + hayar.get_direccion_de_red_principal() + "/" + hayar.get_prefijo() + "\n" +
                        "CLASE DE RED:   " + hayar.get_clase_red() + "\n" +
                        "LA SUBRED FUE DIVIDIDA EN:   " + hayar.get_sudredes_divididas() + "\n" +
                        "MASCARA MODIFICADA:   " + hayar.get_mascara_modificada() + "\n" +
                        "MASCARA MODIFICADA BINARIA:   " + hayar.get_mascara_modificada_binario() + "\n" +
                        "SUBRED LIMITE:   " + (Integer.parseInt(hayar.get_nro_subredes()) + 1) + "\n" +
                        "NUMERO DE SUDREDES SEGUN LA MASCARA MODIFICADA:   "+form_subred+ hayar.get_nro_subredes() + "\n" +
                        "NUMERO DE MAQUINAS SEGUN LA MASCARA MODIFICADA:   " +form_maquinas+ hayar.get_nro_maquinas() + "\n" +
                        "FORMULA APLICACADA:   " + formulaU + "\n" +
                        "EL SALTO FUE CON UN INTERVALO DE:   " +form_salto+ hayar.get_salto_obteto();


                total_limite = hayar.get_SubDeRedes_limite();
                verificacion_si_se_hallo = true;
            }


            return resultado;
        } catch (Exception e) {
            return resultado;
        }

    }


    public static String hayar_subred_especifico(int ip_1, int ip_2, int ip_3, int ip_4, int pprefijo_subredDiv, int indice, String opcion_subred, String formula) {
        String resultado = "";
        try {

            if (indice <= total_limite) {

                if (opcion_subred.equals("usar un prefijo")) {
                    prefijo = pprefijo_subredDiv;
                    clase_subred hayar_2 = new clase_subred(ip_1, ip_2, ip_3, ip_4, prefijo);

                    nsudred = Integer.parseInt(hayar_2.get_nro_subredes());
                }
                if (opcion_subred.equals("usar N° de subred a dividir")) {
                    prefijo = 0;
                    nsudred = pprefijo_subredDiv;

                }

                clase_subred hayar = new clase_subred(ip_1, ip_2, ip_3, ip_4, prefijo, nsudred, formula);

                resultado = "ORDEN:   " + indice + "\n" +
                        "ID DE SUDRED:   " + hayar.get_id_de_subred(indice) + "\n" +
                        "GATEWAY:   " + hayar.get_gateway(indice) + "\n" +
                        "BROADCAST:   " + hayar.get_broacast(indice) + "\n" +
                        "LIMITE INFERIOR:   " + hayar.get_limite_inferior(indice) + "\n" +
                        "LIMITE SUPERIOR:   " + hayar.get_limite_superior(indice);


            }
            return resultado;

        } catch (Exception e) {
            return resultado;
        }
    }


    //==========================================================================================================
    public static int getSubred_limite() {

        return total_limite;
    }


}
