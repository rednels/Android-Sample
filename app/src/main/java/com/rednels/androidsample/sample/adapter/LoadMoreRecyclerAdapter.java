package com.rednels.androidsample.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by rednels on 14-8-23.
 */
public class LoadMoreRecyclerAdapter extends RecyclerView.Adapter<LoadMoreRecyclerAdapter.ViewHolder> {
    private ArrayList<String> datas;
    private OnItemClickListener mItemClickListener;

    public LoadMoreRecyclerAdapter(ArrayList<String> data) {
        this.datas = data;
    }

    public OnItemClickListener getOnItemClickListener() {
        return mItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_list_item_1, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        viewHolder.mTextView.setText(datas.get(i));
        if (mItemClickListener != null)
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(view, i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public interface OnItemClickListener {
        public boolean onItemClick(View view, int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView;
        }
    }
}
