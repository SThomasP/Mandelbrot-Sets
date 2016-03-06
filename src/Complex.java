/**
 * Created by Steffan on 27/02/2016.
 */

import java.text.DecimalFormat;

public class Complex {
    //Class for storing/getting details of Complex numbers
    protected double realPart;
    protected double imaginaryPart;

    public Complex(double realPart, double imaginaryPart){
        this.imaginaryPart = imaginaryPart;
        this.realPart = realPart;
    }

    public double getReal(){
        return realPart;
    }
    public double getImaginary(){
        return imaginaryPart;
    }

    public String getStringValue(){
        if(imaginaryPart<0){
            return (toFourDP(realPart)+" - "+ toFourDP(Math.abs(imaginaryPart))+"i");
        }
        else{
            return (toFourDP(realPart)+" + "+ toFourDP(Math.abs(imaginaryPart))+"i");
        }
    }

    public String getFullStringValue(){
        if(imaginaryPart<0){
            return (realPart+" - "+ Math.abs(imaginaryPart)+"i");
        }
        else{
            return (realPart+" + "+ Math.abs(imaginaryPart)+"i");
        }
    }


    public boolean equals(Complex c){
        return ((c.getImaginary()==getImaginary())&&(c.getReal()==getReal()));
    }

    public Complex getSquare(){
        double squaredReal = realPart*realPart - imaginaryPart*imaginaryPart;
        double squaredImaginary = 2*realPart*imaginaryPart;
        return new Complex(squaredReal,squaredImaginary);
    }

    public double magnitudeSquared(){
        return realPart*realPart +imaginaryPart*imaginaryPart;
    }

    public Complex add(Complex d){
        //adds the imaginary and real parts of the two complex numbers, then returns a new complex number with the result
        double sumReal = d.getReal()+getReal();
        double sumImaginary = d.getImaginary()+getImaginary();
        return new Complex(sumReal, sumImaginary);

    }

    public static String toFourDP(double tooLong){
        DecimalFormat d = new DecimalFormat("#0.0000");
        return d.format(tooLong);
    }
}
