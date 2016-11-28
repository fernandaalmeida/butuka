package butuka.org.butuka.dao;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import butuka.org.butuka.callback.DAOResult;
import butuka.org.butuka.exception.NetworkNotFoundException;
import butuka.org.butuka.util.Utils;

/**
 * Created by iagobelo on 05/10/2016.
 */

abstract class AbstractDAO {
    private static final String TAG = "AbstractDAOLog";
    private static final int TIMEOUT = 6000;
    private static RequestQueue mRequestQueue;
    private Context mContext;
    private StringRequest mStringRequest;

    AbstractDAO(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mContext = context;
    }

    /**
     * @param url    Url onde será feita a requisição via POST.
     * @param map    Hash com os dados que serão enviados.
     * @param result Listener.
     */
    protected void requestByPost(String url, final Map<String, String> map, final DAOResult result) {
        if (Utils.checkConnection(mContext)) {
            mStringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            result.onSuccess(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            result.onFailed(error);
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return map;
                }
            };
            mRequestQueue.add(mStringRequest);
        } else {
            result.onFailed(new NetworkNotFoundException());
        }
    }

    /**
     * @param url Url onde será feita a requisição via GET.
     */
    protected void requestByGet(String url) throws NetworkNotFoundException {
        if (Utils.checkConnection(mContext)) {
            mStringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }
            );
            mRequestQueue.add(mStringRequest);
        } else {
            throw new NetworkNotFoundException();
        }
    }
}
