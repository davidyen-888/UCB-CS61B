package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.lab9.MyTrieSet;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, David Hsieh
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private HashMap<Point, Long> points;
    private KDTree kdTree;
    private MyTrieSet trie;
    private Map<String, List<Node>> nameToNodes;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        List<Node> nodes = this.getNodes();

        // Uses a map to go between Nodes and Points, so able to use kdTree of nodes.
        // This map consists only node ids with neighbors.
        points = new HashMap<>();
        for (Node n : nodes) {
            if (!neighbors(n.id()).isEmpty()) {
                points.put(new Point(n.lon(), n.lat()), n.id());
            }
        }
        // Initialize kdTree for closest method below.
        kdTree = new KDTree(new LinkedList(points.keySet()));
        // Trie initialization
        trie = new MyTrieSet();
        nameToNodes = new HashMap<>();
        for (Node n : nodes) {
            if (n.name() != null) {
                String cleanName = cleanString(n.name());
                trie.add(cleanName);
                if (!nameToNodes.containsKey(cleanName)) {
                    nameToNodes.put(cleanName, new LinkedList<>());
                }
                nameToNodes.get(cleanName).add(n);
            }
        }

    }


    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        Point closestPoint = kdTree.nearest(lon, lat);
        return points.get(closestPoint);
    }


    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        List<String> cleanLocationNames = trie.keysWithPrefix(cleanString(prefix));
        List<String> fullNames = new LinkedList<>();
        for (String name : cleanLocationNames) {
            for (Node n : nameToNodes.get(name)) {
                if (!fullNames.contains(n.name())) {
                    fullNames.add(n.name());
                }
            }
        }
        return fullNames;
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> locations = new LinkedList<>();
        String cleanNames = cleanString(locationName);
        if (nameToNodes.containsKey(cleanNames)) {
            for (Node n : nameToNodes.get(cleanNames)) {
                Map<String, Object> locationParams = new HashMap<>();
                locationParams.put("lat", n.lat());
                locationParams.put("lon", n.lon());
                locationParams.put("name", n.name());
                locationParams.put("id", n.id());
                locations.add(locationParams);
            }
        }
        return locations;
    }


    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
