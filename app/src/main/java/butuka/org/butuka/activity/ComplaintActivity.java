package butuka.org.butuka.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.io.IOException;

import butuka.org.butuka.R;
import butuka.org.butuka.callback.IResult;
import butuka.org.butuka.controller.ComplaintController;
import butuka.org.butuka.model.Complaint;
import butuka.org.butuka.model.Image;
import butuka.org.butuka.util.Utils;

public class ComplaintActivity extends AppCompatActivity {
    private static final int INTERNAL_IMAGE = 10;
    private static final String TAG = "ComplaintActivityLog";

    // Views
    private EditText mLocationEdt,
            mDateEdt,
            mTimeEdt,
            mViolatorEdt,
            mDescriptionEdt;
    private RelativeLayout mAddPhotoBt;
    private RelativeLayout mSendBtn;
    private ImageView mSelectedIV;
    private ProgressBar mProgressBar;

    private ComplaintController mComplaintController;
    private Image mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        // Instancia classes e etc.
        init();

        mAddPhotoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromSDCard();
            }
        });

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);

                final Complaint complaint = Complaint.getInstance();
                complaint.setLocation(mLocationEdt.getText().toString());
                complaint.setDate(mDateEdt.getText().toString());
                complaint.setTime(mTimeEdt.getText().toString());
                complaint.setViolator(mViolatorEdt.getText().toString());
                complaint.setDescription(mDescriptionEdt.getText().toString());
                complaint.setImage(mImage);

                mComplaintController.sendComplaint(complaint, new IResult() {
                    @Override
                    public void result(String s) {
                        mProgressBar.setVisibility(View.GONE);
                        complaint.destroy();
                        startActivity(new Intent(ComplaintActivity.this, ResultActivity.class));
                    }

                    @Override
                    public void onFailed(Exception e) {
                        Utils.showMessage(ComplaintActivity.this, e.getMessage());
                        complaint.destroy();
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
            }
        });
    }

    private void init() {
        mLocationEdt = (EditText) findViewById(R.id.locationEdt);
        mDateEdt = (EditText) findViewById(R.id.dateEdt);
        mTimeEdt = (EditText) findViewById(R.id.timeEdt);
        mViolatorEdt = (EditText) findViewById(R.id.violatorEdt);
        mDescriptionEdt = (EditText) findViewById(R.id.descriptionEdt);
        mSendBtn = (RelativeLayout) findViewById(R.id.sendBt);
        mAddPhotoBt = (RelativeLayout) findViewById(R.id.addPhotoBt);
        mSelectedIV = (ImageView) findViewById(R.id.selectedIv);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mComplaintController = new ComplaintController(this);
        mImage = new Image();
    }

    public void pickImageFromSDCard() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, INTERNAL_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTERNAL_IMAGE) {
            if (resultCode == RESULT_OK) {

                Uri selectedImage = data.getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    mImage.setBitmap(bitmap);

                    // Pega a ext/ da imagem.
                    ContentResolver cR = getContentResolver();
                    MimeTypeMap mime = MimeTypeMap.getSingleton();
                    String type = mime.getExtensionFromMimeType(cR.getType(selectedImage));
                    mImage.setMime(type);
                    Log.i(TAG, "Mime: " + type);

                    // Infla o imageView com a imagem selecionada na galeria.
                    mSelectedIV.setImageBitmap(bitmap);
                    mSelectedIV.setVisibility(View.VISIBLE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
