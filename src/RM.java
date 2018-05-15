
import java.util.Random;

public class RM {
    
    public String R, N;
    public byte[] PTR = new byte[4];
    public short IC = 0;
    public boolean C = false;
    public String[][] atm = new String[136][16];
    public char MODE = 'v';
    public byte PI = 0, SI = 0, TI = 1, IOI = 0, CHST1 = 0, CHST2 = 0,
            CHST3 = 0;
    public boolean[] uzimta = new boolean[136];
    public VM[] vm = new VM[8];
    public int VMsk = 0;
    JobGovernor[] jg = new JobGovernor[8];
    
    RM() {
        for (int i = 0; i < 8; i++) {
            jg[i] = new JobGovernor();
        }
    }

    public void sukurtiVM() {

        if (VMsk == 8) {
            System.out.println("Negalima kurti daugiau nei 8 VM.");
            return;
        }

        jg[VMsk].vm = new VM();
        boolean parinkta = false;
        int r;

        while (!parinkta) {
            r = new Random().nextInt(136);
            if (uzimta[r] == false) {
                uzimta[r] = true;
                jg[VMsk].vm.PTR[2] = (byte) (r / 16);
                jg[VMsk].vm.PTR[3] = (byte) (r % 16);
                parinkta = true;
            }
        }

        for (int i = 0; i < 16; i++) {
            parinkta = false;
            while (!parinkta) {
                r = new Random().nextInt(136);
                if (uzimta[r] == false) {
                    uzimta[r] = true;
                    atm[(int) (jg[VMsk].vm.PTR[2] * 16 + jg[VMsk].vm.PTR[3])][i] = Integer
                            .toString(r);
                    parinkta = true;
                }
            }
        }

        jg[VMsk].vm.sukurta = true;
        for (int i = 0; i < 16; i++) {
            jg[VMsk].vm.buffer[i] = "0000";
        }
        VMsk++;
        ini();
    }

    public boolean pat_rm_adr(int y, int z) {

        if (y < 0 || y > 135 || z < 0 || z > 15) {
            PI = 1;
            return false;
        } else {
            return true;
        }
    }

    public void virt_i_real(VM vm, int y, int z) {

        if (vm.sukurta == false) {
            System.out.println("VM nesukurta.");
            return;
        }

        if (vm.pat_vm_adr(y, z) == true) {
            y = Integer.parseInt(atm[(int) (vm.PTR[2] * 16 + vm.PTR[3])][y]);
            z = Integer.parseInt(atm[y][z]);
        } else {
            System.out.println("Neteisingas virtualus adresas.");
        }
    }

    public void AtnaujintiRM() {

        for (int i = 0; i < VMsk; i++) {
            int blokas = (int) (jg[i].vm.PTR[2] * 16 + jg[i].vm.PTR[3]);
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    atm[Integer.parseInt(atm[blokas][j])][k] = jg[i].vm.atm[j][k];
                }
            }
        }
    }

    public void AtnaujintiVM() {

        for (int i = 0; i < VMsk; i++) {
            int blokas = (int) (jg[i].vm.PTR[2] * 16 + jg[i].vm.PTR[3]);
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    jg[i].vm.atm[j][k] = atm[Integer.parseInt(atm[blokas][j])][k];
                }
            }
        }
    }

    public void atmintis() {

        for (int i = 0; i < 136; i++) {
            System.out.println(i + " blokas");
            for (int j = 0; j < 16; j++) {
                System.out.println(atm[i][j]);
            }
            System.out.println();
        }
    }

    public void atmintis(int puslapis) {

        if (puslapis <= 135 && puslapis >= 0) {
            System.out.println(puslapis + " blokas");
            for (int i = 0; i < 16; i++) {
                System.out.println(atm[puslapis][i]);
            }
        } else {
            System.out.println("Tokio puslapio nera.");
        }
    }

    public void ini() {

        for (int i = 0; i < VMsk; i++) {
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 16; k++) {
                    jg[i].vm.atm[j][k] = "0000";
                }
            }
        }
        AtnaujintiRM();
    }

    public void RMregistrai() {
        System.out.println("R = " + R);
        System.out.println("N = " + N);
        System.out.println("IC = " + IC);
        System.out.println("C = " + C);
        System.out.println("PI = " + PI);
        System.out.println("IOI = " + IOI);
        System.out.println("CHST1 = " + CHST1);
        System.out.println("CHST2 = " + CHST2);
        System.out.println("CHST3 = " + CHST3);
        System.out.println("SI = " + SI);
        System.out.println("TI = " + TI);
        System.out.println("MODE = " + MODE);
    }
    
}
