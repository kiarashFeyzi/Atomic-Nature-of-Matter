import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BeadFinder {
    public BeadFinder (Picture picture, double tau) {

        for (int i = 0; i < picture.width(); i++)
            for (int j = 0; j < picture.height(); j++) {

                Color pix_color = picture.get(i,j);

                int red = pix_color.getRed(),
                        green = pix_color.getGreen(),
                        blue = pix_color.getBlue();

                double luminance = (red * 0.2126 + green * 0.7152 + blue * 0.0722) ;
                if (!(luminance >= (tau)))
                    // if (red < tau && green < tau && blue < tau) doesnt work at least with pic 198
                    picture.set(i,j, Color.black);
                //else picture.set(i, j, Color.white);
            }

        thePic = picture;


        //picture.show();
    }
    public Blob[] getBeads (int min){
        ArrayList<Blob> blobList = new ArrayList<Blob>();
        for (int i = 0; i < thePic.width(); i++)
            for (int j = 0; j < thePic.height(); j++)
                if (!thePic.get(i, j).equals(Color.yellow) && !thePic.get(i, j).equals(Color.black)) {
                    theCurrentBlob = new Blob();
                    recursive(i, j, thePic);
                    if (theCurrentBlob.getMass() >= min){
                        theCurrentBlob.setX_avg(roundTheValue((float)theCurrentBlob.getX_avg()));
                        theCurrentBlob.setY_avg(roundTheValue((float)theCurrentBlob.getY_avg()));
                        blobList.add(theCurrentBlob);
                    }

                }
        Blob[] blobs = blobList.toArray(new Blob[0]);
        return blobs;
    }
    private Picture thePic;
    private Blob theCurrentBlob;
    private void recursive (int i, int j, Picture pic) {
        if (pic.height() == j || pic.width() == i || i == -1 || j == -1)
            return;



        if (pic.get(i, j).equals(Color.black) || pic.get(i, j).equals(Color.yellow)) // when using == exception comes
        {
            return;
        }

        pic.set(i,j,Color.yellow);
        theCurrentBlob.add(i, j);

        recursive(i+1,j,pic);
        recursive(i,j+1,pic);
        recursive(i-1,j,pic);
        recursive(i,j-1,pic);

    }

    private double roundTheValue(double a) {
        double b = a - ((int) a);
        String s = b + "";
        if (s.length() > 6) {
            if (s.charAt(6) >= '5') {
                //if (s.charAt(5) == '9'){
                    s = (b + 0.0001) + "";
                    s = s.substring(0, 6);
                //}
//                else{
//                    String pNum = (char) (((int) s.charAt(5)) + 1) + "";
//                    s = s.substring(0, 5);
//                    s += pNum;
//                }
            }
            else {
                s = s.substring(0, 6);
            }
        }
        a = Double.parseDouble(s) + (int) a;
        return a;
    }
    public static void main(String[] args){
        int minPix = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        String picAddress = args[2];

        Picture picture = new Picture(picAddress);

        BeadFinder beadFinder = new BeadFinder(picture, tau);

        Blob[] beads = beadFinder.getBeads(minPix);

        for (int i = 0; i < beads.length; i++)
            System.out.println(beads[i].toString());

    }
}

