import java.util.Scanner;



public class Polyland {
	static Scanner scanner = new Scanner(System.in);
	final int YEAR_NOW = 2022;
	final int MONTH_NOW = 4;
	final int TODAY = 18;
	
	final int AGE_GROUP_BABY = 1;
	final int AGE_GROUP_KID = 2;
	final int AGE_GROUP_YOUNG = 3;
	final int AGE_GROUP_ADULT = 4;
	final int AGE_GROUP_ELDER = 5;
	
	final int SAVE_TICKET_TYPE = 0;
	final int SAVE_AGE_GROUP = 1;
	final int SAVE_TICKET_COUNT = 2;
	final int SAVE_PRICE_PER_PURCHASE = 3;
	final int SAVE_DISCOUNT = 4;
	
	final int BABY = 15000;
	final int KID_DAY = 46000;
	final int KID_NIGHT = 35000;
	final int YOUNG_DAY = 52000;
	final int YOUNG_NIGHT = 41000;
	final int ADULT_DAY = 59000; 
	final int ADULT_NIGHT = 47000; 
	
	final float DC_DIS_NAT_PREG = 0.5f;
	final float DC_SOLDIER = 0.49f;
	final float DC_MULTIKID = 0.3f;
	
	int dayOrNight;
	char[] jumin = new char[7];
	String juminString;
	int birthYear;
	int birthMonth;
	int birthDate;
	int age;
	int ageGroup;
	int numberOfTicket = 0;
	int discountCondition;
	int price = 0;
	int priceFinal = 0; 
	int repeat;
	int roundCount = 0;
	int[][] orderList = new int [10][10];
	
	public static void main (String[] args) {
		Polyland poly = new Polyland(); 
		do {
			
					
			poly.printTop();
			
			poly.ageCal(poly.jumin);
			
			poly.ticketChoice(poly.dayOrNight);
			
			poly.discount(poly.discountCondition);
			
			poly.priceFinal = poly.priceFinal + poly.price;
			
			poly.saveData();
			
			poly.printMiddle();
			
		
	
		} while (poly.repeat == 1);
	
		poly.printBottom();
	}
	

	int printTop () {
		System.out.printf("권종을 선택하세요.\n1. 주간권\n2. 야간권\n");
		System.out.printf("선택: ");
		dayOrNight = scanner.nextInt();
		
		System.out.printf("****************************************\n");
		System.out.printf("주민등록번호 7번째 자리까지 입력하세요\n");
		System.out.printf("주민번호: ");
		juminString = scanner.next();
		for (int index = 0; index <juminString.length(); index++) {
			jumin[index] = juminString.charAt(index);
		}
		
		do {
		System.out.printf("****************************************\n");
		System.out.printf("몇 장을 주문하시나요? (10장 초과 시 무효)\n"); 
		System.out.printf("수량: ");
		numberOfTicket = scanner.nextInt();
		} while (numberOfTicket > 10);
		
		System.out.printf("****************************************\n");
		System.out.printf("우대사항을 선택하세요.\n");
		System.out.printf("1. 장애인\n2. 국가유공자\n3. 임산부\n4. 휴가장병\n5. 다둥이\n6. 없음\n");
		System.out.printf("선택: ");
		discountCondition = scanner.nextInt();
			
			return 0;
	}


	int ageCal (char socialNumber[]) {
		if ((socialNumber[6] - '0') == 1 || (socialNumber[6] - '0') == 2) {
			birthYear = 1900 + ((jumin[0] - '0') * 10 + (jumin[1] - '0'));
		} else if ((socialNumber[6] - '0') == 3 || (socialNumber[6] - '0') == 4) {
			birthYear = 2000 + ((jumin[0] - '0') * 10 + (jumin[1] - '0'));
		}
		//생월 = 주민번호 3,4번째 자리
		birthMonth = ((socialNumber[2] - '0') * 10) + (socialNumber[3] - '0');
		birthDate = ((socialNumber[4] - '0') * 10) + (socialNumber[5] - '0');
			
			 
			if (MONTH_NOW > birthMonth || (MONTH_NOW == birthMonth && TODAY >= birthDate)) {
				age = YEAR_NOW - birthYear;
			} else {
				age = YEAR_NOW - birthYear - 1;
			}
			
			if (age >=0 && age < 3) {
				ageGroup = AGE_GROUP_BABY;
			} else if (age >= 3 && age < 13) {
				ageGroup = AGE_GROUP_KID;
			} else if (age >= 13 && age < 19 ) {
				ageGroup = AGE_GROUP_YOUNG;
			}else if (age >= 19 && age < 65) {
				ageGroup = AGE_GROUP_ADULT;
			}else if (age >= 65) {
				ageGroup = AGE_GROUP_ELDER;
		}
		return 0;
	}
	
