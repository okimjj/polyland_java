import java.util.Scanner;

public class RepeatCheck {
	Scanner scanner = new Scanner(System.in);
	OrderVariables variables = new OrderVariables();
	
	int check (){
		System.out.printf("****************************************\n");
		System.out.printf("������ %d���Դϴ�. �����մϴ�.\n", variables.price);
		System.out.printf("��� �߱��Ͻðڽ��ϱ�?[1. �� / 2. �ƴϿ�]\n");
		System.out.printf("����: ");
		variables.repeat = scanner.nextInt();
		System.out.printf("\n**************************************\n");
		
		return 0;
	}
}
