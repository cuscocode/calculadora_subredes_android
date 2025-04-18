package com.example.app_navigation_tabbed;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.app_navigation_tabbed.clases_logica.resolucion_subred_2;
import com.example.app_navigation_tabbed.clases_logica.validacion;
import com.example.app_navigation_tabbed.clases_logica.resolucion_subred_2.*;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.conn.ssl.StrictHostnameVerifier;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_tab_calc_subred extends Fragment {
    //leendo componetes
    private Spinner spiner, spiner2, spinner3;
    private EditText txt_ip1, txt_ip2, txt_ip3, txt_ip4, txt_prefijo;
    private Button btn_hallar, btn_hallar_2;
    private ImageButton btn_limpiar;
    private TextView tv_ressultado_1, tv_ressultado_2;

    private boolean verficar_se_hayo = false;

    int datos[] = new int[5];

    public fragment_tab_calc_subred() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View vista = inflater.inflate(R.layout.fragment_tab_calc_subred, container, false);

        //agregando al spinner===========================
        spiner = (Spinner) vista.findViewById(R.id.spinner);
        spiner2 = (Spinner) vista.findViewById(R.id.spinner_2);
        spinner3 = (Spinner) vista.findViewById(R.id.spinner_3);
        txt_ip1 = (EditText) vista.findViewById(R.id.txt_ip12);
        txt_ip2 = (EditText) vista.findViewById(R.id.txt_ip22);
        txt_ip3 = (EditText) vista.findViewById(R.id.txt_ip32);
        txt_ip4 = (EditText) vista.findViewById(R.id.txt_ip42);
        txt_prefijo = (EditText) vista.findViewById(R.id.txt_prefijo_2);

        tv_ressultado_1 = (TextView) vista.findViewById(R.id.tv_resultado12);
        tv_ressultado_2 = (TextView) vista.findViewById(R.id.tv_resultado22);
        btn_hallar = (Button) vista.findViewById(R.id.btn_C_12);
        btn_hallar_2 = (Button) vista.findViewById(R.id.btn_C22);
        btn_limpiar = (ImageButton) vista.findViewById(R.id.btn_limpiar_2);

        //===================================================0
        poner_opciones_Spinner(vista);
        validar_cajas(vista);
        agregar_iconos_Spinner(vista);
        spiner2.setVisibility(View.INVISIBLE);
        btn_hallar_2.setVisibility(View.INVISIBLE);

        btn_hallar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hallar_dividir_subred(vista);
            }
        });
        linpiar_componentes(vista);


        btn_hallar_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hallar_sudred_indice(vista);
            }
        });


        return vista;

    }

    List<Item> items;

    private void agregar_iconos_Spinner(View view) {
        items = new ArrayList<Item>() {{
            add(new Item(R.drawable.ic_2n_formulaa, ""));
            add(new Item(R.drawable.ic_2n_2_formula, ""));
        }};
        ItemAdapter adapter = new ItemAdapter(items, getContext());
        spinner3.setAdapter(adapter);
    }

    private void poner_opciones_Spinner(View view) {
        String[] opciones = {"usar un prefijo", "usar N° de subred a dividir"};
        ArrayAdapter<String> Adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_opciones, opciones);
        spiner.setAdapter(Adapter);

        String[] opciones_2 = {"hallar las 100 primeras subredes", "hallar las 50 primeras y 50 ultimas subredes", "hallar una subred especifica", "el indice es menor a 100 hallar todas las subredes"};
        ArrayAdapter<String> Adapter_2 = new ArrayAdapter<String>(getContext(), R.layout.spinner_item_opciones, opciones_2);
        spiner2.setAdapter(Adapter_2);

    }


    private void validar_cajas(final View view) {
        txt_ip1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//antes de que el texto cambia
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //en el momento del cambio
                try {
                    if (Integer.parseInt(s.toString()) > 223 || Integer.parseInt(s.toString()) <= 0) {
                        txt_ip1.setText("");
                        enviar_mensaje("solo se aceptan redes de clase A,B y C", view);
                    } else {
                        if (s.length() >= 3) {
                            txt_ip2.requestFocus();
                        }
                    }
                } catch (Exception e) {
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
//despues de que el texto cambia
            }
        });
        txt_ip2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//antes de que el texto cambia
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (s.toString().equals("00") || s.toString().equals("000")) {
                        txt_ip2.setText("0");
                    } else if (s.length() > 1 && (s.toString()).substring(0, 1).equals("0")) {
                        txt_ip2.setText(s.toString().substring(1, s.length()));
                    }

                    if (Integer.parseInt(s.toString()) > 255) {
                        txt_ip2.setText("");
                    } else {
                        if (s.length() >= 3) {
                            txt_ip3.requestFocus();
                        }
                    }
                    txt_ip2.setText(Integer.parseInt(txt_ip2.getText().toString()));

                } catch (Exception e) {

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//despues de que el texto cambia
            }
        });
        txt_ip3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//antes de que el texto cambia
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (s.toString().equals("00") || s.toString().equals("000")) {
                        txt_ip3.setText("0");
                    } else if (s.length() > 1 && (s.toString()).substring(0, 1).equals("0")) {
                        txt_ip3.setText(s.toString().substring(1, s.length()));
                    }

                    if (Integer.parseInt(s.toString()) > 255) {
                        txt_ip3.setText("");
                    } else {
                        if (s.length() >= 3) {
                            txt_ip4.requestFocus();
                        }
                    }

                    if (s.equals("00") || s.equals("000")) {
                        txt_ip3.setText("0");
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
//despues de que el texto cambia
            }
        });
        txt_ip4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//antes de que el texto cambia
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //en el momento del cambio
                try {

                    if (s.toString().equals("00") || s.toString().equals("000")) {
                        txt_ip4.setText("0");
                    } else if (s.length() > 1 && (s.toString()).substring(0, 1).equals("0")) {
                        txt_ip4.setText(s.toString().substring(1, s.length()));
                    }

                    if (Integer.parseInt(s.toString()) > 255) {
                        txt_ip4.setText("");
                    } else {
                        if (s.length() >= 3) {
                            txt_prefijo.requestFocus();
                        }
                    }

                    if (s.equals("00") || s.equals("000")) {
                        txt_ip4.setText("0");
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
//despues de que el texto cambia
            }
        });
        txt_prefijo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//antes de que el texto cambia
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //en el momento del cambio
                try {
                    String opciones = spiner.getSelectedItem().toString();
                    if (opciones.equals("usar un prefijo") && Integer.parseInt(s.toString()) > 32) {
                        txt_prefijo.setText("");
                    }

                    if (opciones.equals("usar N° de subred a dividir") && Integer.parseInt(s.toString()) > 16777216) {
                        txt_prefijo.setText("");
                    }

                    if (s.toString().equals("00") || s.toString().equals("000") || s.toString().equals("0")) {
                        txt_prefijo.setText("");
                    }
                } catch (Exception e) {
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
//despues de que el texto cambia
            }
        });
    }


    //=================================================================================================
    //=========================================== VALIDACIONES =======================================================

    private void hallar_dividir_subred(View view) {

        try {
            tv_ressultado_2.setText("");
            verficar_se_hayo = false;
            //reiniciar


            spiner2.setVisibility(View.INVISIBLE);
            btn_hallar_2.setVisibility(View.INVISIBLE);

            String formula = "";
            if (spinner3.getSelectedItemPosition() == 0) {
                formula = "2^n";
            }
            if (spinner3.getSelectedItemPosition() == 1) {
                formula = "2^n-2";
            }


            tv_ressultado_1.setText("");
            if (txt_ip1.getText().length() > 0 && txt_ip2.getText().length() > 0 &&
                    txt_ip3.getText().length() > 0 && txt_ip4.getText().length() > 0) {

                int ip1 = Integer.parseInt(txt_ip1.getText().toString());
                int ip2 = Integer.parseInt(txt_ip2.getText().toString());
                int ip3 = Integer.parseInt(txt_ip3.getText().toString());
                int ip4 = Integer.parseInt(txt_ip4.getText().toString());
                String opcion = spiner.getSelectedItem().toString();


                //======================================
                //=========================================
                if (opcion.equals("usar un prefijo")) {
                    if (txt_prefijo.getText().length() > 0) {
                        int pre = Integer.parseInt(txt_prefijo.getText().toString());
                        String validacio_reci[] = validacion.validar_prefijo(ip1, pre);

                        if (validacio_reci[0].equals("true")) {
                            //============================================================================================
                            tv_ressultado_1.setText(resolucion_subred_2.hallar_subred(ip1, ip2, ip3, ip4, pre, opcion, formula));
                            spiner2.setVisibility(View.VISIBLE);
                            btn_hallar_2.setVisibility(View.VISIBLE);

                            //guardando datos
                            verficar_se_hayo = true;
                            datos[0] = ip1;
                            datos[1] = ip2;
                            datos[2] = ip3;
                            datos[3] = ip4;
                            datos[4] = pre;

                        } else {
                            if (!validacio_reci[1].equals("")) {
                                enviar_mensaje(validacio_reci[1], view);
                            }
                        }
                    } else {
                        enviar_mensaje("ingrese un prefijo para comenzar ", view);
                    }
                }
                //======================================================
                if (opcion.equals("usar N° de subred a dividir")) {
                    if (txt_prefijo.getText().length() > 0) {
                        int subred_div = Integer.parseInt(txt_prefijo.getText().toString());
                        String validacio_reci[] = validacion.validar_subred_dividir(ip1, subred_div);

                        if (validacio_reci[0].equals("true")) {

                            //)))))))))))))))))))))))))))9))))))))))))))))))))))))9
                            tv_ressultado_1.setText(resolucion_subred_2.hallar_subred(ip1, ip2, ip3, ip4, subred_div, opcion, formula));

                            spiner2.setVisibility(View.VISIBLE);
                            btn_hallar_2.setVisibility(View.VISIBLE);

                            //guardando datos
                            verficar_se_hayo = true;
                            datos[0] = ip1;
                            datos[1] = ip2;
                            datos[2] = ip3;
                            datos[3] = ip4;
                            datos[4] = subred_div;
                        } else {
                            if (!validacio_reci[1].equals("")) {
                                enviar_mensaje(validacio_reci[1], view);
                            }
                        }
                    } else {
                        enviar_mensaje("ingrese una subred a dividir ", view);
                    }
                }

                //==============================================================0
                //===================================================================
            } else {
                enviar_mensaje("complete los campos ", view);
            }
        } catch (Exception e) {
            enviar_mensaje("ocurrio un error al resolver", view);
        }
    }

    //=======================================================================================================/
