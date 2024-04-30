import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Main {
    public static int X_LEN = 400, Y_LEN = 600;

    

    public static void main(String ...tushar){

        JFrame jfrm = new JFrame("App");
        jfrm.setSize(Main.X_LEN+25, Main.Y_LEN+50);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLocationRelativeTo(null);
        jfrm.setResizable(false);

        jfrm.add(new MyPaint());
        jfrm.addKeyListener(new BoardListener());


        jfrm.setVisible(true);
    }


    public static void actionListenerExample(){
        JFrame jfrm = new JFrame("App");
        jfrm.setSize(400, 400);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.setLocationRelativeTo(null);
        jfrm.setLayout(null);
        
        JTextArea lbl = new JTextArea("Pre Label");
        class NewActionKey extends KeyAdapter{
            public void keyPressed(KeyEvent k){
                if(k.getKeyCode() == KeyEvent.VK_LEFT){
                    System.out.println("Left");
                }
            }
        }
        lbl.addKeyListener(new NewActionKey());

        JScrollPane jsp = new JScrollPane(lbl);
        jfrm.add(jsp, BorderLayout.CENTER);
        jsp.setBounds(200, 200, 100, 40);

        JButton btn = new JButton("Button");
        class NewAction implements ActionListener{
            public void actionPerformed(ActionEvent ae){

                lbl.setText("New Text");

            }
        }

        btn.addActionListener(new NewAction());
        btn.setBounds(100, 100, 100, 40);
        jfrm.add(btn);


        jfrm.setVisible(true);
    }
}

class MyPaint extends Canvas{
    public static int x = 47, y = 208;
    public static boolean dir_BOTTOM = true, dir_TOP = false, dir_LEFT = false, dir_RIGHT = true;
    public static int boardCenter = 100;


    public void paint(Graphics g){

        g.fillOval(MyPaint.x, MyPaint.y, 25, 25);

        g.fillRect(MyPaint.boardCenter, Main.Y_LEN, 80, 40);
        logic();

    }

    public void logic(){        

        if(MyPaint.dir_BOTTOM)  MyPaint.y++;
        if(MyPaint.dir_LEFT)    MyPaint.x--;
        if(MyPaint.dir_TOP)    MyPaint.y--;
        if(MyPaint.dir_RIGHT)    MyPaint.x++;


        if(MyPaint.y >= Main.Y_LEN-25){
            if(MyPaint.x >= boardCenter-10 && MyPaint.x <= boardCenter+90){
                MyPaint.dir_BOTTOM = false;
                MyPaint.dir_TOP = true;    
            }
            else{
                System.exit(1);
            }
        }
        if(MyPaint.x >= Main.X_LEN){
            MyPaint.dir_LEFT = true;
            MyPaint.dir_RIGHT = false;

        }
        if(MyPaint.x <= 0){
            MyPaint.dir_LEFT = false;
            MyPaint.dir_RIGHT = true;
        }
        if(MyPaint.y <= 0){
            MyPaint.dir_TOP = false;
            MyPaint.dir_BOTTOM = true;
        }

        try{
            Thread.sleep(10);
        }catch(Exception e){}
        repaint();
    }
}

class BoardListener extends KeyAdapter{
    public static int boardSpeed = 10;
    public void keyPressed(KeyEvent ke){
        if(ke.getKeyCode() == KeyEvent.VK_LEFT && MyPaint.boardCenter > 0){
            MyPaint.boardCenter-=BoardListener.boardSpeed;
        }
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT && MyPaint.boardCenter < 340){
            MyPaint.boardCenter+=BoardListener.boardSpeed;
        }
    }
}


