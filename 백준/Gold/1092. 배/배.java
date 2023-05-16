import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        ArrayList<Integer> crane = new ArrayList<>();
        for(int i = 0; i<n; i++){
            crane.add(Integer.parseInt(st.nextToken()));
        }


        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        ArrayList<Integer> box = new ArrayList<>();
        for(int i = 0; i<m; i++){
            box.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crane, Collections.reverseOrder());
        Collections.sort(box, Collections.reverseOrder());

        int day = 0;
        if(box.get(0)>crane.get(0)){
            day = -1;
        }
        else{
            while(!box.isEmpty()){
                int crane_idx = 0;
                int box_idx = 0;
                while(crane_idx<n && box_idx<box.size()){
                    if(box_idx>=box.size()) break;
                    int crane_weight = crane.get(crane_idx);
                    int box_weight = box.get(box_idx);
                    if(crane_weight>=box_weight){
                        box.remove(box_idx);
                        crane_idx ++;
                    }
                    else{
                        box_idx++;
                    }
                }

                day ++;
            }
        }

        System.out.println(day);
    }
}
