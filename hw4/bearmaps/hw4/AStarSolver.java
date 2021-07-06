package bearmaps.hw4;

import java.util.List;

//    Since AStarGraph<Vertex> uses a generic type for vertices, the input graph’s vertices may be a reference type.
//    Thus, make sure to use the equals method whenever you want to compare two vertices for equality.

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    /**
     * Constructor which finds the solution, computing everything necessary for all other
     * methods to return their results in constant time. Note that timeout passed in is in seconds.
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {

    }

    /**
     * Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
     * You should check to see if you have run out of time every time you dequeue.
     */
    public SolverOutcome outcome() {

    }

    /** A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE. */
    public List<Vertex> solution() {

    }

    /**
     * The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     */
    public double solutionWeight() {

    }

    /** The total number of priority queue dequeue operations. */
    public int numStatesExplored() {

    }

    /** The total time spent in seconds by the constructor. */
    public double explorationTime() {

    }
}

