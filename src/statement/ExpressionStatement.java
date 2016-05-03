package statement;

public class ExpressionStatement implements Statement{
	
	private Expression expression;
	
	public ExpressionStatement(Expression expression){
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
	}
}
