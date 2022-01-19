package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class view_customer extends AppCompatActivity {
    TextView tv1, tv2, tv3;
    Button b;
    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
        tv1= findViewById(R.id.vc_tv1);
        tv2= findViewById(R.id.vc_tv2);
        tv3= findViewById(R.id.vc_tv3);
        b=findViewById(R.id.vc_b);
        Intent intent=this.getIntent();
        String find_cid=intent.getStringExtra("row");
        SQLiteDatabase sb=openOrCreateDatabase("bankdatabase", MODE_PRIVATE,null);
        String query = "select * from data where cid='"+find_cid+"'";
        Cursor c=sb.rawQuery(query,null);
        if (c!= null && c.getCount() > 0)
        {
            c.moveToFirst();
            do {
                tv1.setText(c.getString(0));
                tv2.setText(c.getString(1));
                val= c.getString(1);
                tv3.setText(c.getString(3));
            }while(c.moveToNext());
        }

        b.setOnClickListener(v -> {
            Intent intent1= new Intent(view_customer.this, transfer_money.class);
            intent1.putExtra("donor_cid",val);
            startActivity(intent1);
        });
    }
}