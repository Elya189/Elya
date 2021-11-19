package example.lab1;

/**
 *  * @author Shaidylina Elvira
 *  * @version 1.0
 *  * @since 1.0
 *  Task 1
 */
public class Lab1 {

    /**
     * A function for converting from decimal to binary
     * @param number - A number in decimal notation
     * @return - Returns a number in binary notation, which is converted to String for convenience
     */
    public static String from10to2(int number) {
        StringBuilder stringBuilder = new StringBuilder();
        while (number > 1) {
            int tempDel = number / 2;
            int tempOst = number % 2;
            stringBuilder.append(tempOst);
            number = tempDel;
        }

        if (number % 2 == 1) {
            stringBuilder.append(1);
        }

        return stringBuilder.reverse().toString();
    }

    /**
     * A function for converting from binary to decimal
     * @param number - A binary number converted to String for convenience
     * @return - Returns a decimal number
     */
    public static int from2to10(String number) {
        int result = 0;
        for (int i = number.length() - 1; i >= 0; i--) {
            result += Integer.parseInt(String.valueOf(number.charAt(number.length() - i - 1))) * (int) Math.pow(2, i);
        }
        return result;
    }
}
