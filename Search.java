import java.util.ArrayList;
import java.util.List;


class Node {
	private State data;
	private Node parent;

	private List<Node> children;

	public Node () {
		parent = null;
		children = new ArrayList<Node>();
	}

	public boolean equals(Object node){
		return(((Node)node).getData().getJug1() == getData().getJug1() && ((Node)node).getData().getJug2() == getData().getJug2());
	}


	public State getData() {
		return data;
	}
	public void setData(State data) {
		this.data = data;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void addChild(Node child) {
		children.add(child);
		child.setParent(this);
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}

}

class State {

	private int jug1;
	private int jug2;
	public int getJug1() {
		return jug1;
	}
	public void setJug1(int jug1) {
		this.jug1 = jug1;
	}
	public int getJug2() {
		return jug2;
	}
	public void setJug2(int jug2) {
		this.jug2 = jug2;
	}	

}


public class Search {
	static int jug1;
	static int jug2;
	static int goal_amount;
	
	static boolean isSolvable;
	
	static List<Node> openList = new ArrayList<Node>();
	static List<Node> closedList = new ArrayList<Node>();

	public static void main(String [] args) {
		jug1 = Integer.valueOf(args[0]);
		jug2 = Integer.valueOf(args[1]);
		goal_amount = Integer.valueOf(args[2]);	

		if(jug1 < jug2){
			isSolvable = false;
			System.out.println("Unsolvable");
		} else if (goal_amount > jug1) {
			isSolvable = false;
			System.out.println("Unsolvable");
		} else{
			
			Node root = new Node();
			State rootState = new State();
			rootState.setJug1(0);
			rootState.setJug2(0);
			root.setParent(null);
			root.setData(rootState);

			printTraversal(root, "BFS");

			printTraversal(root, "DFS");
		}
	}


	public static List<Node> getChildArray(Node currNode){
		List<Node> childArray = new ArrayList<Node>();

		int currNodeJug1 = currNode.getData().getJug1();
		int currNodeJug2 = currNode.getData().getJug2();

		if(currNodeJug1 < jug1){
			
			Node newNode = new Node();
			State newState = new State();
			newNode.setData(newState);
			newNode.setParent(currNode);
			newState.setJug1(jug1);
			newState.setJug2(currNodeJug2);
			if(!checkForSameState(newNode, childArray) && !currNode.equals(newNode)) {
				childArray.add(newNode);
			}
		}
		if(currNodeJug2 < jug2){
			
			Node newNode = new Node();
			State newState = new State();
			newNode.setData(newState);
			newNode.setParent(currNode);
			newState.setJug1(currNodeJug1);
			newState.setJug2(jug2);
			newNode.setParent(currNode);
			if(!checkForSameState(newNode, childArray) && !currNode.equals(newNode)) {
				childArray.add(newNode);
			}
		}
		if(currNodeJug1 != 0 ){

			Node newNode = new Node();
			State newState = new State();
			newNode.setData(newState);
			newNode.setParent(currNode);
			newState.setJug1(0);
			newState.setJug2(currNodeJug2);
			newNode.setParent(currNode);
			if(!checkForSameState(newNode, childArray) && !currNode.equals(newNode)) {
				childArray.add(newNode);
			}
		}
		if(currNodeJug2 != 0){

			Node newNode = new Node();
			State newState = new State();
			newNode.setData(newState);
			newNode.setParent(currNode);
			newState.setJug1(currNodeJug1);
			newState.setJug2(0);
			newNode.setParent(currNode);

			if(!checkForSameState(newNode, childArray) && !currNode.equals(newNode)) {
				childArray.add(newNode);
			}
		}
		if(currNodeJug1 < jug1){
			if (currNodeJug1 + currNodeJug2 <= jug1){

				Node newNode = new Node();
				State newState = new State();
				newNode.setData(newState);
				newNode.setParent(currNode);
				newState.setJug1(currNodeJug1 + currNodeJug2);
				newState.setJug2(0);
				newNode.setParent(currNode);

				if(!checkForSameState(newNode, childArray) && !currNode.equals(newNode)) {
					childArray.add(newNode);
				}
			} else {

				Node newNode = new Node();
				State newState = new State();
				newNode.setData(newState);
				newNode.setParent(currNode);
				newState.setJug1(jug1);
				newState.setJug2(currNodeJug1 + currNodeJug2 - jug1);
				newNode.setParent(currNode);

				if(!checkForSameState(newNode, childArray) && !currNode.equals(newNode)) {
					childArray.add(newNode);
				}
			}
		}
		if(currNodeJug2 < jug2){			
			if (currNodeJug1 + currNodeJug2 <= jug2){

				Node newNode = new Node();
				State newState = new State();
				newNode.setData(newState);
				newNode.setParent(currNode);
				newState.setJug1(0);
				newState.setJug2(currNodeJug1 + currNodeJug2);
				newNode.setParent(currNode);

				if(!checkForSameState(newNode, childArray) && !currNode.equals(newNode)) {
					childArray.add(newNode);
				}
			} else {

				Node newNode = new Node();
				State newState = new State();
				newNode.setData(newState);
				newNode.setParent(currNode);
				newState.setJug1(currNodeJug1 + currNodeJug2 - jug2);
				newState.setJug2(jug2);
				newNode.setParent(currNode);

				if(!checkForSameState(newNode, childArray) && !currNode.equals(newNode)) {
					childArray.add(newNode);
				}
			}
		}
		return childArray;
	}


