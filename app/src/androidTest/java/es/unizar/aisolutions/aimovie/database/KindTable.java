package es.unizar.aisolutions.aimovie.database;

import android.util.Log;
import android.database.sqlite.SQLiteDatabase;

/**
 * KindTable provides methods so as to create and update table called 'TABLE_NAME'
 * in a SQLite database.
 *
 * Created by diego on 2/04/15.
 * Time spent: 11 minutes.
 */
public class KindTable {

    public static final String TABLE_NAME = "kind";
    public static final String PRIMARY_KEY = "_id";
    public static final String COLUMN_FILM_ID = "film";
    public static final String COLUMN_CATEGORIE_ID = "categorie";


    // SQL code to create table called 'TABLE_NAME'
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "( " + PRIMARY_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CATEGORIE_ID + "INTEGER NOT NULL," +
                    COLUMN_FILM_ID + "INTEGER NOT NULL" +
                    COLUMN_CATEGORIE_ID + "references " + CategoriesTable.TABLE_NAME
                    + "(" + CategoriesTable.PRIMARY_KEY + ")," +
                    COLUMN_FILM_ID + "references " + FilmsTable.TABLE_NAME
                    + "(" + FilmsTable.PRIMARY_KEY + ") );";

    // SQL code to create needed triggers
    public static final String CREATE_TRIGGER = "";

    /**
     * onCreate creates the table defined in 'CREATE_TABLE' and the trigger defined in 'CREATE_TRIGGER'
     * @param db = Database where triggers and tables will be created.
     */
    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TRIGGER);
    }

    /**
     * onUpgrade upgrades the database into a newer version.
     * @param db = Database to upgrade.
     * @param oldVersion = Old database version.
     * @param newVersion = New database version.
     */
    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(FilmsTable.class.getName(), "Upgrading database from version " + oldVersion
                + " to " + newVersion + " (all data will be destroyed)");
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
}
