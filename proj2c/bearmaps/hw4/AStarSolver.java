package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private ArrayHeapMinPQ<Vertex> fringe;      // PQ that stores vertices to be visited and relaxed
    private SolverOutcome outcome;      // problem solved status
    private List<Vertex> solution;      // in order vertices of the shortest path
    private double solutionWeight;      // shortest path's total weight
    private int numStatesExplored;      // number of vertices visited
    private double timeSpent;     // total time to find the shortest path
    private HashMap<Vertex, Double> disTo;  // current shortest distance to vertex
    private HashMap<Vertex, Vertex> edgeTo; // a pair of (v1, v2) means v2 is pointed to v1

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
        // Variable initialization
        fringe = new ArrayHeapMinPQ<>();
        numStatesExplored = 0;
        disTo = new HashMap<>();
        edgeTo = new HashMap<>();
        solution = new LinkedList<>();
        solutionWeight = 0;
        Stopwatch sw = new Stopwatch();

        // Insert the source vertex into the PQ.
        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        disTo.put(start, 0.0);
        edgeTo.put(start, null);

        // Repeat until the PQ is empty, PQ.getSmallest() is the goal, or timeout is exceeded:
        //      p = PQ.removeSmallest()
        //      relax all edges outgoing from p
        while (fringe.size() != 0) {
            Vertex p = fringe.removeSmallest();
            numStatesExplored++;
            // If goal is found, update the solution and stop.
            if (p.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = disTo(end);
                timeSpent = sw.elapsedTime();
                solution = updateSolution(edgeTo, end);
                return;
            }
            // Timeout is exceeded, stop.
            else if (timeSpent > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                solutionWeight = 0;
                timeSpent = sw.elapsedTime();
                return;
            }
            // Relax all edges outgoing from p.
            else {
                for (WeightedEdge<Vertex> e : input.neighbors(p)) {
                    relax(e, input, end);
                }
            }
        }
        // Goal is not found.
        outcome = SolverOutcome.UNSOLVABLE;
        solutionWeight = 0;
        timeSpent = sw.elapsedTime();
    }

    /** Return list of solution from HashMap edgeTo. */
    private List<Vertex> updateSolution(HashMap<Vertex, Vertex> edgeTo, Vertex end) {
        LinkedList<Vertex> result = new LinkedList<>();
        while (edgeTo.containsKey(end)) {
            result.addFirst(end);
            end = edgeTo.get(end);
        }
        return result;
    }

    /** Returns closest distance to the vertex, otherwise set as infinity. */
    private double disTo(Vertex v) {
        return disTo.getOrDefault(v, Double.MAX_VALUE);
    }


    /**
     * relax(e):
     *      > p = e.from(), q = e.to(), w = e.weight()
     *      > if distTo[p] + w < distTo[q]:
     *          distTo[q] = distTo[p] + w
     *          if q is in the PQ: changePriority(q, distTo[q] + h(q, goal))
     *          if q is not in PQ: add(q, distTo[q] + h(q, goal))
     * */
    private void relax(WeightedEdge<Vertex> e, AStarGraph<Vertex> graph, Vertex end) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        if ((disTo(p) + w) < disTo(q)) {
            disTo.put(q, disTo(p) + w);
            edgeTo.put(q, p);
            if (fringe.contains(q)) {
                fringe.changePriority(q, disTo(q) + graph.estimatedDistanceToGoal(q, end));
            } else {
                fringe.add(q, disTo(q) + graph.estimatedDistanceToGoal(q, end));
            }
        }
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

