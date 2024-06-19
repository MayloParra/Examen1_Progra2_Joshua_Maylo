package POO;

public class Arreglo {
    
ReporteDelDia reporte=new ReporteDelDia();
//instancia de la clase ReporteDelDia
public Arreglo(){

}//Hola mundo
public void AgregarEfectivo(double efectivo){
reporte.setEfectivo(efectivo);
}//Este método sirve para agregar las cantidades en efectivo

public void AgregarPagoFacil(double pagorapido){
reporte.setPagoRapido(pagorapido);
}//Este método sirve para agregar las cantidades en PagoFacil

public Double getEfectivo(){
    return reporte.getEfectivo();
}//Este método sirve para obtener el total en efectivo

public Double getPagoFacil(){
    return reporte.getPagoRapido();
}//Este método sirve para obtener el total en efectivo

public Double getTotalDia(){
    reporte.setTotalDia();
    return reporte.getTotalDia();
}//Este método sirve para obtener la suma de las cantidades de Efectivo y PagoFacil

public String getFecha(){
    return reporte.getFecha();
}//este método sirve para obtener la fecha
}//fin de la clase

