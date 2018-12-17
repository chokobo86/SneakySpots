package com.example.jypark.gazua;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class RViewAdapter extends RecyclerView.Adapter<RViewAdapter.DataObjectHolder> {

    private Context context;
    private ArrayList<Coche> listaCoches;

    public RViewAdapter(Context context, ArrayList<Coche> listaCoches) {
        this.context = context;
        this.listaCoches = listaCoches;
    }

    @NonNull
    @Override
    public DataObjectHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_view,viewGroup,false);
        return new DataObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataObjectHolder holder, final int position) {


        holder.txtNombre.setText(listaCoches.get(position).getNombre());
        holder.txtCity.setText(listaCoches.get(position).getCity());

        Glide.with(context).load(listaCoches.get(position).getImg()).into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
              switch (position) {
                  case 0:
                      Intent intent = new Intent(context, Hunting_spot.class);
                      intent.putExtra("x",37.527182);
                      intent.putExtra("y",126.981133);

                      context.startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
                      break;

                  case 1:
                      Intent intent1 = new Intent(context, Hunting_spot.class);
                      intent1.putExtra("x",37.734868);
                      intent1.putExtra("y",128.340878);

                      context.startActivity(intent1.addFlags(FLAG_ACTIVITY_NEW_TASK));
                      break;

                  case 2:
                      Intent intent2 = new Intent(context, Hunting_spot.class);
                      intent2.putExtra("x",37.422987);
                      intent2.putExtra("y",127.510837);

                      context.startActivity(intent2.addFlags(FLAG_ACTIVITY_NEW_TASK));
                      break;

                  case 3:
                      Intent intent3 = new Intent(context, Hunting_spot.class);
                      intent3.putExtra("x",36.891999);
                      intent3.putExtra("y",127.304289);

                      context.startActivity(intent3.addFlags(FLAG_ACTIVITY_NEW_TASK));
                      break;

                  case 4:
                      Intent intent4 = new Intent(context, Hunting_spot.class);
                      intent4.putExtra("x",35.327172);
                      intent4.putExtra("y",127.052691);

                      context.startActivity(intent4.addFlags(FLAG_ACTIVITY_NEW_TASK));
                      break;

                  case 5:
                      Intent intent5 = new Intent(context, Hunting_spot.class);
                      intent5.putExtra("x",35.895947);
                      intent5.putExtra("y",128.667313);

                      context.startActivity(intent5.addFlags(FLAG_ACTIVITY_NEW_TASK));
                      break;

                  case 6:
                      Intent intent6 = new Intent(context, Hunting_spot.class);
                      intent6.putExtra("x",33.418165);
                      intent6.putExtra("y",126.795830);

                      context.startActivity(intent6.addFlags(FLAG_ACTIVITY_NEW_TASK));
                      break;
              }
            }
        });
    }



    @Override
    public int getItemCount() {
        return listaCoches.size();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private TextView txtNombre;
        private TextView txtCity;

        public DataObjectHolder(@NonNull View itemView) {
            super(itemView);
            this.img = itemView.findViewById(R.id.img);
            this.txtNombre = itemView.findViewById(R.id.txtNombre);
            this.txtCity = itemView.findViewById(R.id.txtCity);

        }
    }

}