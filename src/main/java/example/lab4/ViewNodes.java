package example.lab4;

import com.google.common.base.Function;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.DefaultVisualizationModel;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.VisualizationModel;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.PickableEdgePaintTransformer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.MultiPickedState;
import edu.uci.ics.jung.visualization.picking.PickedState;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.util.List;


import static example.lab4.Simulation.DELAY;

public class ViewNodes extends JApplet {

    private Forest<Node, Number> graph = Simulation.createNetwork1();
    private VisualizationViewer<Node, Number> vv1;
    private JButton start = new JButton("Старт");
    private Dimension preferredSize = new Dimension(600, 600);

    public ViewNodes() {
        Layout<Node, Number> layout1 = new TreeLayout(graph);

        VisualizationModel<Node, Number> vm1 = new DefaultVisualizationModel(layout1, preferredSize);

        vv1 = new VisualizationViewer(vm1, preferredSize);


        vv1.setBackground(Color.white);

        PickedState<Node> ps = new MultiPickedState();
        vv1.setPickedVertexState(ps);

        PickedState<Number> pes = new MultiPickedState();
        vv1.setPickedEdgeState(pes);

        vv1.getRenderContext().setEdgeDrawPaintTransformer(new PickableEdgePaintTransformer(vv1.getPickedEdgeState(), Color.black, Color.red));
        vv1.getRenderContext().setVertexFillPaintTransformer(new Function<Node, Paint>() {

            @Override
            public Paint apply( Node node) {
                if (node.getStatus() == StatusNode.RED) {
                    return Color.RED;
                }
                if (node.getStatus() == StatusNode.YELLOW) {
                    return Color.YELLOW;
                }
                return Color.GREEN;
            }
        });
        vv1.setVertexToolTipTransformer(new ToStringLabeller());

        Container content = getContentPane();
        JPanel panel = new JPanel(new GridLayout(1, 0));
        panel.add(new GraphZoomScrollPane(vv1));

        content.add(panel);
        DefaultModalGraphMouse<Node, Number> gm1 = new DefaultModalGraphMouse();
        vv1.setGraphMouse(gm1);

        JPanel simPanel = new JPanel();
        simPanel.setBorder(BorderFactory.createTitledBorder("Поиск"));

        start.addActionListener(e -> {
            start.setEnabled(false);
            sim();
        });
        simPanel.add(start);
        JPanel controls = new JPanel();
        controls.add(simPanel);
        content.add(controls, "South");
    }

    private void sim() {
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Node result = null;

                Node secret = Simulation.generateSecretNode();
                vv1.updateUI();
                while (result == null) {
                    result = recursiveSearch(0);
                }
                if (secret != result) {
                    System.out.println("Ошибка при рекурсивном поиске");
                } else {
                    System.out.println("Рекурсивный поиск нашёл: " + result);
                }
                start.setEnabled(true);
            }
        }).start();

    }

    private Node recursiveSearch(int searchDepth) throws InterruptedException {
        List<Node> list = Simulation.getNodesByDepth(searchDepth);
        if (list == null) {
            return null;
        }
        Node result = null;
        for (Node p : list) {
            if (p.isRed()) {
                result = p;
                break;
            }
            p.setStatus(StatusNode.YELLOW);
            vv1.updateUI();
            Thread.sleep(DELAY);
        }
        if (result != null) {
            return result;
        }
        return recursiveSearch(searchDepth + 1);
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.getContentPane().add(new ViewNodes());
        f.pack();
        f.setVisible(true);
    }
}
