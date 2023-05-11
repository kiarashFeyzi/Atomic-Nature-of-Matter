import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Avogadro {


    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        double a = 0,
                sum = 0;
        int n = 0;
        while (scn.hasNextDouble()) {
            double b = scn.nextDouble();

            a = b * ( 0.175*1E-6);
                sum += (a * a);
                n++;

        }
        double variance = sum / (2 * n),

         t = 0.5 ,

         D = variance / (2*t) ,

         viscos = 9.135 * 1E-4 ,

         T = 297 ,

         ro = 0.5 * 1E-6 ,

         k = (6 * Math.PI * viscos * ro * D ) / T ,

         R = 8.31446 ,

         Na = R / k ;


        System Std =null;
        Std.out.println("Boltzmann = "+String.format("%.4e",k));
        Std.out.println("Avogadro = "+String.format("%.4e",Na));
    }

}


