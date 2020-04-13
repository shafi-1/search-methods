public class State {

    private String[][] state;
    private int[] agentLocation;

    public State(String[][] state) {
        this.state = state;
    }

    public String[][] getState() {
        return this.state;
    }

    public void setAgentLocation(int[] agentLocation) {
        this.agentLocation = agentLocation;
    }

    public int[] getAgentLocation() {
        return this.agentLocation;
    }

    public void move(String direction) {
        int x = 0, y = 0;
        if (direction.equals("U")) {
            y = -1;
        } else if (direction.equals("D")) {
            y = 1;
        } else if (direction.equals("L")) {
            x = -1;
        } else if (direction.equals("R")) {
            x = 1;
        }
        String[][] newState = this.state;
        String temp = newState[this.agentLocation[0]+y][this.agentLocation[1]+x];
        newState[this.agentLocation[0]+y][this.agentLocation[1]+x]
                = newState[this.agentLocation[0]][this.agentLocation[1]];
        newState[this.agentLocation[0]][this.agentLocation[1]]
                = temp;
        setAgentLocation(new int[]{this.agentLocation[0]+y, this.agentLocation[1]+x});
    }

}
