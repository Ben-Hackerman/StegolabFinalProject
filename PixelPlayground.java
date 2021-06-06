import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class PixelPlayground
{
    public static Picture zeroBlue(Picture p)
    {
        Picture newPicture = new Picture(p);
        Pixel[][] pix = newPicture.getPixels2D();
        for(int r = 0; r < pix.length; r++)
        {
            for(int c = 0; c < pix[0].length; c++)
            pix[r][c].setBlue(0);
        }
        return newPicture;
    }

    public static Picture onlyBlue(Picture p)
    {
        Picture newPicture = new Picture(p);
        Pixel[][] pix = newPicture.getPixels2D();
        for(int r = 0; r < pix.length; r++)
        {
            for(int c = 0; c < pix[0].length; c++)
            {
                pix[r][c].setRed(0);
                pix[r][c].setGreen(0);
            }
        }
        return newPicture;
    }

    public static Picture greyscale(Picture p)
    {
        Integer maxVal;
        Picture newPicture = new Picture(p);
        Pixel[][] pix = newPicture.getPixels2D();
        for(int r = 0; r < pix.length; r++)
        {
            for(int c = 0; c < pix[0].length; c++)
            {
                maxVal = 0;
                if (pix[r][c].getRed() > maxVal) {maxVal = pix[r][c].getRed();}
                if (pix[r][c].getGreen() > maxVal) {maxVal = pix[r][c].getGreen();}
                if (pix[r][c].getBlue() > maxVal) {maxVal = pix[r][c].getBlue();}
                pix[r][c].setRed(maxVal);
                pix[r][c].setGreen(maxVal);
                pix[r][c].setBlue(maxVal);
            }
        }
        return newPicture;
    }

    public static boolean isSame(Picture a, Picture b)
    {
        Pixel[][] pixA = a.getPixels2D();
        Pixel[][] pixB = b.getPixels2D();

        if (pixA.length != pixB.length) {return false;}

        for(int r = 0; r < pixA.length; r++)
        {
            for(int c = 0; c < pixA[0].length; c++)
            {
                if (pixA[r][c].getRed() != pixB[r][c].getRed()) {return false;}
                if (pixA[r][c].getGreen() != pixB[r][c].getGreen()) {return false;}
                if (pixA[r][c].getBlue() != pixB[r][c].getBlue()) {return false;}
            }
        }
        return true;
    }

    public static ArrayList<Point> findDifferences(Picture a, Picture b)
    {
        ArrayList<Point> changedPix = new ArrayList<Point>();

        Pixel[][] pixA = a.getPixels2D();
        Pixel[][] pixB = b.getPixels2D();

        for(int r = 0; r < pixA.length; r++)
        {
            for(int c = 0; c < pixA[0].length; c++)
            {
                Point p = new Point(r, c);
                if (pixA[r][c].getRed() != pixB[r][c].getRed()) {changedPix.add(p);}
                else if (pixA[r][c].getGreen() != pixB[r][c].getGreen()) {changedPix.add(p);}
                else if (pixA[r][c].getBlue() != pixB[r][c].getBlue()) {changedPix.add(p);}
            }
        }
        return changedPix;
    }

    public static Picture showDifferent(Picture a, ArrayList<Point> changedPix)
    {
        Picture newPicture = new Picture(a);
        Pixel[][] pix = newPicture.getPixels2D();
        int r;
        int c;

        for (int i = 0; i < changedPix.size(); i++)
        {
            r = changedPix.get(i).x;
            c = changedPix.get(i).y;
            pix[r][c].setRed(255);
            pix[r][c].setGreen(255);
            pix[r][c].setBlue(255);
        }
        return newPicture;
    }

    public static Picture mirrorHorizontal(Picture a)
    {
        Picture newPicture = new Picture(a);
        Pixel[][] pix = newPicture.getPixels2D();
        Picture oldPicture = new Picture(a);
        Pixel[][] pixOld = oldPicture.getPixels2D();

        for(int r = 0; r < (pix.length); r++)
        {
            for(int c = 0; c < (pix[0].length/2); c++)
            {
                pix[r][pix[0].length-c-1].setRed(pixOld[r][c].getRed());
                pix[r][pix[0].length-c-1].setGreen(pixOld[r][c].getGreen());
                pix[r][pix[0].length-c-1].setBlue(pixOld[r][c].getBlue());
            }
        }
        return newPicture;
    }

    public static Picture mirrorVertical(Picture a)
    {
        Picture newPicture = new Picture(a);
        Pixel[][] pix = newPicture.getPixels2D();
        Picture oldPicture = new Picture(a);
        Pixel[][] pixOld = oldPicture.getPixels2D();

        for(int r = 0; r < pix.length; r++)
        {
            for(int c = 0; c < pix[0].length; c++)
            {
                pix[pix.length-r-1][c].setRed(pixOld[r][c].getRed());
                pix[pix.length-r-1][c].setGreen(pixOld[r][c].getGreen());
                pix[pix.length-r-1][c].setBlue(pixOld[r][c].getBlue());
            }
        }
        return newPicture;
    }

    public static void clearLow(Pixel p) {
        int gRed = p.getRed();
        int gGreen = p.getGreen();
        int gBlue = p.getBlue();
 
        gRed = (gRed / 4) * 4;
        gGreen = (gGreen / 4) * 4;
        gBlue = (gBlue / 4) * 4;
 
        p.setRed(gRed);
        p.setGreen(gGreen);
        p.setBlue(gBlue);
    }
 
 
 
    public static Picture testClearLow(Picture p) {
 
        Picture newPicture = new Picture(p);
        Pixel[][] pix = newPicture.getPixels2D();
        for(int r=0; r<pix.length; r++) {
            for(int c=0; c<pix[0].length; c++){
                clearLow(pix[r][c]);
            }
        }
 
        return newPicture;
    }
    
 
 
 
    //sets the lower 2 bits
    public static void setLow (Pixel p, Color c) {
        int gRed = c.getRed();
        int gGreen = c.getGreen();
        int gBlue = c.getBlue();
 
        clearLow(p);
 
        gRed = (gRed / 64);
        gGreen = (gGreen / 64);
        gBlue = (gBlue / 64);
 
        p.setRed(p.getRed()+gRed);
        p.setGreen(p.getGreen()+gGreen);
        p.setBlue(p.getBlue()+gBlue);
    }
 
    public static Picture revealPicture(Picture hidden) { 
    Picture copy = new Picture(hidden); 
    Pixel[][] pixels = copy.getPixels2D();
    Pixel[][] source = hidden.getPixels2D(); 
    for (int r = 0; r < pixels.length; r++)
    { 
    for (int c = 0; c < pixels[0].length; c++)
    {     
    Color col = source[r][c].getColor();
    
    int red = col.getRed() %4 * 64;
    int green = col.getGreen() %4 * 64;
    int blue = col.getBlue() %4 * 64;
    
    pixels[r][c].setRed(red);
    pixels[r][c].setGreen(green);
    pixels[r][c].setBlue(blue);
    }
            }
    return copy; 
    }
    
    public static Picture testSetLow(Picture p, Color color) {
    
        Picture newPicture = new Picture(p);
        Pixel[][] pix = newPicture.getPixels2D();
        for(int r=0; r<pix.length; r++) {
            for(int c=0; c<pix[0].length; c++){
                setLow(pix[r][c], color);
            }
        }
    
        return newPicture;
    }

    public static Picture hidePicture(Picture source, Picture secret)
    {
        Picture sourcePic = new Picture(source);
        Pixel[][] pixSource = sourcePic.getPixels2D();
        Picture secretPic = new Picture(secret);
        Pixel[][] pixSecret = secretPic.getPixels2D();

        for(int r = 0; r < pixSecret.length; r++)
        {
            for(int c = 0; c < pixSecret[0].length; c++)
            {
                setLow(pixSource[r][c], pixSecret[r][c].getColor());
            }
        }

        return sourcePic;
    }

    //-----------------------
    // FINAL PROJECT SECTION
    //-----------------------

    //places a character at the given point. Replaces 8-bit red RGB value 
    public static void insertChar(Pixel p, char character)
    {
        //convert ascii char to it's integer value
        int charInt = (int) character;

        //append the binary numbers to red character for the ascii character
        p.setRed(charInt);
    }

    public static Picture hidePhrase(Picture source, String secret)
    {
        //declare int for parsing through secret phrase
        int len = 0;

        //get pixel array for source image
        Picture sourcePic = new Picture(source);
        Pixel[][] pixSource = sourcePic.getPixels2D();

        //parse through each pixel, adding chars
        for(int r = 0; r < pixSource.length; r++)
        {
            for(int c = 0; c < pixSource[0].length; c++)
            {
                //if the whole length of the string hasnt been parsed through
                if (len < secret.length()) 
                {
                    //insert character at given row, col
                    insertChar(pixSource[r][c], secret.charAt(len));
                    len++;
                }
                
            }
        }

        return sourcePic;
    }


    public static String revealPhrase(Picture source, Picture secret)
    {
        //create string to be appended
        String phrase = new String();

        //get pixels of images
        Pixel[][] pixSource = source.getPixels2D();
        Pixel[][] pixSecret = secret.getPixels2D();

        //for each pixel in the images
        for(int r = 0; r < pixSource.length; r++)
        {
            for(int c = 0; c < pixSource[0].length; c++)
            {
                //if the pixels in the source and secret aren't the same...
                if (pixSource[r][c].getRed() != pixSecret[r][c].getRed()) 
                {
                    //get char from the integer and append it to the string
                    phrase = phrase + (char) pixSecret[r][c].getRed();
                }
            }
        }
    	return phrase;
    }


    public static void main (String[] args)
    {
        //create beach pics
        Picture beachPic = new Picture("beach.jpg");
        Picture beachPicDup = new Picture("beach2.jpg");
        beachPic.explore();

        //secret phrase
        String secretPhrase = "Super secret phrase that I will hide in the image";

        //create beach pic with secret
        Picture beachPicSecret = hidePhrase(beachPicDup, secretPhrase);
        beachPicSecret.explore();

        //decode image from source
        String revealedPhrase = revealPhrase(beachPic, beachPicSecret);

        //print discovered phrase
        System.out.println("The Secret Phrase is: " + revealedPhrase);

    }
}