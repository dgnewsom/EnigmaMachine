import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int numberOfRotors = getNumberOfRotors();
        // Check for the correct # of arguments
        if( args.length != 5 || args[3].length() != 3 ) {

            System.out.printf("""
					***5 arguments required***
					Rotor1 (1-%d),
					Rotor2 (1-%d),
					Rotor3 (1-%d),
					Initial Rotor Positions e.g ###,
					encrypt|decrypt%n""", numberOfRotors,numberOfRotors,numberOfRotors);
            System.exit(1);
        }

        // Check for the correct 3 ints
        for( int i = 0; i < 3; i++ ) {
            if( args[i].charAt(0) < '0' || args[i].charAt(0) > '9' ) {
                System.out.printf("""
						***5 arguments required***
						Rotor1 (1-%d),
						Rotor2 (1-%d),
						Rotor3 (1-%d),
						Initial Rotor Positions e.g ###,
						encrypt|decrypt%n""", numberOfRotors,numberOfRotors,numberOfRotors);
                System.exit(1);
            }
        }

        // Rotors
        int id1 = 1;
        int id2 = 2;
        int id3 = 3;
        try {
            id1 = Integer.parseInt(args[0]);
            id2 = Integer.parseInt(args[1]);
            id3 = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.printf("""
				***5 arguments required***
				Rotor1 (1-%d),
				Rotor2 (1-%d),
				Rotor3 (1-%d),
				Initial Rotor Positions e.g ###,
				encrypt|decrypt%n""", numberOfRotors,numberOfRotors,numberOfRotors);
            System.exit(1);
        }

        // Call the Enigma's constructor to build the machine
        Enigma enigma = new Enigma(id1, id2, id3, args[3]);

        // Encrypt or decrypt argument
        boolean encrypt = (args[4].equalsIgnoreCase("encrypt"));

        if(encrypt)
        {
            System.out.print("Type message to Encrypt: ");
        }
        else
        {
            System.out.print("Type message to Decrypt: ");
        }
        // Get the message from the user, and call the appropriate method
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String message = scan.next().trim().toUpperCase();

        // Encrypt or Decrypt
        if( encrypt )
            System.out.printf("\n\n%s Encrypted is %s%n",message,String.valueOf(enigma.encrypt(message)));
        else
            System.out.printf("\n\n%s Decrypted is %s%n",message,String.valueOf(enigma.decrypt(message)));
    }

    private static int getNumberOfRotors() {
        Enigma enigma = new Enigma();
        return enigma.getNumberOfRotors();
    }
}