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

import com.example.app_navigation_tabbed.clases_logica.Encontar_host.host_pertenece;
import com.example.app_navigation_tabbed.clases_logica.validacion;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_encontrar_N_host_ip extends Fragment {

    //declarando componentes
    private EditText txt_ip1, txt_ip2, txt_ip3, txt_ip4, txt_prefijo;
    private Button btn_resolver;
    private TextView tv_resultado;
    private ImageButton btn_limpiar;

    public fragment_encontrar_N_host_ip() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_encontrar__n_host_ip, container, false);


        txt_ip1 = (EditText) vista.findViewById(R.id.txt_ip1_host_1);
        txt_ip2 = (EditText) vista.findViewById(R.id.txt_ip2_host_1);
        txt_ip3 = (EditText) vista.findViewById(R.id.txt_ip3_host_1);
        txt_ip4 = (EditText) vista.findViewById(R.id.txt_ip4_host_1);
        txt_prefijo = (EditText) vista.findViewById(R.id.txt_prefijo_host_1);
        tv_resultado = (TextView) vista.findViewById(R.id.tv_resultado_host_1);
        btn_resolver = (Button) vista.findViewById(R.id.btn_C_host_1);
        btn_limpiar = (ImageButton) vista.findViewById(R.id.btn_limpiar_host_1);


        //metodos
        validar_cajas(vista);
        boton_calcular(vista);
        limpiar(vista);

        return vista;
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
                    if (Integer.parseInt(s.toString()) > 32) {
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

    private void boton_calcular(final View view) {
        btn_resolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    tv_resultado.setText("");
                    if (txt_ip1.getText().length() > 0 && txt_ip2.getText().length() > 0 && txt_ip3.getText().length() > 0
                            && txt_ip4.getText().length() > 0 && txt_prefijo.getText().length() > 0) {

                        hallar_N_de_ip(view);

                    } else {
                        enviar_mensaje("complete los campos", view);
                    }
                } catch (Exception e) {
                    enviar_mensaje("ocurrio un error", view);
                }
            }
        });
    }

    private void hallar_N_de_ip(View view) {
        try {
            int ip_1 = Integer.parseInt(txt_ip1.getText().toString());
            int ip_2 = Integer.parseInt(txt_ip2.getText().toString());
            int ip_3 = Integer.parseInt(txt_ip3.getText().toString());
            int ip_4 = Integer.parseInt(txt_ip4.getText().toString());
            int prefijo = Integer.parseInt(txt_prefijo.getText().toString());

            //para clase A==================
            if (validacion.clase_de_red(ip_1).equals("A")) {
                if (prefijo >= 8) {
                    host_pertenece clase = new host_pertenece(ip_1, ip_2, ip_3, ip_4, prefijo);

                    tv_resultado.setText("la ip: " + clase.get_ipes() + " pertenece al host N°: " + clase.get_host() + "\n"
                            + "de la red: " + clase.get_id_red());
                } else {
                    enviar_mensaje("el prefijo para la red de clase A debe ser mayor o igual a 8", view);
                }

            }


            //para clase B==================
            if (validacion.clase_de_red(ip_1).equals("B")) {
                if (prefijo >= 16) {
                    host_pertenece clase = new host_pertenece(ip_1, ip_2, ip_3, ip_4, prefijo);

                    tv_resultado.setText("la ip: " + clase.get_ipes() + " pertenece al host N°: " + clase.get_host() + "\n"
                            + "de la red: " + clase.get_id_red());
                } else {
                    enviar_mensaje("el prefijo para la red de clase B debe ser mayor o igual a 16", view);
                }
            }


            //para clase A==================
            if (validacion.clase_de_red(ip_1).equals("C") ) {
                if (prefijo >= 24) {
                    host_pertenece clase = new host_pertenece(ip_1, ip_2, ip_3, ip_4, prefijo);
                    tv_resultado.setText("la ip: " + clase.get_ipes() + " pertenece al host N°: " + clase.get_host() + "\n"
                            + "de la red: " + clase.get_id_red());
                } else {
                    enviar_mensaje("el prefijo para la red de clase C debe ser mayor o igual a 24", view);
                }
            }
        } catch (Exception e) {
            enviar_mensaje("error al resolver", view);
        }
    }


    private void limpiar(View view) {
        btn_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_ip1.setText("");
                txt_ip2.setText("");
                txt_ip3.setText("");
                txt_ip4.setText("");
                txt_prefijo.setText("");
                tv_resultado.setText("");
            }
        });
    }
}
