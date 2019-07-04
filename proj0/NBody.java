import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class NBody {
  // return radius from a text file
  public static double readRadius(String filename){
    double radius = 0.0;

    try {
      File file = new File(filename);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String line;

      // read first line
      bufferedReader.readLine();

      // read the second line
      line = bufferedReader.readLine();

      radius = Double.parseDouble(line);
    }catch (Exception ex){
      ex.printStackTrace();
    }

    return radius;
  }

  // read planets from a text file
  public static Planet[] readPlanets(String filename){
    Planet[] planets = null;

    try {
      File file = new File(filename);
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(fileReader);


      // read first line
      int numPlanets = Integer.parseInt(bufferedReader.readLine());

      // allocate space for planets array
      planets = new Planet[numPlanets];

      // read second line
      bufferedReader.readLine();

      for(int i = 0; i < numPlanets; i++){
        // trim() ensure there is no white space at the start and end
        String line = bufferedReader.readLine().trim();
        // regex '\\s+' split string by white spaces of variable lengths
        String[] buffer = line.split("\\s+");

        // parse x and y coordinates
        double x = Double.parseDouble(buffer[0]);
        double y = Double.parseDouble(buffer[1]);

        // parse x and y velocity
        double xVelo = Double.parseDouble(buffer[2]);
        double yVelo = Double.parseDouble(buffer[3]);

        // parse mass
        double mass = Double.parseDouble(buffer[4]);

        // parse image name
        String image = buffer[5];

        planets[i] = new Planet(x, y, xVelo, yVelo, mass, image);
      }

    }catch (Exception ex){
      ex.printStackTrace();
    }

    return planets;
  }

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];

    Planet[] planets = readPlanets(filename);
    double radius = readRadius(filename);

    int numPlanets = planets.length;

    // set the draw scale using universe radius
    StdDraw.setScale(-radius, radius);

    // prevent flickering
    StdDraw.enableDoubleBuffering();

    // draw the background image
    StdDraw.picture(0, 0, "./images/starfield.jpg");

    // draw all planets
    for(Planet planet: planets){
      planet.draw();
    }
    StdDraw.show();

    double time = 0.0;
    while(time < T){
      double[] xForces = new double[numPlanets];
      double[] yForces = new double[numPlanets];

      // clear screen and show background
      StdDraw.clear();
      StdDraw.picture(0, 0, "./images/starfield.jpg");

      for(int i = 0; i < numPlanets; i++){
        Planet currentPlanet = planets[i];

        xForces[i] = currentPlanet.calcNetForceExertedByX(planets);
        yForces[i] = currentPlanet.calcNetForceExertedByY(planets);

        currentPlanet.update(time, xForces[i], yForces[i]);
        currentPlanet.draw();
      }

      // show the buffer
      StdDraw.show();

      // pause for 10 miliseconds
      StdDraw.pause(50);

      // update time
      time += dt;
    }

    // print the final state of the universe
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
              planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
              planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
    }
  }
}
