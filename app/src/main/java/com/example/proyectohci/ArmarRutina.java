package com.example.proyectohci;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ArmarRutina extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private ListView listView;
    private ArrayList<String> nombreEjercicio;
    private ArrayList<String> tipoEjercicio;
    private ArrayList<Integer> numeroReps;
    private ArrayList<Integer> estados;
    private ArrayList<String> l1,l2,l3;
    private Spinner s1,s2,s3;
    private ArrayAdapter<String> adp1,adp2,adp3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armar_rutina);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        l1=new ArrayList<String>();
        l1.add("Cardio");
        l1.add("Fuerza");
        l1.add("Flexibilidad");
        l2=new ArrayList<String>();
        l3=new ArrayList<String>();
        for (Integer i=1;i<=100;i++){
            l3.add(i.toString());
        }
        s1=(Spinner) findViewById(R.id.spinner_Categoria);
        s2=(Spinner) findViewById(R.id.spinner_ejercicio);
        s3=(Spinner) findViewById(R.id.spinner_cantidad);
        adp1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,l1);
        s1.setAdapter(adp1);
        adp2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,l3);
        s2.setAdapter(adp2);
        adp3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,l3);
        s3.setAdapter(adp3);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView=(ListView)findViewById(R.id.list_view_ejercicios);
        CustomAdapter listAdapter= new CustomAdapter();
        listView.setAdapter(listAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.armar_rutina, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nombreEjercicio.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view=getLayoutInflater().inflate(R.layout.layout_listview_armar_rutina,null);
            ImageView imageView=(ImageView)view.findViewById(R.id.iv_estado);
            TextView textViewEjercicio=(TextView)view.findViewById(R.id.tv_nombre_ejercicio);
            TextView textViewTipo=(TextView)view.findViewById(R.id.tv_tipo);
            TextView textCantidad=(TextView)view.findViewById(R.id.tv_cantidad);
            if (estados.get(i)==0)
                imageView.setImageResource(R.drawable.blue_clock);
            else
                imageView.setImageResource((R.drawable.green_check));
            textViewEjercicio.setText(nombreEjercicio.get(i));
            textViewTipo.setText(tipoEjercicio.get(i));
            textCantidad.setText(numeroReps.get(i).toString());
            return view;
        }
    }
}
