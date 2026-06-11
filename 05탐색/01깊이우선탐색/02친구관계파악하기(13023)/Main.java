import java.io.*;
import java.util.*;

public class Main {
    public static boolean existFlag;
    public static boolean[] visited;
    public static ArrayList<Integer>[] friends;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 친구번호 0 ~ N-1
        friends = new ArrayList[N];
        // 방문기록
        visited = new boolean[N];
        // 친구번호 초기화
        for (int i=0; i<N; i++) {
            friends[i] = new ArrayList<Integer>();
        }

        // 친구관계 정리 -> 양쪽에 다 친추
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            friends[a].add(b);
            friends[b].add(a);
        }

        // 친구관계찾기
        for (int i=0; i<N; i++) {
            if (existFlag) break;
            dfs(i, 0);
        }

        System.out.println(existFlag ? 1 : 0);
    }
    
    public static void dfs(int mainFriend, int depth) {
        if (existFlag) return;
        visited[mainFriend] = true;
        depth++;
        
        // 트래킹 on
        visited[mainFriend] = true;
        for (int friend: friends[mainFriend]) {
            if(visited[friend]) continue;

            if (depth >= 4) {
                existFlag = true;
                return;
            } else {
                dfs(friend, depth);
            }
        }
        // 다 돌아도 안끝났으면 백트래킹 초기화
        visited[mainFriend] = false;
    }
}