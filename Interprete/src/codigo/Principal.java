package codigo;

import javax.swing.*;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame= new MainFrame();
                frame.setSize(800,800);
                frame.setVisible(true);
            }
        });
    }
}
