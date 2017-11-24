package com.example.jmarcel.shuttlesmgmt.Controlleur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.jmarcel.shuttlesmgmt.R;


public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

