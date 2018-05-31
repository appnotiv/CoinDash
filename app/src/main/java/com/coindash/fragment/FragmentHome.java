package com.coindash.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.coindash.Constants.AppGlobal;
import com.coindash.Constants.WsConstant;
import com.coindash.MainActivity;
import com.coindash.R;
import com.coindash.model.LoginResponse;
import com.coindash.webservice.RestClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@SuppressWarnings("All")
public class FragmentHome extends Fragment {

    private MyWalletReceiver receiver;
    private TextView tvBinaryTeam, tvLeftUser, tvRightUser, tvDirectUser, tvPBZWallet, tvInvestmentWallet,
            tvEarningWallet, tvTotalROI, tvTotalInvestment, tvBinaryIncome, tvLeftBusiness, tvRightBusiness;
    private TextView tvWithdraw;
    private LinearLayout lvPBZ, lvBTC;

    public FragmentHome() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        IntentFilter filter = new IntentFilter(MyWalletReceiver.PROCESS_RESPONSE);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new MyWalletReceiver();
        getActivity().registerReceiver(receiver, filter);

        init(view);
    }

    private void init(View v) {
        tvBinaryTeam = (TextView) v.findViewById(R.id.tv_Home_BinaryTeam);
        tvLeftUser = (TextView) v.findViewById(R.id.tv_Home_LeftUser);
        tvRightUser = (TextView) v.findViewById(R.id.tv_Home_RightUser);
        tvDirectUser = (TextView) v.findViewById(R.id.tv_Home_DirectUser);
        tvPBZWallet = (TextView) v.findViewById(R.id.tv_Home_PBZWallet);
        tvInvestmentWallet = (TextView) v.findViewById(R.id.tv_Home_InvestmentWallet);
        tvEarningWallet = (TextView) v.findViewById(R.id.tv_Home_EarningWallet);
        tvTotalROI = (TextView) v.findViewById(R.id.tv_Home_TotalROI);
        tvTotalInvestment = (TextView) v.findViewById(R.id.tv_Home_TotalInvestment);
        tvBinaryIncome = (TextView) v.findViewById(R.id.tv_Home_BinaryIncome);
        tvLeftBusiness = (TextView) v.findViewById(R.id.tv_Home_LeftBusiness);
        tvRightBusiness = (TextView) v.findViewById(R.id.tv_Home_RightBusiness);

        tvWithdraw = (TextView) v.findViewById(R.id.tv_Home_Withdraw);
        tvWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).callFragment(new FragmentEarningWithdraw());
            }
        });

        lvBTC = (LinearLayout) v.findViewById(R.id.linear_Home_Receive_BTC);
        lvPBZ = (LinearLayout) v.findViewById(R.id.linear_Home_Receive_PBZ);

        lvBTC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogReceive("Receive BTC", "btc");
            }
        });

        lvPBZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogReceive("Receive PBZ", "pbz");
            }
        });

        setData();
    }

    private void dialogReceive(String title, String type) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);

        View vi = getLayoutInflater().inflate(R.layout.dialog_receive_btc_and_pbz, null, false);
        dialog.setContentView(vi);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();

        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(lp);

        String trcAddress = "";

        if (type.equalsIgnoreCase("btc")) {
            trcAddress = AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_BTC_ADD);
        } else {
            trcAddress = AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_PBZ_ADD);
        }

        TextView tvClose = (TextView) dialog.findViewById(R.id.tv_Dialog_Receive_BTC_TRC_Close);
        final TextView tvAddress = (TextView) dialog.findViewById(R.id.tv_Dialog_Receive_BTC_TRC_Address);
        TextView tvTitle = (TextView) dialog.findViewById(R.id.tv_Dialog_Receive_BTC_TRC_Title);
        tvTitle.setText(title);

        tvAddress.setText(trcAddress);
        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppGlobal.copyData(getActivity(), tvAddress.getText().toString().trim());
                Toast.makeText(getActivity(), "Address copied....", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView imQrCode = (ImageView) dialog.findViewById(R.id.Image_Dialog_Receive_BTC_TRC_QrCode);
        imQrCode.setImageBitmap(AppGlobal.encodeAsBitmap(trcAddress));

        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void setData() {
        tvBinaryTeam.setText(getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_BINARY_TEAM)));
        tvLeftUser.setText(getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_LEFT_USER)));
        tvRightUser.setText(getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_RIGHT_USER)));
        tvDirectUser.setText(getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_DIRECT_USER)));
        tvPBZWallet.setText(getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_PBZ_WALLET)));
        tvInvestmentWallet.setText("$ " + getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_INVEST_WALLET)));
        tvEarningWallet.setText("$ " + getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_EARNING_WALLET)));
        tvTotalROI.setText("$ " + getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_TOTAL_ROI)));
        tvTotalInvestment.setText("$ " + getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_INVESTMENT)));
        tvBinaryIncome.setText("$ " + getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_BINARY_INCOME)));
        tvLeftBusiness.setText("$ " + getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_LEFT_BUSINESS)));
        tvRightBusiness.setText("$ " + getStringBlank(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_WALLET_RIGHT_BUSINESS)));
    }

    private String getStringBlank(String insert) {
        if (!insert.equalsIgnoreCase("")) {
            return insert;
        }
        return "0.00";
    }

    @Override
    public void onResume() {
        super.onResume();
        doUserDetails();
    }

    public void doUserDetails() {
        if (AppGlobal.isNetwork(getActivity())) {
            AppGlobal.showProgressDialog(getActivity());

            Map<String, String> optioMap = new HashMap<>();
            optioMap.put("RegisterId", AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_REGID));
            optioMap.put("TokenData", AppGlobal.getId(getActivity()));

            new RestClient(getActivity()).getInstance().get().getUserDetails(optioMap).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    AppGlobal.hideProgressDialog(getActivity());
                    if (response.body() != null) {
                        if (response.body().getSuccess() == 1) {

                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getRegisterId(), WsConstant.SP_LOGIN_REGID);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getFullname(), WsConstant.SP_LOGIN_NAME);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getUsername(), WsConstant.SP_LOGIN_USERNAME);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getEmailId(), WsConstant.SP_LOGIN_EMAIL);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getPhone(), WsConstant.SP_LOGIN_MOBILE);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getProfile(), WsConstant.SP_LOGIN_PROFILE_PIC);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getLoginStatus(), WsConstant.SP_LOGIN_STATUS);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getEmailVerificationStatus(), WsConstant.SP_LOGIN_EMAIL_VERIFY);

                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getResidentAddress(), WsConstant.SP_ADDRESS_RES);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getCity(), WsConstant.SP_CITY);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getState(), WsConstant.SP_STATE);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getPincode(), WsConstant.SP_PICODE);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getCountry(), WsConstant.SP_COUNTRY);
                            AppGlobal.setStringPreference(getActivity(), response.body().getInfo().getCountryCode(), WsConstant.SP_COUNTRY_CODE);

                            MainActivity.setUserData();

                        } else if (response.body().getSuccess() == 100) {
                            AppGlobal.logoutApplication(getActivity());
                        } else {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    try {
                        AppGlobal.hideProgressDialog(getActivity());
                        Toast.makeText(getActivity(), getString(R.string.network_time_out_error), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            Toast.makeText(getActivity(), getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(receiver);
    }

    public class MyWalletReceiver extends BroadcastReceiver {

        public static final String PROCESS_RESPONSE = "cears.chiba.Activity.intent.action.PROCESS_RESPONSE";

        @Override
        public void onReceive(Context context, Intent intent) {

            try {
                setData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}