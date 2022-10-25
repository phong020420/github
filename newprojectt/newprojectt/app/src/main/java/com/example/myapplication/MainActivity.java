package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DAO dao;
    RecyclerView rcv;
    IntentFilter intentFilter;
    ArrayList<Cauthu> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intentFilter = new IntentFilter();
        intentFilter.addAction("GetallCt");
        intentFilter.addAction("XoaCtbr");
        intentFilter.addAction("SuaCt");


        rcv = findViewById(R.id.rcv);
        FloatingActionButton floatAdd = findViewById(R.id.add);


        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class));
            }
        });

        Intent intent = new Intent(MainActivity.this,CauThuService.class);
        Bundle bundle = new Bundle();
        intent.putExtras(bundle);
        startService(intent);
    }

    public void loadData(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(layoutManager);
        NAdapter nAdapter = new NAdapter(list,this,dao);
        rcv.setAdapter(nAdapter);
    }




    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mybBroadcastReceiver,intentFilter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(mybBroadcastReceiver);
    }

    private BroadcastReceiver mybBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "GetallCt":
                    Bundle bundle = intent.getExtras() ;
                    list = (ArrayList<Cauthu>) bundle.getSerializable("list");
                    loadData();
                    Toast.makeText(MainActivity.this, "Get Thanh Cong", Toast.LENGTH_SHORT).show();

                case "XoaCtbr":
                    Bundle bundle1 = intent.getExtras();
                    Boolean a = bundle1.getBoolean("madexoanuane");
                    if(a){
                        Toast.makeText(MainActivity.this, "Dung", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Sai ben xoa", Toast.LENGTH_SHORT).show();
                    }
                case "SuaCt":
                    Bundle bundle2 = intent.getExtras();
                    Boolean b = bundle2.getBoolean("CheckSua");
                    if(b){
                        Toast.makeText(MainActivity.this, "Dung", Toast.LENGTH_SHORT).show();
                        loadData();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Sai ben sua", Toast.LENGTH_SHORT).show();
                    }
            }
        }
    };
}