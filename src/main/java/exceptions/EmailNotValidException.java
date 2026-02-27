package exceptions;
public class EmailNotValidException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmailNotValidException()
	{
		super();
	}
	/**This exception is triggered if the question already exists 
	 *@param s String of the exception
	 */
	public EmailNotValidException(String s)
	{
		super(s);
	}
}
