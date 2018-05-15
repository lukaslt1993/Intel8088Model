
import java.util.Scanner;

public class MOS {
    
     public static final String INPUT = "input.txt"; 

    /*String StartStopState, MainProcState, LoaderState, JobGovernorState,
     VirtualMachineState, InterruptState, GetLineState, PrintLineState;*/
     
   public static void main(String[] args) {

        StartStop st = new StartStop();
        boolean on = true;
        int nr = 0;
        
        while (true) {
            System.out.println("###################### Meniu ######################");
            if (on == true) {
                System.out.println("0 - isjungti.");
            } else {
                System.out.println("0 - ijungti.");
            }
            System.out.println("1 - pamatyti visa RM atminti.");
            System.out.println("2 - pamatyti RM puslapi.");
            System.out.println("3 - pamatyti visa VM atminti.");
            System.out.println("4 - pamatyti VM puslapi.");
            System.out.println("5 - perziureti VM registrus.");
            System.out.println("6 - ikelti programa.");
            System.out.println("7 - vykdyti programa.");
            System.out.println("8 - vykdyti programa pazingsniui.");
            System.out.println("9 - perziureti VM sarasa.");
            System.out.println("10 - sukurti VM.");
            System.out.println("11 - sustabdyti VM.");
            System.out.println("12 - nustatyti pasiruosusia VM busena.");
            System.out.println("###################### Meniu pabaiga ######################");
            try {
                Scanner sc = new Scanner(System.in);
                int s = sc.nextInt();
                switch (s) {
                    case 1:
                        st.main.rm.atmintis();
                        continue;
                    case 2:
                        System.out.println("Iveskite puslapio numeri");
                        nr = sc.nextInt();
                        st.main.rm.atmintis(nr);
                        continue;
                    case 3:
                        System.out.println("Iveskite VM numeri");
                        nr = sc.nextInt();
                        st.main.rm.jg[nr].vm.atmintis();
                        continue;
                    case 4:
                        System.out.println("Iveskite VM numeri");
                        nr = sc.nextInt();
                        System.out.println("Iveskite puslapio numeri");
                        int nr2 = sc.nextInt();
                        st.main.rm.jg[nr].vm.atmintis(nr2);
                        continue;
                    case 5:
                        System.out.println("Iveskite VM numeri");
                        nr = sc.nextInt();
                        st.main.rm.jg[nr].vm.VMregistrai();
                        continue;
                    case 6:
                        System.out.println("Iveskite VM numeri");
                        nr = sc.nextInt();
                        st.main.rm.jg[nr].vm.Loader(INPUT);
                        continue;
                    case 7:
                        System.out.println("Iveskite VM numeri");
                        nr = sc.nextInt();
                        st.main.rm.jg[nr].vm.vykdyti(false);
                        continue;
                    case 8:
                        System.out.println("Iveskite VM numeri");
                        nr = sc.nextInt();
                        st.main.rm.jg[nr].vm.vykdyti(true);
                        continue;
                    case 9:
                        System.out.println("VM numeriu sarasas (jei tuscias, tai nera nei 1 VM):");
                        for (int i = 0; i < st.main.rm.VMsk; i++) {
                            System.out.println(i);
                        }
                        continue;
                    case 0:
                        if (on == true) {
                            st = null;
                            on = false;
                        } else {
                            st = new StartStop();
                            on = true;
                        }
                        continue;
                    case 10:
                        st.main.rm.sukurtiVM();
                        System.out.println("VM numeriu " + (st.main.rm.VMsk - 1) + " sukurta.");
                        //st.main.rm.jg[nr].vm[0].atmintis(0);
                        continue;
                    case 11:
                        System.out.println("Iveskite VM numeri");
                        nr = sc.nextInt();
                        st.main.rm.jg[nr].vm.busena = "neutrali";
                        continue;
                    case 12:
                        System.out.println("Iveskite VM numeri");
                        nr = sc.nextInt();
                        st.main.rm.jg[nr].vm.busena = "pasiruosus";

                }
            } catch (NullPointerException ex) {
                System.err.println("Klaida. Galbut nesukurete VM?");
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }

    }
}