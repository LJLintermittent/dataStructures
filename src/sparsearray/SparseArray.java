package sparsearray;

import javax.lang.model.element.NestingKind;
import java.io.*;
import java.util.logging.FileHandler;

/**
 *                                                                         稀疏数组
 * 原理：如果一个二维数组中，有效值占少数，0值占大多数，可以使用稀疏数组来减少内存使用。
 *      1。数组到稀疏数组的转化
 *          稀疏数组一般是3列 有效数组+1 行，其中第0行表示二维数组行列个数 和 有效值个数
 *          SparseArray[0][0] -> 原二维数组行数
 *          SparseArray[0][1] -> 原二维数组列数
 *          SparseArray[0][2] -> 原二维数组有效值个数
 *
 *          SparseArray[1][0] -> 原二维数组第一个有效数值行
 *          SparseArray[1][1] -> 原二维数组第一个有效数值列
 *          SparseArray[1][2] -> 原二维数组第一个有效数值 值
 *          第二个有效数值行、列、值。。。
 *
 *      2。稀疏数组到二维数组到转化
 *         根据SparseArray[0][0] [0][1] -> 生成原二维数组， 根据 [0][2]得值有效值个数
 *         根据SparseArray[1][0] [1][1] -> 定位原二维数组第一个有效值的位置，[1][2]确定原二维数组第一个有效数值的值
 *         ....
 *
 */

public class SparseArray {
    public static void main(String[] args) throws IOException {
        //创建原始二维数组 11*11
        //0:表示没有子， 1： 表示黑子， 2：表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        printArray(chessArr1);
        //遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非零的值存放到稀疏数组中
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
        //输出稀疏数组
        System.out.printf("得到的稀疏数组为：\n");
        printArray(sparseArr);
        String fileName = "sparseArr.data";
        saveArrayToDisk(sparseArr, fileName); //存到磁盘中
        int[][] arrayFromDisk = getArrayFromDisk(fileName); //从磁盘中读取
        System.out.printf("从磁盘中读取的稀疏数组为：\n");
        printArray(arrayFromDisk);

        //将稀疏数组转换为二维数组
        System.out.printf("转换后的二维数组为:\n");
        printArray(sparseToNormalArray(arrayFromDisk));

    }

    /**
     * 打印二维数组
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
     * 将稀疏数组解析到正常的二维数组
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
     * 将二维数组存入磁盘,返回磁盘路径名称
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
     * 镜稀疏数组从磁盘中读取到内存中
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
