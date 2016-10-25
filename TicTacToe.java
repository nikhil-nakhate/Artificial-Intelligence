/* Name: 			Nikhil Sudhindra Nakhate
 * Wisc Username: 	nakhate
 * Class Section:	1
 * Homework #:		5
 * Submission date:	
 */



import java.util.ArrayList;
import java.util.List;


public class TicTacToe {
	
	class Node {


		private int value;
		private Node parent;
		private String type;

		private String whoseTurn;


		private int alphaValue;
		private int betaValue;
		

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
			children.add(child);
			child.setParent(this);
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
	
	
	public int maxValue(Node node) {
		if(node.getType().equalsIgnoreCase("leaf")){
			return node.getValue();
		} else {
			node.setAlphaValue(-2);
			for (int i = 0; i < node.getChildren().size(); i++) {
				
			}
		}
		
		return 0;
		
	}
	
	public int minValue(Node node) {
		if(node.getType().equalsIgnoreCase("leaf")) {
			return node.getValue();
		} else {
			node.setBetaValue(2);
			for (int i1 = 0; i1< node.getChildren().size(); i1++) {
				
			}
		}
		return 0;
		
	}

	public static List<Node> getChildArray(Node currNode) {
		
		
		return null;
		
	}

}
