// Daijon Roberts, 10/09/2022, Assignment 4

public class Mario {

 int x = 20;
 int y = 0;
 int h = 95;
 int w = 60;
 int prevx, prevy;
 double vertVelocity;
 // at first mario should not be able to jump 
 int counter = 31;
 boolean canJump = false;

 @Override
 public String toString() {
  return "Mario (x,y) = (" + x + ", " + y + "), width = " + w + ", height = " + h;
 }

 public void prevCoordinates(){
    prevx = x;
    prevy = y;
 }
 
 public void getOutOfPipe(Pipe pipe){
    // mario collides from left
    if (prevx + w <= pipe.x)
        x = pipe.x - w;

    // mario collides from right
    if (prevx >= pipe.x + pipe.w)
        x = pipe.x + pipe.w;

    // collision with top pipe
    if (prevy + h <= pipe.y){
        y = pipe.y - h;
        vertVelocity = 0;
        canJump = true;
        counter = 0;
    }

    // if collision with bottom pipe
    if (prevy >= pipe.y + pipe.h)
        y = pipe.y + pipe.h;

    
 }

 public void update() {
  vertVelocity += 1.2;
  y += vertVelocity;

  // while 
  if (y < 500 - 125){
    //canJump = false;
    counter += 1;
  }
  
  // when user reaches bottom make velociy zero 
  if (y > 500 - 125) {
    canJump = true;
   vertVelocity = 0.0;
   y = 500 - 125;
   counter = 0;
 }
 }

}

