package com.example.appconsulta;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "MyExternalDatabase.db"; //Nome do arquivo do banco de dados
    private static final int DATABASE_VERSION=1;

    //construtor

    public DatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
}
