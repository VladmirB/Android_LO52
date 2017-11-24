package com.example.jmarcel.shuttlesmgmt.Controlleur;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jmarcel.shuttlesmgmt.R;

import java.util.ArrayList;




public class StockActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private MySQLiteHelper db;
    String  Marq;

    private ArrayList<String> maliste;
    Button  btnResetFiltre;

    ListView list ;
    Context context;
    TextView tubeView;
    Spinner tubemarque;
    public static final int ACHATS_ACTIVITY_REQUEST_CODE = 42;
    public static final int FORMULAIRE_ACTIVITY_REQUEST_CODE= 43;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);
        context = this;
        db = new MySQLiteHelper(this);

        tubeView = (TextView)findViewById(R.id.formulaire_marque);
        tubemarque= (Spinner) findViewById(R.id.marque_Spinner);
        tubemarque.setOnItemSelectedListener(this);

        btnResetFiltre =(Button) findViewById(R.id.btnFiltreFormulaire);

        list = (ListView)findViewById(R.id.tab_data_listview);

        viewAll();
        getMarque();
        ResetFiltre();

    }


    private void viewAll()
    {

        Cursor data = db.getAllStockData();

        if(data.getCount()==0)
        {
            showMessage("Error","Le stock est vide");
            return;

        }
        String[] from = new String[] { "Nom","Reference","Stock"};

        int[] to = new int[] { R.id.marqueCol_textview,
                R.id.referenceCol_textview,R.id.stockCol_textview};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
                R.layout.activity_stocks_lignes, data, from, to, 0);

        ListView lv = (ListView) findViewById(R.id.tab_data_listview);
        lv.setAdapter(adapter);



    }

    private void viewAll1(String nom)
    {

        Cursor data = db.getStockData(nom);

        if(data.getCount()==0)
        {
            showMessage("Error","Le stock est vide");
            return;

        }
        String[] from = new String[] { "Nom","Reference","Stock"};

        int[] to = new int[] { R.id.marqueCol_textview,
                R.id.referenceCol_textview,R.id.stockCol_textview};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
                R.layout.activity_stocks_lignes, data, from, to, 0);

        ListView lv = (ListView) findViewById(R.id.tab_data_listview);
        lv.setAdapter(adapter);

    }


    private void getMarque()
    {
        Cursor data = db.getMarqueData();

        String[] from = new String[] {"Nom"};

        int[] to = new int[] { R.id.marque_formulaireCol_textview};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(context,
                R.layout.activity_stocks_spinner_lignes, data, from, to, 0);


        tubemarque.setAdapter(adapter);


    }


    private void ResetFiltre() {
        btnResetFiltre.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        viewAll();

                    }

                }
        );
    }



    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();

    }


    public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {
        //Récuperation de l'ID après selection de tube

        Cursor cursor= (Cursor)parent.getItemAtPosition(pos);
        Marq = cursor.getString(1);

        viewAll1(Marq);


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //Méthode qui se déclenchera au clic sur un item
    public boolean onOptionsItemSelected(MenuItem item) {
        //On regarde quel item a été cliqué grâce à son id et on déclenche une action
        switch (item.getItemId()) {
            case R.id.menu_achats:

                Intent myIntent = new Intent(this, AchatsActivity.class);
                startActivityForResult(myIntent,0);
                return true;

            case R.id.menu_stocks:

                Intent myIntent1 = new Intent(this, StockActivity.class);
                startActivityForResult(myIntent1,0);
                return true;

            case R.id.menu_formulaire :

                Intent myIntent2 = new Intent(this, FormulaireActivity.class);
                startActivityForResult(myIntent2,0);
                return true;

        }
        return false;
    }
}













