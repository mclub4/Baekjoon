import java.util.*;

class Solution {
    static int maxSheep = 0;
    static List<Integer>[] graph;

    public int solution(int[] info, int[][] edges) {
        int n = info.length;
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0); // 시작 루트 노드

        dfs(info, 0, 0, 0, nextNodes);
        return maxSheep;
    }

    private void dfs(int[] info, int cur, int sheep, int wolf, List<Integer> next) {
        if (info[cur] == 0) sheep++;
        else wolf++;

        if (wolf >= sheep) return;

        maxSheep = Math.max(maxSheep, sheep);

        List<Integer> nextCandidates = new ArrayList<>(next);
        nextCandidates.remove(Integer.valueOf(cur));
        nextCandidates.addAll(graph[cur]);

        for (int nextNode : nextCandidates) {
            dfs(info, nextNode, sheep, wolf, nextCandidates);
        }
    }
}
