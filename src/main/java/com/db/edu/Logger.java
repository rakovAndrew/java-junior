package com.db.edu;

public class Logger {
    public static final String PREFIX_PRIMITIVE = "primitive";
    public static final String PREFIX_CHAR = "char";
    public static final String PREFIX_STRING = "string";
    public static final String PREFIX_REFERENCE = "reference";
    public static int intSum;
    public static String previousString;
    public static String previousType = "no";
    public static int numberOfStrings;

    private Logger() {

    }

    private static boolean isTypeChanged() {
        return !("int".equals(previousType));
    }

    public static void log(int message) {
        if(isTypeChanged()) {
            writePreviousMessage();
        }
        accumulation(message);
        System.out.println(PREFIX_PRIMITIVE + ": " + message);
    }

    public static void log(byte message) {
        System.out.println(PREFIX_PRIMITIVE + ": " + message);
    }

    public static void log(char message) {
        System.out.println(PREFIX_CHAR + ": " + message);
    }

    public static void log(String message) {
        writePreviousMessage();
        accumulation(message);
        System.out.println(PREFIX_STRING + ": " + message);
    }

    public static void log(boolean message) {
        System.out.println(PREFIX_PRIMITIVE + ": " + message);
    }

    public static void log(Object message) {
        System.out.println(PREFIX_REFERENCE + ": " + message);
    }

    public static void writePreviousMessage() {
        switch (previousType) {
            case "String":
                writeString();
                break;
            case "int":
                writeInt();
                break;
        }

    }

    private static void writeInt() {
        System.out.println(PREFIX_PRIMITIVE + ": " + intSum);
        intSum = 0;
    }

    private static void writeString() {
        if(numberOfStrings == 1){
            System.out.println(PREFIX_STRING + ": " + previousString + "\n");
        } else {
            System.out.println(PREFIX_STRING + ": " + previousString
                    + " (x" + numberOfStrings + ")\n");
        }
    }

  /*  private static void accumulation(int message) {
        previousType = "int";
        int div = (intSum + message) / Integer.MAX_VALUE;
        if (div > 0) {
            System.out.println(PREFIX_PRIMITIVE + ": " + Integer.MAX_VALUE);
            intSum =  (intSum + message) % Integer.MAX_VALUE;
        } else {
            intSum += message;
        }
    }*/

    private static void accumulation(int message) {
        previousType = "int";
        if(!veryBig(message) && !verySmall(message)) {
            intSum += message;
        }
    }

    private static boolean veryBig(int message) {
        if((intSum + message < 0) && (intSum > 0) && (message > 0)){
            System.out.println(PREFIX_PRIMITIVE + ": " + Integer.MAX_VALUE);
            intSum = intSum - Integer.MAX_VALUE + message;
            return true;
        }
        return false;
    }

    private static boolean verySmall(int message) {
        if((intSum + message > 0) && (intSum < 0) && (message < 0)){
            System.out.println(PREFIX_PRIMITIVE + ": " + Integer.MIN_VALUE);
            intSum = intSum + Integer.MIN_VALUE + message;
            return true;
        }
        return false;
    }

    private static void accumulation(String message) {
        previousType = "String";
        if (message.equals(previousString)) {
            numberOfStrings++;
        } else {
            previousString = message;
            numberOfStrings = 1;
        }
    }
}