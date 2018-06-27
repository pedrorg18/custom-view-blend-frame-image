package com.example.proiggimenez.imageblendtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final double PERCENT_LEFT_OFFSET = 0.2;
    private static final double PERCENT_BOTTOM_OFFSET = 0.2;
    private int finalHeight;
    private int finalWidth;

    private View stampImage;

//    private Drawable frameDrawable;
//    private Drawable imageDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stampImage = findViewById(R.id.imageViewStamp);


//        frameDrawable = getResources().getDrawable(R.drawable.ic_stamp_placeholder_portrait);
//        imageDrawable = getResources().getDrawable(R.drawable.test_image);

        ViewTreeObserver vto = stampImage.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            public boolean onPreDraw() {
                // Remove after the first run so it doesn't fire forever
                stampImage.getViewTreeObserver().removeOnPreDrawListener(this);
                finalHeight = stampImage.getMeasuredHeight();
                finalWidth = stampImage.getMeasuredWidth();
//                Log.i("IMAGE_BLEND","Height: " + finalHeight + " Width: " + finalWidth);
                blendImageAndFrame();
                return true;
            }

        });

    }

    private void blendImageAndFrame() {

//        int leftOffset = Double.valueOf(finalWidth*PERCENT_LEFT_OFFSET).intValue();
//        int topOffset = Double.valueOf(finalHeight*PERCENT_BOTTOM_OFFSET).intValue();
//
//        Bitmap frameBitmap = getBitmapFromVectorDrawable(this, R.drawable.ic_stamp_placeholder_portrait);
//        Bitmap imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_image);

//        Drawable[] layers = {frameDrawable, imageDrawable};
//        LayerDrawable layerDrawable = new LayerDrawable(layers);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            layerDrawable.setLayerInsetLeft(1, frameDrawable);
//            layerDrawable.setLayerInsetBottom(1, );
//        }

//        Canvas canvas = new Canvas(frameBitmap);
//        canvas.drawBitmap(imageBitmap, leftOffset, finalHeight, null);
//        Log.i("IMAGE_BLEND","leftOffset: " + leftOffset + " top: " + finalHeight);
//        stampImage.setImageBitmap(imageBitmap);

//        Bitmap bmOverlay = Bitmap.createBitmap(finalWidth, finalHeight, frameBitmap.getConfig());
//        Canvas canvas = new Canvas(bmOverlay);
//        Paint paintFrame = new Paint();
//        canvas.drawBitmap(frameBitmap, new Matrix(), paintFrame);
//        canvas.drawBitmap(imageBitmap, 150, 150, null);
//
//        stampImage.setImageBitmap(bmOverlay);



//        ShapeDrawable border = new ShapeDrawable();
//        border.getPaint().setColor(Color.WHITE);
//
//        ShapeDrawable background = new ShapeDrawable();
//        background.getPaint().setColor(Color.BLACK);
//
//
//        ShapeDrawable clip = new ShapeDrawable();
//        clip.getPaint().setColor(Color.WHITE);












//        Log.i("IMAGE_BLEND", " " + stampImage.getMeasuredWidth() + " " +stampImage.getWidth() + " | " + stampImage.getMeasuredHeight() + " " + stampImage.getHeight());
//        Bitmap bmOverlay = Bitmap.createBitmap(drawable.getWidth(), bmp1.getHeight(), bmp1.getConfig());
//        Canvas canvas = new Canvas(bmOverlay);
//        canvas.drawBitmap(bmp1, new Matrix(), null);
//        canvas.drawBitmap(bmp2, 0, 0, null);
//        return bmOverlay;


//        Bitmap bmOverlay = Bitmap.createBitmap(bmp1.getWidth(), bmp1.getHeight(), bmp1.getConfig());
//        Canvas canvas = new Canvas(bmOverlay);
//        canvas.drawBitmap(bmp1, new Matrix(), null);
//        canvas.drawBitmap(bmp2, 0, 0, null);
//        return bmOverlay;
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
