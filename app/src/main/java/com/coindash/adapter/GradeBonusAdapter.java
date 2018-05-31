package com.coindash.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coindash.R;
import com.coindash.model.GradeBonusResponse;

import java.util.ArrayList;


@SuppressWarnings("ALL")
public class GradeBonusAdapter extends RecyclerView.Adapter<GradeBonusAdapter.MyViewHolder> {

    private ArrayList<GradeBonusResponse.Info> listTicket;
    private Context mContext;

    public GradeBonusAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public GradeBonusAdapter(Context mContext, ArrayList<GradeBonusResponse.Info> listTicket) {
        this.listTicket = listTicket;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_grade_bonus, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tvLevel.setText("Level : " + listTicket.get(position).getLevel());
        holder.tvMatching.setText("Matching : $ " + listTicket.get(position).getMatchingAmount());
        holder.tvPer.setText("Per : " + listTicket.get(position).getPercentage() + " %");
        holder.tvBonus.setText("Bonus : $ " + listTicket.get(position).getBonus());
        holder.tvFromUser.setText("FromUser : " + listTicket.get(position).getFromUser());
        holder.tvDate.setText(listTicket.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return listTicket.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvLevel, tvMatching, tvPer, tvFromUser, tvBonus, tvDate;

        public MyViewHolder(View view) {
            super(view);
            tvLevel = (TextView) view.findViewById(R.id.tv_ItemGradeBonus_Level);
            tvMatching = (TextView) view.findViewById(R.id.tv_ItemGradeBonus_Matching);
            tvPer = (TextView) view.findViewById(R.id.tv_ItemGradeBonus_Per);
            tvFromUser = (TextView) view.findViewById(R.id.tv_ItemGradeBonus_FromUser);
            tvBonus = (TextView) view.findViewById(R.id.tv_ItemGradeBonus_Bonus);
            tvDate = (TextView) view.findViewById(R.id.tv_ItemGradeBonus_Date);
        }
    }
}