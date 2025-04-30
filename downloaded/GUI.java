import javax.swing.*;
import java.awt.*;
/*
This class creates the main window holding all the other windows the app and each instance of this class runs the app.
 */
public class GUI {
    public static WelcomeWindow ww ; // The welcome window displayed all along the running of the app.
    public static JFrame mainFrame; // The main window holding all the others windows.
    public static MapWindow mw; // The map window displayed all along the running of the app.
    public static StatisticsWindow sw; // The statistics window displayed all along the running of the app.
    public static AccountWindow aw; // The account window displayed all along the running of the app.

    private GUI()
    {
        makeGUI();
    }

    public static void main(String[] args)
    {
        GUI gui = new GUI();
    }

    // Creates the main window and adds the initial window , account window , into it.
    private void makeGUI()
    {
        mainFrame = new JFrame();
        mainFrame.setPreferredSize(new Dimension(750,400));
        aw = new AccountWindow();
        mainFrame.add(aw);
        mainFrame.setVisible(true);
        mainFrame.pack();
    }


}

