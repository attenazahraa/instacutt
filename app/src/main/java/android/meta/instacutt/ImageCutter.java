package android.meta.instacutt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ImageCutter extends AppCompatActivity {
    ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_cutter);
        img1=findViewById(R.id.imageViewImageCutter);





        File file=new File(getString(R.string.temp_images_dir),getString(R.string.temp_images));
        File Images_temp_dir=new File(this.getFilesDir(),getString(R.string.temp_images));

        Uri uri=getIntent().getData();
        File Image_temp=new File(uri.getPath());
       Bitmap bitmap=BitmapFactory.decodeFile("");
    //    img1.setImageURI(uri);
        Toast.makeText(getApplicationContext(),uri.getPath(),Toast.LENGTH_LONG).show();
        try {
            InputStream f= this.getContentResolver().openInputStream(uri);
             bitmap=BitmapFactory.decodeStream(f);
            f.close();

        } catch (FileNotFoundException e) {
              Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();

        }
        img1.setImageBitmap(bitmap);
    }

    @Override
    public void onBackPressed() {

        startActivity(new Intent(ImageCutter.this,MainActivity.class));
        super.onBackPressed();
    }
}