package dev.seme.sorts;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.Log;

/**
 * Created by wsloth514 on 6/7/13.
 */
public class Straw extends ShapeDrawable implements Animatable {

    private int length = 0;
    private Paint paint = new Paint();

    private static final int X_SIZE = 60;
    private int Y_SIZE;
    /**
     * Coordinates on which the shape should be drawn.
     */
    public Coordinates coordinates;

    public Straw(int length,int x,int y){
        if(length> 0){
            this.length = length;
        }else{
            this.length = 1;
            Log.e("STRAW", "length may not be less than or equal to 0.");
        }

        Y_SIZE = X_SIZE + ( length * 10);

        paint.setAntiAlias(true);
//        paint.setLinearText(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize((float)30);
//        paint.setColor(Color.BLACK);

        setShape(new RectShape());
        setColorFilter(Color.GREEN, PorterDuff.Mode.SRC);

        coordinates = new Coordinates();


        setBounds(x, y);
    }

    public int getLength(){
        return this.length;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }


    /**
     * Contains the coordinates of the instance.
     *
     * @author Walter
     */
    public class Coordinates {
        private int x = 0;
        private int y = 0;

        private int xSize = 40;
        private int ySize = 40;
        /**
         * @return The x coordinate of the upper left corner.
         */
        public int getX() {
            return x;
        }

        /**
         * @return The x coordinate of the center.
         */
        public int getTouchedX() {
            return x + xSize;
        }

        /**
         * @param value The new x coordinate of the upper left corner.
         */
        public void setX(int value) {
            x = value;
        }

        /**
         * @param value The new x coordinate of the center.
         */
        public void setTouchedX(int value) {
            x = value - xSize;
        }

        /**
         * @return The y coordinate of the upper left corner.
         */
        public int getY() {
            return y;
        }

        /**
         * @return The y coordinate of the center.
         */
        public int getTouchedY() {
            return y + ySize;
        }

        /**
         * @param value The new y coordinate of the upper left corner.
         */
        public void setY(int value) {
            y = value;
        }

        /**
         * @param value The new y coordinate of the center.
         */
        public void setTouchedY(int value) {
            y = value - ySize;
        }

        /**
         * Helper method for debugging.
         */
        public String toString() {
            return "Coordinates: (" + x + ", " + y + ")";
        }
    }

    public void setBounds(float touchedX, float touchedY){

        Coordinates cords = this.coordinates;
        cords.setTouchedX((int) touchedX);
        cords.setTouchedY((int) touchedY);

        int left = cords.getX();
        int top = cords.getY() - Y_SIZE;
        int bottom = cords.getY();//top + Y_SIZE;
        int right = left + X_SIZE;

        setBounds(left, top, right, bottom);
    }

    public void draw(Canvas canvas){
        super.draw(canvas);
//        canvas.drawPosText(""+amountOfTokens, new float[]{getCoordinates().getTouchedX(), getCoordinates().getTouchedY()+5}, paint);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);
        if(length > 9)//if double digit move more left
            canvas.drawText(length+"", coordinates.getTouchedX() - 30, coordinates.getTouchedY(), paint);
        else
            canvas.drawText(length+"", coordinates.getTouchedX() - 19, coordinates.getTouchedY(), paint);


        setBounds(coordinates.getTouchedX(), coordinates.getTouchedY());
    }


    public void setColor(int color){
        setColorFilter(color, PorterDuff.Mode.SRC);
    }

}