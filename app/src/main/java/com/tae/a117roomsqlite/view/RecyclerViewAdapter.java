package com.tae.a117roomsqlite.view;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.tae.a117roomsqlite.DATA.User;
import com.tae.a117roomsqlite.R;
import com.tae.a117roomsqlite.repository.ProductDiffUtilCallback;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<User> data;
    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mName, mYear;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.txtName);
            mYear = itemView.findViewById(R.id.txtYear);
        }

        public void bind (final User user, final OnItemClickListener onItemClickListener) {
            mName.setText(user.getName());
            mYear.setText(String.valueOf(user.getYear()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(user);
                }
            });
        }
    }

    public RecyclerViewAdapter(List<User> data, OnItemClickListener onItemClickListener) {
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(data.get(position).getName());
        holder.mYear.setText(String.valueOf(data.get(position).getYear()));
        holder.bind(data.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {

        ProductDiffUtilCallback userDiffUtilCallback = new ProductDiffUtilCallback(getData(), data);
        DiffUtil.DiffResult userDiffResult = DiffUtil.calculateDiff(userDiffUtilCallback);
         this.data.clear();
        this.data = data;
        userDiffResult.dispatchUpdatesTo(this);

    }
}