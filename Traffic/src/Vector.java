public class Vector {
    double x, y;

    public Vector(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void multiply(double factor){
        this.x *= factor;
        this.y *= factor;
    }

    public void mulX(double factor){
        this.x *= factor;
    }

    public void mulY(double factor){
        this.y *= factor;
    }

    public double getVelX(Vector target){
        return Math.cos(Math.atan2(target.y - this.y, target.x - this.x));
    }

    public double getVelY(Vector target){
        return Math.sin(Math.atan2(target.y - this.y, target.x - this.x));
    }
}
