package cn.youye.spring.spel;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Collection;

/**
 * spel测试
 * Created by pc on 2016/8/17.
 */
public class SpELTest01 {

    public static void main(String[] args) {
        //创建解析器
        ExpressionParser parser = new SpelExpressionParser();
        //解析表达式
        Expression expression =
                parser.parseExpression("('Hello '+'World').concat(#end)");
        //构造上下文
        EvaluationContext context = new StandardEvaluationContext();
        //为end参数值来赋值
        context.setVariable("end", "!");
        //打印expression表达式的值
        System.out.println(expression.getValue(context));


        String expression1 = "2>1 and (!true or !false)";
        boolean result1 = parser.parseExpression(expression1).getValue(boolean.class);
        Assert.assertEquals(true, result1);

        String expression2 = "2>1 and (NOT true or NOT false)";
        boolean result2 = parser.parseExpression(expression2).getValue(boolean.class);
        Assert.assertEquals(true, result2);



    }


}
