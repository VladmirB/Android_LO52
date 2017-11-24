package com.example.vladmir.sqlite_proof.Controlleur;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


import com.example.vladmir.sqlite_proof.Modele.Categorie;
import com.example.vladmir.sqlite_proof.Modele.Client;
import com.example.vladmir.sqlite_proof.Modele.Distributeur;
import com.example.vladmir.sqlite_proof.Modele.Facture;
import com.example.vladmir.sqlite_proof.Modele.Tube_Volant;
import com.example.vladmir.sqlite_proof.R;


public class StockActivity extends AppCompatActivity {
    private MySQLiteHelper db;

    //EditText nomInput,auteurInput,categorieInput;
    Button  viewAchats,viewFormulaire;
    ListView list ;
    Context context;
    public static final int ACHATS_ACTIVITY_REQUEST_CODE = 42;
    public static final int FORMULAIRE_ACTIVITY_REQUEST_CODE= 43;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);
        context = this;
        db = new MySQLiteHelper(this);

        list = (ListView)findViewById(R.id.tab_data_listview);
        viewAchats = (Button) findViewById(R.id.achat_button);
        viewFormulaire = (Button) findViewById(R.id.formulaire_button);
        //list.setAdapter(new ArrayAdapter<String>(this,R.layout.categories, categories));

        //addData = (Button) findViewById(R.id.ajouter_button);
        Achats();
        Formulaire();
        //ajoutData();

        viewAll();

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
                                R.layout.activity_lignes_database, data, from, to, 0);

                        ListView lv = (ListView) findViewById(R.id.tab_data_listview);
                        lv.setAdapter(adapter);

    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();

    }

    public void Achats(){
        viewAchats.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent AchatsActivity = new Intent(StockActivity.this, AchatsActivity.class);
                        startActivityForResult(AchatsActivity,ACHATS_ACTIVITY_REQUEST_CODE);

                    }

                }
        );

    }
    public void Formulaire(){
        viewFormulaire.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent FormulaireActivity = new Intent(StockActivity.this, FormulaireActivity.class);
                        startActivityForResult(FormulaireActivity,FORMULAIRE_ACTIVITY_REQUEST_CODE);

                    }

                }
        );

    }

}
