package br.senac.agenda.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "agenda.senac.db";

    private static final Integer DB_VERSION = 4;

    private final String DB_CREATE = "CREATE TABLE CONTATO(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NOME TEXT," +
            "TELEFONE TEXT," +
            "PONTUACAO REAL);";

    private final String DB_ENDERECO = "CREATE TABLE ENDERECO(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "RUA TEXT," +
            "NUMERO TEXT," +
            "CIDADE TEXT);";


    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= 4) {
            db.execSQL(DB_ENDERECO);
        }
    }
}
