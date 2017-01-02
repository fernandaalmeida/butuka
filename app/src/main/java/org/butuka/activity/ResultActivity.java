package org.butuka.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.butuka.R;
import org.butuka.callback.OnCompleteListener;
import org.butuka.constant.Constants;
import org.butuka.controller.ComplaintController;
import org.butuka.model.Complaint;
import org.butuka.model.FileProp;
import org.butuka.model.Task;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {
    // TAG
    private final String TAG = "ResultActivityLog";

    private Button mReturnBt;
    private RelativeLayout mSending;
    private RelativeLayout mSended;
    private RelativeLayout mFailed;

    private ComplaintController mComplaintController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        init();

        mReturnBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSending.setVisibility(View.VISIBLE);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Complaint complaint = new Complaint();
            complaint.setLocation(bundle.getString(Constants.KEYS.LOCATION_KEY));
            complaint.setDate(bundle.getString(Constants.KEYS.DATE_KEY));
            complaint.setTime(bundle.getString(Constants.KEYS.TIME_KEY));
            complaint.setViolator(bundle.getString(Constants.KEYS.VIOLATOR_KEY));
            complaint.setDescription(bundle.getString(Constants.KEYS.DESCRIPTION_KEY));
            String strUri = bundle.getString("uri");

            assert strUri != null;
            if (!strUri.equals("null")) {
                FileProp fileProp = new FileProp(Uri.parse(strUri));
                complaint.setFileProp(fileProp);
            } else {
                complaint.setFileProp(null);
            }
            Log.i(TAG, complaint.toString());

            try {
                mComplaintController.sendComplaint(complaint, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            success();
                        } else {
                            failed();
                        }
                    }
                }, this);
            } catch (IOException e) {
                e.printStackTrace();
                failed();
            }
        } else {
            Log.i(TAG, "bundle nulo.");
        }
    }

    private void init() {
        mReturnBt = (Button) findViewById(R.id.returnBt);
        mSending = (RelativeLayout) findViewById(R.id.sendingContainer);
        mSended = (RelativeLayout) findViewById(R.id.successContainer);
        mFailed = (RelativeLayout) findViewById(R.id.failedContainer);

        mComplaintController = ComplaintController.getInstance();
    }

    private void success() {
        mSended.setVisibility(View.VISIBLE);
        mSending.setVisibility(View.GONE);
        mFailed.setVisibility(View.GONE);
    }

    private void failed() {
        mFailed.setVisibility(View.VISIBLE);
        mSending.setVisibility(View.GONE);
        mSended.setVisibility(View.GONE);
    }
}
