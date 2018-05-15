
class StartStop {

    MainProc main;
    Loader ld;
    ReadLine rd;
    PrintLine pr;
    Interrupt inter;

    StartStop() {

        main = new MainProc();
        ld = new Loader();
        rd = new ReadLine();
        pr = new PrintLine();
        inter = new Interrupt();
    }
}

class MainProc {

    RM rm = new RM();
    
    MainProc() {
        
    }
}

class JobGovernor {
    
    VM vm;
}

class Loader {

    String busena = "neutralus";
    RM rm = new RM();

    Loader(RM rm) {
        this.rm = rm;
    }

    Loader() {
    }
}

class ReadLine {

    String busena = "neutralus";
    RM rm = new RM();

    ReadLine(RM rm) {
        this.rm = rm;
    }

    ReadLine() {
    }
}

class PrintLine {

    String busena = "neutralus";
    RM rm = new RM();

    PrintLine(RM rm) {
        this.rm = rm;
    }

    PrintLine() {
    }
}

class Interrupt {

    String busena = "neutralus";
    RM rm = new RM();

    Interrupt(RM rm) {
        this.rm = rm;
    }

    Interrupt() {
    }
}
