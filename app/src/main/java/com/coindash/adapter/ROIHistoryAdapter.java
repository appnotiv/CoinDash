package com.coindash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.ROIHistoryResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class ROIHistoryAdapter extends RecyclerView.Adapter<ROIHistoryAdapter.MyViewHolder> {

    private ArrayList<ROIHistoryResponse.Info> listTicket;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlan, tvAmout, tvDate, tvIncome;

        public MyViewHolder(View view) {
            super(view);
            tvPlan = (TextView) view.findViewById(R.id.tv_ItemROI_Plan);
            tvAmout = (TextView) view.findViewById(R.id.tv_ItemROI_Amount);
            tvIncome = (TextView) view.findViewById(R.id.tv_ItemROI_Income);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemROI_Date);
        }
    }

    public ROIHistoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ROIHistoryAdapter(Context mContext, ArrayList<ROIHistoryResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_roi_history, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvPlan.setText(listTicket.get(position).getPlanName());
        holder.tvAmout.setText("Amount : $ " + listTicket.get(position).getInvestmentAmount());
        holder.tvIncome.setText("Income : $ " + listTicket.get(position).getIncomeAmount());
        holder.tvDate.setText(listTicket.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}