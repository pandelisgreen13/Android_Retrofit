package com.example.padpad.test_myphp;

/**
 * Created by PadPad on 14/10/2017.
 */

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RequestInterface {

   @POST("hello/")
    Call<ServerResponse> operation(@Body ServerRequest request);

}