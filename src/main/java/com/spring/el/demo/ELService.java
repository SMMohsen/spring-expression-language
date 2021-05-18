package com.spring.el.demo;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Map;

@Service
public class ELService {

    public Object evaluateExpression(ObjectNode params) {
        StandardEvaluationContext context = new StandardEvaluationContext();
        ExpressionParser expressionParser = new SpelExpressionParser();
        String rule = params.get("rule").asText();
        Iterator<Map.Entry<String, JsonNode>> fields = params.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            rule = rule.replace(entry.getKey(), "#" + entry.getKey());
            context.setVariable(entry.getKey(), extractValueFromJsonNode(entry.getValue()));
        }
        Expression expression = expressionParser.parseExpression(rule);
        return expression.getValue(context);
    }

    private Object extractValueFromJsonNode(JsonNode node) {
        if (node.isInt())
            return node.asInt();
        else if (node.isDouble())
            return node.asDouble();
        else if (node.isTextual())
            return node.asText();
        else
            return node;
    }

}
