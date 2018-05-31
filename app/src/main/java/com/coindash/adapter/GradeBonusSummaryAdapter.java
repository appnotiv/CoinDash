package com.coindash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.GradeBonusSummaryResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class GradeBonusSummaryAdapter extends RecyclerView.Adapter<GradeBonusSummaryAdapter.MyViewHolder> {

    private ArrayList<GradeBonusSummaryResponse.Info> listTicket;
    private Context mContext;

    public GradeBonusSummaryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public GradeBonusSummaryAdapter(Context mContext, ArrayList<GradeBonusSummaryResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grade_bonus_summary, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tvAmount.setText("$ " + listTicket.get(position).getTotal());
        holder.tvDate.setText(listTicket.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmount, tvDate;

        public MyViewHolder(View view) {
            super(view);
            tvAmount = (TextView) view.findViewById(R.id.tv_ItemGradeBonus_Date);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemGradeBonus_Amount);
        }
    }
}