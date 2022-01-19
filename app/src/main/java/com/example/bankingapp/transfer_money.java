package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class transfer_money extends AppCompatActivity {
LinearLayout ll;
Button b1,b2;
EditText et1;
TextView tv1,tv2;
float bal=0,cbal=0,dbal=0;
String bal1, r_cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_money);
        b1=findViewById(R.id.tm_b1);
        b2=findViewById(R.id.tm_b2);
        et1=findViewById(R.id.tm_et1);
        tv2=findViewById(R.id.tm_tv2);
        ll=findViewById(R.id.tm_ll);
        tv1=findViewById(R.id.tm_tv1);
        Intent intent2= this.getIntent();
        r_cid= intent2.getStringExtra("donor_cid");
        tv1.setText(r_cid);
        b1.setOnClickListener(v -> {
            SQLiteDatabase sb=openOrCreateDatabase("bankdatabase", MODE_PRIVATE,null);
            String query = "select * from data order by name";
            Cursor c = sb.rawQuery(query, null);
            if (c!= null && c.getCount() > 0) {
                c.moveToFirst();
                do {
                    Button b = new Button(transfer_money.this);
                    b.setText(c.getString(1));
                    b.setTextSize(10);
                    ll.addView(b);
                    b.setOnClickListener(view -> {
                        tv2.setText(b.getText().toString());
                        ll.removeAllViews();
                    });
                } while (c.moveToNext());
            }
        });

        b2.setOnClickListener(v -> {
            float transaction =Float.parseFloat(et1.getText().toString());
            SQLiteDatabase sb=openOrCreateDatabase("bankdatabase",MODE_PRIVATE,null);
            String query="select * from data where cid='"+r_cid+"'";
            Cursor c=sb.rawQuery(query,null);
            if (c!= null && c.getCount() > 0) {
                c.moveToFirst();
                do {
                    bal1=c.getString(3);
                }while(c.moveToNext());
            }
            bal=Float.parseFloat(bal1);
            if (et1.getText().toString().equals("") || tv2.getText().toString().equals(""))
            {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            }
            if(bal< transaction)
            {
                Toast.makeText(this, "Required Balance not available", Toast.LENGTH_SHORT).show();
            }
            else
            {
                cbal=bal+transaction;
                String cbal1=""+cbal;
                dbal=bal-transaction;
                String dbal1=""+dbal;
                String query2="update data set balance='"+cbal1+"' where cid='"+r_cid+"'";
                sb.execSQL(query2);
                String query1="update data set balance='"+dbal1+"' where cid='"+tv2.getText().toString()+"'";
                sb.execSQL(query1);
                Toast.makeText(this, "Transaction Made", Toast.LENGTH_SHORT).show();
                Intent intent3= new Intent(transfer_money.this,MainActivity.class);
                startActivity(intent3);
                et1.setText("");
            }
        });
    }
}