package com.swapnil.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.swapnil.task.activity.GetUserNameActivity;
import com.swapnil.task.activity.SignUpActivity;
import com.swapnil.task.api.ApiClient;
import com.swapnil.task.api.ApiInterface;
import com.swapnil.task.myconstants.MyConstant;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private String strEmail, strPass;
    private EditText edtEmail, etPassword;
    private TextView tvOpenReg, tvGetUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        OnClickListener();
    }

    /**
     * This method is used for performed click operation
     */
    private void OnClickListener() {
        btnSubmit.setOnClickListener(this);
        tvOpenReg.setOnClickListener(this);
        tvGetUser.setOnClickListener(this);
    }

    /**
     * This method is used to initialezed view
     */
    private void initView() {
        edtEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnSubmit = findViewById(R.id.btn_login);
        tvOpenReg = findViewById(R.id.tv_open_reg);
        tvGetUser = findViewById(R.id.tv_get_user);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                strEmail = edtEmail.getText().toString().trim();
                strPass = etPassword.getText().toString().trim();

                if (isValid()) {
                    logInApi(strEmail, strPass);
                }
                break;

            case R.id.tv_open_reg:

                Intent openNewPge = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(openNewPge);

                break;

            case R.id.tv_get_user:


                break;
        }
    }

    /**
     * This method is used to validate a particular filed
     *
     * @return
     */
    private boolean isValid() {
        if (strEmail != null && strEmail.equalsIgnoreCase("")) {
            edtEmail.setError(getResources().getString(R.string.empty_email));
            edtEmail.requestFocus();
            return false;
        } else if (!MyConstant.isValidEmail(strEmail)) {
            edtEmail.setError(getResources().getString(R.string.please_enter_valid_email_id));
            edtEmail.requestFocus();
            return false;
        } else if (strPass != null && strPass.equalsIgnoreCase("")) {
            etPassword.setError(getResources().getString(R.string.empty_pass));
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is used to calling web service of log
     *
     * @param strEmail
     * @param strPass
     */
    private void logInApi(String strEmail, String strPass) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> map = new HashMap<>();
        map.put(MyConstant.EMAIL, strEmail);
        map.put(MyConstant.PASSWORD, strPass);
        Call<ResponseBody> responseBodyCall = apiService.login(map);
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
                            String token = jsonObject1.optString(MyConstant.API_TOKEN);

                            Intent getUserShown = new Intent(MainActivity.this, GetUserNameActivity.class);
                            getUserShown.putExtra("Token", token);
                            startActivity(getUserShown);


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
                                    Toast.makeText(MainActivity.this, "" + message, Toast.LENGTH_SHORT).show();
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