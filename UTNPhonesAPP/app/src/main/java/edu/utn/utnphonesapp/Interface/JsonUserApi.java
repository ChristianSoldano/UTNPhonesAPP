package edu.utn.utnphonesapp.Interface;

import java.util.List;

import edu.utn.utnphonesapp.model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonUserApi {

    @GET("api/user/")
    Call<List<User>> getUser();
}
