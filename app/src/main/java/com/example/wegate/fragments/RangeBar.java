package com.example.wegate.fragments;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wegate.R;

public class RangeBar extends FrameLayout {
    int minVal = 0;
    int maxVal = 100;
    Context context;
    ImageView leftThumb;
    ImageView rightThumb;
    View view;

    int leftThumbPos = 0;
    int rightThumbPos = 100;


    public RangeBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.view = inflate(getContext(), R.layout.range_seekbar, null);
        addView(this.view);
    }

    public RangeBar(Context context) {
        super(context);
        this.context = context;
        this.view = inflate(getContext(), R.layout.range_seekbar, null);
        addView(this.view);
    }

    public void create(TextView view) {
        leftThumb = (ImageView) findViewById(R.id.left_thumb);
        rightThumb = (ImageView) findViewById(R.id.right_thumb);
        view.setText(String.valueOf(minVal)+" - "+String.valueOf(maxVal));
        final View leftBar = findViewById(R.id.left_bar);
        final View rightBar = findViewById(R.id.right_bar);
        final View middleBar = findViewById(R.id.middle_bar);
        final LinearLayout.LayoutParams leftBarLayoutParams = (LinearLayout.LayoutParams) leftBar.getLayoutParams();
        final LinearLayout.LayoutParams rightBarLayoutParams = (LinearLayout.LayoutParams) rightBar.getLayoutParams();
        final LinearLayout.LayoutParams middleBarLayoutParams = (LinearLayout.LayoutParams) middleBar.getLayoutParams();
        final LinearLayout llRangeSeekbar = (LinearLayout) findViewById(R.id.ll_range_seekbar);

        ((TextView) findViewById(R.id.tv_range_max)).setText(maxVal + "");
        ((TextView) findViewById(R.id.tv_range_min)).setText(minVal + "");
        leftThumbPos = Integer.parseInt(((TextView) findViewById(R.id.tv_range_min)).getText() + "");
        rightThumbPos = Integer.parseInt(((TextView) findViewById(R.id.tv_range_max)).getText() + "");


        leftThumb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int diff = maxVal - minVal;
                if (diff < 0) {
                    diff = 100;
                    minVal = 0;
                    maxVal = 100;
                }
                float width = llRangeSeekbar.getWidth();
                float gap = leftThumb.getWidth();

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    leftThumb.bringToFront();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    float temp1 = leftBarLayoutParams.weight;
                    float temp2 = middleBarLayoutParams.weight;
                    leftBarLayoutParams.weight += event.getX() / width;
                    middleBarLayoutParams.weight = 1 - (leftBarLayoutParams.weight + rightBarLayoutParams.weight);

                    int tempMaxVal = Integer.parseInt(((TextView) findViewById(R.id.tv_range_max)).getText() + "");
                    int tempMinVal = (int) (diff * leftBarLayoutParams.weight + minVal);
                    if (tempMinVal > tempMaxVal) {
                        tempMinVal = tempMaxVal;
                    }
                    if (tempMinVal < minVal) {
                        tempMinVal = minVal;
                    }
                    ((TextView) findViewById(R.id.tv_range_min)).setText(tempMinVal + "");

                    if (middleBarLayoutParams.weight > gap / width && leftBarLayoutParams.weight >= 0) {
                        leftBar.setLayoutParams(leftBarLayoutParams);
                        middleBar.setLayoutParams(middleBarLayoutParams);
                    } else {
                        if (leftBarLayoutParams.weight < 0) {
                            leftBarLayoutParams.weight = 0;
                            middleBarLayoutParams.weight = 1 - (rightBarLayoutParams.weight + leftBarLayoutParams.weight);
                        } else {
                            middleBarLayoutParams.weight = gap / width + (tempMaxVal - tempMinVal) / (1.0f * diff);
                            leftBarLayoutParams.weight = 1 - (middleBarLayoutParams.weight + rightBarLayoutParams.weight);
                        }
                        leftBar.setLayoutParams(leftBarLayoutParams);
                        middleBar.setLayoutParams(middleBarLayoutParams);
                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    leftThumbPos = Integer.parseInt(((TextView) findViewById(R.id.tv_range_min)).getText() + "");
                    view.setText(String.valueOf(leftThumbPos)+" - "+String.valueOf(rightThumbPos));
                    return true;
                } else {
                    return false;
                }
            }
        });

        rightThumb.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int diff = maxVal - minVal;
                if (diff < 0) {
                    diff = 100;
                    minVal = 0;
                    maxVal = 100;
                }
                float width = llRangeSeekbar.getWidth();
                float gap = leftThumb.getWidth();

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    rightThumb.bringToFront();
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    float temp1 = middleBarLayoutParams.weight;
                    float temp2 = rightBarLayoutParams.weight;

                    rightBarLayoutParams.weight -= (event.getX() / width);
                    middleBarLayoutParams.weight = 1 - (rightBarLayoutParams.weight + leftBarLayoutParams.weight);

                    int tempMinVal = Integer.parseInt(((TextView) findViewById(R.id.tv_range_min)).getText() + "");
                    int tempMaxVal = (int) (diff * (1 - rightBarLayoutParams.weight) + minVal);
                    if (tempMaxVal < tempMinVal) {
                        tempMaxVal = tempMinVal;
                    }
                    if (tempMaxVal > maxVal) {
                        tempMaxVal = maxVal;
                    }
                    ((TextView) findViewById(R.id.tv_range_max)).setText(tempMaxVal + "");

                    if (middleBarLayoutParams.weight > gap / width && rightBarLayoutParams.weight >= 0) {
                        rightBar.setLayoutParams(rightBarLayoutParams);
                        middleBar.setLayoutParams(middleBarLayoutParams);
                    } else {
                        if (rightBarLayoutParams.weight < 0) {
                            rightBarLayoutParams.weight = 0;
                            middleBarLayoutParams.weight = 1 - (rightBarLayoutParams.weight + leftBarLayoutParams.weight);
                        } else {
                            middleBarLayoutParams.weight = gap / width + (tempMaxVal - tempMinVal) / (1.0f * diff);
                            rightBarLayoutParams.weight = 1 - (leftBarLayoutParams.weight + middleBarLayoutParams.weight);
                        }
                        rightBar.setLayoutParams(rightBarLayoutParams);
                        middleBar.setLayoutParams(middleBarLayoutParams);

                    }
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    rightThumbPos = Integer.parseInt(((TextView) findViewById(R.id.tv_range_max)).getText() + "");
                    view.setText(String.valueOf(leftThumbPos)+" - "+String.valueOf(rightThumbPos));
                    return true;
                } else {
                    return false;
                }
            }
        });
    }


    public int getMinVal() {
        return minVal;
    }

    public void setMinVal(int minVal) {
        this.minVal = minVal;
    }

    public int getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(int maxVal) {
        this.maxVal = maxVal;
    }


    public int getLeftThumbPos() {
        return leftThumbPos;
    }

    public void setLeftThumbPos(int leftThumbPos) {
        this.leftThumbPos = leftThumbPos;
    }

    public int getRightThumbPos() {
        return rightThumbPos;
    }

    public void setRightThumbPos(int rightThumbPos) {
        this.rightThumbPos = rightThumbPos;
    }
}
