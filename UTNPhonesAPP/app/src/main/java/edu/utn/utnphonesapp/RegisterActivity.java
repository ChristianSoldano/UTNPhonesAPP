package edu.utn.utnphonesapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.utn.utnphonesapp.Interface.JsonApi;
import edu.utn.utnphonesapp.dto.LoginResponseDto;
import edu.utn.utnphonesapp.model.City;
import edu.utn.utnphonesapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static edu.utn.utnphonesapp.MainActivity.session;
import static edu.utn.utnphonesapp.config.Constants.API_ROOT_URL;

public class RegisterActivity extends AppCompatActivity {

    private Spinner citiesSpinner;

    private EditText etUsername;
    private EditText etPassword;
    private EditText etEmail;
    private EditText etName;
    private EditText etLastname;
    private EditText etDni;
    private EditText etAddress;
    private Retrofit retrofit;
    public static List<City> cities = new ArrayList<>();
    private ConstraintLayout progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        CardView cardRegister = findViewById(R.id.cardRegister);
        etUsername = findViewById(R.id.editTextRegisterUsername);
        etPassword = findViewById(R.id.editTextRegisterPassword);
        etEmail = findViewById(R.id.editTextRegisterEmail);
        etName = findViewById(R.id.editTextRegisterName);
        etLastname = findViewById(R.id.editTextRegisterLastname);
        etDni = findViewById(R.id.editTextRegisterDni);
        etAddress = findViewById(R.id.editTextRegisterAddress);
        progressBar = findViewById(R.id.loadingScreen);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar.setVisibility(View.VISIBLE);
        retrofit = new Retrofit.Builder()
                .baseUrl(API_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        Call<List<City>> call = jsonApi.getCities();
        call.enqueue(new Callback<List<City>>() {
            @Override
            public void onResponse(Call<List<City>> call, Response<List<City>> response) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
                if (!response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("connection_error", "connection error");
                    startActivity(intent);
                    finish();
                }
                cities = response.body();


                citiesSpinner = findViewById(R.id.citySpinner);
                List<String> options = new ArrayList<>();

                for (City c : cities) {
                    options.add(c.getCityName());
                }
                Collections.sort(options);
                options.add(0, "Seleccione su Ciudad");

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, options);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                citiesSpinner.setAdapter(dataAdapter);
            }

            @Override
            public void onFailure(Call<List<City>> call, Throwable t) {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.putExtra("connection_error", "connection error");
                startActivity(intent);
                finish();
            }
        });


        cardRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }

    public boolean validateFields() {
        int validationPoints = 0;

        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError("El campo Usuario está vacío");
        } else
            validationPoints++;

        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError("El campo Contraseña está vacío");
        } else
            validationPoints++;


        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError("El campo Email está vacío");
        } else
            validationPoints++;

        //check email format
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(etEmail.getText().toString());

        if (!matcher.matches()) {
            etEmail.setError("Formato de Email invalido");
        } else
            validationPoints++;

        if (TextUtils.isEmpty(etName.getText().toString())) {
            etName.setError("El campo Nombre está vacío");
        } else
            validationPoints++;

        if (TextUtils.isEmpty(etLastname.getText().toString())) {
            etLastname.setError("El campo Apellido está vacío");
        } else
            validationPoints++;

        if (TextUtils.isEmpty(etDni.getText().toString())) {
            etDni.setError("El campo Dni está vacío");
        } else
            validationPoints++;

        if (TextUtils.isEmpty(etAddress.getText().toString())) {
            etAddress.setError("El campo Dirección está vacío");
        } else
            validationPoints++;

        if (citiesSpinner.getSelectedItemPosition() == 0) {
            ((TextView) citiesSpinner.getSelectedView()).setError("Seleccione su ciudad");
        } else
            validationPoints++;

        return validationPoints == 9;
    }

    public void click() {


        if (validateFields()) {

            final String username = etUsername.getText().toString().toLowerCase();
            final String password = etPassword.getText().toString();
            String email = etEmail.getText().toString().toLowerCase();
            String name = WordUtils.capitalizeFully(etName.getText().toString());
            String lastname = WordUtils.capitalizeFully(etLastname.getText().toString());
            Integer dni = Integer.parseInt(etDni.getText().toString());
            City city = null;
            for (City c : cities) {
                if (citiesSpinner.getSelectedItem().toString().equals(c.getCityName())) {
                    city = c;
                    break;
                }
            }

            String address = WordUtils.capitalizeFully(etAddress.getText().toString());

            User newUser = User.builder().username(username).password(password).email(email)
                    .name(name).lastname(lastname).dni(dni)
                    .city(city).address(address).build();

            JsonApi jsonApi = retrofit.create(JsonApi.class);
            Call<Void> call = jsonApi.register(newUser);


            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);
                    if (!response.isSuccessful()) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.errorBody().string());
                            if (jsonObject.get("description").equals("Username already exists"))
                                etUsername.setError("El nombre de usuario está en uso");
                            if (jsonObject.get("description").equals("Email already exists"))
                                etEmail.setError("El email ya está en uso");
                            if (jsonObject.get("description").equals("DNI already exists"))
                                etDni.setError("El DNI está en uso");
                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                        return;
                    }

                    //LOGIN

                    JsonApi jsonApi = retrofit.create(JsonApi.class);

                    Call<LoginResponseDto> callLogin = jsonApi.login(username, password);

                    callLogin.enqueue(new Callback<LoginResponseDto>() {
                        @Override
                        public void onResponse(Call<LoginResponseDto> callLogin, Response<LoginResponseDto> response) {
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setVisibility(View.GONE);

                            if (!response.isSuccessful()) {
                                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                progressBar.setVisibility(View.GONE);
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.putExtra("connection_error", "connection error");
                                startActivity(intent);
                                finish();
                            }

                            session.setUserId(response.body().getUserId());
                            session.setUsername(response.body().getUsername());
                            session.setToken(response.body().getToken());
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<LoginResponseDto> callLogin, Throwable t) {
                            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getBaseContext(), "El servicio UTNPhones no está disponible actualmente", Toast.LENGTH_LONG).show();
                        }
                    });
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    progressBar.setVisibility(View.GONE);
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.putExtra("connection_error", "connection error");
                    startActivity(intent);
                    finish();
                }
            });
        }

    }

    public void login_activity(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
