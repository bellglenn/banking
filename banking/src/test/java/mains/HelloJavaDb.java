package mains;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJavaDb {
	Connection conn;

	public static void main(String[] args) throws SQLException {
		HelloJavaDb db = new HelloJavaDb();

		db.connectionToDerby();
		db.normalDbUsage();
	}

	public void connectionToDerby() throws SQLException {
		// -------------------------------------------
		// URL format is
		// jdbc:derby:<local directory to save data>
		// -------------------------------------------
		String dbUrl = "jdbc:derby:c:\\Users\\glenn\\workspace\\banking\\db;create=true";
		conn = DriverManager.getConnection(dbUrl);
	}

	public void normalDbUsage() throws SQLException {
		Statement stmt = conn.createStatement();
		
		// drop
//		 stmt.executeUpdate("Drop view cat_trans_v");
//		 stmt.executeUpdate("Drop view bank_statement_v");
//		 stmt.executeUpdate("Drop Table bank_transaction");
//		 stmt.executeUpdate("Drop Table cat_trans_lnk");
//		 stmt.executeUpdate("Drop Table category");
//		 stmt.executeUpdate("Drop sequence bt_seq");

//		 stmt.executeUpdate("create sequence bt_seq increment by 1");

		 // create table
//		stmt.executeUpdate("  CREATE TABLE category (name VARCHAR(50) not null, \r\n" + 
//				"	type VARCHAR(50), \r\n" + 
//				"	deduction DECIMAL, \r\n" + 
//				"	users VARCHAR(20) not null, \r\n" + 
//				"	sort_order INTEGER\r\n" + 
//				"   ) ");
//		stmt.executeUpdate("CREATE TABLE bank_transaction (when DATE, description VARCHAR(2000), \r\n" + 
//				"	amount DECIMAL, \r\n" + 
//				"	who VARCHAR(20), \r\n" + 
//				"	fye INTEGER, \r\n" + 
//				"	bank VARCHAR(50), \r\n" + 
//				"	users VARCHAR(20), \r\n" + 
//				"	id INTEGER primary key\r\n" + 
//				"   ) ");
//		stmt.executeUpdate(" CREATE TABLE cat_trans_lnk \r\n" + 
//				"   (category VARCHAR(50), \r\n" + 
//				"	search VARCHAR(200) not null, \r\n" + 
//				"	users VARCHAR(20) not null\r\n" + 
//				"   )  ");
//		stmt.executeUpdate("CREATE VIEW bank_statement_v AS "
//				+ "select distinct fye, who, bank, users from bank_transaction order by fye desc, who");
//		
//		stmt.executeUpdate("CREATE VIEW cat_trans_v AS "
//				+ "select lnk.category, lnk.search, bt.WHEN,bt.description,bt.amount,bt.who,bt.fye,bt.bank,bt.id, bt.users "
//				+ "from bank_transaction bt "
//				+ "LEFT OUTER JOIN cat_trans_lnk lnk on (bt.description LIKE lnk.search||'%')	order by 1");
//
//
//		stmt.executeUpdate("ALTER TABLE cat_trans_lnk ADD CONSTRAINT cat_trans_lnk_pk PRIMARY KEY (search, users)");
//		stmt.executeUpdate("ALTER TABLE category ADD CONSTRAINT category_pk PRIMARY KEY (name, users)");
//		stmt.executeUpdate("ALTER TABLE CAT_TRANS_LNK ADD CONSTRAINT cat_trans_lnk_fk FOREIGN KEY (category, users) REFERENCES category (name, users)");
//		
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('HAIR AND BEAUTY','LIFESTYLE',0,'Glenn Jennie',55)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('INTERBANK TRANSFER','TRANSFER',0,'Glenn Jennie',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CWTH SUPER','INCOME',0,'Glenn Jennie',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('QLD HEALTH','INCOME',1,'Glenn Jennie',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CLOTHING','FOOD',0,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('POST','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('TAX','TAX',0,'Glenn Jennie',88)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('MEDICAL INS REFUND','REFUND',0,'Glenn Jennie',77)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('HARDWARE','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('WITHDRAWAL','WITHDRAWAL',0,'Glenn Jennie',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('ACCOM','RECREATION',0,'Glenn Jennie',66)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('DINING','FOOD',0,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('WHITE GOODS','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('BARGE','TRANSPORT',0,'Glenn Jennie',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('FINE','TRANSPORT',0,'Glenn Jennie',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PETROL TOYOTA','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('MUSIC','RECREATION',0,'Glenn Jennie',66)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('QSUPER','INCOME',0,'Glenn Jennie',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('SERVICE VW','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CHARITY','DEDUCTION',1,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('ART','HOMEWARE',0,'Glenn Jennie',44)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('WATER TAXI','TRANSPORT',0,'Glenn Jennie',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PHONE','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('AIR BNB INSURANCE','EXPENSE',1,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('MEDICAL','DEDUCTION',1,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('INTERNET','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('TAX_AGENT','DEDUCTION',1,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('ALCOHOL','FOOD',0,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('POWER','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PARKING','TRANSPORT',0,'Glenn Jennie',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PETROL VW','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('REGO VW','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('HOUSE INSURANCE','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('BUS','TRANSPORT',0,'Glenn Jennie',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('RATES','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('GAS','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('FISHING','RECREATION',0,'Glenn Jennie',66)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('REGO TOYOTA','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('TRANSFER','TRANSFER',0,'Glenn Jennie',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('DEPOSIT','DEPOSIT',0,'Glenn Jennie',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('BANK FEE','DEDUCTION',1,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('AIR BNB','INCOME',0,'Glenn Jennie',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('UQ','INCOME',0,'Glenn Jennie',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('SERVICE TOYOTA','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('REGO SUBARU','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PETROL SUBARU','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('SERVICE SUBARU','CAR',0,'Glenn Jennie',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('GROCERIES','FOOD',0,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CREDIT CARD','TRANSFER',0,'Glenn Jennie',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('REPAIRS','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('HOMEWARE','EXPENSE',0.5,'Glenn Jennie',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('TRAVEL','TRAVEL',0,'Glenn Jennie',68)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CHOOKS','FOOD',0.5,'Glenn Jennie',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('RACQ','TRANSPORT',0,'Glenn Jennie',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('INTEREST','INCOME',1,'Glenn Jennie',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('HAIR AND BEAUTY','LIFESTYLE',0,'Jackson',55)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('INTERBANK TRANSFER','TRANSFER',0,'Jackson',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CWTH SUPER','INCOME',0,'Jackson',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('QLD HEALTH','INCOME',1,'Jackson',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CLOTHING','FOOD',0,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('POST','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('TAX','TAX',0,'Jackson',88)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('MEDICAL INS REFUND','REFUND',0,'Jackson',77)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('HARDWARE','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('WITHDRAWAL','WITHDRAWAL',0,'Jackson',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('ACCOM','RECREATION',0,'Jackson',66)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('DINING','FOOD',0,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('WHITE GOODS','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('BARGE','TRANSPORT',0,'Jackson',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('FINE','TRANSPORT',0,'Jackson',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PETROL TOYOTA','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('MUSIC','RECREATION',0,'Jackson',66)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('QSUPER','INCOME',0,'Jackson',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('SERVICE VW','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CHARITY','DEDUCTION',1,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('ART','HOMEWARE',0,'Jackson',44)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('WATER TAXI','TRANSPORT',0,'Jackson',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PHONE','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('MEDICAL','DEDUCTION',1,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('INTERNET','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('TAX_AGENT','DEDUCTION',1,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('ALCOHOL','FOOD',0,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('POWER','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PARKING','TRANSPORT',0,'Jackson',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PETROL VW','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('REGO VW','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('HOUSE INSURANCE','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('BUS','TRANSPORT',0,'Jackson',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('RATES','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('GAS','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('FISHING','RECREATION',0,'Jackson',66)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('REGO TOYOTA','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('TRANSFER','TRANSFER',0,'Jackson',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('DEPOSIT','DEPOSIT',0,'Jackson',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('BANK FEE','DEDUCTION',1,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('UQ','INCOME',0,'Jackson',11)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('SERVICE TOYOTA','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('REGO SUBARU','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('PETROL SUBARU','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('SERVICE SUBARU','CAR',0,'Jackson',70)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('GROCERIES','FOOD',0,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CREDIT CARD','TRANSFER',0,'Jackson',99)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('REPAIRS','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('HOMEWARE','EXPENSE',0.5,'Jackson',22)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('TRAVEL','TRAVEL',0,'Jackson',68)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('CHOOKS','FOOD',0.5,'Jackson',33)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('RACQ','TRANSPORT',0,'Jackson',67)");
//		stmt.executeUpdate("Insert into category (name,type,DEDUCTION,users,sort_order) values ('INTEREST','INCOME',1,'Jackson',11)");
//		
//		
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','POINT LOOKOUT BLUE R','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER DEBIT TO 450201286 REFERENCE NO 54613381 tax','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('SERVICE VW','RABY BAY TYRE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BANK FEE','FOREIGN CURRENCY CONVERSION FEE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','Agnes Water','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','ISLAND FRUIT BARN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','UQ TRAFFIC AND PARK','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','SKIPS FIRST STOP DUNWICH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','IGA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','MITRE 10','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','CLEVELAND FRUIT BARN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HAIR AND BEAUTY','Activeskin Solutions','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','POINT LOOKOUT NEWS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','Bell speedy bell - DIRECT CREDIT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 450201286 REF NO 54529402 surf trip','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET EXTERNAL TRANSFER TO 923100 036362017 REF NO 17695631 ing startup','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','HARVEY NORMAN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','EW LEAF NATRL THRPIES WYNNUM','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','LOGAN HEART SERVICE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','EW LEAF','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('CHARITY','NIGHT NINJAS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('TRAVEL','BNEAIR BNEAIR Int LS Dep Brisban - Receipt 5669ATM owner fee of $2.50 charged by BNEAIR BNEAIR Int LS Dep BrisbanDate 30 Jan 2017 Time 9:27PM Card 462263xxxxxx4176','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER DEBIT TO 29272623','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','BAC PARKING','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','MOGO TRADING','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','MANNYS DELI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','LIQUORLAND','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('FISHING','EARLY BAIT AND TACKL ULLADULLA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','ULLADULLA PHARMAC','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','SIX SISTERS LAKES ENTRAN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','THE COFFEE CLUB','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','CGS COFFEE & GRUB BETHANIA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','Stradbroke Pharmacy Dunwich','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','BRUNSWICK VILLAGE GREEN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','DELUXE LINEN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','MICKS NUT SHOP','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HAIR AND BEAUTY','BEAUTY AT BARDON','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','Initial Deposit - Internal Transfer - Receipt 696801 Savings Maximiser 0089263069','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','Esther Lodge H 3118973 TATHRA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','INTERNET EXTERNAL TRANSFER TO 923100 035245805 REF NO 22736460 Building supplies','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HAIR AND BEAUTY','SQ*STRANDED','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','CAFE ARABICA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','DYMOCKS CARINDALE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','SQ*STARFISH STUDIO','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BARGE','Stradbroke F*','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PETROL VW','BP ','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','COLES','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BANK FEE','VISA CASH ADVANCE FEE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','BUPA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('FISHING','STRADDIE SUPER SPORT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','Dr D Turkiewicz Greenslope','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','MALOUF GROUP PHMCY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','MYER LOGAN LOGANHOLME','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','SPAR MACLEAN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','MACLEAN CELLARS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','BATHURST REGN COUNCIL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ART','WITZIG & ASSOCIATES','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','CafeEscargot','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PETROL VW','CALTEX','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','EL CAMINODE BULLI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','THE BEAN & LEAF CAFE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','SHEESH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REPAIRS','014274347582599 REF NO 9458540','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','LA DOLCE VITA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','Spirithouse Yandina','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('TRAVEL','TIGER AIR','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','THAISONS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','CW CORNUBIA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','GRAND VIEW HOTEL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','THE ORMISTON BAKERY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','WATERFORD DIS FRUIT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','LA BOUCHE WELLINGTON','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','GLOBAL FINE FOOD ASHGROVE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PHONE','OFFICEWORKS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','Stradbroke Hotel','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PETROL VW','QLD MOTORWAYS MANAGEME EIGHT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','MANNYS FRUIT MARKET','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','WOODFIRED ETC TATHRA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','DRAKE SUPERMARKET','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','VISA CASH ADV 00000000000/HT MERCURE PADANG  21/01 ID 1250000.000 IDR','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HAIR AND BEAUTY','REV SIMPLY ME CLEVELAND  CLEVELAND AU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','WATERFORD DISCOUNT  HILTON  AU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','TRANSLINK BRISBANE  18/02 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PETROL TOYOTA','STRADDIE ROADHOUSE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','BOOKDEPOSITORY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('WATER TAXI','STRADBROKE FLYER WATER','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('AIR BNB','CITIBANK EUROPE Worldpay AP Ltd','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','Queensland X-Ray','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','BRUNSWICKVILLAGEGREENS BRUNSWICK HE 07/12 NS 47.110 AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','STRADBROKE ISLAND BUT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','RMS*Takarakka Bush R 0383999462 26/03 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','Surfstreet Point Lookou 28/12 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','UQ MORETON BAY STATI  NORTH STRA AU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('FISHING','MOSSOPS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','STRADDIE NEWS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REPAIRS','014218 254857661 REF NO 13269380 Inv* 3884','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','SQ *MADE ON MINJERRIBA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','Dr D Turkiewicz   Greenslope AU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REPAIRS','INTERNET EXTERNAL TRANSFER TO 014274 347582599 REF NO 94585401 BELL GL 108898','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ART','Salt water Mur','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','Esther Lodge H3118973 TATHRA2  AU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','DAX AUST PTY LTD TA BRUNSWICK HE 07/12 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','RUFUS KING SEAFOODS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','BELL ins isp - DIRECT CREDIT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','BRENLAW PTY LTD   ABERDEEN  AU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('TRAVEL','OEH WOMBEYAN CAVES TARALGA  15/12 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','NORTH STRADBROKE FOO  DUNWICH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REPAIRS','014218 254857661 REF NO 51495391 Inv* 3669','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','NORTH STRADBROKE FOO DUNWICH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','POST DUNWICH LPO DUNWICH  03/08 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','CWH CORNUBIA       AU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','MIRAJE HOME DECOR','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HAIR AND BEAUTY','SIMPLY ME CLEVELAND','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','WOOLWORTHS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','VISA CREDIT  TRANSLINK BRISBANE  26/04 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','NORTH STRADBROKE FOODW DUNWICH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('CHARITY','PCYC NAT LOTTERIE QLD WHALAN  02/12 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('TRAVEL','Tickets.com Pty Ltd th WollongNS 09/09 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','PILLOW TALK PTY LTD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 24243293 REF NO 37919321 vw','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','UQ MORETON BAY STATI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HAIR AND BEAUTY','SHOE IN PTY LTD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','BELL - DIRECT CREDIT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','PT LOOKOUT SOUVENIRS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 450201286 REF NO 59770341 xmas','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HAIR AND BEAUTY','STUDIO DIAMONDS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 29272623 REF NO 78320500','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER DEBIT TO 29272623 REFERENCE NO 4865912 EFFECTIVE DATE 16/09/2016','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL INS REFUND','MCARE BENEFITS 003079056 EYWQ - DIRECT CREDIT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','BARDON SMILES PTY LTD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL INS REFUND','MCARE BENEFITS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HAIR AND BEAUTY','SQ *STRANDED ON STRAD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BANK FEE','ATM Rebate - Receipt 6368We paid the ATM fee for you!','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('CWTH SUPER','CSC       60640077PS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BANK FEE','ATM Rebate','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','Internal Transfer','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','DCP-Foodworks Point LooSt radbroke Is','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','LITTLE PRINCE EATIN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','SQ *STARFISH STUDIO','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','Transfer to G C BELL - Receipt 211301 To 484799 24243293 - Transfer to G C BELL - Receipt 211301 To 484799 24243293','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('CLOTHING','DBS MENWEAR','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('POST','POST  POINT LOOKOUT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','1 - Internal Transfer - Receipt 235378 Savings Maximiser 0089263069','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','BELL GC     ing startup - Receipt 101688','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','rates and power - Transfer to G C BELL - Receipt 21490 To 484799 24243293','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','R L KOPPENOL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REPAIRS','INTERNET EXTERNAL TRANSFER TO 064466 010074783 REF NO 86207430 BELL, GC','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','STRADDIE BAKERY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','STRADBROKE RSL DUNWICH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','ROLLESTON GENERAL ST ROLLESTON','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','CARNARVON GORGE WILD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','SUNCORP BANK CLEVELAND QL - Receipt 8307ATM owner fee of $2.00 charged by SUNCORP BANK CLEVELAND QLDate 09 Jun 2017 Time 1:20PM Card 462263xxxxxx4176','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','BELL GLENN COLIN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','STARLIGHT MOTOR INN ROMA - EFTPOS Purchase - Receipt 278Date 04 Jun 2017 Time 3:41PM Card 462263xxxxxx4176','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REGO SUBARU','TMR REG RENEW 48272      10249312514707 - BPAY Bill Payment - Receipt 9850 To TMR REG RENEW 48272 10249312514707','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','Transfer to G C BELL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','Orchid Floral Design','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','ASPIRATIONAL HOSPITA CHILDERS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','MPH REDLANDS CLEVELAND - EFTPOS Purchase - Receipt 30085Date 18 Apr 2017 Time 1:11PM Card 462263xxxxxx4176','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 29272623 REF NO 16113311','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','CWH CAPALABA ','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','L/LAND','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','INTERNET TRANSFER DEBIT TO 509220860 REFERENCE NO 27898441 stuff','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','4 SIMPLICITY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','1ST CHOICE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 29272623 REF NO 78058462','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 29272623','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','PS SANDHU PTY LTD  ABERDEEN  AU - EFTPOS WDL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REPAIRS','INTERNET EXTERNAL TRANSFER TO 014218 254857661 REF NO 13269380 Inv* 3884','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REPAIRS','INTERNET EXTERNAL TRANSFER TO 014218 254857661 REF NO 51495391 Inv* 3669','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET EXTERNAL TRANSFER TO 923100 035245805 REF NO 58755310 Merry Xmas','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REPAIRS','INTERNET EXTERNAL TRANSFER TO 034115 000273629 REF NO 69043380 622507','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REGO SUBARU','TMR REG RENEW 48298      10245850093286 - BPAY Bill Payment - Receipt 535867 To TMR REG RENEW 48298 10245850093286','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','QUEENSLAND TRANSPORT  CLEVELAND AU - EFTPOS WDL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','R.L KOPPENOL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 509220860 REF NO 58883410 close','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('RATES','BPAY DEBIT VIA INTERNET REDLAND COUNCIL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('POWER','BPAY DEBIT VIA INTERNET LUMO ENERGY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOUSE INSURANCE','BPAY DEBIT VIA INTERNET RACQ INSURANCE REFERENCE NUMBER  3369','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PHONE','BPAY DEBIT VIA INTERNET TELSTRA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('CREDIT CARD','BPAY DEBIT VIA INTERNET CITIBANK CREDITCARDS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('RACQ','BPAY DEBIT VIA INTERNET RACQ     92000093157329 REFERENCE NUMBER 32028391','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('POWER','BPAY DEBIT VIA INTERNET RED ENERGY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GAS','BPAY DEBIT VIA INTERNET ORIGIN ENERGY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','BPAY DEBIT VIA INTERNET SDRO PENALTIES  89460788327336 REFERENCE NUMBER 52889322','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','BPAY DEBIT VIA INTERNET','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTEREST','Interest Credit','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTEREST','Bonus Interest Credit','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','Initial Deposit - Receipt 696801 - From Orange Everyday','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('WHITE GOODS','JB HI FI - Visa Purchase - Receipt 171662In BRISBANE Date 30 Mar 2017 Card 462263xxxxxx4176','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','Medicare Benefit','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('UQ','UNI OF QLD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MUSIC','ALBUMWORKS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','SHINGLE INN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('QSUPER','QSUPER','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','Sunburst Outdoor Livin','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('TAX','ATO','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('CHARITY','World Vision','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','STRADBROKE ISLAN DBUT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','BUNNINGS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','WORDSMITHS CAFE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('QLD HEALTH','QUEENSLAND HEALT SALARY 00190392','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERNET','IINET','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','BCC ON-STREET PARKING','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('FISHING','BCF','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','MAROOCHYDORE DISCOUN MAROOCHYDORE 10/08 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','NEWMARKET GARDENS CARA ASHGROVE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','DAN MURPHY''S','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','MURA TRAIN SUSHI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','WHALES WAY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','TAKARU NATIONAL PROP ROLLESTON','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','RED LOTUS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','REDLANDS INDIGISCAPE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','WHO GIVES A CRA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','OCEANIC GELATI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','SING''S ASIAN KITCHEN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','PADDINGTON HARDWARE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','923100035245805 REF NO 22736460 Building supplies','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','CHEMIST WAREHOUSE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','UQ MORETONBAY STATI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','LITTLE SHIP CLUB','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','ARBONNE EUROPE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','ORMISTON FAMILY PHAR','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','STRADDIE SERVO','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','UNITED CHURCHILL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','THE HIDDEN DONUT ABERDEEN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BANK FEE','ACCOUNT KEEPING FEE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','Cafe Escargot','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','Invoice 18742 - Transfer to Dr V Swainsbury - Receipt 507237 To 084004 846163911','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('WITHDRAWAL','ATM WITHDRAWAL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BANK FEE','ATM OPERATOR FEE BALANCE ENQUIRY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('AIR BNB','ENVOY SERVICES','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','CLEVELAND TOBACCO','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 29272623 REF NO 96934471','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BANK FEE','ATM OPERATOR FEE WITHDRAWAL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PETROL VW','WESTSIDE PETROLEUM BRAIDWOOD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PHONE','VIRGIN MOBILE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','THE PRAWN SHACK','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BARGE','STRADBROKE FERRIES','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('SERVICE VW','AARONS MECHANICAL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('RATES','REDLAND COUNCIL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER DEBIT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('POWER','RED ENERGY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PHONE','TELSTRA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','BOBS SHOP','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','INTERNET TRANSFER CREDIT FROM 450201286 REF NO 90159710','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REGO VW','BPAY DEBIT VIA INTERNET TMR REG RENEW 48272 REFERENCE NUMBER  7099','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','Seymour St Newsagency','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','LITTLE PRINCE EATIN TRARALGON','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('SERVICE SUBARU','STRADBROKE AUTO','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','Pt Lookout Pharmacy','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','JOHN TRUMAN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','PAPABUNZ MBL JAKARTA  21/01 ID 109200.000 IDR','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','P&C T3 DT CENTRAL SINGAPORE 21/01 SG 111.100 SGD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('REGO TOYOTA','BPAY DEBIT VIA INTERNET TMR REG RENEW 48272 REFERENCE NUMBER  9779','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('CREDIT CARD','CITIBANK CREDIT CARDS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','BAC PARKING ASCOT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','JR DUTY FRE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','LGB MEDICAL GREENSLOPES','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','ALDI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('POWER','LUMO ENERGY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GAS','ORIGIN ENERGY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTERBANK TRANSFER','1 - Receipt 235378 - From Orange Everyday','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','Queensland XRay','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','D P CARROL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','STARBUCKS','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('TRAVEL','JETSTAR','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','RAFFERTY''S CAFE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PARKING','METRO PARKING','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','THE REJECT SHOP','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','NOREENS SEASIDE SHOP','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('BANK FEE','TRANSACTION FEE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','BOOK DEPOSITORY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('CHOOKS','CAPALABA PRODUCE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOUSE INSURANCE','RACQ INSURANCE REFERENCE NUMBER 3369','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','STRADDIE BAKERY DUNWICH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('INTEREST','CREDIT INTEREST','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','STRADBROKE PHARMACY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','STRADDIE SERVO DUNWICH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('RACQ','RACQ 92000093157329 REFERENCE NUMBER 32028391','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('FISHING','STRADDIE SUPER SPORT DUNWICH','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('MEDICAL','SURF STREET HEALTH POINT LOOKOU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','WYNNUM FRUIT AND VEG','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','WRAY ORGANIC CLEVE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('FISHING','AMITY POINT INVEST','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','ARRIVEDERCI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('SERVICE SUBARU','STRADBROKE AUTOMOTIV','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','BOOKTOPIA','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ALCOHOL','DAN MURPHY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('TAX_AGENT','BIRD WALKER MCDONALD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','PSS AND HU PTY LTD ABERDEEN','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','BOOLARRA STORE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','DAX AUST PTY LTD TA BRUNSWICKHE','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HOMEWARE','S/ARMY','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','FISHES AT THE POINT','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('DINING','EL CAMINO DE BULLI','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','OEH DIAMOND HEAD CAM','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('PETROL VW','SHELL','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('ACCOM','SAPPHIRE RESORT TH BRISBANQL 31/01 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('GROCERIES','EGGINS LAURIETON NEWSA LAURIETON 07/12 AU AUD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('RACQ','RACQ EIGHT MILE PLAIN EIGHT MILE P','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('SERVICE TOYOTA','ABS CLEVELAND PTY LTD ORMISTON  AU','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','SANDBAG LTDWASTE 441189569183 12/09 GB 12.990 USD','Glenn Jennie')");
//		stmt.executeUpdate("insert into cat_trans_lnk (category,search,users) values ('HARDWARE','923100 035245805 REF NO 22736460 Building supplies','Glenn Jennie')");
//		// insert 2 rows
////		stmt.executeUpdate("insert into users values (3,'glenn')");
////		stmt.executeUpdate("insert into users values (4,'jennie')");
//
		// query
		ResultSet rs = stmt.executeQuery("SELECT name FROM category");
		
		// print out query result
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
	}
}