// Daijon Roberts, 09/29/2022, Assignment 3

import java.util.ArrayList;

class Model {
	int x;
	int y;
	ArrayList<Pipe> pipes;
	Mario mario;

	//	Constructor
	Model() {
		pipes = new ArrayList<Pipe>();
	}

	// Constructor with parameters
	Model(Mario mario) {
		pipes = new ArrayList<Pipe>();
		this.mario = mario;
	}
	
	//	Marshals a Model to a JSON DOM
	Json marshall(){
		Json ob = Json.newObject();
		ob.add("modelX", x);
		ob.add("modelY", y);
		Json tmpList = Json.newList();
		ob.add("pipes", tmpList);
		for(int i = 0; i < pipes.size(); i++)
						tmpList.add(pipes.get(i).marshal());
	 return ob;
	}

	// Unmarshals a Model from a JSON DOM
	Model(Json ob){
		this.x = (int)ob.getLong("modelX");
		this.y = (int)ob.getLong("modelY");
		this.pipes = new ArrayList<Pipe>();
		Json tmpList = ob.get("pipes");
		for(int i = 0; i < tmpList.size(); i++)
			pipes.add(new Pipe(tmpList.get(i)));
	}

	//	Load the model from a file
	public void load(String filename) {
		Json ob = Json.load(filename);
		Model model = new Model(ob);
		setModel(model);	
	}

	//	Set the model to the given model	
	public void setModel(Model m){
		this.x = m.x;
		this.y = m.y;
		this.pipes = m.pipes;
	}
	
	public void update() {
		mario.update();
	}

	//	Save the model to a file
	public void save (String filename) {
		Json ob = marshall();
		ob.save(filename);
	}

	//	Add or remove a pipe at the destination
	public void setDestination(int x, int y) {
		this.x = x;
		this.y = y;

		int i = 0;
		boolean found = false;
		
		// loop entire pipe array to find matching pipe
		while (i < pipes.size()) {
			//	if pipe is in that same spot remove pipe from array
			if (pipes.get(i).removePipe(x, y)) {
				found = true;
				pipes.remove(i);
			}
			i++;
		}

		// if no pipe is in that same spot add pipe to array
		if (found == false) {
			Pipe p = new Pipe(this.x, this.y);
			pipes.add(p);
		}
	}
}
