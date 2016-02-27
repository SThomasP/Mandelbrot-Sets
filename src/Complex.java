/**
 * Created by Steffan on 27/02/2016.
 */
public class Complex {
    //Class for storing/getting details of Complex numbers
    protected double realPart;
    protected double imaginaryPart;

    public Complex(double realPart, double imaginaryPart){
        this.imaginaryPart = imaginaryPart;
        this.realPart = realPart;
    }

    public Double getReal(){
        return realPart;
    }
    public Double getImaginary(){
        return imaginaryPart;
    }
}
