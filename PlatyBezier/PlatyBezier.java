package PlatyBezier;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlatyBezier {

    int ile_platow;
    double[][][] k = new double[4][4][3]; //tablica przechowuje wszystkie płaty i ich punkty(o współrzędnych X, Y, Z)
    String sciezka1 = "C:\\Users\\krzys\\IdeaProjects\\GiK C-K\\src\\PlatyBezier\\teacup1.txt";
    String sciezka2 = "C:\\Users\\krzys\\IdeaProjects\\GiK C-K\\src\\PlatyBezier\\teapot1.txt";
    String sciezka3 = "C:\\Users\\krzys\\IdeaProjects\\GiK C-K\\src\\PlatyBezier\\teaspoon1.txt";

    String sciezka4 = "C:\\Users\\krzys\\IdeaProjects\\GiK C-K\\src\\PlatyBezier\\teacup1bezier.txt";
    String sciezka5 = "C:\\Users\\krzys\\IdeaProjects\\GiK C-K\\src\\PlatyBezier\\teapot1bezier.txt";
    String sciezka6 = "C:\\Users\\krzys\\IdeaProjects\\GiK C-K\\src\\PlatyBezier\\teaspoon1bezier.txt";

    double Px , Py , Pz;

    PlatyBezier() throws IOException {
        Bezier(sciezka1, sciezka4);
        Bezier(sciezka2, sciezka5);
        Bezier(sciezka3, sciezka6);
    }

    public int dwomianNewtona (int n, int k)
    {
        if (k == 0 || k == n)
            return 1;
        else
            return dwomianNewtona(n-1, k-1) + dwomianNewtona(n-1, k);
    }

    public Double bazowaBeziera(int m, int i, double v){
        return Math.pow(v,i) * Math.pow(1-v,m-i) * dwomianNewtona(m,i);
    }

    public void Bezier(String s1, String s2) throws IOException {
        File file1 = new File(s1);
        String tab1[];
        List<String> list1 = new ArrayList<String>();
        BufferedWriter bw = new BufferedWriter(new FileWriter(s2));

        int nr_lini = 0;

        double Px2 = 0.0;
        double Py2 = 0.0;
        double Pz2 = 0.0;

        try (Scanner sc = new Scanner(file1)) {
            while (sc.hasNextLine()) {
                list1.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }

        tab1 = list1.get(nr_lini).split(" ");
        ile_platow = Integer.parseInt(tab1[0]);

        for (int i = 0; i < ile_platow; i++) {
            nr_lini++;
            for (int j = 0; j < 4; j++) {//petla po kazdej linijce ze współrzędnymi danego płata (oprócz pierwszej i oprócz lini: 3 3) w pliku .txt
                for (int l = 0; l < 4; l++) {
                    nr_lini += 1;
                    tab1 = list1.get(nr_lini).split(" ");
                    k[j][l][0] = Double.parseDouble(tab1[0]); //x
                    k[j][l][1] = Double.parseDouble(tab1[1]); //y
                    k[j][l][2] = Double.parseDouble(tab1[2]); //z
                }
            }
            for (double v = 0.0; v <= 1.0; v += 0.01) {
                for (double w = 0.0; w <= 1.0; w += 0.01) {
                    for (int a = 0; a <= 3; a++) {
                        for (int b = 0; b <= 3; b++) {
                            Px2 += k[a][b][0] * bazowaBeziera(3, a, w) * bazowaBeziera(3, b, v);
                            Py2 += k[a][b][1] * bazowaBeziera(3, a, w) * bazowaBeziera(3, b, v);
                            Pz2 += k[a][b][2] * bazowaBeziera(3, a, w) * bazowaBeziera(3, b, v);
                        }
                    }
                    //zapisz do nowego pliku(używając "," )
                    bw.write(Px2 + "," + Py2 + "," + Pz2 + System.lineSeparator());
                    Px2 = 0.0;
                    Py2 = 0.0;
                    Pz2 = 0.0;
                }
            }
        }
        bw.close();
    }

    public static void main(String[] args) throws IOException {
        new PlatyBezier();
    }
}
