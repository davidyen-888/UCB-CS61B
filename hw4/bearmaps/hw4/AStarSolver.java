package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;

import java.util.HashMap;
import java.util.List;

//    Since AStarGraph<Vertex> uses a generic type for vertices, the input graph’s vertices may be a reference type.
//    Thus, make sure to use the equals method whenever you want to compare two vertices for equality.

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private ArrayHeapMinPQ<Vertex> fringe;      // PQ that stores vertices to be visited
    private SolverOutcome outcome;      // problem solved status
    private List<Vertex> solution;      // in order vertices of the shortest path
    private double solutionWeight;      // shortest path's total weight
    private int numStatesExplored;      // number of vertices visited
    private double timeSpent;     // total time to find the shortest path
    private HashMap<Vertex, Double> disTo;  // distance from start vertex
    private HashMap<Vertex, Vertex> edgeTo; // edge to the leading vertex

    /**
     * Constructor which finds the solution, computing everything necessary for all other
     * methods to return their results in constant time. Note that timeout passed in is in seconds.
     *
     * A* Algorithm(memory optimized version):
     *      > Create a PQ where each vertex v will have priority p equal to the sum of v’s distance from
     *      the source plus the heuristic estimate from v to the goal, i.e. p = distTo[v] + h(v, goal).
     *      > Insert the source vertex into the PQ.
     *      > Repeat until the PQ is empty, PQ.getSmallest() is the goal, or timeout is exceeded:
     *          p = PQ.removeSmallest()
     *          relax all edges outgoing from p
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {

    }

    /**
     * relax(e):
     *      > p = e.from(), q = e.to(), w = e.weight()
     *      > if distTo[p] + w < distTo[q]:
     *          distTo[q] = distTo[p] + w
     *          if q is in the PQ: changePriority(q, distTo[q] + h(q, goal))
     *          if q is not in PQ: add(q, distTo[q] + h(q, goal))
     * */
    private void relax(AStarGraph<Vertex> input, Vertex end, Vertex p) {

    }

    /**
     * Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty. TIMEOUT if the solver ran out of time.
     * You should check to see if you have run out of time every time you dequeue.
     */
    public SolverOutcome outcome() {
        return outcome;
    }

    /** A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE. */
    public List<Vertex> solution() {
        return solution;
    }

    /**
     * The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     */
    public double solutionWeight() {
        return solutionWeight;
    }

    /** The total number of priority queue dequeue operations. */
    public int numStatesExplored() {
        return numStatesExplored;
    }

    /** The total time spent in seconds by the constructor. */
    public double explorationTime() {
        return timeSpent;
    }
}

