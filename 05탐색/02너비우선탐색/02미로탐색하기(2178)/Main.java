import java.io.*;
import java.util.*;

public class Main {
    public static boolean[][] visited;
    public static boolean[][] map;
    public static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 이동 가능한 곳 : x(1~M), y(1~N)

        visited = new boolean[M+1][N+1];
        map = new boolean[M+1][N+1];

        for (int y=1; y<=N; y++) {
            String[] mapRow = br.readLine().split(""); //["1","0",...]
            for (int x=1; x<=M; x++) {
                map[x][y] = mapRow[x-1].equals("1") ? true : false;
            }
        }
        
        int goal = bfs();

        System.out.println(goal);
    }

    public static int bfs() {
        Queue<int[]> queue = new LinkedList<int[]>();

        // nowX, nowY, nowStep
        queue.offer(new int[] {1,1,1});
        visited[1][1] = true;

        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            int nowX = xy[0];
            int nowY = xy[1];
            int nowStep = xy[2];

            if (nowX == M && nowY == N) {
                return nowStep;
            }

            nowStep++;

            // nowX, nowY의 동서남북(안간곳, 갈수있는곳) + nowStep을 모두 queue에 집어넣음
            if (nowX < M && !visited[nowX+1][nowY] && map[nowX+1][nowY]) { // 동
                visited[nowX+1][nowY] = true;
                queue.offer(new int[] {nowX+1,nowY,nowStep});
            }
            if (nowX > 1 && !visited[nowX-1][nowY] && map[nowX-1][nowY]) { // 서
                visited[nowX-1][nowY] = true;
                queue.offer(new int[] {nowX-1,nowY,nowStep});
            }
            if (nowY > 1 && !visited[nowX][nowY-1] && map[nowX][nowY-1]) { // 남
                visited[nowX][nowY-1] = true;
                queue.offer(new int[] {nowX,nowY-1,nowStep});
            }
            if (nowY < N && !visited[nowX][nowY+1] && map[nowX][nowY+1]) { // 북
                visited[nowX][nowY+1] = true;
                queue.offer(new int[] {nowX,nowY+1,nowStep});
            }
        }

        return 0;
    }
}
