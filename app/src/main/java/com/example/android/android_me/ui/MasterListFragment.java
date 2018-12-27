package com.example.android.android_me.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MasterListFragment extends Fragment {

    public MasterListFragment() {

    }

    public OnImageClickListener mCallback;

    public interface OnImageClickListener {
        void onImageSelected(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        GridView gridView = rootView.findViewById(R.id.images_grid_view);
        MasterListAdapter masterListAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(masterListAdapter);

        // Set a click listener on the grid items and use the interface to call the onImageSelected method
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mCallback.onImageSelected(i);
            }
        });

        return rootView;
    }

    // Add code to make sure the callback is implemented where the fragment is attached
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener");
        }
    }
}
