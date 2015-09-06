package com.example.satyendra.mapdemoapp.Overlay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by satyendra on 05/08/15.
 */
public class ViewDraw extends View  {

    private Paint mPaint;
    public Path path;
    private ArrayList<Path> _graphics = new ArrayList<Path>();

    public ViewDraw(Context context) {
        super(context);

        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
    }

    public ViewDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
    }



    public ViewDraw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
    }

    public ViewDraw(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(0xFFFFFF00);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Path path = new Path();
        path.moveTo(1, 1);
        path.lineTo(200, 200);
        if(!_graphics.contains(path))
        {
            _graphics.add(path);
        }
        for (Path path1 : _graphics) {
//            canvas.drawPoint(20, 20, mPaint);
            canvas.drawPath(path1, mPaint);
        }


//        for (int i = 0; i < selected.size() ; i++) {
//
//            Point p = (Point)selected.get(i) ;
//            Point p2 = (Point)selected.get(i) ;
//
//            canvas.drawLine(p.x, p.y, p2.x, p2.y, );
//        }


    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        boolean result =  super.onTouchEvent(event);
//        synchronized (_thread.getSurfaceHolder()) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            path = new Path();
            path.moveTo(event.getX(), event.getY());
            path.lineTo(event.getX(), event.getY());
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            path.lineTo(event.getX(), event.getY());
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            path.lineTo(event.getX(), event.getY());
//        }
            if(!_graphics.contains(path))
            {
                _graphics.add(path);
            }
            this.invalidate();
            return true;

        }

        return result;
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        return super.onKeyDown(keyCode, event);
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        return super.onKeyUp(keyCode, event);
//    }
//
//
//    @Override
//    public void setOnTouchListener(OnTouchListener l) {
//        super.setOnTouchListener(l);
//    }
//
//
//    @Override
//    public boolean onTouch(View v, MotionEvent event) {
//        return false;
//    }
}
