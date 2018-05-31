package com.coindash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.BinaryIncomeResponse;
import com.coindash.model.ROIHistoryResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class BinaryIncomeAdapter extends RecyclerView.Adapter<BinaryIncomeAdapter.MyViewHolder> {

    private ArrayList<BinaryIncomeResponse.Info> listTicket;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvMatching, tvPer, tvTotalIncome, tvCapping, tvNetIncome, tvDate;

        public MyViewHolder(View view) {
            super(view);
            tvMatching = (TextView) view.findViewById(R.id.tv_ItemBinaryIncome_Matching);
            tvPer = (TextView) view.findViewById(R.id.tv_ItemBinaryIncome_Per);
            tvTotalIncome = (TextView) view.findViewById(R.id.tv_ItemBinaryIncome_TotalIncome);
            tvCapping = (TextView) view.findViewById(R.id.tv_ItemBinaryIncome_Capping);
            tvNetIncome = (TextView) view.findViewById(R.id.tv_ItemBinaryIncome_NetIncome);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemBinaryIncome_Date);
        }
    }

    public BinaryIncomeAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BinaryIncomeAdapter(Context mContext, ArrayList<BinaryIncomeResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_binary_income, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvMatching.setText("Matching : $ " + listTicket.get(position).getMatchingAmount());
        holder.tvPer.setText("Per : " + listTicket.get(position).getPercentage() + " %");
        holder.tvTotalIncome.setText("Total : $ " + listTicket.get(position).getTotalIncome());
        holder.tvCapping.setText("Capping : $ " + listTicket.get(position).getCapping());
        holder.tvNetIncome.setText("Net : $ " + listTicket.get(position).getNetIncome());
        holder.tvDate.setText(listTicket.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}