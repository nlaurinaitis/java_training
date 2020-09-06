package ru.stqa.training.sandbox;

public class Equality {
    public static void main (String[] args) {
        String s1 = "firefox";
        String s2 = "firefox";
/*        s1 and s2 variables are independent from each other. Compiler optimises code - if objects refer to the same
 string, they'll be kept in the same place physically => both comparisons return true.*/

//        String s2 = new String(s1);
/*        copied object. Now s1 variable refers to an old s1 object and s2 refers to the newly created s1 object */

//String s2 = s1;
/* link to existing s1 object, a new object is not created. There're two variable referring to the same s1 object*/

        System.out.println(s1 == s2);
/*        object are identical as both variable refer to the same object => true OR objects are different and are kept in different places => false. Physical comparison*/
        System.out.println(s1.equals(s2));
/*        compares not objects but their contents => true. Logical comparison.*/
    }
}
