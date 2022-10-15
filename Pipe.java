// Daijon Roberts, 09/29/2022, Assignment 3

class Pipe
{
  int x;
  int y;
  int h = 400;
  int w = 55;

  // Constructor
  Pipe(int x, int y)
  {
    this.x = x;
    this.y = y;
  }

  // Unmarshals a Pipe from a JSON DOM
  Pipe(Json ob)
  {
    this.x = (int)ob.getLong("pipeX");
    this.y = (int)ob.getLong("pipeY");
  }
  
  // Marshals this object into a JSON DOM
  Json marshal() {
    Json ob = Json.newObject();
    ob.add("pipeX", x);
    ob.add("pipeY", y);
    return ob;
  }

  // Returns true if the given point is within the pipe
  public boolean removePipe(int x, int y)
  {
    if ((x < this.x || x > this.x + 55) || (y < this.y || y > this.y + 400))
      return false;
    else
      return true;
    
  }

  @Override
  public String toString() {
    return "Pipe (x,y) = (" + this.x + ", " + this.y + "), width = " + this.w + ", height = " + this.h;
  }

}
