import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;


class Game extends JPanel implements KeyListener, ActionListener{
    private boolean play=false;
    private int score1=0;
    private int score2=0;


    private Timer time;
    private int delay=4;

    private int s1px=270;
    private int s2px=270;
    private int s1py=90;
    private int s2py=570;

    private int bpx=285;
    private int bpy=340;
    private int bxd=-1;
    private int byd=-2;

    public Game(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay,this);
        time.start();
    }
    public void paint(Graphics g){
        //super.paint(g);


        g.setColor(Color.BLACK);
        g.fillRect(1,2,590,690);

        g.setColor(Color.RED);
        g.fillRect(0,350,10,700);
        g.fillRect(0,650,240,10);
        g.fillRect(360,650,270,10);
        g.fillRect(574,350,10,700);


        g.setColor(Color.BLUE);
        g.setFont(new Font("serif",Font.BOLD,28));
        g.drawString("Player 1",30,320);

        g.setColor(Color.BLUE);
        g.setFont(new Font("serif",Font.BOLD,28));
        g.drawString(String.valueOf(score1),30,350);


        g.setColor(Color.RED);
        g.setFont(new Font("serif",Font.BOLD,28));
        g.drawString(String.valueOf(score2),30,380);


        g.setColor(Color.RED);
        g.setFont(new Font("serif",Font.BOLD,28));
        g.drawString("Player 2",30,410);

        g.setColor(Color.BLUE);
        g.fillRect(0,10,10,340);
        g.fillRect(0,0,240,10);
        g.fillRect(360,0,250,10);
        g.fillRect(574,0,10,350);


        g.setColor(Color.green);
        //g.fillOval(s1px,s1py,40,40);
        g.fillRect(s1px,s1py,60,10);

        g.setColor(Color.ORANGE);
        //g.fillOval(s2px,s2py,40,40);
        g.fillRect(s2px,s2py,60,10);




        g.setColor(Color.GRAY);
        g.drawOval(240,600,120,120);
        g.drawOval(240,300,120,120);
        g.drawLine(0,350,600,350);
        g.drawOval(240,-45,120,120);


        g.setColor(Color.YELLOW);
        g.fillOval(bpx,bpy,20,20);


        g.dispose();
    }

    public void gameOver(){
        if(score1==5){
            JOptionPane.showMessageDialog(this,"Game Over \nPlayer 1 won! \nPlayer1 score = "+score1+"\nPlayer2 score = "+score2,"Game Over"+score2,JOptionPane.YES_NO_OPTION);
            System.exit(ABORT);
        }
        else {
            JOptionPane.showMessageDialog(this,"Game Over \nPlayer 2 won! \nPlayer1 score = "+score1+"\nPlayer2 score = "+score2,"Game Over"+score2,JOptionPane.YES_NO_OPTION);
            System.exit(ABORT);
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        time.start();
        if(play){
            if(new Rectangle(bpx,bpy,20,20).intersects(new Rectangle(s1px,s1py,60,10)))
            {
                byd=-byd;
            }
            if(new Rectangle(bpx,bpy,20,20).intersects(new Rectangle(s2px,s2py,60,10)))
            {
                byd=-byd;
            }
            /*
            double xDif = s1px - bpx;
            double yDif = s1py - bpy;
            double distanceSquared = xDif * xDif + yDif * yDif;
            boolean collision = distanceSquared < (30) * (30);
            if(collision)
            {
                byd=-byd;
            }
            xDif = s2px - bpx;
            yDif = s2py - bpy;
            distanceSquared = xDif * xDif + yDif * yDif;
            collision = distanceSquared < (30) * (30);
            if(collision)
            {
                byd=-byd;
            }*/
            if(new Rectangle(bpx,bpy,20,20).intersects(new Rectangle(240,650,120,10)))
            {
                score1++;
                bpx=300;
                bpy=300;
                byd=-byd;
            }
            if(new Rectangle(bpx,bpy,20,20).intersects(new Rectangle(240,0,120,10)))
            {
                score2++;
                bpx=300;
                bpy=300;

            }
            bpx+=bxd;
            bpy+=byd;
            if(bpx<10){
                bxd=-bxd;
            }
            if(bpy<10){
                byd=-byd;
            }
            if(bpx>560){
                bxd=-bxd;
            }
            if(bpy>630){
                byd=-byd;
            }
            if(score1==5 || score2==5){
                gameOver();
            }
            this.repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode()==KeyEvent.VK_RIGHT){
            if(s1px+20>=510){
                s1px=510;
            }
            else {
                s1px=moveright(s1px);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_LEFT) {
            if (s1px-20 <= 15) {
                s1px = 15;
            } else {
                s1px=moveleft(s1px);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_DOWN) {
            if (s1py+20 >= 310) {
                s1py = 310;
            }
            else {
                s1py=movedown(s1py);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_UP) {
            if (s1py-20 <= 10) {
                s1py = 10;
            } else {
                s1py=moveup(s1py);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_A) {
            if (s2px-20 <= 15) {
                s2px = 15;
            }
            else{
                s2px=moveleft(s2px);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_D) {
            if(s2px+20>=510){
                s2px=510;
            }
            else {
                s2px=moveright(s2px);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_S) {
            if (s2py+20 >= 610) {
                s2py = 610;
            }
            else {
                s2py=movedown(s2py);
            }
        }
        else if(keyEvent.getKeyCode()==KeyEvent.VK_W) {
            if (s2py-20 <= 390) {
                s2py = 390;
            }
            else {
                s2py=moveup(s2py);
            }
        }

    }

    public int moveright(int i){
        play=true;
        return i+20;
    }
    public int moveleft(int i){
        play=true;
        return i-20;
    }
    public int movedown(int i){
        play=true;
        return i+20;
    }
    public int moveup(int i){
        play=true;
        return i-20;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }


}


public class Gameplay{
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Gameplay");
        Game game = new Game();
        frame.add(game);
        frame.setSize(600,700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true){
            game.repaint();
            Thread.sleep(10);
        }
    }
}