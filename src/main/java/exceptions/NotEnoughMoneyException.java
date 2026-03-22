package exceptions;
public class NotEnoughMoneyException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public NotEnoughMoneyException()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public NotEnoughMoneyException(String s) {
	  super(s);
  }
}