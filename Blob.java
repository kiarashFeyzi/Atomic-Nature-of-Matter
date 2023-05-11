public class Blob {
    public Blob(){
        x_avg = 0;
        y_avg = 0;
        mass = 0;
    }
    public void add(int x, int y){
        x_avg = (x_avg * mass + x) / (mass + 1);
        y_avg = (y_avg * mass + y) / (mass + 1);
        mass++;
    }
    private int mass;
    public double distanceTo(Blob secondBlob){
        return Math.sqrt(Math.pow(x_avg - secondBlob.x_avg, 2) + Math.pow(y_avg - secondBlob.y_avg, 2));
    }
    public String toString(){
        String x_forPrint = x_avg + "",
                y_forPrint = y_avg +  "";
        float   xDecimalString =(float) (x_avg - (int) x_avg),
                yDecimalString = (float)(y_avg - (int) y_avg);

        //with using double we have problem

        if ((xDecimalString + "").length()  < 6)
            for (int i = (xDecimalString + "").length(); i < 6; i++)
                x_forPrint += "0";
        if ((yDecimalString + "").length() < 6)
            for (int i = (yDecimalString + "").length(); i < 6; i++)
                y_forPrint += "0";
        return  mass +" (" + x_forPrint + ", " + y_forPrint +")";
    }
    private double x_avg;
    private double y_avg;
    public double getX_avg() {
        return x_avg;
    }
    public double getY_avg() {
        return y_avg;
    }
    public void setX_avg(double x_avg) {
        this.x_avg = x_avg;
    }
    public void setY_avg(double y_avg) {
        this.y_avg = y_avg;
    }
    public int getMass() {
        return mass;
    }
    public static void main(String[] args){

    }
}

