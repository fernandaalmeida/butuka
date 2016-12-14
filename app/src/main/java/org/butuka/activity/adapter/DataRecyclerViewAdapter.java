package org.butuka.activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.butuka.R;
import org.butuka.model.File;

import java.util.List;

/**
 * Created by iagobelo on 24/10/2016.
 */

public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.ViewHolder> {
    private List<File> mFileList;

    // Provide a suitable constructor (depends on the kind of dataset)
    public DataRecyclerViewAdapter(List<File> list) {
        mFileList = list;
    }

    public void setData(List<File> list) {
        this.mFileList = list;
        notifyDataSetChanged();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public DataRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.data_layout, parent, false);

        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.mTextView.setText(mFileList.get(position).getMime());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFileList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mFileList.size();
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.fileNameTv);
        }
    }
}
