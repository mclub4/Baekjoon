import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Emotion{
        int emotion;
        int time;
        int clipboard;

        public Emotion(int emotion, int time, int clipboard){
            this.emotion = emotion;
            this.time = time;
            this.clipboard = clipboard;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
        System.out.println(bfs(s));
    }

    public static int bfs(int s){
        Queue<Emotion> queue = new LinkedList<>();
        //Emotion(현재 이모티콘 개수, 현재 시간, 클립보드에 있는 이모티콘 개수)
        queue.add(new Emotion(1,0,0));

        //visited[현재 이모티콘 개수][작업한 후 이모티콘 개수][작업의 종류(0:복사, 1:붙어넣기, 2:하나 빼기)]
        boolean[][][] visited = new boolean[1002][1002][3];

        while(!queue.isEmpty()){
            Emotion emotion = queue.poll();
            int time = emotion.time;
            int emoticon = emotion.emotion;
            int clipboard = emotion.clipboard;

            //우리가 원하는 목표에 도달하면 시간 리턴
            if(emoticon == s) return time;

            //현재 이모티콘 복사하기
            if(!visited[emoticon][emoticon][0]){
                visited[emoticon][emoticon][0] = true;
                queue.add(new Emotion(emoticon, time+1, emoticon));
            }
            //현재 이모티콘에 붙어넣기
            if(emoticon+clipboard<1002){
                if(clipboard != 0 && !visited[emoticon][emoticon+clipboard][1]){
                    visited[emoticon][emoticon+clipboard][1] = true;
                    queue.add(new Emotion(emoticon+clipboard, time+1, clipboard));
                }
            }
            //이모티콘 하나 빼기
            if(emoticon != 0 && !visited[emoticon][emoticon-1][2]){
                visited[emoticon][emoticon-1][2] = true;
                queue.add(new Emotion(emoticon-1, time+1, clipboard));
            }
        }

        return -1;
    }

}
