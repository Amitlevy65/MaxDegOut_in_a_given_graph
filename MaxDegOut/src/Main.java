import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filename = "src\\test\\tests.txt";
        List<String> lines = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
        int correctDegs = 0;
        int correctPaths = 0;
        int iter = 1;
        for (String line: lines) {
            System.out.print("test " + iter++ + ": ");
            String[] parts = line.split(",");
            int maxDeg = Integer.parseInt(parts[0]);
            int vertices = Integer.parseInt(parts[1]);
            List<VerticesPair> edges = new LinkedList<>();
            for (int i = 2; i < parts.length; i++) {
                String[] pair = parts[i].split(":");
                edges.add(new VerticesPair(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
            }
            try {
                Solver s = new Solver();
                int outputDeg = s.findOPT(vertices, edges);
                if (outputDeg == maxDeg) {
                    correctDegs++;
                    System.out.print("opt is correct, ");
                } else {
                    System.out.print("opt is incorrect, ");
                }
                List<Integer> outputPath = s.getSol();
                if (TestPath(outputPath, edges, maxDeg)) {
                    correctPaths++;
                    System.out.println("path is correct");
                } else
                    System.out.println("path is incorrect");
            } catch (Exception e) {
                System.out.println("test crashed");
            }
        }
        System.out.println("opt: " + correctDegs + " correct out of " + lines.size());
        System.out.println("path: " + correctPaths + " correct out of " + lines.size());
        System.out.println("total grade: " + (correctDegs + correctPaths) * 20.0 / lines.size() + " out of 40");
    }
    public static boolean TestPath(List<Integer> path, List<VerticesPair> edges, int maxDeg) {
        /**
         * check if a path is in graph and its degree is the max degree
         */
        for (int i = 0; i < path.size() - 1; i++) {
            VerticesPair edge = new VerticesPair(path.get(i), path.get(i + 1));
            if (!edges.contains(edge))
                return false;
        }
        int deg = 0;
        for (VerticesPair edge: edges) {
            if (path.contains(edge.GetFirst())) {
                deg++;
            }
        }
        if (deg == maxDeg) {
            return true;
        }
        return false;
    }
}
