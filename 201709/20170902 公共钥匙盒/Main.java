/**����Կ�׺�����**����Ĺؼ�������ʽ��������������⡣һ��Ҫ�����θ���Щ���ܵĲ�������������Ϊ��Щ���������Ⱥ����ġ���������ú��ʵ����ݽṹ�����������뵽����Ȼ����ƺ��ʵ����ݽṹ���������ͺܼ��ˡ�
 * ����ʦ��ȡԿ�׵�״̬�����һ���࣬���� ʱ��/������ȡԿ�׻��߷�Կ�ף�/Կ�ױ���������ԡ�
 * ���������������򣬶�Կ�׺е�״̬���θ��¼��ɡ�
 **/
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class Main{
	public static void main(String args[]){
		Scanner cin = new Scanner(System.in);
		//Կ������
		int keyTotal = cin.nextInt();
		//��ʦ����
		int teacherTotal = cin.nextInt();
		//����״̬����
		List<TeacherKey> action = new ArrayList<TeacherKey>();
		for (int i = 0 ; i < teacherTotal; i++) {
			int keyNum = cin.nextInt();
			int timeStart = cin.nextInt();
			int timeEnd = cin.nextInt();
			//TeacherKey tk1 = new TeacherKey(timeStart, 1, keyNum);
			//TeacherKey tk2 = new TeacherKey(timeStart+timeEnd , -1, keyNum);
			action.add(new TeacherKey(timeStart, 1, keyNum));
			action.add(new TeacherKey(timeStart+timeEnd , -1, keyNum));
		}
		
		//�鿴��ŵ����
		//printList(action);
		//����
		Collections.sort(action); 
		//�鿴���������
		//printList(action);
		
		//����Կ�׺�״̬,-1Ϊû��Կ�ף�����0���Ƕ�Ӧ��Կ�ױ��
		int[] keyState = new int[keyTotal];
		//��ʼ��״̬����: 1,2,...,N;
		for(int i = 0; i<keyTotal; )
			keyState[i] = ++i;
		//action ������Կ�׺�״̬����
		for (TeacherKey tk : action) {
			//System.out.println("fangqu:" + tk);
			if (tk.getTake() == 1){//take�õ���Ŷ�Ӧ��Կ�׺󣬸�λ����-1
				for(int i = 0 ;i < keyTotal; i++) {
					if(keyState[i] == tk.getKeyNum()){
						keyState[i] = -1;
						break;
					}
				}
			}else {//��Կ�׵�ʱ�򣬴�С������ŷ�
				for(int i =0; i < keyTotal; i++){
					if (keyState[i] == -1){
						keyState[i] = tk.getKeyNum();
						break;
					}
				}
			}
		}
		//������
		for (int i = 0; i < keyTotal; i++){
			System.out.print(keyState[i] + " ");
		}
		
	}
	
	/**
	 * ���List����
	 **/
	private static void printList(List<TeacherKey> list){
		for(TeacherKey tk : list) {
			System.out.println(tk);
		}
	}
	
}

/**
 * ����ʦ��ȡԿ�׵�״̬�����һ���࣬���� ʱ��/������ȡԿ�׻��߷�Կ�ף�/Կ�ױ���������ԡ������������򷽷�
 *ע��Comparable�ӿ�Ҫָ����������ͣ�����compareTo��������һ�£�
 */
class TeacherKey  implements Comparable<TeacherKey>{
	//������ʱ��
	private int time;
	//����״̬��ȡԿ��Ϊ1����Կ��Ϊ0
	private int take;
	//Կ�ױ��
	private int keyNum;
	public TeacherKey(){}
	public TeacherKey(int time,int take,int keyNum){
		this.time = time;
		this.take = take;
		this.keyNum = keyNum;
	}
	
	public int getTake(){
		return this.take;
	}
	public int getKeyNum(){
		return this.keyNum;
	}
	/**
	 *��д�������������
	 **/
	public int compareTo(TeacherKey tk) {  
		if (this.time != tk.time){//ʱ������
			//System.out.println("ʱ������");
			return this.time>tk.time ? 1:-1;
		}else{//ʱ����ͬʱ����ȡ���
			if (this.take != tk.take){
				//System.out.println("ʱ����ͬʱ����ȡ���");
				return this.take > tk.take ? 1:-1;
			}else { //ʱ��״̬����ͬ�����С�����ȣ�
				//System.out.println("ʱ��״̬����ͬ�����С�����ȣ�");
				return this.keyNum >= tk.keyNum ? 1:-1;
			}
		}
	}
	
	/**
	 * ��д���������Ϊ�ַ����ĸ�ʽ��
	 **/
	 public String toString(){
		 return this.time + "," + this.take + "," + this.keyNum + ";";
	 }
	
}