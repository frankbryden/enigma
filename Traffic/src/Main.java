public class Main {
    public static void main(String[] args){
        SimulationWindow simulationWindow = new SimulationWindow(1024, 720);
        TrafficRender tf = simulationWindow.getTrafficRender();
        Car car = new Car(20, 20, 50);
        tf.addEntity(car);
        simulationWindow.runSim();
    }
}
