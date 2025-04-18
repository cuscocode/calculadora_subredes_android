package com.example.app_navigation_tabbed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_binario_y_decimal extends Fragment {


    //declarando componentes
    private EditText txt_ip1, txt_ip2, txt_ip3, txt_ip4, txt_B1, txt_B2, txt_B3, txt_B4;
    private TextView tv_resultado_decimal, tv_resultado_biario;
    private ImageButton btn_limpiar;


    String binarios[] = {"", "", "", ""};
    String decimales[] = {"", "", "", ""};

    public fragment_binario_y_decimal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_binario_y_decimal, container, false);


        txt_ip1 = (EditText) vista.findViewById(R.id.txt_ip1D);
        txt_ip2 = (EditText) vista.findViewById(R.id.txt_ip2D);
        txt_ip3 = (EditText) vista.findViewById(R.id.txt_ip3D);
        txt_ip4 = (EditText) vista.findViewById(R.id.txt_ip4D);

        txt_B1 = (EditText) vista.findViewById(R.id.txt_1B);
        txt_B2 = (EditText) vista.findViewById(R.id.txt_2B);
        txt_B3 = (EditText) vista.findViewById(R.id.txt_3B);
        txt_B4 = (EditText) vista.findViewById(R.id.txt_4B);

        tv_resultado_decimal = (TextView) vista.findViewById(R.id.tv_resultadoD);
        tv_resultado_biario = (TextView) vista.findViewById(R.id.tv_resultadoB);


        btn_limpiar = (ImageButton) vista.findViewById(R.id.btn_limpiar_B);
        validar_cajas_binario(vista);
        validar_cajas_decimal(vista);
        borrar(vista);
        return vista;
    }

    private void validar_cajas_decimal(final View view) {
        txt_ip1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    if (Integer.parseInt(s.toString()) > 255 || Integer.parseInt(s.toString()) < 0) {
                        txt_ip1.setText("");
                    } else {

                        borarr_anteriores_decimal(view, 1);
                        binarios[0] = decimal_a_Binario(Integer.parseInt(s.toString()));
                        mostrar_resultados_binarios(view);

                        if (s.length() >= 3) {
                            txt_ip2.requestFocus();
                        }
                    }


                } catch (Exception e) {
                    borarr_anteriores_decimal(view, 1);

                    binarios[0] = "";
                    mostrar_resultados_binarios(view);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_ip2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (Integer.parseInt(s.toString()) > 255 || Integer.parseInt(s.toString()) < 0) {
                        txt_ip2.setText("");
                    } else {
                         if (txt_ip1.getText().length() > 0) {
                            borarr_anteriores_decimal(view, 2);
                            binarios[1] = " . " + decimal_a_Binario(Integer.parseInt(s.toString()));
                            mostrar_resultados_binarios(view);

                             if (s.length() >= 3) {
                                 txt_ip3.requestFocus();
                             }
                        } else {

                            enviar_mensaje("complete los campos anteriores", view);
                            txt_ip2.setText("");
                        }
                    }
                } catch (Exception e) {
                    borarr_anteriores_decimal(view, 2);
                    binarios[1] = "";
                    mostrar_resultados_binarios(view);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_ip3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (Integer.parseInt(s.toString()) > 255 || Integer.parseInt(s.toString()) < 0) {
                        txt_ip3.setText("");
                    } else {
                         if (txt_ip2.getText().length() > 0) {
                            borarr_anteriores_decimal(view, 3);
                            binarios[2] = " . " + decimal_a_Binario(Integer.parseInt(s.toString()));
                            mostrar_resultados_binarios(view);

                             if (s.length() >= 3) {
                                 txt_ip4.requestFocus();
                             }
                        } else {
                            txt_ip3.setText("");
                            enviar_mensaje("complete los campos anteriores", view);
                        }
                    }
                } catch (Exception e) {
                    borarr_anteriores_decimal(view, 3);
                    binarios[2] = "";
                    mostrar_resultados_binarios(view);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_ip4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if (Integer.parseInt(s.toString()) > 255 || Integer.parseInt(s.toString()) < 0) {
                        txt_ip4.setText("");
                    } else {
                        if (txt_ip3.getText().length() > 0) {
                            binarios[3] = " . " + decimal_a_Binario(Integer.parseInt(s.toString()));
                            mostrar_resultados_binarios(view);
                        } else {
                            txt_ip4.setText("");
                            enviar_mensaje("complete los campos anteriores", view);
                        }
                    }
                } catch (Exception e) {
                    binarios[3] = "";
                    mostrar_resultados_binarios(view);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void validar_cajas_binario(final View view) {
        txt_B1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {

                    int a = s.length();
                    int bi = Integer.parseInt(s.toString().substring(a - 1, a));

                    if (bi == 1 || bi == 0) {
                        borarr_anteriores_binarios(view, 1);
                        decimales[0] = (binario_A_decimal(s.toString())) + "";
                        mostrar_resultados_decimales(view);
                    } else {
                        txt_B1.setText("");
                    }

                    if (s.length() >= 8) {
                        txt_B2.requestFocus();
                    }


                } catch (Exception e) {
                    borarr_anteriores_binarios(view, 1);
                    decimales[0] = "";
                    mostrar_resultados_decimales(view);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_B2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int a = s.length();
                    int bi = Integer.parseInt(s.toString().substring(a - 1, a));

                    if (txt_B1.getText().length() > 0) {
                        if (bi == 1 || bi == 0) {
                            borarr_anteriores_binarios(view, 2);
                            decimales[1] = " . " + (binario_A_decimal(s.toString()));
                            mostrar_resultados_decimales(view);
                        } else {
                            txt_B2.setText("");
                        }
                        if (s.length() >= 8) {
                            txt_B3.requestFocus();
                        }

                    } else {
                        txt_B2.setText("");
                        enviar_mensaje("complete los campos anteriores", view);
                    }
                } catch (Exception e) {
                    borarr_anteriores_binarios(view, 2);
                    decimales[1] = "";
                    mostrar_resultados_decimales(view);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_B3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int a = s.length();
                    int bi = Integer.parseInt(s.toString().substring(a - 1, a));

                    if (txt_B3.getText().length() > 0) {
                        if (bi == 1 || bi == 0) {
                            borarr_anteriores_binarios(view, 3);
                            decimales[2] = " . " + (binario_A_decimal(s.toString()));
                            mostrar_resultados_decimales(view);
                        } else {
                            txt_B3.setText("");
                        }

                        if (s.length() >= 8) {
                            txt_B4.requestFocus();
                        }
                    } else {
                        txt_B3.setText("");
                        enviar_mensaje("complete los campos anteriores", view);
                    }
                } catch (Exception e) {
                    borarr_anteriores_binarios(view, 3);
                    decimales[2] = "";
                    mostrar_resultados_decimales(view);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        txt_B4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int a = s.length();
                    int bi = Integer.parseInt(s.toString().substring(a - 1, a));

                    if (txt_B3.getText().length() > 0) {
                        if (bi == 1 || bi == 0) {

                            decimales[3] = " . " + (binario_A_decimal(s.toString()));
                            mostrar_resultados_decimales(view);
                        } else {
                            txt_B4.setText("");
                        }
                    } else {
                        txt_B4.setText("");
                        enviar_mensaje("complete los demas campos anteriores", view);
                    }

                } catch (Exception e) {
                    decimales[3] = "";
                    mostrar_resultados_decimales(view);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    //==========================================================
    private String decimal_a_Binario(int numero) {

        String bin8 = "0", bin7 = "0", bin6 = "0", bin5 = "0", bin4 = "0", bin3 = "0", bin2 = "0", bin1 = "0";
        int nu8, nu7, nu6, nu5, nu4, nu3, nu2, nu1;

//------------------------
        if (numero >= 128) {
            bin8 = "1";
            nu8 = numero - 128;
        } else {
            nu8 = numero;
        }
        //7
        if (nu8 >= 64) {
            bin7 = "1";
            nu7 = nu8 - 64;
        } else {
            nu7 = nu8;
        }
        //6
        if (nu7 >= 32) {
            bin6 = "1";
            nu6 = nu7 - 32;
        } else {
            nu6 = nu7;
        }
        //5
        if (nu6 >= 16) {
            bin5 = "1";
            nu5 = nu6 - 16;
        } else {
            nu5 = nu6;
        }
        //-4--------
        if (nu5 >= 8) {
            bin4 = "1";
            nu4 = nu5 - 8;
        } else {
            nu4 = nu5;
        }
        //3------------
        if (nu4 >= 4) {
            bin3 = "1";
            nu3 = nu4 - 4;
        } else {
            nu3 = nu4;
        }
        //2-------
        if (nu3 >= 2) {
            bin2 = "1";
            nu2 = nu3 - 2;
        } else {
            nu2 = nu3;
        }
        //---1
        if (nu2 >= 1) {
            bin1 = "1";
            nu1 = nu2 - 1;
        }
//----------------------
        String Binario = bin8 + bin7 + bin6 + bin5 + bin4 + bin3 + bin2 + bin1;
        return Binario;
    }

    private int binario_A_decimal(String numero) {

        String[] arreglo = new String[8];
        String[] arregloax = new String[8];
        for (int a = 0; a < 8; a++) {
            arreglo[a] = "0";
            arregloax[a] = "0";
        }
        //-----------------
        int b = 1;
        int c = numero.length();
        for (int k = 0; k < c; k++) {
            String d = numero.substring(k, b);
            arreglo[k] = d;
            b++;
        }
        //---------------
        int e = c - 1;
        for (int l = 0; l < c; l++) {
            arregloax[l] = arreglo[e];
            e--;
        }
        //-------------------
        int[] arreglo2 = new int[8];
        arreglo2[0] = 1;
        arreglo2[1] = 2;
        arreglo2[2] = 4;
        arreglo2[3] = 8;
        arreglo2[4] = 16;
        arreglo2[5] = 32;
        arreglo2[6] = 64;
        arreglo2[7] = 128;
        //-----------------
        int decimal = 0;
        for (int i = 0; i < 8; i++) {
            if (arregloax[i].equals("1")) {
                decimal = decimal + arreglo2[i];
            } else {
                decimal = decimal + 0;
            }
        }
        return decimal;
    }


    private void mostrar_resultados_binarios(View view) {
        try {
            tv_resultado_decimal.setText("");
            String resultado = "";
            for (int i = 0; i < 4; i++) {
                resultado = resultado + binarios[i];
            }
            tv_resultado_decimal.setText(resultado);
        } catch (Exception e) {
            enviar_mensaje("ocurrio un error", view);
        }
    }


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

    private void borarr_anteriores_decimal(View view, int posicion) {
        for (int i = posicion; i < 4; i++) {
            binarios[i] = "";
        }
        mostrar_resultados_binarios(view);

        switch (posicion) {
            case 1:
                txt_ip2.setText("");
                txt_ip3.setText("");
                txt_ip4.setText("");
                break;
            case 2:
                txt_ip3.setText("");
                txt_ip4.setText("");
                break;
            case 3:
                txt_ip4.setText("");
                break;
        }
    }

    private void mostrar_resultados_decimales(View view) {
        try {
            tv_resultado_biario.setText("");
            String resultado = "";
            for (int i = 0; i < 4; i++) {
                resultado = resultado + decimales[i];
            }
            tv_resultado_biario.setText(resultado);
        } catch (Exception e) {
            enviar_mensaje("ocurrio un error", view);
        }
    }

    private void borarr_anteriores_binarios(View view, int posicion) {
        for (int i = posicion; i < 4; i++) {
            decimales[i] = "";
        }
        mostrar_resultados_decimales(view);

        switch (posicion) {
            case 1:
                txt_B2.setText("");
                txt_B3.setText("");
                txt_B4.setText("");
                break;
            case 2:
                txt_B3.setText("");
                txt_B4.setText("");
                break;
            case 3:
                txt_B4.setText("");
                break;
        }
    }

    private void borrar(View view) {
        try {
            btn_limpiar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    txt_ip1.setText("");
                    txt_ip2.setText("");
                    txt_ip3.setText("");
                    txt_ip4.setText("");
                    tv_resultado_decimal.setText("");

                    txt_B1.setText("");
                    txt_B2.setText("");
                    txt_B3.setText("");
                    txt_B4.setText("");
                    tv_resultado_biario.setText("");
                }
            });


        } catch (Exception e) {
            enviar_mensaje("error al limpiar", view);
        }
    }
}
