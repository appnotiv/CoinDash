package com.coindash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.BinaryIncomeResponse;
import com.coindash.model.SponsorListResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class SponsorListAdapter extends RecyclerView.Adapter<SponsorListAdapter.MyViewHolder> {

    private ArrayList<SponsorListResponse.Info> listTicket;
    private Context mContext;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserName, tvMobile, tvAmount, tvEmail, tvCountry, tvDate;

        public MyViewHolder(View view) {
            super(view);
            tvUserName = (TextView) view.findViewById(R.id.tv_ItemSponsorList_UserName);
            tvMobile = (TextView) view.findViewById(R.id.tv_ItemSponsorList_Mobile);
            tvAmount = (TextView) view.findViewById(R.id.tv_ItemSponsorList_ActiveAmount);
            tvEmail = (TextView) view.findViewById(R.id.tv_ItemSponsorList_EmailId);
            tvCountry = (TextView) view.findViewById(R.id.tv_ItemSponsorList_Country);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemSponsorList_Date);
        }
    }

    public SponsorListAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public SponsorListAdapter(Context mContext, ArrayList<SponsorListResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_sponsor_list, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvUserName.setText(listTicket.get(position).getUserName());
        holder.tvMobile.setText("Mo : " + listTicket.get(position).getMobileNo());
        holder.tvAmount.setText("$ " + listTicket.get(position).getActiveInvestment());
        holder.tvEmail.setText("" + listTicket.get(position).getEmailID());
        holder.tvCountry.setText("" + listTicket.get(position).getCountry());
        holder.tvDate.setText(listTicket.get(position).getJoiningDate());
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }
}