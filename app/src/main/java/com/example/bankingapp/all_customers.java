package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class all_customers extends AppCompatActivity {
    LinearLayout ll;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_customers);
        ll= findViewById(R.id.ll);
        SQLiteDatabase sb=openOrCreateDatabase("bankdatabase", MODE_PRIVATE,null);
        sb.execSQL("create table if not exists data(name text, cid text primary key, eid text, balance text)");
        Cursor mCursor = sb.rawQuery("select * from data", null);
        Boolean rowExists;
        if(mCursor.moveToFirst())
            rowExists = true;
        else
            rowExists = false;
        if(!rowExists) {
            sb.execSQL("insert into data values('Ishita Gupta','200104036','im.ishita152@gmail.com','13500')");
            sb.execSQL("insert into data values('Prasoon Yadav','200104044','anoop.gupta2569@gmail.com','13500')");
            sb.execSQL("insert into data values('Ayushi pal','200104020','ayushipal26@gmail.com','135000')");
            sb.execSQL("insert into data values('Sonal Sangvi','200104058','sonalsangvi@gmail.com','23450')");
            sb.execSQL("insert into data values('Anoop Gupta','200104001','anoopgupta2569@gmail.com','269000')");
            sb.execSQL("insert into data values('Richa Gupta','200104072','richa@gmail.com','23789')");
            sb.execSQL("insert into data values('Anshit Agarwal','200104010','anshitagarwal470@gmail.com','23450')");
            sb.execSQL("insert into data values('Aishwarya Tayal','200104018','aishtayal@gmail.com','56789')");
            sb.execSQL("insert into data values('Aditya pal','200104056','adipal@gmail.com','2350')");
            sb.execSQL("insert into data values('Silvi Taneja','200104057','tanejasilvi@gmail.com','85756')");
        }
        query = "select * from data order by name";
        Cursor c = sb.rawQuery(query, null);
        if (c!= null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                Button b = new Button(all_customers.this);
                b.setText(c.getString(1));
                b.setTextSize(10);
                b.setTextColor(getColor(android.R.color.white));
                ll.addView(b);
                b.setOnClickListener(v -> {
                    Intent in = new Intent(all_customers.this, view_customer.class);
                    in.putExtra("row",b.getText());
                    startActivity(in);
                });
            } while (c.moveToNext());
        }
    }
}