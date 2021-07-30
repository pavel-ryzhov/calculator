package com.example.calculator;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class Math2 {

    private static final char[] signs = {'+', '-', '*', '/', 'c', 's', 'a', 't', 'b', 'd', 'g', 'n', '^', '√', '!', 'e'};
    private static final char[] fsigns = {'c', 's', 'a', 't', 'b', 'd', 'g', 'n', '√'};
    private static final char[] fsigns1 = {'!'};
    public static boolean radians = false;
    /*
    c -> cos
    s -> sin
    t -> tan
    a -> arcsin
    b -> arccos
    d -> arctan
    g -> lg
    n -> ln
     */


    private static double counte(double a){
        return Math.pow(10, a);
    }
    private static long fact(long t){
        long res = 1;
        for (int i = 1; i <= t; i++) {
            res *= i;
        }
        return res;
    }
    private static int min(int[] mass){
        int min = 2147483647;
        for(Integer x : mass){
            if (x < min && x != -1) min = x;
        }
        return min;
    }
    private static double countf(double a, String zn){
        double x;
        if (zn.equals("c"))
            if (radians) x = Math.cos(a);
            else x = Math.cos(Math.toRadians(a));
        else if (zn.equals("s"))
            if (radians) x = Math.sin(a);
            else x = Math.sin(Math.toRadians(a));
        else if (zn.equals("t"))
            if (radians) x = Math.tan(a);
            else x = Math.tan(Math.toRadians(a));
        else if (zn.equals("a"))
            if (radians) x = Math.asin(a);
            else x = Math.toDegrees(Math.asin(a));
        else if (zn.equals("b"))
            if (radians) x = Math.acos(a);
            else x = Math.toDegrees(Math.acos(a));
        else if (zn.equals("d"))
            if (radians) x = Math.atan(a);
            else x = Math.toDegrees(Math.atan(a));
        else if (zn.equals("g")) x = Math.log(a);
        else if (zn.equals("√")) x = Math.sqrt(a);
        else x = (-Math.log(1-a))/a;
        return x;

    }
    private static boolean contains(String c){
        for (Character x : signs) {
            if (c.equals(x + ""))return true;
        }
        return false;
    }
    private static boolean contains(String c, char[] a){
        for (Character x : a) {
            if (c.equals(x + ""))return true;
        }
        return false;
    }
    private static double countall (LinkedList<String> ch1, LinkedList<String> zn){
        LinkedList<Double> ch = new LinkedList<>();
//        System.out.println(ch1);
//        System.out.println(zn);
        for (int i = 0; i < ch1.size(); i++) {
            if(ch1.get(i).equals("#"))
                ch.add(null);
            else{
                try{
                    ch.add(Double.parseDouble(ch1.get(i)));
                }catch (NumberFormatException e){
//                    System.out.println(new BigDecimal(countwhithoutspace("0+" + ch1.get(i))));
//                    System.out.println("0+" + ch1.get(i));
                    ch.add(Double.parseDouble(countwhithoutspace("0+" + ch1.get(i))));
                }
            }
        }
//        System.out.println(zn);
        while (zn.contains("e")){
            int x = zn.indexOf("e");
            ch.set(x + 1, counte(ch.get(x + 1)));
            zn.set(x, "*");
        }
        while (zn.contains("!")){
            int x = zn.indexOf("!");
            ch.set(x - 1, Long.valueOf(fact(ch.get(x - 1).longValue())).doubleValue());
            ch.remove(x);
            zn.remove(x);
        }
        while (zn.contains("c") || zn.contains("s") || zn.contains("t") || zn.contains("a") || zn.contains("b") || zn.contains("d") || zn.contains("g") || zn.contains("n")){
            int[] znaks = {zn.indexOf("c"), zn.indexOf("s"), zn.indexOf("t"), zn.indexOf("a"), zn.indexOf("b"), zn.indexOf("d"), zn.indexOf("g"), zn.indexOf("n")};
            int x = min(znaks);
            ch.set(x, countf(ch.get(x + 1), zn.get(x)));
            ch.remove(x + 1);
            zn.remove(x);
        }
        while (zn.contains("√")){
            int x = zn.indexOf("√");
            ch.set(x, countf(ch.get(x + 1), zn.get(x)));
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
    private static double count (double a, double b, String zn){
        switch (zn) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "/":
                return a / b;
            case "*":
                return a * b;
            default:
                return Math.pow(a, b);
        }
    }
    public static double splitandcount (String str){
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
    private static double countwithbrackets (LinkedList[] o){

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
                        if (!contains(s.charAt(i) + "", fsigns)) z.add(i);
                        if (!contains(s.charAt(i) + "", fsigns1))z.add(i + 1);
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
//                    System.out.println(s);
                    return "Result: " + splitandcount(s);
                }else {
                    return "\uD83E\uDD14";
                }
            } catch (Exception e) {
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
        s = replaceall(s, "(-", "(0-");
//        s = replaceall(s, "√", "√(");



        return countwhithoutspace(s);
    }
    private static boolean strcontainschars(String s){
        for (int i = 0; i < s.length(); i++) {
            if (contains(s.charAt(i) + "")) return true;
        }
        return false;
    }
    public static String replaceall(String str, String oldStr, String newStr){
        if ("".equals(str) || "".equals(oldStr) || oldStr.equals(newStr)) {
            return str;
        }
        if (newStr == null) {
            newStr = "";
        }
        final int strLength = str.length();
        final int oldStrLength = oldStr.length();
        StringBuilder builder = new StringBuilder(str);

        for (int i = 0; i < strLength; i++) {
            int index = builder.indexOf(oldStr, i);

            if (index == -1) {
                if (i == 0) {
                    return str;
                }
                return builder.toString();
            }
            builder = builder.replace(index, index + oldStrLength, newStr);

        }
        return builder.toString();
    }
}
