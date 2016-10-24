package butuka.org.butuka.dao;

import android.content.Context;

import butuka.org.butuka.callback.DAOResult;
import butuka.org.butuka.constant.Constants;
import butuka.org.butuka.model.Complaint;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class ComplaintDAO extends AbstractDAO implements IComplaintDAO {
    private static final String TAG = "ComplaintDAOLog";
    private static volatile ComplaintDAO instance;
    private Context mContext;

    private ComplaintDAO(Context context) {
        super(context);
        mContext = context;
    }

    public static ComplaintDAO getInstance(Context context) {
        if (instance == null) {
            synchronized (ComplaintDAO.class) {
                if (instance == null) {
                    instance = new ComplaintDAO(context);
                }
            }
        }
        return instance;
    }

    @Override
    public void insertComplaint(Complaint complaint, final DAOResult result) {
        super.requestByPost(Constants.URLS.WEB_SERVICE_URL, complaint.toHashMap(), new DAOResult() {
            @Override
            public void onSuccess(String s) {
                result.onSuccess(s);
            }

            @Override
            public void onFailed(Exception e) {
                result.onFailed(e);
            }
        });
    }
}
