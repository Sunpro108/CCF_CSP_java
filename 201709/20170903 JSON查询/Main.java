/**JSON��ѯ
��һ��JSON����
����ʱ��
1. �����һ���ַ�����
2. ȥ�� ���лس�/�ո�
3. ȥ��ͷβ��{}
4. �� , �ָ�
5. ��ÿ����ֵ�Դ洢��map<String,String>�У�
��ѯʱ��
1. ���ݼ��ҵ���Ӧ��ֵ��
2. ����ֵ�����ַ��ж����ַ�������JSON����������ַ���"���Ǿ����ַ�����������ַ���{,�Ǿ���JSON����
3. ������ַ�����ֱ�Ӳ����������JSON�����ظ�����Ĺ��̡�
*/

import java.util.*;
import java.util.regex.*;
public class Main {
	public static Boolean DEBUG = true;
	public static int level = 1;
	public static void main(String args[]) {
		Scanner cin = new Scanner(System.in);
		int rowJSON = cin.nextInt();
		int numQuery = cin.nextInt();
		//��һ�����ֺ���Ļس�����
		cin.nextLine();
		StringBuilder sb = new StringBuilder(256);//��ʼ������Ϊ128
		for(int i = 0; i<rowJSON; i++) {
			sb.append(cin.nextLine());
			//System.out.println("sb:" + i + ": " + sb.toString());
		}
		String json = sb.toString();
		
		String[] jQuery = new String[numQuery];
		for(int i = 0; i<numQuery; i++) {
			jQuery[i] = cin.nextLine();
		}
		//�������
		//json = replaceBlank(json);
		if(Main.DEBUG && Main.level > 1){
			System.out.println(json);
		}
		if(Main.DEBUG && Main.level > 1){
			for(int i = 0; i<numQuery; i++) 
				System.out.println(jQuery[i]);
		}
		//�����ַ�����Map��
		Map<String,String> mapJson = parseJson(json);
		//��ѯ�ַ���
		for(int i = 0; i<numQuery; i++) {
			System.out.println(queryJson(mapJson, jQuery[i]));
		}
		 
	}
	
	/**��ѯ�ַ���
	 *
	 */
	private static String queryJson(Map<String, String> mapJson, String str) {
		//1. ����str���� : �ָ�����鳤�ȴ���1��˵���ж�����
		//�����鳤��Ϊ1������ǲ�ѯ��ǰ���JSON;
		//String[] keys = str.split(".");
		int indexNode = str.indexOf(".");
		//�ж��Ƿ���
		if(indexNode == -1) {//���㣻
			//String key = str;
			//2. ���ݵ�һ��ļ������Ҷ�Ӧ��ֵ��
			String value = mapJson.get(str);
			//3. ��ֵ���ַ������򷵻ؽ��String + value��
			//��ֵ��JSON������return queryJson(parseJson(value),str[1:])
			//������null���򷵻� "NOTEXIST";
			if(value == null)
				return "NOTEXIST";
			else{
				char c = value.charAt(0); //��ȡ���ַ�
				if (c == '\"'){ //���ַ��� ��,˵�����ַ�����ֱ�ӷ���ȥ�������ַ�����
					return "STRING " + value.substring(1,value.length());
				}else{
					return "OBJECT";
				}
			}	
		}else { //��㣻
			String key = str.substring(0,indexNode);//��ȡ��һ��ļ�
			//2. ���ݵ�һ��ļ������Ҷ�Ӧ��ֵ��
			String value = mapJson.get(key);
			//3. ��ֵ���ַ������򷵻ؽ��String + value��
			//��ֵ��JSON������return queryJson(parseJson(value),str[1:])
			//������null���򷵻� "NOTEXIST";
			if(value == null)
				return "NOTEXIST";
			else{
				char c = value.charAt(0); //��ȡ���ַ�
				if (c == '\"'){ //���ַ��� ��,˵�����ַ�����ֱ�ӷ���ȥ�������ַ�����
					return "NOTEXIST";
				}else{
					return queryJson(parseJson(value),str.substring(indexNode+1,str.length()));
				}
			}	
		}
		
		
	}
	
	/** �����ַ���ΪJSON
	 *JSON���� ����Ϊ Map<String,String>
	 **/
	 private static Map<String,String> parseJson(String strjson) {
		 //1. ȥ��ͷβ��{}
		 //��ȡsb����
		 //int length = strb.length();
		 //strbs = strb.deleteCharAt(strb.length()-1);
		 String strjson1 = strjson.substring(1,strjson.length());
		 //2. �� ���ָ��ַ���
		 String[] kvs = strjson1.split(",");
		 //3. �ָ���ÿ���ַ���,�ٰ���:���ָ���浽Map<String,String>�У�
		 Map<String, String> mapKV = new HashMap<String,String>();
		 for(String str : kvs){
			 String[] kv = str.split(":");
			 //�Ѽ��ġ���ȥ����
			 mapKV.put(kv[0].substring(1,kv[0].length()), kv[1]);
		 }
		 //. ����List
		 return mapKV;
	 }
	
	
	/** ������ʽȥ���ַ����еĿո��Ʊ��س����ַ�
	*
	**/
	public static String replaceBlank(String str) {
		String dest = "";
		if (str!=null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}
}
