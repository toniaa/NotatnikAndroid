package com.tonia.notatnik;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.tonia.notatnik.databinding.ActivitySzukajBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SzukajActivity extends AppCompatActivity {
    private Filtr filtr;
    private ActivitySzukajBinding binding;
    private Spinner poleKategoria;
    private List<Kategoria> kategorie;
    private KategorieAdapter kategorieAdapter;
    private KategorieViewModel kategorieViewModel;
    private CalendarView poleDataUtworzeniaOd, poleDataUtworzeniaDo, poleDataModyfikacjiOd, poleDataModyfikacjiDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szukaj);

        filtr = new Filtr();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_szukaj);
        binding.setFiltr(filtr);

        poleKategoria = (Spinner)findViewById(R.id.spKategoria);
        kategorie = new ArrayList<Kategoria>();
        kategorieAdapter = new KategorieAdapter(this, android.R.layout.simple_spinner_item, kategorie);
        poleKategoria.setAdapter(kategorieAdapter);

        kategorieViewModel = ViewModelProviders.of(this).get(KategorieViewModel.class);
        kategorieViewModel.getKategorie().observe(this, new Observer<List<Kategoria>>() {
            @Override
            public void onChanged(@Nullable final List<Kategoria> zaladowaneKategorie) {
                kategorie = zaladowaneKategorie;
                kategorieAdapter.setData(kategorie);
            }
        });

        poleKategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Kategoria kategoria = (Kategoria)poleKategoria.getSelectedItem();
                filtr.setKategoria(kategoria);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        poleDataUtworzeniaOd = (CalendarView)findViewById(R.id.cvDataUtworzeniaOd);
        poleDataUtworzeniaDo = (CalendarView)findViewById(R.id.cvDataUtworzeniaDo);
        poleDataModyfikacjiDo = (CalendarView)findViewById(R.id.cvDataModyfikacjiOd);
        poleDataModyfikacjiOd = (CalendarView)findViewById(R.id.cvDataModyfikacjiDo);

        poleDataUtworzeniaOd.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth, 0, 0, 0);
                filtr.setDataUtworzeniaOd(calendar.getTimeInMillis());
            }
        });
        poleDataUtworzeniaDo.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth, 0, 0, 0);
                filtr.setDataUtworzeniaDo(calendar.getTimeInMillis());
            }
        });
        poleDataModyfikacjiOd.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth, 0, 0, 0);
                filtr.setDataModyfikacjiOd(calendar.getTimeInMillis());            }
        });
        poleDataModyfikacjiDo.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth, 0, 0, 0);
                filtr.setDataModyfikacjiDo(calendar.getTimeInMillis());            }
        });
    }

    public void btZatwierdz_onClick(View view) {
        Intent powrot = new Intent();
        powrot.putExtra("filtr", binding.getFiltr());
        setResult(RESULT_OK, powrot);
        finish();
    }

    public void btAnuluj_onClick(View view) {
        Intent powrot = new Intent();
        setResult(RESULT_CANCELED, powrot);
        finish();
    }
}