	public static boolean checkForSameState (Node node1, List<Node> nodeList) {
		for (int hh = 0; hh<nodeList.size(); hh++) {
			if((node1.getData().getJug1() == nodeList.get(hh).getData().getJug1()) && 
					(node1.getData().getJug2() == nodeList.get(hh).getData().getJug2())) {
				return true;
			}
		}
		return false;
	}


	public static void printTraversal(Node root, String traversalType){
		boolean isSolvable = false;
		openList.clear();
		closedList.clear();
		if(traversalType.equalsIgnoreCase("BFS")){
			System.out.println("BFS");
		} else {
			System.out.println("DFS");
		}

		openList.add(root);
		int iterationNumber = 0;
		System.out.println("Iteration:");
		while(!openList.isEmpty()) {

			String openStr = "";
			String closedStr = "";
			Node currNode = new Node();
			if(traversalType.equalsIgnoreCase("BFS")) {
				currNode = openList.get(0);
			} else {
				currNode = openList.get(openList.size() - 1);
			}

			while(checkForSameState(currNode, closedList)){
				openList.remove(currNode);                     
				if(openList.isEmpty()) {
					break;
				} else if(traversalType == "BFS"){
					currNode = openList.get(0);
				} else {
					currNode = openList.get(openList.size() - 1);
				}
			}
			if(openList.isEmpty()){
				break;
			}
			if(currNode.getData().getJug1() == goal_amount && currNode.getData().getJug2() == 0) {
				System.out.println("Result:");
				List<Node> createPathList = new ArrayList<Node>();
				createPathList.add(currNode);
				while(currNode.getParent() != null){
					createPathList.add(currNode.getParent());
					currNode = currNode.getParent();
				}
				String pathStr = "";
				for (int i=createPathList.size() - 1; i >= 0; i--) {
					pathStr = pathStr + "(" + createPathList.get(i).getData().getJug1()
							+ ", " + createPathList.get(i).getData().getJug2() + ") ";
				}


				System.out.println(pathStr);
				isSolvable = true;
				break;
			} else {
				List<Node> succList = getChildArray(currNode);
				for(int i=0; i < succList.size(); i++ ){

					Node newChild = succList.get(i);
					openStr = openStr + "(" + newChild.getData().getJug1() + ", " + newChild.getData().getJug2() + ") ";
					openList.add(newChild);
				}
			}

			openList.remove(currNode);
			closedList.add(currNode);

			for(int i=0; i < closedList.size(); i++ ){
				Node closedNode = closedList.get(i);
				closedStr = closedStr + "(" + closedNode.getData().getJug1() + ", " + closedNode.getData().getJug2() + ") ";
			}
			System.out.println(iterationNumber);
			System.out.println(closedStr);
			System.out.println(openStr);

			iterationNumber++;
		}
		if(!isSolvable) {
			System.out.println("Unsolvable");
		}
	}
}
