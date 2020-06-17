package edu.utn.utnphonesapp.Interface;

import edu.utn.utnphonesapp.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface JsonUserApi {


    @Headers("Authorization: eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxMDMiLCJzdWIiOiJzaWRlcmpvbmFzMiIsImF1dGhvcml0aWVzIjpbIlJPTEVfQ0xJRU5UIl0sImlhdCI6MTU5MjQyNzQ2OH0.6j5IQJbLayDX_QPVrUI3gSBNKBw18ww1sT0_s0Z1PLLA3_JFwHhLTmQZrec-_qtBgh9eKytzXRXmzfefMs7WQg")
    @GET("api/user/{userId}")
    Call<User> getUser(@Path("userId") Integer userId);

}
