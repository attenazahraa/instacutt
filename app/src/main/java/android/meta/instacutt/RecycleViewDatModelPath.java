package android.meta.instacutt;

public class RecycleViewDatModelPath extends RecycleViewDataModel {
    public String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RecycleViewDatModelPath(String path) {
        super(path);
        this.path = path;
    }

}
