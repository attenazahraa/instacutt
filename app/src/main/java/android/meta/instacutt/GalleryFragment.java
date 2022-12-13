package android.meta.instacutt;

import android.database.Cursor;
import android.meta.instacutt.helper.Helper;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GalleryFragment extends Fragment {

    List<RecycleViewDataModel> recycleViewDataModelList=new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerViewAdaptor recyclerViewAdaptor;
    ProgressBar progressBarGalleryFraf;
    TextView textViewLoadImg;
     Handler galleryHandeler=new Handler();
    Helper hl=new Helper();
    ThreadInit threadInit=new ThreadInit();
    int index;


    public GalleryFragment() {
        // Required empty public constructor

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         index=new Helper().getAllImagesPath(getActivity()).length;

        View GalleryView = inflater.inflate(R.layout.fragment_gallery, container, false);


        textViewLoadImg=GalleryView.findViewById(R.id.FragGalleryTextView);
        progressBarGalleryFraf=GalleryView.findViewById(R.id.GalleryFragmentProgressBar);
         RecyclerView recyclerView=GalleryView.findViewById(R.id.GalleryRecycleView);

         recycleViewDataModelList=initArray();

        recyclerViewAdaptor=new RecyclerViewAdaptor(recycleViewDataModelList,getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(recyclerViewAdaptor);
        threadInit.start();




        // Inflate the layout for this fragment
        return GalleryView;
    }


      public void stopThread()  {
threadInit.check=true;

      }

      public void startThread(){
        threadInit.check=false;

      }






    public List<RecycleViewDataModel> initArray(){
        Helper helper1=new Helper();
        int index=helper1.initialData(getActivity()).size();


            recycleViewDataModelList=new ArrayList<>();
           for (int i=0;i<1;i++){
                
               Uri uri=helper1.initialData(getActivity()).get(0);
               recycleViewDataModelList.add(i,new RecycleViewDataModel(uri));
           }

           return recycleViewDataModelList;



    }
public  class ThreadInit extends Thread{

       ThreadInit(){}
    Uri uri;
       int ik=0;
    boolean check=false;

    @Override
    public void run() {

for (int i=0;i<index;i++){

   if (check){break;}

        uri=hl.initialData(getActivity()).get(i);
        boolean mHandeler=new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
         public void run() {

          recycleViewDataModelList.add(new RecycleViewDataModel(uri));

          recyclerViewAdaptor.notifyItemInserted(ik);


            ik++;
            progressBarGalleryFraf.setVisibility(View.GONE);
            textViewLoadImg.setVisibility(View.GONE);
          }

    });

    }

    };


}


}