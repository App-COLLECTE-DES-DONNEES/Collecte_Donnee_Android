package com.ditros.mcd.adapter;


import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.ditros.mcd.R;
import com.ditros.mcd.model.dto.DataLoad;
import gr.escsoft.michaelprimez.searchablespinner.interfaces.ISpinnerSelectedView;
import gr.escsoft.michaelprimez.searchablespinner.tools.UITools;


/**
     * Created by michael on 1/8/17.
     */

    public class SimpleArrayListAdapter extends ArrayAdapter<String> implements Filterable, ISpinnerSelectedView {

        private Context mContext;
        private List<DataLoad> mBackupStrings;
        private List<DataLoad> mStrings;
        private StringFilter mStringFilter = new StringFilter();

        public SimpleArrayListAdapter(Context context, List<DataLoad> strings) {
            super(context, R.layout.view_list_item);
            mContext = context;
            mStrings = strings;
            mBackupStrings = strings;
        }

        @Override
        public int getCount() {
            return mStrings == null ? 0 : mStrings.size() + 1;
        }

        @Override
        public String getItem(int position) {
            if (mStrings != null && position > 0)
                return mStrings.get(position - 1).getValue();
            else
                return null;
        }

        @Override
        public long getItemId(int position) {
            if (mStrings == null && position > 0)
                return mStrings.get(position).getValue().hashCode();
            else
                return -1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           // View view = null;
           /* if (position == 0) {
                view = getNoSelectionView();
            } else*/
            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(mContext);
                convertView = inflater.inflate(R.layout.view_list_item, parent, false);
            }


                //view = View.inflate(mContext, R.layout.view_list_item, null);


                TextView code = (TextView) convertView.findViewById(R.id.code);
                TextView value = (TextView) convertView.findViewById(R.id.value);


                code.setText(String.valueOf(mStrings.get(position).getCode()));
                value.setText(mStrings.get(position).getValue());


              return convertView;
        }

        @Override
        public View getSelectedView(int position) {
            View view = null;
            if (position == 0) {
                view = getNoSelectionView();
            } else {
                view = View.inflate(mContext, R.layout.view_list_item, null);
                TextView code = (TextView) view.findViewById(R.id.code);
                TextView value = (TextView) view.findViewById(R.id.value);

                code.setText(String.valueOf(mStrings.get(position-1).getCode()));
                value.setText(mStrings.get(position-1).getValue());
            }
            return view;
        }

        @Override
        public View getNoSelectionView() {
            View view = View.inflate(mContext, R.layout.view_list_item, null);
            return view;
        }
        private TextDrawable getTextDrawable(String displayName) {
            TextDrawable drawable = null;
            if (!TextUtils.isEmpty(displayName)) {
                int color2 = ColorGenerator.MATERIAL.getColor(displayName);
                drawable = TextDrawable.builder()
                        .beginConfig()
                        .width(UITools.dpToPx(mContext, 32))
                        .height(UITools.dpToPx(mContext, 32))
                        .textColor(Color.WHITE)
                        .toUpperCase()
                        .endConfig()
                        .round()
                        .build(displayName.substring(0, 1), color2);
            } else {
                drawable = TextDrawable.builder()
                        .beginConfig()
                        .width(UITools.dpToPx(mContext, 32))
                        .height(UITools.dpToPx(mContext, 32))
                        .endConfig()
                        .round()
                        .build("?", Color.GRAY);
            }
            return drawable;
        }

        @Override
        public Filter getFilter() {
            return mStringFilter;
        }

        public class StringFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults filterResults = new FilterResults();
                if (TextUtils.isEmpty(constraint)) {
                    filterResults.count = mBackupStrings.size();
                    filterResults.values = mBackupStrings;
                    return filterResults;
                }
                final ArrayList<String> filterStrings = new ArrayList<>();
                for (DataLoad text : mBackupStrings) {
                    if (text.getValue().toLowerCase().contains(constraint)) {
                        filterStrings.add(text.getValue());
                    }
                }
                filterResults.count = filterStrings.size();
                filterResults.values = filterStrings;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mStrings = (ArrayList) results.values;
                notifyDataSetChanged();
            }
        }

        private class ItemView {
            public ImageView mImageView;
            public TextView mTextView;
        }

        public enum ItemViewType {
            ITEM, NO_SELECTION_ITEM;
        }
    }


