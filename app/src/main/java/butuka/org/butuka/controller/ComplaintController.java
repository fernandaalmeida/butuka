package butuka.org.butuka.controller;

import android.content.Context;

import butuka.org.butuka.callback.IDAOResult;
import butuka.org.butuka.callback.IResult;
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

    public void sendComplaint(Complaint complaint, final IResult result) {
        mComplaintDAO.insertComplaint(complaint, new IDAOResult() {
            @Override
            public void onSuccess(String s) {
                if (s.equals("1")) {
                    result.result("Denuncia enviada com sucesso!");
                } else {
                    result.onFailed(new NetworkNotFoundException("Falha ao enviar a denuncia."));
                }
            }

            @Override
            public void onFailed(Exception e) {
                result.onFailed(e);
            }
        });
    }
}
