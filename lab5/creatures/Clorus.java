package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

/**
 * An implementation of Clorus, the mortal enemy for Plip.
 *
 * @author davidhsieh
 */
public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /** Always returns the color red = 34, green = 0, blue = 231. */
    public Color color() {
        return color(r, g, b);
    }

    /** Cloruses should lose 0.03 units of energy on a MOVE action. */
    public void move() {
        energy -= 0.03;
    }

    /** Cloruses should lose 0.01 units of energy on a STAY action. */
    public void stay() {
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * Like a Plip, when a Clorus replicates, it keeps 50% of its energy.
     * The other 50% goes to its offspring. No energy is lost in the replication process.
     */
    public Clorus replicate() {
        energy = energy * 0.5;
        double babyEnergy = energy;
        return new Clorus(babyEnergy);
    }

    /** If a Clorus attacks another creature, it should gain that creature’s energy. */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Cloruses should obey exactly the following behavioral rules:
     * 1. If there are no empty squares, the Clorus will STAY (even if there are Plips nearby
     * they could attack since plip squares do not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        //boolean anyClorus = false;
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            String value = entry.getValue().name();
            if (value.equals("empty")) {
                emptyNeighbors.addLast(entry.getKey());
            } else if (value.equals("plip")) {
                plipNeighbors.addLast(entry.getKey());
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (!plipNeighbors.isEmpty()){
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }
        // Rule 3
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }

        // Rule 4
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }

}