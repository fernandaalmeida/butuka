package org.butuka.dao;

import java.io.IOException;

import org.butuka.callback.OnCompleteListener;
import org.butuka.model.Complaint;

/**
 * Created by iagobelo on 27/09/2016.
 */

public interface IComplaintDAO {
    void insertComplaint(Complaint complaint, OnCompleteListener<Integer> listener) throws IOException;
}
