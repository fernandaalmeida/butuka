package butuka.org.butuka.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import butuka.org.butuka.callback.IDAOResult;
import butuka.org.butuka.constant.Constants;
import butuka.org.butuka.exception.NetworkNotFoundException;
import butuka.org.butuka.model.Complaint;
import butuka.org.butuka.util.Utils;

/**
 * Created by iagobelo on 27/09/2016.
 */

public class ComplaintDAO implements IComplaintDAO {
    private static final String TAG = "ComplaintDAOLog";
    private static volatile ComplaintDAO instance;
    private static RequestQueue mRequestQueue;
    private Context mContext;
    private StringRequest mStringRequest;


    private ComplaintDAO(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
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
    public void insertComplaint(final Complaint complaint, final IDAOResult result) {
        if (Utils.checkConnection(mContext)) {
            mStringRequest = new StringRequest(Request.Method.POST, Constants.URLS.WEB_SERVICE_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            result.onSuccess(response);
                            Log.i(TAG, "Response: " + response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            result.onFailed(error);
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return complaint.toHashMap();
                }
            };
            mRequestQueue.add(mStringRequest);
        } else {
            result.onFailed(new NetworkNotFoundException(Constants.MESSAGES.NO_NETWORK));
        }
    }
}
