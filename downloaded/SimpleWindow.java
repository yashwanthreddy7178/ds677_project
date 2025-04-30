package js11.guiCoba;

import javax.swing.JWindow;

/**
 *
 * @author ardan
 */
public class SimpleWindow extends JWindow{

    public SimpleWindow() {
        super();
        setBounds(0,0,300,150);
    }
    
    public static void main(String[] args) {
        SimpleWindow sw = new SimpleWindow();
        
    }
}
