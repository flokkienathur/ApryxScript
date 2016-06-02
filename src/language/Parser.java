package language;

import java.util.ArrayList;
import java.util.List;

import statement.Expression;
import statement.ExpressionStatement;
import statement.IdentifierExpression;
import statement.InvokeExpression;
import statement.OperatorExpression;
import statement.SetExpression;
import statement.StringExpression;
import tokens.Token;
import tokens.TokenType;
import tokens.UnexpectedTokenException;
import tokens.UnimplementedLanguageFeatureException;
import context.Context;

public class Parser {
	
	private Lexer lexer;
	
	public Parser(Lexer lexer){
		this.lexer = lexer;
		lexer.next();
	}
	
	public Context parseContext(){
		Context context = new Context();
		
		//TODO parse the context
		parseStatement(context);
		
		return context;
	}
	
	public void parseStatement(Context context){
		Token current = lexer.current();
		if(current.getType() == TokenType.KEYWORD){
			parseKeyword(context);
		}
		else if(current.getType() == TokenType.IDENTIFIER){
			context.addStatement(new ExpressionStatement(parseExpression()));
		}
		else if(current.getType() == TokenType.LINE_END){
			//Consume the line-end
			lexer.next();
		}
		else{
			throw new UnexpectedTokenException(current, TokenType.IDENTIFIER, TokenType.KEYWORD);
		}
	}
	
	public void parseKeyword(Context context){
		Token currentToken = lexer.current();
		if(currentToken.getType() != TokenType.KEYWORD)
			throw new UnexpectedTokenException(currentToken, TokenType.KEYWORD);
		
		if(currentToken.getData().equals(Language.VAR)){
			Token nameToken = lexer.next();
			if(nameToken.getType() != TokenType.IDENTIFIER)
				throw new UnexpectedTokenException(nameToken, TokenType.IDENTIFIER);
			
			Token operatorToken = lexer.next();
			
			context.addVariable(nameToken.getData());
			
			if(operatorToken.getType() == TokenType.EQUALS){
				//consume equals token
				lexer.next();
				Expression e = parseExpression();
				
				context.addStatement(new ExpressionStatement(new SetExpression(new IdentifierExpression(nameToken.getData()), e)));
			}
			
		}else{
			throw new UnimplementedLanguageFeatureException();
		}
		
	}
	
	public Expression parseExpression(){
		Expression lhs = parseExpressionSimple();
		
		Token operator = lexer.current();
		
		//plus or minus
		if(operator.getType() == TokenType.BINARYOPERATOR){
			lexer.next();
			Expression rhs = parseExpression();
			
			return new OperatorExpression(lhs,rhs);
		}
		
		//function call
		else if(operator.getType() == TokenType.BRACKET_OPEN){
			//consume the operator
			Token n = lexer.next();
			List<Expression> expressions;
			if(n.getType() != TokenType.BRACKET_CLOSE){
				expressions = parseExpressionList();
				n = lexer.current();
				if(n.getType() != TokenType.BRACKET_CLOSE)
					throw new UnexpectedTokenException(n, TokenType.BRACKET_CLOSE);
			}else{
				expressions = new ArrayList<Expression>();
			}
			
			return new InvokeExpression(lhs, expressions);
		}
		
		return lhs;
	}
	
	public List<Expression> parseExpressionList(){
		ArrayList<Expression> expressions = new ArrayList<Expression>();
		
		Expression exp = parseExpression();
		expressions.add(exp);
		while(lexer.current().getType() == TokenType.SEPERATOR){
			lexer.next();
			exp = parseExpression();
			expressions.add(exp);
		}
		
		return expressions;
	}
	
	public Expression parseExpressionSimple(){
		Token start = lexer.current();
		
		if(start.getType() == TokenType.IDENTIFIER){
			lexer.next();
			return new IdentifierExpression(start.getData());
		}
		if(start.getType() == TokenType.STRING){
			lexer.next();
			return new StringExpression(start.getData());
		}
		else{
			throw new UnexpectedTokenException(start, TokenType.IDENTIFIER);
		}
	}
	
}
