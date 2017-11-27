import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;


public class TrafficRender extends JPanel{
    ArrayList<Entity> entities;
    long last_update;

    public TrafficRender(){
        this.entities = new ArrayList<Entity>();
        last_update = System.currentTimeMillis();
    }

    public void update(){
        long delta = System.currentTimeMillis() - last_update;
        last_update = System.currentTimeMillis();
        for (Entity e : entities){
            e.update(delta);
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Entity e : entities){
            e.draw(g2d);
        }

    }

    public void addEntity(Entity e){
        Log.i("Added entity");
        this.entities.add(e);
    }

    public void removeEntity(Entity e){
        Iterator<Entity> it = entities.iterator();
        while (it.hasNext()){
            Entity entity = it.next();
            if (entity.equals(e)){
                Log.i("Removed entity");
                it.remove();
                return;
            }
        }
    }

}
