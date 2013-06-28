package dev.seme.sortingstraws;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import dev.seme.sortexamples.R;

public class SortListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bubbleBtn = (Button)findViewById(R.id.bubbleBtn);
        bubbleBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent sortIntent = new Intent(SortListActivity.this, SortActivity.class);
                sortIntent.putExtra("sort", getString(R.string.bubble_sort));

                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, view.getWidth()/2, view.getHeight(),0,0);
                startActivity(sortIntent, options.toBundle());
            }
        });

        Button insertionBtn = (Button)findViewById(R.id.insertionBtn);
        insertionBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent sortIntent = new Intent(SortListActivity.this, SortActivity.class);
                sortIntent.putExtra("sort", getString(R.string.insertion_sort));

                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, view.getWidth()/2, view.getHeight(),0,0);
                startActivity(sortIntent, options.toBundle());
            }
        });

        Button shellBtn = (Button)findViewById(R.id.shellBtn);
        shellBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent sortIntent = new Intent(SortListActivity.this, SortActivity.class);
                sortIntent.putExtra("sort", getString(R.string.insertion_sort));

                ActivityOptions options = ActivityOptions.makeScaleUpAnimation(view, view.getWidth()/2, view.getHeight(),0,0);
                startActivity(sortIntent, options.toBundle());
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sort_list, menu);
        return true;
    }

    private int getSort(dev.seme.sortingstraws.Sorts sortEnum){

        if(sortEnum == Sorts.BUBBLE){
            return  0;
        }else if(sortEnum == Sorts.INSERTION){
            return 1;
        }


        return -1;
    }
}
