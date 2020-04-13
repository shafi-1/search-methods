import java.util.*;

public class Main {

    public static void main(String args[]) {
        Node root = new Node(new State(new String[][]{{"0","0","0","0"},
                {"0","0","0","0"}, {"0","0","0","0"}, {"A","B","C","S"}}));
        root.setDefault(new int[]{3,3});
        // Method that is not commented will be run
        breadthFirst(root);
//        depthFirst(root);
//        iterativeDeepening(root);
//        aStar(root);
    }

    private static void breadthFirst(Node root) {
        int nodesGenerated = 0;
        Queue<Node> queue = new LinkedList<Node>();
        Node node = root;
        printStatus(nodesGenerated, node.getPath(), node.getDepth());
        while(!node.goalState()) {
            nodesGenerated = nodesGenerated + node.branch(queue);
            node = queue.remove();
            printStatus(nodesGenerated, node.getPath(), node.getDepth());
        }
        printGoal(node.getPath(), nodesGenerated, node.getDepth());
    }

    private static void depthFirst(Node root) {
        int nodesGenerated = 0;
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        printStatus(nodesGenerated, node.getPath(), node.getDepth());
        while(!node.goalState()) {
            List<Node> list = new ArrayList<Node>();
            nodesGenerated = nodesGenerated + node.branch(list);
            Collections.shuffle(list);
            stack.addAll(list);
            node = stack.pop();
            printStatus(nodesGenerated, node.getPath(), node.getDepth());
        }
        printGoal(node.getPath(), nodesGenerated, node.getDepth());
    }

    private static void iterativeDeepening(Node root) {
        int nodesGenerated = 0;
        int depthLimit = 0;
        Stack<Node> stack = new Stack<Node>();
        Node node = root;
        System.out.println("Nodes Generated: " + nodesGenerated
                + " -- Path: " + node.getPath()
                + " -- Depth: " + node.getDepth()
                + " -- Depth Limit: " + depthLimit);
        while(!node.goalState()) {
            if (node.getDepth() < depthLimit) {
                List<Node> list = new ArrayList<Node>();
                nodesGenerated = nodesGenerated + node.branch(list);
                stack.addAll(list);
            }
            if (stack.empty()) {
                depthLimit++;
                node = root;
            } else {
                node = stack.pop();
                System.out.println("Nodes Generated: " + nodesGenerated
                        + " -- Path: " + node.getPath()
                        + " -- Depth: " + node.getDepth()
                        + " -- Depth Limit: " + depthLimit);
            }
        }
        System.out.println("\nGoal State Found: \n"
                + "Path: " + node.getPath() + "\n"
                + "Nodes Generated: " + nodesGenerated + "\n"
                + "Depth: " + node.getDepth() + "\n"
                + "Depth Limit: " + depthLimit);
    }

    private static void aStar(Node root) {
        int nodesGenerated = 0;
        PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();
        Node node = root;
        printStatus(nodesGenerated, node.getPath(), node.getDepth());
        while(!node.goalState()) {
            nodesGenerated = nodesGenerated + node.branch(priorityQueue);
            node = priorityQueue.remove();
            printStatus(nodesGenerated, node.getPath(), node.getDepth());
        }
        printGoal(node.getPath(), nodesGenerated, node.getDepth());
    }

    private static void printStatus(int nodes, String path, int depth) {
        System.out.println("Nodes Generated: " + nodes
                + " -- Path: " + path
                + " -- Depth: " + depth);
    }

    private static void printGoal(String path, int nodes, int depth) {
        System.out.println("\nGoal State Found: \n"
                + "Path: " + path + "\n"
                + "Nodes Generated: " + nodes + "\n"
                + "Depth: " + depth);
    }

}
