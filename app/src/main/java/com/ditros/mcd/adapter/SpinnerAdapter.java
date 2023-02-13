package com.ditros.mcd.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.ditros.mcd.R;
import com.ditros.mcd.model.dto.DataLoad;

import java.util.List;


public class SpinnerAdapter extends ArrayAdapter<DataLoad> {

    List<DataLoad> algorithmList;
        public SpinnerAdapter(Context context,
                                List<DataLoad> algorithmList)
        {
            super(context, 0, algorithmList);
            this.algorithmList = algorithmList;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable
        View convertView, @NonNull ViewGroup parent)
        {
            return initView(position, convertView, parent);
        }

        @Override
        public View getDropDownView(int position, @Nullable
        View convertView, @NonNull ViewGroup parent)
        {
            return initView(position, convertView, parent);
        }

        public int getItemPosition(long id)
        {
            for (int position=0; position<algorithmList.size(); position++)
                if (algorithmList.get(position).getId() == id)
                    return position;
            return 0;
        }

        private View initView(int position, View convertView,
                              ViewGroup parent)
        {
            // It is used to set our custom view.
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.view_list_item, parent, false);
            }

            TextView textViewName = convertView.findViewById(R.id.code);
            TextView textViewvalue = convertView.findViewById(R.id.value);
            DataLoad currentItem = getItem(position);

            // It is used the name to the TextView when the
            // current item is not null.
            if (currentItem != null) {
                //textViewName.setText(currentItem.getCode());
                textViewvalue.setText(currentItem.getValue());
            }
            return convertView;
        }

    }
