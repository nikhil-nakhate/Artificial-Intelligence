/* Name: 			Nikhil Sudhindra Nakhate
 * Wisc Username: 	nakhate
 * Class Section:	1
 * Homework #:		5
 * Submission date:	
 */



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


class Node {


	private int value;
	private Node parent;
	private String type;
	
	private ArrayList<Integer> xIndexArray;
	private ArrayList<Integer> oIndexArray;
	
	private ArrayList<Integer> StateArray;
	
	private String whoseTurn;
	
	
	private int alphaValue;
	private int betaValue;
	
	public ArrayList<Integer> getXIndexArray() {
		return xIndexArray;
	}

	public void updateXIndexArray(int indexElement) {
		this.xIndexArray.add(indexElement);
	}

	public ArrayList<Integer> getOIndexArray() {
		return oIndexArray;
	}

	public void updateOIndexArray(int indexElement) {
		this.oIndexArray.add(indexElement);
	}

	public ArrayList<Integer> getStateArray() {
		return StateArray;
	}

	public void addStateElement(int element) {
		this.StateArray.add(element);
	}
	
	public void updateStateArray(int index, int element) {
		this.StateArray.set(index, element);
	}
	
	public void setStateArray(ArrayList<Integer> stateArray) {
		this.StateArray = stateArray;
	}

	public void setXIndexArray(ArrayList<Integer> xIndexArray) {
		this.xIndexArray = xIndexArray;
	}
	
	public void setOIndexArray(ArrayList<Integer> oIndexArray) {
		this.oIndexArray = oIndexArray;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private List<Node> children;

	public Node () {
		parent = null;
		children = new ArrayList<Node>();
		StateArray = new ArrayList<>();
		xIndexArray = new ArrayList<>();
		oIndexArray = new ArrayList<>();
	}

	public boolean equals(Object node){
		return(((Node)node).getValue() == getValue() && ((Node)node).getValue() == getValue());
	}

	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void addChild(Node child) {
		this.children.add(child);
		child.setParent(this);
	}
	
	public void removeChild(int index) {
		this.children.get(index).parent = null;
		this.children.remove(index);
		
	}

	public List<Node> getChildren() {
		return children;
	}

	public void setChildren(List<Node> children) {
		this.children = children;
	}
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public int getAlphaValue() {
		return alphaValue;
	}

	public void setAlphaValue(int alphaValue) {
		this.alphaValue = alphaValue;
	}

	public int getBetaValue() {
		return betaValue;
	}

	public void setBetaValue(int betaValue) {
		this.betaValue = betaValue;
	}
	
	public String getWhoseTurn() {
		return whoseTurn;
	}

	public void setWhoseTurn(String whoseTurn) {
		this.whoseTurn = whoseTurn;
	}


}


public class TicTacToe {
	
	static ArrayList<ArrayList<Integer>> winningLeafStates = (ArrayList<ArrayList<Integer>>) CreateWinningLeafNodes.getWinningLeafStateIndexesList();
	
	public static void main(String [] args) {
		Node root = new Node();
		List<Integer> xIndexArray = new ArrayList<>();
		List<Integer> oIndexArray = new ArrayList<>();
		List<Integer> currStateList = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			if (args[i].equalsIgnoreCase("X")) {
				currStateList.add(1);
				xIndexArray.add(i);
			} else if (args[i].equalsIgnoreCase("O")) {
				currStateList.add(0);
				oIndexArray.add(i);
			} else if (args[i].equalsIgnoreCase("_")) {
				currStateList.add(2);
			} else if (args[i].equalsIgnoreCase("#")) {
				currStateList.add(3);
			}
		}
		System.out.println("INITIAL STATE" + currStateList);
		root.setParent(null);
		for (int i =0; i< currStateList.size(); i++){
			root.addStateElement(currStateList.get(i));
		}
		//root.setStateArray((ArrayList<Integer>) currStateList);
		root.setAlphaValue(-2);
		root.setBetaValue(2);
		root.setWhoseTurn("O");
		for (int f3 = 0; f3<xIndexArray.size(); f3++){
			root.updateXIndexArray(xIndexArray.get(f3));
		}
		
		for (int f3 = 0; f3<oIndexArray.size(); f3++){
			root.updateOIndexArray(oIndexArray.get(f3));
		}
		root.setType("not_leaf");
		
