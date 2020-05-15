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
import com.example.appmobilefoot.presentation.model.Player;

import java.util.List;

public class ListPlayerAdapter extends RecyclerView.Adapter<ListPlayerAdapter.ViewHolder> {
    private List<Player> values;
    private Context mContext;
    private ImageView imageView;


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



    ListPlayerAdapter(List<Player> myDataset, Context context) {
        values = myDataset;
        mContext = context;
    }


    @NonNull
    public ListPlayerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout_player, parent, false);

        ListPlayerAdapter.ViewHolder vh = new ListPlayerAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ListPlayerAdapter.ViewHolder holder, final int position) {
        final Player name = values.get(position);
        holder.txtHeader.setText(name.getName());
        holder.txtFooter.setText(name.getClub());

        Glide.with(mContext).load(values.get(position).getImageURL()).fitCenter().into(imageView);
    }

    public int getItemCount() {
        return values.size();
    }
}
