package com.example.alumnedam.horario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ALUMNEDAM on 01/12/2016.
 */

public class Basededatos extends SQLiteOpenHelper{

    String[] sqlCreate = new String[3];

    public Basededatos (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        slqCreate[0] = "CREATE TABLE PROFESORES (id_profe INTEGER, nom_prof TEXT, cognoms_profe)";
        slqCreate[1] = "CREATE TABLE ASIGNATURA (id_asignatura INTEGER, id_profe INTEGER, nom_prof TEXT, cognoms_profe)";
        slqCreate[2] = "CREATE TABLE PROFESORES (id_profe INTEGER, nom_prof TEXT, cognoms_profe)";

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}


