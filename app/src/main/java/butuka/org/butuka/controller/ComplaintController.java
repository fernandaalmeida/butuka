package butuka.org.butuka.controller;

import android.content.Context;

import butuka.org.butuka.callback.IDAOResult;
import butuka.org.butuka.callback.IResult;
import butuka.org.butuka.constant.Constants;
import butuka.org.butuka.dao.ComplaintDAO;
import butuka.org.butuka.dao.IComplaintDAO;
import butuka.org.butuka.exception.NetworkNotFoundException;
import butuka.org.butuka.model.Complaint;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class ComplaintController {
    private IComplaintDAO mComplaintDAO;
    private static final String SUCCESS = "1";

    public ComplaintController(Context context) {
        mComplaintDAO = ComplaintDAO.getInstance(context);
    }

    public void sendComplaint(Complaint complaint, final IResult result) {
        mComplaintDAO.insertComplaint(complaint, new IDAOResult() {
            @Override
            public void onSuccess(String s) {
                if (s.equals(SUCCESS)) {
                    result.success();
                } else {
                    result.onFailed(Constants.MESSAGES.FAILED);
                }
            }

            @Override
            public void onFailed(Exception e) {
                if (e instanceof NetworkNotFoundException) {
                    result.onFailed(Constants.MESSAGES.NO_NETWORK);
                } else {
                    result.onFailed(Constants.MESSAGES.FAILED);
                }
            }
        });
    }
}
