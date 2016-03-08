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


    //getters for the two parts of the complex number
    public double getReal(){
        return realPart;
    }
    public double getImaginary(){
        return imaginaryPart;
    }


    //round the two parts of the Complex number to 4dp, then returns a string value in the form a+bi
    public String getStringValue(){
        if(imaginaryPart<0){
            return (toFourDP(realPart)+" - "+ toFourDP(Math.abs(imaginaryPart))+"i");
        }
        else{
            return (toFourDP(realPart)+" + "+ toFourDP(Math.abs(imaginaryPart))+"i");
        }
    }
    //same as above, just without the rounding
    public String getFullStringValue(){
        if(imaginaryPart<0){
            return (realPart+" - "+ Math.abs(imaginaryPart)+"i");
        }
        else{
            return (realPart+" + "+ Math.abs(imaginaryPart)+"i");
        }
    }

    //checks whether a complex equals another complex, based on the values within, not location in memory
    public boolean equals(Complex c){
        return ((c.getImaginary()==getImaginary())&&(c.getReal()==getReal()));
    }

    public Complex getSquare(){
        //returns the square of the number and creates a new complex for it
        double squaredReal = realPart*realPart - imaginaryPart*imaginaryPart;
        double squaredImaginary = 2*realPart*imaginaryPart;
        return new Complex(squaredReal,squaredImaginary);
    }
    // returns the sum of the squares of the parts, as m^2=r^2+i^;
    public double magnitudeSquared(){
        return realPart*realPart +imaginaryPart*imaginaryPart;
    }


    public Complex add(Complex d){
        //adds the imaginary and real parts of the two complex numbers, then returns a new complex number with the result
        double sumReal = d.getReal()+getReal();
        double sumImaginary = d.getImaginary()+getImaginary();
        return new Complex(sumReal, sumImaginary);

    }


    //static method that returns a string that has rounded a inputted double to 4dp, allowing the doubles in the program to appear a reasonable length
    public static String toFourDP(double tooLong){
        DecimalFormat d = new DecimalFormat("#0.0000");
        return d.format(tooLong);
    }
}
