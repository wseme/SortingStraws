package dev.seme.sortingstraws;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dev.seme.sortexamples.R;
import dev.seme.sortingstraws.views.SortView;

/**
 * Created by wsloth514 on 6/7/13.
 */
public class SortActivity extends Activity {

    private String sortType;
    private SortView sortView;

    private Button playBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sort_layout);

//        initViews();

        sortView = (SortView) findViewById(R.id.SortView);

        //get sort type
        Bundle extra = getIntent().getExtras();
        sortType = extra.getString("sort");

        initBtn();

    }

    private void initBtn() {
        playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (sortType.equals(getString(R.string.bubble_sort))) {

                    sortView.bubbleSort();
                }else if(sortType.equals(getString(R.string.insertion_sort))){
                    sortView.insertionSort();
                }

            }
        });
//
//        Button backBtn = (Button) findViewById(R.id.backBtn);
//        backBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        Button forwardBtn = (Button) findViewById(R.id.forwardBtn);
//        forwardBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        Button pauseBtn = (Button) findViewById(R.id.pauseBtn);
//        pauseBtn.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }


}
