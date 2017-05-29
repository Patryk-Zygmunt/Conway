/**
 * Created by Linus on 11.11.2016.
 */
public  class Coords2D implements CellCoordinates, Comparable {
     public int x;
    public int y;


    public Coords2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coords2D() {

    }

    @Override
    public String toString() {
        return "Coords2D{" +
                "x=" + x +
                ", y=" + y +
                "}\n";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coords2D coords2D = (Coords2D) o;

        if (x != coords2D.x) return false;
        return y == coords2D.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;

    }

    public int compareTo(Object o) {
        if (this == o) return 1;
        if (o == null || getClass() != o.getClass()) return -1;

        Coords2D c = (Coords2D) o;
        if (x > c.x)
            return 1;
        if (x < c.x)
            return -1;
        else {
            if (y > c.y)
                return 1;
            if (y < c.y)
                return -1;
        }
        return 0;
    }


}