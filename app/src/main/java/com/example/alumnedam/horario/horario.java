package com.example.alumnedam.horario;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class horario extends AppCompatActivity {

    private SQLiteDatabase db;
    String color, grupo, usuario;


    /**
     * En el on Create del horario tendremos los parametros que hemos pasado por el intent y ejecutaremos
     * las consulta
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        grupo = getIntent().getStringExtra("grup");
        color = getIntent().getStringExtra("color");
        usuario = getIntent().getStringExtra("nom");
        consulta();
    }


    /**
     *
     * dentro de las consulta tenemos todos los campos declarados al principio que sera lo que muestre
     * despues el resultado en el layout
     *
     * establecemos el dia de la semana que vamos a mostrar que sera el dia actual, y cogemos la hora del
     * sistema para saber que asignatura nos toca en ese momento
     *
     * Tambien le pasamos el grupo para  diferencias entre un grupo y otro a la hora de mostrar el resutltado
     *
     *
     */
    public void consulta(){

        String codiAssignatura, horaInici, horaFi, diaSetmana, diaSetmanaHorari, profesor, aula, grup;

        diaSetmana = ActualDay();

        Basededatos sql = new Basededatos(this, "HorariABA", null, 1);

        db = sql.getWritableDatabase();

        String horaDelSistema;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        horaDelSistema = formato.format(calendar.getTime());

        String[] dades = new String[]{horaDelSistema, grupo, diaSetmana};
        Cursor c = db.rawQuery("SELECT * FROM HORARI WHERE ? BETWEEN hora_inici AND hora_fi AND id_subgrup = ? AND dia_setmana = ?", dades);
        if (c.moveToFirst()) {
           do {
               //grup = c.getString(1);
               codiAssignatura = asignatura(c.getString(2));
               horaInici = c.getString(3);
               horaFi = c.getString(4);
               diaSetmanaHorari = c.getString(5);
               profesor = professor(c.getString(6));
               aula = c.getString(7);

                } while (c.moveToNext());

                asignaTV(usuario, codiAssignatura, horaInici, horaFi, diaSetmanaHorari, profesor, aula);
            }

    }


    /**
     *
     * Este metodo esta dentro del anterior y lo que hace es principalmente devolver el dia en el que se encuentra
     * el telefono
     *
     *
     */
    public String ActualDay(){
        String[] diesSetmana = new String[]{ "Dilluns", "Dimarts", "Dimecres", "Dijous", "Divendres", "Dissabte", "Diumenge"};
        Calendar cal = Calendar.getInstance();
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        String dia = diesSetmana[dow-1];
        return dia;
    }



    /**
     *
     *Este metodo basicamente devuelve el nombre de la asignatura
     *
     * @param codiAsignatura
     * @return
     */
    public String asignatura(String codiAsignatura) {
        String nom = "";
        String[] asig = new String[]{codiAsignatura};
        Cursor c = db.rawQuery("SELECT nom_asig FROM ASIGNATURA WHERE ? LIKE Id_Asignatura", asig);
        if (c.moveToFirst()) {
            do {
                nom = c.getString(0);
            } while (c.moveToNext());
        }
        return nom;
    }

    /**
     *
     * Hace lo mismo que asignatura pero de la tabla profesor
     *
     * @param profesor
     * @return
     */
    public String professor(String profesor) {
        String nom = "";
        String[] prof = new String[]{profesor};
        Cursor c = db.rawQuery("SELECT nom_prof FROM PROFESOR WHERE ? LIKE Id_profesor", prof);
        if (c.moveToFirst()) {
            do {
                nom = c.getString(0);
            } while (c.moveToNext());
        }
        return nom;
    }

    /**
     *
     *
     * Se cambian todos los TextView relevantes para darles el color que se escogió inicialmente y
     * mostrar el resultado de la consulta

     * @param codiAsignatura
     * @param horaInici
     * @param horaFi
     * @param diaSetmanaHorari
     * @param profesor
     * @param aula
     */
    public void asignaTV( String usuario,String codiAsignatura, String horaInici, String horaFi, String diaSetmanaHorari, String profesor, String aula) {


        TextView saludos = (TextView) findViewById(R.id.saludos2);
        saludos.setText(usuario);


        TextView hInici = (TextView) findViewById(R.id.tvHoraIn2);
        hInici.setText(horaInici);
        hInici.setTextColor(Integer.parseInt(color));

        TextView hFi = (TextView) findViewById(R.id.tvHoraFi2);
        hFi.setText(horaFi);
        hFi.setTextColor(Integer.parseInt(color));

        TextView dSetmana = (TextView) findViewById(R.id.tvDia2);
        dSetmana.setText(diaSetmanaHorari);
        dSetmana.setTextColor(Integer.parseInt(color));

        TextView classe = (TextView) findViewById(R.id.tvClasse2);
        classe.setText(aula);
        classe.setTextColor(Integer.parseInt(color));

        TextView assig = (TextView) findViewById(R.id.tvAsignatura2);
        assig.setText(codiAsignatura);
        assig.setTextColor(Integer.parseInt(color));

        TextView profe = (TextView) findViewById(R.id.tvProfessor2);
        profe.setText(profesor);
        profe.setTextColor(Integer.parseInt(color));
    }


}

