package butuka.org.butuka.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butuka.org.butuka.R;
import butuka.org.butuka.activity.adapter.DataRecyclerViewAdapter;
import butuka.org.butuka.callback.OnCompleteListener;
import butuka.org.butuka.controller.ComplaintController;
import butuka.org.butuka.model.Complaint;
import butuka.org.butuka.model.Data;
import butuka.org.butuka.model.Task;
import butuka.org.butuka.util.Utils;

public class ComplaintActivity extends AppCompatActivity {
    // Constants da classe
    private static final int INTERNAL_DATA = 10;
    private static final String TAG = "ComplaintActivityLog";

    // Views
    private EditText mLocationEdt,
            mDateEdt,
            mTimeEdt,
            mViolatorEdt,
            mDescriptionEdt;
    private RelativeLayout mAddPhotoBt;
    private RelativeLayout mSendBtn;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    private ComplaintController mComplaintController;
    private Complaint mComplaint;
    private List<Data> mDataList;
    private RecyclerView.LayoutManager mLayoutManager;
    private DataRecyclerViewAdapter mDataRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        init();

        mAddPhotoBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromSDCard();
            }
        });

        mDataList = new ArrayList<>();

        mDataRecyclerViewAdapter = new DataRecyclerViewAdapter(mDataList);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mDataRecyclerViewAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

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
                //mComplaint.setData(mData);

                mComplaintController.sendComplaint(mComplaint, new OnCompleteListener() {
                    @Override
                    public void onComplete(Task task) {
                        if (task.isSuccessful()) {
                            mProgressBar.setVisibility(View.GONE);
                            startActivity(new Intent(ComplaintActivity.this, ResultActivity.class));
                        } else {
                            mProgressBar.setVisibility(View.GONE);
                            Utils.showMessage(ComplaintActivity.this, task.getMessage());
                        }
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
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mComplaintController = ComplaintController.getInstance(this);
    }

    private void pickImageFromSDCard() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, INTERNAL_DATA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTERNAL_DATA) {
            if (resultCode == RESULT_OK) {
                Data file = new Data();

                Uri selectedImage = data.getData();
                //InputStream inputStream = getContentResolver().openInputStream(selectedImage);
                //LightEncode lightEncode = new LightEncode(inputStream);
                file.setUri(selectedImage);

                // Pega a extensao da imagem.
                ContentResolver cR = getContentResolver();
                MimeTypeMap mime = MimeTypeMap.getSingleton();
                String type = mime.getExtensionFromMimeType(cR.getType(selectedImage));
                file.setMime(type);

                // Adiciona o arquivo selecionado na lista.
                mDataList.add(file);
                mDataRecyclerViewAdapter.setData(mDataList);

                Log.i(TAG, "Uri: " + selectedImage);
            }
        }
    }
}
