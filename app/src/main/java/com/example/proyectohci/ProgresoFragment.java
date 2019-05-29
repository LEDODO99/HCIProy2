package com.example.proyectohci;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ProgresoFragment extends Fragment {

    private CalendarView calendarView;

    private ArrayList<Progreso> progresos = new ArrayList<>();

    private Date fechaSeleccionada;
    private EditText editTextPeso;
    private EditText editTextCintura;
    private EditText editTextBrazoIzquierdo;
    private EditText editTextBrazoDerecho;
    private EditText editTextPiernaIzquierda;
    private EditText editTextPiernaDerecha;

    private TextInputLayout tilPeso;
    private TextInputLayout tilCintura;
    private TextInputLayout tilBrazoIzquierdo;
    private TextInputLayout tilBrazoDerecho;
    private TextInputLayout tilPiernaIzquierda;
    private TextInputLayout tilPiernaDerecha;

    private Button btnGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_progreso, container, false);
        fechaSeleccionada = Calendar.getInstance().getTime();
        calendarView = view.findViewById(R.id.calendarView);

        editTextPeso = view.findViewById(R.id.et_peso);
        editTextCintura = view.findViewById(R.id.et_medidaCintura);
        editTextBrazoIzquierdo = view.findViewById(R.id.et_medidaBrazoI);
        editTextBrazoDerecho = view.findViewById(R.id.et_medidaBrazoD);
        editTextPiernaIzquierda = view.findViewById(R.id.et_medidaPiernaI);
        editTextPiernaDerecha = view.findViewById(R.id.et_medidaPiernaD);

        tilPeso = view.findViewById(R.id.til_peso);
        tilCintura = view.findViewById(R.id.til_medidaCintura);
        tilBrazoIzquierdo = view.findViewById(R.id.til_medidaBrazoI);
        tilBrazoDerecho = view.findViewById(R.id.til_medidaBrazoD);
        tilPiernaIzquierda = view.findViewById(R.id.til_medidaPiernaI);
        tilPiernaDerecha = view.findViewById(R.id.til_medidaPiernaD);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        fechaSeleccionada = cal.getTime();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                 Calendar cal = Calendar.getInstance();
                 cal.set(Calendar.YEAR, year);
                 cal.set(Calendar.MONTH, month);
                 cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                 cal.set(Calendar.HOUR, 0);
                 cal.set(Calendar.MINUTE, 0);
                 cal.set(Calendar.SECOND, 0);
                 cal.set(Calendar.MILLISECOND, 0);
                 fechaSeleccionada = cal.getTime();

                 boolean encontrado = false;
                 for(Progreso progreso : progresos){
                     if(progreso.getFecha().compareTo(fechaSeleccionada) == 0){
                         editTextPeso.setText(String.valueOf(progreso.getPeso()));
                         editTextCintura.setText(String.valueOf(progreso.getCintura()));
                         editTextBrazoIzquierdo.setText(String.valueOf(progreso.getBrazoIzquierdo()));
                         editTextBrazoDerecho.setText(String.valueOf(progreso.getBrazoDerecho()));
                         editTextPiernaIzquierda.setText(String.valueOf(progreso.getPiernaIzquierda()));
                         editTextPiernaDerecha.setText(String.valueOf(progreso.getPiernaDerecha()));
                         encontrado = true;
                         break;
                     }else if (progreso.getFecha().compareTo(fechaSeleccionada) == -1){
                         editTextPeso.setText(String.valueOf(progreso.getPeso()));
                         editTextCintura.setText(String.valueOf(progreso.getCintura()));
                         editTextBrazoIzquierdo.setText(String.valueOf(progreso.getBrazoIzquierdo()));
                         editTextBrazoDerecho.setText(String.valueOf(progreso.getBrazoDerecho()));
                         editTextPiernaIzquierda.setText(String.valueOf(progreso.getPiernaIzquierda()));
                         editTextPiernaDerecha.setText(String.valueOf(progreso.getPiernaDerecha()));
                         encontrado = true;
                     }
                 }

                 if(!encontrado){
                     editTextPeso.setText("");
                     editTextCintura.setText("");
                     editTextBrazoIzquierdo.setText("");
                     editTextBrazoDerecho.setText("");
                     editTextPiernaIzquierda.setText("");
                     editTextPiernaDerecha.setText("");
                     btnGuardar.setText("Guardar Datos");
                 }else{
                     btnGuardar.setText("Actualizar");
                 }
             }
         });
        btnGuardar = view.findViewById(R.id.button_guardarprogreso);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View button) {

                boolean flag = true;

                if(editTextPeso.getText().toString().isEmpty()){
                    tilPeso.setError(" ");
                    flag = false;
                }else{
                    tilPeso.setError("");
                }

                if(editTextCintura.getText().toString().isEmpty()){
                    tilCintura.setError(" ");
                    flag = false;
                }else{
                    tilCintura.setError("");
                }

                if(editTextBrazoIzquierdo.getText().toString().isEmpty()){
                    tilBrazoIzquierdo.setError(" ");
                    flag = false;
                }else{
                    tilBrazoIzquierdo.setError("");
                }

                if(editTextBrazoDerecho.getText().toString().isEmpty()){
                    tilBrazoDerecho.setError(" ");
                    flag = false;
                }else{
                    tilBrazoDerecho.setError("");
                }

                if(editTextPiernaIzquierda.getText().toString().isEmpty()){
                    tilPiernaIzquierda.setError(" ");
                    flag = false;
                }else{
                    tilPiernaIzquierda.setError("");
                }

                if(editTextPiernaDerecha.getText().toString().isEmpty()){
                    tilPiernaDerecha.setError(" ");
                    flag = false;
                }else{
                    tilPiernaDerecha.setError("");
                }

                if(flag) {
                    for(Progreso progreso : progresos){
                        if(progreso.getFecha().compareTo(fechaSeleccionada) == 0){
                            progresos.remove(progreso);
                        }
                    }
                    progresos.add(new Progreso(fechaSeleccionada, Integer.valueOf(editTextPeso.getText().toString()), Integer.valueOf(editTextCintura.getText().toString()), Integer.valueOf(editTextBrazoDerecho.getText().toString()), Integer.valueOf(editTextBrazoIzquierdo.getText().toString()), Integer.valueOf(editTextPiernaDerecha.getText().toString()), Integer.valueOf(editTextPiernaDerecha.getText().toString())));
                    btnGuardar.setText("Actualizar");
                    Toast.makeText(view.getContext(), "Datos ingresados exitosamente!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(view.getContext(), "Por favor ingrese todas las medidas solicitadas.",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
