public class Enigma {
    Rotor[] allRotors;
    Rotor[] rotorsInUse;
    public Enigma() {
        initialiseAllRotors();
    }

    public Enigma(int id1, int id2, int id3, String initialRotorPositions) {

        initialiseAllRotors();

        checkRotors(id1,id2,id3);
        /*
         * Set rotors to use
         */
        rotorsInUse = new Rotor[] {
                allRotors[id1-1],
                allRotors[id2-1],
                allRotors[id3-1]
        };
        System.out.print(String.format("\n****ROTORS IN USE****\n"
                        + "Rotor 1 = Rotor %d = %s\n"
                        + "Rotor 2 = Rotor %d = %s\n"
                        + "Rotor 3 = Rotor %d = %s\n",
                id1, rotorsInUse[0].GetCurrentRotorString(),
                id2, rotorsInUse[1].GetCurrentRotorString(),
                id3, rotorsInUse[2].GetCurrentRotorString()));

        /*
         * Set rotors to initial positions
         */
        for(int i = 0; i < initialRotorPositions.length(); i++) {
            char initialCharToSet = initialRotorPositions.toUpperCase().charAt(i);
            if(initialCharToSet == '#' || initialCharToSet >='A' && initialCharToSet <= 'Z')
            {
                rotorsInUse[i].SetInitialPosition(initialCharToSet);
            }
            else
            {
                System.out.println("Initial Rotor values must be '#' or between A-Z");
                System.exit(1);
            }
        }
        System.out.println(String.format("\n****ROTORS INITIAL POSITIONS****\n"
                        + "Rotor 1 = Initial Position = %c = %s\n"
                        + "Rotor 2 = Initial Position = %c = %s\n"
                        + "Rotor 3 = Initial Position = %c = %s\n",
                initialRotorPositions.charAt(0), rotorsInUse[0].GetCurrentRotorString(),
                initialRotorPositions.charAt(1), rotorsInUse[1].GetCurrentRotorString(),
                initialRotorPositions.charAt(2), rotorsInUse[2].GetCurrentRotorString()));
    }

    private void initialiseAllRotors() {
        /*
         * Initialise all Rotors
         */
        allRotors = new Rotor[] {

                new Rotor("#GNUAHOVBIPWCJQXDKRYELSZFMT",'#'),
                new Rotor("#EJOTYCHMRWAFKPUZDINSXBGLQV",'#'),
                new Rotor("#BDFHJLNPRTVXZACEGIKMOQSUWY",'#'),
                new Rotor("#NWDKHGXZVRIFJBLMAOPSCYUTQE",'#'),
                new Rotor("#TGOWHLIFMCSZYRVXQABUPEJKND",'#'),
                new Rotor("#ZVRIKTONFWMCPSDUYBAGQJELXH",'#'),
                new Rotor("#ZXYCSIALGFRBKOEMUJTWNHQDVP",'#'),
                new Rotor("#SFIAJBRXQHPWCMTUYONELDGVZK",'#'),
                new Rotor("#LUYRQMBGNDFITWSZHAVKPCOJXE",'#'),
                new Rotor("#XFCULGEVHBWSTJMYZQKOAIRDNP",'#')
        };
    }



    private void checkRotors(int id1, int id2, int id3) {

        if(id1 == id2 || id1 == id3 || id2 == id3) {
            System.out.println("Each Rotor can only be used once, please select 3 unique Rotors");
            System.exit(1);
        }
        int numberOfRotors = allRotors.length;

        if(id1 > numberOfRotors || id2 > numberOfRotors || id3 > numberOfRotors) {
            System.out.println(String.format("Rotor numbers must be between 1 and %d", numberOfRotors));
            System.exit(1);
        }
    }



    public char[] encrypt(String message) {

        char[] output = new char[message.length()];

        for(int i = 0; i < message.length(); i++) {

            char charToEncrypt = message.charAt(i);
            int rotor1Index = rotorsInUse[0].GetIndexOfCharacter(charToEncrypt);
            char rotor3Char = rotorsInUse[2].GetCharacterAtIndex(rotor1Index);
            int rotor2Index = rotorsInUse[1].GetIndexOfCharacter(rotor3Char);
            output[i] = rotorsInUse[2].GetCharacterAtIndex(rotor2Index);
            RotateMachine();
        }

        return output;
    }

    public char[] decrypt(String message) {

        char[] output = new char[message.length()];

        for(int i = 0; i < message.length(); i++) {

            char charToEncrypt = message.charAt(i);
            int rotor3Index = rotorsInUse[2].GetIndexOfCharacter(charToEncrypt);
            char rotor2Char = rotorsInUse[1].GetCharacterAtIndex(rotor3Index);
            rotor3Index = rotorsInUse[2].GetIndexOfCharacter(rotor2Char);
            output[i] = rotorsInUse[0].GetCharacterAtIndex(rotor3Index);
            RotateMachine();
        }

        return output;
    }

    public void RotateMachine() {
        boolean RotateNextRotor = rotorsInUse[0].RotateClockwise();
        if(RotateNextRotor) {
            RotateNextRotor = rotorsInUse[1].RotateClockwise();
        }
        if(RotateNextRotor) {
            rotorsInUse[2].RotateClockwise();
        }
    }

    public int getNumberOfRotors() {
        return allRotors.length;
    }
}

