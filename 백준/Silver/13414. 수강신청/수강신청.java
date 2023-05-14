import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        LinkedHashSet<String> list = new LinkedHashSet<>();

        for(int i = 0; i<l; i++){
            String student = br.readLine();
            if(list.contains(student)) list.remove(student);
            list.add(student);
        }

        StringBuilder sb = new StringBuilder();
        for(String set:list){
            if(k<=0) break;
            sb.append(set+"\n");
            k--;
        }

        System.out.println(sb);
    }
}
