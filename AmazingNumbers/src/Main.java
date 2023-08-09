import java.util.Scanner;
import java.util.HashSet;

enum Propertiess {
    EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD
}

public class Main {

    static boolean goodNumber (long number) {
        String tmp = Long.toString(number);
        if (tmp.length() > 1) {
            return tmp.charAt(0) != '0' && number >= 0;
        } else {
            return number >= 0;
        }
    }
    static boolean goodProperty(String property) {
        for(Propertiess propertiess : Propertiess.values()) {
            if (propertiess.toString().equals(property)) {
                return true;
            }
        }
        return false;
    }
    static boolean divideBy7 (long number) {
        return number % 7 == 0;
    }
    static boolean endsWith7 (long number) {
        String tmp = Long.toString(number);
        return tmp.charAt(tmp.length() - 1) == '7';
    }

    static boolean parity (long number) {
        return number % 2 == 0;
    }

    static boolean has0 (long number) {
        String tmp = Long.toString(number);
        for(int i = 0; i < tmp.length(); i++) {
            if(i > 0 && tmp.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }

    static boolean gapfulNumbers(long number) {
        String tmp = Long.toString(number);
        if(tmp.length() >= 3) {
            String divisor = "" + tmp.charAt(0) + tmp.charAt(tmp.length()-1);
            int divisorI = Integer.parseInt(divisor);
            return number % divisorI == 0;
        }else {
            return false;
        }

    }

    static boolean spyNumbers(long number) {
        long sum = 0;
        long product = 1;
        long digit;
        while(number > 0) {
            digit = number % 10;
            sum += digit;
            product *= digit;
            number /= 10;
        }
        return sum == product;
    }

    static boolean buzzNumbers(long number) {
        return divideBy7(number) || endsWith7(number);
    }

    static boolean duckNumbers(long number) {
        return has0(number);
    }

    static boolean palindromicNumbers(long number) {
        String tmp = Long.toString(number);
        StringBuilder tmp2 = new StringBuilder(tmp);
        String reverse = tmp2.reverse().toString();
        return tmp.equals(reverse);
    }
    static boolean squareNumbers(long number) {
        return Math.sqrt(number) == (int)Math.sqrt(number);
    }
    static boolean sunnyNumbers(long number) {
        return squareNumbers(number + 1);
    }
    static boolean jumpingNumber(long number) {
        while (number != 0) {
            long digit1 = number % 10;
            number = number / 10;
            if (number != 0) {
                long digit2 = number % 10;
                if (Math.abs(digit1 - digit2) != 1) {
                    return false;
                }
            }
        }
        return true;
    }
    static long numSquareSum(long n)
    {
        long squareSum = 0;
        while (n!= 0)
        {
            squareSum += (n % 10) * (n % 10);
            n /= 10;
        }
        return squareSum;
    }

    static boolean happyNumber(long n) {
        HashSet<Long> st = new HashSet<>();
        while (true) {
            n = numSquareSum(n);
            if (n == 1)
                return true;
            if (st.contains(n))
                return false;
            st.add(n);
        }
    }

    static int testInput(String input) {
        if (input.isEmpty()) return 0; //input is empty
        if (input.equals("0")) return 1; //input is '0'(exit)
        String [] stringSplit = input.split(" ");
        if (stringSplit.length == 1) return 2; //first option
        if (stringSplit.length == 2) return 3; //second option
        if (stringSplit.length >= 3) return 4; //third option
        return 0;
    }

    static void showInfo (Long number) {
        System.out.println("Properties of " + number);
        System.out.println("buzz: " + buzzNumbers(number));
        System.out.println("duck: " + duckNumbers(number));
        System.out.println("palindromic: " + palindromicNumbers(number));
        System.out.println("gapful: " + gapfulNumbers(number));
        System.out.println("spy: " + spyNumbers(number));
        System.out.println("square: " + squareNumbers(number));
        System.out.println("sunny: " + sunnyNumbers(number));
        System.out.println("jumping: " + jumpingNumber(number));
        System.out.println("happy: " + happyNumber(number));
        System.out.println("sad: " + !happyNumber(number));
        System.out.println("even: " + parity(number));
        System.out.println("odd: " + !parity(number));
        System.out.println();
    }
    static void showInfo2 (Long number) {
        StringBuilder info = new StringBuilder();
        info.append(number).append(" is");
        if(buzzNumbers(number)) info.append(" buzz,");
        if(duckNumbers(number)) info.append(" duck,");
        if(palindromicNumbers(number)) info.append(" palindromic,");
        if(gapfulNumbers(number)) info.append(" gapful,");
        if(spyNumbers(number)) info.append(" spy,");
        if(squareNumbers(number)) info.append(" square,");
        if(sunnyNumbers(number)) info.append(" sunny,");
        if(jumpingNumber(number)) info.append(" jumping,");
        if(happyNumber(number)) info.append(" happy,");
        if(!happyNumber(number)) info.append(" sad,");
        if(parity(number)) {
            info.append(" even,");
        }else {
            info.append(" odd,");
        }
        info.deleteCharAt(info.length() - 1);
        System.out.println(info);
    }
    static void availableProperties() {
        StringBuilder info = new StringBuilder("Available properties: [");
        for (Propertiess propertiess : Propertiess.values()) {
            info.append(propertiess).append(", ");
        }
        info.deleteCharAt(info.length() -1);
        info.setCharAt(info.length() - 1, ']');
        System.out.println(info);
    }
    static boolean goodProperties (String[] property) {
        String[] tmpProperty = new String[property.length];
        for(int i = 0; i < property.length; i++) {
            if(property[i].charAt(0) == '-') {
                StringBuilder tmp = new StringBuilder(property[i]);
                tmp.deleteCharAt(0);
                tmpProperty[i] = tmp.toString();
            }else {
                tmpProperty[i] = property[i];
            }
        }
        StringBuilder info = new StringBuilder("The property [");
        int badProperties = 0;
        for (String s : tmpProperty) {
            if (!goodProperty(s)) {
                info.append(s.toUpperCase()).append(", ");
                badProperties++;
            }
        }
        info.delete(info.length() - 2, info.length());
        if(badProperties == 1) {
            info.append("] is wrong.");
            System.out.println(info);
            availableProperties();
            return false;
        }else if(badProperties > 1) {
            info.replace(11,12,"ies");
            info.append("] are wrong.");
            System.out.println(info);
            availableProperties();
            return false;
        }else {
            return true;
        }
    }

    static void possibleCommands() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");
    }

    static void firstOption(String input) {
        try {
            long number = Long.parseLong(input);
            if(goodNumber(number)) {
                showInfo(number);
            }else {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println();
            }
        }catch (Exception e) {
            System.out.println("The first parameter should be a natural number or zero.");
            System.out.println();
        }
    }

    static void secondOption(String input) {
        String[] numbers = input.split(" ");
        long startingNumber = Long.parseLong(numbers[0]);
        long howMany = Long.parseLong(numbers[1]);
        if(goodNumber(startingNumber)) {
            if(goodNumber(howMany)) {
                for(int i = 0; i < howMany; i++) {
                    showInfo2(startingNumber + i);
                }
            }else {
                System.out.println("The second parameter should be a natural number.");
            }
        }else {
            System.out.println("The first parameter should be a natural number or zero.");
        }
    }

    static int searchProperty(String property, Long number) {
        switch (property) {
            case "BUZZ":
                if (buzzNumbers(number)) {
                    return 1;
                }
                break;
            case "DUCK":
                if (duckNumbers(number)) {
                    return 1;
                }
                break;
            case "PALINDROMIC":
                if (palindromicNumbers(number)) {
                    return 1;
                }
                break;
            case "GAPFUL":
                if (gapfulNumbers(number)) {
                    return 1;
                }
                break;
            case "SPY":
                if (spyNumbers(number)) {
                    return 1;
                }
                break;
            case "SQUARE":
                if (squareNumbers(number)) {
                    return 1;
                }
                break;
            case "SUNNY":
                if (sunnyNumbers(number)) {
                    return 1;
                }
                break;
            case "EVEN":
                if (parity(number)) {
                    return 1;
                }
                break;
            case "ODD":
                if (!parity(number)) {
                    return 1;
                }
                break;
            case "JUMPING":
                if(jumpingNumber(number)) {
                    return 1;
                }
                break;
            case "HAPPY":
                if(happyNumber(number)) {
                    return 1;
                }
                break;
            case "SAD":
                if(!happyNumber(number)) {
                    return 1;
                }
                break;

        }
        return 0;
    }
    static boolean exclusiveProperties(String[] input) {
        StringBuilder info = new StringBuilder("The request contains mutually exclusive properties:");
        for(String str : input) {
            switch (str) {
                case "SQUARE" -> {
                    for (String str1 : input) {
                        if (str1.equals("SUNNY")) {
                            info.append(" [SQUARE, SUNNY]\n");
                            info.append("There are no numbers with these properties.");
                            System.out.println(info);
                            return false;
                        }
                    }
                }
                case "EVEN"-> {
                    for (String str1 : input) {
                        if (str1.equals("ODD")) {
                            info.append(" [EVEN, ODD]\n");
                            info.append("There are no numbers with these properties.");
                            System.out.println(info);
                            return false;
                        }
                    }
                }
                case "-EVEN"-> {
                    for (String str1 : input) {
                        if (str1.equals("EVEN")) {
                            info.append(" [-EVEN, EVEN]\n");
                            info.append("There are no numbers with these properties.");
                            System.out.println(info);
                            return false;
                        }
                    }
                }
                case "DUCK" -> {
                    for (String str1 : input) {
                        if (str1.equals("SPY")) {
                            info.append(" [DUCK, SPY]\n");
                            info.append("There are no numbers with these properties.");
                            System.out.println(info);
                            return false;
                        }
                    }
                }
                case "-DUCK" -> {
                    for (String str1 : input) {
                        if (str1.equals("DUCK")) {
                            info.append(" [-DUCK, DUCK]\n");
                            info.append("There are no numbers with these properties.");
                            System.out.println(info);
                            return false;
                        }
                    }
                }
                case "SAD" -> {
                    for (String str1 : input) {
                        if (str1.equals("HAPPY")) {
                            info.append(" [SAD, HAPPY]\n");
                            info.append("There are no numbers with these properties.");
                            System.out.println(info);
                            return false;
                        }
                    }
                }
                case "-ODD" -> {
                    for (String str1 : input) {
                        if (str1.equals("-EVEN")) {
                            info.append(" [-ODD, -EVEN]\n");
                            info.append("There are no numbers with these properties.");
                            System.out.println(info);
                            return false;
                        }
                    }
                }
                case "ODD" -> {
                    for (String str1 : input) {
                        if (str1.equals("-ODD")) {
                            info.append(" [-ODD, -EVEN]\n");
                            info.append("There are no numbers with these properties.");
                            System.out.println(info);
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static String[] inputForThirdOption(String[] input) {
        String[] properties = new String[input.length - 2];
        for(int i = 2; i < input.length; i++) {
            properties[i-2] = input[i].toUpperCase();
        }
        return properties;
    }
    static void thirdOption(String input) {
        String[] numbers = input.split(" ");
        try {
            long startingNumber = Long.parseLong(numbers[0]);
            try {
                long amountLookingNumbers = Long.parseLong(numbers[1]);
                if(goodNumber(startingNumber)) {
                    if(goodNumber(amountLookingNumbers)) {
                        String[] properties = inputForThirdOption(numbers);
                        if(goodProperties(properties)) {
                            if(exclusiveProperties(properties)) {
                                long matchNumbers = 0;
                                while(matchNumbers != amountLookingNumbers) {
                                    int correctProperties = 0;
                                    int minusProperties = 0;
                                    for(String str : properties) {
                                        if(str.charAt(0) == '-') {
                                            StringBuilder tmp = new StringBuilder(str);
                                            tmp.deleteCharAt(0);
                                            if(searchProperty(tmp.toString(),startingNumber) == 1) {
                                                minusProperties++;
                                                break;
                                            }
                                            correctProperties++;
                                        }else if(searchProperty(str,startingNumber) == 1) {
                                            correctProperties++;
                                        }
                                    }
                                    if(correctProperties == properties.length && minusProperties == 0) {
                                        matchNumbers++;
                                        showInfo2(startingNumber);
                                    }
                                    startingNumber++;
                                }
                            }
                        }
                    }else {
                        System.out.println("The second parameter should be a natural number.");                    }
                }else {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            } catch (Exception e){
                System.out.println("The second parameter should be a natural number.");
            }
        }catch (Exception e) {
            System.out.println("The first parameter should be a natural number or zero.");
        }
    }

    static void menu() {
        Scanner sc = new Scanner(System.in);
        boolean nextStep = true;
        String number;
        System.out.println("Welcome to Amazing Numbers!\n");
        possibleCommands();
        while(nextStep) {
            System.out.println("Enter a request: > ");
            number = sc.nextLine();
            switch (testInput(number)) {
                case 0:
                    possibleCommands();
                    break;
                case 1:
                    System.out.println("Goodbye!");
                    nextStep = false;
                    break;
                case 2:
                    firstOption(number);
                    break;
                case 3:
                    secondOption(number);
                    System.out.println();
                    break;
                case 4:
                    thirdOption(number);
                    System.out.println();
                    break;
            }
        }
    }

    public static void main(String[] args) {
        menu();
    }
}