package com.agaigets.easyemail.decorator;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.agaigets.easyemail.R;

public class MyRecyclerViewDecorator extends RecyclerView.ItemDecoration {

    private static final String TAG = "MyRecyclerViewDecorator";
    private static int divider=12;
    private Paint paint;

    public MyRecyclerViewDecorator() {
        this.paint = new Paint();
        paint.setColor(Color.LTGRAY);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int left = view.getLeft();
            int top = view.getBottom();
            int right = view.getRight();
            int bottom = top + divider;
            c.drawRect(left,top,right,bottom,paint);
        }

    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0, 0, 0, divider);
    }

}
