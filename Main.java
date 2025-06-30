import javax.swing.JFrame;

import model.Model;
import view.View;

public class Main {
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);

        JFrame frame = new JFrame("Solar system simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }

}
