package com.example.app_navigation_tabbed;

import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_inicio extends Fragment {

    //AGREGANDO EL BAR==========================
    private AppBarLayout appbar;
    private TabLayout tabs;
    private ViewPager viewPager;


//==========================================================

    public fragment_inicio() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_inicio, container, false);



        //=========================== AGREGANDO LOS TABS
        View contenedor=(View)container.getParent();
        appbar=(AppBarLayout)contenedor.findViewById(R.id.appbar);  //viene del activity main
        tabs=new TabLayout(getActivity());

        tabs.setTabTextColors(Color.parseColor("#FFFFFF"),Color.parseColor("#FFFFFF"));
        appbar.addView(tabs);

        //MOSTRANDO LOS TABS
        viewPager=(ViewPager)view.findViewById(R.id.pager);  //viene de lo que se le agrego un View pager al fratment inicio
        ViewPagerAdapter PagerAdapter=new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(PagerAdapter);
        tabs.setupWithViewPager(viewPager);


        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        appbar.removeView(tabs);
    }


    public class ViewPagerAdapter extends FragmentStatePagerAdapter{

        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager) {
            super(fragmentManager);
        }





        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0:
                    fragment_tab_calc_subred fragment_tab_calc_subred=new fragment_tab_calc_subred();
                    return fragment_tab_calc_subred;

                case 1:
                    fragment_tab_calc_N_pertenece fragment_tab_calc_N_pertenece=new fragment_tab_calc_N_pertenece();
                    return fragment_tab_calc_N_pertenece;
            }


            return null;
        }


        //debe retornar el la posicion mayor o el numero de gragment
        @Override
        public int getCount() {
            return 2;
        }


        //PONIENDO TITULOS A LOS FRAGMEN
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){


                case 0:
                    String tab_text_1="SUBNETTING";
                    return tab_text_1;

                case 1:
                    String tab_text_2="BUSCAR SUBRED";
                    return tab_text_2;
            }
            return super.getPageTitle(position);
        }
    }



}
