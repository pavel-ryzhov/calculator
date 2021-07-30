package com.example.calculator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

public class Math2a {
    public static int accuracy = 10;
    private static final char[] signs = {'+', '-', '*', '/', '^', '√'};
    private static boolean contains(String c){
        for (Character x : signs) {
            if (c.equals(x + ""))return true;
        }
        return false;
    }
    public static BigDecimal splitandcount (String str){
        str = str.trim();
        LinkedList<String> ch = new LinkedList<>();
        LinkedList<String> zn = new LinkedList<>();
        LinkedList<String> sk = new LinkedList<>();
        String[] str1 = str.split(" ");
        for (int i = 0; i < str1.length; i++) {
            if (strcontainschars(str1[i])){
//                zn.add(str1[i]);
                ch.add("#");
//                sk.add("#");







                LinkedList<Character> l = new LinkedList<>();
                String s = "";
                for (int j = 0; j < str1[i].length(); j++) {
                    l.add(str1[i].charAt(j));
                    if (l.get(j) == '(' || l.get(j) == ')'){
                        s += l.get(j);
                    }
                }
                if (s.equals("")){
                    sk.add("#");
                    zn.add(str1[i]);
                }else{
                    while (l.contains('(')){
                        l.remove(l.indexOf('('));
                    }
                    while (l.contains(')')){
                        l.remove(l.indexOf(')'));
                    }
                    String g = "";
                    for (Character x : l) {
                        g += x;
                    }
                    zn.add(g);
                    sk.add(s);
                }








            }
            else {
                LinkedList<Character> l = new LinkedList<>();
                String s = "";
                for (int j = 0; j < str1[i].length(); j++) {
                    l.add(str1[i].charAt(j));
                    if (l.get(j) == '(' || l.get(j) == ')'){
                        s += l.get(j);
                    }
                }
                if (s.equals("")){
                    sk.add("#");
                    ch.add(str1[i]);
                }else{
                    while (l.contains('(')){
                        l.remove(l.indexOf('('));
                    }
                    while (l.contains(')')){
                        l.remove(l.indexOf(')'));
                    }
                    String g = "";
                    for (Character x : l) {
                        g += x;
                    }
                    ch.add(g);
                    sk.add(s);
                }
                zn.add("#");
            }
        }
//        System.out.println(ch);
//        System.out.println(zn);
//        System.out.println(sk);
        LinkedList[] o = {ch , zn, sk};
        return countwithbrackets(o);
    }
    public static String countwhithoutspace (String s){
        SimpleDateFormat r = new SimpleDateFormat("HH.mm");
//        System.out.println(s);
        boolean j = true;
        int y = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                y++;
            }else if (s.charAt(i) == ')'){
                y--;
            }
            if (y < 0){
                j = false;
            }
        }
        if (y != 0){
            j = false;
        }
        if (j) {
            try {
                s = "0+" + s;
                s = s.trim();
                LinkedList<Character> c = new LinkedList<>();
                for (int i = 0; i < s.length(); i++) {
                    c.add(s.charAt(i));
                }
                LinkedList<Integer> z = new LinkedList<>();
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == '(' && s.charAt(i + 1) != '('){
//                i++;
                    }else if(contains(s.charAt(i) + "") && i != 0){
                        if (s.charAt(i) != '√')z.add(i);
                        z.add(i + 1);
                    }
                }
                for (int i = 0; i < z.size(); i++) {
                    c.add(i + z.get(i), ' ');
                }
                s = "";
                for (int i = 0; i < c.size(); i++) {
                    s += c.get(i);
                }

                if (!s.equals(r.format(new Date()))) {
                    return "Result: " + splitandcount(s);
                }else {
                    return "\uD83E\uDD14";
                }
            } catch (Exception e) {
//                e.printStackTrace();
                if (s.equals("")) {
                    return "Enter text";
                } else {
                    return "Something is wrong";
                }
            }
        }else{
            return "Something is wrong";
        }
    }
    public static String forcountwhithoutspace(String s){
        s = s.replaceAll("arccos", "b");
        s = s.replaceAll("arcsin", "a");
        s = s.replaceAll("arctan", "d");
        s = s.replaceAll("cos", "c");
        s = s.replaceAll("sin", "s");
        s = s.replaceAll("tan", "t");
        s = s.replaceAll("lg", "g");
        s = s.replaceAll("ln", "n");
        s = s.replaceAll("×", "*");
        s = s.replaceAll("÷", "/");
        s = s.replaceAll("π", Math.PI + "");
        s = Math2.replaceall(s, "(-", "(0-");

        return countwhithoutspace(s);
    }
    private static BigDecimal count (BigDecimal a, BigDecimal b, String zn){
        switch (zn) {
            case "+":
                return a.add(b);
            case "-":
                return a.subtract(b);
            case "/": {
                try {
                    return a.divide(b);
                }catch (ArithmeticException e) {
                    return a.divide(b, new MathContext(accuracy + a.toBigInteger().divide(b.toBigInteger()).toString().length()));
                }
            }
            case "*":
                return a.multiply(b);
            default: {
                BigDecimal x = b.abs();
                BigDecimal y = BigDecimal.ONE;
                while (x.compareTo(BigDecimal.ZERO) != 0){
                    y = y.multiply(a);
                    x = x.subtract(BigDecimal.ONE);
                }
                if (b.compareTo(BigDecimal.ZERO) > 0) return y;
                else{
                    try {
                        return BigDecimal.ONE.divide(y);
                    }catch (ArithmeticException e) {
                        return BigDecimal.ONE.divide(y, new MathContext(accuracy + a.toBigInteger().divide(b.toBigInteger()).toString().length()));
                    }
                }
            }
        }
    }
    private static boolean strcontainschars(String s){
        for (int i = 0; i < s.length(); i++) {
            if (contains(s.charAt(i) + "")) return true;
        }
        return false;
    }
    private static BigDecimal countwithbrackets (LinkedList[] o){

        int y = -1;
        while (y != 0){
//            System.out.println(o[0]);
            int z = 0, x = 0;
            int[] z1 = new int[2];
            int[] x1 = new int[2];
            y = 0;
            cycle:
            for (int i = 0; i < o[2].size(); i++) {
                if (!o[2].get(i).equals("#")) {

                    y++;
                    if (o[2].get(i).toString().charAt(0) == '('){
                        z = i;
                        z1[0] = countsimbols(o[2].get(i).toString(), '(');
                        z1[1] = countsimbols(o[2].get(i).toString(), ')');
                    }
                    if (o[2].get(i).toString().charAt(o[2].get(i).toString().length() - 1) == ')') {
                        LinkedList<String> ch = new LinkedList<>();
                        LinkedList<String> zn = new LinkedList<>();
                        for (int j = z; j <= i; j++) {
                            ch.add(o[0].get(j).toString());
                            zn.add(o[1].get(j).toString());
                        }
                        if (z != i) {
                            x1[0] = countsimbols(o[2].get(i).toString(), '(');
                            x1[1] = countsimbols(o[2].get(i).toString(), ')');
                        }else{
                            x1[0] = 0;
                            x1[1] = 0;
                        }
                        o[0].set(z, countall(ch, zn) + "");
                        o[1].set(z, "#");
                        o[2].set(z, myltiplystring("(", z1[0] + x1[0] - 1) + myltiplystring(")", z1[1] + x1[1] - 1));
//                         System.out.println(z1[0] + x1[0]);
                        if (o[2].get(z).equals(""))
                            o[2].set(z, "#");
                        for (int j = z + 1; j <= i; j++) {
                            o[0].remove(z + 1);
                            o[1].remove(z + 1);
                            o[2].remove(z + 1);
                        }
                        break cycle;
                    }
                }
            }
        }
        return countall(o[0], o[1]);
    }
    private static int countsimbols (String s, char d){
        int y = 0;
        for (int i = 0; i < s.length(); i++) {
            if (d == s.charAt(i))
                y++;
        }
        return y;
    }
    private static String myltiplystring (String s, int x){
        String y = "";
        for (int i = 0; i < x; i++) {
            y += s;
        }
        return y;
    }
    private static BigDecimal countall (LinkedList<String> ch1, LinkedList<String> zn){
        LinkedList<BigDecimal> ch = new LinkedList<>();
//        System.out.println(ch1);
//        System.out.println(zn);
        for (int i = 0; i < ch1.size(); i++) {
            if(ch1.get(i).equals("#"))
                ch.add(null);
            else{
                try{
                    ch.add(new BigDecimal(ch1.get(i)));
                }catch (NumberFormatException e){
//                    System.out.println(new BigDecimal(countwhithoutspace("0+" + ch1.get(i))));
//                    System.out.println("0+" + ch1.get(i));
                    ch.add(new BigDecimal(countwhithoutspace("0+" + ch1.get(i))));
                }
            }
        }
//        System.out.println(zn);

        while (zn.contains("√")){
            int x = zn.indexOf("√");
            ch.set(x, countsqrt(ch.get(x + 1), accuracy));
            ch.remove(x + 1);
            zn.remove(x);
        }
        while (zn.contains("^")){
            int x = zn.indexOf("^");
            ch.set(x - 1, count(ch.get(x - 1), ch.get(x + 1), "^"));
            ch.remove(x);
            ch.remove(x);
            zn.remove(x);
            zn.remove(x);
        }
        while (zn.contains("*") || zn.contains("/")){
            if ((zn.contains("*") && zn.contains("/") && zn.indexOf("*") < zn.indexOf("/")) || (zn.contains("*") && !zn.contains("/"))){
                int x = zn.indexOf("*");
                ch.set(x - 1, count(ch.get(x - 1), ch.get(x + 1), "*"));
                ch.remove(x);
                ch.remove(x);
                zn.remove(x);
                zn.remove(x);
            }else{
                int x = zn.indexOf("/");
                ch.set(x - 1, count(ch.get(x - 1), ch.get(x + 1), "/"));
                ch.remove(x);
                ch.remove(x);
                zn.remove(x);
                zn.remove(x);
            }
        }
        while (zn.contains("+") || zn.contains("-")){
            if ((zn.contains("+") && zn.contains("-") && zn.indexOf("+") < zn.indexOf("-")) || (zn.contains("+") && !zn.contains("-"))){
                int x = zn.indexOf("+");
                ch.set(x - 1, count(ch.get(x - 1), ch.get(x + 1), "+"));
                ch.remove(x);
                ch.remove(x);
                zn.remove(x);
                zn.remove(x);
            }else{
                int x = zn.indexOf("-");
                ch.set(x - 1, count(ch.get(x - 1), ch.get(x + 1), "-"));
                ch.remove(x);
                ch.remove(x);
                zn.remove(x);
                zn.remove(x);
            }
        }
        return ch.get(0);
    }
    private static BigDecimal countsqrt(BigDecimal a, int s){
        String start = a.toString();
        String result = "";
        if (start.indexOf('.') == -1)start += '.';
        int point = start.indexOf('.');

        int p = ((int)Math.sqrt(Double.parseDouble(start)) + "").length();
        String[] beforepoint = new String[(int) Math.ceil(point / 2.0)];
        String[] afterpoint = new String[(int) Math.floor((start.length() - point) / 2.0)];
        int i = point;
        int y = beforepoint.length - 1;
        while (i > 0) {
            if (i - 2 >= 0) {
                beforepoint[y] = (start.charAt(i - 2) + "" + start.charAt(i - 1));
                i -= 2;
            } else {
                beforepoint[y] = ("" + start.charAt(0));
                i--;
            }
            y--;
        }
        i = point;
        y = 0;
        while (i < start.length() - 1) {
            if (i != start.length() - 2) {
                afterpoint[y] = start.charAt(i + 1) + "" + start.charAt(i + 2);
                i += 2;
            } else {
                afterpoint[y] = start.charAt(i + 1) + "";
                i++;
            }
            y++;
        }
        if (afterpoint.length != 0){
            if (afterpoint[afterpoint.length - 1].length() == 1){
                afterpoint[afterpoint.length - 1] += "0";
            }
        }
        ArrayList<String> par = new ArrayList<>();
        Collections.addAll(par, beforepoint);
        Collections.addAll(par, afterpoint);
        int j = 0;
        BigInteger f;
        while (true) {
            if (j == par.size() - 1)par.add("00");
            if (j == 0){
                f = BigInteger.valueOf((long)Math.sqrt(Integer.parseInt(par.get(0))));
                result += f.toString();
                par.set(j + 1, Integer.parseInt(par.get(j)) - (int) Math.pow(f.doubleValue(), 2) + par.get(j + 1));
            }else {
                f = new BigInteger(result);
                par.set(j + 1, par.get(j) + par.get(j + 1));
            }
            int g = 0;
            while (true){
                String s1 = f.multiply(BigInteger.valueOf(2)).toString();
                if (new BigInteger(s1 + g).multiply(BigInteger.valueOf(g)).compareTo(new BigInteger(par.get(j + 1))) > 0) {
                    par.set(j + 1, new BigInteger(par.get(j + 1)).subtract(new BigInteger(s1 + (g - 1)).multiply(BigInteger.valueOf(g - 1))).toString());
                    result += g - 1;
                    break;
                }
                g++;
            }
            j++;
            if (result.length() >= p + s){
                break;
            }
        }
        if (p == result.length())return new BigDecimal(result);
        else if (s == 0)return new BigDecimal((int)Double.parseDouble(result.substring(0, p) + '.' + result.substring(p)) + "");
        else return new BigDecimal(result.substring(0, p) + '.' + result.substring(p));
    }
}
