package dev.seme.sortingstraws.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import dev.seme.sortexamples.R;
import dev.seme.sortingstraws.Sorts;
import dev.seme.sorts.Straw;

/**
 * Created by wsloth514 on 6/14/13.
 */
public class SortView extends SurfaceView implements SurfaceHolder.Callback{

    private static final int STRAW_AMOUNT = 10;
    private static final int MAX_STRAW_LENGTH = 20;

    private SortThread thread;

    private Straw[] straws;
    private int backgroundColor = Color.BLACK;// use for later changes if wanted

    public SortView(Context context) {
        super(context);
        init();
    }

    public SortView(Context context, AttributeSet set) {
        super(context, set);
        init();
    }

    public SortView(Context context, AttributeSet set, int defStyle) {
        super(context, set, defStyle);
        init();
    }

    public void init() {
//        currentSort = getContext().getString(R.string.bubble_sort);
        getHolder().addCallback(this);
        thread = new SortThread(this);
        setFocusable(true);


        int Measuredwidth = 0;
        int Measuredheight = 0;
        Point size = new Point();
        WindowManager w = (WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE);;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2){
            w.getDefaultDisplay().getSize(size);

            Measuredwidth = size.x;
            Measuredheight = size.y;
        }else{
            Display d = w.getDefaultDisplay();
            Measuredwidth = d.getWidth();
            Measuredheight = d.getHeight();
        }

        Log.d("SORT", "Measured width " + Measuredwidth); //720
        Log.d("SORT", "Measured height "+ Measuredheight);//1184

        int x =  60;//GET SCREEN SIZE
        int y =  600;

        straws = new Straw[STRAW_AMOUNT];
        for(int i = 0; i < straws.length; i++){
            int length =  (int) Math.round(Math.random() * MAX_STRAW_LENGTH);

            Straw straw = new Straw(length, x, y);
            straws[i] = straw;

            x += 65;
        }

    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (!thread.isAlive()) {
            thread = new SortThread(this);
        }
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // we will try it again and again...
            }
        }
        Log.i("thread", "Thread terminated...");
    }

    @Override
    public void onDraw(Canvas c){

        if(c == null)
            return;

        if(straws == null){
            return;
        }

        c.drawColor(Color.YELLOW);

        int x =  60;//GET SCREEN SIZE
        int y =  600;

        for(int i = 0; i < straws.length; i++){

            straws[i].setBounds(x, y);
            straws[i].draw(c);

            x += 65;
        }

    }

    public void bubbleSort() {

        int n = STRAW_AMOUNT;
        int i, j;
        Straw t;

        for(i = 0; i < n; i++){

            for(j = 1; j < (n-i); j++){
                straws[j - 1].setColor(Color.BLUE);
                straws[j].setColor(Color.BLUE);

                if(straws[j-1].getLength() > straws[j].getLength()){

                    straws[j - 1].setColor(Color.RED);
                    straws[j].setColor(Color.RED);

                    t = straws[j-1];
                    straws[j-1]=straws[j];
                    straws[j]=t;






                }
                this.refreshDrawableState();

                wait(1000);
                straws[j - 1].setColor(Color.GREEN);
                straws[j].setColor(Color.GREEN);


            }

        }
    }

    public void insertionSort(){

        int n = STRAW_AMOUNT;

        for (int i = 1; i < n; i++){
            int j = i;
            Straw B = straws[i];

            while ((j > 0) && (straws[j-1].getLength() > B.getLength())){

                straws[j - 1].setColor(Color.RED);
                B.setColor(Color.RED);

                straws[j] = straws[j-1];
                j--;
                wait(1000);

                this.refreshDrawableState();

                straws[j].setColor(Color.GREEN);


            }
            straws[j] = B;
            B.setColor(Color.GREEN);



        }
    }


    private static void wait (int n){
        long t0,t1;
        t0=System.currentTimeMillis();
        do{
            t1=System.currentTimeMillis();
        }
        while (t1-t0<n);
    }

}
