package java8;

/**
 * @author missb
 */
public class T1  {
    final static int MAX = 100;

    static void reverse(int[] arr){
        int hi = arr.length - 1;
        int mid = hi >> 1;

        for(int i = 0; i < mid; i++){
           int temp = arr[i];
           arr[i] = arr[hi - i];
           arr[hi - i] = temp;
        }
    }


    public static void main(String[] args) {
        String str = Integer.toHexString(70);
        System.out.println(str);
    }
}
