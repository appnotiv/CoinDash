package com.coindash.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


import com.coindash.Constants.AppGlobal;
import com.coindash.Constants.WsConstant;
import com.coindash.MainActivity;
import com.coindash.fragment.FragmentHome;
import com.coindash.model.WalletAmountResponse;
import com.coindash.webservice.RestClient;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class WalletAmountService extends Service {

    public static Runnable runnable = null;
    public static Handler handler = new Handler();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        doCallBuySellRate();
        return super.onStartCommand(intent, flags, startId);
    }

    private void callEvent() {
        runnable = new Runnable() {
            @Override
            public void run() {
                doCallBuySellRate();
            }
        };
        handler.postDelayed(runnable, 15 * 1000);
    }

    public static void removeCallback() {
        if (runnable != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        removeCallback();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void doCallBuySellRate() {
        if (AppGlobal.isNetwork(this)) {
            Map<String, String> options = new HashMap<>();
            options.put("RegisterId", AppGlobal.getStringPreference(this, WsConstant.SP_LOGIN_REGID));
            options.put("TokenData", AppGlobal.getId(WalletAmountService.this));

            new RestClient(this).getInstance().get().WalletLiveAmount(options).enqueue(new Callback<WalletAmountResponse>() {
                @Override
                public void onResponse(Call<WalletAmountResponse> call, Response<WalletAmountResponse> response) {

                    Log.e("TAG", "Result : " + response.body());

                    if (response.body() != null) {
                        if (response.body().getSuccess() == 1) {
                            try {

                                WalletAmountResponse.Info info = response.body().getInfo();
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getBTCAddress(), WsConstant.SP_WALLET_BTC_ADD);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getPBZAddress(), WsConstant.SP_WALLET_PBZ_ADD);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getInvestmentWallet(), WsConstant.SP_WALLET_INVEST_WALLET);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalROI(), WsConstant.SP_WALLET_TOTAL_ROI);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getEarningWallet(), WsConstant.SP_WALLET_EARNING_WALLET);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getPBZWallet(), WsConstant.SP_WALLET_PBZ_WALLET);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getBTCWallet(), WsConstant.SP_WALLET_BTC_WALLET);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getSendFees(), WsConstant.SP_WALLET_SEND_FEES);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getWithdrawFees(), WsConstant.SP_WALLET_WITHDRAW_FEES);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getPrice(), WsConstant.SP_WALLET_PRICE);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalBinaryTeam(), WsConstant.SP_WALLET_BINARY_TEAM);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalLeftUser(), WsConstant.SP_WALLET_LEFT_USER);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalRightUser(), WsConstant.SP_WALLET_RIGHT_USER);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalDirectUser(), WsConstant.SP_WALLET_DIRECT_USER);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalInvestment(), WsConstant.SP_WALLET_INVESTMENT);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalBinaryIncome(), WsConstant.SP_WALLET_BINARY_INCOME);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalLeftBusiness(), WsConstant.SP_WALLET_LEFT_BUSINESS);
                                AppGlobal.setStringPreference(WalletAmountService.this, info.getTotalRightBusiness(), WsConstant.SP_WALLET_RIGHT_BUSINESS);

                                Intent broadcastIntentDashBoard = new Intent();
                                if (MainActivity.mainActivity != null) {
                                    broadcastIntentDashBoard.setAction(FragmentHome.MyWalletReceiver.PROCESS_RESPONSE);
                                }

                                broadcastIntentDashBoard.addCategory(Intent.CATEGORY_DEFAULT);
                                sendBroadcast(broadcastIntentDashBoard);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    callEvent();
                }

                @Override
                public void onFailure(Call<WalletAmountResponse> call, Throwable t) {
                    callEvent();
                }
            });
        } else {
            callEvent();
        }
    }
}