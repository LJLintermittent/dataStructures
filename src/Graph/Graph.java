package Graph;
//*******************************************************************ͼ�Ļ�����ʾ***************************************************************************
import java.util.Arrays;

public class Graph {

	private int[][] edges; // �洢ͼ��Ӧ���ڽ����

	public static void main(String[] args) {
		// ����
		int n = 5; // ���ĸ���

		// ����ͼ����
		Graph graph = new Graph(n);

		// ��ӱ�
		// A-B A-C B-C B-D B-E
		graph.insertEdge(0, 1, 1); // A-B
		graph.insertEdge(0, 2, 1); // A-C
		graph.insertEdge(1, 2, 1); // B-C
		graph.insertEdge(1, 3, 1); // B-D
		graph.insertEdge(1, 4, 1); // B-E

		// ��ʾ
		graph.showGraph();

	}

	// ������
	public Graph(int n) {
		// ��ʼ������
		edges = new int[n][n];
	}

	// ��ʾͼ��Ӧ�ľ���
	public void showGraph() {
		for (int[] link : edges) {
			System.err.println(Arrays.toString(link));
		}
	}

	// ��ӱ�
	/**
	 * @param v1
	 *            ��ʾ����±��ǵڼ������� "A"-"B" "A"->0 "B"->1
	 * @param v2
	 *            �ڶ��������Ӧ���±�
	 * @param weight
	 *            ��ʾ��ͨ��
	 */
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		edges[v2][v1] = weight;
	}
}

