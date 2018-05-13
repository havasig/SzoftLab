package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;


public class Menu extends JFrame{

    //singleton/////////////////////
    private static final Menu menu = new Menu();
    /**
     * A Menu konstruktora.
     */
    private Menu() {
        frame = new JFrame("Killer Sokoban a'la rajahalegr");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 600));
        frame.getContentPane().setBackground( Color.DARK_GRAY );
        frame.setLayout(null);
        frame.addComponentListener(new ComponentAdapter( ) {
            public void componentResized(ComponentEvent ev) {
                start.setBounds((frame.getWidth()/2)-(startImageWidth/2), 300, startImageWidth, startImageHeight );
                exit.setBounds((frame.getWidth()/2)-(exitImageWidth/2), 400, exitImageWidth, exitImageHeight );
                title.setBounds((frame.getWidth()/2)-(titleImageWidth/2), 50, titleImageWidth, titleImageHeight );
            }
        });

        contentPaneStart = frame.getContentPane();
        contentPaneExit  = frame.getContentPane();
        contentPaneTitle = frame.getContentPane();

        menuInit();

        frame.setVisible(true);

    }
    ////////////////////////////////

    static Menu getInstance() {
        return menu;
    }

    private JFrame frame;

    private JButton start;
    private JButton exit;
    private JLabel title;

    private Container contentPaneStart;
    private Container contentPaneExit;
    private Container contentPaneTitle;

    private int startImageWidth = 0;
    private int startImageHeight = 0;
    private int exitImageWidth = 0;
    private int exitImageHeight = 0;
    private int titleImageWidth = 0;
    private int titleImageHeight = 0;

    private void menuInit() {

        try {
            BufferedImage myPicture = ImageIO.read(new File("resources/start.png"));
            start = new JButton(new ImageIcon(myPicture));
            startImageWidth = myPicture.getWidth();
            startImageHeight = myPicture.getHeight();
            start.setBounds((frame.getWidth()/2)-(startImageWidth/2), 300, startImageWidth, startImageHeight );
            contentPaneStart.add(start);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            BufferedImage myPicture = ImageIO.read(new File("resources/exit.png"));
            exit = new JButton(new ImageIcon(myPicture));
            exitImageWidth = myPicture.getWidth();
            exitImageHeight = myPicture.getHeight();
            exit.setBounds((frame.getWidth()/2)-(exitImageWidth/2), 400, exitImageWidth, exitImageHeight );
            contentPaneExit.add(exit);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            BufferedImage myPicture = ImageIO.read(new File("resources/title.png"));
            title = new JLabel(new ImageIcon(myPicture));
            titleImageWidth = myPicture.getWidth();
            titleImageHeight = myPicture.getHeight();
            title.setBounds((frame.getWidth()/2)-(titleImageWidth/2), 50, titleImageWidth, titleImageHeight );
            contentPaneTitle.add(title);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        start.addActionListener(new StartButtonListener());
        exit.addActionListener(new ExitButtonListener());
    }

    public void Run() {
        while (true) { }
    }

    public class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        Game.getInstance().StartGame();
        }
    }

    public class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
