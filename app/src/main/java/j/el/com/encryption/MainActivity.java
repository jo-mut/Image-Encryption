package j.el.com.encryption;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView1;
    private ImageView mImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView1 = (ImageView) findViewById(R.id.imageIImageView);
        mImageView2 = (ImageView) findViewById(R.id.image2ImageView);

        imagePixels(getResources().getDrawable(R.drawable.image1), getResources().getDrawable(R.drawable.image2));
    }

    private void imagePixels(Drawable image1, Drawable image2) {
        Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.image1);
        Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.image2);

        int w1 = b1.getWidth();
        int h1 = b1.getHeight();
        int w2 = b2.getWidth();
        int h2 = b2.getHeight();

        int[][] pixels1 = new int[w1][h1];
        int[][] pixels2 = new int[w2][h2];

        String[][] hexValues1 = new String[w1][h1];
        String[][] hexValues2 = new String[w2][h2];

        for (int i = 0; i < w1; i++) {
            for (int j = 0; j < h1; j++) {
                pixels1[i][j] = b1.getPixel(i, j);
                String hex = Integer.toHexString(pixels1[i][j]);
                hexValues1[i][j] = hex;
            }
        }

        for (int i = 0; i < w2; i++) {
            for (int j = 0; j < h2; j++) {
                pixels2[i][j] = b2.getPixel(i, j);
                String hex = Integer.toHexString(pixels2[i][j]);
                hexValues2[i][j] = hex;
            }
        }


        if (isScattable(hexValues1, hexValues2)) {
            scatterImage(hexValues1, hexValues2);
        }


    }


    public boolean isScattable(String[][] hexValues1, String[][] hexValues2) {

        if (hexValues1 == hexValues2){
            return false;
        }

        return true;
    }

    public void scatterImage(String[][] hexValues1, String[][] hexValues2) {
        String [][] image1 = hexValues1;
        String [][] image2 = hexValues2;

        for (int i = 0; i < hexValues1.length; i++){
            for (int j = 0; j < hexValues1[i].length; j++) {
                int i1 = (int)Math.random() * hexValues1.length;
                int j1 = (int)Math.random() * hexValues1.length;

                image2[i][j] = hexValues1[i1][j1];

            }
        }

        isImageRandomlyScattered(image1, image2);

    }

    public boolean isImageRandomlyScattered(String[][] hexValues1, String[][] hexValues2) {
        int hexValuesTotal1 = 0;
        int hexValuesTotal2 = 0;

        if (hexValues1 == hexValues2) {
            Toast.makeText(this, "Image 1 cannot be scattered into image 2", Toast.LENGTH_SHORT).show();
            return false;
        }


        for (int i = 0; i < hexValues1.length; i++) {
            for (int j = 0; j < hexValues2[i].length; j++) {
                hexValuesTotal1 += 1;
            }

        }

        for (int i = 0; i < hexValues1.length; i++) {
            for (int j = 0; j < hexValues2[i].length; j++) {
                hexValuesTotal2 += 2;
            }
        }

        if (hexValuesTotal1 > hexValuesTotal2) {
            return false;
        }

        Toast.makeText(this, "Image 1 can be scattered into image 2", Toast.LENGTH_SHORT).show();
        return true;
    }

}
