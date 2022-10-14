// Daijon Roberts, 10/09/2022, Assignment 4

public class Mario {

 int x;
 int y;
 int h = 95;
 int w = 60;
 double vertVelocity;

 @Override
 public String toString() {
  return "Mario (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
 }
 
 public void update() {
  vertVelocity += 1.2;
  y += vertVelocity;

  if (y > 500 - 125) {
   vertVelocity = 0.0;
   y = 500 - 125;
  }
 }

}

