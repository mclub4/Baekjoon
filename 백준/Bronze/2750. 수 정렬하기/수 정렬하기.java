import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer> array = new ArrayList<>(n);
        for(int i = 0; i<n; i++){
            array.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(array);

        for(int i = 0; i<n; i++){
            System.out.println(array.get(i));
        }

    }

}