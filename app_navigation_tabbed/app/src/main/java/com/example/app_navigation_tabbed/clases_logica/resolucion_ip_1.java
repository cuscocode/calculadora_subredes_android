package com.example.app_navigation_tabbed.clases_logica;

import com.example.app_navigation_tabbed.clases_logica.direcionamiento_ip.clase_direccionamiento_ip;


public class resolucion_ip_1 {
    clase_direccionamiento_ip clase_prefijo = new clase_direccionamiento_ip();

    public resolucion_ip_1() {

    }

    private static String clase_de_red(int ip) {
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

    public static String resolocion_ip(int ip_1, int ip_2, int ip_3, int ip_4, String pprefijo) {
        String Solucion = "";
        try {
            int prefijo = 0;

            if (pprefijo.length() <= 0) {
                if (clase_de_red(ip_1).equals("A")) {
                    prefijo = 8;
                }
                if (clase_de_red(ip_1).equals("B")) {
                    prefijo = 16;
                }
                if (clase_de_red(ip_1).equals("C")) {
                    prefijo = 24;
                }
            } else {
                prefijo = Integer.parseInt(pprefijo);

            }
            clase_direccionamiento_ip clase_direccionamiento = new clase_direccionamiento_ip(ip_1, ip_2, ip_3, ip_4, prefijo);


            //leemos los resultado decimales
            String claseD = clase_direccionamiento.get_clase_de_red();
            String idREdD = clase_direccionamiento.get_id_red_decimal();
            String gatewayD = clase_direccionamiento.get_gateway_decimal();
            String broacastD = clase_direccionamiento.get_broadcats_decimal();
            String mascaraD = clase_direccionamiento.get_mascara_decimal();
            String limiteinfD = clase_direccionamiento.get_limiteInf_decimal();
            String limiteSupD = clase_direccionamiento.get_limiteSup_decimal();
            String ipeD = clase_direccionamiento.get_ipes_decimal();

            //leemos los resultado binarios

            String idREdB = clase_direccionamiento.getId_red_binario();
            String gatewayB = clase_direccionamiento.getGateway_binario();
            String broacastB = clase_direccionamiento.getBroadcast_binario();
            String mascaraB = clase_direccionamiento.getMascara_binario();
            String limiteinfB = clase_direccionamiento.getLimite_inf_binario();
            String limiteSupB = clase_direccionamiento.getLimite_super_binario();

            String ipeB = clase_direccionamiento.get_ipes_binario();

            if (!claseD.equals("D") && !claseD.equals("E")) {  //SI SON DE CLASE A,B,C SOLUCION NORMAL

                Solucion = "DIRECCION IP:   " + ipeD + "\n" +
                        "MASCARA: " + mascaraD + "\n" +
                        "CLASE DE RED:   " + claseD + "\n" +
                        "ID DE RED:   " + idREdD + "\n" +
                        "GATEWAY:   " + gatewayD + "\n" +
                        "BROADCAST:   " + broacastD + "\n" +
                        "LIMITE INFERIOR:   " + limiteinfD + "\n" +
                        "LIMITE SUPERIOR:   " + limiteSupD + "\n" +
                        clase_direccionamiento.get_numero_de_maquinas() + "\n" +
                        clase_direccionamiento.get_numero_de_subredes() + "\n" + "\n" + "\n" +

                        "DIRECCION IP:   " + ipeB + "\n" +
                        "MASCARA: " + mascaraB + "\n" +
                        "ID DE RED:   " + idREdB + "\n" +
                        "GATEWAY:   " + gatewayB + "\n" +
                        "BROADCAST:   " + broacastB + "\n" +
                        "LIMITE INFERIOR:   " + limiteinfB + "\n" +
                        "LIMITE SUPERIOR:   " + limiteSupB;
            } else {

                Solucion = "DIRECCION IP:   " + ipeD + "\n" +
                        "CLASE DE RED:   " + claseD + "\n" + "\n" +
                        "DIRECCION IP:   " + ipeB + "\n"
                ;
            }

            return Solucion;
        } catch (Exception e) {
            //  Toast.makeText(getActivity(), "ocurrio un error ", Toast.LENGTH_LONG).show();
            return Solucion;
        }

    }
}