	int ticketChoice (int choice) {
				if (choice == 1) {
				if (age >= 0 && age < 3 || (age > 64)) {
					price = BABY * numberOfTicket;
				} else if (age >= 3 && age < 13) {
					price = KID_DAY * numberOfTicket;
				} else if (age >= 13 && age < 19) {
					price = YOUNG_DAY * numberOfTicket;
				} else if (age >= 19 && age < 65) {
					price = ADULT_DAY * numberOfTicket;
				}
			}
			
			if (choice == 2) {
				if (age >= 0 && age <= 3 || age >= 65) {
					price = BABY * numberOfTicket;
				} else if (age >= 13 && age < 19) {
					price = YOUNG_NIGHT * numberOfTicket;
				} else if (age >= 19 && age < 65) {
					price = ADULT_NIGHT * numberOfTicket;
				}
			}
		return 0;
	}
	
	int discount (int dcCondition) {
			if (dcCondition == 1 || dcCondition == 2 || dcCondition == 3) {
				price = price - (int)(price * DC_DIS_NAT_PREG);
			} else if (discountCondition == 4) {
				price = price - (int)(price * DC_SOLDIER);
			} else if (discountCondition == 5) {
				price = price - (int)(price * DC_MULTIKID);
			}
		return 0;
	}
	
	int saveData () {
			orderList[roundCount][SAVE_TICKET_TYPE] = dayOrNight;
			orderList[roundCount][SAVE_AGE_GROUP] = ageGroup;
			orderList[roundCount][SAVE_TICKET_COUNT] = numberOfTicket;
			orderList[roundCount][SAVE_PRICE_PER_PURCHASE] = price;
			orderList[roundCount][SAVE_DISCOUNT] = discountCondition;
			roundCount ++;
		return 0;
	}
	
	void printMiddle (){
		System.out.printf("****************************************\n");
		System.out.printf("가격은 %d원입니다. 감사합니다.\n", price);
		System.out.printf("계속 발권하시겠습니까?[1. 예 / 2. 아니오]\n");
		System.out.printf("선택: ");
		repeat = scanner.nextInt();
		System.out.printf("\n**************************************\n");
		
	}
	
	void printBottom() {
		System.out.printf("티켓 발권을 종료합니다. 감사합니다.\n");
		System.out.printf("\n=============폴리랜드==============\n");
	
	for (int index = 0; index < roundCount; index++) { //다시,,,
		if (orderList[index][SAVE_TICKET_TYPE] == 1) {
			System.out.printf("주간권\t");
		} else if (orderList[index][SAVE_TICKET_TYPE] == 2) {
			System.out.printf("야간권\t");
		}
		
		if (orderList[index][SAVE_AGE_GROUP] == 1) {
			System.out.printf("베이비\t");
		} else if (orderList[index][SAVE_AGE_GROUP] == 2) {
			System.out.printf("어린이\t");
		} else if (orderList[index][SAVE_AGE_GROUP] == 3) {
			System.out.printf("청소년\t"); 
		} else if (orderList[index][SAVE_AGE_GROUP] == 4) {
			System.out.printf("성인\t");
		} else if (orderList[index][SAVE_AGE_GROUP] == 5) {
			System.out.printf("노인\t");
		}
		
		System.out.printf ("%d 장\t", orderList[index][SAVE_TICKET_COUNT]);
		System.out.printf("%5d 원\t", orderList[index][SAVE_PRICE_PER_PURCHASE]);
		
		if (orderList[index][SAVE_DISCOUNT] == 1) {
			System.out.printf(" (장애인 할인)\n");
		} else if (orderList[index][SAVE_DISCOUNT] == 2) {
			System.out.printf(" (국가유공자 할인)\n");
		} else if (orderList[index][SAVE_DISCOUNT] == 3) {
			System.out.printf(" (임산부 할인)\n");
		} else if (orderList[index][SAVE_DISCOUNT] == 4) {
			System.out.printf(" (휴가장병 할인)\n");
		} else if (orderList[index][SAVE_DISCOUNT] == 5) {
			System.out.printf(" (다둥이 할인)\n");
		} else {
			System.out.printf(" (할인 없음)\n");
		}
		
	}
	System.out.printf("==================================\n");
	System.out.printf("총 결제액은 %d원입니다.", priceFinal);
	}	
}
