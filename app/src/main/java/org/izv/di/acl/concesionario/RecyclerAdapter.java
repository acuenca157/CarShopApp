package org.izv.di.acl.concesionario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CarViewHolder> {
    ArrayList<Car> list;
    Context context;

    public RecyclerAdapter(Context context){this.context = context;}

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        Car car = list.get(position);
        holder.car = car;
        holder.tvTitle.setText(car.title);
        holder.tvDesc.setText(car.description);
        holder.tvPrice.setText(car.price);
        String imgUrl = car.imagesUrls.get(0);
        Glide.with(context).load(imgUrl).into(holder.ivCar);
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

    public void setList(ArrayList<Car> cars){
        list = cars;
    }

    class CarViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle, tvDesc, tvPrice;
        public ImageView ivCar;
        public Car car;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ivCar = itemView.findViewById(R.id.ivCar);

            itemView.setOnClickListener((View v) -> {
                Log.v("xyzyx", "Debo cambiar de escena");
                Intent intent = new Intent(context, CarDetail.class);
                intent.putExtra("car", car);
                context.startActivity(intent);
            });
        }
    }
}
