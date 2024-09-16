public class Polynomial {
    double[] coef = new double[];
    
    public void Polynomial() {
        coef = {0};
    }    
    
    public void Polynomial(double[] input) {
        for(int i = 0; i < input.length; i++){
            this.coef[i] = input[i];
        }
    }
    
    public Polynomial add(Polynomial input) {
        Polynoimal result = Polynomial();
        for(int i = 0; i < max(input.length, this.coef.length); i++){
            result[i] = input[i] + this.coef[i];
        }
        return result;
    }
    
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coef.length; i++) {
            result = result + this.coef[i]*(x^i);
        }
        return result;
    }
    
    public boolean hasRoot(double input) {
        return evaluate(input) = 0;
    }
}
