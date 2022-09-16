package com.example.appconsulta;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//vamos usar a instancia dessa classe para ter acesso ao banco
public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    Cursor c = null;

    //construtor privado da classe
    private DatabaseAccess(Context context){
        this.openHelper= new DatabaseOpenHelper(context);

    }

    public static DatabaseAccess getInstance(Context context){
        if(instance==null){
            instance=new DatabaseAccess(context);

        }
        return instance;
    }

    public void open(){
        this.db=openHelper.getWritableDatabase();
    }

    public void close(){
        if(db!=null){
            this.db.close();
        }
    }

    //metodo para retornar o resultado do banco
    // vamos selecionar passando o id

    public String getNomePop(String ID){
        c=db.rawQuery("select Nome_Popular from Table1 where ID_Arvore = '"+ID+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String nomePop = c.getString(0);
            buffer.append(""+nomePop);
        }
        return buffer.toString();

    }

    /*public String getNomePop(String ID){
        c=db.rawQuery("select Nome_Popular from Table1 where ID_Arvore = '"+ID+"'", new String[]{});
        StringBuffer buffer = new StringBuffer();
        while(c.moveToNext()){
            String nomePop = c.getString(0);
            buffer.append(""+nomePop);
        }
        return buffer.toString();

    }*/
}
