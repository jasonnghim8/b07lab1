public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,0,0,5};
        Polynomial p1 = new Polynomial(c1);
        double [] c2 = {0,-2,0,0,-9};
        Polynomial p2 = new Polynomial(c2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
                System.out.println("1 is a root of s");
        else
                System.out.println("1 is not a root of s");
        Polynomial s1 = p1.multiply(p2);
        System.out.println("s1(1) = " + s1.evaluate(1));
        Polynomial t0 = p.add(p1);
        Polynomial t01 = p1.add(p);
        System.out.println("t0(1) = " + t0.evaluate(1));
        System.out.println("t01(1) = " + t01.evaluate(1));
        double[]c3 = {3};
        Polynomial p3 = new Polynomial(c3);
        Polynomial t1 = p3.add(p2);
        System.out.println("t1(1) = " + t1.evaluate(1));
        Polynomial t2 = p.multiply(p2);
        System.out.println("t2(1) = " + t2.evaluate(1));
        Polynomial t3 = p3.multiply(p2);
        System.out.println("t3(1) = " + t3.evaluate(1));
    }
}
