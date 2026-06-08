import java.io.*;
import java.util.*;

public class Main {
  public static ArrayList<Integer>[] lines;
  public static boolean[] visited;

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    // ( 1 ≤ N(점개수) ≤ 1,000  //  0 ≤ M(선개수) ≤ N×(N-1)/2 )
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    // 연결요소 (연결된 그룹의 수)
    int answer = 0;

    // 숫자를 편하게 넣기위해 [0] 사용안함
    lines = new ArrayList[N+1];
    visited = new boolean[N+1];
    
    // [0]제외, 점 개수만큼 리스트 생성
    for (int i=1; i<=N; i++) {
      lines[i] = new ArrayList<Integer>();
    }

    // ( 1 ≤ u,v ≤ N  //  u ≠ v )
    // list 저장(!양방향으로 모두 저장)
    for (int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());
      lines[u].add(v);
      lines[v].add(u);
    }

    // dfs실행
    for (int i=1; i<=N; i++) {
      if (!visited[i]) { // <ㄱ 그래서 이 조건을 꼭 넣어야 재탐색 안함.
        dfs(i); // 여기서 i와 연관된 끝까지 찍고옴.
        answer++;
      }
    }

    System.out.println(answer);
  }

  // dfs-먼저 끝까지 찍고오기(깊이우선)
  public static void dfs(int num) {
    if (visited[num]) return;
    visited[num] = true;

    for (int i : lines[num]) {
      if (visited[i]) continue;
      dfs(i);
    }
  }
}