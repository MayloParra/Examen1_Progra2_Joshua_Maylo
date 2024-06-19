package POO;

public abstract class Informe {
    
private Double Efectivo=0.0;
private Double PagoRapido=0.0;
private Double TotalDia=0.0;
public Informe(){

}
public Double getEfectivo() {
    return Efectivo;
}
public void setEfectivo(Double efectivo) {
    Efectivo += efectivo;
}
public Double getPagoRapido() {
    return PagoRapido;
}
public void setPagoRapido(Double pagoRapido) {
    PagoRapido += pagoRapido;
}
public Double getTotalDia() {
    return TotalDia;
}
public void setTotalDia() {
    TotalDia = Efectivo+PagoRapido;;
}

}