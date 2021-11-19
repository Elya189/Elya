package example.lab4;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@AllArgsConstructor
public class Node {
    private int id;
    private int depth;
    private StatusNode status;
    private List<Node> linked;


    public Node(final int id, int depth) {
        this.id = id;
        this.depth = depth;
        status = StatusNode.GREEN;
        linked = new ArrayList<>();
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void addLink(final Node link) {
        linked.add(link);
    }

    public boolean isRed() {
        if (status == StatusNode.RED) {
            return true;
        } else {
            return false;
        }
    }

    public void setStatus(StatusNode status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", depth=" + depth +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Node people = (Node) o;
        return id == people.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
