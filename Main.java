import javax.swing.JFrame;

import controller.Controller;
import model.Model;
import view.View;

//Fix draw detection in projector

public class Main {
    
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);

        JFrame frame = new JFrame("Solar system simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(view);
        frame.pack();
        frame.setVisible(true);
    }

}
