import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Integer>[] lineList;
    public static boolean[] visited;
    public static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        lineList = new ArrayList[N+1];

        // 초기화
        for (int i=1; i<=N; i++) {
            lineList[i] = new ArrayList<Integer>();
        }

        // 관계정리
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            lineList[a].add(b);
            lineList[b].add(a);
        }

        // 오름차순 정렬
        for (int i=1; i<=N; i++) {
            lineList[i].sort((a,b) -> a.compareTo(b));
        }

        sb = new StringBuilder();
        visited = new boolean[N+1];
        dfs(V);
        sb.setLength(sb.length() - 1); // 마지막 공백 삭제
        sb.append("\n");

        visited = new boolean[N+1];
        bfs(V);
        sb.setLength(sb.length() - 1); // 마지막 공백 삭제
        sb.append("\n");

        System.out.println(sb);
    }

    public static void dfs(int num) {
        sb.append(num).append(" ");
        visited[num] = true;

        for (int frag : lineList[num]) {
            if (visited[frag]) continue;
            dfs(frag);
            return;
        }
    }
    
    public static void bfs(int num) {
        // 첫 큐는 직접 넣어줌.
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(num);
        visited[num] = true;

        // 그 후 큐는 돌아가면서 출력
        while (!queue.isEmpty()) {
            // !! 결과 기록은 뽑아낼때 해야하고,
            int nowNum = queue.poll();
            sb.append(nowNum).append(" ");

            for (int frag : lineList[nowNum]) {
                if (visited[frag]) continue;
                // !! 방문한(할)곳은 큐를 넣을때 기록해야 대기중인 큐를 다시 안넣음
                visited[frag] = true;
                queue.offer(frag);
            }
        }
    }
}
