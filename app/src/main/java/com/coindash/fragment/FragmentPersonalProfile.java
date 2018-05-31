package com.coindash.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
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
import com.coindash.MainActivity;
import com.coindash.R;
import com.coindash.model.CommonStatusResponse;
import com.coindash.model.LoginResponse;
import com.coindash.webservice.RestClient;
import com.kbeanie.imagechooser.api.ChooserType;
import com.kbeanie.imagechooser.api.ChosenImage;
import com.kbeanie.imagechooser.api.ChosenImages;
import com.kbeanie.imagechooser.api.ImageChooserListener;
import com.kbeanie.imagechooser.api.ImageChooserManager;
import com.mlsdev.rximagepicker.RxImagePicker;
import com.mlsdev.rximagepicker.Sources;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;
import static com.kbeanie.imagechooser.api.ChooserType.REQUEST_CAPTURE_PICTURE;

@SuppressWarnings("ALL")
public class FragmentPersonalProfile extends Fragment implements ImageChooserListener {
    ProgressBar pDialog;
    private EditText edRegId, edFullName, edUserName, edEmail, edCode, edNumber;
    private TextView tvSave;
    private ImageView imSelect, imDP;
    private String filePath = "";
    private ImageChooserManager imageChooserManager;
    private int chooserType;
    private MultipartBody.Part body;

