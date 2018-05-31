package com.coindash;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.coindash.Constants.AppGlobal;
import com.coindash.Constants.WsConstant;
import com.coindash.activity.BaseActivity;
import com.coindash.fragment.FragmentBinaryIncome;
import com.coindash.fragment.FragmentDirectReferral;
import com.coindash.fragment.FragmentEarningHistory;
import com.coindash.fragment.FragmentEarningWalletHistory;
import com.coindash.fragment.FragmentGradeBonusDetails;
import com.coindash.fragment.FragmentGradeBonusSummary;
import com.coindash.fragment.FragmentHome;
import com.coindash.fragment.FragmentInvestmentHistory;
import com.coindash.fragment.FragmentLevelSummery;
import com.coindash.fragment.FragmentMyTree;
import com.coindash.fragment.FragmentPBZWalletHistory;
import com.coindash.fragment.FragmentROIHistory;
import com.coindash.fragment.FragmentRewardEligible;
import com.coindash.fragment.FragmentSend;
import com.coindash.fragment.FragmentSetting;
import com.coindash.fragment.FragmentSponsotList;
import com.coindash.fragment.FragmentTicket;
import com.coindash.fragment.FragmentTransactionHistory;
import com.coindash.fragment.FragmentUpdateProfile;
import com.coindash.model.CommonStatusResponse;
import com.coindash.services.WalletAmountService;
import com.coindash.webservice.RestClient;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.coindash.Constants.AppGlobal.toast;

@SuppressWarnings("ALL")
public class MainActivity extends BaseActivity {
    public static ImageView drawerImg, userImage;
    public static TextView drawerName, drawerEmail, mainTitle;
    public static MainActivity mainActivity;
    public static ProgressBar pDialog;

    long lastBackPressTime = 0;
    String mainActivityComeFrom = "";

    private DrawerLayout drawerLayout;
    private ImageView imEditProfile;
    private Toolbar toolbar;

    LinearLayout lvHome, lvSecurity, lvSupport, lvLogOut;
    LinearLayout lvBuyPackage, lvDirectReferral, lvPBZTrnasactionHistory, lvROIHistory, lvBinaryBonus, lvGradeBonusDetails,
            lvGradeBonusSummery, lbPBZWallet, lvInvestmentHistory, lvEarningHistory, lvRewardEligible, lvWithdrawal;

    ExpandableLinearLayout expandGenealogy, expandEWallet, expandReports;
    LinearLayout lvGenealogy, lvEWallet, lvReports;

    LinearLayout lvMyTree, lvLevelSummery, lvSponsorList;
    LinearLayout lvFundTransfer, lvAccountStatement;

    public static void setUserData() {
        drawerName.setText("" + AppGlobal.getStringPreference(mainActivity, WsConstant.SP_LOGIN_NAME));
        drawerEmail.setText("" + AppGlobal.getStringPreference(mainActivity, WsConstant.SP_LOGIN_EMAIL));

        if (!AppGlobal.getStringPreference(mainActivity, WsConstant.SP_LOGIN_PROFILE_PIC).equalsIgnoreCase("")) {
            Glide.with(mainActivity)
                    .load(AppGlobal.getStringPreference(mainActivity, WsConstant.SP_LOGIN_PROFILE_PIC))
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            pDialog.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            pDialog.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(userImage);
        } else {
            pDialog.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = MainActivity.this;

        startService(new Intent(MainActivity.this, WalletAmountService.class));

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            mainActivityComeFrom = bundle.getString("forVerifyAccount");
        }

        init();
        setClickEvent();

        callFragment(new FragmentHome(), getString(R.string.app_name));
    }

