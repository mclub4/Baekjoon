import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        double a = Double.parseDouble(st.nextToken());
        double b = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());
        double d = Double.parseDouble(st.nextToken());

        double s = (a+b+c)/2;
        double width = Math.sqrt(s*(s-a)*(s-b)*(s-c));

        double r = (2*width)/(a+b+c);
        double width2 = (width*Math.pow(r-d,2))/Math.pow(r,2);

        String answer = String.format("%.5f", width-width2);

        System.out.println(answer);
    }
}