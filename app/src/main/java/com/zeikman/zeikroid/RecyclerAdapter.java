package com.zeikman.zeikroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by zeikman on 12/28/15.
 */
/*public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String[][] dataSource;

    public RecyclerAdapter(String[][] dataArgs) {
        dataSource = dataArgs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         holder.dataTitle.setText(dataSource[position][0]);
         holder.dataContent.setText(dataSource[position][1]);
    }

    @Override
    public int getItemCount() {
        return dataSource.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView dataTitle, dataContent;

        public ViewHolder(View itemView) {
            super(itemView);
            dataTitle = (TextView) itemView.findViewById(R.id.data_title);
            dataContent= (TextView) itemView.findViewById(R.id.data_desc);
        }
    }
}*/

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataObjectHolder> {
    private ArrayList<DataObject> mDataset;
    private static CardClickListener cardClickListener;

    public RecyclerAdapter(ArrayList<DataObject> myDataset) {
        mDataset = myDataset;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        TextView title, content;

        public DataObjectHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.data_title);
            content= (TextView) itemView.findViewById(R.id.data_desc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            cardClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickLisntener(CardClickListener cardClickListener) {
        this.cardClickListener = cardClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());
        holder.content.setText(mDataset.get(position).getContent());
    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface CardClickListener {
        public void onItemClick(int position, View v);
    }
};