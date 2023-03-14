package com.example.projetexercices;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<Exercice> exercices;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar myToolbar;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //
        String nom1 = "Crunchs au sol";
        String categorie1 = "Abdominaux";
        String description1 = "Le crunch est un exercice simple et efficace pour muscler les abdominaux. Il affine et raffermit la taille si vous " +
                "travaillez avec le poids du corps, et développe les abdominaux si vous utilisez un lest de plus en plus lourd. Il ne nécessite pas " +
                "de matériel et peut être réalisé n’importe où.";
        String instructions1 = "Position de départ allongé sur le sol ou sur un banc. Les mains peuvent être placées sur la tête au niveau des " +
                "tempes, sur la poitrine, ou encore le long du corps (plus facile). Evitez de les positionner derrière la nuque.\n" +
                "\n" + "Les pieds peuvent être posés sur le sol, près des fesses, ou reposer sur un banc. On peut aussi placer les cuisses à la " +
                "verticale, genoux fléchis et écartés, pieds croisés, de sorte à ne pas cambrer le bas du dos lors du mouvement. Attention, plus " +
                "les jambes sont surélevées voire tendues, plus la difficulté augmente.\n" +
                "\n" + "Enrouler le buste vers l’avant en contractant les abdominaux. Les épaules ne décollent que de quelques centimètres du sol," +
                " et le bas du dos et les hanches restent fixes.";
        String urlVideo1 = "https://www.youtube.com/watch?v=zUk1BiL6Ajc";
        File file1 = new File(getFilesDir(), "crunchs_sol.png");
        String cheminImage1 = file1.getAbsolutePath();
        //
        String nom2 = "Flexions des poignets avec la barre";
        String categorie2 = "Avant-Bras";
        String description2 = "La flexion des poignets à la barre est le mouvement le plus pratiqué pour travailler les muscles fléchisseurs du " +
                "poignet qui sont sollicités dans tous les exercices où il est question d'empoigner des haltères ou une barre. C'est dire leur " +
                "importance.";
        String instructions2 = "Les flexions des poignets avec la barre se pratiquent en position assise ou à genou. Saisissez-vous d'une barre " +
                "droite, les mains en position de supination, c’est-à-dire avec les pouces orientés vers l'extérieur. Posez vos avant-bras sur vos " +
                "cuisses ou sur un banc, de façon à ce que vos mains en dépassent et pendent dans le vide. Reculez-vous de façon à ce que vos bras ne " +
                "soient pas à 90 degrés, mais un peu plus tendus. Levez la barre le plus haut possible en fléchissant les poignets.\n" +
                "\n" + "Maintenez la contraction maximale pendant une seconde puis redescendez en contrôlant le mouvement, jusqu'à ce que vos poignets " +
                "soient en hyper-extension. Répétez le mouvement jusqu'à l'accomplissement de votre série.";
        String urlVideo2 = "https://www.youtube.com/watch?v=dK76O_TtHEo";
        File file2 = new File(getFilesDir(), "flexions_poignets_barre.png");
        String cheminImage2 = file2.getAbsolutePath();
        //
        String nom3 = "Curls avec la barre";
        String categorie3 = "Biceps";
        String description3 = "Cet exercice de musculation sollicite et développe les biceps. Le curl barre est l’exercice d’isolation de base pour les" +
                " biceps.";
        String instructions3 = "En position de départ debout, le dos immobile et droit, les genoux fléchis ou une jambe avancée pour éviter de tricher " +
                "en s'aidant de l'élan et les coudes prés du corps. Monter et descendre la barre sans à-coups. Vous pouvez varier l'écartement des " +
                "mains en utilisant une prise large, moyenne ou serrée.";
        String urlVideo3 = "https://www.youtube.com/watch?v=ZLRBO5wiPwM";
        File file3 = new File(getFilesDir(), "curl_barre.png");
        String cheminImage3 = file3.getAbsolutePath();
        //
        String nom4 = "Squats";
        String categorie4 = "Cuisses";
        String description4 = "Cet exercice de musculation sollicite et développe l’ensemble du corps mais vise principalement les muscles des cuisses " +
                "et les fessiers. C'est l'exercice roi de la musculation. « Ceux qui ne font pas de squat, ne font pas de musculation » est une " +
                "expression qu'on entend souvent de la bouche des culturistes. Assez technique, il demande un bon équilibre et une certaine souplesse. " +
                "Efficace et rentable, il ne convient pas à toutes les morphologies qui tireront plus de bénéfices d'autres exercices.";
        String instructions4 = "Debout, la barre reposant sur les trapèzes, mains espacées de la largeur des épaules ou plus, pieds en canard - à dix " +
                "heure dix - légèrement plus écartés que la largeur des épaules et placés dans l’axe des genoux. Fléchir les genoux et pousser les " +
                "fesses en arrière avec le buste droit. Descendre théoriquement jusqu'à ce que vos cuisses soient parallèle au sol. Limitez l’amplitude" +
                " si vous sentez que vous allez arrondir le dos ou si vos talons décollent du sol. La barre descend et remonte verticalement, sans " +
                "arrêts en position haute et rebonds en position basse. L'équilibre peut être difficile au début, il faut du temps pour maîtriser " +
                "l'exercice.";
        String urlVideo4 = "https://www.youtube.com/watch?v=fypQ8tQ1OP0";
        File file4 = new File(getFilesDir(), "squat.png");
        String cheminImage4 = file4.getAbsolutePath();
        //
        String nom5 = "Tirages de la poitrine";
        String categorie5 = "Dos";
        String description5 = "Cet exercice de musculation sollicite les muscles du dos au niveau de la largeur. Le travail à la poulie haute permet à " +
                "ceux qui ne sont pas encore capables de faire des tractions sur la barre fixe avec le poids du corps, d’exercer les dorsaux.";
        String instructions5 = "Debout, prendre la barre de traction avec une prise plus grande que la largeur d'épaules. Descendre la barre et " +
                "s’asseoir sur le siège de la machine en mettant les genoux sous les boudins. Pencher le buste un peu en arrière en cambrant le bas du " +
                "dos et amener la barre au dessus de la poitrine en contractant fortement les dorsaux. Revenir lentement à la position de départ.\n" +
                "\n" + "Il faut veiller a ce que le buste ne bouge pas pendant l’exécution de l’exercice.";
        String urlVideo5 = "https://www.youtube.com/watch?v=9fEGXlAOC8g";
        File file5 = new File(getFilesDir(), "tirages_poitrine.png");
        String cheminImage5 = file5.getAbsolutePath();
        //
        String nom6 = "Développés avec haltères";
        String categorie6 = "Épaules";
        String description6 = "Cet exercice de musculation sollicite les muscles des épaules, et indirectement les triceps – à l'arrière des bras – et" +
                " le haut des pectoraux. C’est un exercice de base, idéal pour prendre de la masse, de l'épaisseur et construire des épaules massives.";
        String instructions6 = "Debout, les deux haltères à la main, s’asseoir en posant les haltères sur les cuisses. Cela permet d’éviter le les " +
                "ramasser à terre avec tous les risques que cela comporte. Position de départ assis sur le banc, les pieds bien écartés, les haltères" +
                " au niveau des épaules avec les mains en pronation. Développer les haltères puis revenir lentement à la position de départ, en " +
                "freinant la descente.";
        String urlVideo6 = "https://www.youtube.com/watch?v=L0z13X2C0UU";
        File file6 = new File(getFilesDir(), "developpes_haltere.png");
        String cheminImage6 = file6.getAbsolutePath();
        //
        String nom7 = "Mollets debout";
        String categorie7 = "Mollets";
        String description7 = "Cet exercice de musculation renforce et développe les mollets, plus particulièrement les jumeaux tout au-dessus, du " +
                "fait de la position james tendues. Les jumeaux sont constitués de fibres lentes et de fibres rapides, il faudra donc les travailler " +
                "en séries longues et courtes. Il est préférable de commencer sa séance de mollets par les exercices de musculation comme les mollets " +
                "debout et de finir par les exercices assis.";
        String instructions7 = "On peut réaliser cet exercice de musculation debout sur une seule jambe ou sur une machine spécifique pour les " +
                "mollets. Debout, maintenir l'équilibre avec une main, la pointe du pied sur la cale. Monter le plus haut possible en contractant fort" +
                " et redescendre le plus bas possible. Ne pas plier le genou pour donner une impulsion. Eviter également de travailler les jambes " +
                "complètement tendues. Si nécessaire, utiliser un lest pour ajouter de la difficulté à l'exercice.\n" +
                "\n" + " Pour la version sur la machine à mollets, régler le poids et se placer sous les boudins. Soulever la charge, position jambes" +
                " presque tendues. Monter et descendre en contractant bien les mollets en position haute. Reposer la charge en gardant le dos bien droit.";
        String urlVideo7 = "https://www.youtube.com/watch?v=L_A3pFgYcA8";
        File file7 = new File(getFilesDir(), "mollets_debout.png");
        String cheminImage7 = file7.getAbsolutePath();
        //
        String nom8 = "Développés couchés avec la barre";
        String categorie8 = "Pectoraux";
        String description8 = "Cet exercice de musculation très populaire développe la poitrine. Le développe couché est un exercice de base qui fait " +
                "intervenir deux articulations (coude et épaule) et qui permet de travailler l'ensemble du buste, pas seulement les pectoraux. C'est un" +
                " des mouvement qui permet d'évaluer la force musculaire des membres supérieurs et qui est utilisé dans les épreuves de force athlétique.";
        String instructions8 = "Position de départ allongé sur le banc de développé couché. La barre posée sur les supports se trouve au niveau des yeux" +
                ". Placer les mains sur la barre, en pronation, écartées d'une distance légèrement supérieur à la largeur des épaules. Fléchir les " +
                "jambes et les rabattre pour avoir les lombaires bien collés au banc et eviter de cambrer. Vous pouvez aussi poser les pieds sur le bord" +
                " du banc ou à plat sur le sol pour plus d'équilibre mais ne levez pas les fesses en poussant.\n" +
                "\n" + " Décrocher la barre des supports et l'immobiliser bras tendus. Elle se trouve alors au niveau du haut des pectoraux. Descendre" +
                " la charge lentement, en la contrôlant, jusqu'à frôler les pectoraux (mamelons). Revenir à la position initiale.\n" +
                "\n" + " A noter que le mouvement du développé couché n'est pas strictement vertical. A la fin, reposer la barre doucement sur les " +
                "supports avec l'aide du partenaire.";
        String urlVideo8 = "https://www.youtube.com/watch?v=1_k-ChdhzOo";
        File file8 = new File(getFilesDir(), "developpes_couche_barre.png");
        String cheminImage8 = file8.getAbsolutePath();
        //
        String nom9 = "Barre-Front";
        String categorie9 = "Triceps";
        String description9 = "Cet exercice de musculation développe la masse et la force des triceps, à l'arrière des bras. Dans le jargon de la " +
                "musculation, on le surnomme « barre-front » du fait qu'on amène la barre au niveau du front durant la phase négative du mouvement. " +
                "Il consiste à faire des extensions à la barre droite ou à la barre EZ (plus confortable pour les coudes et les poignets), couché sur " +
                "un banc ou allongé sur le sol.";
        String instructions9 = "Position de départ allongé sur le sol ou sur un banc, les bras tendus. Les jambes peuvent être bloquées ou fléchies sur " +
                "la poitrine pour éviter de cambrer trop le bas du dos. Prendre une barre droite ou coudée, prise de la largeur des épaules, les mains " +
                "en pronation.\n" +
                "\n" + " Amener la barre au-dessus de la tête en tendant les bras. Ensuite, fléchir les bras jusqu'à effleurer le front avec la barre, " +
                "en veillant à garder les coudes immobiles et les bras serrés (les bras ne doivent pas s'écarter l'un de l'autre). Pousser presque " +
                "jusqu'à l'extension complète.\n" +
                "\n" + " Le mouvement décrit un quart d'arc de cercle et seuls les avants bras bougent durant l'exercice.";
        String urlVideo9 = "https://www.youtube.com/watch?v=-a4FR3zmdJ8";
        File file9 = new File(getFilesDir(), "barre_front.png");
        String cheminImage9 = file9.getAbsolutePath();
        //
        ArrayList<Exercice> exercices = new ArrayList<>();
        exercices.add(new Exercice(-1, nom1, categorie1, description1, instructions1, urlVideo1, cheminImage1));
        exercices.add(new Exercice(-1, nom2, categorie2, description2, instructions2, urlVideo2, cheminImage2));
        exercices.add(new Exercice(-1, nom3, categorie3, description3, instructions3, urlVideo3, cheminImage3));
        exercices.add(new Exercice(-1, nom4, categorie4, description4, instructions4, urlVideo4, cheminImage4));
        exercices.add(new Exercice(-1, nom5, categorie5, description5, instructions5, urlVideo5, cheminImage5));
        exercices.add(new Exercice(-1, nom6, categorie6, description6, instructions6, urlVideo6, cheminImage6));
        exercices.add(new Exercice(-1, nom7, categorie7, description7, instructions7, urlVideo7, cheminImage7));
        exercices.add(new Exercice(-1, nom8, categorie8, description8, instructions8, urlVideo8, cheminImage8));
        exercices.add(new Exercice(-1, nom9, categorie9, description9, instructions9, urlVideo9, cheminImage9));
        //
        setMyToolbar();
        setIntentProcessing();
        try {
            setMyRecyclerView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setMyDrawerLayout();
    }

    private void setMyNavigationView() {
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                // Handle navigation view item clicks here
                if (id == R.id.nav_item1) {
                    Toast.makeText(SecondActivity.this, "navItem1", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.nav_item2) {
                    // Handle item 2 click
                } else if (id == R.id.nav_setting1) {
                    // Handle setting 1 click
                } else if (id == R.id.nav_setting2) {
                    // Handle setting 2 click
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void setMyDrawerLayout() {
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setMyNavigationView();
    }

    private void setMyRecyclerView() throws IOException {
        myRecyclerView = findViewById(R.id.my_recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        myRecyclerView.setLayoutManager(gridLayoutManager);
        //
        ArrayList<Exercice> exercices = new ArrayList<>();
        File directory = this.getFilesDir();
        int idDrawable;
        Drawable drawable;
        Bitmap bitmap;
        String fileName;
        File file;
        FileOutputStream fos;
        for(int i=0; i < 7; i++) {
            /*
            idDrawable = CATEGORIES.get(CATEGORIES.keySet().toArray()[i]);
            drawable = ContextCompat.getDrawable(this, idDrawable);
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            fileName = getResources().getResourceEntryName(idDrawable) + ".png"; // add file extension
            file = new File(directory, fileName);
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        }
        myRecyclerView.setAdapter(new MyCategListAdapter(getApplicationContext(), CATEGORIES, this));
             */
        }
    }

    private void setIntentProcessing() {
        String categorie = getIntent().getStringExtra("categorie");
        switch(categorie) {
            case "Abdominaux":
                break;
            case "Avant-Bras":
                break;
            case "Biceps":
                break;
            case "Cuisses":
                break;
            case "Dos":
                break;
            case "Pectoraux":
                break;
            case "Triceps":
                break;
        }
    }

    private void setMyToolbar() {
        myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_bg, getTheme()));
        setSupportActionBar(myToolbar);
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
        if (id == R.id.listerCateg_tab) {
            Toast.makeText(this, "allo", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == android.R.id.home) {
            DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}