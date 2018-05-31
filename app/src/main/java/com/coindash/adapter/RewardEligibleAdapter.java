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
import com.coindash.model.RewardEligibleResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class RewardEligibleAdapter extends RecyclerView.Adapter<RewardEligibleAdapter.MyViewHolder> {

    private ArrayList<RewardEligibleResponse.Info> listTicket;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvLeft, tvRight, tvDate, tvDesc;

        public MyViewHolder(View view) {
            super(view);
            tvLeft = (TextView) view.findViewById(R.id.tv_ItemRewardEligible_Left);
            tvRight = (TextView) view.findViewById(R.id.tv_ItemRewardEligible_Right);
            tvDesc = (TextView) view.findViewById(R.id.tv_ItemRewardEligible_Desc);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemRewardEligible_date);
        }
    }

    public RewardEligibleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public RewardEligibleAdapter(Context mContext, ArrayList<RewardEligibleResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reward_eligible, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvLeft.setText("Left : $ " + listTicket.get(position).getLeftBusiness());
        holder.tvRight.setText("Right : $ " + listTicket.get(position).getRightBusiness());
        holder.tvDesc.setText("Desc : " + listTicket.get(position).getDescription());
        holder.tvDate.setText(listTicket.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}