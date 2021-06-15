package search;

//œﬂ–‘≤È’“
public class SeqSearch {
	public static void main(String[] args) {
		int array[] = {1,9,11,-1,34,89};
		System.out.print(Seq(array,-1));
	}
	
	public static int Seq(int[] array, int value) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == value)
				return i;
		}
		return -1;
	}
}