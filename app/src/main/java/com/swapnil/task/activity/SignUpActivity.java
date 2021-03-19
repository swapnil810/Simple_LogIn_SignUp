package com.swapnil.task.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etFirstName, etLastName, etEmail, etRole, etPass, etPasswordConfirmation;
    private Button btnSubmit;
    private String strFstName, strLstName, strEmail, strRole, strPass, strConfirmPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initView();
        OnClickListener();
    }

    /**
     * This method is used for performed click operation
     */
    private void OnClickListener() {
        btnSubmit.setOnClickListener(this);
    }

    /**
     * This method is used to initialezed view
     */
    private void initView() {
        etFirstName = findViewById(R.id.et_first_name);
        etLastName = findViewById(R.id.et_last_name);
        etEmail = findViewById(R.id.et_email);
        etPass = findViewById(R.id.et_pass);
        etRole = findViewById(R.id.et_role);
        etPasswordConfirmation = findViewById(R.id.et_password_confirmation);
        btnSubmit = findViewById(R.id.btn_submit);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_submit:
                strFstName = etFirstName.getText().toString().trim();
                strLstName = etLastName.getText().toString().trim();
                strEmail = etEmail.getText().toString().trim();
                strRole = etRole.getText().toString().trim();
                strPass = etPass.getText().toString().trim();
                strConfirmPass = etPasswordConfirmation.getText().toString().trim();

                if (isValid()) {
                    signUpVerification(strFstName, strLstName, strEmail, strRole, strPass, strConfirmPass);
                }


                break;

        }
    }

    /*
  Validation checks for entered information by user
   */
    private boolean isValid() {
        if (strFstName != null && strFstName.equalsIgnoreCase("")) {
            etFirstName.setError(getResources().getString(R.string.empty_first_name));
            etFirstName.requestFocus();
            return false;
        } else if (strLstName != null && strLstName.equalsIgnoreCase("")) {
            etLastName.setError(getResources().getString(R.string.empty_last_name));
            etLastName.requestFocus();
            return false;
        } else if (strEmail != null && strEmail.equalsIgnoreCase("")) {
            etEmail.setError(getResources().getString(R.string.empty_email));
            etEmail.requestFocus();
            return false;
        } else if (!MyConstant.isValidEmail(strEmail)) {
            etEmail.setError(getResources().getString(R.string.please_enter_valid_email_id));
            etEmail.requestFocus();
            return false;
        } else if (strRole != null && strRole.equalsIgnoreCase("")) {
            etRole.setError(getResources().getString(R.string.empty_role));
            etRole.requestFocus();
            return false;
        } else if (strPass != null && strPass.equalsIgnoreCase("")) {
            etPass.setError(getResources().getString(R.string.empty_pass));
            etPass.requestFocus();
            return false;
        } else if (strPass.length() < 6) {
            etPass.setError(getResources().getString(R.string.valid_pass));
            etPass.requestFocus();
            return false;
        } else if (strConfirmPass != null && strConfirmPass.equalsIgnoreCase("")) {
            etPasswordConfirmation.setError(getResources().getString(R.string.empty_pass_confirm));
            etPasswordConfirmation.requestFocus();
            return false;
        } else if (!strPass.equals(strConfirmPass)) {
            etPasswordConfirmation.setError(getResources().getString(R.string.password_do_not));
            etPasswordConfirmation.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void signUpVerification(String strName, String lstName, String strEmail, String strRole,
                                    String strPass, String strConfirmPass) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        HashMap<String, String> map = new HashMap<>();
        map.put(MyConstant.NAME, strName);
        map.put(MyConstant.USER_NAME, strName);
        map.put(MyConstant.LAST_NAME, lstName);
        map.put(MyConstant.EMAIL, strEmail);
        map.put(MyConstant.ROLE, strRole);
        map.put(MyConstant.PASSWORD, strPass);
        map.put(MyConstant.CONFIRM_PASSWORD, strConfirmPass);
        Call<ResponseBody> responseBodyCall = apiService.signup(map);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (response.body() != null) {

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (response.code() == 400) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            if (response.errorBody() != null) {
                              /*  String status = jsonObject.optString(MyConstants.STATUS);
                                String message = jsonObject.optString(MyConstants.MESSAGE);
                                if (status.equalsIgnoreCase("400")) {
                                    Toast.makeText(SignUpActivity.this, "" + message, Toast.LENGTH_SHORT).show();
                                }*/
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