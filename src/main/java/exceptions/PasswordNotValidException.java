package exceptions;
public class PasswordNotValidException extends Exception {
 private static final long serialVersionUID = 1L;
 
 public PasswordNotValidException()
  {
    super();
  }
  /**This exception is triggered if the question already exists 
  *@param s String of the exception
  */
  public PasswordNotValidException(String s)
  {
    super(s);
  }
}
