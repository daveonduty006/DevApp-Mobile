package com.example.projetexercices;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExerciceDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exercices.db";
    private static final int DATABASE_VERSION = 1;

    private Context context;

    private final String EXERCICE_TABLE = "exercices";
    private final String COL_EXERCICE_ID = "_id";
    private final String COL_EXERCICE_NOM = "nom";
    private final String COL_EXERCICE_CATEG = "categorie";
    private final String COL_EXERCICE_DESC = "description";
    private final String COL_EXERCICE_INSTR = "instructions";
    private final String COL_EXERCICE_VIDEO = "urlVideo";
    private final String COL_EXERCICE_IMAGE = "cheminImage";

    public ExerciceDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ EXERCICE_TABLE + " ("
                + COL_EXERCICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_EXERCICE_NOM   + " TEXT, "
                + COL_EXERCICE_CATEG + " TEXT, "
                + COL_EXERCICE_DESC  + " TEXT, "
                + COL_EXERCICE_INSTR + " TEXT, "
                + COL_EXERCICE_VIDEO + " TEXT, "
                + COL_EXERCICE_IMAGE + " TEXT)";
        db.execSQL(sql);
        if (isDatabaseEmpty(db)) {
            try {
                preInsertExercice(db, nom1, categorie1, description1, instructions1, urlVideo1, R.drawable.crunchs_sol);
                preInsertExercice(db, nom2, categorie2, description2, instructions2, urlVideo2, R.drawable.flexions_poignets_barre);
                preInsertExercice(db, nom3, categorie3, description3, instructions3, urlVideo3, R.drawable.curl_barre);
                preInsertExercice(db, nom4, categorie4, description4, instructions4, urlVideo4, R.drawable.squat);
                preInsertExercice(db, nom5, categorie5, description5, instructions5, urlVideo5, R.drawable.tirages_poitrine);
                preInsertExercice(db, nom6, categorie6, description6, instructions6, urlVideo6, R.drawable.developpes_haltere);
                preInsertExercice(db, nom7, categorie7, description7, instructions7, urlVideo7, R.drawable.mollets_debout);
                preInsertExercice(db, nom8, categorie8, description8, instructions8, urlVideo8, R.drawable.developpes_couche_barre);
                preInsertExercice(db, nom9, categorie9, description9, instructions9, urlVideo9, R.drawable.barre_front);
                preInsertExercice(db, nom10, categorie10, description10, instructions10, urlVideo10, R.drawable.roue_abdominale);
                preInsertExercice(db, nom11, categorie11, description11, instructions11, urlVideo11, R.drawable.developpes_pallof);
                preInsertExercice(db, nom12, categorie12, description12, instructions12, urlVideo12, R.drawable.planche);
                preInsertExercice(db, nom13, categorie13, description13, instructions13, urlVideo13, R.drawable.situp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void preInsertExercice(SQLiteDatabase db, String nom, String categ, String desc, String instr, String url, int idImg) throws IOException {
        // Copy the image from assets to internal storage
        String chemin = copyImageToInternalStorage(idImg);
        ContentValues values = new ContentValues();
        values.put("nom", nom);
        values.put("categorie", categ);
        values.put("description", desc);
        values.put("instructions", instr);
        values.put("urlVideo", url);
        values.put("cheminImage", chemin);
        db.insert(EXERCICE_TABLE, null, values);
    }

    private String copyImageToInternalStorage(int idImg) throws IOException {
        // Get the app's internal storage directory
        File directory = this.context.getFilesDir();
        Drawable drawable;
        Bitmap bitmap;
        String fileName;
        File file;
        FileOutputStream fos;
        drawable = ContextCompat.getDrawable(this.context, idImg);
        bitmap = ((BitmapDrawable) drawable).getBitmap();
        fileName = this.context.getResources().getResourceEntryName(idImg) + ".png"; // add file extension
        file = new File(directory, fileName);
        fos = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        fos.close();
        return file.getAbsolutePath();
    }

    private boolean isDatabaseEmpty(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + EXERCICE_TABLE, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EXERCICE_TABLE);
        onCreate(db);
    }

    ///////// CRUD

    public List<Exercice> getAll() {
        List<Exercice> exercices = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXERCICE_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            do {
                long id = cursor.getLong(0);
                String nom = cursor.getString(1);
                String categorie = cursor.getString(2);
                String description = cursor.getString(3);
                String instructions = cursor.getString(4);
                String urlVideo = cursor.getString(5);
                String cheminImage = cursor.getString(6);
                //
                Exercice exercice = new Exercice(id, nom, categorie, description, instructions, urlVideo, cheminImage);
                exercices.add(exercice);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exercices;
    }

    public List<Exercice> getAllByCateg(String categorie) {
        List<Exercice> exercices = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXERCICE_TABLE + " WHERE " + COL_EXERCICE_CATEG + " = '" + categorie + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            do {
                long id = cursor.getLong(0);
                String nom = cursor.getString(1);
                categorie = cursor.getString(2);
                String description = cursor.getString(3);
                String instructions = cursor.getString(4);
                String urlVideo = cursor.getString(5);
                String cheminImage = cursor.getString(6);
                //
                Exercice exercice = new Exercice(id, nom, categorie, description, instructions, urlVideo, cheminImage);
                exercices.add(exercice);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exercices;
    }

    public Exercice getOne(long id) {
        Exercice exercice = new Exercice();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + EXERCICE_TABLE + " WHERE " + COL_EXERCICE_ID + " = '" + id + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) { // we move the cursor over the first row of its attached result set
            do {
                String nom = cursor.getString(1);
                String categorie = cursor.getString(2);
                String description = cursor.getString(3);
                String instructions = cursor.getString(4);
                String urlVideo = cursor.getString(5);
                String cheminImage = cursor.getString(6);
                //
                exercice.setId(id);
                exercice.setNom(nom);
                exercice.setCategorie(categorie);
                exercice.setDescription(description);
                exercice.setInstructions(instructions);
                exercice.setUrlVideo(urlVideo);
                exercice.setCheminImage(cheminImage);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return exercice;
    }

    public boolean addOne(Exercice exercice) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO " + EXERCICE_TABLE + " ("
                + COL_EXERCICE_NOM    + ","
                + COL_EXERCICE_CATEG  + ","
                + COL_EXERCICE_DESC   + ","
                + COL_EXERCICE_INSTR  + ","
                + COL_EXERCICE_VIDEO  + ","
                + COL_EXERCICE_IMAGE + ") VALUES (?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, exercice.getNom());
        statement.bindString(2, exercice.getCategorie());
        statement.bindString(3, exercice.getDescription());
        statement.bindString(4, exercice.getInstructions());
        statement.bindString(5, exercice.getUrlVideo());
        statement.bindString(6, exercice.getCheminImage());
        long newRowId = statement.executeInsert(); // return -1 if no row was added
        statement.close();
        db.close();
        return newRowId > -1;
    }

    public boolean updateOne(Exercice exercice) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + EXERCICE_TABLE + " SET "
                + COL_EXERCICE_NOM +   " = ?, "
                + COL_EXERCICE_CATEG + " = ?, "
                + COL_EXERCICE_DESC +  " = ?, "
                + COL_EXERCICE_INSTR + " = ?, "
                + COL_EXERCICE_VIDEO + " = ?, "
                + COL_EXERCICE_IMAGE + " = ? WHERE "
                + COL_EXERCICE_ID +    " = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindString(1, exercice.getNom());
        statement.bindString(2, exercice.getCategorie());
        statement.bindString(3, exercice.getDescription());
        statement.bindString(4, exercice.getInstructions());
        statement.bindString(5, exercice.getUrlVideo());
        statement.bindString(6, exercice.getCheminImage());
        statement.bindLong(7, exercice.getId());
        int numRowsAffected = statement.executeUpdateDelete(); // return 0 if no row was affected
        statement.close();
        db.close();
        return numRowsAffected > 0;
    }

    public boolean deleteOne(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + EXERCICE_TABLE + " WHERE " + COL_EXERCICE_ID + " = ?";
        SQLiteStatement statement = db.compileStatement(query);
        statement.bindLong(1, id);
        int numRowsAffected = statement.executeUpdateDelete(); // return 0 if no row was affected
        statement.close();
        db.close();
        return numRowsAffected > 0;
    }

    /////////

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
    //
    String nom3 = "Curls avec la barre";
    String categorie3 = "Biceps";
    String description3 = "Cet exercice de musculation sollicite et développe les biceps. Le curl barre est l’exercice d’isolation de base pour les" +
            " biceps.";
    String instructions3 = "En position de départ debout, le dos immobile et droit, les genoux fléchis ou une jambe avancée pour éviter de tricher " +
            "en s'aidant de l'élan et les coudes prés du corps. Monter et descendre la barre sans à-coups. Vous pouvez varier l'écartement des " +
            "mains en utilisant une prise large, moyenne ou serrée.";
    String urlVideo3 = "https://www.youtube.com/watch?v=ZLRBO5wiPwM";
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
    //
    String nom13 = "Situp";
    String categorie13 = "Abdominaux";
    String description13 = "Cet exercice de musculation raffermit la taille et muscle les abdominaux. Il a la réputation de travailler le bas du " +
            "ventre, là où le crunch au sol sollicite plutôt le haut des abdominaux.";
    String instructions13 = "Assis au travers du banc ou sur un tapis de sol, mains au bord du banc pour l'équilibre, tendre les jambes puis " +
            "ramener les genoux vers la poitrine. Garder les abdos sous tension pendant le mouvement.";
    String urlVideo13 = "https://www.youtube.com/watch?v=nKFxkSzYr80";

}
