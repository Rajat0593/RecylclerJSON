package com.f.rajat.demotest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    private ArrayList<EmployeeModel> dataList;
    private Context context;

    RecyclerAdapter(ArrayList<EmployeeModel> dataList, Context context){
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        holder.mid.setText(dataList.get(position).getMid());
        holder.mPassword.setText(dataList.get(position).getMpassword());
        holder.mMobileNo.setText(dataList.get(position).getmMobileNo());
        holder.mManagerId.setText(dataList.get(position).getmManagerId());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder{
         TextView mid, mPassword, mMobileNo , mManagerId;

        RecyclerViewHolder(View itemView) {
            super(itemView);

                mid = itemView.findViewById(R.id.textView_id);
                mPassword = itemView.findViewById(R.id.textView_password);
                mMobileNo = itemView.findViewById(R.id.textView_mobileNo);
                mManagerId = itemView.findViewById(R.id.textView_managerId);
        }
    }
}
