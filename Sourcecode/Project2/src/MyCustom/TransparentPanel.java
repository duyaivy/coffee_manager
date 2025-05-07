package MyCustom;

import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel {

    public TransparentPanel() {
        this.setOpaque(false);
    }

    public TransparentPanel(LayoutManager layout) {
        this.setLayout(layout);
    }

}