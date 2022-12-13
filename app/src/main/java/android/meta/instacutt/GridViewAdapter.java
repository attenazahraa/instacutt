package android.meta.instacutt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class GridViewAdapter extends ArrayAdapter<GridModel> {


        public GridViewAdapter(@NonNull Context context, ArrayList<GridModel> resource) {
                super(context,0, resource);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ListItemView=convertView;
        if (ListItemView==null){
           ListItemView=LayoutInflater.from(getContext()).inflate(R.layout.grid_row,parent,false);
        }
         GridModel gridModel=getItem(position);
        ImageView imageView=ListItemView.findViewById(R.id.gridRowImageView);
        imageView.setImageURI(gridModel.getUri());

        return ListItemView;
        }
}
