import java.util.*;

public class Main {
	
	private static boolean isDebug = false;
	private static List<Option> listOptions = new ArrayList<Option>();
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		String formStr = cin.next();//�����ʽ�ַ���
		int N = cin.nextInt();//���������еĸ���
		cin.nextLine();
		String[] commondStrs = new String[N];
		for(int i=0; i < N; i++) {
			commondStrs[i] = cin.nextLine();
		}
		if(false)
			for(int i=0; i < N; i++)
				System.out.println(commondStrs[i]);
		//�������
		//������ʽ�ַ����� listOptions
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
		
		if(false){
			System.out.println("����ѡ�");
			for(Option op : listOptions)
				System.out.println(op);
		}
		//���н����������ַ���
		List<Option> listOpOut;
		for(int i = 0; i < N; i++){
			String commondLine = commondStrs[i];
			
			//��ʼ��������ѡ��List
			listOpOut = new ArrayList<Option>();
			//�ո�ָ�
			String[] opsStr = commondLine.split(" ");
			//����opsStr�ĳ��ȣ��ж�����ѡ�����Ϊ1Ϊ��ѡ�>1Ϊ��ѡ��
			if(opsStr.length == 1){
				System.out.println("Case " + (i+1) + ": ");
				continue;
			}
			String opStr = "";
			String argStr = "";
			for(int j = 1; j < opsStr.length; j++){
				opStr = opsStr[j];
				if(opStr.charAt(0) != '-')
					break;
				//-���������������ַ�������Ҫ�ж�һ���ǲ��ǵ�����
				if(opStr.length() > 2)
					break;
				Option op = opFactory(listOptions, opStr.charAt(1));
				//�ж��ǲ��ǺϷ�ѡ�
				if(op == null)//δ�ҵ���ѡ�
					break;
				//�ж��Ƿ��Ǵ���ѡ��
				if(!op.getHasArg()){//�޲�ѡ��
					Option o = opFactory(listOpOut, op.getName());
					if(o == null)
						listOpOut.add(op);
					//continue;
				}
				else {//�в�ѡ��
					if(isDebug)
						System.out.println("�в�����" + op);
					//�����жϺ�����û�в������������û���ַ����ˣ��϶������ܡ�
					if(++j >= opsStr.length) //��Ȼ�Ǵ���ѡ�����û�д�����
						break;
					argStr = opsStr[j];
					if(isDebug)
						System.out.println("�в���������Ϊ��" + argStr);
					//�жϲ����Ƿ�Ϸ�:Сд��ĸ,���ֺͼ�����ɵķǿ��ַ���
					if(!isLegal(argStr))
						break;
					
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
			System.out.print("Case " + (i+1) + ":");
			for(Option op : listOpOut)
				System.out.print(op.toString());
			System.out.println();
		}
		
		
	}
	
	/**�жϲ����Ƿ�Ϸ�
	 */
	private static boolean isLegal(String argStr){
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