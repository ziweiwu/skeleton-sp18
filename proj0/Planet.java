import java.lang.Math.*;
import java.util.List;

public class Planet {
  public double xxPos; // its current x position
  public double yyPos; // its current y position
  public double xxVel; // its current velocity in the x direction
  public double yyVel; // its current velocity in the y direction
  public double mass;  // its mass
  public String imgFileName; // name of the file that corresponds to image that depicts the planet e.g. jupiter.gif

  // gravity constant (note: java supports scientific notation)
  private static final double G = 6.674e-11;

  // all args constructor
  public Planet(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
    this.xxPos = xxPos;
    this.yyPos = yyPos;
    this.xxVel = xxVel;
    this.yyVel = yyVel;
    this.mass = mass;
    this.imgFileName = imgFileName;
  }

  // copy constructor
  public Planet(Planet p) {
    this.xxPos = p.xxPos;
    this.yyPos = p.yyPos;
    this.xxVel = p.xxVel;
    this.yyVel = p.yyVel;
    this.mass = p.mass;
    this.imgFileName = p.imgFileName;
  }

  // calculate distance between current planet and another planet
  private double calcDistance(Planet anotherPlanet){
    double distanceX = anotherPlanet.xxPos - this.xxPos;
    double distanceY = anotherPlanet.yyPos - this.yyPos;
    double distance = Math.sqrt(Math.pow(distanceX, 2.0) + Math.pow(distanceY, 2.0));
    return distance;
  }

  // calculate the force exerted on this planet by another planet
  private double calcForceExertedBy(Planet anotherPlanet) {
    double r = calcDistance(anotherPlanet);
    double F = (G * anotherPlanet.mass * mass) / Math.pow(r, 2.0);
    return F;
  }

  // compute the force exerted on x direction
  private double calcForceExertedByX(Planet anotherPlanet){
    double F = calcForceExertedBy(anotherPlanet);
    double dx = anotherPlanet.xxPos - this.xxPos;
    double r = calcDistance(anotherPlanet);
    double Fx = F*dx/r;
    return Fx;
  }

  // compute the force exerted on y direction
  private double calcForceExertedByY(Planet anotherPlanet){
    double F = calcForceExertedBy(anotherPlanet);
    double dy = anotherPlanet.yyPos - this.yyPos;
    double r = calcDistance(anotherPlanet);
    double Fy = F*dy/r;
    return Fy;
  }

  // compute the net force exerted on x direction
  public double calcNetForceExertedByX(Planet[] allPlanets) {
    double FxNet = 0.0;
    for(Planet planet: allPlanets){
      if(planet.equals(this)){
        continue;
      }
     FxNet += calcForceExertedByX(planet);
    }
    return FxNet;
  }

  // compute the net force exerted on y direction
  public double calcNetForceExertedByY(Planet[] allPlanets) {
    double FyNet = 0.0;
    for(Planet planet: allPlanets){
      if(planet.equals(this)){
        continue;
      }
      FyNet += calcForceExertedByY(planet);
    }
    return FyNet;
  }

  // update the velocity and position based on net x and y force
  public void update(double t, double fX, double fY){
    // compute acceleration on x and y direction
    double aX = fX/this.mass;
    double aY = fY/this.mass;

    // update velocity on x and y direction
    this.xxVel = this.xxVel + t * aX;
    this.yyVel = this.yyVel + t * aY;

    // update the position
    this.xxPos = this.xxPos + t * this.xxVel;
    this.yyPos = this.yyPos + t * this.yyVel;
  }

  // draw planet
  public void draw(){
    StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
  }

  // getters and setters
  public double getXxPos() {
    return xxPos;
  }

  public void setXxPos(double xxPos) {
    this.xxPos = xxPos;
  }

  public double getYyPos() {
    return yyPos;
  }

  public void setYyPos(double yyPos) {
    this.yyPos = yyPos;
  }

  public double getXxVel() {
    return xxVel;
  }

  public void setXxVel(double xxVel) {
    this.xxVel = xxVel;
  }

  public double getYyVel() {
    return yyVel;
  }

  public void setYyVel(double yyVel) {
    this.yyVel = yyVel;
  }

  public double getMass() {
    return mass;
  }

  public void setMass(double mass) {
    this.mass = mass;
  }

  public String getImgFileName() {
    return imgFileName;
  }

  public void setImgFileName(String imgFileName) {
    this.imgFileName = imgFileName;
  }
}
