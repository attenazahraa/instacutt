 package  android.meta.instacutt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.meta.instacutt.helper.Helper;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;


 public class MainActivity extends AppCompatActivity {

  FragmentContainerView fragmentContainerView;
  BottomNavigationView bottomNavigationView;
  GalleryFragment galleryFragment;
  AboutFragment aboutFragment;
  CameraFragment cameraFragment;
     Helper hel=new Helper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

           hel.setPermissions(this);

        Toast.makeText(this, ""+hel.getImagesPath(MainActivity.this).size(), Toast.LENGTH_LONG).show();


        RatingBar rt=new RatingBar(this);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        fragmentContainerView=findViewById(R.id.fragmentContainerView);

        galleryFragment=new GalleryFragment();
        aboutFragment=new AboutFragment();
        cameraFragment=new CameraFragment();
        bottomNavigationView.setSelectedItemId(R.id.galleryNav);
        loadFragment(galleryFragment);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

             if (item.getItemId()==R.id.cameraFragment){

                     galleryFragment.stopThread();

                 loadFragment(cameraFragment);
             }
             if (item.getItemId()==R.id.aboutFragment){

                     galleryFragment.stopThread();
                loadFragment(aboutFragment);
             }

             if (item.getItemId()==R.id.galleryNav){
                 galleryFragment.startThread();
                 loadFragment(galleryFragment);
             }
                return true;
            }
        });








    }
 public  void loadFragment(Fragment fragment){
     FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
     fragmentTransaction.replace(R.id.fragmentContainerView, fragment, "");
     fragmentTransaction.commit();

 }


     @Override
     public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
         super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (hel.REQUEST_CODE==requestCode&&grantResults[0]==PackageManager.PERMISSION_DENIED){
            hel.setPermissions(this);
        }
     }
 }