package android.meta.instacutt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class RecyclerViewAdaptor extends RecyclerView.Adapter<RecyclerViewAdaptor.DataViewHolder> {

    List<RecycleViewDataModel> DataModelList= Collections.emptyList();
    Context Mcontext;


    public RecyclerViewAdaptor(List<RecycleViewDataModel> dataModelList, Context mcontext) {
        DataModelList = dataModelList;
        Mcontext = mcontext;
    }



    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row,parent,false);


        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        Bitmap bitmap= null;
        Bitmap bitmap1=null;
        try {
        //    Bitmap b=Bitmap.createBitmap()
            bitmap = MediaStore.Images.Media.getBitmap(Mcontext.getContentResolver(),DataModelList.get(position).getUri());
            ParcelFileDescriptor fileDescriptor=Mcontext.getContentResolver().openFileDescriptor(DataModelList.get(position).getUri(),"rw");
            FileDescriptor fileDescriptor1=fileDescriptor.getFileDescriptor();
             bitmap1= BitmapFactory.decodeFileDescriptor(fileDescriptor1);
           //  bitmap1.compress(png,)

        } catch (IOException e) {
            e.printStackTrace();
        }
        holder.img1.setImageBitmap(bitmap1);
    // holder.img1.setImageURI(DataModelList.get(position).getUri());



    }


    @Override
    public int getItemCount() {
        return DataModelList.size();
    }


    public  class DataViewHolder extends RecyclerView.ViewHolder {
     Uri uri;
     ImageView img1;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.RecycleRowImageView);

        }
    }

}
