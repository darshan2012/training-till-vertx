package stringExamples;

import java.util.Arrays;
import java.util.StringTokenizer;

public class Practice{
    String s1 = "hello";
    String s2 = "hello";
    String s3 = new String("hello");
    String s4 = new String("hello").intern();
    String s5 = "HELLO";
    public void comparision(){


        // ==
        System.out.println("\nComparision using ==");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s4);
        System.out.println(s1 == s5);
        //equals method
        System.out.println("\n\nComparision using equals method");
        System.out.println(s1.equals(s2));
        System.out.println(s1.equals(s3));
        System.out.println(s1.equals(s4));
        System.out.println("hello == HELLO : " + s1.equals(s5));

        //equalsignorecase
        System.out.println("\n\n.equalsIgnoreCase Method");
        System.out.println(s1.equalsIgnoreCase(s4));

        //compareTo Method
        System.out.println("\n\ncompareTO Method");
        System.out.println(s1.compareTo(s2));
        System.out.println(s1.compareTo(s3));
        System.out.println(s1.compareTo(s4));
        System.out.println(s1.compareTo(s5));
    }
    public void stringConcatenation(){
        System.out.println(s1 + " + " + s2 + " : " + s1 + s2);
        System.out.println("10+ string: " + 10+"string");

        //concat Method
        System.out.println("\n\nconcate() Method");
        System.out.println(s1.concat(" world"));

        //format() Method
        System.out.println("\n\nformat() Method allows to add multiple strings using %s%s");
        String s = String.format("%s %s",s1,s2);
        System.out.println(s);

        //join() Method
        System.out.println("\n\njoin() Method");
        s  = String.join("-",s1,s2,s3,s4);
        System.out.println(s);
        s= String.join("_", "hey","there");
        System.out.println(s);
    }
    public void subStringExamples(){
        System.out.println(s1.substring(1));
        System.out.println("this is a long statement".substring(4,8));
        //Split examples
        String s = "This is a very long statement";
        String[] sentence = s.split(" ",1);
        System.out.println(Arrays.toString(sentence));
    }
    public void stringTokenizedExample(){
        String s = "This is a string to be tokenize.";

        StringTokenizer st = new StringTokenizer(s," ");
        System.out.println(st.countTokens());
        System.out.println(st.nextToken());
        System.out.println(st.countTokens());
        System.out.println(st.nextToken("i"));
        while(st.hasMoreTokens()){
            System.out.println(st.nextToken());
        }
    }
}