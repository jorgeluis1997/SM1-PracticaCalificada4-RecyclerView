package com.example.recyclerview.activity;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.R;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RVAdapater extends RecyclerView.Adapter<RVAdapater.RecyclerHolder> {
    private List<Persona> items;
    private List<Persona> originalItems;
    private RecyclerItemClick itemClick;

    public RVAdapater(List<Persona> items, RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_view, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder holder, final int position) {
        final Persona item = items.get(position);
        holder.imgFoto.setImageResource(item.getFoto());
        holder.tvNombre.setText(item.getNombre());
        holder.tvEdad.setText(item.getEdad());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(final String strSearch) {
        if (strSearch.length() == 0) {
            items.clear();
            items.addAll(originalItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                items.clear();
                List<Persona> collect = originalItems.stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());

                items.addAll(collect);
            }
            else {
                items.clear();
                for (Persona i : originalItems) {
                    if (i.getNombre().toLowerCase().contains(strSearch)) {
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvEdad;

        public RecyclerHolder(@NonNull View itemView_1) {
            super(itemView_1);

            imgFoto = itemView.findViewById(R.id.fotoPersona);
            tvNombre = itemView.findViewById(R.id.nombrePersona);
            tvEdad = itemView.findViewById(R.id.edadPersona);
        }
    }

    public interface RecyclerItemClick {
        void itemClick(Persona item);
    }
}
