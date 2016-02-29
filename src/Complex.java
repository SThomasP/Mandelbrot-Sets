/**
 * Created by Steffan on 27/02/2016.
 */

import java.math.*;
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
}
