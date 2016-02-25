package tokens;

public class UnimplementedLanguageFeatureException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public UnimplementedLanguageFeatureException(){
		super("Language feature unimplemented");
	}
	
	public UnimplementedLanguageFeatureException(String feature){
		super("Language feature unimplemented " + feature);
	}
}
