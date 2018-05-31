package com.coindash.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coindash.Constants.AppGlobal;
import com.coindash.Constants.WsConstant;
import com.coindash.MainActivity;
import com.coindash.R;
import com.coindash.adapter.GradeBonusAdapter;
import com.coindash.adapter.GradeBonusSummaryAdapter;
import com.coindash.model.GradeBonusResponse;
import com.coindash.model.GradeBonusSummaryResponse;
import com.coindash.webservice.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class FragmentGradeBonusSummary extends Fragment {

    private ArrayList<GradeBonusSummaryResponse.Info> listTicket = new ArrayList<>();
    private RecyclerView rvTickets;
    private SwipeRefreshLayout refreshLayout;

    public FragmentGradeBonusSummary() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_common_recyclerview, container, false);
        MainActivity.mainTitle.setText("Grade Bonus Summary");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View v) {
        rvTickets = (RecyclerView) v.findViewById(R.id.recyclerView_Common);
        rvTickets.setLayoutManager(new LinearLayoutManager(getActivity()));

        refreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeToRefresh_Common);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        getData();
    }

    public void getData() {

        if (AppGlobal.isNetwork(getActivity())) {

            refreshLayout.setRefreshing(true);

            Map<String, String> optioMap = new HashMap<>();
            optioMap.put("RegisterId", AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_REGID));
            optioMap.put("TokenData", AppGlobal.getId(getActivity()));

            new RestClient(getActivity()).getInstance().get().getGradeBonusSummary(optioMap).enqueue(new Callback<GradeBonusSummaryResponse>() {
                @Override
                public void onResponse(Call<GradeBonusSummaryResponse> call, Response<GradeBonusSummaryResponse> response) {
                    refreshLayout.setRefreshing(false);

                    if (response.body() != null) {
                        if (response.body().getStatus() == 1) {
                            listTicket = new ArrayList<>();
                            if (response.body().getInfo().size() > 0) {

                                listTicket = response.body().getInfo();
                                GradeBonusSummaryAdapter adapter = new GradeBonusSummaryAdapter(getActivity(), response.body().getInfo());
                                rvTickets.setAdapter(adapter);
                            } else {
                                Toast.makeText(getActivity(), "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        } else if (response.body().getStatus() == 100) {
                            AppGlobal.logoutApplication(getActivity());
                        } else {
                            Toast.makeText(getActivity(), "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<GradeBonusSummaryResponse> call, Throwable t) {
                    try {
                        refreshLayout.setRefreshing(false);
                        Log.e("Taurus", "Error while call API : " + t.toString());
                        Toast.makeText(getActivity(), getString(R.string.network_time_out_error), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else {
            Toast.makeText(getActivity(), getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
    }
}