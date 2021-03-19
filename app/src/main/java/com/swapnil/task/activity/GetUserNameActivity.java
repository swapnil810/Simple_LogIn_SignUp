package com.swapnil.task.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.swapnil.task.R;
import com.swapnil.task.api.ApiClient;
import com.swapnil.task.api.ApiInterface;
import com.swapnil.task.myconstants.MyConstant;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserNameActivity extends AppCompatActivity {
    private TextView tvShowName;
    private String tokenResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_name);
        tokenResult = getIntent().getStringExtra("Token");
        tvShowName = findViewById(R.id.tv_show_name);

        if (tvShowName != null) {
            getUserApi();

        }
    }

    private void getUserApi() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> responseBodyCall = apiService.getUserDetails(MyConstant.MY_TOKEN);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (response.body() != null) {
                            String status = jsonObject.optString(MyConstant.STATUS);
                            String message = jsonObject.optString(MyConstant.MESSAGE);
                            JSONObject jsonObject1 = jsonObject.optJSONObject("data");
                            String fstName = jsonObject1.optString(MyConstant.FIRST_NAME);
                            tvShowName.setText(fstName);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.code() == 400) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            if (response.errorBody() != null) {
                                String status = jsonObject.optString(MyConstant.STATUS);
                                String message = jsonObject.optString(MyConstant.MESSAGE);
                                if (status.equalsIgnoreCase("400")) {
                                    Toast.makeText(GetUserNameActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("sign_up_failure", "sign_up_failure: " + t.getMessage());
            }
        });
    }
}