import java.util.Scanner;

public class OrderStage {
	OrderVariables variables = new OrderVariables();
	Scanner scanner = new Scanner(System.in);
	
	public int order() {
		System.out.printf("������ �����ϼ���.\n1. ���ϱ�\n2. �߰���\n");
		System.out.printf("����: ");
		variables.dayOrNight = scanner.nextInt();
		
		System.out.printf("****************************************\n");
		System.out.printf("�ֹε�Ϲ�ȣ 7��° �ڸ����� �Է��ϼ���\n");
		System.out.printf("�ֹι�ȣ: ");
	variables.juminString = scanner.next();
	for (int index = 0; index < variables.juminString.length(); index++) {
		variables.jumin[index] = variables.juminString.charAt(index);
	}
	
	do {
		System.out.printf("****************************************\n");
		System.out.printf("�� ���� �ֹ��Ͻó���? (10�� �ʰ� �� ��ȿ)\n"); 
		System.out.printf("����: ");
		variables.numberOfTicket = scanner.nextInt();
	} while (variables.numberOfTicket > 10);
	
	System.out.printf("****************************************\n");
	System.out.printf("�������� �����ϼ���.\n");
	System.out.printf("1. �����\n2. ����������\n3. �ӻ��\n4. �ް��庴\n5. �ٵ���\n6. ����\n");
	System.out.printf("����: ");
	variables.discountCondition = scanner.nextInt();
	
	return 0;
	}
}
