package project_card;

public class CADTO {
	//member 테이블
	String id;
	String pwd;
	String name;
	String inputdate;
	
	//Account 테이블
	String accDiv;
	String bank;
	String accName;
	String accno;
	String bankNick;
	int accAmount;
	
	//Card 테이블
	String card;
	String cardName;
	String cardno;
	String cardNum;
	String cardNick;
	int cardAmount;
	
	//Cardwithdraw 테이블, AccountDisposit 테이블, AccountWithdraw 테이블
	String CADate;
	String category;
	int CAAmount;
	String CANick;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}
	public String getAccDiv() {
		return accDiv;
	}
	public void setAccDiv(String accDiv) {
		this.accDiv = accDiv;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccno() {
		return accno;
	}
	public void setAccno(String accno) {
		this.accno = accno;
	}
	public String getBankNick() {
		return bankNick;
	}
	public void setBankNick(String bankNick) {
		this.bankNick = bankNick;
	}
	public int getAccAmount() {
		return accAmount;
	}
	public void setAccAmount(int num) {
		this.accAmount = num;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public String getCardNick() {
		return cardNick;
	}
	public void setCardNick(String cardNick) {
		this.cardNick = cardNick;
	}
	public int getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(int cardAmount) {
		this.cardAmount = cardAmount;
	}
	public String getCADate() {
		return CADate;
	}
	public void setCADate(String cADate) {
		CADate = cADate;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getCAAmount() {
		return CAAmount;
	}
	public void setCAAmount(int cAAmount) {
		CAAmount = cAAmount;
	}
	public String getCANick() {
		return CANick;
	}
	public void setCANick(String cANick) {
		CANick = cANick;
	}
	
	
	public void printAcc() {
		System.out.printf("%6s통장\t%10s\t%20s\t%10d\t%8s\n", accDiv,bank,accName,accno,accAmount,bankNick);
	}
	public void printCard() {
		String str = "카드";
		System.out.printf	("%6s\t%10s\t%10s\t%20s\t10d\t%8s\n", str,card,cardName,cardno,cardAmount,cardNick);
	}
	public void print() {
		System.out.printf("%s %s %5s %10d %6s \n", id, CADate, CANick,accAmount,category);
	}
	
}
