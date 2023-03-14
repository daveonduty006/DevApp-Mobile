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

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.File;;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MyCategSelectListener {

    private final static HashMap<String,Integer> CATEGORIES = new HashMap(Map.of(
            "Abdominaux", R.drawable.abdominaux,
            "Avant-Bras", R.drawable.avant_bras,
            "Biceps", R.drawable.biceps,
            "Cuisses", R.drawable.cuisses,
            "Dos", R.drawable.dos,
            "Épaules", R.drawable.epaules,
            "Mollets", R.drawable.mollets,
            "Pectoraux", R.drawable.pectoraux,
            "Triceps", R.drawable.triceps
    ));

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar myToolbar;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
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
        String nom10 = "Roue Abdominale";
        String categorie10 = "Abdominaux";
        String description10 = "La roue abdominale est un accessoire peu onéreux qui permet d'effectuer des mouvements très efficaces pour muscler les" +
                " abdominaux, améliorer la posture et la stabilité du corps, si l'on sait comment bien l'utiliser.";
        String instructions10 = "Positionnez-vous genoux au sol et saisissez votre roue abdominale avec vos deux mains pour la placer au niveau de vos" +
                " genoux. En conservant les bras et les genoux serrés, et en gardant votre corps stable, déplacez la roue devant vous.\n" +
                "\n" + " Concentrez-vous sur votre sangle abdominale. Puis, tirer la roue à vous pour revenir dans votre position de départ. Plus " +
                "l'amplitude sera grande, plus l'exercice sera difficile et sollicitera vos abdominaux.";
        String urlVideo10 = "https://www.youtube.com/watch?v=dHgzIVPlk6Q";
        File file10 = new File(getFilesDir(), "roue_abdominale.png");
        String cheminImage10 = file10.getAbsolutePath();
        //
        String nom11 = "Développés Pallof";
        String categorie11 = "Abdominaux";
        String description11 = "Et non, il ne s’agit pas d’un exercice pour la poitrine ou les épaules. Cette fois-ci, ce sont les abdominaux qui s’y" +
                " collent ! Cet exercice qui porte le nom du physiothérapeute John Pallof a l’air au premier abord assez facile. Mais détrompez-vous, " +
                "il est plus difficile qu’il n’y parait !";
        String instructions11 = "Debout, pieds écartés de la largeur des épaules, les genoux légèrement fléchis. Développez le câble jusqu’à " +
                "l’extension complète des bras. Maintenir la position bras tendus pendant quelques secondes tout en gardant les abdominaux bien " +
                "contractés - important - comme si on allait vous mettre un coup de poing dans le ventre.\n" +
                "\n" + " Faites entre 5 et 10 répétitions par série en veillant à changer de côté. Si vous ne parvenez pas à maintenir votre corps " +
                "immobile et droit, c’est que la charge utilisée est trop lourde.";
        String urlVideo11 = "https://www.youtube.com/watch?v=YpvbR_Ah8_g";
        File file11 = new File(getFilesDir(), "developpes_pallof.png");
        String cheminImage11 = file11.getAbsolutePath();
        //
        String nom12 = "Planche";
        String categorie12 = "Abdominaux";
        String description12 = "Le gainage face au sol, aussi appelé « planche » est un exercice isométrique, c'est-à-dire qu'il se pratique de façon" +
                " statique. Le but est de maintenir une position pendant un certain temps en utilisant les muscles posturaux pour verrouiller les " +
                "articulations. C'est un exercice surtout utilisé pour renforcer les muscles profonds de l'abdomen, obtenir un ventre plus plat et " +
                "réduire les risques de blessure d'un entraînement classique.";
        String instructions12 = "Le gainage de face est aussi connu sous le nom de « planche », tout simplement parce qu'il s'agit de former une sorte" +
                " de planche avec le corps et de conserver cette position un certain temps.\n" +
                "\n" + "Pour commencer, il faut se positionner face au sol, si possible sur un tapis pour éviter des douleurs aux coudes. Vous devez " +
                "vous placer ventre en direction du sol, en équilibre sur les coudes et les pointes de pieds. Votre corps forme ainsi la fameuse " +
                "planche.\n" +
                "\n" + "En fait, le corps ne doit pas être tout à fait droit. Pour éviter de cambrer et forcer sur le bas du dos, on place le bassin " +
                "en rétroversion (en arrière), ce qui permet d'effacer la lordose lombaire. Vos bras et vos avant-bras doivent normalement conserver un" +
                " angle de 90 degrés durant votre exercice. On pousse sur les coudes pour arrondir la partie haute de votre dos et préserver " +
                "l’articulation de vos épaules. Au niveau de la respiration, on expire en rentrant le ventre avec pour objectif de tonifier le muscle " +
                "transverse.\n" +
                "\n" + "La tête peut être droite ou regarder les pieds afin d'éviter les tensions dans la nuque.";
        String urlVideo12 = "https://www.youtube.com/watch?v=GhR1YszAldQ";
        File file12 = new File(getFilesDir(), "planche.png");
        String cheminImage12 = file12.getAbsolutePath();
        //
        String nom13 = "Situp";
        String categorie13 = "Abdominaux";
        String description13 = "Cet exercice de musculation raffermit la taille et muscle les abdominaux. Il a la réputation de travailler le bas du " +
                "ventre, là où le crunch au sol sollicite plutôt le haut des abdominaux.";
        String instructions13 = "Assis au travers du banc ou sur un tapis de sol, mains au bord du banc pour l'équilibre, tendre les jambes puis " +
                "ramener les genoux vers la poitrine. Garder les abdos sous tension pendant le mouvement.";
        String urlVideo13 = "https://www.youtube.com/watch?v=nKFxkSzYr80";
        File file13 = new File(getFilesDir(), "situp.png");
        String cheminImage13 = file13.getAbsolutePath();
        //
        ExerciceDbHelper exerciceDbHelper = new ExerciceDbHelper(this);
        List<Exercice> exercices = new ArrayList<>();
        exercices.add(new Exercice(-1, nom1, categorie1, description1, instructions1, urlVideo1, cheminImage1));
        exercices.add(new Exercice(-1, nom2, categorie2, description2, instructions2, urlVideo2, cheminImage2));
        exercices.add(new Exercice(-1, nom3, categorie3, description3, instructions3, urlVideo3, cheminImage3));
        exercices.add(new Exercice(-1, nom4, categorie4, description4, instructions4, urlVideo4, cheminImage4));
        exercices.add(new Exercice(-1, nom5, categorie5, description5, instructions5, urlVideo5, cheminImage5));
        exercices.add(new Exercice(-1, nom6, categorie6, description6, instructions6, urlVideo6, cheminImage6));
        exercices.add(new Exercice(-1, nom7, categorie7, description7, instructions7, urlVideo7, cheminImage7));
        exercices.add(new Exercice(-1, nom8, categorie8, description8, instructions8, urlVideo8, cheminImage8));
        exercices.add(new Exercice(-1, nom9, categorie9, description9, instructions9, urlVideo9, cheminImage9));
        exercices.add(new Exercice(-1, nom10, categorie10, description10, instructions10, urlVideo10, cheminImage10));
        exercices.add(new Exercice(-1, nom11, categorie11, description11, instructions11, urlVideo11, cheminImage11));
        exercices.add(new Exercice(-1, nom12, categorie12, description12, instructions12, urlVideo12, cheminImage12));
        exercices.add(new Exercice(-1, nom13, categorie13, description13, instructions13, urlVideo13, cheminImage13));
        for(Exercice unExercice : exercices){
            exerciceDbHelper.addOne(unExercice);
        }
        //
        */
        setMyToolbar();
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
                    Toast.makeText(MainActivity.this, "navItem1", Toast.LENGTH_SHORT).show();
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
        File directory = this.getFilesDir();
        int idDrawable;
        Drawable drawable;
        Bitmap bitmap;
        String fileName;
        File file;
        FileOutputStream fos;
        for(int i=0; i < CATEGORIES.size(); i++){
            idDrawable = CATEGORIES.get(CATEGORIES.keySet().toArray()[i]);
            drawable = ContextCompat.getDrawable(this, idDrawable);
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            fileName = getResources().getResourceEntryName(idDrawable) + ".png"; // add file extension
            file = new File(directory, fileName);
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        }
        /*     COMPRESSION ET STOCKAGE DES IMAGES DES EXERCICES DE TEST DANS LE STORAGE INTERNE DE L'APP
        ArrayList<Integer> drawableIds = new ArrayList<>();
        drawableIds.add(R.drawable.curl_barre);
        drawableIds.add(R.drawable.barre_front);
        drawableIds.add(R.drawable.crunchs_sol);
        drawableIds.add(R.drawable.developpes_couche_barre);
        drawableIds.add(R.drawable.flexions_poignets_barre);
        drawableIds.add(R.drawable.squat);
        drawableIds.add(R.drawable.tirages_poitrine);
        drawableIds.add(R.drawable.developpes_haltere);
        drawableIds.add(R.drawable.mollets_debout);
        drawableIds.add(R.drawable.situp);
        drawableIds.add(R.drawable.planche);
        drawableIds.add(R.drawable.developpes_pallof);
        drawableIds.add(R.drawable.roue_abdominale);
        for(int i=0; i < drawableIds.size(); i++){
            idDrawable = drawableIds.get(i);
            drawable = ContextCompat.getDrawable(this, idDrawable);
            bitmap = ((BitmapDrawable) drawable).getBitmap();
            fileName = getResources().getResourceEntryName(idDrawable) + ".png"; // add file extension
            file = new File(directory, fileName);
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        }
        */
        myRecyclerView.setAdapter(new MyCategListAdapter(getApplicationContext(), CATEGORIES, this));
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

    @Override
    public void onCategBtnClicked(String categorie) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("categorie", categorie);
        startActivity(intent);
    }
}