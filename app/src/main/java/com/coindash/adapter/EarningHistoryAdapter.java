package com.coindash.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.EarningWalletHistoryResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class EarningHistoryAdapter extends RecyclerView.Adapter<EarningHistoryAdapter.MyViewHolder> {

    private ArrayList<EarningWalletHistoryResponse.Info> listTicket;
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

    public EarningHistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public EarningHistoryAdapter(Context mContext, ArrayList<EarningWalletHistoryResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_investment_history, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvPlan.setText(listTicket.get(position).getType());
        holder.tvActive.setText("" + listTicket.get(position).getReceive());
        holder.tvExpired.setText("" + listTicket.get(position).getSend());
        holder.tvDate.setText(listTicket.get(position).getDate());

        holder.tvActive.setTextColor(Color.parseColor("#008000"));
        holder.tvExpired.setTextColor(Color.parseColor("#FF0000"));
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}