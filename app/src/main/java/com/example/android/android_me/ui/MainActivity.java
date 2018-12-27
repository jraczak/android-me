package com.example.android.android_me.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.android_me.R;

public class MainActivity extends Activity implements MasterListFragment.OnImageClickListener {

    public int headIndex;
    public int bodyIndex;
    public int legIndex;

    public Bundle imageBundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    // Implement the callback from the interface in MasterListFragment
    public void onImageSelected(int position) {
        Toast.makeText(this, "Clicked position " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position / 12;
        System.out.println("Body part number: " + bodyPartNumber);
        int listIndex = position - 12 * bodyPartNumber;
        System.out.println("List index: " + listIndex);

        // 0, 1, 2 map to head, body, and leg image sets defined in AndroidImageAssets
        // listIndex is then dynamic based on which body part number is used, 11 indexes in each set
        switch (bodyPartNumber) {
            case 0: headIndex = listIndex;
            break;
            case 1: bodyIndex = listIndex;
            break;
            case 2: legIndex = listIndex;
            break;
            default: break;
        }

        imageBundle = new Bundle();
        imageBundle.putInt("headIndex", headIndex);
        imageBundle.putInt("bodyIndex", bodyIndex);
        imageBundle.putInt("legIndex", legIndex);
        final Intent intent = new Intent(this, AndroidMeActivity.class);
        intent.putExtras(imageBundle);

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}
