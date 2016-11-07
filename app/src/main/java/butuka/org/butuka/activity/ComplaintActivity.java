package butuka.org.butuka.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import butuka.org.butuka.R;
import butuka.org.butuka.callback.OnCompleteListener;
import butuka.org.butuka.constant.Constants;
import butuka.org.butuka.controller.ComplaintController;
import butuka.org.butuka.model.Complaint;
import butuka.org.butuka.model.File;
import butuka.org.butuka.model.Task;
import butuka.org.butuka.util.Utils;

public class ComplaintActivity extends AppCompatActivity {

    // Constantes
    private static final int DATA_RESULT = 10;
    private static final int MAP_RESULT = 15;
    private static final String TAG = "ComplaintActivityLog";

    // Views
    private EditText mLocationEdt,
            mDateEdt,
            mTimeEdt,
            mViolatorEdt,
            mDescriptionEdt;
    private ImageView mMapBt;
    private RelativeLayout mAddFileBt;
    private RelativeLayout mSendBtn;
    private LinearLayout mFileNameContainer;
    private ProgressBar mProgressBar;
    private TextView mFileNameTv;

    private ComplaintController mComplaintController;
    private Complaint mComplaint;
    private File mFile;
    private LatLng mLatLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        init();

        mAddFileBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFileFromSdCard();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Envia denuncia.
        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);

                mComplaint = new Complaint();
                mComplaint.setLocation(mLocationEdt.getText().toString());
                mComplaint.setDate(mDateEdt.getText().toString());
                mComplaint.setTime(mTimeEdt.getText().toString());
                mComplaint.setViolator(mViolatorEdt.getText().toString());
                mComplaint.setDescription(mDescriptionEdt.getText().toString());
                mComplaint.setFile(mFile);

                mComplaintController.sendComplaint(mComplaint, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        if (task.isSuccessful()) {
                            mProgressBar.setVisibility(View.GONE);
                            startActivity(new Intent(ComplaintActivity.this, ResultActivity.class));
                        } else {
                            mProgressBar.setVisibility(View.GONE);
                            Utils.showMessage(ComplaintActivity.this, task.getException().getMessage());
                            task.getException().printStackTrace();
                        }
                    }
                });
            }
        });

        // Esconde fileNameContainer caso clicado.
        mFileNameContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFileNameContainer.setVisibility(View.GONE);
            }
        });

        /* Mapa desativado por enquanto.
        mMapBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(
                        new Intent(ComplaintActivity.this, MapsActivity.class), MAP_RESULT
                );
            }
        });*/
    }

    private void init() {
        mLocationEdt = (EditText) findViewById(R.id.locationEdt);
        mDateEdt = (EditText) findViewById(R.id.dateEdt);
        mTimeEdt = (EditText) findViewById(R.id.timeEdt);
        mViolatorEdt = (EditText) findViewById(R.id.violatorEdt);
        mDescriptionEdt = (EditText) findViewById(R.id.descriptionEdt);
        mSendBtn = (RelativeLayout) findViewById(R.id.sendBt);
        mAddFileBt = (RelativeLayout) findViewById(R.id.addFileBt);
        mFileNameContainer = (LinearLayout) findViewById(R.id.fileNameContainer);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        //mMapBt = (ImageView) findViewById(R.id.mapBt);
        mFileNameTv = (TextView) findViewById(R.id.fileNameTv);

        mComplaintController = ComplaintController.getInstance(this);
    }

    /**
     * Abre activity para busca de arquivos pelo usuario.
     */
    private void pickFileFromSdCard() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, DATA_RESULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case DATA_RESULT:
                if (resultCode == RESULT_OK) {
                    Uri selectedData = data.getData();

                    mFile = new File(this, selectedData);
                    mFileNameTv.setText(mFile.getFileName());

                    mFileNameContainer.setVisibility(View.VISIBLE);
                }
                break;

            case MAP_RESULT:
                if (resultCode == RESULT_OK) {
                    mLatLng = new LatLng(
                            data.getExtras().getDouble(Constants.KEYS.LATITUDE),
                            data.getExtras().getDouble(Constants.KEYS.LONGITUDE)
                    );
                    mLocationEdt.setText(mLatLng.latitude + ", " + mLatLng.longitude);
                }
                break;
        }
    }
}
