import java.util.HashSet;
import java.util.LinkedList;
import java.util.Stack;

class Evaluar {

    HashSet<Nodos> cerrados;
  
    void metodoEA() {
        // Cacidad de la Jarra 1
        int jr1 = 4;
        //Capacidad de la Jarra 2
        int jr2 = 3;
        // Valor Final deseado en la Jarra 1
        int valorFinal = 2;

        Nodos estadoInicial = new Nodos(0, 0);
        Nodos estadoFinal = new Nodos(valorFinal, 0);
        Nodos caminoFinal = null;

        cerrados = new HashSet<>();

        LinkedList<Nodos> cola = new LinkedList<>();
        cola.add(estadoInicial);

        while (!cola.isEmpty()) {
            Nodos estadoActual = cola.poll();
            if (estadoActual.equals(estadoFinal)) {
                caminoFinal = estadoActual;
                System.out.println("J1  J2");
                System.out.println(caminoFinal);
                break;
            }

            // Si x es 0 llenarlo
            if (estadoActual.x == 0) {
                Nodos estadoSiguiente = new Nodos(jr1, estadoActual.y, estadoActual);
                verificarEstadosUnicos(cerrados, estadoSiguiente, cola);
            }

            // Si y es 0 llenarlo
            if (estadoActual.y == 0) {
                Nodos estadoSiguiente = new Nodos(estadoActual.x, jr2, estadoActual);
                verificarEstadosUnicos(cerrados, estadoSiguiente, cola);
            }

            // Si x no esta vacio, vaciarlo
            if (estadoActual.x > 0) {
                Nodos estadoSiguiente = new Nodos(0, estadoActual.y, estadoActual);
                verificarEstadosUnicos(cerrados, estadoSiguiente, cola);
            }

            // Si y no esta vacia, vaciarlo
            if (estadoActual.y > 0) {
                Nodos estadoSiguiente = new Nodos(estadoActual.x, 0, estadoActual);
                verificarEstadosUnicos(cerrados, estadoSiguiente, cola);
            }

            // tranferir x a y, cuando x no esta vacia y y no esta llena
            if (estadoActual.x > 0 && estadoActual.y < jr2) {
                int CantATransferir = Math.min(estadoActual.x, jr2 - estadoActual.y);
                Nodos estadoSiguiente = new Nodos(estadoActual.x - CantATransferir, estadoActual.y
                        + CantATransferir,
                        estadoActual);
                verificarEstadosUnicos(cerrados, estadoSiguiente, cola);
            }

            // tranferir de y a x, cuando y no esta vacia y x no esta llena
            if (estadoActual.y > 0 && estadoActual.x < jr1) {
                int CantATransferir = Math.min(estadoActual.y, jr1 - estadoActual.x);
                Nodos estadoSiguiente = new Nodos(estadoActual.x + CantATransferir, estadoActual.y
                        - CantATransferir,
                        estadoActual);
                verificarEstadosUnicos(cerrados, estadoSiguiente, cola);
            }
        }
    }
    
    void verificarEstadosUnicos(HashSet<Nodos> cerrados, Nodos estadoEvaluar, LinkedList<Nodos> cola) 
    {
        if (!cerrados.contains(estadoEvaluar)) 
        {
            cerrados.add(estadoEvaluar);
            cola.add(estadoEvaluar);
        }
    }

    void metodoPEP() {
        // Cacidad de la Jarra 1
        int jr1 = 4;
        //Capacidad de la Jarra 2
        int jr2 = 3;
        // Valor Final deseado en la Jarra 1
        int valorFinal = 2;

        Nodos estadoInicial = new Nodos(0, 0);
        Nodos estadoFinal = new Nodos(valorFinal, 3);
        Nodos caminoFinal = null;

        cerrados = new HashSet<>();

        Stack<Nodos> pila = new Stack<>();
        
        pila.push(estadoInicial);
       
        while (!pila.isEmpty()) {
            
            Nodos estadoActual = pila.pop();
            
            cerrados.add(estadoActual);
            
            if (estadoActual.equals(estadoFinal)) {
                caminoFinal = estadoActual;
                System.out.println("J1  J2");
                System.out.println(caminoFinal);
                break;
            }
            
            if (estadoActual.y > 0 && estadoActual.x < jr1) {
                int CantATransferir = Math.min(estadoActual.y, jr1 - estadoActual.x);
                Nodos estadoSiguiente = new Nodos(estadoActual.x + CantATransferir, estadoActual.y
                        - CantATransferir,
                        estadoActual);
                verificarEstadosUnicosEP(cerrados, estadoSiguiente, pila);
            }
            
            if (estadoActual.y == 0) {
                Nodos estadoSiguiente = new Nodos(estadoActual.x, jr2, estadoActual);
                if (estadoSiguiente.equals(estadoFinal)) {
                    pila.push(estadoSiguiente);
                    estadoActual = pila.pop();
                    caminoFinal = estadoActual;
                    break;
                    }else{
                verificarEstadosUnicosEP(cerrados, estadoSiguiente, pila);}
            }
            
            if (estadoActual.x == 0) {
                Nodos estadoSiguiente = new Nodos(jr1, estadoActual.y, estadoActual);
                verificarEstadosUnicosEP(cerrados, estadoSiguiente, pila);
            }
            
            if (estadoActual.y > 0) {
                Nodos estadoSiguiente = new Nodos(estadoActual.x, 0, estadoActual);
                verificarEstadosUnicosEP(cerrados, estadoSiguiente, pila);
            }
             
            if (estadoActual.x < 0) {
                Nodos estadoSiguiente = new Nodos(0, estadoActual.y, estadoActual);
                verificarEstadosUnicosEP(cerrados, estadoSiguiente, pila);
            }
            
            if (estadoActual.x > 0 && estadoActual.y < jr2) {
                int CantATransferir = Math.min(estadoActual.x, jr2 - estadoActual.y);
                Nodos estadoSiguiente = new Nodos(estadoActual.x - CantATransferir, estadoActual.y
                        + CantATransferir,
                        estadoActual);         
                verificarEstadosUnicosEP(cerrados, estadoSiguiente, pila);
            }           
        }
    }
    
    void verificarEstadosUnicosEP(HashSet<Nodos> cerrados, Nodos estadoEvaluar, Stack<Nodos> pila) {
        if (!cerrados.contains(estadoEvaluar) && !pila.contains(estadoEvaluar)) { 
            pila.push(estadoEvaluar);
        }
    }

    public static void main(String[] args) {
        System.out.println("Por metodo en Profundidad:");
        new Evaluar().metodoPEP();
    }
}