		maxValue(root);
		ArrayList<Integer> valueList = new ArrayList<>();
		for (int i = 0; i< root.getChildren().size(); i++) {
			valueList.add(root.getChildren().get(i).getValue());
			
		}
		int minValue = Collections.min(valueList);
		int minIndex = valueList.indexOf(minValue);
		System.out.println("SOLUTION" + root.getChildren().get(minIndex).getStateArray());

		
	}
		
	
	public static int maxValue(Node node) {
		if(node.getType().equalsIgnoreCase("leaf")){
/*			if (node.getAlphaValue() < node.getValue()) {
				node.setAlphaValue(node.getValue());
			}*/

			System.out.println(node.getStateArray());
			System.out.println("Alpha: " + node.getAlphaValue() + "  Beta: " + node.getBetaValue());
			return node.getValue();
		} else {
			//node.setAlphaValue(-2);
			getChildArray(node);
			
			for (int i = 0; i < node.getChildren().size(); i++) {
				//node.setValue(node.getAlphaValue());
				node.setAlphaValue(Math.max(node.getAlphaValue(), minValue(node.getChildren().get(i))));
				
				if (node.getAlphaValue() >= node.getBetaValue()) {
					//List<Node> currChildArray = node.getChildren();
					for (int succIndex = i+1; succIndex < node.getChildren().size(); succIndex++) {
						//currListChildArray.remove(succIndex);
						node.removeChild(succIndex);
					}
					System.out.println(node.getStateArray());
					System.out.println("Alpha: " + node.getAlphaValue() + "  Beta: " + node.getBetaValue());
					//node.setValue(node.getBetaValue());
					return node.getBetaValue();
				}
			}
		}

		System.out.println(node.getStateArray());
		System.out.println("Alpha: " + node.getAlphaValue() + "  Beta: " + node.getBetaValue());
		//node.setValue(node.getAlphaValue());
		return node.getAlphaValue();
		
	}
	
	public static int minValue(Node node) {

		if(node.getType().equalsIgnoreCase("leaf")) {
			//System.out.println("LEAF");
/*			if (node.getBetaValue() > node.getValue()) {
				node.setBetaValue(node.getValue());
			}*/
			//System.out.println(node.getBetaValue());

			System.out.println(node.getStateArray());
			System.out.println("Alpha: " + node.getAlphaValue() + "  Beta: " + node.getBetaValue());
			return node.getValue();
		} else {
			//System.out.println("Are you coming in min");
			//node.setBetaValue(2);
			getChildArray(node);
			for (int i1 = 0; i1< node.getChildren().size(); i1++) {
				node.setBetaValue(Math.min(node.getBetaValue(), maxValue(node.getChildren().get(i1))));
				
				if (node.getAlphaValue() >= node.getBetaValue()) {
					//List<Node> currChildArray = node.getChildren();
					for (int succIndex = i1+1; succIndex < node.getChildren().size(); succIndex++) {
						//currListChildArray.remove(succIndex);
						node.removeChild(succIndex);
					}
	
					System.out.println(node.getStateArray());
					System.out.println("Alpha: " + node.getAlphaValue() + "  Beta: " + node.getBetaValue());
					//node.setValue(node.getAlphaValue());
					return node.getAlphaValue();
					
				}
			}
			System.out.println(node.getStateArray());
			System.out.println("Alpha: " + node.getAlphaValue() + "  Beta: " + node.getBetaValue());
			//node.setValue(node.getBetaValue());
			return node.getBetaValue();
		}	
	}
	
		
	public static boolean checkIsLeaf(Node currNode) {
		boolean isLeaf = false;

			for (int i = 0; i < 12; i++) {
				//System.out.println(currNode.getXIndexArray() + "HELLLLL");
				
				//System.out.println(winningLeafStates.get(i));
				if (!isLeaf) {
					//System.out.println(currNode.getXIndexArray() + "HELLLasdfasdfadsfLL");
					if(currNode.getWhoseTurn().equalsIgnoreCase("X")) {
						isLeaf = currNode.getXIndexArray().containsAll(winningLeafStates.get(i));
						currNode.setValue(-1);
						//currNode.setBetaValue(-1);
						break;
					} else {
						isLeaf = currNode.getOIndexArray().containsAll(winningLeafStates.get(i));
						currNode.setValue(1);
						//currNode.setAlphaValue(1);
						break;
					}
				}

		}
			if (!currNode.getStateArray().contains(2) && !isLeaf) {
				//System.out.println("Leaf");
				currNode.setValue(0);
/*				if(currNode.getWhoseTurn().equalsIgnoreCase("X")) {
					currNode.setBetaValue(0);
				} else {
					currNode.setAlphaValue(0);
				}*/
				isLeaf = true;
			}
		
		return isLeaf;
	}
	

