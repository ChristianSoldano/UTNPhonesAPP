package edu.utn.utnphonesapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import edu.utn.utnphonesapp.Interface.JsonApi;
import edu.utn.utnphonesapp.dto.LoginResponseDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static edu.utn.utnphonesapp.MainActivity.session;
import static edu.utn.utnphonesapp.config.Constants.API_ROOT_URL;

public class LoginActivity extends AppCompatActivity {

    private CardView cardLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private ConstraintLayout progressBar;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        intent.getExtras();

        if (intent.hasExtra("sesion_expired")) {
            Toast.makeText(this, "Su sesión expiró", Toast.LENGTH_LONG).show();
        } else if (intent.hasExtra("connection_error")) {
            Toast.makeText(this, "El servicio UTNPhones no está disponible actualmente", Toast.LENGTH_LONG).show();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cardLogin = findViewById(R.id.cardView);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        progressBar = findViewById(R.id.loadingScreen);

        cardLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });

    }

    public void click() {

        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("El campo Usuario está vacío");
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("El campo Contraseña está vacío");
        }

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            progressBar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            JsonApi jsonApi = retrofit.create(JsonApi.class);

            Call<LoginResponseDto> call = jsonApi.login(username, password);

            call.enqueue(new Callback<LoginResponseDto>() {

                @Override
                public void onResponse(Call<LoginResponseDto> call, Response<LoginResponseDto> response) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);

                    if (!response.isSuccessful()) {
                        if (response.code() == 403 || response.code() == 404) {
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getBaseContext(), "Usuario o Contraseña invalidos", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        return;
                    }

                    session.setUserId(response.body().getUserId());
                    session.setUsername(response.body().getUsername());
                    session.setToken(response.body().getToken());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<LoginResponseDto> call, Throwable t) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getBaseContext(), "El servicio UTNPhones no está disponible actualmente", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void register_activity(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}
