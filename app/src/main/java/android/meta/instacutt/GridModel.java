package android.meta.instacutt;

import android.net.Uri;

public class GridModel {

    private int imgResource;
   private Uri uri;

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Uri getUri() {
        return uri;
    }

    public GridModel(Uri uri) {
        this.uri = uri;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }






}
