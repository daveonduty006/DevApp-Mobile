package com.example.labo2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final Produit[] TAB_PRODUITS = {
            new Produit(1,"Chai Tea",0,90,39),
            new Produit(2,"Chang Tea",0,95,17),
            new Produit(3,"Aniseed Syrup",1,50,13),
            new Produit(4,"Chef Anton's Cajun Seasoning",1,110,53),
            new Produit(5,"Chef Anton's Gumbo Mix",1,106.75,0),
            new Produit(6,"Grandma's Boysenberry Spread",1,125,120),
            new Produit(7,"Uncle Bob's Organic Dried Pears",2,150,15),
            new Produit(8,"Northwood's Cranberry Sauce",1,200,6),
            new Produit(9,"Mishi Kobe Niku",3,485,29),
            new Produit(10,"Ikura",4,155,31)
    };
    private Toolbar myToolbar;
    private ListView listView;
    private PopupWindow popupWindow;
    private TableLayout tableLayout;
    private ImageButton clearBtn;
    private TextView totalTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalTxtView = findViewById(R.id.total_txtview);
        setToolbar();
        setTableLayout();
        remplirTableLayout(TAB_PRODUITS);
        setCategSelectionPopUpWindow();
        setTableClearButton();
    }

    private void showTotal() {
        double inventoryValue = 0;
        for (int i = 1; i < tableLayout.getChildCount(); i++) {
            TableRow row = (TableRow) tableLayout.getChildAt(i);
            TextView quantityView = (TextView) row.getChildAt(4);
            TextView unitPriceView = (TextView) row.getChildAt(3);
            int quantity = Integer.parseInt(quantityView.getText().toString());
            String unitPriceStr = unitPriceView.getText().toString();
            double unitPrice = Double.parseDouble(unitPriceStr.replace("$",""));
            inventoryValue += quantity * unitPrice;
        }
        totalTxtView.setText("La valeur de l'inventaire présentement dans le tableau est de $"+String.format("%.2f", inventoryValue));
    }

    private Produit[] creerSubArraySelonCateg(String categStr) {
        Produit[] tableauFiltre = Arrays.stream(TAB_PRODUITS)
                .filter(p -> categStr.equals(p.getCategString()))
                .toArray(Produit[]::new);
        return tableauFiltre;
    }

    private void remplirTableLayout(Produit[] tableauDeProduits) {
        totalTxtView.setText("");
        for(Produit unProduit : tableauDeProduits) {
            LayoutInflater inflater = LayoutInflater.from(this);
            TableRow newRow = (TableRow) inflater.inflate(R.layout.table_row_layout, null);
            newRow.setBackgroundResource(R.drawable.row_border);
            //
            TextView colId = newRow.findViewById(R.id.id_txtview);
            TextView colNom = newRow.findViewById(R.id.nom_txtview);
            TextView colCategStr = newRow.findViewById(R.id.categStr_txtview);
            TextView colPrix = newRow.findViewById(R.id.prix_txtview);
            TextView colQte = newRow.findViewById(R.id.qte_txtview);
            //
            colId.setText(String.valueOf(unProduit.getId()));
            colNom.setText(unProduit.getNom());
            colCategStr.setText(unProduit.getCategString());
            colPrix.setText("$" + String.format("%.2f", unProduit.getPrix()));
            colQte.setText(String.valueOf(unProduit.getQte()));
            //
            tableLayout.addView(newRow);
        }
    }

    private void setCategSelectionPopUpWindow() {
        View popupView = getLayoutInflater().inflate(R.layout.popup_window_layout, null);
        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //
        listView = popupView.findViewById(R.id.category_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Produit.CODES_CATEG);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String category = (String) parent.getItemAtPosition(position);
                Produit[] tabProduitsFiltre = creerSubArraySelonCateg(category);
                clearBtn.performClick();
                remplirTableLayout(tabProduitsFiltre);
                popupWindow.dismiss();
            }
        });
    }

    private void setTableClearButton() {
        clearBtn = findViewById(R.id.clear_btn);
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalTxtView.setText("");
                int childCount = tableLayout.getChildCount();
                for (int i = childCount - 1; i > 0; i--) { // start from index 1
                    View child = tableLayout.getChildAt(i);
                    if (child instanceof TableRow) {
                        tableLayout.removeView(child); // remove the row
                    }
                }
            }
        });
    }

    private void setTableLayout() {
        tableLayout = findViewById(R.id.table_layout);
        tableLayout.setBackgroundResource(R.drawable.row_border);
    }

    private void setToolbar() {
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // Handle menu item clicks
        if (id == R.id.lister_tab) {
            clearBtn.performClick();
            remplirTableLayout(TAB_PRODUITS);
            return true;
        } else if (id == R.id.categ_tab) {
            popupWindow.showAtLocation(myToolbar, Gravity.CENTER, 0, 0);
            return true;
        } else if (id == R.id.total_tab) {
            showTotal();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}