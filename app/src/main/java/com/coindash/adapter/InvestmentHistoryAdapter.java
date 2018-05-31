package com.coindash.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.InvestmentHistoryResponse;
import com.coindash.model.ROIHistoryResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class InvestmentHistoryAdapter extends RecyclerView.Adapter<InvestmentHistoryAdapter.MyViewHolder> {

    private ArrayList<InvestmentHistoryResponse.Info> listTicket;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlan, tvActive, tvDate, tvExpired;

        public MyViewHolder(View view) {
            super(view);
            tvPlan = (TextView) view.findViewById(R.id.tv_ItemInvestmentHistory_Package);
            tvActive = (TextView) view.findViewById(R.id.tv_ItemInvestmentHistory_Active);
            tvExpired = (TextView) view.findViewById(R.id.tv_ItemInvestmentHistory_Expired);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemInvestmentHistory_Date);
        }
    }

    public InvestmentHistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public InvestmentHistoryAdapter(Context mContext, ArrayList<InvestmentHistoryResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_investment_history, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvPlan.setText(listTicket.get(position).getPackageName());
        holder.tvActive.setText("Active : " + listTicket.get(position).getActiveAmount());
        holder.tvExpired.setText("Expired : " + listTicket.get(position).getExpiredAmount());
        holder.tvDate.setText(listTicket.get(position).getDate());

        holder.tvActive.setTextColor(Color.parseColor("#008000"));
        holder.tvExpired.setTextColor(Color.parseColor("#FF0000"));
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}