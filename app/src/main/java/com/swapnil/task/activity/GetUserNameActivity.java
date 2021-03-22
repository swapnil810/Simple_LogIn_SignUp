package com.swapnil.task.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.google.gson.Gson;
import com.swapnil.task.MainActivity;
import com.swapnil.task.R;
import com.swapnil.task.api.ApiClient;
import com.swapnil.task.api.ApiInterface;
import com.swapnil.task.myconstants.CamraPermissionCheck;
import com.swapnil.task.myconstants.ImageFilePath;
import com.swapnil.task.myconstants.MyConstant;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetUserNameActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvShowName, tvLastName;
    private Button btnLogOut,btnUplodePic;
    private CircleImageView ivEditImage;
    private AlertDialog addPicDialog;
    private TextView tvTakePhoto, tvChooseFromGallery, tvCancelD, tvSetErrorTitle, tvRecoverTitle, tvNameField, tvLastNameField, tvUserNameField, tvDobField, tvSecureTitle;
    private static final int PICK_FROM_GALLERY = 1, SELECT_IMAGE_FROM_GALLERY = 104, REQUEST_SLOT_DATA_INTENT = 103;
    public Uri uri, picUri, selectedImageUri;
    private File file;
    private final int CAMERA_CAPTURE = 202;
    public static String cameraPicturePath = "";
    private Bitmap converetdImage;
    private String tokenResult,str, directory_Name = "Task", encodeImage = "", filePath = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_user_name);
        initView();
        OnClickListener();
        getUserApi();

    }

    private void OnClickListener() {
        btnLogOut.setOnClickListener(this);
        btnUplodePic.setOnClickListener(this);
        ivEditImage.performClick();
    }

    private void initView() {
        tokenResult = getIntent().getStringExtra("Token");
        tvShowName = findViewById(R.id.tv_show_name);
        tvLastName = findViewById(R.id.tv_last_name);
        btnLogOut = findViewById(R.id.btn_logOut);
        btnUplodePic = findViewById(R.id.btn_uplode_pic);
        ivEditImage = findViewById(R.id.iv_sign_up_image);
    }

    /**
     * This method is used to get user details.
     */
    private void getUserApi() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> responseBodyCall = apiService.getUserDetails("Bearer" + tokenResult);
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
                            String lstName = jsonObject1.optString(MyConstant.LAST_NAME);
                            tvShowName.setText(fstName);
                            tvLastName.setText(lstName);

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
                Log.d("failure", "failure: " + t.getMessage());
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_logOut:
                logOutApi();
                break;

            case R.id.iv_sign_up_image:
                str = getResources().getString(R.string.setProfilePic);
                pickImageDialog();
                if (ActivityCompat.checkSelfPermission(GetUserNameActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has not been granted.
                    CamraPermissionCheck.requestCameraPermission(GetUserNameActivity.this);
                } else if (ActivityCompat.checkSelfPermission(GetUserNameActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has not been granted.
                    CamraPermissionCheck.requestWriteStoragePermission(GetUserNameActivity.this);
                } else {
                    pickImageDialog();
                }
                break;

            case R.id.btn_uplode_pic:
                str = getResources().getString(R.string.setProfilePic);
                pickImageDialog();
                if (ActivityCompat.checkSelfPermission(GetUserNameActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has not been granted.
                    CamraPermissionCheck.requestCameraPermission(GetUserNameActivity.this);
                } else if (ActivityCompat.checkSelfPermission(GetUserNameActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    // Camera permission has not been granted.
                    CamraPermissionCheck.requestWriteStoragePermission(GetUserNameActivity.this);
                } else {
                    pickImageDialog();
                }
                break;

            case R.id.tvTakePhoto:
                if (CamraPermissionCheck.checkAndRequestPermissions(GetUserNameActivity.this)) {
                    openCamera();
                    addPicDialog.dismiss();
                } else {
                    if (ActivityCompat.checkSelfPermission(GetUserNameActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(GetUserNameActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    }
                    addPicDialog.dismiss();
                    Toast.makeText(GetUserNameActivity.this, "Please allow Permissions from App Info", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tvChooseFromGallery:
                if (CamraPermissionCheck.checkAndRequestPermissions(GetUserNameActivity.this)) {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_IMAGE_FROM_GALLERY);
                    addPicDialog.dismiss();
                } else {
                    if (ActivityCompat.checkSelfPermission(GetUserNameActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(GetUserNameActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                    }
                    addPicDialog.dismiss();
                    Toast.makeText(GetUserNameActivity.this, "Please allow Permissions from App Info", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_cancel:
                addPicDialog.dismiss();
                break;
        }
    }

    /**
     * This method is used to log out from the app.
     */
    private void logOutApi() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> responseBodyCall = apiService.logout("Bearer" + tokenResult);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (response.body() != null) {
                            String status = jsonObject.optString(MyConstant.STATUS);
                            String messae = jsonObject.optString(MyConstant.MESSAGE);
                            Toast.makeText(GetUserNameActivity.this, ""+messae, Toast.LENGTH_SHORT).show();
                            Intent goToLoginPage = new Intent(GetUserNameActivity.this, MainActivity.class);
                            startActivity(goToLoginPage);
                            finishAffinity();
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
                Log.d("failure", "failure: " + t.getMessage());
            }
        });
    }


    private void editPicApi(String filePath) {
        File file = new File(filePath);
        MultipartBody.Part part;
        if (file.exists()) {
            RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
            part = MultipartBody.Part.createFormData(MyConstant.ENCODE_URL, file.getName(), fileReqBody);
        } else {
            part = MultipartBody.Part.createFormData("noimage", "noimage");
        }
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> responseBodyCall = apiService.updateEmail("Bearer" + tokenResult, part);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Log.d("UploadProfilePictureApi", "Response: " + jsonObject);
                        if (response.body() != null) {
                            String status = jsonObject.optString(MyConstant.STATUS);
                            String message = jsonObject.optString(MyConstant.MESSAGE);
                            Toast.makeText(GetUserNameActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                            String fileUrl = jsonObject.optString(MyConstant.FILE_URL);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(GetUserNameActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.code() == 400) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        if (response.errorBody() != null) {
                            String status = jsonObject.optString(MyConstant.STATUS);
                            String message = jsonObject.optString(MyConstant.MESSAGE);
                            Toast.makeText(GetUserNameActivity.this, "" + message, Toast.LENGTH_SHORT).show();

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(GetUserNameActivity.this, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * This method is used to pick image
     */
    private void pickImageDialog() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(GetUserNameActivity.this);
        final View mView = getLayoutInflater().inflate(R.layout.dailog_select_pic, null);
        tvTakePhoto = mView.findViewById(R.id.tvTakePhoto);
        tvChooseFromGallery = mView.findViewById(R.id.tvChooseFromGallery);
        tvCancelD = mView.findViewById(R.id.tv_cancel);
        tvTakePhoto.setOnClickListener(this);
        tvChooseFromGallery.setOnClickListener(this);
        tvCancelD.setOnClickListener(this);
        mBuilder.setView(mView);
        addPicDialog = mBuilder.create();
        addPicDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addPicDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == SELECT_IMAGE_FROM_GALLERY) {
                Uri selectedImageUri = data.getData();
                CropImage.activity(selectedImageUri).setCropShape(CropImageView.CropShape.RECTANGLE).start(GetUserNameActivity.this);
            } else if (requestCode == CAMERA_CAPTURE) {
                CropImage.activity(picUri).setCropShape(CropImageView.CropShape.RECTANGLE).start(GetUserNameActivity.this);
            } else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    try {
                        selectedImageUri = result.getUri();
                        cameraPicturePath = ImageFilePath.getPath(GetUserNameActivity.this, selectedImageUri);
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(GetUserNameActivity.this.getContentResolver(), selectedImageUri);
                        BitmapDrawable ob = new BitmapDrawable(getResources(), bitmap);
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        float bitmapRatio = (float) width / (float) height;
                        if (bitmapRatio > 1) {
                            width = 300;
                            height = (int) (width / bitmapRatio);
                        } else {
                            height = 300;
                            width = (int) (height * bitmapRatio);
                        }
                        converetdImage = Bitmap.createScaledBitmap(bitmap, width, height, true);

                        if (str.equalsIgnoreCase(getResources().getString(R.string.setProfilePic))) {
                            ivEditImage.setImageBitmap(bitmap);
                        }
                        Bitmap bm = BitmapFactory.decodeFile(cameraPicturePath);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG, 50, baos); //bm is the bitmap object
                        byte[] b = baos.toByteArray();
                        encodeImage = Base64.encodeToString(b, Base64.DEFAULT);
                        File f = new File(GetUserNameActivity.this.getCacheDir(), "finalCropImage" + System.currentTimeMillis() + ".jpg");
                        f.createNewFile();
                        FileOutputStream fos = new FileOutputStream(f);
                        fos.write(b);
                        fos.flush();
                        fos.close();
                        filePath = f.getAbsolutePath();
                        editPicApi(filePath);
                        Log.e("file___look", "onActivityResult: " + filePath);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("Exception", "onActivityResult:IOException " + e.getLocalizedMessage());
                    }
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }
            } else if (requestCode == REQUEST_SLOT_DATA_INTENT) {
                if (resultCode == RESULT_OK) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Exception", "onActivityResult:Exception " + e.getLocalizedMessage());
        }
    }

    /*
     * This code is to create storage for captured image for noughat
     */
    private void openCamera() {
        try {
            File projectDirectory = new File(Environment.getExternalStorageDirectory() + File.separator + directory_Name);
            if (!projectDirectory.exists()) {
                projectDirectory.mkdir();
            } else {
                for (File file : projectDirectory.listFiles()) {
                    if (file.getName().startsWith(getResources().getString(R.string.tmp_))) {
                        file.delete();
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(GetUserNameActivity.this, "com.swapnil.task.fileprovider", new File(projectDirectory, "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg"));
                file = new File(projectDirectory, getResources().getString(R.string.cropped) + String.valueOf(System.currentTimeMillis()) + ".jpg");
                file.setWritable(true);
                picUri = FileProvider.getUriForFile(GetUserNameActivity.this, "com.swapnil.task.fileprovider", file);
            } else {
                uri = Uri.fromFile(new File(projectDirectory, getResources().getString(R.string.tmp_) + String.valueOf(System.currentTimeMillis()) + ".jpg"));
                file = new File(projectDirectory, getResources().getString(R.string.croped_) + String.valueOf(System.currentTimeMillis()) + ".jpg");
                file.setWritable(true);
                picUri = Uri.fromFile(file);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri);
            startActivityForResult(intent, CAMERA_CAPTURE);
        } catch (Exception e) {
            Log.d("exception_camera", e.toString());
            e.printStackTrace();
        }
    }
}