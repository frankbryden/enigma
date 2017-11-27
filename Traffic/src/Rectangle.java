public class Rectangle {
    int x, y;
    int width, height;

    public Rectangle(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    public Boolean intersects(Rectangle r){
        if (r.x < this.x + this.width && r.x > this.x){
            if (r.y < this.y + this.height && r.y > this.y){
                Log.i("Rectangles intersect");
                return true;
            }
        }
        return false;
    }
}
