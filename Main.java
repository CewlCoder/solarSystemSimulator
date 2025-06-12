import javax.swing.JFrame;

import view.View;

public class Main {
    
    public static void main(String[] args) {
        View view = new View();

        JFrame frame = new JFrame("Solar system simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }

}
