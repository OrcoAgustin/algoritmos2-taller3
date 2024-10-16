package aed;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int largo;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;
        Nodo(T v) { this.valor = v; }
    }

    public ListaEnlazada() {
        this.primero= new Nodo(null);
        this.ultimo = new Nodo(null);
        this.largo = 0;
    }

    public int longitud() {
       return this.largo;
    }

    public void agregarAdelante(T elem) {
        Nodo nodoNuevo = new Nodo(elem);
        if (this.largo == 0){
            this.primero = nodoNuevo;
            this.ultimo = nodoNuevo;
        }else{
            this.primero.anterior = nodoNuevo;
            nodoNuevo.siguiente = this.primero;
            this.primero = nodoNuevo;
        }
        this.largo += 1;
    }

    public void agregarAtras(T elem) {
        Nodo nodoNuevo = new Nodo(elem);
        if (this.largo == 0){
            this.primero = nodoNuevo;
            this.ultimo = nodoNuevo;
        }else{
            this.ultimo.siguiente = nodoNuevo;
            nodoNuevo.anterior = this.ultimo;
            nodoNuevo.siguiente= new Nodo (null);
            this.ultimo = nodoNuevo;
        }
        this.largo += 1;
    }

    public T obtener(int i) {
        Nodo apuntado = primero;
        for (int j = 0; j< i; j++){
            apuntado = apuntado.siguiente;
        }
        return apuntado.valor;
    }

    public void eliminar(int i) {
        int contador = 0;
        Nodo apuntado = primero;
        if(i == 0){
            this.primero = apuntado.siguiente;
        } else if (i==this.largo){
            while(apuntado.siguiente != null){
                apuntado = apuntado.siguiente;
            }
            apuntado.anterior.siguiente = null;
        } else {
            while (contador != i){
                apuntado = apuntado.siguiente;
                contador = contador + 1;
            }
            apuntado.anterior.siguiente = apuntado.siguiente;
            apuntado.siguiente.anterior = apuntado.anterior;
        }
        largo -= 1;
    } 

    public void modificarPosicion(int indice, T elem) {
        Nodo apuntado = primero;
        Nodo nodoNuevo = new Nodo(elem);
        for (int n=0; n<indice; n++){
            apuntado = apuntado.siguiente;
        }
        apuntado.valor = nodoNuevo.valor;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        for (int n = 0; n < lista.largo ; n++){
            this.agregarAtras(lista.obtener(n));
        }
    }
    
    @Override
    public String toString() {
        String lista = "[";
        for (int n = 0; n < this.largo - 1; n++){
            lista = lista + this.obtener(n) + ", ";
        }
        return lista + this.ultimo.valor + "]";
    }

    private class ListaIterador implements Iterador<T> {
    	ListaEnlazada<T> lista;
        int puntero ; 

        public boolean haySiguiente() {
	        return puntero !=  lista.largo;
        }
        
        public boolean hayAnterior() {
	        return puntero !=  0;
        }

        public T siguiente() {
	        puntero ++;
            return lista.obtener(puntero - 1);
        }
        

        public T anterior() {
	        puntero --;
            return lista.obtener(puntero);
        }
    }

    public Iterador<T> iterador() {
        ListaIterador iterador = new ListaIterador();  
        iterador.lista = this;  
        return iterador;
    }

}
