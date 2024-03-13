package android.meta.instacutt;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.meta.instacutt.helper.Helper;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.meta.instacutt.databinding.ActivityMainSplashBinding;

public class MainActivitSplash extends AppCompatActivity {

    Helper helper=new Helper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED||ContextCompat.checkSelfPermission(getApplicationContext(),Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            setContentView(R.layout.activity_main_splash);
            helper.setPermission(MainActivitSplash.this);
        }else {
            startActivity(new Intent(MainActivitSplash.this,MainActivity.class));

        }



    }

    @Override
    protected void onStart() {
        super.onStart();
       // startActivity(new Intent(MainActivitSplash.this,MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
      //  startActivity(new Intent(MainActivitSplash.this,MainActivity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
       // startActivity(new Intent(MainActivitSplash.this,MainActivity.class));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED&&grantResults[1]== PackageManager.PERMISSION_GRANTED&&grantResults[2]== PackageManager.PERMISSION_GRANTED){
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        }else {
            helper.setPermission(this);

        }
    }
}





