package com.androidvoyage.zakat.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.androidvoyage.zakat.R;
import com.androidvoyage.zakat.model.Features;

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
            holder.clParent.setBackgroundTintList(ContextCompat.getColorStateList(context, Features.INSTANCE.getColorRes(listArea.get(position))));
            holder.ivStart.setImageResource(Features.INSTANCE.getIcon(listArea.get(position)));
            UtilsKt.setOnClickAnimateListener(holder.itemView, view -> {
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
        ConstraintLayout clParent;
        ImageView ivStart;

        public ViewHolder(View view) {
            super(view);
            tvAreaName = view.findViewById(R.id.tv_name);
            clParent = view.findViewById(R.id.cl_parent);
            ivStart = view.findViewById(R.id.iv_start);
        }
    }

}

