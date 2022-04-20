import java.util.Scanner;
import java.util.ArrayList;


public class Polyland {
	Scanner scanner = new Scanner(System.in);
	OrderData orderVariable;
	ArrayList <OrderData> listMaker = new ArrayList <OrderData>();
	

	int printTop () {
		System.out.printf("권종을 선택하세요.\n1. 주간권\n2. 야간권\n");
		System.out.printf("선택: ");
		orderVariable.dayOrNight = scanner.nextInt();
		
		System.out.printf("****************************************\n");
		System.out.printf("주민등록번호 7번째 자리까지 입력하세요\n");
		System.out.printf("주민번호: ");
		orderVariable.juminString = scanner.next();
		for (int index = 0; index <orderVariable.juminString.length(); index++) {
			orderVariable.jumin[index] = orderVariable.juminString.charAt(index);
		}
		
		do {
		System.out.printf("****************************************\n");
		System.out.printf("몇 장을 주문하시나요? (10장 초과 시 무효)\n"); 
		System.out.printf("수량: ");
		orderVariable.numberOfTicket = scanner.nextInt();
		} while (orderVariable.numberOfTicket > 10);
		
		System.out.printf("****************************************\n");
		System.out.printf("우대사항을 선택하세요.\n");
		System.out.printf("1. 장애인\n2. 국가유공자\n3. 임산부\n4. 휴가장병\n5. 다둥이\n6. 없음\n");
		System.out.printf("선택: ");
		orderVariable.discountCondition = scanner.nextInt();
			
			return 0;
	}


	void ageCal () {
		if ((orderVariable.jumin[6] - '0') == 1 || (orderVariable.jumin[6] - '0') == 2) {
			orderVariable.birthYear = 1900 + ((orderVariable.jumin[0] - '0') * 10 + (orderVariable.jumin[1] - '0'));
		} else if ((orderVariable.jumin[6] - '0') == 3 || (orderVariable.jumin[6] - '0') == 4) {
			orderVariable.birthYear = 2000 + ((orderVariable.jumin[0] - '0') * 10 + (orderVariable.jumin[1] - '0'));
		}
		
		orderVariable.birthMonth = ((orderVariable.jumin[2] - '0') * 10) + (orderVariable.jumin[3] - '0');
		orderVariable.birthDate = ((orderVariable.jumin[4] - '0') * 10) + (orderVariable.jumin[5] - '0');
			
			 
			if (StaticValue.MONTH_NOW > orderVariable.birthMonth || (StaticValue.MONTH_NOW == orderVariable.birthMonth && StaticValue.TODAY >= orderVariable.birthDate)) {
				orderVariable.age = StaticValue.YEAR_NOW - orderVariable.birthYear;
			} else {
				orderVariable.age = StaticValue.YEAR_NOW - orderVariable.birthYear - 1;
			}
			
			if (orderVariable.age >=0 && orderVariable.age < 3) {
				orderVariable.ageGroup = StaticValue.AGE_GROUP_BABY;
			} else if (orderVariable.age >= 3 && orderVariable.age < 13) {
				orderVariable.ageGroup = StaticValue.AGE_GROUP_KID;
			} else if (orderVariable.age >= 13 && orderVariable.age < 19 ) {
				orderVariable.ageGroup = StaticValue.AGE_GROUP_YOUNG;
			}else if (orderVariable.age >= 19 && orderVariable.age < 65) {
				orderVariable.ageGroup = StaticValue.AGE_GROUP_ADULT;
			}else if (orderVariable.age >= 65) {
				orderVariable.ageGroup = StaticValue.AGE_GROUP_ELDER;
		}
		
	}
	
	void ticketChoice (int choice) {
				if (choice == 1) {
				if (orderVariable.age >= 0 && orderVariable.age < 3 || (orderVariable.age > 64)) {
					orderVariable.price = StaticValue.BABY * orderVariable.numberOfTicket;
				} else if (orderVariable.age >= 3 && orderVariable.age < 13) {
					orderVariable.price = StaticValue.KID_DAY * orderVariable.numberOfTicket;
				} else if (orderVariable.age >= 13 && orderVariable.age < 19) {
					orderVariable.price = StaticValue.YOUNG_DAY * orderVariable.numberOfTicket;
				} else if (orderVariable.age >= 19 &&orderVariable.age < 65) {
					orderVariable.price = StaticValue.ADULT_DAY * orderVariable.numberOfTicket;
				}
				orderVariable.ticketTypeName = StaticValue.TICKET_TYPE_DAY;
			}
			
			if (choice == 2) {
				if (orderVariable.age >= 0 && orderVariable.age <= 3 || orderVariable.age >= 65) {
					orderVariable.price = StaticValue.BABY * orderVariable.numberOfTicket;
				} else if (orderVariable.age >= 13 && orderVariable.age < 19) {
					orderVariable.price = StaticValue.YOUNG_NIGHT * orderVariable.numberOfTicket;
				} else if (orderVariable.age >= 19 && orderVariable.age < 65) {
					orderVariable.price = StaticValue.ADULT_NIGHT * orderVariable.numberOfTicket;
				}
				orderVariable.ticketTypeName = StaticValue.TICKET_TYPE_NIGHT;
			}
	}
	
