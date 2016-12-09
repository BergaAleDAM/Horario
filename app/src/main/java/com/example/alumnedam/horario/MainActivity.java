package com.example.alumnedam.horario;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Basededatos bd =   new Basededatos(this, "Il horario", null, 2);
        SQLiteDatabase slq= bd.getWritableDatabase();
    }
}
