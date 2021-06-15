package sparsearray;

import javax.lang.model.element.NestingKind;
import java.io.*;
import java.util.logging.FileHandler;

/**
 *                                                                         ϡ������
 * ԭ�����һ����ά�����У���Чֵռ������0ֵռ�����������ʹ��ϡ�������������ڴ�ʹ�á�
 *      1�����鵽ϡ�������ת��
 *          ϡ������һ����3�� ��Ч����+1 �У����е�0�б�ʾ��ά�������и��� �� ��Чֵ����
 *          SparseArray[0][0] -> ԭ��ά��������
 *          SparseArray[0][1] -> ԭ��ά��������
 *          SparseArray[0][2] -> ԭ��ά������Чֵ����
 *
 *          SparseArray[1][0] -> ԭ��ά�����һ����Ч��ֵ��
 *          SparseArray[1][1] -> ԭ��ά�����һ����Ч��ֵ��
 *          SparseArray[1][2] -> ԭ��ά�����һ����Ч��ֵ ֵ
 *          �ڶ�����Ч��ֵ�С��С�ֵ������
 *
 *      2��ϡ�����鵽��ά���鵽ת��
 *         ����SparseArray[0][0] [0][1] -> ����ԭ��ά���飬 ���� [0][2]��ֵ��Чֵ����
 *         ����SparseArray[1][0] [1][1] -> ��λԭ��ά�����һ����Чֵ��λ�ã�[1][2]ȷ��ԭ��ά�����һ����Ч��ֵ��ֵ
 *         ....
 *
 */

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //����ԭʼ��ά���� 11*11
        //0:��ʾû���ӣ� 1�� ��ʾ���ӣ� 2����ʾ����
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        printArray(chessArr1);
        //������ά���飬�õ���0���ݵĸ���
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //������Ӧ��ϡ������
        int sparseArr[][] = new int[sum+1][3];
        //��ϡ�����鸳ֵ
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //������ά���飬�������ֵ��ŵ�ϡ��������
        int k = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    ++k;
                    sparseArr[k][0] = i;
                    sparseArr[k][1] = j;
                    sparseArr[k][2] = chessArr1[i][j];
                }
            }
        }
        //���ϡ������
        System.out.printf("�õ���ϡ������Ϊ��\n");
        printArray(sparseArr);
        String fileName = "sparseArr.data";
        saveArrayToDisk(sparseArr, fileName); //�浽������
        int[][] arrayFromDisk = getArrayFromDisk(fileName); //�Ӵ����ж�ȡ
        System.out.printf("�Ӵ����ж�ȡ��ϡ������Ϊ��\n");
        printArray(arrayFromDisk);

        //��ϡ������ת��Ϊ��ά����
        System.out.printf("ת����Ķ�ά����Ϊ:\n");
        printArray(sparseToNormalArray(arrayFromDisk));

    }

    /**
     * ��ӡ��ά����
     * @param chessArr1
     */
    public static void printArray(int chessArr1[][] ) {
        for (int[] row: chessArr1 ) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * ��ϡ����������������Ķ�ά����
     * @param sparseA
     * @return
     */
    public static int[][] sparseToNormalArray(int sparseA[][]) {
        int[][] normalA = new int[sparseA[0][0]][sparseA[0][1]];
        //int valueNum = sparseA[0][2];
        for (int i = 1; i <= sparseA.length-1; i++) {
            normalA[sparseA[i][0]][sparseA[i][1]] = sparseA[i][2];
        }

        return normalA;
    }

    /**
     * ����ά����������,���ش���·������
     * @param array
     * @throws IOException
     */
    public static void saveArrayToDisk(int array[][], String fileName) throws IOException {
        OutputStream os = new FileOutputStream(fileName);
        PrintStream ps = new PrintStream(os);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < 3; j++) {
                ps.print(array[i][j]+",");
            }
            ps.println();
        }

        ps.close();
        os.close();
    }

    /**
     * ��ϡ������Ӵ����ж�ȡ���ڴ���
     * @param fileName
     * @return
     * @throws IOException
     */
    public static int[][] getArrayFromDisk(String fileName) throws IOException {
        String[] arr = new String[1024];
        BufferedReader bReader = new BufferedReader(new FileReader(fileName));
        String line = null;
        int number = 0;
        while ( (line = bReader.readLine()) != null) {
            arr[number] = line;
            number++;
        }

        int[][] sparserArr = new int[number][3];
        for (int i = 0; i < number; i++) {
            String[] split = arr[i].split(",");
            for (int j = 0; j < split.length; j++) {
                sparserArr[i][j] = Integer.parseInt(split[j]);
            }
        }

        return sparserArr;
    }

}
