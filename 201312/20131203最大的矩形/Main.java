import java.util.*;

public class Main {
	
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		int n = cin.nextInt();
		int[] num = new int[n];
		int maxH = 0;
		for(int i=0; i < n; i++) {
			num[i] = cin.nextInt();
			if(num[i] > maxH) //��¼���߶�
				maxH = num[i];
		}
		
		//�ɵ͵��߱���
		int maxArea=0;
		int maxW=0;
		for(int h = 1; h <= maxH; h++) {
			maxW = maxWeight(num, h);
			//System.out.println("maxW=" + maxW + ", h=" + h);
			if(maxW * h > maxArea){
				maxArea = maxW * h;
			}
		}
		
		System.out.println("" + maxArea);
	}
	
	/**��ָ���߶��µ�����������
	 */
	private static int maxWeight(int[] num, int h){
		int count = 0;
        int max = 0;
		for(int i = 0; i < num.length; i++){
			if(h <= num[i]){//����
				count++;
			}else{
				count = 0;
			}
			if(count > max)
				max = count;
		}
		return max;
	}
}
