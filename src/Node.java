import java.util.*;

public class Node implements Comparable<Node> {

    private State state;
    private String path = "";
    private boolean moveUp;
    private boolean moveDown;
    private boolean moveLeft;
    private boolean moveRight;

    public Node(State state) {
        this.state = state;
        this.moveUp = true;
        this.moveDown = true;
        this.moveLeft = true;
        this.moveRight = true;
    }

    public String getPath() {
        return path;
    }

    public int getDepth() {
        return path.length();
    }

    public String[][] getState() {
        return this.state.getState();
    }

    public int[] getAgentLocation() {
        return this.state.getAgentLocation();
    }

    public void setPath(String current, String update) {
        this.path = current + update;
    }

    public void setDefault(int[] agentLocation) {
        this.state.setAgentLocation(agentLocation);
    }

    public int branch(Collection<Node> collection) {
        int nodeIncrease = 0;
        this.directions();
        collection.add(nodeChange(moveUp, "U"));
        nodeIncrease++;
        collection.add(nodeChange(moveDown, "D"));
        nodeIncrease++;
        collection.add(nodeChange(moveLeft, "L"));
        nodeIncrease++;
        collection.add(nodeChange(moveRight, "R"));
        nodeIncrease++;
        return nodeIncrease;
    }

    // INFORMED BRANCH vvvvvvvvvvvvvvvv
    // Comment old branch, comment out informed branch
//    public int branch(Collection<Node> collection) {
//        int nodeIncrease = 0;
//        this.directions();
//        if (moveUp) {
//            collection.add(nodeChange(moveUp, "U"));
//            nodeIncrease++;
//        }
//        if (moveDown) {
//            collection.add(nodeChange(moveDown, "D"));
//            nodeIncrease++;
//        }
//        if (moveLeft) {
//            collection.add(nodeChange(moveLeft, "L"));
//            nodeIncrease++;
//        }
//        if (moveRight) {
//            collection.add(nodeChange(moveRight, "R"));
//            nodeIncrease++;
//        }
//        return nodeIncrease;
//    }
    // INFORMED BRANCH ^^^^^^^^^^^^^^^^

    private Node nodeChange(Boolean direction, String updatePath) {
        State newState = new State(this.cloneArray(this.getState()));
        newState.setAgentLocation(this.cloneAgentLocation(this.getAgentLocation()));
        if (direction) {
            newState.move(updatePath);
        }
        Node node = new Node(newState);
        node.setPath(this.path, updatePath);
        return node;
    }

    private void directions() {
        int[] arr = this.state.getAgentLocation();
        if (arr[0] == 0) {
            this.moveUp = false;
        }
        if (arr[0] == 3) {
            this.moveDown = false;
        }
        if (arr[1] == 0) {
            this.moveLeft = false;
        }
        if (arr[1] == 3) {
            this.moveRight = false;
        }
    }

    public String[][] cloneArray(String[][] oldState) {
        String[][] newState = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                   newState[i][j] = oldState[i][j];
            }
        }
        return newState;
    }

    public int[] cloneAgentLocation(int[] oldAgentLocation) {
        int[] newAgentLocation = new int[2];
        for (int i = 0; i < 2; i++) {
            newAgentLocation[i] = oldAgentLocation[i];
        }
        return newAgentLocation;
    }

    public void print() {
        System.out.println();
        for (String[] row : this.state.getState()) {
            for (String col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean goalState() {
        String[][] state = this.state.getState();
        return state[1][1].equals("A") && state[2][1].equals("B") && state[3][1].equals("C");
    }

    public int getHeuristic() {
        int ax = 0, ay = 0, bx = 0, by = 0, cx = 0, cy = 0;
        String[][] state = this.state.getState();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                   if (state[i][j].equals("A")) {
                       ax = j;
                       ay = i;
                   } else if (state[i][j].equals("B")) {
                       bx = j;
                       by = i;
                   } else if (state[i][j].equals("C")) {
                       cx = j;
                       cy = i;
                   }
            }
        }
        int totalDifference =
                (Math.abs(1-ax) + Math.abs(1-ay)
                + Math.abs(1-bx) + Math.abs(2-by)
                + Math.abs(1-cx) + Math.abs(3-cy));
        return totalDifference;
    }

    @Override
    public int compareTo(Node o) {
        int thisScore = this.getDepth() + this.getHeuristic();
        int nodeScore = o.getDepth() + o.getHeuristic();

        return Integer.compare(thisScore, nodeScore);
    }

}
