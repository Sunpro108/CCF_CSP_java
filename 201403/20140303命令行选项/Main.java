import java.util.*;

public class Main {
	
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		String formStr = cin.next();//�����ʽ�ַ���
		int N = cin.nextInt();//���������еĸ���
		cin.nextLine();
		String[] commondStrs = new String[N]; //ֻ�Ǵ������ַ������飻������ַ�������û�д�����
		for(int i=0; i<N; i++) {
			commondStrs[i] = cin.nextLine();
		}
		//�������
		
		//������ʽ�ַ����� listOptions
		List<Option> listOptions = new ArrayList<Option>();
		int lenFormStr = formStr.length();
		for(int i=0; i<lenFormStr; i++) {
			//����ǲ������һ���ַ�����ֹIndex���
			if(i+1 >= lenFormStr){ //���ַ����Ǵ���ѡ��
				listOptions.add(new Option(formStr.charAt(i),false));
				break;
			}
			if(formStr.charAt(i+1) == ':'){//����ǰ�ַ��������":"��˵���Ǵ���ѡ��
				listOptions.add(new Option(formStr.charAt(i),true));
				i++;//��ָ���1��Խ��":",ָ����һ���ַ�
			}else{
				listOptions.add(new Option(formStr.charAt(i),false));
			}	
		}
		
		//���н����������ַ���
		List<Option> listOpOut;
		int i = 0;
		for(String commondLine : commondStrs){
			//��ʼ��������ѡ��List
			listOpOut = new ArrayList<Option>();
			//�ո�ָ�
			String[] opsStr = commondLine.split(" ");
			//����opsStr�ĳ��ȣ��ж�����ѡ�����Ϊ1Ϊ��ѡ�>1Ϊ��ѡ��
			if(opsStr.length == 1){
				System.out.println("Case " + (++i) + ": ");
				continue;
			}
			
			String argStr = "";
			for(int j = 1; j < opsStr.length; j++){
				
				//�ж�ѡ�����Ƿ�Ϸ���
				Option op = islegalOption(listOptions, opsStr[j]);
				if(op == null)//����nullΪ���Ϸ���
					break;
					
				//�ж��Ƿ��Ǵ���ѡ��
				if(!op.getHasArg()){//�޲�ѡ��
					Option o = opFactory(listOpOut, op.getName());
					if(o == null)
						listOpOut.add(op);
				}
				else {//�в�ѡ��
					//�����жϺ�����û�в������������û���ַ����ˣ��϶������ܡ�
					if(++j >= opsStr.length) //��Ȼ�Ǵ���ѡ�����û�д�����
						break;
					argStr = opsStr[j];
					
					//�жϲ����Ƿ�Ϸ�:Сд��ĸ,���ֺͼ�����ɵķǿ��ַ���
					if(!islegalArg(argStr))
						break;
					//���ѡ��
					Option o = opFactory(listOpOut, op.getName());
					if(o == null){
						op.setArg(argStr);
						listOpOut.add(op);
					}else{
						o.setArg(argStr);
					}
				}
			}
			
			//�Ѹ��е�ѡ�����˳������
			Collections.sort(listOpOut);
			
			//���
			System.out.print("Case " + (++i) + ":");
			for(Option op : listOpOut)
				System.out.print(op.toString());
			System.out.println();
		}
		
		
	}
	
	/**�ж�ѡ�����Ƿ�Ϸ�
	 *����nullΪ���Ϸ�
	 */
	private static Option islegalOption(List<Option> listOptions, String opStr) {
		//�ж�ѡ�����Ƿ�Ϸ���'-' + һ��Сд��ĸ�������ַ�
		if(opStr.length() > 2 || opStr.charAt(0) != '-')
			return null;
		//�ж�ѡ�����Ƿ�Ϸ����Ƿ��Ǹ�����ѡ������
		return opFactory(listOptions, opStr.charAt(1));
	}
	
	/**�жϲ����Ƿ�Ϸ�
	 */
	private static boolean islegalArg(String argStr){
		char[] argCharArr = argStr.toCharArray();
		for(char c : argCharArr)
			if(!((c>= '0' && c <='9') || (c >= 'a' && c<='z') || c == '-'))
				return false;
		return true;
	}
	 
	/**OptionFactory
	 *
	 **/
	private static Option opFactory(List<Option> list, char name) {
		for(Option op : list){
			if(op.getName() == name)
				return op;
		}
		return null;
	}
}

/**ѡ���� Option
 *
 **/
class Option implements Comparable<Option>{
	//ѡ������ƣ���Ϊֻ��һ��Сд��ĸ��Ϊ�˺������򷽱㣬ֱ��ʹ��char
	private char name;
	//ѡ��Ĳ���������,����Сд��ĸ,���ֺͼ�����ɵķǿ��ַ�����
	private String arg;
	//�Ƿ��в����ı�־
	private boolean hasArg;
	
	public Option(){}
	public Option(char name, boolean hasArg){
		this.name = name;
		this.hasArg = hasArg;
		this.arg = "";
	}
	
	public char getName(){
		return this.name;
	}
	
	public boolean getHasArg() {
		return this.hasArg;
	}
	
	public void setArg(String arg) {
		this.arg = arg;
	}
	
	/**�ȽϷ���
	 *�������ֵ��Ⱥ�˳������
	 */
	public int compareTo(Option o){
		return this.name - o.getName();
    }
	
	/**toString
	 */
	public String toString() {
		if(hasArg)
			return " -" + name + " " + arg;
		else 
			return " -" + name;
	}
	
}