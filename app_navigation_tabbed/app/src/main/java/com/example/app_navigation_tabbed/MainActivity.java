package com.example.app_navigation_tabbed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_navigation_tabbed.clases_logica.resolucion_subred_2;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.*;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;


//implementar navigation View
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //llamar al toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //============================ navegador drawer ==========================
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

        //private Toolbar toolbar;
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navegador_abrir_drawer, R.string.navegador_cerar_drawer);

        drawer.addDrawerListener(toogle);
        toogle.syncState();
//====================================

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contenedor, new fragment_tab_calc_ip()).commit();
        //CAMBIA FRAGMENT PARA AUE INICIE CON ESE


        //===============================================================================================================
        //=============================================================================================================0
        //=============================================================================================================

        //evento cuandoel menu se habre y cerra_
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                //Se llama cuando cambia la posición de un cajón.
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                //Llamado cuando un cajón se ha asentado en un estado completamente abierto.
                ocultar_teclado();
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                //   Llamado cuando un cajón se ha asentado en un estado completamente cerrado.
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                //Se llama cuando cambia el estado de movimiento del cajón
                ocultar_teclado();
            }
        });


        //para que los iconos del munu lateral conserven sus colores
        navigationView.setItemIconTintList(null);

      /*  para que demore al inicar try {
            Thread.sleep(5000);
        } catch (Exception e) {
        }*/
    }


    //======================================================================

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //MENU OPCIONES

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }


    //MENU OPCIONES DE LA PARTE DE ARRIBA
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.menu_pregunta_usuario) {
            //codigo

            cuadro_dialogo_ayuda_usuario();
            //--------------------
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    //OPCIONES DEL DRAWER NAVEGADOR
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int id = Item.getItemId();

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (id == R.id.nav_menu_direccionamiento) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new fragment_tab_calc_ip()).commit();
        }
        if (id == R.id.nav_menu_inicio) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new fragment_inicio()).commit();
        }
        if (id == R.id.nav_menu_host) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new fragment_inicio_host()).commit();
        }
        if (id == R.id.nav_menu_binario) {
            fragmentManager.beginTransaction().replace(R.id.contenedor, new fragment_binario_y_decimal()).commit();
        }

        if (id == R.id.nav_menu_hacer_de) {
            cuadro_dialogo_hacerca_de();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void cuadro_dialogo_ayuda_usuario() {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //MainActivity.this
            LayoutInflater inflater = getLayoutInflater();
            View vista_dialogo = inflater.inflate(R.layout.ayuda_usuario, null);
            builder.setView(vista_dialogo);

            final AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            //leer componentes
            ImageView btn_regresar = vista_dialogo.findViewById(R.id.btn_regresar);


            //acion de lboton
            btn_regresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss(); //termina
                }
            });


            dialog.show();
        } catch (Exception e) {

        }

    }

    private void cuadro_dialogo_hacerca_de() {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //MainActivity.this
            LayoutInflater inflater = getLayoutInflater();
            View vista_dialogo = inflater.inflate(R.layout.hacerca_de, null);
            builder.setView(vista_dialogo);

            final AlertDialog dialog = builder.create();
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            //leer componentes
            ImageView btn_facebook = vista_dialogo.findViewById(R.id.btn_facebook);
            ImageView btn_whatsaap = vista_dialogo.findViewById(R.id.btn_whatsaap);

            //DIRIJIR A FACEBOOK =========================================================
            btn_facebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Intent ir_a_sitio = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/rg.hack.incognitus"));
                        startActivity(ir_a_sitio);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                    // dialog.dismiss(); //termina
                }
            });

            //DIRIJIR A FACEBOOK=================================================
            btn_whatsaap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Intent ir_a_sitio = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/qr/F6MMI6AYRQAWF1"));
                        startActivity(ir_a_sitio);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                    // dialog.dismiss(); //termina
                }
            });


            dialog.show();
        } catch (Exception e) {

        }

    }


    private void ocultar_teclado() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager entrada = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            entrada.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
