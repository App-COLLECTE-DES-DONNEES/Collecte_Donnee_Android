package com.ditros.mcd.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ditros.mcd.R;
import com.ditros.mcd.model.dto.Content;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterListAccident extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Content> items = new ArrayList<>();

    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, Content obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListAccident(Context context, List<Content> items,
                               OnItemClickListener onItemClickListener) {
        this.items = items;
        ctx = context;
        this.mOnItemClickListener = onItemClickListener;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name,description;
        public View lyt_parent;

        public OriginalViewHolder(View v) {

            super(v);
            image = v.findViewById(R.id.image);
            name = v.findViewById(R.id.name);
            description = v.findViewById(R.id.description);
            lyt_parent = v.findViewById(R.id.lyt_parent);

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        vh = new OriginalViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            Content p = items.get(position);

            view.name.setText("Accident No "+p.getId());
            view.description.setText(p.getPlace());

            //Tools.displayImageRound(ctx, view.image, p.image);
            view.lyt_parent.setOnClickListener(view1 -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(view1, items.get(position), position);
                }
            });
        }

    }


    // Replace the contents of a view (invoked by the layout manager)
    /*@Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {



    }*/

    @Override
    public int getItemCount() {
        return items.size();
    }
    
}
