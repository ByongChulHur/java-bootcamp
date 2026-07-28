public class PropagationDemo {
    static void accountLayer() throws  InsufficientFundsException { // TODO: throws InsufficientFundsException
        // Deepest layer creates the domain failure.
        throw new InsufficientFundsException(100.00 , 150.00);
        // TODO: throw new InsufficientFundsException(100.00, 150.00)
    }

    static void serviceLayer() throws InsufficientFundsException {
        accountLayer();
    }

    static void menuLayer() throws InsufficientFundsException { // TODO: throws InsufficientFundsException
        serviceLayer();
    }

    public static void main(String[] args) {
        try {
            menuLayer();
        } catch (InsufficientFundsException ex) { // TODO: catch InsufficientFundsException
            System.out.println("Caught at main: " + ex.getMessage());
            ex.printStackTrace(System.out);

        }
    }
}