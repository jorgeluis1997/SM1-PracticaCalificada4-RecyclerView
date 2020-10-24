package com.example.recyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RVAdapater.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RVAdapater adapter;
    private List<Persona> itemsPersonas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
        initListener();
    }

    private void initViews(){
        rvLista = findViewById(R.id.rv);
        svSearch = findViewById(R.id.svSearch);
    }

    private void initValues() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        itemsPersonas = getItems();
        adapter = new RVAdapater(itemsPersonas, this);
        rvLista.setAdapter(adapter);
    }

    private void initListener() {
        svSearch.setOnQueryTextListener(this);
    }

    private List<Persona> getItems() {
        List<Persona> itemListPersona = new ArrayList<>();
        itemListPersona.add(new Persona("Katharina Peralta", "30 años de edad", R.drawable.img1));
        itemListPersona.add(new Persona("Franco Escamilla", "21 años de edad", R.drawable.img2));
        itemListPersona.add(new Persona("Fernanda Alves", "40 años de edad", R.drawable.img3));
        itemListPersona.add(new Persona("Miguel Cervantes", "32 años de edad", R.drawable.img4));
        itemListPersona.add(new Persona("Samuel Reinoso", "65 años de edad", R.drawable.img5));
        itemListPersona.add(new Persona("Andre Gutierrez", "38 años de edad", R.drawable.img6));
        itemListPersona.add(new Persona("Samira Carrasco", "25 años de edad", R.drawable.img7));
        itemListPersona.add(new Persona("Yerson Morgade", "70 años de edad", R.drawable.img8));

        return itemListPersona;
    }

    @Override
    public void itemClick(Persona item) {
        Intent intent = new Intent(this, DetailPersona.class);
        intent.putExtra("itemDetail", item);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }
}
