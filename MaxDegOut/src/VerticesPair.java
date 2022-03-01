public class VerticesPair {
    /**
     * class to represent a pair of vertices - an edge.
     * the direction of the edge is from first to second.
     */
    private int first;
    private int second;
    public VerticesPair(int first, int second) {
        this.first = first;
        this.second = second;
    }
    public int GetFirst() {
        return this.first;
    }
    public int GetSecond() {
        return this.second;
    }
    public boolean equals(Object o) {
        if (!(o instanceof VerticesPair)) {
            return false;
        }
        VerticesPair other = (VerticesPair) o;
        return this.first == other.first && this.second == other.second;
    }
}
