package com.example.getlistofalbums;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumVH> {

    private List<ResponseModel> responseList;

    public AlbumAdapter(List<ResponseModel> responseList) {
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public AlbumVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new AlbumVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumVH holder, int position) {
        holder.setData(responseList.get(position));
    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<ResponseModel> responseList) {
        this.responseList = responseList;
        notifyDataSetChanged();
    }

    public class AlbumVH extends RecyclerView.ViewHolder {

        private TextView id, title;

        public AlbumVH(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
        }

        public void setData(ResponseModel responseModel) {
            id.setText(responseModel.getId() + " ");
            title.setText(responseModel.getTitle());
        }
    }
}