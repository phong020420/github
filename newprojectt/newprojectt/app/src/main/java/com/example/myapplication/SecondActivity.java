package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    IntentFilter intentFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        EditText masp = findViewById(R.id.edtmasp);
        EditText tensp = findViewById(R.id.edttensp);
        EditText mota = findViewById(R.id.edtmota);
        EditText soluong = findViewById(R.id.edtsoluong);


        intentFilter = new IntentFilter();
        intentFilter.addAction("ThemCt");


        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this,ThemCauThuService.class);
                Bundle bundle = new Bundle();

                bundle.putSerializable("listne",new Cauthu(
                        masp.getText().toString(),
                        tensp.getText().toString(),
                        mota.getText().toString(),
                        Integer.parseInt(String.valueOf(soluong.getText()))));
                intent.putExtras(bundle);
                startService(intent);
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mybBroadcastReceiver,intentFilter);

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
                case "ThemCt":
                    Bundle bundle = intent.getExtras();
                    Boolean a = bundle.getBoolean("CheckThem");
                    if(a){
                        Toast.makeText(SecondActivity.this, "Dung", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SecondActivity.this,MainActivity.class));
                    }
                    else{
                        Toast.makeText(SecondActivity.this, "Sai", Toast.LENGTH_SHORT).show();
                    }

            }
        }
    };
}