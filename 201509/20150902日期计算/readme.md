int year;
int num;
int[12] daysOfMonth;
Boolean isLeap;
//�ж��Ƿ�������
isLeap;
//��daysOfMonth��ֵ��
�������� dayOfMonth[1] = 29;
���� daysOfMonth[1] = 28;
�������ǹ̶��ĸ�ֵ
//��������ʱ��ľ�������
int sum;
for(int i = 0; i < 12; i++){
	sum += daysOfMonth[i];
	if( num > sum) continue;
	else{
	month = i + 1;
	day = daysOfMonth[i] - (sum - daysOfMonth[i]); 
	}
}

