import java.util.*;

public class Main {
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		int N = cin.nextInt();//���ڸ���
		int M = cin.nextInt();//�����ĸ�����
		int[][] wins = new int[N][4];//�������ڵ����ꣻ
		for(int i =0;i<N;i++){
			for(int j = 0; j < 4; j++){
				wins[i][j] = cin.nextInt();
			}
		}
		//�������������
		int[][] ps = new int[M][2];
		for(int i =0;i<M;i++){
			for(int j = 0; j < 2; j++){
				ps[i][j] = cin.nextInt();
			}
		}
		//��ʼ��һ����Ŵ��ڱ�ŵ�List
		List<Integer> indexWins = new ArrayList<Integer>();
		for(int i =N-1;i>=0;i--){
			indexWins.add(i);//��ŵĴ��ڵı��С1�������ʱ����Ҫ��1��
		}
		//��ѯ
		int result = 0;
		for(int i=0; i<M; i++){
			 result = click(wins, indexWins, ps[i]);
			 if(result == -1)
				 System.out.println("IGNORED");
			 else 
				 System.out.println(result);
		}
		
	}
	
	/*�����ѯ����
	 *@param wins ���д��ڵķ�Χ
	 *@param indexWins :��ǰ���ڵı�ŵ�����
	 *@param p: ����ĵ㣻
	 *@return index : ��ѡ�еĴ��ڵı�ţ�-1��ʾ"IGNORED"
	 */
	private static int click(int[][] wins, List<Integer> indexWins, int[] p){
		int index = 0;
		int N = indexWins.size();
		for(int i = 0; i < N;i++ ){
			index = indexWins.get(i);
			int[] rang = wins[index];//ȡ���ô��ڵķ�Χ��
			if( p[0] <= rang[2] & p[0] >= rang[0] & p[1] <= rang[3] & p[1] >= rang[1]){ //�õ���Ŀǰ�Ĵ����ڣ�
				//���ô��ڵı�ŷŵ���ǰ��
				indexWins.remove(i);
				indexWins.add(0,index);
				//���ظô��ڵı��
				return index+1;
			}
		}
		//û�д��ڱ�ѡ�У�
		return -1;
	}
}