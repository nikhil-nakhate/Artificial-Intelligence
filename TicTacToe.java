/* Name: 			Nikhil Sudhindra Nakhate
 * Wisc Username: 	nakhate
 * Class Section:	1
 * Homework #:		5
 * Submission date:	10/27/2016
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

	public Node() {
		parent = null;
		children = new ArrayList<Node>();
		StateArray = new ArrayList<>();
		xIndexArray = new ArrayList<>();
		oIndexArray = new ArrayList<>();
	}

	public boolean equals(Object node) {
		return (((Node) node).getValue() == getValue() && ((Node) node).getValue() == getValue());
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

class CreateWinningLeafNodes {

	public static List<ArrayList<Integer>> getWinningLeafStateIndexesList() {

		ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();

		List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(0, 1, 2));
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(0, 5, 10));
		List<Integer> list3 = new ArrayList<Integer>(Arrays.asList(1, 2, 3));
		List<Integer> list4 = new ArrayList<Integer>(Arrays.asList(1, 5, 9));
		List<Integer> list5 = new ArrayList<Integer>(Arrays.asList(1, 6, 11));
		List<Integer> list6 = new ArrayList<Integer>(Arrays.asList(2, 5, 8));
		List<Integer> list7 = new ArrayList<Integer>(Arrays.asList(2, 6, 10));
		List<Integer> list8 = new ArrayList<Integer>(Arrays.asList(3, 6, 9));
		List<Integer> list9 = new ArrayList<Integer>(Arrays.asList(3, 7, 11));
		List<Integer> list10 = new ArrayList<Integer>(Arrays.asList(5, 6, 7));
		List<Integer> list11 = new ArrayList<Integer>(Arrays.asList(8, 9, 10));
		List<Integer> list12 = new ArrayList<Integer>(Arrays.asList(9, 10, 11));

		mainList.add((ArrayList<Integer>) list1);
		mainList.add((ArrayList<Integer>) list2);
		mainList.add((ArrayList<Integer>) list3);
		mainList.add((ArrayList<Integer>) list4);
		mainList.add((ArrayList<Integer>) list5);
		mainList.add((ArrayList<Integer>) list6);
		mainList.add((ArrayList<Integer>) list7);
		mainList.add((ArrayList<Integer>) list8);
		mainList.add((ArrayList<Integer>) list9);
		mainList.add((ArrayList<Integer>) list10);
		mainList.add((ArrayList<Integer>) list11);
		mainList.add((ArrayList<Integer>) list12);

		return mainList;

	}

}

public class TicTacToe {

	static ArrayList<ArrayList<Integer>> winningLeafStates = (ArrayList<ArrayList<Integer>>) CreateWinningLeafNodes
			.getWinningLeafStateIndexesList();
	static boolean toPrintStates;

	public static void main(String[] args) {
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

		if (args[12].equalsIgnoreCase("Y")) {
			toPrintStates = true;
		} else if (args[12].equalsIgnoreCase("N")) {
			toPrintStates = false;
		}

		// System.out.println("INITIAL STATE" + currStateList);
		root.setParent(null);
		for (int i = 0; i < currStateList.size(); i++) {
			root.addStateElement(currStateList.get(i));
		}
		// root.setStateArray((ArrayList<Integer>) currStateList);
		root.setAlphaValue(-2);
		root.setBetaValue(2);
		root.setWhoseTurn("O");
		for (int f3 = 0; f3 < xIndexArray.size(); f3++) {
			root.updateXIndexArray(xIndexArray.get(f3));
		}

		for (int f3 = 0; f3 < oIndexArray.size(); f3++) {
			root.updateOIndexArray(oIndexArray.get(f3));
		}
		root.setType("not_leaf");

		minValue(root, -2, 2);
		ArrayList<Integer> valueList = new ArrayList<>();
		for (int i = 0; i < root.getChildren().size(); i++) {
			valueList.add(root.getChildren().get(i).getValue());
		}
		int minValue = Collections.min(valueList);
		int minIndex = valueList.indexOf(minValue);
		List<Integer> solutionArray = new ArrayList<>();
		solutionArray = root.getChildren().get(minIndex).getStateArray();

		System.out.println("SOLUTION");
		printOutput(solutionArray);

	}

	public static void printOutput(List<Integer> stateList) {
		for (int i = 0; i < stateList.size(); i = i + 4) {
			for (int j = i; j < i + 4; j++) {
				if (stateList.get(j) == 0) {
					System.out.print("O ");
				}
				if (stateList.get(j) == 1) {
					System.out.print("X ");
				}
				if (stateList.get(j) == 2) {
					System.out.print("_ ");
				}
				if (stateList.get(j) == 3) {
					System.out.print("# ");
				}

			}
			System.out.print('\n');
		}
	}

	public static int maxValue(Node node, int alpha, int beta) {

		if (node.getType().equalsIgnoreCase("leaf")) {

			if (toPrintStates) {
				printOutput(node.getStateArray());
				System.out.println("Alpha: " + alpha + " Beta: " + beta);
			}

			return node.getValue();
		} else {

			getChildArray(node);

			for (int i = 0; i < node.getChildren().size(); i++) {

				alpha = (Math.max(alpha, minValue(node.getChildren().get(i), alpha, beta)));
				node.setValue(alpha);
				/*
				 * if(node.getParent() != null) {
				 * node.getParent().setBetaValue(Math.min(node.getValue(),
				 * node.getParent().getBetaValue())); }
				 */

				if (alpha >= beta) {

					if (toPrintStates) {
						printOutput(node.getStateArray());
						System.out.println("Alpha: " + alpha + "  Beta: " + beta);
					}
					return beta;
				}
			}
		}

		// System.out.println(node.getStateArray());
		if (toPrintStates) {
			printOutput(node.getStateArray());
			System.out.println("Alpha: " + alpha + "  Beta: " + beta);
		}
		node.setValue(alpha);
		return alpha;

	}

	public static int minValue(Node node, int alpha, int beta) {

		if (node.getType().equalsIgnoreCase("leaf")) {

			if (toPrintStates) {
				printOutput(node.getStateArray());
				System.out.println("Alpha: " + alpha + "  Beta: " + beta);
			}
			return node.getValue();
		} else {

			getChildArray(node);

			for (int i1 = 0; i1 < node.getChildren().size(); i1++) {

				beta = Math.min(beta, maxValue(node.getChildren().get(i1), alpha, beta));
				node.setValue(beta);
				if (alpha >= beta) {
					if (toPrintStates) {
						printOutput(node.getStateArray());
						System.out.println("Alpha: " + alpha + "  Beta: " + beta);
					}
					return alpha;

				}
			}
			if (toPrintStates) {
				printOutput(node.getStateArray());
				System.out.println("Alpha: " + alpha + "  Beta: " + beta);
			}

			node.setValue(beta);
			return beta;
		}
	}

	public static boolean checkIsLeaf(Node currNode) {
		boolean isLeaf = false;

		if (currNode.getWhoseTurn().equalsIgnoreCase("X")) {
			for (int i = 0; i < 12; i++) {
				isLeaf = currNode.getXIndexArray().containsAll(winningLeafStates.get(i));
				currNode.setValue(-1);
				if (isLeaf) {
					break;
				}
			}
		} else {
			for (int i = 0; i < 12; i++) {
				isLeaf = currNode.getOIndexArray().containsAll(winningLeafStates.get(i));
				currNode.setValue(1);
				if (isLeaf) {
					break;
				}
			}
		}
		if (!currNode.getStateArray().contains(2) && !isLeaf) {
			// System.out.println("Leaf");
			currNode.setValue(0);
			isLeaf = true;
		}
		return isLeaf;
	}

	public static void getChildArray(Node currNode) {
		List<Integer> freeIndexArray = new ArrayList<>();
		for (int i = 0; i < currNode.getStateArray().size(); i++) {
			if (currNode.getStateArray().get(i) == 2) {
				freeIndexArray.add(i);
			}
		}
		if (currNode.getWhoseTurn().equalsIgnoreCase("X")) {
			for (int l = 0; l < freeIndexArray.size(); l++) {
				Node childNode = new Node();
				// List<Integer> currXIndexArray = new ArrayList<>();
				List<Integer> currStateList = new ArrayList<>();
				currStateList = currNode.getStateArray();
				// currStateList.set(freeIndexArray.get(l), 1);
				// childNode.setStateArray((ArrayList<Integer>)currStateList);

				for (int i = 0; i < currStateList.size(); i++) {
					childNode.addStateElement(currStateList.get(i));
				}

				// childNode.updateStateArray(freeIndexArray.get(l), 1);
				childNode.setWhoseTurn("O");
				// childNode.setAlphaValue(currNode.getAlphaValue());
				// childNode.setBetaValue(currNode.getBetaValue());
				// currXIndexArray.add(freeIndexArray.get(l));

				for (int i = 0; i < currNode.getXIndexArray().size(); i++) {
					childNode.updateXIndexArray(currNode.getXIndexArray().get(i));
				}

				for (int i = 0; i < currNode.getOIndexArray().size(); i++) {
					childNode.updateOIndexArray(currNode.getOIndexArray().get(i));
				}

				childNode.updateStateArray(freeIndexArray.get(l), 0);

				childNode.updateOIndexArray(freeIndexArray.get(l));

				currNode.addChild(childNode);

				boolean isLeaf = checkIsLeaf(childNode);
				// System.out.println(isLeaf);
				if (isLeaf) {
					childNode.setType("leaf");
				} else {
					childNode.setType("not_leaf");
				}

			}

		} else if (currNode.getWhoseTurn().equalsIgnoreCase("O")) {
			for (int l1 = 0; l1 < freeIndexArray.size(); l1++) {
				// System.out.println("HOW MANY" + freeIndexArray.get(l1));
				Node childNode = new Node();
				List<Integer> currStateList = new ArrayList<>();

				currStateList = currNode.getStateArray();

				childNode.setWhoseTurn("X");
				// childNode.setAlphaValue(currNode.getAlphaValue());
				// childNode.setBetaValue(currNode.getBetaValue());

				for (int i = 0; i < currStateList.size(); i++) {
					childNode.addStateElement(currStateList.get(i));
				}

				for (int i = 0; i < currNode.getOIndexArray().size(); i++) {
					childNode.updateOIndexArray(currNode.getOIndexArray().get(i));
				}

				for (int i = 0; i < currNode.getXIndexArray().size(); i++) {
					childNode.updateXIndexArray(currNode.getXIndexArray().get(i));
				}

				childNode.updateStateArray(freeIndexArray.get(l1), 1);

				// System.out.println("ChildNODEState" +
				// childNode.getStateArray());

				childNode.updateXIndexArray(freeIndexArray.get(l1));

				currNode.addChild(childNode);

				boolean isLeaf = checkIsLeaf(childNode);
				if (isLeaf) {
					childNode.setType("leaf");
				} else {
					childNode.setType("not_leaf");
				}

			}
		}

	}

}
