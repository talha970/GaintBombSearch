package com.example.tjaved.testapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tjaved.testapp.R;
import com.example.tjaved.testapp.data.model.Result;
import com.squareup.picasso.Picasso;

import junit.framework.Test;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tjaved on 3/24/18.
 */

public class ResultsRecycleAdapter extends RecyclerView.Adapter<ResultsRecycleAdapter.ResultsViewHolder> {

    RecycleItemOnClickListener listener;
    List<Result> resultList;

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
        notifyDataSetChanged();
    }

    public ResultsRecycleAdapter(RecycleItemOnClickListener listener, List<Result> resultList) {
        this.listener = listener;
        this.resultList = resultList;
    }

    @Override
    public ResultsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_items, parent, false);
        return new ResultsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ResultsViewHolder holder, int position) {
        Result item = resultList.get(position);
        holder.bind(item, listener);


    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ResultsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView tvName;
        @BindView(R.id.imScreen)
        ImageView imScreenImg;
        @BindView(R.id.tvDesc)
        TextView tvDesc;
        @BindView(R.id.tvDate)
        TextView tvDate;

        public ResultsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final Result item, final RecycleItemOnClickListener onClickListener) {
            tvName.setText(item.getName());
            tvDesc.setText(item.getDeck());
            tvDate.setText(item.getDateAdded());
            String imgPath = item.getImage().getThumbUrl();
            if (!imgPath.isEmpty())
                Picasso.with(itemView.getContext()).load(imgPath).into(imScreenImg);
            else
                imScreenImg.setBackgroundResource(R.drawable.ic_launcher_background);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(item);
                }
            });

        }

    }

    public interface RecycleItemOnClickListener {
        void onClick(Result result);
    }
}
