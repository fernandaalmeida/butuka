package org.butuka.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import org.butuka.R;
import org.butuka.constant.Constants;
import org.butuka.model.File;

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
    //private ImageView mMapBt;
    private RelativeLayout mAddFileBt;
    private RelativeLayout mSendBtn;
    private LinearLayout mFileNameContainer;
    private TextView mFileNameTv;

    private Uri mUri;

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

                Intent intent = new Intent(ComplaintActivity.this, ResultActivity.class);

                Bundle extras = new Bundle();
                extras.putString(Constants.KEYS.LOCATION_KEY, mLocationEdt.getText().toString());
                extras.putString(Constants.KEYS.DATE_KEY, mDateEdt.getText().toString());
                extras.putString(Constants.KEYS.TIME_KEY, mTimeEdt.getText().toString());
                extras.putString(Constants.KEYS.VIOLATOR_KEY, mViolatorEdt.getText().toString());
                extras.putString(Constants.KEYS.DESCRIPTION_KEY, mDescriptionEdt.getText().toString());
                if (mUri != null) {
                    extras.putString("uri", String.valueOf(mUri));
                } else {
                    extras.putString("uri", "null");
                }
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

        // Esconde fileNameContainer caso clicado.
        mFileNameContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFileNameContainer.setVisibility(View.GONE);
                mUri = null;
            }
        });

        /*mMapBt.setOnClickListener(new View.OnClickListener() {
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
        //mMapBt = (ImageView) findViewById(R.id.mapBt);
        mFileNameTv = (TextView) findViewById(R.id.fileNameTv);

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
                    mUri = data.getData();

                    mFileNameTv.setText(new File(mUri).getFileName(this));

                    mFileNameContainer.setVisibility(View.VISIBLE);
                }
                break;

            case MAP_RESULT:
                if (resultCode == RESULT_OK) {
                    LatLng mLatLng = new LatLng(
                            data.getExtras().getDouble(Constants.KEYS.LATITUDE),
                            data.getExtras().getDouble(Constants.KEYS.LONGITUDE)
                    );
                    mLocationEdt.setText(mLatLng.latitude + ", " + mLatLng.longitude);
                }
                break;
        }
    }
}
