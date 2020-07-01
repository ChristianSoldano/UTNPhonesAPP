package edu.utn.utnphonesapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import edu.utn.utnphonesapp.Interface.JsonApi;
import edu.utn.utnphonesapp.LoginActivity;
import edu.utn.utnphonesapp.MainActivity;
import edu.utn.utnphonesapp.R;
import edu.utn.utnphonesapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static edu.utn.utnphonesapp.MainActivity.session;
import static edu.utn.utnphonesapp.config.Constants.API_ROOT_URL;


public class HomeFragment extends Fragment {

    private TextView tvName;
    private TextView tvLastname;
    private TextView tvClientNumber;
    private TextView tvDni;
    private TextView tvAdress;
    private TextView tvCity;
    private TextView tvProvince;

    private ConstraintLayout progressBar;
    private TextView tvMenuUsername;
    private TextView tvMenuEmail;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavigationView navigationView = ((MainActivity)getActivity()).getNavigationView();
        View headerView = navigationView.getHeaderView(0);
        tvMenuUsername = headerView.findViewById(R.id.textViewMenuUsername);
        tvMenuEmail = headerView.findViewById(R.id.textViewMenuEmail);

        progressBar = view.findViewById(R.id.loadingScreen);

        tvName = view.findViewById(R.id.textValueName);
        tvLastname = view.findViewById(R.id.textValueLastname);
        tvClientNumber = view.findViewById(R.id.textValueClientNumber);
        tvDni = view.findViewById(R.id.textValueDni);
        tvAdress = view.findViewById(R.id.textValueNumber);
        tvCity = view.findViewById(R.id.textValueType);
        tvProvince = view.findViewById(R.id.textValueStatus);

        getUser();
    }

    private void getUser() {
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonApi jsonApi = retrofit.create(JsonApi.class);

        Call<User> call = jsonApi.getUser(session.getUserId(), session.getToken());

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);

                if (!response.isSuccessful()) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    if (response.code() == 403) {
                        intent.putExtra("sesion_expired", "expired");
                    }
                    startActivity(intent);
                    ((MainActivity) getActivity()).finish();
                }

                User user = response.body();

                tvMenuUsername.setText(user.getUsername());
                tvMenuEmail.setText(user.getEmail());

                tvName.setText(user.getName());
                tvLastname.setText(user.getLastname());
                Integer clientNumber = 1000000 + Integer.parseInt(user.getIdUser().toString());
                tvClientNumber.setText(clientNumber.toString());
                tvDni.setText(user.getDni().toString());
                tvAdress.setText(user.getAddress());
                tvCity.setText(user.getCity().getCityName());
                tvProvince.setText(user.getCity().getProvince().getProvinceName());

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                progressBar.setVisibility(View.GONE);

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("connection_error", "connection error");
                startActivity(intent);
                ((MainActivity) getActivity()).finish();
            }
        });
    }

}
