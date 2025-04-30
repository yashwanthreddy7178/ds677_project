package prop;

import java.time.LocalTime;

public class DuesHores {
    private final LocalTime horaIni;
    private final LocalTime horaFi;
    
    public DuesHores(LocalTime hI, LocalTime hF){
        horaIni=hI;
        horaFi=hF;
    }
    
    public boolean estaEntreMig(LocalTime h){
        return (h.isAfter(horaIni) && h.isBefore(horaFi));
    }
    
    
}
