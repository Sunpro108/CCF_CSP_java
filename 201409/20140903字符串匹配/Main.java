import java.util.*;

public class Main {
	
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		String S = cin.next();
		int flag = cin.nextInt(); //������Ϊ0ʱ��ʾ��Сд�����У�������Ϊ1ʱ��ʾ��Сд���С�
		int n = cin.nextInt();
		String[] strs = new String[n];
		for(int i = 0; i<n; i++) {
			strs[i] = cin.next();
		}
		//������ɣ�
		//String SPrint = S;
		String[] strsPrint = new String[n];
		
		for(int i = 0; i<n; i++) {
			strsPrint[i] = strs[i];
		}
		
		//�Ƿ���Դ�Сд�����ԵĻ���ȫ��ת��ΪСд�ַ�
		if(flag == 0){
			S = S.toLowerCase();
			for(int i = 0; i<n; i++) {
				strs[i] = strs[i].toLowerCase();
			}
		}
		
		//���жԱ�
		//char[] charS = S.toCharArray();
		int lenS = S.length();
		for(int j = 0; j<strs.length; j++) {
			//char[] charArrStr = str.toCharArray();
			String str = strs[j];
			int lenStr = str.length();
			for(int i = 0; i < lenStr; i++) {
				if(str.charAt(i) == S.charAt(0) &&  (lenStr - i) >= lenS 
					&& str.substring(i,i+lenS).equals(S)){ //��һ���ַ�����
					System.out.println(strsPrint[j]);
					break;
				}
			}
		}
			
	}
}
