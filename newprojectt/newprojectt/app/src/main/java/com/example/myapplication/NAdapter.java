package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;


public class NAdapter extends RecyclerView.Adapter<NAdapter.ViewHolder> {
    private ArrayList<Cauthu> list;
    private Context context;
    private DAO dao;

    public NAdapter(ArrayList<Cauthu> list, Context context, DAO dao) {
        this.list = list;
        this.context = context;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        ViewHolder ViewHolder = new ViewHolder(view);
        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cauthu Model = list.get(position);
        holder.txtMact.setText(Model.getMact());
        holder.txtTenct.setText(Model.getTenct());
        holder.txtNgaysinh.setText(Model.getNgaysinh());
        holder.txtLuong.setText(String.valueOf(Model.getLuong()));

        holder.ivdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,XoaService.class);
                Bundle bundle = new Bundle();
                bundle.putString("madexoa",list.get(position).getMact());
                intent.putExtras(bundle);
                context.startService(intent);
                notifyDataSetChanged();
            }
        });

        holder.ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_aaa,null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                EditText edtten = view.findViewById(R.id.edttenctne);
                EditText edtngaysinh = view.findViewById(R.id.edtngaysinhne);
                EditText edtluong = view.findViewById(R.id.edtluongne);
                Button btnsuact = view.findViewById(R.id.btnsuact);

                btnsuact.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,SuaCtService.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("modelsua",new Cauthu(
                                list.get(position).getMact(),
                                edtten.getText().toString(),
                                edtngaysinh.getText().toString(),
                                Integer.parseInt(edtluong.getText().toString())));

                        intent.putExtras(bundle);
                        context.startService(intent);
                        alertDialog.dismiss();

//                        list.clear();
//                        list = dao.getallct();



                    }
                });



            }
        });



    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMact,txtTenct,txtNgaysinh,txtLuong;
        ImageView ivedit,ivdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMact = itemView.findViewById(R.id.txtMact);
            txtTenct = itemView.findViewById(R.id.txtTenct);
            txtNgaysinh = itemView.findViewById(R.id.txtNgaysinh);
            txtLuong = itemView.findViewById(R.id.txtLuong);
            ivedit = itemView.findViewById(R.id.ivedit);
            ivdelete = itemView.findViewById(R.id.ivdelete);
        }
    }




}
