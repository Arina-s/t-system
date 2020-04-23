package com.tsystems.javaschool.tasks.calculator;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        String result = null;
        if (statement != null && checkStatement(statement)) {
            try {
                result = String.valueOf(new ScriptEngineManager().getEngineByName("JavaScript").eval(statement));
            } catch (ScriptException e) {
                result = null;
            }
        }
        result = checkResult(result);
        return result;
    }

    private boolean checkStatement(String statement) {
        for (int i = 0; i < statement.length(); i++) {
            char character = statement.charAt(i);
            if (character < 40 || character > 57 || character == 44) {
                return false;
            }
            if (i != statement.length() - 1) {
                char next = statement.charAt(i + 1);
                if (character >= 42 && character <= 47 && next >= 42 && next <= 47) {
                    return false;
                }
            }
        }
        return true;
    }

    private String checkResult(String result) {
        if (result != null) {
            if (result.contains(".") && (result.length() - result.indexOf(".") > 4)) {
                result = round(result);
            }
            if (result.equals("Infinity") || result.equals("null")) {
                result = null;
            }
        }
        return result;
    }

    private String round(String value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return String.valueOf(bd);
    }

}
