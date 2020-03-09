public class checkForGCD {

    public static void main(String[] args) {
        String matrikelnummer = "09126243";
        checkForGCD.divideAndCheck(matrikelnummer);
    }

    public static void divideAndCheck(String matrikelnummer) {
        int[] ziffer = new int[8];
        for (int i = 0; i < matrikelnummer.length(); i++) {
            ziffer[i] = Integer.parseInt(matrikelnummer.substring(i, i + 1));
            //System.out.println(ziffer[i]);
        }

        for (int i = 0; i < matrikelnummer.length() - 1; i++) {
            for (int j = i + 1; j < matrikelnummer.length(); j++) {
                /*ggT > 1 genau dann, wenn entweder beide zu vergleichende Ziffern mod 2 == 0 oder
                beide zu vergleichende Ziffern mod 3 == 0 (vereinfachter GGT-Test)
                 */
                if ((ziffer[i] != 0 && ziffer[j] != 0) &&
                        ((ziffer[i] % 2 == 0 && ziffer[j] % 2 == 0) ||
                        (ziffer[i] % 3 == 0 && ziffer[j] % 3 == 0))) {
                    System.out.println("Index i = " + i + ", Index j = " + j);
                }
            }
        }


    }


}
