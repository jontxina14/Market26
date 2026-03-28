package dataAccess;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Registered;
import domain.Sale;
import enums.MovementType;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.NotEnoughMoneyException;
import exceptions.SaleAlreadyExistException;

import domain.*;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	private  EntityManager  db;
	private  EntityManagerFactory emf;
	private static final int baseSize = 160;

	private static final String basePath="src/main/resources/images/";



	ConfigXML c=ConfigXML.getInstance();

	public DataAccess()  {
		if (c.isDatabaseInitialized()) {
			String fileName=c.getDbFilename();

			File fileToDelete= new File(fileName);
			if(fileToDelete.delete()){
				File fileToDeleteTemp= new File(fileName+"$");
				fileToDeleteTemp.delete();
				System.out.println("File deleted");
			} else {
				System.out.println("Operation failed");
			}
		}
		open();
		if  (c.isDatabaseInitialized()) 
			initializeDB();
		System.out.println("DataAccess created => isDatabaseLocal: "+c.isDatabaseLocal()+" isDatabaseInitialized: "+c.isDatabaseInitialized());

		close();

	}

	public DataAccess(EntityManager db) {
		this.db=db;
	}



	/**
	 * This method  initializes the database with some products and sellers.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		db.getTransaction().begin();

		try { 

			//Create sellers 
			Registered seller1=new Registered("seller1@gmail.com","123","123");
			Registered seller2=new Registered("seller22@gmail.com","Ane Gaztañaga","123");
			Registered seller3=new Registered("seller3@gmail.com","Test Seller","123");


			//Create products
			Date today = UtilDate.trim(new Date());

			//Create admin
			Admin admin1=new Admin("admin1@admin.com", "123");




			seller1.addSale("futbol baloia", "oso polita, gutxi erabilita", 2, 10,  today, null);
			seller1.addSale("salomon mendiko botak", "44 zenbakia, 3 ateraldi",2,  20,  today, null);
			seller1.addSale("samsung 42\" telebista", "berria, erabili gabe", 1, 175,  today, null);


			seller2.addSale("imac 27", "7 urte, dena ondo dabil", 1, 200,today, null);
			seller2.addSale("iphone 17", "oso gutxi erabilita", 2, 400, today, null);
			seller2.addSale("orbea mendiko bizikleta", "29\" 10 urte, mantenua behar du", 3,225, today, null);
			seller2.addSale("polar kilor erlojua", "Vantage M, ondo dago", 3, 30, today, null);

			seller3.addSale("sukaldeko mahaia", "1.8*0.8, 4 aulkiekin. Prezio finkoa", 3,45, today, null);


			db.persist(seller1);
			db.persist(seller2);
			db.persist(seller3);

			db.persist(admin1);


			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}


	/**
	 * This method creates/adds a product to a seller
	 * 
	 * @param title of the product
	 * @param description of the product
	 * @param status 
	 * @param selling price
	 * @param category of a product
	 * @param publicationDate
	 * @return Product
	 * @throws SaleAlreadyExistException if the same product already exists for the seller
	 */
	public Sale createSale(String title, String description, int status, float price,  Date pubDate, String sellerEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException {


		System.out.println(">> DataAccess: createProduct=> title= "+title+" seller="+sellerEmail);
		try {


			if(pubDate.before(UtilDate.trim(new Date()))) {
				throw new MustBeLaterThanTodayException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.ErrorSaleMustBeLaterThanToday"));
			}
			if (file==null)
				throw new FileNotUploadedException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.ErrorFileNotUploadedException"));

			db.getTransaction().begin();

			Registered seller = db.find(Registered.class, sellerEmail);
			if (seller.doesSaleExist(title)) {
				db.getTransaction().commit();
				throw new SaleAlreadyExistException(ResourceBundle.getBundle("Etiquetas").getString("DataAccess.SaleAlreadyExist"));
			}

			Sale sale = seller.addSale(title, description, status, price, pubDate, file);
			//next instruction can be obviated

			db.persist(seller); 
			db.getTransaction().commit();
			System.out.println("sale stored "+sale+ " "+seller);



			System.out.println("hasta aqui");

			return sale;
		} catch (NullPointerException e) {
			e.printStackTrace();
			db.getTransaction().commit();
			return null;
		}


	}

	/**
	 * This method retrieves all the products that contain a desc text in a title
	 * 
	 * @param desc the text to search
	 * @return collection of products that contain desc in a title
	 */
	/*public List<Sale> getSales(String desc) {
		System.out.println(">> DataAccess: getProducts=> from= "+desc);

		List<Sale> res = new ArrayList<Sale>();	
		TypedQuery<Sale> query = db.createQuery("SELECT s FROM Sale s WHERE s.title LIKE ?1",Sale.class);   
		query.setParameter(1, "%"+desc+"%");

		List<Sale> sales = query.getResultList();
		for (Sale sale:sales){
			res.add(sale);
		}
		return res;
	} */

	/**
	 * This method retrieves the products that contain a desc text in a title and the publicationDate today or before
	 * 
	 * @param desc the text to search
	 * @return collection of products that contain desc in a title
	 */
	public List<Sale> getPublishedSales(String desc, Date pubDate, String email) {
		System.out.println(">> DataAccess: getProducts=> from= "+desc);

		TypedQuery<Sale> query = db.createQuery("SELECT s FROM Sale s WHERE s.title LIKE ?1 AND s.pubDate <= ?2 AND s.saleStatus == 0",Sale.class);   
		query.setParameter(1, "%"+desc+"%");
		query.setParameter(2,pubDate);		

		List<Sale> sales = query.getResultList();
		ArrayList<Sale> ema = new ArrayList<Sale>();
		for(Sale s : sales) {
			if(!s.getSeller().getEmail().equals(email)) {
				ema.add(s);
			}
		}
		return ema;
	}

	public List<Sale> getOnSales(String email, String filter) {
		System.out.println(">> DataAccess: getOnSales=> from= " + email);
		List<Sale> res = db.find(Registered.class, email).getSales();
		return getFiltered(res, filter);
	}
	public List<Sale> getWhisList(String email, String filter) {
		System.out.println(">> DataAccess: getWhisList=> from= "+email);
		List<Sale> res = db.find(Registered.class, email).getWishList();
		return getFiltered(res, filter);
	}
	public List<Sale> getPurchased(String email, String filter) {
		System.out.println(">> DataAccess: getBought => from= "+email);
		List<Sale> res = db.find(Registered.class, email).getBought();
		return getFiltered(res, filter);
	}

	private ArrayList<Sale> getFiltered(List<Sale> list, String filter){
		ArrayList<Sale> res = new ArrayList<Sale>();
		for (Sale s : list) {
			if(s.getTitle().contains(filter)) res.add(s);
		}
		return res;
	}

	public ArrayList<Movement> getMovements(String email, MovementType type){
		TypedQuery<Movement> query = null;
		if(!type.equals(MovementType.ALL)) {
			query = db.createQuery("SELECT m FROM Movement m WHERE m.user.email = ?1 AND m.type = ?2",Movement.class);   
			query.setParameter(1, email);
			query.setParameter(2,type);	
		}else {
			query = db.createQuery("SELECT m FROM Movement m WHERE m.user.email = ?1",Movement.class);   
			query.setParameter(1, email);
		}
		List<Movement> sales = query.getResultList();
		ArrayList<Movement> ema = new ArrayList<Movement>(sales);
		return ema;
	}



	public void open(){
		String fileName=c.getDbFilename();
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);
			db = emf.createEntityManager();
		}
		System.out.println("DataAccess opened => isDatabaseLocal: "+c.isDatabaseLocal());


	}

	public BufferedImage getFile(String fileName) {
		File file=new File(basePath+fileName);
		BufferedImage targetImg=null;
		try {
			targetImg = rescale(ImageIO.read(file));
		} catch (IOException ex) {
			//Logger.getLogger(MainAppFrame.class.getName()).log(Level.SEVERE, null, ex);
		}
		return targetImg;

	}

	public BufferedImage rescale(BufferedImage originalImage)
	{
		System.out.println("rescale "+originalImage);
		BufferedImage resizedImage = new BufferedImage(baseSize, baseSize, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, baseSize, baseSize, null);
		g.dispose();
		return resizedImage;
	}



	public void close(){
		db.close();
		System.out.println("DataAcess closed");
	}


	public Registered isRegistered(String email, String pass) {
		if(pass == "") {
			TypedQuery<Registered> query = db.createQuery(
					"SELECT s FROM Registered s WHERE s.email = ?1",
					Registered.class
					);
			query.setParameter(1, email);

			//@Id-aren gatik bilatzen ari garenez, elementu bakarra dago 0 posizioan
			return query.getResultList().isEmpty()? null: query.getResultList().get(0);

		}else {
			TypedQuery<Registered> query = db.createQuery(
					"SELECT s FROM Registered s WHERE s.email = ?1 AND s.pass = ?2",
					Registered.class
					);

			query.setParameter(1, email);
			query.setParameter(2, pass);

			return query.getResultList().isEmpty()? null: query.getResultList().get(0);
		}
	}

	public Admin isAdmin(String email, String pass) {
		TypedQuery<Admin> query = db.createQuery(
				"SELECT s FROM Admin s WHERE s.email = ?1 AND s.password = ?2",
				Admin.class
				);

		query.setParameter(1, email);
		query.setParameter(2, pass);

		return query.getResultList().isEmpty()? null: query.getResultList().get(0);

	}




	public boolean removeSale(int saleNumber) {
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM Sale s WHERE s.saleNumber = ?1");
		query.setParameter(1, saleNumber);

		int deleted = query.executeUpdate();
		db.getTransaction().commit();

		if (deleted > 0) {
			System.out.println("Sale deleted correctly");
			return true;
		} else {
			System.out.println("No sale found with that number");
			return false;
		}
	}
	public boolean buySale(String mail, int saleNumber) throws NotEnoughMoneyException{
		db.getTransaction().begin();
		Registered buyer = db.find(Registered.class, mail);
		Sale sale = db.find(Sale.class, saleNumber);	

		if (buyer == null || sale == null) {
			db.getTransaction().commit();
			return false;
		}
		Registered seller = sale.getSeller();


		if (sale.getPrice() > buyer.getBalance()) {
			db.getTransaction().rollback();
			throw new NotEnoughMoneyException();
		}

		sale.setSaleStatus(1);

		double newBuyerBalance = buyer.getBalance()-sale.getPrice();
		buyer.addToBought(sale);
		buyer.setBalance(newBuyerBalance);
		buyer.addToMovements(new Movement(MovementType.BUY,sale.getPrice(),newBuyerBalance,sale,buyer));

		double newSellerBalance = seller.getBalance()+sale.getPrice();
		seller.setBalance(newSellerBalance);
		seller.addToMovements(new Movement(MovementType.SELL,sale.getPrice(),newSellerBalance,sale,seller));

		cleanWishLists(sale);

		db.getTransaction().commit();


		return true;

	}


	public void register(Registered seller) {
		db.getTransaction().begin();
		db.persist(seller);
		db.getTransaction().commit();
	}




	public boolean toggleWishList(String mail, int saleNumber) {

		boolean listanDago = isInWishList(mail, saleNumber);

		db.getTransaction().begin();
		Registered seller = db.find(Registered.class, mail);
		Sale sale = db.find(Sale.class, saleNumber);

		if (seller == null || sale == null) {
			db.getTransaction().commit();
			return false;
		}

		//Ezin daitezke bi trantsakzio aldi berean hasi, beraz hasieran ikusi dagoen edo ez
		if(!listanDago) seller.addToWishList(sale);
		else 			seller.removeFromWishList(sale);
		db.getTransaction().commit();
		return true;

	}

	public boolean isInWishList(String mail, int saleNumber) {
		db.getTransaction().begin();
		Registered seller = db.find(Registered.class, mail);
		Sale sale = db.find(Sale.class, saleNumber);

		if (seller == null || sale == null) {
			db.getTransaction().commit();
			return false;
		}
		db.getTransaction().commit();
		return seller.getWishList().contains(sale);


	}

	public void cleanWishLists(Sale sale) {

		TypedQuery<Registered> query = db.createQuery("SELECT r FROM Registered r",Registered.class);

		List<Registered> users = query.getResultList();

		for (Registered r : users) {
			if (r.getWishList().contains(sale)) {
				r.removeFromWishList(sale);
			}
		}

	}

	public Registered manageMoney(Registered r, double amount, MovementType type) throws NotEnoughMoneyException{
		db.getTransaction().begin();
		Registered reg = db.find(Registered.class, r.getEmail());
		double balance = reg.getBalance();
		if(type == MovementType.WITHDRAW) {
			if(balance - amount < 0 ) throw new NotEnoughMoneyException();
			reg.setBalance(balance - amount);
			reg.addToMovements(new Movement(type, amount ,balance-amount ,null, reg)); 
		}else if(type == MovementType.DEPOSIT ) {
			reg.setBalance(balance + amount);
			reg.addToMovements(new Movement(type, amount ,balance+amount ,null, reg)); 
		}
		db.getTransaction().commit();
		return reg;
	}





}
