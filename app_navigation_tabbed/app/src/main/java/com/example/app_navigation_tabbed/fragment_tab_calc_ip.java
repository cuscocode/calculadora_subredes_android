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

import com.example.app_navigation_tabbed.clases_logica.resolucion_ip_1;



/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_tab_calc_ip extends Fragment {


    //declarando componentes
    private EditText txt_ip1, txt_ip2, txt_ip3, txt_ip4, txt_prefijo;
    private Button btn_resolver;
    private TextView tv_resultado;
    private ImageButton btn_limpiar;

    public fragment_tab_calc_ip() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_tab_calc_ip, container, false);


        txt_ip1 = (EditText) vista.findViewById(R.id.txt_ip11);
        txt_ip2 = (EditText) vista.findViewById(R.id.txt_ip21);
        txt_ip3 = (EditText) vista.findViewById(R.id.txt_ip31);
        txt_ip4 = (EditText) vista.findViewById(R.id.txt_ip41);
        txt_prefijo = (EditText) vista.findViewById(R.id.txt_prefijo_1);
        tv_resultado = (TextView) vista.findViewById(R.id.tv_resultado1);
        btn_resolver = (Button) vista.findViewById(R.id.btn_C11);
        btn_limpiar = (ImageButton) vista.findViewById(R.id.btn_limpiar_1);

        validar_cajas(vista);
        hallar_ip(vista);
        limpiar_componentes(vista);


        return vista;

    }

    private void validar_cajas(View view) {
        txt_ip1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//antes de que el texto cambia
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //en el momento del cambio
                try {
                    if (Integer.parseInt(s.toString()) > 255 || Integer.parseInt(s.toString()) <= 0) {
                        txt_ip1.setText("");
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


    private void hallar_ip(final View view) {
        btn_resolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    if (txt_ip1.getText().length() > 0 && txt_ip2.getText().length() > 0
                            && txt_ip3.getText().length() > 0 && txt_ip4.getText().length() > 0) {
                        int ip1 = Integer.parseInt(txt_ip1.getText().toString());
                        int ip2 = Integer.parseInt(txt_ip2.getText().toString());
                        int ip3 = Integer.parseInt(txt_ip3.getText().toString());
                        int ip4 = Integer.parseInt(txt_ip4.getText().toString());

                        String prefijo = txt_prefijo.getText().toString();

                        tv_resultado.setText("");
                        tv_resultado.setText(resolucion_ip_1.resolocion_ip(ip1, ip2, ip3, ip4, prefijo));
                    } else {
                        enviar_mensaje("complete los campos ", view);
                    }

                } catch (Exception e) {
                    enviar_mensaje("ocurrio un error ", view);
                }

            }
        });


    }

    private void limpiar_componentes(final View view) {
        btn_limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    txt_ip1.setText("");
                    txt_ip2.setText("");
                    txt_ip3.setText("");
                    txt_ip4.setText("");
                    txt_prefijo.setText("");
                    txt_prefijo.setText("");
                    tv_resultado.setText("");
                } catch (Exception e) {
                    enviar_mensaje("ocurrio un error al borar", view);
                }

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


}
