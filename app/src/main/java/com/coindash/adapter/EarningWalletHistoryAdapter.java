package com.coindash.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.EarningWithdrawHistoryResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class EarningWalletHistoryAdapter extends RecyclerView.Adapter<EarningWalletHistoryAdapter.MyViewHolder> {

    private ArrayList<EarningWithdrawHistoryResponse.Info> listTicket;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvStatus, tvAmoutUSD, tvDate, tvAmountPBZ, tvDes;

        public MyViewHolder(View view) {
            super(view);
            tvStatus = (TextView) view.findViewById(R.id.tv_ItemEarningWalletHistory_Status);
            tvAmoutUSD = (TextView) view.findViewById(R.id.tv_ItemEarningWalletHistory_AmountUSD);
            tvAmountPBZ = (TextView) view.findViewById(R.id.tv_ItemEarningWalletHistory_AmountPBZ);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemEarningWalletHistory_Date);
            tvDes = (TextView) view.findViewById(R.id.tv_ItemEarningWalletHistory_Des);
        }
    }

    public EarningWalletHistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public EarningWalletHistoryAdapter(Context mContext, ArrayList<EarningWithdrawHistoryResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_earning_wallet_history, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvStatus.setText(listTicket.get(position).getStatus());
        holder.tvAmoutUSD.setText(Html.fromHtml(listTicket.get(position).getAmountUSD()));
        holder.tvAmountPBZ.setText(Html.fromHtml(listTicket.get(position).getAmountPBZ()));
        holder.tvDate.setText(listTicket.get(position).getDate());
        holder.tvDes.setText(Html.fromHtml(listTicket.get(position).getDescription()));

        holder.tvAmountPBZ.setTextColor(Color.parseColor("#008000"));
        holder.tvAmoutUSD.setTextColor(Color.parseColor("#FF0000"));
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}