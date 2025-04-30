package c.jasonli6395.napatgame.GameObjects.UserObjects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

import c.jasonli6395.napatgame.Constants;
import c.jasonli6395.napatgame.GameObjects.UserObjects.UserObjects;
import c.jasonli6395.napatgame.HelperLibrary;

public class Square implements UserObjects {

    private Paint paint;
    private boolean placed;
    private Rect rectangle;
    private int x, y;
    private int xVel = 10, yVel = 5;
    private Point topLeft;
    private Point topRight;
    private Point bottomLeft;
    private Point bottomRight;

    public Square() {
        rectangle = new Rect();
        placed = false;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        x = Constants.SCREEN_WIDRTH / 2 - Constants.USEROBJECT_WIDTH / 16;
        y = Constants.SCREEN_HEIGHT - 100;
        topLeft = new Point();
        topRight = new Point();
        bottomLeft = new Point();
        bottomRight = new Point();
        update();
    }

    @Override
    public void place() {
        placed = true;
    }

    @Override
    public boolean CheckCollision(int x, int y, int radius) {
        //LEFT EDGE
        Log.d("RADIUS", Integer.toString(radius));
        Log.d("LEFT", Integer.toString(HelperLibrary.ShortestDistance(x, y, topLeft.x, topLeft.y, bottomLeft.x, bottomLeft.y)));
        Log.d("RIGHT", Integer.toString(HelperLibrary.ShortestDistance(x, y, topRight.x, topRight.y, bottomRight.x,bottomRight.y)));
        Log.d("TOP", Integer.toString(HelperLibrary.ShortestDistance(x, y, topLeft.x, topLeft.y, topRight.x,topRight.y)));
        Log.d("BOTTOM", Integer.toString(HelperLibrary.ShortestDistance(x, y, bottomLeft.x, bottomLeft.y, bottomRight.x,bottomRight.y)));
        Log.d("*******","***************************************************************************************************");
        if (HelperLibrary.ShortestDistance(x, y, topLeft.x, topLeft.y, bottomLeft.x, bottomLeft.y) <= radius){
            return true;
        }
        //RIGHT EDGE
        else if(HelperLibrary.ShortestDistance(x, y, topRight.x, topRight.y, bottomRight.x,bottomRight.y) <= radius){
            return  true;
        }
        //TOP EDGE
        else if(HelperLibrary.ShortestDistance(x, y, topLeft.x, topLeft.y, topRight.x,topRight.y) <= radius){
            return  true;
        }
        //BOTTOM EDGE
        else if(HelperLibrary.ShortestDistance(x, y, bottomLeft.x, bottomLeft.y, bottomRight.x,bottomRight.y) <= radius){
            return  true;
        }
        return false;

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {
        int left = x - Constants.USEROBJECT_WIDTH / 2;
        int top = y - Constants.USEROBJECT_WIDTH / 2;
        int right = x + Constants.USEROBJECT_WIDTH / 2;
        int bottom = y + Constants.USEROBJECT_WIDTH / 2;
        rectangle.set(left, top, right, bottom);
        topLeft.x = left;
        topLeft.y = top;
        bottomLeft.x = left;
        bottomLeft.y = bottom;
        bottomRight.x = right;
        bottomRight.y = bottom;
        topRight.x = right;
        topRight.y = top;
    }

    @Override
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
