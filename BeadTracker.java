public class BeadTracker {

    private static double radius_aroundTheBlob;
    private static int min_pixel;
    private static double tau;

    private BeadTracker(int minimumPixel, double tauNumber, double delta) {
        min_pixel = minimumPixel;
        tau = tauNumber;
        radius_aroundTheBlob = delta;
    }
    public static void main(String[] args) {

        int minimum_pixel = Integer.parseInt(args[0]);
        double tau_2 = Double.parseDouble(args[1]);
        double delta = Double.parseDouble(args[2]);



        BeadTracker beadTracker = new BeadTracker(minimum_pixel, tau_2,delta);


        for (int i = 0; i < 199; i++) {
            Picture pic = new Picture(args[i + 3]);
            BeadFinder beadFinder = new BeadFinder(pic, tau);
            Blob[] blobs1 = beadFinder.getBeads(min_pixel);

            pic = new Picture(args[i + 4]);
            beadFinder = new BeadFinder(pic, tau);
            Blob[] blobs2 = beadFinder.getBeads(min_pixel);

            double minDistance = 0;
            for (int i2 = 0; i2 < blobs1.length; i2++){
                minDistance = blobs1[i2].distanceTo(blobs2[0]);
                boolean b = false;
                for (int j = 0; j < blobs2.length; j++){
                    if (blobs1[i2].distanceTo(blobs2[j]) < radius_aroundTheBlob){
                        b = true;
                        if (blobs1[i2].distanceTo(blobs2[j]) < minDistance)
                            minDistance = blobs1[i2].distanceTo(blobs2[j]);
                    }
                }
                if (b){
                    System.out.printf("%.4f", minDistance);
                    System.out.println();
                }
            }
            System.out.println();
        }

    }
}




