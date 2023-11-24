package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etTitle;
    EditText etYear;
    SQLiteDatabase db;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    public void onClick(View v) // добавляет записи в таблицу
    {
        int year = 2002; String title = "Harry Potter";
        db.execSQL("INSERT INTO " +
                dbHelper.TABLE_NAME + " VALUES (1,\""+title+"\","+year+")");

        Cursor c = db.rawQuery("SELECT * FROM " + dbHelper.TABLE_NAME, null);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this, R.layout.item, c, c.getColumnNames(),
                new int[]{R.id.id, R.id.title, R.id.year}
        );

        // используя setAdapter()


        String count = Integer.toString(c.getCount());
        Toast.makeText(this, count, Toast.LENGTH_SHORT).show();

    }
}