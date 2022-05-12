package com.example.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.LineBackgroundSpan;

import androidx.annotation.NonNull;

public class DotsSpan implements LineBackgroundSpan {

    private final float radius;
    private final int color;
    private final float position;
    private final int size;

    public DotsSpan(float radius, int color, float position, int size) {
        this.radius = radius;
        this.color = color;
        this.position = position;
        this.size = size;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);
        }
        canvas.drawCircle((left + right)*position / (size+1), bottom + radius, radius, paint);
        paint.setColor(oldColor);
    }
}
