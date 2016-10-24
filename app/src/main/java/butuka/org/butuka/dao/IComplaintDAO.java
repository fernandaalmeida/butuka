package butuka.org.butuka.dao;

import butuka.org.butuka.callback.DAOResult;
import butuka.org.butuka.model.Complaint;

/**
 * Created by iagobelo on 27/09/2016.
 */

public interface IComplaintDAO {
    void insertComplaint(Complaint complaint, DAOResult result);
}
