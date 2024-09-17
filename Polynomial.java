public class Polynomial {
    double[] coef;
    
    public Polynomial() {
        double[] help = {0};
        coef = help;
    }    
    
    public Polynomial(double[] input) {
        coef = input;
    }
    
    public Polynomial add(Polynomial input) {
        Polynomial result;
        int len = 0;
        double s = 0;
        if (input.coef.length <= this.coef.length){
            len = this.coef.length;
            result = this;
        }
        else{
            len = input.coef.length;
            result = input;
        }
        for(int i = 0; i < len; i++){
            if (i < this.coef.length && i < input.coef.length){
                s = input.coef[i] + this.coef[i];
                result.coef[i] = s;
            }
            else if (i >= input.coef.length){
                result.coef[i] = this.coef[i];
            }
            else result.coef[i] = input.coef[i];
        }
        return result;
    }
    
    public double evaluate(double x) {
        double result = 0;
        for (int i = 0; i < this.coef.length; i++) {
            result = result + this.coef[i]* Math.pow(x, i);
        }
        return result;
    }
    
    public boolean hasRoot(double input) {
        return evaluate(input) == 0;
    }
}