	void discount (int dcCondition) {
			if (dcCondition == 1) {
				orderVariable.price = orderVariable.price - (int)(orderVariable.price * StaticValue.DC_DIS_NAT_PREG);
				orderVariable.discountType = StaticValue.DC_TYPE_DISABLED;
			} else if (dcCondition == 2) {
				orderVariable.price = orderVariable.price - (int)(orderVariable.price * StaticValue.DC_DIS_NAT_PREG);
				orderVariable.discountType = StaticValue.DC_TYPE_MERIT;
			} else if (dcCondition == 3) {
				orderVariable.price = orderVariable.price - (int)(orderVariable.price * StaticValue.DC_DIS_NAT_PREG);
				orderVariable.discountType = StaticValue.DC_TYPE_PREGNANT;
			} else if (orderVariable.discountCondition == 4) {
				orderVariable.price = orderVariable.price - (int)(orderVariable.price * StaticValue.DC_SOLDIER);
				orderVariable.discountType = StaticValue.DC_TYPE_SOLDIER;
			} else if (orderVariable.discountCondition == 5) {
				orderVariable.price = orderVariable.price - (int)(orderVariable.price * StaticValue.DC_MULTIKID);
				orderVariable.discountType = StaticValue.DC_TYPE_MULTILKID;
			}
			
			
	}
	
	/*int saveData () {
			orderList[roundCount][StaticValue.SAVE_TICKET_TYPE] = dayOrNight;
			orderList[roundCount][StaticValue.SAVE_AGE_GROUP] = ageGroup;
			orderList[roundCount][StaticValue.SAVE_TICKET_COUNT] = numberOfTicket;
			orderList[roundCount][StaticValue.SAVE_PRICE_PER_PURCHASE] = price;
			orderList[roundCount][StaticValue.SAVE_DISCOUNT] = discountCondition;
			roundCount ++;
		return 0;
	}*/
	
	void printMiddle (){
		System.out.printf("****************************************\n");
		System.out.printf("가격은 %d원입니다. 감사합니다.\n", orderVariable.price);
		System.out.printf("계속 발권하시겠습니까?[1. 예 / 2. 아니오]\n");
		System.out.printf("선택: ");
		orderVariable.repeat = scanner.nextInt();
		System.out.printf("\n**************************************\n");
		
	}
	
	void printBottom() {
		System.out.printf("티켓 발권을 종료합니다. 감사합니다.\n");
		System.out.printf("\n=============폴리랜드==============\n");
	
	/*for (int index = 0; index < roundCount; index++) { //�ٽ�,,,
		if (orderList[index][StaticValue.SAVE_TICKET_TYPE] == 1) {
			System.out.printf("주간권\t");
		} else if (orderList[index][StaticValue.SAVE_TICKET_TYPE] == 2) {
			System.out.printf("야간권\t");
		}
		
		if (orderList[index][StaticValue.SAVE_AGE_GROUP] == 1) {
			System.out.printf("베이비\t");
		} else if (orderList[index][StaticValue.SAVE_AGE_GROUP] == 2) {
			System.out.printf("어린이\t");
		} else if (orderList[index][StaticValue.SAVE_AGE_GROUP] == 3) {
			System.out.printf("청소년\t"); 
		} else if (orderList[index][StaticValue.SAVE_AGE_GROUP] == 4) {
			System.out.printf("성인\t");
		} else if (orderList[index][StaticValue.SAVE_AGE_GROUP] == 5) {
			System.out.printf("노인\t");
		}
		
		System.out.printf ("%d 장\t", orrderList[index][StaticValue.SAVE_TICKET_COUNT]);
		System.out.printf("%5d 원\t", orderList[index][StaticValue.SAVE_PRICE_PER_PURCHASE]);
		
		if (orderList[index][StaticValue.SAVE_DISCOUNT] == 1) {
			System.out.printf(" (장애인 할인)\n");
		} else if (orderList[index][StaticValue.SAVE_DISCOUNT] == 2) {
			System.out.printf(" (국가유공자 할인)\n");
		} else if (orderList[index][StaticValue.SAVE_DISCOUNT] == 3) {
			System.out.printf(" (임산부 할인)\n");
		} else if (orderList[index][StaticValue.SAVE_DISCOUNT] == 4) {
			System.out.printf(" (휴가장병 할인)\n");
		} else if (orderList[index][StaticValue.SAVE_DISCOUNT] == 5) {
			System.out.printf(" (다둥이 할인)\n");
		} else {
			System.out.printf(" (할인 없음)\n");
		}
		
	}*/
	System.out.printf("==================================\n");
	System.out.printf("총 결제액은 %d원입니다.", OrderData.priceFinal);
	}	
	public static void main (String[] args) {
		Polyland poly = new Polyland(); 
		do {
			
					
			poly.printTop();
			
			poly.ageCal();
			
			poly.ticketChoice(poly.orderVariable.dayOrNight);
			
			poly.discount(poly.orderVariable.discountCondition);
			
			poly.orderVariable.priceFinal = poly.orderVariable.priceFinal + poly.orderVariable.price;
			
			//poly.saveData();
			
			
			poly.printMiddle();
			
	
		} while (poly.orderVariable.repeat == 1);
	
		//poly.printBottom();
	}
}