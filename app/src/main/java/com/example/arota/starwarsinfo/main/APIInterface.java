package com.example.arota.starwarsinfo.main;

import com.example.arota.starwarsinfo.main.Models.PeopleWrapper;
import com.example.arota.starwarsinfo.main.Models.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("api/people")
    Call<Person> doGetPerson();

    @GET("api/people/")
    Call<PeopleWrapper> doGetPeople();





    /*
    @POST("/api/users")
    Call<User> createUser(@Body User user);

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @FormUrlEncoded
    @POST("/api/users?")
    Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);
    */
}
