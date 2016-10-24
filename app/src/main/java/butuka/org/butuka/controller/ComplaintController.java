package butuka.org.butuka.controller;

import android.content.Context;

import butuka.org.butuka.callback.DAOResult;
import butuka.org.butuka.callback.OnCompleteListener;
import butuka.org.butuka.dao.ComplaintDAO;
import butuka.org.butuka.dao.IComplaintDAO;
import butuka.org.butuka.model.Complaint;
import butuka.org.butuka.model.Task;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class ComplaintController {
    private static ComplaintController instance;
    private IComplaintDAO mComplaintDAO;
    private Task mTask;

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

    public void sendComplaint(Complaint complaint, final OnCompleteListener listener) {
        mComplaintDAO.insertComplaint(complaint, new DAOResult() {
            @Override
            public void onSuccess(String s) {
                mTask = new Task.Builder(true).build();
                listener.onComplete(mTask);
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
                mTask = new Task.Builder(false)
                        .withMessage(e.getMessage())
                        .build();
                listener.onComplete(mTask);
            }
        });
    }
}
