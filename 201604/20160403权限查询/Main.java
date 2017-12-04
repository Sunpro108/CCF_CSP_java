/**
 * �û�Ȩ�޲�ѯ����
 * ֮ǰ���õ�˼·�ǣ��ֱ𴴽�Ȩ��/��ɫ/�û������࣬�û��д洢��ɫ����ɫ�д洢Ȩ�ޣ���ѯ�û�Ȩ��ʱ���������û������н�ɫ������Ȩ�ޣ��鿴�����Ƿ��и�Ȩ�ޡ�����
 * mmp��д���ҵ�ʱ�����˼·�������������ˡ�������ʱ�����һǻ��Ѫ������һ���㷨��������㰡����ʱ��������Ǳ����г�ʱ����ô�㲻��ʱ�Źְ���
 * �µ�˼·�����Բ�����ã��������ٱ�ɵ����1.0ʱ��ǿ��ɡ�
 *��Ȼ��Ϊ�����ࣺ�û�/��ɫ/Ȩ�ޣ����ǣ��洢��ʱ�򣬽�ɫֻ�ǹ��ɣ��û�ֱ�Ӵ洢��Ȩ�ޣ���ѯ��ʱ�����ĳ���û���ֱ�Ӳ�ѯ�Ƿ��и�Ȩ���Լ�Ȩ���Ƿ񹻵����⡣�ܵ���˵��Ӧ�������Ӵ洢ʱ��Ĵ���ʱ�䣬���Ǵ����ٲ�ѯ��ʱ�䡣
 **/
 import java.util.*;
 public class Main {
    public static Boolean debug = false;
    public static int level = 0;// 0 for info  1 for debug
    public static void main(String args[]) {
        //��ȡ����
        Scanner cin = new Scanner(System.in);
        // input Privission list
        int p = cin.nextInt();
        Privilege[] arrayPrivi = new Privilege[p];
        //List<Privi> priviList = new ArrayList<Privi>();
        for (int i = 0; i < p; i++) {
            arrayPrivi[i] = new Privilege(cin.next());
        }
        // input role list
        int r = cin.nextInt();
        Role[] arrayRole = new Role[r];
        for (int i = 0; i < r; i++) {
            String nameRole = cin.next();
            int countPrivi = cin.nextInt();
            Role role = new Role(nameRole, countPrivi);
            for (int j = 0; j < countPrivi; j++) {
                role.addPrivi(new Privilege(cin.next()));
            }
            arrayRole[i] = role;
        }
        //������ɫ�󣬲鿴һ�½�ɫ�б�
        if(Main.debug  && Main.level > 1){
            for(int j = 0; j < r; j++) {
                System.out.print("������ɫ��");
                System.out.println(arrayRole[j]);
            }
        }
        // input user list;
        int u = cin.nextInt();
        User[] arrayUser = new User[u];
        for (int i = 0; i < u; i++) {
            String name = cin.next();
            int countRole = cin.nextInt();
            User user = new User(name);
            for (int j = 0; j < countRole; j++) {
                Role role = roleFactory(arrayRole,cin.next());
                if(Main.debug  && Main.level > 0){
                    System.out.println("Main:role to added:" + role);
                }
                user.addRole(role);
                //��ӡ�û���Ϣ
                if(Main.debug  && Main.level > 0){
                    System.out.println("Main: user: " + user);
                }
            }
            arrayUser[i] = user;
            //������û��󣬲鿴һ�½�ɫ�б�
            if(Main.debug  && Main.level > 0){
                for(int k = 0; k < r; k++) {
                    System.out.print("�����һ���û���role list: ");
                    System.out.println(arrayRole[k]);
                }
            }
            //��ӡ�û���Ϣ
            if(Main.debug  && Main.level > 0){
                System.out.println("Main: ��ɫ��������user: " + user);
            }
        }
        //
        int q = cin.nextInt();
        QueryHolder[] arrayQuery = new QueryHolder[q];
        for (int i = 0; i < q; i++) {
            arrayQuery[i] = new QueryHolder(cin.next(),cin.next());
        }
        cin.close();
        if(Main.debug  && Main.level > 1) 
              System.out.println("Main:�������");
        //��ѯ��ʼ��
        for(QueryHolder query : arrayQuery) {
            User qUser = userFactory(arrayUser, query.qUserName);
            if(qUser == null){
                System.out.println(false);
                continue;
            }
            if(Main.debug  && Main.level > 1)
                System.out.println("Main:qUser: " + qUser);
            int result = qUser.quaryPrivi(query.qPrivi);
            if(result == -2 )
                System.out.println("false");
            else if (result == -1)
                System.out.println("true");
            else 
                System.out.println("" + result);
        }
    }

    /**���ݽ�ɫ���ַ��ؽ�ɫ����
    */
    private static Role roleFactory(Role[] arrayRole,String name) {
        for(Role role : arrayRole) {
            if(name.equals(role.getRoleName())) {
                return role;
            }
        }
        return null;
    }

   /**�����û����ַ����û�����
    */
    private static User userFactory(User[] arrayUser,String name) {
        for(User user : arrayUser) {
            if(name.equals(user.getUserName())) {
                return user;
            }
        }
        return null;
    }

 }

 /**��ѯ����������
  *
  */
 class QueryHolder {
        public String qUserName;
        public Privilege qPrivi;
        QueryHolder(String username, String Priviname) {
            this.qUserName = username;
            this.qPrivi = new Privilege(Priviname);
        }
        public String toString() {
            return "query: " + this.qUserName + " " + this.qPrivi; 

        }
 }

 /**�û���
  *
  **/
 class User {
     //�û���
     private String userName;
     //�û�ӵ�н�ɫ������
     private int numRoles;
     //�û�ӵ��Ȩ�޵��б�
     private List<Privilege> userPrivi;
     /**
     *���캯��
     **/
     public User(){}
     public User(String userName){
         this.userName = userName;
         //this.numRoles = numRoles;
         this.userPrivi = new ArrayList<Privilege>();
     }

     /**��дtoString()����
      *
      **/
      public String toString() {
          String str = "user:" + this.userName + "-";
          for (Privilege privi : this.userPrivi)
              str += privi + " ";
          return str;
      }


     public String getUserName() {
         return this.userName;
     }

     /**��ѯ�û��Ƿ����ĳȨ��
      * ע�⣺Ȩ�޵ȼ���0-9������
      *@return -2(���߱���Ȩ��/�߱���Ȩ�޵��ǵȼ�������;
      *@return -1 (�߱���Ȩ��������еĵȼ������ڸ����ĵȼ�);
      *@return >=0��ֵ �߱���Ȩ�ޣ������Ȩ��Ϊ����ֵ��
      **/
      public int quaryPrivi(Privilege privi) {

          for(Privilege p : this.userPrivi) {
              if(p.namePrivi.equals(privi.namePrivi)){//���Ȳ鿴�Ƿ�������Ȩ�ޣ�
                  if(privi.rankPrivi <= p.rankPrivi){
                      //�޵ȼ�Ȩ�ޣ�ֱ�ӷ���0��
                      // �еȼ�Ȩ��, ������Ȩ�޵ȼ�������еĵȼ������ڸ����ĵȼ�, ���� 0��
                      if(p.rankPrivi == -1 || privi.rankPrivi != -1)
                          return -1;
                      // �еȼ�Ȩ��, ����û����Ȩ�޵ȼ�, ������ӵ�е���ߵȼ�Ȩ�ޣ�
                      else
                          return p.rankPrivi;
                  }else 
                      return -2;
              }
          }
          //û������Ȩ��
          return -2;
      }

     /** 
      * ��ȡ�û���Ȩ�޼���
      */
     public List<Privilege> getUserPrivi(){
         return this.userPrivi;
     }

     /***����û��Ľ�ɫ����,
      *ֱ�ӽ���ɫ��Ȩ�޴浽�û���Ȩ���б���
      *ע�⣬��ӵĽ�ɫ��ʱ�򣬿��ܻ���֣���ͬ��ɫ������ͬ��Ȩ�ޣ�����Ȩ�޵ȼ���ͬ�������
      *���ڲ�ͬ�Ľ�ɫ�У�Ȩ������ͬ�Ĳ��֣���������޵ȼ�Ȩ�޻��ߵȼ���ͬ��������List,�Զ�ɾ���ظ��ġ�����ǲ�ͬ�ȼ���ͬ��Ȩ�ޣ������ߵȼ���Ȩ�ޡ�
      **/
      public void addRole(Role r) {
          if(Main.debug && Main.level > 0){ 
              System.out.println("add role: user:" + this.userName);
              System.out.println("add role: role to added: " + r);
          }
          //��ȡ����ӽ�ɫ��Ȩ���б�
          List<Privilege> rolePrivi = r.getRolePrivi();
          //��ȡ���û���Ȩ���������������Ϊ0���򲻻����Ȩ�޳�ͻ�����⣬ֱ���������Ȩ��
          int numUserPrivi = this.userPrivi.size();
          if(numUserPrivi == 0){
              this.userPrivi.addAll(rolePrivi);
              return;
          }
          //������û��Ѿ����˲���Ȩ�ޣ���Ҫ�жϴ���ӵĽ�ɫ��Ȩ���Ƿ�����е��ظ���
          //��ȡ����ӽ�ɫ��Ȩ�޵�����
          int numRolePrivi = rolePrivi.size();
          for(int i = 0; i < numRolePrivi; i++) {
              Boolean hasSameName = false;//�Ƿ�������Ȩ�޵ı�־��ÿ�ε���Ҫ��ʼ��
              if(Main.debug  && Main.level > 1){ 
                System.out.println("add role:" + i);
                System.out.println("add role: rolePrivi:i:" + rolePrivi.size());
              }
              Privilege iPrivi = rolePrivi.get(i);
              for (int j = 0; j < numUserPrivi; j++) {
                  if(Main.debug  && Main.level > 1){ 
                    System.out.println("add role: userPrivi:j:" + j);
                    System.out.println("add role: userPrivi:j:" + this.userPrivi.size());
                  }
                  Privilege jPrivi = this.userPrivi.get(j);
                  if (iPrivi.namePrivi.equals(jPrivi.namePrivi)){//������Ȩ��
                      hasSameName = true;
                      if(iPrivi.rankPrivi > jPrivi.rankPrivi){ //������Ȩ�ޣ����µ�Ȩ�޵ĵȼ�����ԭȨ�޵ȼ��������е�Ȩ��ɾ��������µ�Ȩ�ޣ������û�Ȩ������
                          this.userPrivi.remove(j);
                          this.userPrivi.add(iPrivi);
                          //numUserPrivi = this.userPrivi.size();
                      }else{ //������Ȩ�ޣ����µ�Ȩ�޵ĵȼ�������ԭȨ�޵ȼ�������Ҫ��ӣ��Ѹ�Ȩ�޴ӽ�ɫȨ���б�ɾ�������½�ɫ�б�Ȩ�޵�������
                          //rolePrivi.remove(i);
                          //numRolePrivi = rolePrivi.size();
                      }
                      //�������������Ȩ�ޣ���Ϊ��ɫȨ���б�϶�û������Ȩ�ޣ����û�Ȩ���б�Ҳû������Ȩ�ޣ���˿��������û���ѭ����
                      break;
                  }
              }
              //user���е�Ȩ�޺�Ҫ��ӵ�Ȩ�޶������������
              if(!hasSameName){
                  this.userPrivi.add(iPrivi);
              }
          }
          //�ų��������������ܣ�ֱ���������Ȩ��
          //this.userPrivi.addAll(rolePrivi);
      }


 }

 /**��ɫ��
  * 
  **/
 class Role {
     //��ɫ����
     private String roleName;    
    //��ɫӵ�е�Ȩ���б�: ��ά�����Ÿ�ʽ: [[Ȩ��1����Ȩ��1�ȼ�]��[Ȩ��2����Ȩ��2�ȼ�], ...](���У�Ȩ�����ɶ�Ӧ�����ֱ�ʾ��)
     private List<Privilege> rolePrivilege;
     //Ȩ�޴�������ļ�����
     //private int counterPrivilege;
     //public Role(){}
     /**
      *Role ���캯��
      *@param roleName 
      *@param numPrivilege �ý�ɫӵ�е�Ȩ��������Ŀ���ɴ˳�ʼ��rolePrivilege
      **/
     public Role(String roleName, int numOfPrivilege){
         this.roleName = roleName;
         //this.counterPrivilege = 0;
         this.rolePrivilege = new ArrayList<Privilege>();
     }

     public String toString() {
         String str = "Role:" + this.roleName + "-";
         for (Privilege privi : rolePrivilege)
             str += privi + " ";
         return str;
     }

     public String getRoleName(){
         return this.roleName;
     }

     public List<Privilege> getRolePrivi(){
         return this.rolePrivilege;
     }
     /**Ϊ��ɫ����Ȩ�ޣ�
      * ע�⣡Ȩ�޿����ظ����֣�������ȼ���Ȩ���ظ����֣��Եȼ���ߵ�Ϊ׼
      *@param Privilege Ȩ���ַ�������ʽ��name:rank; �� crm:1
      *Ϊ���Ϳռ䣬����ٶȣ�ֱ�ӽ�name����ӳ���ϵ��¼������;
      **/
     public void addPrivi(Privilege privi) {
         //��ȡȨ�޵�����
         String namePrivi = privi.namePrivi;
         //��ȡȨ�޵ĵȼ�
         int rankPrivi = privi.rankPrivi;
         //��ȡ�ý�ɫ���е�Ȩ�޵�����
         int numRolePrivi = this.rolePrivilege.size();
         //ȷ���Ƿ����ظ���Ȩ�ޣ��ظ���Ȩ�ޱ�����ߵ�Ȩ�ޡ�
         Boolean hasSameName = false;
         Privilege iPrivi = null;
         for (int i  =0; i < numRolePrivi; i++) { //�������е�Ȩ���б�
             iPrivi = this.rolePrivilege.get(i);
             if (iPrivi.namePrivi.equals(namePrivi)){ //������Ȩ��
                 hasSameName = true;
                 if(iPrivi.rankPrivi < rankPrivi){//�滻�б��еȼ��ϵ͵�Ȩ��
                     this.rolePrivilege.remove(i);
                     this.rolePrivilege.add(privi);
                 } 
                 //���iprivi.rankprivi >= rankprivi:
                 //���Ƴ�������ӣ�����Ҳ������
                 break;
             }
         }
         if(!hasSameName)
            this.rolePrivilege.add(privi);
     }
 }

 /**Ȩ����
  *Ϊ������ٶȣ���Privilegeֱ�Ӵ洢Ϊ���飻[p1,p2,p3....]
  */
 class Privilege {
     public String namePrivi;
     //Ȩ�޵ȼ���0-9������-1��ʾΪ�޵ȼ�Ȩ�ޣ�
     public int rankPrivi;
     public Privilege(){}
     /** 
      *
      */
     public Privilege(String nk){
         String[] privi = nk.split(":");
         if(privi.length == 2){
             this.namePrivi = privi[0];
             this.rankPrivi = Integer.parseInt(privi[1]);
         }else if (privi.length == 1) {
             this.namePrivi = privi[0];
             this.rankPrivi = -1;
         }else {
             this.namePrivi = "utility";
             this.rankPrivi = -1;
         }
     }
     public Privilege(String namePrivi, int rankPrivi){
         this.namePrivi = namePrivi;
         this.rankPrivi = rankPrivi;
     }

     public String toString() {
         if(this.rankPrivi == -1)
             return "privi=" + this.namePrivi;
         else 
             return "privi=" + this.namePrivi + ":" + this.rankPrivi;
     }

     /**
      *��дequals����
      */
     public Boolean equals(Privilege p) {
          if(this.namePrivi.equals(p.namePrivi) && (this.rankPrivi == p.rankPrivi))
              return true;
          return false;
     }
 }