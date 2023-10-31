import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static class Node{
        int root;
        Node left,right;

        Node(int root){
            this.root = root;
        }

        void insert(int n){
            if(n< this.root){
                if(this.left == null) this.left = new Node(n);
                else this.left.insert(n);
            }
            else{
                if(this.right == null) this.right = new Node(n);
                else this.right.insert(n);
            }
        }
    }

    public static void postorder(Node node){
        if(node == null){
            return;
        }
        else{
            postorder(node.left);
            postorder(node.right);
            System.out.println(node.root);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node root = new Node(n);

        while(true){
            String tmp = br.readLine();
            if(tmp == null || tmp.equals("")) break;

            n = Integer.parseInt(tmp);
            root.insert(n);
        }

        postorder(root);
    }
}