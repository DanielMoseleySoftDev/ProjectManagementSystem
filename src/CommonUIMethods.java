import javax.swing.*;

public abstract class CommonUIMethods extends JFrame{

    public void onExit(JFrame mainFrame) { //un-freezes main GUI and closes the pop out window
        mainFrame.setEnabled(true);
        this.dispose();
    }
}
