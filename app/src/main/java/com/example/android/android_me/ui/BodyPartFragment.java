package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    // Strings for storing state info for restoring
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    private final static String LOG_TAG = BodyPartFragment.class.getSimpleName();
    private List<Integer> mImageIds;
    private int mListIndex;

    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        if (savedInstanceState != null) {
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        // Get a reference to the image view for the body
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // Set the image resource to display
        if (mImageIds != null) {
            imageView.setImageResource(mImageIds.get(mListIndex));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex < mImageIds.size()-1) {
                        mListIndex++;
                    } else {
                        mListIndex = 0;
                    }
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        } else {
            Log.v(LOG_TAG, "No image IDs found for image resource");
        }

        return rootView;
    }

    public List<Integer> getImageIds() {
        return mImageIds;
    }

    public void setImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    public int getListIndex() {
        return mListIndex;
    }

    public void setListIndex(int mListIndex) {
        this.mListIndex = mListIndex;
    }

    // Handle saving instances of the fragment during rotation etc

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        outState.putInt(LIST_INDEX, mListIndex);
        super.onSaveInstanceState(outState);
    }
}
