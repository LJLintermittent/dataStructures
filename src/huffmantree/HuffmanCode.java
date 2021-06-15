package huffmantree;
/*
 * 首先将字符串转成字节数组
 * 然后将字节数组转成Node你结点，node中data代表 ASCII  weight表示出现的次数
 * 构建赫夫曼树
 * 同上篇所讲，不同的是新结点的data为null 注意新加结点的左右子结点分别是原来的
 * 赫夫曼编码  如果是非叶子结点 则找它的左右子树，并将拼接字符传入，如果是叶子结点，将其加入到赫夫曼编码中
 * 
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class HuffmanCode {

	public static void main(String[] args) {

		String content = "i like like like java do you like a java";
		byte[] contentBytes = content.getBytes();
		// System.out.println(contentBytes.length); //40
		// List<Node> nodes = getNodes(contentBytes);
		// System.out.println("nodes=" + nodes);
		// //赫夫曼树
		// System.out.println("赫夫曼树");
		//
		// Node root = createHuffmanTree(nodes);
		// preOrder(root);
		// //root.preOrder();
		//
		// getCodes(root,"",stringBuilder);
		// System.out.println("生成的赫夫曼编码表："+ huffmanCodes);
		// //System.out.println("nodes=" + nodes.toString());
		//
		// byte[] huffmanCodeBytes = zip(contentBytes,huffmanCodes);
		// System.out.println("huffmanCodes=" + Arrays.toString(huffmanCodeBytes));

		byte[] huffmanCodeBytes = huffmanZip(contentBytes);
		// System.out.println("huffmanCodes=" + Arrays.toString(huffmanCodeBytes));

		byte[] sourseBytes = decode(huffmanCodes, huffmanCodeBytes);
		// System.out.println("原来的字符串是：" + new String(sourseBytes));
		// 666,如果用Arrays.toString(sourseBytes)输出，则输出的是ASCII码 所以要使用new
		// String(sourseBytes)进行输出

		String srcFile = "d://java.docx";
		String dstFile = "d://TEST.zip";
		zipFile(srcFile, dstFile);
		System.out.println("压缩文件OK");

		String zipFile = "d://TEST.zip";
		String dstFile1 = "d://java.docx";
		unZipFile(zipFile, dstFile1);
		System.out.println("解压成功");

	}

	// 文件进行解压
	public static void unZipFile(String zipFile, String dstFile) {
		// 定义文件输入流
		InputStream is = null;
		;
		// 定义对象输入流
		ObjectInputStream ois = null;
		// 定义文件的输出流
		OutputStream os = null;
		try {
			// 创建文件输入流
			is = new FileInputStream(zipFile);
			// 创建一个和is相关联的对象输入流
			ois = new ObjectInputStream(is);
			// 读取byte数组huffmanBytes
			byte[] huffmanBytes = (byte[]) ois.readObject();
			// 读取赫夫曼编码表
			@SuppressWarnings("unchecked")
			Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();
			// 解码
			byte[] bytes = decode(huffmanCodes, huffmanBytes);
			// 将bytes数组写入到募兵文件
			os = new FileOutputStream(dstFile);
			os.write(bytes);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				ois.close();
				is.close();
				os.close();

			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	// 将文件进行压缩的方法
	public static void zipFile(String srcFile, String dstFile) {
		// 创建输出流
		FileInputStream is = null;
		FileOutputStream os = null;
		ObjectOutputStream oos = null;
		try {
			// 创建文件的输入流
			is = new FileInputStream(srcFile);
			// 创建一个和源文件大小一样的byte[]
			byte[] b = new byte[is.available()];
			is.read(b);
			byte[] huffmanBytes = huffmanZip(b);
			// 创建文件的输出流，存放压缩文件
			os = new FileOutputStream(dstFile);
			// 创建一个和文件输出流关联的ObjectOutputStream
			oos = new ObjectOutputStream(os);
			oos.write(huffmanBytes);// 把荷夫曼编码后的字节数组写入压缩文件
			// 这里以对象流的方式写入赫夫曼编码，是为了我们以后恢复文件时使用
			// 注意一定要把荷夫曼编码写入压缩文件
			oos.writeObject(huffmanCodes);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				is.close();
				os.close();

				oos.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
	}

	/*
	 * 将一个byte转成一个二进制的字符串
	 */

	private static String byteToBitString(boolean flag, byte b) {
		// 使用变量保存b
		int temp = b; // 将b转成int
		if (flag) {
			temp |= 256; // 还是没有搞明白这一点，得去复习计组
		}
		String str = Integer.toBinaryString(temp);
		if (flag) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}

	private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
		// 1.先得到huffmanBytes 对应的二进制的字符串
		StringBuilder stringBuilder = new StringBuilder();
		// 将byte数组转成二进制的字符串
		for (int i = 0; i < huffmanBytes.length; i++) {
			byte b = huffmanBytes[i];
			// 判断最后一个字节
			boolean flag = (i == huffmanBytes.length - 1);
			stringBuilder.append(byteToBitString(!flag, b));
		}

		// 把字符串按照指定的赫夫曼编码进行编码
		Map<String, Byte> map = new HashMap<String, Byte>();
		for (Map.Entry<Byte, String> enrty : huffmanCodes.entrySet()) {
			map.put(enrty.getValue(), enrty.getKey());
		}
		// System.out.println("map=" + map);

		List<Byte> list = new ArrayList<>();
		// i 可以理解成是索引，扫描stringBuilder
		for (int i = 0; i < stringBuilder.length();) {
			int count = 1; // 计数器
			boolean flag = true;
			Byte b = null;

			while (flag) {
				String key = stringBuilder.substring(i, i + count);
				b = map.get(key);
				if (b == null) {
					// 说明没有匹配到
					count++;
				} else {
					flag = false;
				}

			}
			list.add(b);
			i += count; // 将i移动到新的位置
		}

		byte b[] = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}

	private static byte[] huffmanZip(byte[] bytes) {
		List<Node> nodes = getNodes(bytes);

		Node huffmanTreeRoot = createHuffmanTree(nodes);

		getCodes(huffmanTreeRoot, "", stringBuilder);
		byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
		return huffmanCodeBytes;

	}

	// 编写一个方法，将字符串对应的byte[]数组，通过生成的荷夫曼编码表，返回一个赫夫曼编码压缩后的byte[]
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCode) {
		// 利用huffmanCode将bytes转成荷夫曼编码对应的字符串
		StringBuilder stringBuilder = new StringBuilder();
		for (byte b : bytes) {
			stringBuilder.append(huffmanCodes.get(b));
		}
		int length;
		if (stringBuilder.length() % 8 == 0) {
			length = stringBuilder.length() / 8;
		} else {
			length = stringBuilder.length() / 8 + 1;
		}

		// 创建 存储压缩后的byte数组
		byte[] huffmanCodeBytes = new byte[length];
		int index = 0;
		for (int i = 0; i < stringBuilder.length(); i += 8) {
			String strByte;
			if (i + 8 > stringBuilder.length()) {
				strByte = stringBuilder.substring(i);
			} else {
				strByte = stringBuilder.substring(i, i + 8);
			}
			huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte, 2);
		}
		return huffmanCodeBytes;
	}

	static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
	static StringBuilder stringBuilder = new StringBuilder();

	// 为了调用方便，重载getCodes
	private static Map<Byte, String> getCodes(Node root) {
		if (root == null) {
			return null;
		}
		// 处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		getCodes(root.right, "1", stringBuilder);
		return huffmanCodes;
	}

	/*
	 * 将传入的node结点的所有叶子结点的赫夫曼编码得到，并传入huffmanCode集合 node 传入结点 code 路径 左0右1
	 */
	private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(code);
		if (node != null) {
			// 如果node == null 不处理
			// 判断当前Node为叶子结点还是非叶子结点
			if (node.data == null) {
				// 非叶子结点
				// 递归处理
				getCodes(node.left, "0", stringBuilder2);
				getCodes(node.right, "1", stringBuilder2);

			} else {
				// 这是叶子结点
				huffmanCodes.put(node.data, stringBuilder2.toString());
			}
		}
	}

	// 前序遍历
	private static void preOrder(Node node) {
		if (node != null) {
			node.preOrder();

		} else {
			System.out.println("赫夫曼树为空");
		}

	}

	// 通过list创建赫夫曼树
	private static Node createHuffmanTree(List<Node> nodes) {
		while (nodes.size() > 1) {
			Collections.sort(nodes);
			Node leftNode = nodes.get(0);
			Node rightNode = nodes.get(1);
			Node parent = new Node(null, leftNode.weight + rightNode.weight);
			// 忘写了下面两句，错误
			parent.left = leftNode;
			parent.right = rightNode;
			nodes.remove(leftNode);
			nodes.remove(rightNode);
			nodes.add(parent);
		}
		return nodes.get(0);
	}

	// 接收字节数组，返回Node List
	private static List<Node> getNodes(byte[] bytes) {
		Map<Byte, Integer> counts = new HashMap<>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) {
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}

		}

		// 把每一个键值对转成一个Node对象，并加入到Node集合
		// 遍历map
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			nodes.add(new Node(entry.getKey(), entry.getValue()));
		}
		return nodes;
	}

	// 创建Node,数据和权值
	static class Node implements Comparable<Node> {
		Byte data;
		int weight;
		Node left;
		Node right;

		public Node(Byte data, int weight) {
			super();
			this.data = data;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {

			return this.weight - o.weight;
		}

		// 因为一开始少写了toString方法，输出的是地址 切记
		@Override
		public String toString() {
			return "Node [data=" + data + ", weight=" + weight + "]";
		}

		// 前序遍历
		public void preOrder() {
			System.out.println(this);
			if (this.left != null)
				this.left.preOrder();
			;
			if (this.right != null)
				this.right.preOrder();
		}

	}

}