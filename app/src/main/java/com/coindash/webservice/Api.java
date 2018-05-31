package com.coindash.webservice;




import com.coindash.model.BinaryIncomeResponse;
import com.coindash.model.CommonStatusResponse;
import com.coindash.model.CountryResponse;
import com.coindash.model.DirectReferralResponse;
import com.coindash.model.EarningWalletHistoryResponse;
import com.coindash.model.EarningWithdrawHistoryResponse;
import com.coindash.model.GetReplyTicketResponse;
import com.coindash.model.GradeBonusResponse;
import com.coindash.model.GradeBonusSummaryResponse;
import com.coindash.model.InvestmentHistoryResponse;
import com.coindash.model.LoginResponse;
import com.coindash.model.PBZWalletHistoryResponse;
import com.coindash.model.ROIHistoryResponse;
import com.coindash.model.RewardEligibleResponse;
import com.coindash.model.SponsorListResponse;
import com.coindash.model.TicketHistoryResponse;
import com.coindash.model.TransactionsResponse;
import com.coindash.model.WalletAmountResponse;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

@SuppressWarnings("ALL")
public interface Api {


    @POST("CountryList.php")
    Call<CountryResponse> getCountry();

    @POST("UserRegister.php")
    Call<CommonStatusResponse> UserRegisterNew(@Body Map<String, String> postSellparams);

    @POST("ResendMail.php")
    Call<CommonStatusResponse> resendLink(@Body Map<String, String> postSellparams);

    @POST("ForgotPassword.php")
    Call<CommonStatusResponse> ResetPassword(@Body Map<String, String> postSellparams);

    @POST("UserLogin.php")
    Call<LoginResponse> UserLoginNew(@Body Map<String, String> postSellparams);

    @POST("UserAllData.php")
    Call<LoginResponse> getUserDetails(@Body Map<String, String> postSellparams);

    @POST("WalletAmount.php")
    Call<WalletAmountResponse> WalletLiveAmount(@Body Map<String, String> postBuyparams);

    @POST("SponsorList.php")
    Call<SponsorListResponse> getSponsorList(@Body Map<String, String> postBuyparams);

    @POST("PBZWalletHistory.php")
    Call<PBZWalletHistoryResponse> getPBZWalletHistory(@Body Map<String, String> postBuyparams);

    @POST("ChangePassword.php")
    Call<CommonStatusResponse> changePassword(@Body Map<String, String> postSellparams);

    @Multipart
    @POST("UpdateProfile.php")
    Call<CommonStatusResponse> updateProfile(@Part MultipartBody.Part image,
                                             @Part("ProfilePicture") RequestBody filename,
                                             @Part("RegisterId") RequestBody register_id,
                                             @Part("FullName") RequestBody name,
                                             @Part("MobileNo") RequestBody mobile,
                                             @Part("TokenData") RequestBody validData);

    @POST("UpdateContact.php")
    Call<CommonStatusResponse> updateContactInfo(@Body Map<String, String> params);

    @POST("ReferralIncome.php")
    Call<DirectReferralResponse> getDirectReferral(@Body Map<String, String> params);

    @POST("ROIHistory.php")
    Call<ROIHistoryResponse> getROI(@Body Map<String, String> params);

    @POST("BinaryIncome.php")
    Call<BinaryIncomeResponse> getBinaryIncome(@Body Map<String, String> params);

    @POST("GradeBonusHistory.php")
    Call<GradeBonusResponse> getGradeBonus(@Body Map<String, String> params);

    @POST("GradeBonusSummary.php")
    Call<GradeBonusSummaryResponse> getGradeBonusSummary(@Body Map<String, String> params);

    @POST("PBZTransactionHistory.php")
    Call<TransactionsResponse> getTransactions(@Body Map<String, String> params);

    @POST("EarningWithdrawHistory.php")
    Call<EarningWithdrawHistoryResponse> getEarningWallet(@Body Map<String, String> params);

    @POST("PBZTransaction.php")
    Call<CommonStatusResponse> soSend(@Body Map<String, String> postSellparams);

    @POST("EarningWithdrawRequest.php")
    Call<CommonStatusResponse> doEarningWithdraw(@Body Map<String, String> params);

    @POST("InvestmentHistory.php")
    Call<InvestmentHistoryResponse> getInvestmentHistory(@Body Map<String, String> params);

    @POST("AchieversHistory.php")
    Call<RewardEligibleResponse> getRewardEligible(@Body Map<String, String> params);

    @POST("EarningWalletHistory.php")
    Call<EarningWalletHistoryResponse> getEarningWalletHistory(@Body Map<String, String> params);

    @POST("TicketHistory.php")
    Call<TicketHistoryResponse> getTicketHistory(@Body Map<String, String> params);

    @POST("AllTicketHistory.php")
    Call<GetReplyTicketResponse> getSingleTicketHistory(@Body Map<String, String> params);

    @Multipart
    @POST("CreateTicket.php")
    Call<CommonStatusResponse> createTicket(@Part MultipartBody.Part image,
                                            @Part("ProfilePicture") RequestBody ProfilePicture,
                                            @Part("RegisterId") RequestBody register_id,
                                            @Part("Subject") RequestBody subject,
                                            @Part("Message") RequestBody message,
                                            @Part("TokenData") RequestBody validData);

    @Multipart
    @POST("ReplyTicket.php")
    Call<CommonStatusResponse> replyTicket(@Part MultipartBody.Part image,
                                           @Part("ProfilePicture") RequestBody subject,
                                           @Part("RegisterId") RequestBody register_id,
                                           @Part("TicketNumber") RequestBody tck_number,
                                           @Part("Message") RequestBody message,
                                           @Part("TokenData") RequestBody validData);

    @POST("AndroidVersion.php")
    Call<CommonStatusResponse> getVersion();

    @POST("UserLogout.php")
    Call<CommonStatusResponse> logoutApp(@Body Map<String, String> postSellparams);

    /*@POST("TransactionHistory.php")
    Call<TransactionResponse> getHistory(@Body Map<String, String> postSellparams);

    @POST("ReceiveOtherTransaction.php")
    Call<CommonStatusResponse> depositData(@Body Map<String, String> postBuyparams);*/
}
