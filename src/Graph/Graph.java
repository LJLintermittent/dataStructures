package Graph;
//*******************************************************************图的基本表示***************************************************************************
import java.util.Arrays;

public class Graph {

	private int[][] edges; // 存储图对应的邻结矩阵

	public static void main(String[] args) {
		// 测试
		int n = 5; // 结点的个数

		// 创建图对象
		Graph graph = new Graph(n);

		// 添加边
		// A-B A-C B-C B-D B-E
		graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1); // A-C
		graph.insertEdge(1, 2, 1); // B-C
		graph.insertEdge(1, 3, 1); // B-D
		graph.insertEdge(1, 4, 1); // B-E

		// 显示
		graph.showGraph();

	}

	// 构造器
	public Graph(int n) {
		// 初始化矩阵
		edges = new int[n][n];
	}

	// 显示图对应的矩阵
	public void showGraph() {
		for (int[] link : edges) {
			System.err.println(Arrays.toString(link));
		}
	}

	// 添加边
	/**
	 * @param v1
	 *            表示点的下标是第几个顶点 "A"-"B" "A"->0 "B"->1
	 * @param v2
	 *            第二个顶点对应的下标
	 * @param weight
	 *            表示连通性
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
	}
}

