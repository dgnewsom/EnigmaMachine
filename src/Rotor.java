public class Rotor {
    String rotorCharacters;
    char initialTopChar;

    public Rotor(String rotorCharactersToSet, char topCharToSet) {
        this.rotorCharacters = rotorCharactersToSet;
        SetInitialPosition(topCharToSet);
    }

    public void SetInitialPosition(char topCharToSet) {
        initialTopChar = topCharToSet;
        boolean topCharMatch = initialTopChar == rotorCharacters.charAt(0);
        while(!topCharMatch)
        {
            RotateClockwise();
            topCharMatch = initialTopChar == rotorCharacters.charAt(0);
        }
    }

    public boolean RotateClockwise() {
        rotorCharacters = rotorCharacters.substring(rotorCharacters.length()-1) + rotorCharacters.substring(0,rotorCharacters.length()-1);
        return rotorCharacters.charAt(0) == initialTopChar;
    }

    public int GetIndexOfCharacter(char characterToCheck) {
        return rotorCharacters.indexOf(characterToCheck);
    }

    public char GetCharacterAtIndex(int indexToCheck) {
        return rotorCharacters.charAt(indexToCheck);
    }

    public String GetCurrentRotorString() {
        return rotorCharacters;
    }
}

