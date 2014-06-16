package com.oboenikui.imagetest;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        Bitmap mask1 = BitmapFactory.decodeResource(getResources(), R.drawable.mask1);
        Bitmap mask2 = BitmapFactory.decodeResource(getResources(), R.drawable.mask2);
        Bitmap maskedIcon1 = maskImage(icon, mask1);
        Bitmap maskedIcon2 = maskImage(icon, mask2);
        ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView1.setImageBitmap(maskedIcon1);
        ImageView imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView2.setImageBitmap(maskedIcon2);
        icon.recycle();
        mask1.recycle();
        mask2.recycle();
    }
    
    private Bitmap maskImage(Bitmap src, Bitmap mask){
        if(src==null||mask==null){
            return null;
        }
        int w = src.getWidth();
        int h = src.getHeight();
        int length = w*h;
        int[] srcPixels = new int[length];
        int[] maskPixels = new int[length];
        int[] resultPixels = new int[length];
        src.getPixels(srcPixels, 0, w, 0, 0, w, h);
        mask.getPixels(maskPixels, 0, w, 0, 0, w, h);
        for(int i=0;i<length;i++){
            resultPixels[i] = (maskPixels[i]&0xff000000)|(srcPixels[i]&0xffffff);
        }
        return Bitmap.createBitmap(resultPixels, w, h, Bitmap.Config.ARGB_8888);
    }
}
