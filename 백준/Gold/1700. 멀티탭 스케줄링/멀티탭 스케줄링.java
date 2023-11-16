import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] electronic = new int[k+1];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i<k+1; i++){
            electronic[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        int count = 0;

        for(int i = 1; i<k+1; i++){
            if(list.contains(electronic[i])) continue;
            if(list.size() < n){
                list.add(electronic[i]);
                continue;
            }

            PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[1] - o1[1];
                }
            });
            for(int j = 0; j<list.size(); j++){
                int[] tmp = new int[2];
                tmp[0] = list.get(j);
                tmp[1] = 101;
                int idx = -1;
                for(int s = i+1; s<k+1; s++){
                    if(electronic[s] == tmp[0]){
                        idx = s;
                        break;
                    }
                }
                if(idx != -1) tmp[1] = idx;
                queue.add(tmp);
            }

//            System.out.println(queue.peek()[0] + " : " + queue.peek()[1]);

            list.remove(list.indexOf(queue.poll()[0]));
            list.add(electronic[i]);
            count ++;
        }

        System.out.println(count);
    }
}