package com.coindash.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.coindash.Constants.AppGlobal;
import com.coindash.R;
import com.coindash.model.CommonStatusResponse;
import com.coindash.model.CountryResponse;
import com.coindash.webservice.RestClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressWarnings("ALL")
public class SignUpActivity extends BaseActivity {

    ImageView imBack;
    Spinner spCountry, spPosition;
    ArrayList<CountryResponse.Info> listCityMain;
    ArrayList<String> listCity;
    private EditText edFirstName, edLastName, edtUserName,  edtEmail, edtContactNumber, edtPassword, edtConfirmPassword;
    private EditText edCode, edSponsor;
    private TextView txtSignIn;
    private TextView txtSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();
        doGetCountry();
    }

    private void init() {
        edFirstName = (EditText) findViewById(R.id.ed_Register_Name);
        edLastName = (EditText) findViewById(R.id.ed_Register_LastName);
        edtUserName = (EditText) findViewById(R.id.ed_Register_UserName);
        edtEmail = (EditText) findViewById(R.id.ed_Register_Email);
        edtContactNumber = (EditText) findViewById(R.id.ed_Register_MobileNo);
        edtPassword = (EditText) findViewById(R.id.ed_Register_Password);
        edtConfirmPassword = (EditText) findViewById(R.id.ed_Register_ConfirmPassword);
        edCode = (EditText) findViewById(R.id.ed_Register_MobileCode);
        edSponsor = (EditText) findViewById(R.id.ed_Register_Sponsor);

        spPosition = (Spinner) findViewById(R.id.spinner_Position);
        ArrayList<String> listPosition = new ArrayList<>();
        listPosition.add("Left");
        listPosition.add("Right");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_text, listPosition);
        adapter.setDropDownViewResource(R.layout.spinner_text_drop_down);
        spPosition.setAdapter(adapter);

        spCountry = (Spinner) findViewById(R.id.spinner_Country);
        spCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                edCode.setText("+" + listCityMain.get(i).getCountryCode());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                edCode.setText("+" + listCityMain.get(0).getCountryCode());
            }
        });

        imBack = (ImageView) findViewById(R.id.image_SignUp_Back);

        txtSignUp = (TextView) findViewById(R.id.signup_signup);
        txtSignIn = (TextView) findViewById(R.id.signup_signin);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edFirstName.getText().toString().trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter First Name", Toast.LENGTH_SHORT).show();
                } else if (edLastName.getText().toString().trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Last Name", Toast.LENGTH_SHORT).show();
                } else if (edtUserName.getText().toString().trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter UserName", Toast.LENGTH_SHORT).show();
                } else if (edtContactNumber.getText().toString().trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter ContactNumber", Toast.LENGTH_SHORT).show();
                } else if (edtEmail.getText().toString().trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().toString().trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                } else if (edtConfirmPassword.getText().toString().trim().equals("")) {
                    Toast.makeText(SignUpActivity.this, "Please Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim())) {
                    Toast.makeText(SignUpActivity.this, "Password mismatch", Toast.LENGTH_SHORT).show();
                } else if (edtContactNumber.getText().toString().length() != 10) {
                    Toast.makeText(SignUpActivity.this, "Enter valid mobile number", Toast.LENGTH_SHORT).show();
                } else if (edtUserName.getText().toString().contains(" ")) {
                    Toast.makeText(SignUpActivity.this, "UserName does not allow space", Toast.LENGTH_SHORT).show();
                } else if (edCode.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(SignUpActivity.this, "Please enter Code", Toast.LENGTH_SHORT).show();
                } else if (edSponsor.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(SignUpActivity.this, "Please enter Sponsor ID", Toast.LENGTH_SHORT).show();
                } else {
                    doRegistartion(edFirstName.getText().toString(),edLastName.getText().toString(),
                            edtUserName.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString(),
                            edtContactNumber.getText().toString(), edSponsor.getText().toString());
                }
            }
        });
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpActivity.this.finish();
            }
        });
    }

    public void doRegistartion(String firstName, String lastName, String UserName, String Email, String Password, String contactNumber, String sponsor) {
        if (AppGlobal.isNetwork(SignUpActivity.this)) {

            AppGlobal.showProgressDialog(SignUpActivity.this);
            Map<String, String> optioMap = new HashMap<>();
            optioMap.put("FirstName", firstName);
            optioMap.put("LastName", lastName);
            optioMap.put("UserName", UserName);
            optioMap.put("EmailID", Email);
            optioMap.put("Password", Password);
            optioMap.put("MobileNo", contactNumber);
            optioMap.put("SponsorID", sponsor);
            optioMap.put("TokenData", AppGlobal.getId(SignUpActivity.this));
            optioMap.put("Country", spCountry.getSelectedItem().toString());
            optioMap.put("Position", spPosition.getSelectedItem().toString());

            new RestClient(SignUpActivity.this).getInstance().get().UserRegisterNew(optioMap).enqueue(new Callback<CommonStatusResponse>() {
                @Override
                public void onResponse(Call<CommonStatusResponse> call, Response<CommonStatusResponse> response) {
                    AppGlobal.hideProgressDialog(SignUpActivity.this);
                    if (response.body() != null) {
                        if (response.body().getSuccess() == 1) {
                            openDialogForResendLink(response.body().getMsg());
                        } else {
                            Toast.makeText(SignUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CommonStatusResponse> call, Throwable t) {
                    try {
                        AppGlobal.hideProgressDialog(SignUpActivity.this);
                        Toast.makeText(SignUpActivity.this, getString(R.string.network_time_out_error), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            Toast.makeText(SignUpActivity.this, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void openDialogForResendLink(final String regId) {
        final Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);

        View vi = getLayoutInflater().inflate(R.layout.dialog_resend_link, null, false);
        dialog.setContentView(vi);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();

        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(lp);

        final TextView txtForgotPassword = (TextView) dialog.findViewById(R.id.tv_DialogResendLink_Resend);

        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doResendLink(regId);
            }
        });

        dialog.show();
    }

    public void doResendLink(String regId) {
        if (AppGlobal.isNetwork(SignUpActivity.this)) {

            AppGlobal.showProgressDialog(SignUpActivity.this);
            Map<String, String> optioMap = new HashMap<>();
            optioMap.put("RegisterId", regId);
            optioMap.put("TokenData", AppGlobal.getId(SignUpActivity.this));

            new RestClient(SignUpActivity.this).getInstance().get().resendLink(optioMap).enqueue(new Callback<CommonStatusResponse>() {
                @Override
                public void onResponse(Call<CommonStatusResponse> call, Response<CommonStatusResponse> response) {
                    AppGlobal.hideProgressDialog(SignUpActivity.this);
                    if (response.body() != null) {
                        if (response.body().getSuccess() == 1) {
                            Toast.makeText(SignUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CommonStatusResponse> call, Throwable t) {
                    try {
                        AppGlobal.hideProgressDialog(SignUpActivity.this);
                        Toast.makeText(SignUpActivity.this, getString(R.string.network_time_out_error), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            Toast.makeText(SignUpActivity.this, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
    }

    public void doGetCountry() {
        if (AppGlobal.isNetwork(SignUpActivity.this)) {

            AppGlobal.showProgressDialog(SignUpActivity.this);

            new RestClient(SignUpActivity.this).getInstance().get().getCountry().enqueue(new Callback<CountryResponse>() {
                @Override
                public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                    AppGlobal.hideProgressDialog(SignUpActivity.this);
                    if (response.body() != null) {
                        if (response.body().getSuccess() == 1) {

                            listCityMain = new ArrayList<CountryResponse.Info>();
                            listCity = new ArrayList<String>();

                            listCityMain = response.body().getInfo();
                            for (int i = 0; i < listCityMain.size(); i++) {
                                listCity.add(listCityMain.get(i).getCountryName());
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_text, listCity);
                            adapter.setDropDownViewResource(R.layout.spinner_text_drop_down);
                            spCountry.setAdapter(adapter);

                            edCode.setText("+" + listCityMain.get(0).getCountryCode());
                        } else {
                            Toast.makeText(SignUpActivity.this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CountryResponse> call, Throwable t) {
                    try {
                        AppGlobal.hideProgressDialog(SignUpActivity.this);
                        Toast.makeText(SignUpActivity.this, getString(R.string.network_time_out_error), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                    }
                }
            });
        } else {
            Toast.makeText(SignUpActivity.this, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        }
    }
}
