package butuka.org.butuka.dao;

import com.android.volley.VolleyError;

import butuka.org.butuka.callback.IDAOResult;
import butuka.org.butuka.exception.NetworkNotFoundException;
import butuka.org.butuka.model.Complaint;

/**
 * Created by iagobelo on 27/09/2016.
 */

public interface IComplaintDAO {
    void insertComplaint(Complaint complaint) throws NetworkNotFoundException, VolleyError;
}
