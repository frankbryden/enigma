import javax.swing.*;

public class SimulationWindow extends JFrame {
    int width, height;
    TrafficRender trafficRender;

    public SimulationWindow(int width, int height){
        this.width = width;
        this.height = height;
        this.setSize(width, height);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        trafficRender = new TrafficRender();
        this.add(trafficRender);
    }

    public void runSim(){
        while (true){
            this.trafficRender.update();
            this.trafficRender.repaint();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Log.e("Got interrupted...");
            }
        }
    }

    public TrafficRender getTrafficRender() {
        return trafficRender;
    }
}
