package br.com.seasyc.providingaccount.helpers;

public class GenerateKey {
    public static String generate() {
        int max = 10;
        String[] characters = {"0", "1", "b", "2", "4", "5", "6", "7", "8",
                "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z"};

        StringBuilder senha = new StringBuilder();

        for (int i = 0; i < max; i++) {
            int posicao = (int) (Math.random() * characters.length);
            senha.append(characters[posicao]);
        }
        return senha.toString();

    }
}