//=======================================================================================================
    private void enviar_mensaje(String mensaje, View vista) {
        LayoutInflater inflater = getLayoutInflater();
        View layaout = inflater.inflate(R.layout.mensaje_toast, (ViewGroup) vista.findViewById(R.id.layout_mensaje));

        TextView tv_mostrar = layaout.findViewById(R.id.tv_toast);
        tv_mostrar.setText(mensaje);

        Toast Toast_mensaje = new Toast(getContext());
        Toast_mensaje.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 200);
        Toast_mensaje.setDuration(Toast.LENGTH_LONG);
        Toast_mensaje.setView(layaout);
        Toast_mensaje.show();
    }

    private void linpiar_componentes(final View view) {
        btn_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    txt_ip1.setText("");
                    txt_ip2.setText("");
                    txt_ip3.setText("");
                    txt_ip4.setText("");
                    txt_prefijo.setText("");
                    spiner2.setVisibility(View.INVISIBLE);
                    btn_hallar_2.setVisibility(View.INVISIBLE);
                    tv_ressultado_1.setText("");
                    tv_ressultado_2.setText("");
                } catch (Exception e) {
                    enviar_mensaje("error al limpiar campos", view);
                }
            }
        });


    }


    private void cuadro_de_dialogo_subred(View view) {


        final String aux[] = new String[1];
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); //MainActivity.this
            LayoutInflater inflater = getLayoutInflater();
            View vista_dialogo = inflater.inflate(R.layout.cuadro_de_dialogo_subred, null);
            builder.setView(vista_dialogo);

            final AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            //leer componentes
            final TextView tv_titulo = vista_dialogo.findViewById(R.id.tv_dialogo);
            final EditText txt_entrada = vista_dialogo.findViewById(R.id.txt_dialogo);
            Button btn_aceptar = vista_dialogo.findViewById(R.id.btn_aceptar_dilogo);


            //acion de lboton
            btn_aceptar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //codigo

                    aux[0] = txt_entrada.getText().toString();
                    if (!aux[0].equals("")) {

                        if (Integer.parseInt(txt_entrada.getText().toString()) > resolucion_subred_2.getSubred_limite()) {
                            tv_titulo.setText("Ingrese Un Indice Menor o Igual a: " + resolucion_subred_2.getSubred_limite());
                        } else {

                            //resolucion================================
                            String formula = "";
                            if (spinner3.getSelectedItemPosition() == 0) {
                                formula = "2^n";
                            }
                            if (spinner3.getSelectedItemPosition() == 1) {
                                formula = "2^n-2";
                            }

                            String opcion = spiner.getSelectedItem().toString();
                            int indice = Integer.parseInt(aux[0]);
                            tv_ressultado_2.setText(resolucion_subred_2.hayar_subred_especifico
                                    (datos[0], datos[1], datos[2], datos[3], datos[4], indice, opcion, formula));

                            //=============================================================================

                            dialog.dismiss(); //termina
                        }
                    }


                }
            });


            dialog.show();
        } catch (Exception e) {
            enviar_mensaje("ocurrio un error en el dialogo", view);
        }

    }


    private void hallar_sudred_indice(View view) {
        try {
            tv_ressultado_2.setText("");
            if (verficar_se_hayo == true) {
                String formula = "";
                if (spinner3.getSelectedItemPosition() == 0) {
                    formula = "2^n";
                }
                if (spinner3.getSelectedItemPosition() == 1) {
                    formula = "2^n-2";
                }
                String opcion_2 = spiner2.getSelectedItem().toString();
                //==============================================================================
                if (opcion_2.equals("hallar una subred especifica")) {
                    cuadro_de_dialogo_subred(view);
                }
                //==============================================================================
                if (resolucion_subred_2.getSubred_limite() >= 100) {


                    //======================================
                    if (opcion_2.equals("hallar las 100 primeras subredes")) {
                        String opcion = spiner.getSelectedItem().toString();

                        String resultado = "";
                        for (int i = 0; i < 100; i++) {
                            resultado = resultado +
                                    resolucion_subred_2.hayar_subred_especifico
                                            (datos[0], datos[1], datos[2], datos[3], datos[4], i, opcion, formula) + "\n" + "\n" + "\n";
                        }
                        tv_ressultado_2.setText(resultado);
                    }
                    //=======================================================
                    if (opcion_2.equals("hallar las 50 primeras y 50 ultimas subredes")) {
                        String opcion = spiner.getSelectedItem().toString();

                        String resultado = "";
                        for (int i = 0; i < 25; i++) {
                            resultado = resultado +
                                    resolucion_subred_2.hayar_subred_especifico
                                            (datos[0], datos[1], datos[2], datos[3], datos[4], i, opcion, formula) + "\n" + "\n";
                        }

                        int limite = resolucion_subred_2.getSubred_limite();
                        for (int k = limite - 50; k < limite; k++) {
                            resultado = resultado +
                                    resolucion_subred_2.hayar_subred_especifico
                                            (datos[0], datos[1], datos[2], datos[3], datos[4], k, opcion, formula) + "\n" + "\n";
                        }
                        tv_ressultado_2.setText(resultado);
                    }


                } else {
                    enviar_mensaje("el indice total es menor a 100", view);
                }

                //==============================================================================
                if (opcion_2.equals("el indice es menor a 100 hallar todas las subredes")) {
                    if (resolucion_subred_2.getSubred_limite() <= 100) {
                        String opcion = spiner.getSelectedItem().toString();
                        String resultado = "";
                        for (int i = 0; i < resolucion_subred_2.getSubred_limite(); i++) {
                            resultado = resultado +
                                    resolucion_subred_2.hayar_subred_especifico
                                            (datos[0], datos[1], datos[2], datos[3], datos[4], i, opcion, formula) + "\n" + "\n" + "\n";
                        }
                        tv_ressultado_2.setText(resultado);
                    } else {
                        enviar_mensaje("el indice es mayor a 100", view);
                    }
                }
                //==============================================================================

            } else {
                enviar_mensaje("halle una sudred para comenzar", view);
            }
        } catch (Exception e) {
            enviar_mensaje("ocurrio un error al hallar el indice", view);
        }
    }


}
