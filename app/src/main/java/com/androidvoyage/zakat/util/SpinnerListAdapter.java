package com.androidvoyage.zakat.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.androidvoyage.zakat.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class SpinnerListAdapter extends RecyclerView.Adapter<SpinnerListAdapter.ViewHolder> {

    Context context;
    ArrayList<String> listArea;
    OnSelectListener onAreaSelectListener;


    public SpinnerListAdapter(Context context, ArrayList<String> listArea, OnSelectListener onAreaSelectListener) {
        this.context = context;
        if (listArea!=null&&listArea.size()>0) {
            this.listArea = listArea;
            this.onAreaSelectListener = onAreaSelectListener;
        }else {
            this.listArea = new ArrayList<>();
        }
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_title, parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, final int position) {

        try {
            holder.tvAreaName.setText(listArea.get(position));
            holder.itemView.setOnClickListener(v -> {
                onAreaSelectListener.onSelected(listArea.get(position));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listArea.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvAreaName;

        public ViewHolder(View view) {
            super(view);
            tvAreaName = view.findViewById(R.id.tv_name);
        }
    }

}

