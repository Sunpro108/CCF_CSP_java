import java.util.Scanner;
public class Main {

	/**
	 *���ͣ�����̰���㷨��˼·��ʣ�µ�Ǯ���������Żݵ��ײ���
	 *@parameter money ʣ�µ���Ǯ��
	 *@parameter numBou �Ѿ���Ľ���ƿ��
	 */
	private static int buy(int money,int numBou){
		if (money >= 50){ //��ʣ���Ǯ����50Ԫʱ������5��2��㣬��50Ԫ��7ƿ��
			numBou += 7;
			money -= 50;
		} else if (money >= 30){ //��ʣ���Ǯ����50Ԫ�ֳ���30Ԫʱ������3��1��㣬��30Ԫ��4ƿ��
			numBou += 4;
			money -= 30;
			//System.out.println("30:" + numBou + ",money:" + money);
		} else{ //ʣ��Ǯ����30Ԫ������ƿ�㼸ƿ�ˡ���
			numBou += money / 10;
			money = 0;
			//System.out.println("20:" + numBou + ",money:" + money);
			return numBou;
		}
		System.out.println("10:" + numBou + ",money:" + money);
		 //��������ʣ�µ�Ǯ���ꡣ
		return buy(money,numBou);
	}
	
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
        //������Ǯ��
        int N = cin.nextInt();
		//����Ǯ
		int numBottle = buy(N,0);
		System.out.println(numBottle + "");
	}
	
	
	
}
