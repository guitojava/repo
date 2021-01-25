package gr.boomer.fm.fmdao;

public class RootFolderPathObject {

    public String name;
    public String path;
    public Object children;

    public RootFolderPathObject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getChildren() {
        return children;
    }

    public void setChildren(Object children) {
        this.children = children;
    }
}
