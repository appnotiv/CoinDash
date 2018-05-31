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
import com.coindash.model.PBZWalletHistoryResponse;
import com.coindash.model.TransactionsResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class PBZWalletHistoryAdapter extends RecyclerView.Adapter<PBZWalletHistoryAdapter.MyViewHolder> {

    private ArrayList<PBZWalletHistoryResponse.Info> listTicket;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmount, tvStatus, tvDate, tvDes;

        public MyViewHolder(View view) {
            super(view);
            tvAmount = (TextView) view.findViewById(R.id.tv_ItemTransaction_Amount);
            tvStatus = (TextView) view.findViewById(R.id.tv_ItemTransaction_Status);
            tvDes = (TextView) view.findViewById(R.id.tv_ItemTransaction_Des);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemTransaction_Date);
        }
    }

    public PBZWalletHistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public PBZWalletHistoryAdapter(Context mContext, ArrayList<PBZWalletHistoryResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_transactions_history, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        if (!listTicket.get(position).getSend().equalsIgnoreCase("-")) {
            holder.tvAmount.setText(Html.fromHtml(listTicket.get(position).getSend()));
            holder.tvAmount.setTextColor(Color.parseColor("#FF0000"));
        } else {
            holder.tvAmount.setText(Html.fromHtml(listTicket.get(position).getReceive()));
            holder.tvAmount.setTextColor(Color.parseColor("#008000"));
        }

        holder.tvStatus.setText(listTicket.get(position).getStatus());
        holder.tvDes.setText(Html.fromHtml(listTicket.get(position).getDescription()));
        holder.tvDate.setText(listTicket.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}