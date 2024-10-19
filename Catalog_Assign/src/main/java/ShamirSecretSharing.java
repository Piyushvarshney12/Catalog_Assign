import org.json.JSONObject;
import java.math.BigInteger;

public class ShamirSecretSharing {

    // Large range interpolation function to find f(x)
    public static double lagrangeInterpolation(double[][] points, double x) {
        double totalSum = 0;
        int k = points.length;

        for (int i = 0; i < k; i++) {
            double xi = points[i][0];
            double yi = points[i][1];
            double term = yi;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    double xj = points[j][0];
                    term *= (x - xj) / (xi - xj);
                }
            }
            totalSum += term;
        }

        return totalSum;
    }

    // Function to parse the JSON input and extract the points
    public static double[][] parseInput(String jsonString) {
        JSONObject json = new JSONObject(jsonString);
        JSONObject keys = json.getJSONObject("keys");

        int k = keys.getInt("k");

        double[][] points = new double[k][2];

        for (int i = 1; i <= k; i++) {
            JSONObject point = json.getJSONObject(String.valueOf(i));
            int base = Integer.parseInt(point.getString("base"));
            String value = point.getString("value");

            double x = i;  // x is the index of the point
            BigInteger bigIntValue = new BigInteger(value, base);  // Convert value from the given base to base 10
            double y = bigIntValue.doubleValue();

            points[i - 1][0] = x;
            points[i - 1][1] = y;
        }

        return points;
    }

    public static void main(String[] args) {
    	
    	String jsonInput1 = """
    	        {
    	            "keys": {
    	                "n": 4,
    	                "k": 3
    	            },
    	            "1": {
    	                "base": "10",
    	                "value": "4"
    	            },
    	            "2": {
    	                "base": "2",
    	                "value": "111"
    	            },
    	            "3": {
    	                "base": "10",
    	                "value": "12"
    	            },
    	            "6": {
    	                "base": "4",
    	                "value": "213"
    	            }
    	        }
    	        """;
        // Sample input in JSON format for test case 2
        String jsonInput2 = """
        {
            "keys": {
                "n": 10,
                "k": 7
            },
            "1": {
                "base": "6",
                "value": "13444211440455345511"
            },
            "2": {
                "base": "15",
                "value": "aed7015a346d63"
            },
            "3": {
                "base": "15",
                "value": "6aeeb69631c227c"
            },
            "4": {
                "base": "16",
                "value": "e1b5e05623d881f"
            },
            "5": {
                "base": "8",
                "value": "316034514573652620673"
            },
            "6": {
                "base": "3",
                "value": "2122212201122002221120200210011020220200"
            },
            "7": {
                "base": "3",
                "value": "20120221122211000100210021102001201112121"
            },
            "8": {
                "base": "6",
                "value": "20220554335330240002224253"
            },
            "9": {
                "base": "12",
                "value": "45153788322a1255483"
            },
            "10": {
                "base": "7",
                "value": "1101613130313526312514143"
            }
        }
        """;

        // Parse the input
        double[][] points1 = parseInput(jsonInput1);
        double[][] points2 = parseInput(jsonInput2);

        // Find the constant term c (f(0))
        double constantTerm1 = lagrangeInterpolation(points1, 0);
        double constantTerm2 = lagrangeInterpolation(points2, 0);
        System.out.println("The constant term c is: " + constantTerm1);
        System.out.println("The constant term c is: " + constantTerm2);
    }
}