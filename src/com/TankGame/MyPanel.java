package com.TankGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import java.util.Iterator;
import java.awt.Rectangle; // Import Rectangle
//import java.awt.event.KeyEvent;
//this is the panel of the game
//为了实现监听键盘事件，需要实现KeyListener
//实现KeyListener创建按下按键快捷键是Ctrl+i
public class MyPanel extends JPanel implements KeyListener, Runnable { // Implement Runnable
    MyTank mytank=null;
    //采用vector 集合，存储多个坦克
    Vector<EnemyTank> enemyTanks=new Vector<>();
    int enemyTankSize=3;
    Vector<Bullet> bullets = new Vector<>(); // Store bullets


    public MyPanel() {
        //this.mytank = mytank;
        //初始化自己坦克
        mytank=new MyTank(100,100);
        mytank.setSpeed(2);
        //初始化敌方坦克
        for(int i=0;i<enemyTankSize;i++) {
           EnemyTank enemyTank=new EnemyTank(100*(i+1),0);
           // enemyTank.setDirect(2); // Removed to allow random initial direction from EnemyTank constructor
            enemyTanks.add(enemyTank);

        }
        new Thread(this).start(); // Start the repaint thread


    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色
        drawTank(mytank.getX(),mytank.getY(),g,mytank.getDirect(),0);
        for(int i=0;i<enemyTanks.size();i++)
        {
            EnemyTank enemyTank=enemyTanks.get(i);
            drawTank(enemyTank.getX(),enemyTank.getY(),g,enemyTank.getDirect(),1);
        }

        // Draw bullets
        g.setColor(Color.WHITE); // Set bullet color
        for (Bullet bullet : bullets) { // Iterate and draw remaining bullets
            // Draw bullet as a small circle, adjusting for its center
            g.fillOval(bullet.getX() - 2, bullet.getY() - 2, 4, 4);
        }
    }

    private void checkCollisions() {
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();
            // boolean bulletHit = false; // Not strictly needed if we break inner loop

            Iterator<EnemyTank> enemyTankIterator = enemyTanks.iterator();
            while (enemyTankIterator.hasNext()) {
                EnemyTank enemyTank = enemyTankIterator.next();

                // Define bullet rectangle
                Rectangle bulletRect = new Rectangle(bullet.getX() - 2, bullet.getY() - 2, 4, 4);

                // Define enemy tank rectangle based on its direction
                Rectangle tankRect;
                if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) { // Up or Down
                    tankRect = new Rectangle(enemyTank.getX(), enemyTank.getY(), 40, 60);
                } else { // Right or Left
                    tankRect = new Rectangle(enemyTank.getX(), enemyTank.getY(), 60, 40);
                }

                if (bulletRect.intersects(tankRect)) {
                    bulletIterator.remove(); // Remove the bullet
                    enemyTankIterator.remove(); // Remove the enemy tank
                    // bulletHit = true; // Mark bullet as hit
                    break; // Bullet is gone, no need to check against other tanks for this bullet
                }
            }
        }
    }
    //画出坦克
    /*x 坦克左上角的x坐标
    y 坦克左上角的y坐标
    direct 坦克方向(0:向上 1：向右 2：向下 3：向左)
    type 坦克类型(0:自机 1：敌方)
    */
    public void drawTank(int x,int y,Graphics g,int direct,int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;

        }
        //根据坦克方向绘制坦克
        switch (direct) {
            case 0://向上
                g.fill3DRect(x,y,10,60,false);//画出坦克左边轮子
                g.fill3DRect(x+30,y,10,60,false);//画出坦克右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出坦克中间矩形
                g.fillOval(x+10,y+20,20,20);//画出坦克圆盖子
                g.drawLine(x+20,y+30,x+20,y); //画出坦克炮口
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://向下
                g.fill3DRect(x,y,10,60,false);//画出坦克左边轮子
                g.fill3DRect(x+30,y,10,60,false);//画出坦克右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//画出坦克中间矩形
                g.fillOval(x+10,y+20,20,20);//画出坦克圆盖子
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            mytank.setDirect(0);
            mytank.moveUp();
        }else if(e.getKeyCode()==KeyEvent.VK_D){
            mytank.setDirect(1);
            mytank.moveRight();
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            mytank.setDirect(2);
            mytank.moveDown();
        }else if(e.getKeyCode()==KeyEvent.VK_A){
            mytank.setDirect(3);
            mytank.moveLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_J || e.getKeyCode() == KeyEvent.VK_SPACE) { // Shoot key
            Bullet bullet = mytank.shoot();
            if (bullet != null) {
                bullets.add(bullet);
            }
        }
        // this.repaint(); // Removed as repaint is handled by the thread

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50); // Adjust delay for bullet speed/smoothness
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Update bullets and remove if off-screen
            Iterator<Bullet> bulletUpdaterIterator = bullets.iterator();
            while (bulletUpdaterIterator.hasNext()) {
                Bullet bullet = bulletUpdaterIterator.next();
                bullet.update();
                // Check for off-screen and remove
                if (bullet.getX() < 0 || bullet.getX() > 1000 || bullet.getY() < 0 || bullet.getY() > 750) {
                    bulletUpdaterIterator.remove();
                }
            }

            // Update enemy tanks
            for (EnemyTank enemyTank : enemyTanks) {
                enemyTank.update();
            }

            checkCollisions(); // Call the collision detection method

            repaint();
        }
    }
}
