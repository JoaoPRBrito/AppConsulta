package com.example.listviewandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase dataBase;
    public ListView listViewData;
    public TextView resultado;

    public EditText test;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listViewData = (ListView) findViewById(R.id.listViewDatas);

        resultado = findViewById(R.id.listViewDatas);

        buildSqliteDatabase();
        insertDataOnDatabase();
        listViewDatabase();
    }

    public void printDatabase() {

    }

    public void buildSqliteDatabase() {
        try {
            dataBase = openOrCreateDatabase("db_treeinventory", MODE_PRIVATE, null);
            dataBase.execSQL("CREATE TABLE IF NOT EXISTS tree(" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT" +
                             ",ut INTEGER"+
                             ",popular_name VARCHAR)");
            dataBase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listViewDatabase() {
        try {
            dataBase = openOrCreateDatabase("db_treeinventory", MODE_PRIVATE, null);
            Cursor cursor = dataBase.rawQuery("SELECT * FROM tree", null);
            ArrayList<String> dataLine = new ArrayList<String>();

            /*ArrayAdapter adapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    dataLine
            );*/

            //resultado = findViewById(R.id.listViewDatas);

            //listViewData.setAdapter(adapter);
            cursor.moveToFirst();
            while (cursor != null) {
                dataLine.add(cursor.getString(1));
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("DefaultLocale")
    public void insertDataOnDatabase() {
        String file = "java/com/example/listviewandroid/dataframe_f.csv";
        BufferedReader reader = null;

        String line = "";
        String tableName = "tree";
        String columns = "id, ut, popular_name";

        String sql1 = "INSERT INTO " + tableName + " (" + columns + ") VALUES (";
        String sql2 = ");";

        String insertSql;
        insertSql = "INSERT INTO %s (%s) VALUES (%d, %d, %s);";

        List<Integer> treeID = new ArrayList<Integer>();
        List<Integer> treeUT = new ArrayList<Integer>();
        List<String> treePopularName = new ArrayList<String>();

        dataBase.beginTransaction();
        try {
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                String[] valuesOfLine = line.split(",");

                Integer insert_treeID = Integer.valueOf(valuesOfLine[0]);
                Integer insert_treeUT = Integer.valueOf(valuesOfLine[1]);
                String  insert_treePopularName = valuesOfLine[2];

                insertSql = String.format(
                        insertSql, tableName, columns,
                        insert_treeID, insert_treeUT, insert_treePopularName
                );

                dataBase.execSQL(insertSql);
            }
            dataBase.setTransactionSuccessful();
            dataBase.endTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
