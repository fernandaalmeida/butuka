package org.butuka.controller;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.butuka.callback.OnCompleteListener;
import org.butuka.model.Complaint;
import org.butuka.model.FileProp;
import org.butuka.model.Task;
import org.butuka.service.ComplaintUploadService;
import org.butuka.service.ServiceGenerator;
import org.butuka.util.FileUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class ComplaintController {
    // Constantes
    private static final int RESULT_SUCCESS = 1;
    private static final int RESULT_FAILED = 0;
    private static ComplaintController instance;
    private static String TAG = "ComplaintController";


    private ComplaintController() {
    }

    public static ComplaintController getInstance() {
        if (instance == null) {
            synchronized (ComplaintController.class) {
                if (instance == null) {
                    instance = new ComplaintController();
                }
            }
        }
        return instance;
    }

    public void sendComplaint(Complaint complaint, final OnCompleteListener<Void> listener,
                              Context context) throws IOException {

        Uri fileUri = complaint.getFileProp().getUri();

        // create upload service client
        ComplaintUploadService service =
                ServiceGenerator.createService(ComplaintUploadService.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(context, fileUri);
        FileProp fileProp = new FileProp(fileUri);

        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        //MultipartBody.Part body =
        //        MultipartBody.Part.createFormData("file", file.getName(), requestFile);

        // finally, execute the request
        Call<ResponseBody> call = service.insertComplaint(
                complaint.getLocation(),
                complaint.getDate(),
                complaint.getTime(),
                complaint.getViolator(),
                complaint.getDescription(),
                fileProp.getMime(context),
                requestFile);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.v("Upload", "success");
                Log.v("Response", response.message());
                listener.onComplete(new Task<Void>(true));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.toString());
                listener.onComplete(new Task<Void>(false));
            }
        });
    }

    /*public void sendComplaint(Complaint complaint, final OnCompleteListener<Void> listener) throws IOException {
        mComplaintDAO.insertComplaint(complaint, new OnCompleteListener<Integer>() {
            @Override
            public void onComplete(Task<Integer> task) {
                if (task.isSuccessful()) {
                    switch (task.getResult()) {
                        case RESULT_SUCCESS:
                            listener.onComplete(new Task<Void>(true));
                            break;

                        case RESULT_FAILED:
                            listener.onComplete(new Task<Void>(task));
                            //Log.i(TAG, task.getException().getMessage());
                            break;
                    }
                }
                listener.onComplete(new Task<Void>(task));
            }
        });
    }*/
}
