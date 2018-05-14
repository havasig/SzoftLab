package Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;


public class Menu extends JPanel{

    //windowheight and width
    private int height = 600;
    private int width = 600;

    public Menu(JFrame window) {
        this.window = window;
        menuInit();
        this.addComponentListener(new ComponentAdapter( ) {
            public void componentResized(ComponentEvent ev) {
                start.setBounds((window.getWidth()/2)-(startImageWidth/2), 300, startImageWidth, startImageHeight );
                exit.setBounds((window.getWidth()/2)-(exitImageWidth/2), 400, exitImageWidth, exitImageHeight );
                title.setBounds((window.getWidth()/2)-(titleImageWidth/2), 50, titleImageWidth, titleImageHeight );
            }
        });
    }


    private JFrame window;

    private JButton start;
    private JButton exit;
    private JLabel title;

    private int startImageWidth = 0;
    private int startImageHeight = 0;
    private int exitImageWidth = 0;
    private int exitImageHeight = 0;
    private int titleImageWidth = 0;
    private int titleImageHeight = 0;

    private void menuInit() {

        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setFocusable(true);
        requestFocus();

        try {
            BufferedImage myPicture = ImageIO.read(new File("resources/start.png"));
            start = new JButton(new ImageIcon(myPicture));
            startImageWidth = myPicture.getWidth();
            startImageHeight = myPicture.getHeight();
            start.setBounds((window.getWidth()/2)-(startImageWidth/2), 300, startImageWidth, startImageHeight );
            this.add(start);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            BufferedImage myPicture = ImageIO.read(new File("resources/exit.png"));
            exit = new JButton(new ImageIcon(myPicture));
            exitImageWidth = myPicture.getWidth();
            exitImageHeight = myPicture.getHeight();
            exit.setBounds((window.getWidth()/2)-(exitImageWidth/2), 400, exitImageWidth, exitImageHeight );
            this.add(exit);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        try {
            BufferedImage myPicture = ImageIO.read(new File("resources/title.png"));
            title = new JLabel(new ImageIcon(myPicture));
            titleImageWidth = myPicture.getWidth();
            titleImageHeight = myPicture.getHeight();
            title.setBounds((window.getWidth()/2)-(titleImageWidth/2), 50, titleImageWidth, titleImageHeight );
            this.add(title);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        start.addActionListener(new StartButtonListener());
        exit.addActionListener(new ExitButtonListener());
    }

    boolean pushed = false;

    public void Run() {
        window.add(this);
        window.pack();
    }

    public class StartButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pushed = true;
            Game.getInstance().StartGame();
        }
    }

    public class ExitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
        }
    }
}
