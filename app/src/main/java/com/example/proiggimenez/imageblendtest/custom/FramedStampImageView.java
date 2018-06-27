package com.example.proiggimenez.imageblendtest.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.proiggimenez.imageblendtest.R;

public class FramedStampImageView extends View {

    private static final double PORTRAIT_PERCENT_LEFT_OFFSET = 0.19;
    private static final double PORTRAIT_PERCENT_BOTTOM_OFFSET = 0.18;
    private static final double PORTRAIT_PERCENT_RIGHT_OFFSET = 0.2;

    private static final double LANDSCAPE_PERCENT_LEFT_OFFSET = 0.19;
    private static final double LANDSCAPE_PERCENT_BOTTOM_OFFSET = 0.18;
    private static final double LANDSCAPE_PERCENT_RIGHT_OFFSET = 0.09;

    private static final double PORTRAIT_ASPECT_RATIO = 0.7030625832223702;
    private static final double PORTRAIT_FRAME_ASPECT_RATIO = 0.8298507462686567;
    private static final double LANDSCAPE_ASPECT_RATIO = 1.423180592991914;
    private static final double LANDSCAPE_FRAME_ASPECT_RATIO = 1.205035971223022;


    private int finalHeight;
    private int finalWidth;

    private int innerImageWidth;
    private int innerImageHeight;

    private boolean portrait;
    private int leftOffset;
    private int topOffset;
    private int rightOffset;
    private int bottomOffset;

    public FramedStampImageView(Context context) {
        super(context);
        Log.i("IMAGE_BLEND","FramedStampImageView(Context context)");
    }

    public FramedStampImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i("IMAGE_BLEND","FramedStampImageView(Context context, @Nullable AttributeSet attrs)");
        setPortraitAttribute(attrs);
    }

    public FramedStampImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i("IMAGE_BLEND","FramedStampImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr)");
        setPortraitAttribute(attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        finalWidth = getMeasuredWidth();

        if(portrait) {
            finalHeight = Double.valueOf(finalWidth/PORTRAIT_FRAME_ASPECT_RATIO).intValue();
            Log.i("IMAGE_BLEND","onDraw() - Height: " + finalHeight + " Width: " + finalWidth);
            leftOffset = Double.valueOf(finalWidth* PORTRAIT_PERCENT_LEFT_OFFSET).intValue();
            rightOffset = Double.valueOf(finalWidth* PORTRAIT_PERCENT_RIGHT_OFFSET).intValue();
            innerImageWidth = finalWidth-leftOffset-rightOffset;
            innerImageHeight = Double.valueOf(innerImageWidth/PORTRAIT_ASPECT_RATIO).intValue();
            bottomOffset = Double.valueOf(finalHeight* PORTRAIT_PERCENT_BOTTOM_OFFSET).intValue();
            topOffset = finalHeight - innerImageHeight - bottomOffset;

            Log.i("IMAGE_BLEND","onDraw() - leftOffset: " + leftOffset + " rightOffset: " + rightOffset + "topOffset" + topOffset + "bottomOffset" + bottomOffset);

            Drawable frameDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_stamp_placeholder_portrait);
            frameDrawable.setBounds(0, 0, finalWidth, finalHeight);
            frameDrawable.draw(canvas);

            Drawable imageDrawable = getResources().getDrawable(R.drawable.test_image_portrait);
            imageDrawable.setBounds(leftOffset, topOffset, finalWidth-rightOffset, finalHeight-bottomOffset);
            imageDrawable.draw(canvas);

        } else {
            finalHeight = Double.valueOf(finalWidth/LANDSCAPE_FRAME_ASPECT_RATIO).intValue();
            Log.i("IMAGE_BLEND","onDraw() - Height: " + finalHeight + " Width: " + finalWidth);
            leftOffset = Double.valueOf(finalWidth * LANDSCAPE_PERCENT_LEFT_OFFSET).intValue();
            rightOffset = Double.valueOf(finalWidth * LANDSCAPE_PERCENT_RIGHT_OFFSET).intValue();
            innerImageWidth = finalWidth-leftOffset-rightOffset;
            innerImageHeight = Double.valueOf(innerImageWidth/LANDSCAPE_ASPECT_RATIO).intValue();
            bottomOffset = Double.valueOf(finalHeight* LANDSCAPE_PERCENT_BOTTOM_OFFSET).intValue();
            topOffset = finalHeight - innerImageHeight - bottomOffset;

            Log.i("IMAGE_BLEND","onDraw() - leftOffset: " + leftOffset + " rightOffset: " + rightOffset + "topOffset" + topOffset + "bottomOffset" + bottomOffset);

            Drawable frameDrawable = ContextCompat.getDrawable(getContext(), R.drawable.stamp_placeholder_landscape);
            frameDrawable.setBounds(0, 0, finalWidth, finalHeight);
            frameDrawable.draw(canvas);

            Drawable imageDrawable = getResources().getDrawable(R.drawable.image_test_landscape);
            imageDrawable.setBounds(leftOffset, topOffset, finalWidth-rightOffset, finalHeight-bottomOffset);
            imageDrawable.draw(canvas);
        }

    }


    public void setPortraitAttribute(AttributeSet attrs) {
        if(attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.FramedStampImageView,
                    0, 0);

            try {
                portrait= a.getBoolean(R.styleable.FramedStampImageView_is_portrait, true);
                Log.i("IMAGE_BLEND","FramedStampImageView - isPortrait: "+portrait);
            } finally {
                a.recycle();
            }
        } else {
            //TODO investigate when ot if this can happen
            throw new RuntimeException("AttributeSet was null");
        }
    }
}
