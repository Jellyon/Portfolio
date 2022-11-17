package com.lyon.hw14;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SimpleDrawingView extends View {
    private final int paintColor = Color.BLACK;
    private Paint drawPaint;
    private List<Point> circlePoints;
    private Path path = new Path();
    //mode: true=path false = circle
    public boolean mode=true;

    public SimpleDrawingView(Context context, AttributeSet attrs){
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setupPaint();
        circlePoints= new ArrayList<Point>();


    }

    @Override
    protected void onDraw(Canvas canvas){
        if(mode)
            canvas.drawPath(path, drawPaint);
        else {
            for(Point p : circlePoints){
                canvas.drawCircle(p.x, p.y, 10, drawPaint);
            }
        }
        //canvas.drawCircle(50,50,20,drawPaint);
        //drawPaint.setColor(Color.RED);
        //canvas.drawCircle(50,150,30, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e){
        float X = e.getX();
        float Y = e.getY();
        if (!mode)
            circlePoints.add(new Point(Math.round(X),Math.round(Y)));
        else {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(X, Y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    path.lineTo(X, Y);
                    break;
                default:
                    return false;
            }
        }
        postInvalidate();
        return true;
    }

    void setupPaint(){
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);

        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        if(mode)    drawPaint.setStyle(Paint.Style.STROKE);
        else        drawPaint.setStyle(Paint.Style.FILL);
    }


}
