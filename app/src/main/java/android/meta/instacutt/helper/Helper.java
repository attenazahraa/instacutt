package android.meta.instacutt.helper;

import static androidx.activity.result.ActivityResultCallerKt.registerForActivityResult;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.meta.instacutt.RecycleViewDataModel;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Helper extends AppCompatActivity {
    public int REQUEST_CODE=100;
    public   String[] permission=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.CAMERA,Manifest.permission.MANAGE_EXTERNAL_STORAGE};
    static Context mcontext;
     Activity activ;
    boolean permbool=false;
    boolean sdCardIsPesent;

    public  boolean setPermissions(Activity context){
     activ=context;
    mcontext=(Context)context;

    // sala
   

  if(ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(context,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED&&ContextCompat.checkSelfPermission(context,Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){
        //permissions is granted!

        }else {


      requestPermissin();
    }



return permbool;

    }
    public  void requestPermissin(){

      if (ActivityCompat.shouldShowRequestPermissionRationale(activ,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
          new AlertDialog.Builder(activ)
                  .setTitle("درخواست مجوز")
                  .setMessage("برای دسترسی به حافظه و ذوربین باید مجوز را تایید کنید")
                  .setPositiveButton("موافقم", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {

                          reqPermission();

                      }
                  })
                  .setNegativeButton("لغو", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int i) {

                          dialogInterface.dismiss();
                          reqPermission();


                      }
                  })
                  .create()
                  .show();
      }


    }
    public void reqPermission(){

        ActivityCompat.requestPermissions(activ,permission,REQUEST_CODE);
    }

    public String[] getAllImagesPath(Activity activity){
    Uri storagUri=MediaStore.Images.Media.INTERNAL_CONTENT_URI;

        boolean sdCardIs=android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (sdCardIs){
            storagUri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        }

        if (!sdCardIs){
            storagUri=MediaStore.Images.Media.INTERNAL_CONTENT_URI;
        }

        final String[] columns={MediaStore.Images.Media.DATA,MediaStore.Images.Media._ID};
   final String orderBy=MediaStore.Images.Media._ID;

   Cursor cursor=activity.getContentResolver().query(storagUri,columns,null,null,orderBy);


   int count=cursor.getCount();
   String[] arrPath=new String[count];

   for (int i=0;i<count;i++){
       cursor.moveToPosition(i);
       int dataColumnsIndex=cursor.getColumnIndex(MediaStore.Images.Media.DATA);
       arrPath[i]=cursor.getString(dataColumnsIndex);



   }
cursor.close();
   return arrPath;


    }

    public Uri getUriFromPat(Context context, String file){


        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.Media._ID }, MediaStore.Images.Media.DATA + "=? ", new String[] { file }, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns._ID));
            cursor.close();
            return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + id);
        } else {
            if (!file.isEmpty()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, file);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            }




        return  null;
    }


    }

    public Uri[] t(Activity context){

    String[] paths=getAllImagesPath(context);
    int index=paths.length;
    Uri[] th=new Uri[index];

       List<Uri> uriList=new ArrayList<>(index);

        for (int i=0;i<5;i++){
        String s=paths[i];
       getUriFromPat(context,s);
        th[i]= getUriFromPat(context,s);
        uriList.add(i,getUriFromPat(context,s));
        
    }


        return th;}

     public List<Uri> initialData(Activity context){

        final String DATA_COLOUMN=MediaStore.Images.Media.DATA;
        final String ID_COLOUMN=MediaStore.Images.Media._ID;
        final  String ORDER_BY=MediaStore.Images.Media._ID;
        List<Uri> uriList=new ArrayList<>();
         String s1="";
         int id=0;
         final String[] COLOUMNs={ID_COLOUMN,DATA_COLOUMN};
         Uri EXTR_URI=MediaStore.Images.Media.INTERNAL_CONTENT_URI;
         boolean checkSD_MOUNTED=android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

         if (checkSD_MOUNTED){
         EXTR_URI=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
         }
         Cursor cursor=context.getContentResolver().query(EXTR_URI,COLOUMNs,null,null,null);
      int index=cursor.getCount();
      for (int i=0;i<index;i++){
          cursor.moveToPosition(i);
         id=cursor.getColumnIndex(ID_COLOUMN);
         int indx=cursor.getInt(id);
        uriList.add(Uri.withAppendedPath(EXTR_URI,""+indx));


      }

cursor.close();


return  uriList;
}

    public List<String> getImagesPath(Activity context){
    List<String> PatList=new ArrayList<>();
        int COLOUMN_INDEX;
        final String DATA_COLOUMN=MediaStore.MediaColumns.DATA;
        final String ID_COLOUMN=MediaStore.Images.Media._ID;
        final  String ORDER_BY=MediaStore.Images.Media._ID;
      //  final  String BUCKET_COULOMN=MediaStore.MediaColumns.BUCKET_DISPLAY_NAME  ;
               String[] COLOUMS={DATA_COLOUMN};

    Uri MediaUri;
    boolean checkSD_MOUNTED=android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
     MediaUri=checkSD_MOUNTED?MediaUri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI:MediaStore.Images.Media.INTERNAL_CONTENT_URI;
        MediaUri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    Cursor cursor=context.getContentResolver().query(MediaUri,COLOUMS,null,null,null);
    if(cursor.getCount()==0||cursor.getColumnIndex(DATA_COLOUMN)==-1){
       return null;
    }
    COLOUMN_INDEX=cursor.getColumnIndex(DATA_COLOUMN);
    while (cursor.moveToNext()){
        PatList.add(cursor.getString(COLOUMN_INDEX));
    }

        return PatList;
}

}

