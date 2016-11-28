package butuka.org.butuka.dao;

import android.content.Context;

import org.json.JSONException;

import java.io.IOException;

import butuka.org.butuka.callback.DAOResult;
import butuka.org.butuka.callback.OnCompleteListener;
import butuka.org.butuka.constant.Constants;
import butuka.org.butuka.exception.DAOException;
import butuka.org.butuka.model.Complaint;
import butuka.org.butuka.model.Task;
import butuka.org.butuka.parse.JSONParser;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class ComplaintDAO extends AbstractDAO implements IComplaintDAO {
    private static final String TAG = "ComplaintDAOLog";
    private static volatile ComplaintDAO instance;
    private JSONParser mJsonParser;
    private Context mContext;

    private ComplaintDAO(Context context) {
        super(context);
        mContext = context;
        mJsonParser = new JSONParser();
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
    public void insertComplaint(Complaint complaint, final OnCompleteListener<Integer> listener) throws IOException {
        super.requestByPost(Constants.URLS.WEB_SERVICE_URL, complaint.toHashMap(), new DAOResult() {
            @Override
            public void onSuccess(String s) {
                try {
                    int result = mJsonParser.parseResult(s);
                    listener.onComplete(new Task<>(true, result));
                } catch (JSONException e) {
                    listener.onComplete(new Task<Integer>(false, new DAOException(e)));
                }
            }

            @Override
            public void onFailed(Exception e) {
                e.printStackTrace();
                listener.onComplete(
                        new Task<Integer>(false, new DAOException(Constants.MESSAGES.FAILED, e)));
            }
        });
    }
}
