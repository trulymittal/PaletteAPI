package com.example.killer.paletteapi;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class ImageActivity extends AppCompatActivity {

    ImageView imageView;
    Toolbar toolbar;

    static final String IMAGE_KEY = "this is my image key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageView = findViewById(R.id.image_view);

        File file = new File(getFilesDir(), "my_image");

        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());

        if (bitmap != null) {

            imageView.setImageBitmap(bitmap);

            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(@NonNull Palette palette) {

                    Palette.Swatch swatch = palette.getVibrantSwatch();

                    if (swatch != null) {

                        int rgb = swatch.getRgb();
                        int titleTextColor = swatch.getTitleTextColor();
                        int bodyTextColor = swatch.getBodyTextColor();

                        toolbar.setBackgroundColor(rgb);
                        toolbar.setTitleTextColor(titleTextColor);

                        int darkerColor = manipulateColor(rgb, (float) 0.8);

                        getWindow().setStatusBarColor(darkerColor);



                    }


                }
            });


        }


    }

    public static int manipulateColor(int color, float factor) {
        int a = Color.alpha(color);
        int r = Math.round(Color.red(color) * factor);
        int g = Math.round(Color.green(color) * factor);
        int b = Math.round(Color.blue(color) * factor);
        return Color.argb(a,
                Math.min(r,255),
                Math.min(g,255),
                Math.min(b,255));
    }

}
