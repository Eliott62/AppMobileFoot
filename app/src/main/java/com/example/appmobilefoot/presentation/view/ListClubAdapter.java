package com.example.appmobilefoot.presentation.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appmobilefoot.R;
import com.example.appmobilefoot.presentation.model.Club;

import java.util.List;

public class ListClubAdapter extends RecyclerView.Adapter<ListClubAdapter.ViewHolder> {
    private List<Club> values;
    private Context mContext;
    private ImageView imageView;

    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Club item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHeader;
        TextView txtFooter;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            txtFooter = v.findViewById(R.id.secondLine);
            imageView = v.findViewById(R.id.icona);
        }
    }

    public ListClubAdapter(List<Club> myDataset, Context context, OnItemClickListener listener) {
        this.values = myDataset;
        this.mContext = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListClubAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout_club, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Club currentClub = values.get(position);
        holder.txtHeader.setText(currentClub.getName());
        holder.txtFooter.setText(currentClub.getStadium());

        Glide.with(mContext).load(values.get(position).getImageURL()).fitCenter().into(imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(currentClub);
            }
        });
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}

