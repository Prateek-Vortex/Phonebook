package com.example.prateeksaxena.phonebook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class add extends AppCompatActivity {
    Button b;
    EditText t1,t2;
    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        b=findViewById(R.id.ab);
        t1=findViewById(R.id.et1);
        t2=findViewById(R.id.et2);
        ll=findViewById(R.id.ll);
        SQLiteDatabase sb=openOrCreateDatabase("phone",MODE_PRIVATE,null);
        sb.execSQL("create table if not exists contact(name varchar(30),phno varchar(30))");
        sb.execSQL("insert into contact values('','')");
        String ss="select * from contact";
        Cursor c=sb.rawQuery(ss,null);
        if(c.moveToFirst())
        {
            do{
                String t=c.getString(0)+" "+c.getString(1);
                TextView tv=new TextView(add.this);
                tv.setText(t);
                ll.addView(tv);
            }while(c.moveToNext());
            sb.close();
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sb=openOrCreateDatabase("phone",MODE_PRIVATE,null);
                sb.execSQL("create table if not exists contact(name varchar(30),phno varchar(30))");
                sb.execSQL("insert into contact values('"+t1.getText().toString()+"','"+t2.getText().toString()+"') ");
                Toast.makeText(add.this, "Contact saved", Toast.LENGTH_SHORT).show();
                String f=t1.getText().toString()+" "+t2.getText().toString();
                TextView tt=new TextView(add.this);
                tt.setText(f);
                ll.addView(tt);

            }
        });
    }
}
