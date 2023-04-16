import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static int priority(char st){
        if(st == '*' || st == '/') return 2;
        else if(st == '+' || st == '-') return 1;
        else return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String n = br.readLine();
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n.length(); i++){
            char cur = n.charAt(i);

            switch (cur){
                case '+':
                case '-':
                case '*':
                case '/':
                    while(!stack.isEmpty() && priority(stack.peek()) >= priority(cur)){
                        sb.append(stack.pop());
                    }
                    stack.add(cur);
                    break;
                case '(':
                    stack.add(cur);
                    break;
                case ')':
                    while(stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(cur);
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}