package butuka.org.butuka.controller;

import android.content.Context;

import com.android.volley.VolleyError;

import butuka.org.butuka.dao.ComplaintDAO;
import butuka.org.butuka.dao.IComplaintDAO;
import butuka.org.butuka.exception.NetworkNotFoundException;
import butuka.org.butuka.model.Complaint;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class ComplaintController {
    private IComplaintDAO mComplaintDAO;

    public ComplaintController(Context context) {
        mComplaintDAO = ComplaintDAO.getInstance(context);
    }

    public void sendComplaint(Complaint complaint) throws NetworkNotFoundException, VolleyError {
        mComplaintDAO.insertComplaint(complaint);
    }
}
