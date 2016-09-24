package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {


    /**
     * You can change your factors here. The current factors are just for the demo
     */
    private static final double LDPI_FACTOR = 0.375;
    private static final double MDPI_FACTOR = 0.5;
    private static final double HDPI_FACTOR = 0.75;
    private static final double XHDPI_FACTOR = 1.0;
    private static final double XXHDPI_FACTOR = 1.5;
    private static final double XXXHDPI_FACTOR = 2.0;

    private static double factor;

    public static void main(String[] args) throws IOException {


        Scanner in = new Scanner(System.in);
        System.out.println("Enter the location of the project/module");
        String projectPath = in.nextLine();

        System.out.println("Which of the following dimension file do you want?\n1. ldpi \n2. mdpi \n3. hdpi \n4. xhdpi \n5. xxhdpi \n6. xxxhdpi");

        int dimenType = in.nextInt();

        switch (dimenType) {
            case 1: factor = LDPI_FACTOR;
                break;
            case 2: factor = MDPI_FACTOR;
                break;
            case 3: factor = HDPI_FACTOR;
                break;
            case 4: factor = XHDPI_FACTOR;
                break;
            case 5: factor = XXHDPI_FACTOR;
                break;
            case 6: factor = XXXHDPI_FACTOR;
                break;
            default:
                factor = 1.0;
        }

        //full path = "/home/akeshwar/android-sat-bothIncluded-notintegrated/code/tpr-5-5-9/princetonReview/src/main/res/values/dimens.xml"
        //location of the project or module = "/home/akeshwar/android-sat-bothIncluded-notintegrated/code/tpr-5-5-9/princetonReview/"


        /**
         * In case there is some I/O exception with the file, you can directly copy-paste the full path to the file here:
         */
        String fullPath = projectPath + "/src/main/res/values/dimens.xml";

        FileInputStream fstream = new FileInputStream(fullPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

        while ((strLine = br.readLine()) != null)   {
            modifyLine(strLine);
        }
        br.close();

    }

    private static void modifyLine(String line) {

        /**
         * Well, this is how I'm detecting if the line has some dimension value or not.
         */
        if(line.contains("p</")) {
            int endIndex = line.indexOf("p</");

            //since indexOf returns the first instance of the occurring string. And, the actual dimension would follow after the first ">" in the screen
            int begIndex = line.indexOf(">");

            String prefix = line.substring(0, begIndex+1);
            String root = line.substring(begIndex+1, endIndex-1);
            String suffix = line.substring(endIndex-1,line.length());


            /**
             * Now, we have the root. We can use it to create different dimensions. Root is simply the dimension number.
             */

            double dimens = Double.parseDouble(root);
            dimens = dimens*factor*1000;
            dimens = (double)((int)dimens);
            dimens = dimens/1000;
            root = dimens + "";

            System.out.println(prefix + " " +  root + " " + suffix );

        }

        System.out.println(line);
    }
}
