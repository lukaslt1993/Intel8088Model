
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class VM {

    public String R = "0000", N = "0000";
    public byte[] PTR = new byte[4];
    public short IC = 0;
    public boolean C = false;
    public boolean sukurta = false;
    public char MODE = 'v';
    public byte PI = 0, SI = 0, TI = 1, IOI = 0, CHST1 = 0, CHST2 = 0,
            CHST3 = 0;
    public String[][] atm = new String[16][16];
    public String[] buffer = new String[16];
    public int time = 100;
    String busena = "pasiruosus";
    public int takelis;

    public void atmintis() {

        for (int i = 0; i < 16; i++) {
            System.out.println(i + " blokas");
            for (int j = 0; j < 16; j++) {
                System.out.println(atm[i][j]);
            }
            System.out.println();
        }
    }

    public void atmintis(int puslapis) {

        if (puslapis <= 15 && puslapis >= 0) {
            System.out.println(puslapis + " blokas");
            for (int i = 0; i < 16; i++) {
                System.out.println(atm[puslapis][i]);
            }
        } else {
            System.out.println("Tokio puslapio nera.");
        }
    }

    public void Loader(String failas) {

        IC = 0;

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            FileInputStream fstream = new FileInputStream(failas);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            int counter = 0, counter2 = 0;
            String strLine;
            ArrayList<String> a = new ArrayList<>();

            while ((strLine = br.readLine()) != null) {
                if (strLine.length() > 4) {
                    System.out
                            .println("Ikelimas neivyko. Faile yra per ilgu komandu");
                    fstream.close();
                    in.close();
                    br.close();
                    return;
                }
                a.add(strLine);
            }

            if (a.size() > 256) {
                System.out
                        .println("Ikelimas neivyko. Faile daugiau nei 256 irasai.");
                fstream.close();
                in.close();
                br.close();
                return;
            }

            for (int i = 0; i < a.size(); i++) {
                atm[counter][counter2] = a.get(i);
                counter2 = counter2 + 1;
                if (counter2 > 15) {
                    counter += 1;
                    counter2 = 0;
                }
            }

            fstream.close();
            in.close();
            br.close();
            // IOI = 4;
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public boolean pat_vm_adr(int y, int z) {

        if (y < 0 || y > 15 || z < 0 || z > 15) {
            PI = 1;
            return false;
        } else {
            return true;
        }
    }

    public void AR(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            int r = Integer.parseInt(R, 16);
            int r2 = Integer.parseInt(atm[y][z], 16);
            R = Integer.toHexString(r + r2);
            if (R.length() > 4) {
                R = R.substring(R.length() - 4);
                PI = 4;
            }
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }
    }

    public void AN(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            int n = Integer.parseInt(N, 16);
            int n2 = Integer.parseInt(atm[y][z], 16);
            N = Integer.toHexString(n + n2);
            if (N.length() > 4) {
                N = N.substring(N.length() - 4);
                PI = 4;
            }
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }

    }

    public void ADRN() {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            int n = Integer.parseInt(R, 16);
            int n2 = Integer.parseInt(N, 16);
            R = Integer.toHexString(n + n2);
            if (R.length() > 4) {
                R = R.substring(R.length() - 4);
                PI = 4;
            }
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }

    }

    public void ADNR() {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            int n = Integer.parseInt(N, 16);
            int n2 = Integer.parseInt(R, 16);
            N = Integer.toHexString(n + n2);
            if (N.length() > 4) {
                N = N.substring(N.length() - 4);
                PI = 4;
            }
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }

    }

    public void DR(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            int r = Integer.parseInt(R, 16);
            int r2 = Integer.parseInt(atm[y][z], 16);
            R = Integer.toHexString(r - r2);
            if (N.length() > 4) {
                N = N.substring(N.length() - 4);
                PI = 4;
            }
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }
    }

    public void DN(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            int n = Integer.parseInt(N, 16);
            int n2 = Integer.parseInt(atm[y][z], 16);
            N = Integer.toHexString(n - n2);
            if (N.length() > 4) {
                N = N.substring(N.length() - 4);
                PI = 4;
            }
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }

    }

    public void DERN() {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            int r = Integer.parseInt(R, 16);
            int r2 = Integer.parseInt(N, 16);
            R = Integer.toHexString(r - r2);
            if (N.length() > 4) {
                N = N.substring(N.length() - 4);
                PI = 4;
            }
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }
    }

    public void DENR() {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        try {
            int n = Integer.parseInt(N, 16);
            int n2 = Integer.parseInt(R, 16);
            N = Integer.toHexString(n - n2);
            if (N.length() > 4) {
                N = N.substring(N.length() - 4);
                PI = 4;
            }
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }

    }

    public void CR(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        if (R.equals(atm[y][z])) {
            C = true;
        } else {
            C = false;
        }
    }

    public void CN(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        if (N.equals(atm[y][z])) {
            C = true;
        } else {
            C = false;
        }
    }

    public void CMRN() {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        if (R.equals(N)) {
            C = true;
        } else {
            C = false;
        }
    }

    public void CMNR() {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        if (N.equals(R)) {
            C = true;
        } else {
            C = false;
        }
    }

    public void SR(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        atm[y][z] = R;
    }

    public void SN(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        atm[y][z] = N;
    }

    public void LR(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        R = atm[y][z];
    }

    public void LN(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        N = atm[y][z];
    }

    public void BT(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        if (C == true) {
            IC = (short) (y * 16 + z);
        }
    }

    public void JM(int y, int z) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        IC = (short) (y * 16 + z);
    }

    public void GetLine(int y) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 16; i++) {
            System.out.println("Iveskite zodi numeriu " + i + " (daugiausia 4 simboliai).");
            System.out.println("Noredami iseiti, iveskite END");
            String str = sc.nextLine();
            if (str.equals("END")) {
                break;
            } else if (str.length() > 4) {
                System.out
                        .println("Ikelimas neivyko. Ivedimo buferyje atrastas irasas, ilgesnis uz 4 simbolius.");
                i--;
                continue;
            } else {
                atm[y][i] = str;
            }
            IOI = 1;
            CHST1 = 0;
        }

    }

    public void PrintLine(int y) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }
        for (int i = 0; i < 16; i++) {
            System.out.println(atm[y][i]);
        }
        IOI = 2;
        CHST2 = 0;
    }

    public void RJ(int x) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }
        String s = R.substring(2);
        try {
            R = Integer.toHexString(x) + s;
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }
    }

    public void RV(int x) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }
        String s = R.substring(0, 2);
        try {
            R = s + Integer.toHexString(x);
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }
    }

    public void NJ(int x) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }
        String s = N.substring(2);
        try {
            N = Integer.toHexString(x) + s;
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }
    }

    public void NV(int x) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }
        String s = N.substring(0, 2);
        try {
            N = s + Integer.toHexString(x);
        } catch (Exception ex) {
            // System.out.println("Atmintyje/registre ne skaicius.");
            PI = 3;
        }
    }

    public void MORN() {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        R = N;
    }

    public void MONR() {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        N = R;
    }

    public void pat_opk_ir_adr(String opk, int y, int z) {
    }

    public void PI() {

        if (PI != 4) {
            busena = "neutrali";
        }
        PI = 0;
    }

    public void SI() {

        if (SI == 3) {
            busena = "neutrali";
        } else if (SI == 2) {
            PrintLine(takelis);
        } else {
            GetLine(takelis);
        }
        SI = 0;
    }

    public void TI() {

        busena = "neutrali";
        TI = 1;
    }

    public void IOI() {

        IOI = 0;
    }

    public boolean test() {
        
        if ((PI + SI + TI + IOI) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void Interrupt() {

        if (PI != 0) {
            PI();
        }
        if (SI != 0) {
            SI();
        }
        if (TI != 1) {
            TI();
        }
        if (IOI != 0) {
            IOI();
        }

    }

    public void vykdyti(boolean b) {

        if (sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        if (busena.equals("neutrali")) {
            System.out.println("VM yra sustabdytoj (neutralioj) busenoj.");
            return;
        }
        int n = 0, n2 = 0, y = 0, z = 0, counter = 0, timer = 0;
        while (true) {
            n = (short) (IC / 16);
            if (n > 15) {
                break;
            }
            n2 = (short) (IC % 16);

            if (b == true) {
                System.out.println("Noredami vykdyti zingsni, spauskite enter");
                System.out.println("Noredami sugrizti, iveskite END");
                String sc = new Scanner(System.in).nextLine();
                if (sc.equals("")) {
                    counter++;
                    System.out.println(counter + " zingsnis.");

                    System.out.println();
                    VMregistrai();
                    System.out.println();
                    System.out.println("IC rodo i " + atm[n][n2]);
                    System.out.println("Puslapio nr = " + n);
                    System.out.println(n + " Puslapio reiksmes:");
                    atmintis(n);
                    System.out.println();
                } else if (sc.equals("END")) {
                    break;
                } else {
                    continue;
                }
            }

            if (test() == false) {
                Interrupt();
                if (busena.equals("neutrali")) {
                    break;
                }
            }

            IC = (short) (IC + 1);
            if (atm[n][n2].equals("HALT")) {
                SI = 3;
                continue;
            }
            if (atm[n][n2].equals("DATA")) {
                continue;
            }
            if (atm[n][n2].equals("CODE")) {
                busena = "vykdo";
                continue;
            }
            if (atm[n][n2].length() == 4) {
                try {
                    String w = atm[n][n2];
                    String s = w.substring(0, 2);
                    String s2 = w.substring(2);

                    if (s.equals("RJ") || s.equals("RV") || s.equals("NJ")
                            || s.equals("NV")) {

                        int x = Integer.parseInt(s2, 16);
                        if (s.equals("RJ")) {
                            RJ(x);
                        } else if (s.equals("RV")) {
                            RV(x);
                        } else if (s.equals("NJ")) {
                            NJ(x);
                        } else {
                            NV(x);
                        }

                    } else if (w.equals("ADRN") || w.equals("ADNR")
                            || w.equals("DERN") || w.equals("DENR")
                            || w.equals("CMRN") || w.equals("CMNR")
                            || w.equals("MORN") || w.equals("MONR")) {
                        if (w.equals("ADRN")) {
                            ADRN();
                        } else if (s.equals("ADNR")) {
                            ADNR();
                        } else if (s.equals("DERN")) {
                            DERN();
                        } else if (s.equals("DENR")) {
                            DENR();
                        } else if (s.equals("CMRN")) {
                            CMRN();
                        } else if (s.equals("CMNR")) {
                            CMNR();
                        } else if (s.equals("MORN")) {
                            MORN();
                        } else {
                            MONR();
                        }

                    } else {

                        y = Integer.parseInt(s2, 16) / 16;
                        z = Integer.parseInt(s2, 16) % 16;

                        if (pat_vm_adr(y, z) == true) {
                            if (s.equals("AR")) {
                                AR(y, z);
                            } else if (s.equals("AN")) {
                                AN(y, z);
                            } else if (s.equals("DR")) {
                                DR(y, z);
                            } else if (s.equals("DN")) {
                                DN(y, z);
                            } else if (s.equals("CR")) {
                                CR(y, z);
                            } else if (s.equals("CN")) {
                                CN(y, z);
                            } else if (s.equals("SR")) {
                                SR(y, z);
                            } else if (s.equals("SN")) {
                                SN(y, z);
                            } else if (s.equals("LR")) {
                                LR(y, z);
                            } else if (s.equals("LN")) {
                                LN(y, z);
                            } else if (s.equals("BT")) {
                                BT(y, z);
                            } else if (s.equals("JM")) {
                                JM(y, z);
                            } else if (s.equals("PD")) {
                                takelis = y;
                                SI = 2;
                                CHST2 = 2;
                            } else if (s.equals("GD")) {
                                takelis = y;
                                SI = 1;
                                CHST1 = 1;
                            } else {
                                if (busena.equals("vykdo")) {
                                    PI = 2;
                                }
                            }
                        } else {
                            PI = 2;
                        }
                    }
                } catch (Exception ex) {
                    // System.out.println("skaiciaus exception");
                    PI = 3;
                    continue;
                }
            } else {
                if (busena.equals("vykdo")) {
                    PI = 2;
                }
            }
            timer++;
            if (timer > time) {
                TI = 0;
            }

        }
        if (b == false) {
            VMregistrai();
            System.out.println();
        }

    }

    public void VMregistrai() {
        System.out.println("R = " + R);
        System.out.println("N = " + N);
        System.out.println("IC = " + IC);
        System.out.println("C = " + C);
        System.out.println("PI = " + PI);
        System.out.println("IOI = " + IOI);
        System.out.println("CHST1 = " + CHST1);
        System.out.println("CHST2 = " + CHST2);
        // System.out.println("CHST3 = " + CHST3);
        System.out.println("SI = " + SI);
        System.out.println("TI = " + TI);
        System.out.println("MODE = " + MODE);
    }

    public void VMsekanti_komanda() {
    }

}
