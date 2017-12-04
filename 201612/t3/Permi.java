public class Permi {
	private String name;
	private int level;
	public Permi() {}

	public Permi(String per) {
		String[] p = per.split(":");
		if (p.length == 1) {
			Permi.this.name = p[0];
			Permi.this.level = -1;
		} else if (p.length == 2) {
			Permi.this.name = p[0];
			Permi.this.level = Integer.parseInt(p[1]);
		}
	}

	public String getName() {
		return this.name;
	}
	public void setName(String s) {
		this.name = s;
	}
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int l) {
		this.level = l;
	}

	/**
	*�ж�����Permi�Ĺ�ϵ��
	*���������ֲ�ͬ������ͬһ��Ȩ�ޣ�����-1��
	*��������ͬ��
	*	���ֵȼ�Ȩ�ޣ�����0��
	*	�ֵȼ�Ȩ�ޣ���
	*/
	public int equals(Permi p) {
		if (this.name.equals(p.getName())) {
			if(this.level == -1 && this.level < p.getLevel()) //�ֵȼ�Ȩ��
				return p.getLevel();//���ؽϴ��Ȩ�޵ȼ�ֵ
			else if(this.level == -1 && p.getLevel() == -1) //���ֵȼ�Ȩ��
				return -1;//����true;
			else if(this.level != -1 && this.level <= p.getLevel() ) //�ֵȼ�Ȩ��
				return -1;//����true;
			else //
				return -2;//����false
		} else
			return -2;//����false;
	}

	public String toString() {
		return this.name +(this.level != -1 ? (":" + this.level) : "");
	}
}
