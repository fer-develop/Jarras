class Nodos {
    //Cantidad en jarra 1 para el estado actual
    int x;
    //Cantidad en jarra 2 para el estado actual
    int y;
    //Padre del estado actual
    Nodos padre;

    public Nodos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Nodos(int x, int y, Nodos padre) {
        this.x = x;
        this.y = y;
        this.padre = padre;
    }

    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Nodos other = (Nodos) obj;
        if (x != other.x) {
            return false;
        }
        if (y != other.y) {
            return false;
        }
        return true;
    }
    
    public String toString() {
        StringBuilder constructor = new StringBuilder();
        if (padre != null) {
            constructor.append(padre);
        }
        constructor.append(x);
        constructor.append("    ").append(y).append("\n");
        return constructor.toString();
    }
}

