import java.util.ArrayList;
import java.util.List;

public class Arvore<T> {
    T data;
    List<Arvore<T>> children;

    public Arvore(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(Arvore<T> child) {
        this.children.add(child);
    }

    public Arvore<T> getChild(T data) {
        for (Arvore<T> child : children) {
            if (child.data.equals(data)) {
                return child;
            }
        }
        return null;
    }

    public void printTree(String prefix) {
        System.out.println(prefix + data);
        for (Arvore<T> child : children) {
            child.printTree(prefix + "--");
        }
    }
}
