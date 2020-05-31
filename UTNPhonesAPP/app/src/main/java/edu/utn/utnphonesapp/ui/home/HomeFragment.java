package edu.utn.utnphonesapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

import edu.utn.utnphonesapp.Interface.JsonUserApi;
import edu.utn.utnphonesapp.R;
import edu.utn.utnphonesapp.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textViewHome = view.findViewById(R.id.textViewHome);
        getUsers(textViewHome);
    }

    private void getUsers(final TextView textViewHome){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.108:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonUserApi jsonUserApi = retrofit.create(JsonUserApi.class);

        Call<List<User>> call = jsonUserApi.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(!response.isSuccessful()){
                    textViewHome.setText("Codigo de respuesta: " +response.code());
                    return;
                }
                List<User> userList = response.body();
                for(User user : userList){
                    String content = "";
                    content += "idUser:" + user.getIdUser() + "\n";
                    content += "username:" + user.getUsername() + "\n";
                    content += "password:" + user.getPassword() + "\n";
                    content += "dni:" + user.getDni() + "\n";
                    content += "\n\n\n";
                    textViewHome.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                textViewHome.setText(t.getMessage());
            }
        });
    }
}
