package com.coindash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.DirectReferralResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class DirectReferralAdapter extends RecyclerView.Adapter<DirectReferralAdapter.MyViewHolder> {

    private ArrayList<DirectReferralResponse.Info> listTicket;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvAmout, tvDate, tvFromUser, tvPer, tvInvestment;

        public MyViewHolder(View view) {
            super(view);
            tvAmout = (TextView) view.findViewById(R.id.tv_ItemDirectReferral_Amount);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemDirectReferral_Date);
            tvFromUser = (TextView) view.findViewById(R.id.tv_ItemDirectReferral_FromUser);
            tvPer = (TextView) view.findViewById(R.id.tv_ItemDirectReferral_Per);
            tvInvestment = (TextView) view.findViewById(R.id.tv_ItemDirectReferral_Investment);
        }
    }

    public DirectReferralAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public DirectReferralAdapter(Context mContext, ArrayList<DirectReferralResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_direct_referral, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvAmout.setText("$ " + listTicket.get(position).getIncomeAmount());
        holder.tvDate.setText(listTicket.get(position).getDate());
        holder.tvFromUser.setText("FromUser : " + listTicket.get(position).getFromUser());
        holder.tvPer.setText("Per : " + listTicket.get(position).getPercentage() + " %");
        holder.tvInvestment.setText("Investment Amount : $" + listTicket.get(position).getInvestmentAmount());
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}