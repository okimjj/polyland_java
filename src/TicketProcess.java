import java.util.Scanner;
import java.util.ArrayList;

public class TicketProcess {
	ArrayList <OrderVariables> orderList = new ArrayList <OrderVariables>(); 
	OrderVariables var;
	OrderStage ordStage = new OrderStage();
	AgeCalculator ageCal = new AgeCalculator();
	TicketChoice tickChoice = new TicketChoice();
	Discount dc = new Discount();
	RepeatCheck rpCheck = new RepeatCheck();
	EndPrint end = new EndPrint();
	
	
	
	void run() {
		
		do {
			var = new OrderVariables();
			
			ordStage.order();
			
			ageCal.ageCal();
			
			tickChoice.ticketChoice(var.dayOrNight);
			
			dc.discount(var.discountCondition);
			
			var.priceFinal = var.priceFinal + var.price;
			
			rpCheck.check();
			
			orderList.add(var);	
			
		} while (var.repeat == 1);
		
		end.ending();
		
		for (int index = 0; index < orderList.size(); index++) {
			System.out.printf("%5s\t", orderList.get(index).ticketTypeName);
			System.out.printf("%3s\t", orderList.get(index).ageGroup);
			System.out.printf("%d장\t", orderList.get(index).numberOfTicket);
			System.out.printf("%7d원\t", orderList.get(index).price);
			System.out.printf("%s\n", orderList.get(index).discountType);
		}
		
		System.out.printf("==================================\n");
		System.out.printf("총 결제액은 %d원입니다.", var.priceFinal);
	}
}