	public static void getChildArray(Node currNode) {
		List<Integer> freeIndexArray = new ArrayList<>();
		for (int i = 0; i < currNode.getStateArray().size(); i++) {
			if(currNode.getStateArray().get(i) == 2) {
				freeIndexArray.add(i);
			}
		}
		if(currNode.getWhoseTurn().equalsIgnoreCase("X")) {
			for (int l = 0; l< freeIndexArray.size(); l++) {
				Node childNode = new Node();
				//List<Integer> currXIndexArray = new ArrayList<>();
				List<Integer> currStateList = new ArrayList<>();
				currStateList = currNode.getStateArray();
				//currStateList.set(freeIndexArray.get(l), 1);
				//childNode.setStateArray((ArrayList<Integer>)currStateList);
				
				for (int i=0; i<currStateList.size(); i++) {
					childNode.addStateElement(currStateList.get(i));
				}
				
				//childNode.updateStateArray(freeIndexArray.get(l), 1);
				childNode.setWhoseTurn("O");
				childNode.setAlphaValue(currNode.getAlphaValue());
				childNode.setBetaValue(currNode.getBetaValue());
				//currXIndexArray.add(freeIndexArray.get(l));
				
				for (int i = 0; i< currNode.getXIndexArray().size(); i++){
					childNode.updateXIndexArray(currNode.getXIndexArray().get(i));
				}
				
				
				for (int i=0;i<currNode.getOIndexArray().size(); i++) {
					childNode.updateOIndexArray(currNode.getOIndexArray().get(i));
				}
				
				childNode.updateStateArray(freeIndexArray.get(l), 0);
				
				childNode.updateOIndexArray(freeIndexArray.get(l));


				//childNode.setParent(currNode);                            //doing in addChild
				
				
				currNode.addChild(childNode);
				
				boolean isLeaf = checkIsLeaf(childNode);
				//System.out.println(isLeaf);
				if(isLeaf) {
					childNode.setType("leaf");
				} else {
					childNode.setType("not_leaf");
				}
				

			}
			
		} else if (currNode.getWhoseTurn().equalsIgnoreCase("O")) {
			for (int l1 = 0; l1 < freeIndexArray.size(); l1++) {
				//System.out.println("HOW MANY" + freeIndexArray.get(l1));
				Node childNode = new Node();
				List<Integer> currStateList = new ArrayList<>();
				//List<Integer> currOIndexArray = new ArrayList<>();
				//List<Integer> currXIndexArray = new ArrayList<>();
				currStateList = currNode.getStateArray();
				//currStateList.set(freeIndexArray.get(l1), 0);
				//childNode.setStateArray((ArrayList<Integer>)currStateList);
				
				
				//childNode.updateStateArray(freeIndexArray.get(l1), 0);
				
				//childNode.setStateArray((ArrayList<Integer>)currStateList);
				childNode.setWhoseTurn("X");
				childNode.setAlphaValue(currNode.getAlphaValue());
				childNode.setBetaValue(currNode.getBetaValue());
				//currOIndexArray = currNode.getOIndexArray();
				//currXIndexArray = currNode.getXIndexArray();
					
				//currOIndexArray.add(freeIndexArray.get(l1));
				//childNode.setOIndexArray((ArrayList<Integer>)currOIndexArray);
				for (int i =0; i<currStateList.size(); i++){
					childNode.addStateElement(currStateList.get(i));
				}
				
				for (int i=0;i<currNode.getOIndexArray().size(); i++) {
					childNode.updateOIndexArray(currNode.getOIndexArray().get(i));
				}
				
				for (int i = 0; i<currNode.getXIndexArray().size(); i++){
					childNode.updateXIndexArray(currNode.getXIndexArray().get(i));
				}
							
				childNode.updateStateArray(freeIndexArray.get(l1), 1);
				
				//System.out.println("ChildNODEState" + childNode.getStateArray());
				
				childNode.updateXIndexArray(freeIndexArray.get(l1));
				
				//childNode.setXIndexArray((ArrayList<Integer>)currXIndexArray);
				//childNode.setParent(currNode);                            //doing in addChild
				currNode.addChild(childNode);
				
				boolean isLeaf = checkIsLeaf(childNode);
				if(isLeaf) {
					childNode.setType("leaf");
				}  else {
					childNode.setType("not_leaf");
				}
				//System.out.println(isLeaf);

			}
		}
		
		
	}
	
}
