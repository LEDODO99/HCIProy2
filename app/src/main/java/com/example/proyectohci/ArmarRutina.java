package com.example.proyectohci;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ArmarRutina extends AppCompatActivity {
    ListView listView;
    ArrayList<String> nombreEjercicio;
    ArrayList<String> tipoEjercicio;
    ArrayList<Integer> numeroReps;
    ArrayList<Integer> estados;
    ArrayList<String> l1,l2,l3;
    Spinner s1,s2,s3;
    ArrayAdapter<String> adp1,adp2,adp3;
    TextView textViewMedidores;
    Button AgregarEjercicio;
    CustomAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_armar_rutina2);
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
        nombreEjercicio=new ArrayList<>();
        tipoEjercicio=new ArrayList<>();
        numeroReps=new ArrayList<>();
        estados=new ArrayList<>();
        textViewMedidores=(TextView)findViewById(R.id.txt_medicion_agregar_ejercicio);
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
        adp2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,l2);
        s2.setAdapter(adp2);
        adp3=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,l3);
        s3.setAdapter(adp3);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                String sp1= String.valueOf(s1.getSelectedItem());
                if(sp1.equals("Cardio")){
                    l2.clear();
                    l2.add("Trote");
                    l2.add("Bicicleta");
                    l2.add("Natacion");
                    l2.add("Burpees");
                    l2.add("Aerobicos");
                }else if(sp1.equals("Fuerza")){
                    l2.clear();
                    l2.add("Peso Libre");
                    l2.add("Plancha");
                    l2.add("Despechadas");
                    l2.add("Abdominales");
                    l2.add("Squats");
                }else if(sp1.equals("Flexibilidad")){
                    l2.clear();
                    l2.add("Lunges");
                    l2.add("Yoga");
                    l2.add("Tai-Chi");
                    l2.add("Abductores");
                }else{
                    l2.clear();
                }
                adp2.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                l2.clear();
                adp2.notifyDataSetChanged();
            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String sp2= String.valueOf(s2.getSelectedItem());
                if (Arrays.asList(getResources().getStringArray(R.array.time_exercises_routine)).contains(sp2)){
                    textViewMedidores.setText("Minutos");
                }else{
                    textViewMedidores.setText("Repeticiones");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listView=(ListView)findViewById(R.id.list_view_rutina_ejercicios);
        listAdapter= new CustomAdapter();
        listView.setAdapter(listAdapter);

        registerForContextMenu(listView);

        AgregarEjercicio=(Button)findViewById(R.id.btn_agregar_ejercicio);
        AgregarEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombreEjercicio.add(s2.getSelectedItem().toString());
                tipoEjercicio.add(textViewMedidores.getText().toString());
                numeroReps.add(Integer.parseInt(s3.getSelectedItem().toString()));
                estados.add(0);
                listAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.pop_up_rutinas,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.marcar_completado:
                estados.set(info.position, 1);
                listAdapter.notifyDataSetChanged();
                return true;
            case R.id.elimina_de_rutina:
                estados.remove(info.position);
                nombreEjercicio.remove(info.position);
                tipoEjercicio.remove(info.position);
                numeroReps.remove(info.position);
                listAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);

        }
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
            view = getLayoutInflater().inflate(R.layout.custom_layout_rutina_ejercicio, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_estado);
            TextView textViewEjercicio = (TextView) view.findViewById(R.id.custom_nombre_ejercicio);
            TextView textViewTipo = (TextView) view.findViewById(R.id.custom_medidor_set);
            TextView textCantidad = (TextView) view.findViewById(R.id.custom_cantidad_set);
            if (estados.get(i) == 0)
                imageView.setImageResource(R.drawable.blue_clock);
            else
                imageView.setImageResource((R.drawable.green_check));
            textViewEjercicio.setText(nombreEjercicio.get(i));
            textViewTipo.setText(tipoEjercicio.get(i));
            textCantidad.setText(numeroReps.get(i).toString());
            view.setLongClickable(true);
            return view;
        }
    }

}
