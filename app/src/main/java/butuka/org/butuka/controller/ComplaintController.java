package butuka.org.butuka.controller;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import butuka.org.butuka.callback.OnCompleteListener;
import butuka.org.butuka.dao.ComplaintDAO;
import butuka.org.butuka.dao.IComplaintDAO;
import butuka.org.butuka.model.Complaint;
import butuka.org.butuka.model.Task;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class ComplaintController {
    // Constantes
    private static final int RESULT_SUCCESS = 1;
    private static final int RESULT_FAILED = 0;
    private static ComplaintController instance;
    private static String TAG = "ComplaintController";

    private IComplaintDAO mComplaintDAO;

    private ComplaintController(Context context) {
        mComplaintDAO = ComplaintDAO.getInstance(context);
    }

    public static ComplaintController getInstance(Context context) {
        if (instance == null) {
            synchronized (ComplaintController.class) {
                if (instance == null) {
                    instance = new ComplaintController(context);
                }
            }
        }
        return instance;
    }

    public void sendComplaint(Complaint complaint, final OnCompleteListener<Void> listener) throws IOException {
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
                            Log.i(TAG, task.getException().getMessage());
                            break;
                    }
                }
                listener.onComplete(new Task<Void>(task));
            }
        });
    }
}
