package com.fincons.pgd.utilities;

import java.util.Random;

public class GeneratoreCodiceFiscaleTest {

    private static final String LETTERE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMERI = "0123456789";
    private static final Random random = new Random();

    public static String generaCodiceFiscaleCasuale() {
        StringBuilder cf = new StringBuilder();

        // 1. Cognome e Nome: 6 lettere totali
        cf.append(generaCaratteriCasuali(LETTERE, 6));

        // 2. Anno di nascita: 2 numeri
        cf.append(generaCaratteriCasuali(NUMERI, 2));

        // 3. Mese di nascita: 1 lettera
        cf.append(generaCaratteriCasuali(LETTERE, 1));

        // 4. Giorno di nascita e sesso: 2 numeri
        cf.append(generaCaratteriCasuali(NUMERI, 2));

        // 5. Codice comune: 1 lettera e 3 numeri (es. F205 per Milano)
        cf.append(generaCaratteriCasuali(LETTERE, 1));
        cf.append(generaCaratteriCasuali(NUMERI, 3));

        // 6. Carattere di controllo finale: 1 lettera
        cf.append(generaCaratteriCasuali(LETTERE, 1));

        return cf.toString();
    }

    private static String generaCaratteriCasuali(String sorgente, int lunghezza) {
        StringBuilder risultato = new StringBuilder();
        for (int i = 0; i < lunghezza; i++) {
            int indice = random.nextInt(sorgente.length());
            risultato.append(sorgente.charAt(indice));
        }
        return risultato.toString();
    }


}
