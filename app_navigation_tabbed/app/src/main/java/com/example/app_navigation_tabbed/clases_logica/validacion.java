package com.example.app_navigation_tabbed.clases_logica;


public class validacion {

    public validacion() {

    }

    ////// METODODOS DE VALIDACION

    public static String clase_de_red(int ip) {
        String claseRed = "";
        if (ip >= 1 && ip <= 127) { //clase A
            claseRed = "A";
        }
        if (ip >= 128 && ip <= 191) {
            claseRed = "B";
        }
        if (ip >= 192 && ip <= 223) {
            claseRed = "C";
        }
        return claseRed;
    }

    // SI RETORNA FALSE LA VALIDACION NO ESTA COMPLETA// DEBE ENVIARSE EL MENSAJE
    public static String[] validar_prefijo(int ip_1, int prefijo) {
        String verifica_prefijo[] = {"false", ""};


        try {
            if (clase_de_red(ip_1).equals("A")) { //clase A
                if (prefijo > 8 && prefijo <= 32) {
                    verifica_prefijo[0] = "true";
                } else {
                    verifica_prefijo[1] = "el prefijo para una red de clase A debe ser mayor a 8 y menor  o igual a 32";
                }
            }


            if (clase_de_red(ip_1).equals("B")) {
                if (prefijo > 16 && prefijo <= 32) {
                    verifica_prefijo[0] = "true";
                } else {
                    verifica_prefijo[1] = "el prefijo para una red de clase B debe ser mayor a 16 y menor o igual a 32";
                }
            }


            if (clase_de_red(ip_1).equals("C")) {
                if (prefijo > 24 && prefijo <= 32) {
                    verifica_prefijo[0] = "true";
                } else {
                    verifica_prefijo[1] = "el prefijo para una red de clase C debe ser mayor a 24 y menor o igual a 32";
                }

            }

            return verifica_prefijo;
        } catch (Exception e) {
            verifica_prefijo[1] = "error al validar prefijo";
            return verifica_prefijo;
        }
    }


    public static String[] validar_subred_dividir(int ip_1, int subred_dividir) {
        String verifificar[] = {"false", ""};

        try {
            //para clase A
            if (clase_de_red(ip_1).equals("A")) {
                if (subred_dividir <= 16777216) {
                    verifificar[0] = "true";
                } else {
                    verifificar[1] = "la red de clase A solo puede dividirse asta 16 777 216 sudredes";
                }
            }

            //para clase b
            if (clase_de_red(ip_1).equals("B")) {
                if (subred_dividir <= 65536) { //16384
                    verifificar[0] = "true";
                } else {
                    verifificar[1] = "la red de clase B solo puede dividirse asta  65 536 sudredes ";
                }
            }

            //par clse C
            if (clase_de_red(ip_1).equals("C")) {
                if (subred_dividir <= 256) { //254
                    verifificar[0] = "true";
                } else {
                    verifificar[1] = "la red de clase C solo puede dividirse asta 256 sudredes";
                }
            }
            return verifificar;
        } catch (Exception e) {
            verifificar[1] = "error al validar subred ";
            return verifificar;
        }
    }


}
