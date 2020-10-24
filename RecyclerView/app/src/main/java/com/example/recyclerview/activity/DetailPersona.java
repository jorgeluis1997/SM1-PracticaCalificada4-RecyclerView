package com.example.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.recyclerview.R;

public class DetailPersona extends AppCompatActivity {
    private TextView tvNombreDetail;
    private TextView tvEdadDetail;
    private ImageView imgFotoDetail;
    private Persona itemDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.persona);
        setTitle(getClass().getSimpleName());

        initViews();
        initValues();
    }

    private void initViews() {
        imgFotoDetail = findViewById(R.id.fotoPersona);
        tvNombreDetail = findViewById(R.id.nombrePersona);
        tvEdadDetail = findViewById(R.id.edadPersona);
    }

    private void initValues(){
        itemDetail = (Persona) getIntent().getExtras().getSerializable("itemDetail");

        imgFotoDetail.setImageResource(itemDetail.getFoto());
        tvNombreDetail.setText(itemDetail.getNombre());
        tvEdadDetail.setText(itemDetail.getEdad());
    }
}
