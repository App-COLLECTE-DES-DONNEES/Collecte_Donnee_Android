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
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.R;
import com.ditros.mcd.model.VehicleModel;
import com.ditros.mcd.model.dto.PersonDtoReq;
import com.ditros.mcd.model.dto.PersonDtoReq;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterListBasic1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PersonDtoReq> items = new
            ArrayList<>();
    List<VehicleModel> vehicleModelList = new ArrayList<>();
    appDatabase appDatabases;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(View view, PersonDtoReq obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasic1(Context context, List<PersonDtoReq> items,OnItemClickListener mItemClickListener) {
        this.items = items;
        ctx = context;
        this.mOnItemClickListener = mItemClickListener;
    }

    public class OriginalViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView name,desc;
        public View lyt_parent;

        public OriginalViewHolder(View v) {
            super(v);
            image = (ImageView) v.findViewById(R.id.image);
            name = (TextView) v.findViewById(R.id.name);
            desc = (TextView) v.findViewById(R.id.description);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        vh = new OriginalViewHolder(v);
        appDatabases = appDatabase.getInstance(parent.getContext());
        vehicleModelList = appDatabases.vehicleModelDAO().getAll();

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof OriginalViewHolder) {
            OriginalViewHolder view = (OriginalViewHolder) holder;

            PersonDtoReq p = items.get(position);
            view.name.setText(String.valueOf(p.getPersonAccidentNumber()));
            view.desc.setText(String.valueOf(vehicleModelList.get(p.getGender().intValue()-1).getValue()));
            //Log.e("reve",p.getChassis());

            //Tools.displayImageRound(ctx, view.image, p.image);
            view.lyt_parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(view, items.get(position), position);
                    }
                }
            });
        }
    }

    // Replace the contents of a view (invoked by the layout manager)
   /* @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

    }*/

    @Override
    public int getItemCount() {
        return items.size();
    }

}
