import java.io.File;
import java.util.HashMap;
import java.nio.file.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
;

public class Polynomial {
    double[] coef;
    int[] exp;
    File file;
    
    public Polynomial() {
        double[] coef = new double[0];
        int[] exp = new int[0];
    }    
    
    public Polynomial(double[] input) {
        int z = 0;
        for (int i = 0; i < input.length; i++){
            if (input[i] == 0.0) z++;
        }
        coef = new double[input.length - z];
        exp = new int[input.length - z];
        int k = 0;
        for (int i = 0; i < input.length; i++){
            if (input[i] != 0.0){
                exp[k] = i;
                this.coef[k] = input[i];
                k++;
            }
        }
    }

   public Polynomial(File input){
        this.file = input;
        String lines = "";
        lines = readFile();
        String[] var = lines.split("+-");
        coef = new double[var.length];
        exp = new int[var.length];
        String[][] value = new String [var.length][2];
        for (int i = 0; i < var.length; i++){
            value[i] = var[i].split("x");
        }
        for (int i = 0; i < var.length; i++){
            coef[i] = Double.parseDouble(value[i][0]);
            if (value[i].length == 2){
                exp[i] = Integer.parseInt(value[i][1]);
            }
            else exp[i] = 0;
        }
    }

    public String readFile(){
        StringBuilder content = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            content.append(br.readLine());
        } catch (IOException e){
            e.printStackTrace();
        }
        return content.toString();
    }

    public int max(HashMap<Integer, Double> input){
        int m = 0;
        for (int i : input.keySet()){
            if (i > m) m = i;
        }
        return m;
    }

    public Polynomial maptoarray(HashMap<Integer, Double> input){
        int m = max(input);
        double[] result = new double[m+1];
        int i = 0;
        for (i = 0; i <= m; i++){
            result[i] = input.getOrDefault(i, 0.0);
        }
        return new Polynomial(result);
    }

    public Polynomial add(Polynomial input) {
        HashMap<Integer, Double> sum = new HashMap<>();
        int c = 0;
        if (this.coef == null) return input;
        if (input.coef == null) return this;
        for(int i = 0; i < this.coef.length; i++){
            c = this.exp[i];
            if (!sum.containsKey(c)){
                sum.put(c, this.coef[i]);
            }
            else sum.put(c, sum.get(c) + this.coef[i]);
        }
        for(int i = 0; i < input.coef.length; i++){
            c = input.exp[i];
            if (!sum.containsKey(c)){
                sum.put(c, input.coef[i]);
            }
            else sum.put(c, sum.get(c) + input.coef[i]);
        }
        return maptoarray(sum);
    }

    public boolean isempty(int[] array){
        return array.length == 0;
    }

    public double evaluate(double x) {
        double result = 0;
        if (this.coef == null) return 0;
        for (int i = 0; i < this.coef.length; i++) {
            result = result + this.coef[i]* Math.pow(x, this.exp[i]);
        }
        return result;
    }
    
    public boolean hasRoot(double input) {
        return evaluate(input) == 0;
    }

    public Polynomial multiply(Polynomial input){
        if (this.coef == null) return this;
        if (input.coef == null) return input;
        HashMap<Integer, Double> sum = new HashMap<>();
        int m = 0;
        int i = 0;
        int s = 0;
        double p = 0;
        int tel = this.exp.length;
        int iel = input.exp.length;
        if (this.exp[tel-1]>=input.exp[iel-1]) m = this.exp[-1];
        else m = input.exp[iel-1];
        for (i = 0; i < tel; i++){
            for (int k = 0; k < iel; k++){
                s = this.exp[i]+input.exp[k];
                p = this.coef[i]*input.coef[k];
                if (!sum.containsKey(s)) sum.put(s, p);
                else sum.put(s, sum.get(s) + p);
            }
        }
        return maptoarray(sum);
    }

    public File saveToFile(String name){
        file = new File(name);
        String polynomial = "";
        for (int i = 0; i < this.exp.length; i++){
            if (this.exp[i] != 0) 
                polynomial = polynomial + 
                    Double.toString(this.coef[i]) +
                        Integer.toString(this.exp[i]);
            else polynomial = polynomial + 
                    Double.toString(this.coef[i]);
        }
        try{
            FileWriter writer = new FileWriter(name);
            writer.write(polynomial);
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return file;
    }
}
