package butuka.org.butuka.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.io.IOException;

import butuka.org.butuka.R;
import butuka.org.butuka.callback.OnCompleteListener;
import butuka.org.butuka.constant.Constants;
import butuka.org.butuka.controller.ComplaintController;
import butuka.org.butuka.model.Complaint;
import butuka.org.butuka.model.File;
import butuka.org.butuka.model.Task;
import butuka.org.butuka.util.Utils;

public class ResultActivity extends AppCompatActivity {

    public final String TAG = "ResultActivityLog";

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

            if (!strUri.equals("null")) {
                Uri uri = Uri.parse(strUri);
                File file = new File(uri);

                try {
                    complaint.setBase64(file.toBase64(ResultActivity.this));
                    complaint.setFileMime(file.getMime(ResultActivity.this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Log.i(TAG, complaint.toString());

            mComplaintController.sendComplaint(complaint, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    if (task.isSuccessful()) {
                        success();
                    } else {
                        failed();
                        Utils.showMessage(ResultActivity.this, task.getException().getMessage());
                        task.getException().printStackTrace();
                    }
                }
            });
        } else {
            Log.i(TAG, "bundle nulo.");
        }
    }

    private void init() {
        mReturnBt = (Button) findViewById(R.id.returnBt);
        mSending = (RelativeLayout) findViewById(R.id.sendingContainer);
        mSended = (RelativeLayout) findViewById(R.id.successContainer);
        mFailed = (RelativeLayout) findViewById(R.id.failedContainer);

        mComplaintController = ComplaintController.getInstance(this);
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
