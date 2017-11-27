import java.awt.*;
import java.util.Random;

public class Car extends Vehicle {
    static int width = 20;
    static int height = 10;

    Rectangle car_rect;
    Vector current_speed;
    Vector target;

    Random random;
    int timer;
    final int NEW_TARGET = 10000;

    public Car(int x, int y, int max_speed){
        this.max_speed = max_speed;
        this.car_rect = new Rectangle(x, y, width, height);
        this.current_speed = new Vector(1, 1);
        this.timer = NEW_TARGET;
        this.random = new Random(System.currentTimeMillis());
        getNewTarget();
    }

    public void getNewTarget(){
        this.target = new Vector(random.nextDouble() * 1024.0, random.nextDouble()* 720.0);
    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(car_rect.x, car_rect.y, car_rect.width, car_rect.height);
        g.fillOval((int)target.x, (int)target.y, 10, 10);
    }

    @Override
    public void update(long delta) {
        timer -= delta;
        if (timer <= 0){
            timer = NEW_TARGET;
        }
        /*this.current_speed.x = this.current_speed.getVelX(target);
        this.current_speed.y = this.current_speed.getVelY(target);*/
        if (car_rect.x < target.x){
            this.current_speed.mulX(1.1);
        } else {
            this.current_speed.mulX(-1.1);
        }
        if (car_rect.y < target.y){
            this.current_speed.mulY(1.1);
        } else {
            this.current_speed.mulY(-1.1);
        }
        this.car_rect.x += this.current_speed.x;
        this.car_rect.y += this.current_speed.y;
        //Log.i("Speed is x = " + String.valueOf(current_speed.x) + " y = " + String.valueOf(current_speed.y));
    }

}