package com.ditros.mcd.adapter;


import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.ditros.mcd.Database.appDatabase;
import com.ditros.mcd.R;
import com.ditros.mcd.model.VehicleModel;
import com.ditros.mcd.model.dto.VehiculeReq;
import org.jetbrains.annotations.NotNull;


import java.util.ArrayList;
import java.util.List;

public class AdapterListBasic extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<VehiculeReq> items = new
            ArrayList<>();
    List<VehicleModel> vehicleModelList = new ArrayList<>();
    appDatabase appDatabases;
    private Context ctx;
    private OnItemClickListener mOnItemClickListener;
   // private final OnItemClickListener listener;
    public interface OnItemClickListener {
        void onItemClick(View view, VehiculeReq obj, int position);
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mOnItemClickListener = mItemClickListener;
    }

    public AdapterListBasic(Context context, List<VehiculeReq> items, OnItemClickListener listener) {
        this.items = items;
        this.ctx = context;
        this.mOnItemClickListener = listener;
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

    /*public void setOnItemListener(Context context,List<VehiculeReq> dataList, OnItemClickListener listener) {
        this.context = context;
        this.listItem = dataList;
        this.listener = listener;
    }*/
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
            Log.e("vehiclemodel", String.valueOf(vehicleModelList.size()));

            VehiculeReq p = items.get(position);
            view.name.setText(String.valueOf(p.getPlate()));
            view.desc.setText(String.valueOf(vehicleModelList.get(p.getModel().intValue()-1).getValue()));
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
