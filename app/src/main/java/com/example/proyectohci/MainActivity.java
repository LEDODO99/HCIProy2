package com.example.proyectohci;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity 
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQ_CODE_SPEECH_INPUT = 100;

    private InicioFragment inicioFragment;
    private EjerciciosFragment ejerciciosFragment;
    private ProgresoFragment progresoFragment;
    private AyudaFragment ayudaFragment;
    private RutinasFragment rutinasFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startVoiceInput();
                //Intent myIntent = new Intent(getApplicationContext(), EjercicioActivity.class);
                //startActivity(myIntent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        inicioFragment = new InicioFragment();
        ejerciciosFragment = new EjerciciosFragment();
        progresoFragment = new ProgresoFragment();
        ayudaFragment = new AyudaFragment();
        rutinasFragment = new RutinasFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, inicioFragment).commit();
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Diga un comando");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new InicioFragment()).commit();
        } else if (id == R.id.nav_videos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new EjerciciosFragment()).commit();
        } else if (id == R.id.nav_progreso) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProgresoFragment()).commit();
        } else if (id == R.id.nav_ayuda) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AyudaFragment()).commit();
        } else if (id == R.id.nav_repeticiones) {
            Intent in= new Intent(this,EjercicioActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_rutinas) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RutinasFragment()).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    parseVoiceCommand(result.get(0));
                }
                break;
            }

        }
    }

    private void parseVoiceCommand(String voiceCommand){
        String snackbarMessage = "";

        if(voiceCommand.toLowerCase().contains("inicio")){
            snackbarMessage = "Redireccionando a Inicio.";
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, inicioFragment).commit();
        }else if(voiceCommand.toLowerCase().contains("videos")){
            snackbarMessage = "Redireccionando a Videos.";
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ejerciciosFragment).commit();
        }else if(voiceCommand.toLowerCase().contains("progreso")){
            snackbarMessage = "Redireccionando a Progreso.";
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, progresoFragment).commit();
        }else if(voiceCommand.toLowerCase().contains("ayuda")){
            snackbarMessage = "Redireccionando a Ayuda.";
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ayudaFragment).commit();
        }else if(voiceCommand.toLowerCase().contains("rutinas")){
            snackbarMessage = "Redireccionando a Rutinas.";
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, rutinasFragment).commit();
        }else if(voiceCommand.toLowerCase().contains("repeticiones")){
            snackbarMessage = "Redireccionando a Repeticiones.";
            Intent myIntent = new Intent(getApplicationContext(), EjercicioActivity.class);
            startActivity(myIntent);
        }else{
            snackbarMessage = "Comando de voz no indentificado.";
        }


        Snackbar.make(getWindow().getDecorView().getRootView(), snackbarMessage, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
}
