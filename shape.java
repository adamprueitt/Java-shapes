import java.lang.Math;
import java.util.Random;

/**********************************/ 
public interface Shape { 
	public double getArea(); 
	public double getPerimeter(); 
} 
/**********************************/ 

//custom Exception class for errors during Square and Circle construction
class ShapeConstructionException extends Exception{
	public ShapeConstructionException() {}
	public ShapeConstructionException(String message){
		super(message);
	}
}

class Triangle implements Shape{
//I'm not going to do all the math for calculating the base and height from sides, or sides and angles since that's not the point.
//similarly I am not going to check if the height and base work for the given sides
	private static double sideA, sideB, sideC;
	private static double width, height;
	
	public Triangle(double givenA, double givenB, double givenC, double givenWidth, double givenHeight){
		sideA = givenA;
		sideB = givenB;
		sideC = givenC;
		width = givenWidth;
		height = givenHeight;
	}
	
	//Get methods for calculation helpers
	public double getWidth(){
		return width;
	}
	public double getHeight(){
		return height;
	}
	
//spot for get methods for each of the defining characteristics, as well as general useful operations such as getShortestSide, getLargestAngle, isRight, isScalene, isEquilateral
	
	//inherited get methods for calculated characteristics
	public double getArea(){
		return width*height/2;
	}
	public double getPerimeter(){
		return sideA+sideB+sideC;
	}
}

class Rectangle extends Square{
	private static double length, width; //defining characteristics of a rectangle
	
	//basic constructor asks for length and width, in that order
	public Rectangle(double givenLength){
		super(givenLength);
		width = givenLength;
	}
	
	public Rectangle(double givenLength, double givenWidth){
		super(givenLength);
		width = givenWidth;
	}
	
	//get methods for characteristics
	public double getWidth(){
		return width;
	}
	public double getLength(){
		return length;
	}
	
	//inherited get methods for calculated characteristics
	public double getArea(){
		return width*length;
	}
	public double getPerimeter(){
		return (width+length)*2;
	}
}

class Square implements Shape{
	private static double length;
	
	//construct square given one side
	public Square(double given){
		length = given;
	}
	
	public double getLength() {
		return length;
	}
	
	public double getArea(){
		return Math.pow(length, 2);
	}
	public double getPerimeter(){
		return length*4;
	}
}

class Ellipse implements Shape{
	private static double axisMajor, axisMinor, focalLength; //defining characteristics of an ellipse
	private static double ramanujanH; //a calculation helper for perimeter, determined in construction method
	
	public Ellipse(double givenMajor, double givenMinor){
		axisMajor = givenMajor;
		axisMinor = givenMinor;
		focalLength = Math.sqrt(Math.pow(axisMajor/2, 2)+Math.pow(axisMinor/2, 2));
		ramanujanH = Math.pow((axisMajor/2)-(axisMinor/2), 2)/Math.pow((axisMajor/2)+(axisMinor/2), 2);
	}
	
	//get methods for characteristics
	public double getMajorAxis(){
		return axisMajor;
	}
	public double getMinorAxis(){
		return axisMinor;
	}
	public double getSemiMajorAxis(){
		return axisMajor/2;
	}
	public double getSemiMinorAxis(){
		return axisMinor/2;
	}
	public double getFocalLength(){
		return focalLength;
	}
//spot for methods that state if the ellipse is longer on vertically or horizontally, using isVertical
	
	//inherited get methods for calculated characteristics
	public double getArea(){
		return Math.PI*axisMajor*axisMinor/4;
	}
	//getPerimeter uses Ramanujan's slightly more complicated formula
	public double getPerimeter(){
		return Math.PI*(axisMajor/2+axisMinor/2)*(1+(ramanujanH*3)/(10+Math.sqrt(4-3*ramanujanH)));
	}
	//non-inherited extra get for people who feel like nitpicking semantics
	public double getCircumference(){
		return this.getPerimeter();
	}
}

class Circle implements Shape{ //would extend Ellipse if they had anything remotely in common
	private static double radius; //defining characteristic of a circle
	
	public Circle(double givenRadius){
		radius = givenRadius;
	}
	
	//get method(s) for radius
	public double getRadius(){
		return radius;
	}
	public double getDiameter(){
		return radius*2;
	}
	
	//inherited get methods for calculated characteristics
	public double getArea(){
		return Math.PI*radius*radius; /* check if Math.pow(radius, 2) is more efficient */
	}
	public double getPerimeter(){
		return Math.PI*radius*2;
	}
	//non-inherited extra get for people who feel like nitpicking semantics
	public double getCircumference(){
		return this.getPerimeter();
	}
}

/**********
 TEST CLASS
 **********/
class Demo{
	//main function creates a randomly generated object of each class and prints it and its area and perimeter
	//measurements are only whole numbers, hopefully to make checking the answers easier
	//except the triangle, which is a 3, 4, 5 right triangle. For ease of calculation.
	public static void main(String[] args){
		Random rand = new Random();
		Triangle testTri = new Triangle(3.0,4.0,5.0,4.0,3.0);
		Rectangle testRect = new Rectangle((double)rand.nextInt(8)+1.0, (double)rand.nextInt(8)+1.0);
		Square testSqr = new Square((double)rand.nextInt(8)+1.0);
		Ellipse testEll = new Ellipse((double)rand.nextInt(4)+1.0, (double)rand.nextInt(4)+5.0);
		Circle testCirc = new Circle((double)rand.nextInt(8)+1.0);
		
		System.out.format("Triangle: height: %f width: %f area: %f perimiter: %f\n", testTri.getHeight(), testTri.getWidth(), testTri.getArea(), testTri.getPerimeter());
		System.out.format("Rectangle: length: %f width: %f area: % perimiter: %ff\n", testRect.getLength(), testRect.getWidth(), testRect.getArea(), testRect.getPerimeter());
		System.out.format("Square: height: %f area: %f perimiter: %f\n", testSqr.getLength(), testSqr.getArea(), testSqr.getPerimeter());
		System.out.format("Ellipse: major axis: %f minor axis: %f area: %f perimiter: %f\n", testEll.getMajorAxis(), testEll.getMinorAxis(), testEll.getArea(), testEll.getPerimeter());
		System.out.format("Circle: radius: %f area: %f perimiter: %f\n", testCirc.getRadius(), testCirc.getArea(), testCirc.getPerimeter());
	}
}
