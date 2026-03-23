package businessLogic;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import dataAccess.DataAccess;
import domain.Sale;
import domain.MovementType;
import domain.Registered;
import exceptions.FileNotUploadedException;
import exceptions.MustBeLaterThanTodayException;
import exceptions.NotEnoughMoneyException;
import exceptions.SaleAlreadyExistException;
import gui.QueryType;

import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;


/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	 private static final int baseSize = 160;

		private static final String basePath="src/main/resources/images/";
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		dbManager=new DataAccess();		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		dbManager=da;		
	}
    

	/**
	 * {@inheritDoc}
	 */
   @WebMethod
	public Sale createSale(String title, String description,int status, float price, Date pubDate, String sellerEmail, File file) throws  FileNotUploadedException, MustBeLaterThanTodayException, SaleAlreadyExistException {
		dbManager.open();
		Sale product=dbManager.createSale(title, description, status, price, pubDate, sellerEmail, file);		
		dbManager.close();
		return product;
   };
	
   /**
    * {@inheritDoc}
    */
	@WebMethod 
	public List<Sale> getSales(String desc){
		dbManager.open();
		List<Sale>  rides=dbManager.getSales(desc);
		dbManager.close();
		return rides;
	}
	
	/**
	    * {@inheritDoc}
	    */
		@WebMethod 
		public List<Sale> getPublishedSales(String desc, Date pubDate, QueryType query, String email) {
			System.out.println(query);
			dbManager.open();
			System.out.println(query);
			
		
			List<Sale> rides= new ArrayList<Sale>();
			//if (query==null) System.out.println("Query==null");
			//else
			switch (query) {
			case ON_SALES:
				rides = dbManager.getOnSales(email);
				break;
			case PUBLISHED_SALES:
				rides = dbManager.getPublishedSales(desc,pubDate);
				break;
			case PURCHASED:
				rides = dbManager.getPurchased(email);
				break;
			case WISHLIST:
				rides = dbManager.getWhisList(email);
				break;
			}

					
			dbManager.close();
			return rides;
		}
	/**
	    * {@inheritDoc}
	    */
	@WebMethod public BufferedImage getFile(String fileName) {
		return dbManager.getFile(fileName);
	}

    
	public void close() {
		DataAccess dB4oManager=new DataAccess();
		dB4oManager.close();

	}

	/**
	 * {@inheritDoc}
	 */
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}
    /**
	 * {@inheritDoc}
	 */
    @WebMethod public Image downloadImage(String imageName) {
        File image = new File(basePath+imageName);
        try {
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @WebMethod public Registered isRegistered(String mail, String pass){
    	dbManager.open();
    	Registered b = dbManager.isRegistered(mail,pass);
    	dbManager.close();
    	return b;
    }
    
    @WebMethod public void register(Registered seller) {
    	dbManager.open();
    	dbManager.register(seller);
    	dbManager.close();
    }
    
    @WebMethod public boolean removeSale(int SaleNumber) {
        dbManager.open();
        boolean b = dbManager.removeSale(SaleNumber);
        dbManager.close();
        return b;
    }
    
    @WebMethod public boolean buySale(String mail, int SaleNumber) throws NotEnoughMoneyException{
    	//TODO
        dbManager.open();
        boolean b = dbManager.buySale(mail, SaleNumber);
        dbManager.close();
        return b;
    }
    
    @WebMethod public boolean toggleWishList(String mail, int saleNumber) {
        dbManager.open();
        boolean result = dbManager.toggleWishList(mail, saleNumber);
        dbManager.close();
        return result;
    }
    
	@WebMethod public boolean isInWishList(String mail, int saleNumber) {
		dbManager.open();
        boolean result = dbManager.isInWishList(mail, saleNumber);
        dbManager.close();
        return result;
	}



	@WebMethod public Registered manageMoney(Registered r,double amount, MovementType type) throws NotEnoughMoneyException {
		dbManager.open();
        Registered result = dbManager.manageMoney(r, amount, type);
        dbManager.close();
        return result;
	}

    
}

