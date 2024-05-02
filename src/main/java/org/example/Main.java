package org.example;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

    public static final String GOOD_RESULT_WITH_One_RelationalRestrictionContext = "a == 5 || == 6";

    public static final String GOOD_RESULT_WITH_One_RelationalRestrictionContext_WITH_CustomOperator = "a == 5 || customOperator 6";

    public static final String GOOD_RESULT_WITH_Two_RelationalRestriction = "a == 5 || b == 6";

    public static final String BAD_RESULT = "a == 5 || notCustomOperator(b) == 6";

    public static void main(String[] args) {
        InterpreterLexer lexer = new InterpreterLexer(CharStreams.fromString(BAD_RESULT));
        InterpreterParser parser = new InterpreterParser(new CommonTokenStream(lexer));

        InterpreterParser.ConditionalOrExpressionContext context = parser.conditionalOrExpression();

        printTree(context, parser, 0);
    }

    private static void printTree(ParseTree tree, Parser parser, int indent) {
        String toPrint = indentString(indent) + tree.getClass().getSimpleName() + " '" + tree.getText() + "'";
        System.out.println(toPrint);

        for (int i = 0; i < tree.getChildCount(); i++) {
            printTree(tree.getChild(i), parser, indent + 1);
        }
    }

    private static String indentString(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }
}
