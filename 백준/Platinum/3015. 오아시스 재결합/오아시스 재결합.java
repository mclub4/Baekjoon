import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Node{
        int cur;
        long count;

        public Node(int cur, long count){
            this.cur = cur;
            this.count = count;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long answer = 0;
        Stack<Node> stack = new Stack<>();
        for(int i = 0; i<n; i++){
            int m = Integer.parseInt(br.readLine());
            Node cur = new Node(m, 1);
            while(!stack.isEmpty() && stack.peek().cur<=m){
                Node prev = stack.pop();
                answer += prev.count;
                if(prev.cur == m){
                     cur.count += prev.count;
                }
            }

            if(!stack.isEmpty()){
                answer ++;
            }

            stack.push(cur);
        }

        System.out.println(answer);
    }
}