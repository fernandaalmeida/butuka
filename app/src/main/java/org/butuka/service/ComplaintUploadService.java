package org.butuka.service;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by iagobelodeoliveiravieira on 20/12/16.
 */

public interface ComplaintUploadService {

    @Multipart
    @POST("service.php")
    Call<ResponseBody> insertComplaint(
            @Part("location") String location,
            @Part("date") String date,
            @Part("time") String time,
            @Part("violator") String violator,
            @Part("description") String description,
            @Part("mime") String mime,
            @Part("binary") RequestBody requestBody
    );
}