    private void init() {

        mainTitle = (TextView) findViewById(R.id.titleActionbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        pDialog = (ProgressBar) findViewById(R.id.progressBar_Main);

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                if (expandGenealogy.isExpanded()) {
                    expandGenealogy.collapse();
                }
                if (expandEWallet.isExpanded()) {
                    expandEWallet.collapse();
                }
                if (expandReports.isExpanded()) {
                    expandReports.collapse();
                }
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


        // toolbar.setNavigationIcon(R.drawable.dr_icon);
        drawerName = (TextView) findViewById(R.id.activity_main_drawer_name);
        drawerEmail = (TextView) findViewById(R.id.activity_main_drawer_email);

        drawerImg = (ImageView) findViewById(R.id.drawerImg);
        userImage = (ImageView) findViewById(R.id.user_image);

        imEditProfile = (ImageView) findViewById(R.id.image_Home_EditProfile);

        expandGenealogy = (ExpandableLinearLayout) findViewById(R.id.expandableLayout_Genealogy);
        expandEWallet = (ExpandableLinearLayout) findViewById(R.id.expandableLayout_EWallet);
        expandReports = (ExpandableLinearLayout) findViewById(R.id.expandableLayout_Reports);

        lvGenealogy = (LinearLayout) findViewById(R.id.linear_Home_Genealogy);
        lvEWallet = (LinearLayout) findViewById(R.id.linear_Home_EWallet);
        lvReports = (LinearLayout) findViewById(R.id.linear_Home_Reports);

        lvHome = (LinearLayout) findViewById(R.id.linear_Home_Home);
        lvSecurity = (LinearLayout) findViewById(R.id.linear_Home_Security);
        lvSupport = (LinearLayout) findViewById(R.id.linear_Home_Support);
        lvLogOut = (LinearLayout) findViewById(R.id.linear_Home_Logout);

        lvFundTransfer = (LinearLayout) findViewById(R.id.linear_Home_FundTransfer);
        lvAccountStatement = (LinearLayout) findViewById(R.id.linear_Home_AccountStatement);

        lvBuyPackage = (LinearLayout) findViewById(R.id.linear_Home_BuyPackage);

        lvMyTree = (LinearLayout) findViewById(R.id.linear_Home_MyTree);
        lvLevelSummery = (LinearLayout) findViewById(R.id.linear_Home_LevelSummery);
        lvSponsorList = (LinearLayout) findViewById(R.id.linear_Home_SponsorList);

        lvDirectReferral = (LinearLayout) findViewById(R.id.linear_Home_DirectReferralBonus);
        lvPBZTrnasactionHistory = (LinearLayout) findViewById(R.id.linear_Home_PbzTransactions);
        lvROIHistory = (LinearLayout) findViewById(R.id.linear_Home_ROI);
        lvBinaryBonus = (LinearLayout) findViewById(R.id.linear_Home_BinaryBonus);
        lvGradeBonusDetails = (LinearLayout) findViewById(R.id.linear_Home_GradeBonusDetails);
        lvGradeBonusSummery = (LinearLayout) findViewById(R.id.linear_Home_GradeBonusSummery);
        lbPBZWallet = (LinearLayout) findViewById(R.id.linear_Home_PBZWalletHistory);
        lvInvestmentHistory = (LinearLayout) findViewById(R.id.linear_Home_InvestmentWalletHistory);
        lvEarningHistory = (LinearLayout) findViewById(R.id.linear_Home_EarningWalletHistory);
        lvRewardEligible = (LinearLayout) findViewById(R.id.linear_Home_RewardEligible);
        lvWithdrawal = (LinearLayout) findViewById(R.id.linear_Home_Withdrawal);

        setUserData();
        setClickEvent();
    }

    private void setClickEvent() {

        lvGenealogy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandGenealogy.isExpanded()) {
                    expandGenealogy.collapse();
                } else {
                    expandGenealogy.expand();
                }
            }
        });

        lvEWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandEWallet.isExpanded()) {
                    expandEWallet.collapse();
                } else {
                    expandEWallet.expand();
                }
            }
        });

        lvReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (expandReports.isExpanded()) {
                    expandReports.collapse();
                } else {
                    expandReports.expand();
                }
            }
        });

        lvSecurity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentSetting());
            }
        });

        imEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentUpdateProfile());
            }
        });

        lvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentHome(), getString(R.string.app_name));
            }
        });

        lvSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentTicket());
            }
        });

        lvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogForLogOut();
            }
        });

        lvFundTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentSend());
            }
        });

        lvBuyPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lvMyTree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentMyTree());
            }
        });

        lvLevelSummery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentLevelSummery());
            }
        });

        lvSponsorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentSponsotList());
            }
        });

        lvDirectReferral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentDirectReferral());
            }
        });

        lvPBZTrnasactionHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentTransactionHistory());
            }
        });

        lvROIHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentROIHistory());
            }
        });

        lvBinaryBonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentBinaryIncome());
            }
        });

        lvGradeBonusDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentGradeBonusDetails());
            }
        });

        lvGradeBonusSummery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentGradeBonusSummary());
            }
        });

        lbPBZWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentPBZWalletHistory());
            }
        });

        lvInvestmentHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentInvestmentHistory());
            }
        });

        lvAccountStatement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentEarningHistory());
            }
        });

        lvEarningHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentEarningHistory());
            }
        });

        lvRewardEligible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentRewardEligible());
            }
        });

        lvWithdrawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callFragment(new FragmentEarningWalletHistory());
            }
        });

        drawerImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
    }

    public void callFragment(Fragment fragment, String title) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.relative_container, fragment)
                .addToBackStack(null)
                .commit();

        if (drawerLayout.isDrawerOpen(Gravity.START))
            drawerLayout.closeDrawers();

        mainTitle.setText(title);
    }

    public void callFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.relative_container, fragment)
                .addToBackStack(null)
                .commit();

        if (drawerLayout.isDrawerOpen(Gravity.START))
            drawerLayout.closeDrawers();
    }

    public boolean getFragmentIntanceForClick() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.relative_container);
        if (fragment instanceof FragmentHome) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onBackPressed() {
        Fragment frag = getSupportFragmentManager().findFragmentById(R.id.relative_container);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else if (frag instanceof FragmentHome) {
//            openDialogForCloseApp();
            if (this.lastBackPressTime < System.currentTimeMillis() - 3000) {
             Snackbar snackbar = Snackbar.make(drawerLayout, "Double Tap to Exit ..!!", Snackbar.LENGTH_SHORT);
                View view = snackbar.getView();
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                snackbar.setActionTextColor(getResources().getColor(R.color.white));
                TextView txt = (TextView) view.findViewById(R.id.snackbar_text);
                txt.setTextColor(getResources().getColor(R.color.white));
                snackbar.show();
                this.lastBackPressTime = System.currentTimeMillis();
                openDialogForCloseApp();
            } else {
                if (toast != null) {
                    toast.cancel();
                }

                MainActivity.this.finish();
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.relative_container);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openDialogForCloseApp() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);

        View vi = getLayoutInflater().inflate(R.layout.dialog_close_app, null, false);
        dialog.setContentView(vi);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();

        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(lp);

        final TextView dialogText = (TextView) dialog.findViewById(R.id.dialog_text);
        dialogText.setText("Are you sure you want to exit ?");

        final TextView closeAppNo = (TextView) dialog.findViewById(R.id.close_app_no);
        closeAppNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        final TextView closeAppYes = (TextView) dialog.findViewById(R.id.close_app_yes);
        closeAppYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
            }
        });

        dialog.show();
    }

    private void openDialogForLogOut() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);

        View vi = getLayoutInflater().inflate(R.layout.dialog_close_app, null, false);
        dialog.setContentView(vi);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();

        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(lp);

        final TextView dialogText = (TextView) dialog.findViewById(R.id.dialog_text);
        dialogText.setText("Are you sure you want to Logout?");

        final TextView closeAppNo = (TextView) dialog.findViewById(R.id.close_app_no);
        closeAppNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        final TextView closeAppYes = (TextView) dialog.findViewById(R.id.close_app_yes);
        closeAppYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutApplication();
            }
        });

        dialog.show();
    }

    public void logoutApplication() {
        if (AppGlobal.isNetwork(MainActivity.this)) {
            Map<String, String> optioMap = new HashMap<>();
            optioMap.put("RegisterId", AppGlobal.getStringPreference(MainActivity.this, WsConstant.SP_LOGIN_REGID));
            optioMap.put("TokenData", AppGlobal.getId(MainActivity.this));

            AppGlobal.showProgressDialog(MainActivity.this);

            new RestClient(MainActivity.this).getInstance().get().logoutApp(optioMap).enqueue(new Callback<CommonStatusResponse>() {
                @Override
                public void onResponse(Call<CommonStatusResponse> call, Response<CommonStatusResponse> response) {
                    AppGlobal.hideProgressDialog(MainActivity.this);
                    if (response.body() != null) {
                        if (response.body().getSuccess() == 1) {
                            AppGlobal.logoutApplication(MainActivity.this);
                        } else {
                            Toast.makeText(MainActivity.this, "No data available", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CommonStatusResponse> call, Throwable t) {
                    AppGlobal.hideProgressDialog(MainActivity.this);
                    Toast.makeText(MainActivity.this, MainActivity.this.getResources().getString(R.string.network_time_out_error), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}