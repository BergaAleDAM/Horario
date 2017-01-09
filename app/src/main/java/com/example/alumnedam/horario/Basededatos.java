package com.example.alumnedam.horario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by ALUMNEDAM on 01/12/2016.
 */

public class  Basededatos extends SQLiteOpenHelper{

    String[] sqlCreate = new String[3];
    String[] sqlInserts = new String[5];
    public Basededatos (Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        sqlCreate[0] = "CREATE TABLE PROFESORES (id_profe INTEGER, nom_prof TEXT, cognom_prof TEXT)";
        sqlCreate[1] = "CREATE TABLE ASIGNATURA (id_asignatura INTEGER, id_profe INTEGER, nom_asig TEXT)";
        sqlCreate[2] = "CREATE TABLE HORARI (id_horari INTEGER , id_subgrup TEXT, id_asignatura INTEGER, hora_inici TEXT, hora_fi TEXT, dia_setmana TEXT )";



        sqlInserts[0]="INSERT INTO PROFESORES VALUES (1, 'Lluis Maria', 'Perpinya'), (2, 'Jorge', 'Rubio'), (3, 'Josefa', 'Gonzalez') , (4, 'Marta', 'Planas'), (5, 'Jose Antonio', 'Leo')";
        sqlInserts[1]="INSERT INTO ASIGNATURA VALUES ('M3', 3, 'Programacion'),('M5-M2-M6' , 2,'Cosas que tienen que ver con programacion'),('M7', 5, 'PHP')," +
                "('M8', 1, 'Programacio amb android'),('M9', 2, 'Hilos y procesos'),('M10', 4, 'Cosas con erp'), ('MPatio',NULL, 'Patio')";
        sqlInserts[2]="INSERT INTO HORARI VALUES (1, 'A', 'M5-M2-M6', '16:00:00', '16:59:59', 'Dimecres')," +
                "(2, 'A1', 'M9', '17:00:00', '17:59:59', 'Dimecres')," +
                "(3, 'A1', 'M9', '18:20:00', '19:19:59', 'Dimecres')," +
                "(4, 'A1', 'M3', '19:20:00', '21:19:59', 'Dimecres')," +
                "(5, 'A2', 'M8', '17:00:00', '17:59:59', 'Dimecres')," +
                "(6, 'A2', 'M8', '18:20:00', '19:19:59', 'Dimecres')," +
                "(7, 'A2', 'M9', '19:20:00', '21:19:59', 'Dimecres')" ;

        sqlInserts[3]="INSERT INTO HORARI VALUES (10, 'A', 'M9', '15:00:00', '15:59:59','Dijous')," +
                "(11, 'A1', 'M8', '16:00:00', '17:59:59', 'Dijous')," +
                "(12, 'A', 'M5-M2-M6', '18:20:00', '21:19:59', 'Dijous')," +
                "(13, 'A2', 'M3', '16:00:00', '17:59:59', 'Dijous')" ;

        sqlInserts[4]="INSERT INTO HORARI VALUES (10, 'A', 'M10', '15:00:00', '16:59:59','Divendres')," +
                "(11, 'A1', 'M8', '17:00:00', '18:19:59', 'Divendres')," +
                "(12, 'A', 'M5-M2-M6', '18:20:00', '21:19:59', 'Divendres')," +
                "(13, 'A2', 'M9', '16:00:00', '17:59:59', 'Divendres')" ;



        for(int i=0;i < sqlCreate.length; i++){
            sqLiteDatabase.execSQL(sqlCreate[i]);
        }

        for (int i=0; i< sqlInserts.length; i++){
            sqLiteDatabase.execSQL(sqlInserts[i]);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}


