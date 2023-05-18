import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] building = new long[n];

        for(int i = 0; i<n; i++){
            building[i] = Long.parseLong(br.readLine());
        }

        Stack<Long> stack = new Stack<>();
//        stack.push(building[0]);

        long result = 0;

        for(int i = 0; i<n; i++){
            while(!stack.isEmpty() && stack.peek() <= building[i]){
                stack.pop();
                result += stack.size();
            }
            stack.push(building[i]);
        }
        while(!stack.isEmpty()){
            stack.pop();
            result += stack.size();
        }

        System.out.println(result);
    }
}
