package android.meta.instacutt;

import android.net.Uri;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class RecycleViewDataModel  {

 Uri uri;
 String path;

    public RecycleViewDataModel(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public RecycleViewDataModel(Uri uri) {
        this.uri = uri;
    }
}