    public FragmentPersonalProfile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity.mainTitle.setText("Personal Info");
        init(view);
        setClickEvent();
    }

    private void init(View view) {
        edRegId = (EditText) view.findViewById(R.id.ed_ProfileUpDate_RegId);
        edUserName = (EditText) view.findViewById(R.id.ed_ProfileUpDate_UserName);
        edFullName = (EditText) view.findViewById(R.id.ed_ProfileUpDate_Name);
        edEmail = (EditText) view.findViewById(R.id.ed_ProfileUpDate_Email);
        edCode = (EditText) view.findViewById(R.id.ed_ProfileUpDate_Code);
        edNumber = (EditText) view.findViewById(R.id.ed_ProfileUpDate_Number);

        tvSave = (TextView) view.findViewById(R.id.tv_ProfileInfo_Save);

        pDialog = (ProgressBar) view.findViewById(R.id.progressBar_Profile);

        edRegId.setText(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_REGID));
        edUserName.setText(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_USERNAME));
        edFullName.setText(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_NAME));
        edEmail.setText(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_EMAIL));
        edCode.setText(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_COUNTRY_CODE));
        edNumber.setText(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_MOBILE));

        imSelect = (ImageView) view.findViewById(R.id.image_PersonalInfo_Select);
        imDP = (ImageView) view.findViewById(R.id.image_PersonalInfo_DP);

        Glide.with(getActivity())
                .load(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_PROFILE_PIC))
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
                .into(imDP);
    }

    private void setClickEvent() {
        imSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogForChosseImage();
            }
        });

        tvSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doUpdateProfile(AppGlobal.getStringPreference(getActivity(), WsConstant.SP_LOGIN_REGID),
                        edFullName.getText().toString().trim(),
                        edNumber.getText().toString().trim());
            }
        });
    }

    private void openDialogForChosseImage() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(true);

        View vi = getActivity().getLayoutInflater().inflate(R.layout.dialog_open_image, null, false);
        dialog.setContentView(vi);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = dialog.getWindow();

        lp.copyFrom(window.getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setAttributes(lp);


        final TextView closeAppNo = (TextView) dialog.findViewById(R.id.image_camera);
        closeAppNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
                dialog.dismiss();
            }
        });
        final TextView closeAppYes = (TextView) dialog.findViewById(R.id.image_gallary);
        closeAppYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void chooseImage() {
        chooserType = ChooserType.REQUEST_PICK_PICTURE;
        imageChooserManager = new ImageChooserManager(this, ChooserType.REQUEST_PICK_PICTURE, true);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, true);
        imageChooserManager.setExtras(bundle);
        imageChooserManager.setImageChooserListener(this);
        imageChooserManager.clearOldFiles();
        try {
            filePath = imageChooserManager.choose();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void takePicture() {
        RxImagePicker.with(getActivity()).requestImage(Sources.CAMERA).subscribe(new Consumer<Uri>() {
            @Override
            public void accept(@NonNull Uri uri) throws Exception {
                File finalFile = new File(getRealPathFromURI(uri));
                filePath = finalFile.getAbsolutePath();
                Glide.with(getActivity()).load(filePath).into(imDP);
            }
        });
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(TAG, "File Path : " + filePath + "\nChooser Type: " + chooserType);

        if (resultCode == RESULT_OK && (requestCode == ChooserType.REQUEST_PICK_PICTURE)) {
            if (imageChooserManager == null) {
                reinitializeImageChooser();
            }
            imageChooserManager.submit(requestCode, data);
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CAPTURE_PICTURE) {
            try {
                Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                File destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                FileOutputStream fo;

                destination.createNewFile();
                fo = new FileOutputStream(destination);
                fo.write(bytes.toByteArray());
                fo.close();
                filePath = destination.getAbsolutePath();
                imDP.setImageBitmap(thumbnail);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void reinitializeImageChooser() {
        imageChooserManager = new ImageChooserManager(getActivity(), chooserType, true);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Intent.EXTRA_ALLOW_MULTIPLE, true);
        imageChooserManager.setExtras(bundle);
        imageChooserManager.setImageChooserListener(this);
        imageChooserManager.reinitialize(filePath);
    }

    @Override
    public void onImageChosen(final ChosenImage image) {
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                filePath = image.getFilePathOriginal();
                Glide.with(getActivity()).load(image.getFilePathOriginal()).into(imDP);
            }
        });
    }

    @Override
    public void onError(String s) {
        Log.e("Error", ":" + s);
    }

    @Override
    public void onImagesChosen(final ChosenImages images) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "On Images Chosen: " + images.size());
                onImageChosen(images.getImage(0));
            }
        });
    }

    public void doUpdateProfile(String regId, String name, String mobile) {
        if (AppGlobal.isNetwork(getActivity())) {

            AppGlobal.showProgressDialog(getActivity());
            String fileName = "";

            if (!filePath.equalsIgnoreCase("")) {
                File file = new File(filePath);

                if (!file.exists()) {
                    Toast.makeText(getActivity(), "File Not Found", Toast.LENGTH_SHORT).show();
                    return;
                }

                fileName = file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('/') + 1, file.getAbsolutePath().length());
                RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), file);
                body = MultipartBody.Part.createFormData("image", file.getName(), reqFile);
            }

            RequestBody fileName1 = RequestBody.create(MediaType.parse("text/plain"), fileName);
            RequestBody regId1 = RequestBody.create(MediaType.parse("text/plain"), regId);
            RequestBody name1 = RequestBody.create(MediaType.parse("text/plain"), name);
            RequestBody mobile1 = RequestBody.create(MediaType.parse("text/plain"), mobile);
            RequestBody token = RequestBody.create(MediaType.parse("text/plain"), AppGlobal.getId(getActivity()));

            new RestClient(getActivity()).getInstance().get()
                    .updateProfile(body, fileName1, regId1, name1, mobile1, token).enqueue(new Callback<CommonStatusResponse>() {
                @Override
                public void onResponse(Call<CommonStatusResponse> call, Response<CommonStatusResponse> response) {
                    AppGlobal.hideProgressDialog(getActivity());
                    if (response.body() != null) {
                        if (response.body().getSuccess() == 1) {
                            Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();

                            AppGlobal.setStringPreference(getActivity(), edNumber.getText().toString().trim(), WsConstant.SP_LOGIN_MOBILE);
                            AppGlobal.setStringPreference(getActivity(), edFullName.getText().toString().trim(), WsConstant.SP_LOGIN_NAME);

                            MainActivity.setUserData();

                            doUserDetails();

                        } else {
                            Toast.makeText(getActivity(), "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<CommonStatusResponse> call, Throwable t) {
                    try {
                        AppGlobal.hideProgressDialog(getActivity());
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

}