package mvc.model;

import java.util.Random;

/**
 * Taken from https://github.com/Sevenblade11/Password-Generator.git
 * TODO Probably needs some clean up and fine-tuning in the future.
 */
public class PasswordGenerator {

        private static final char[] LOWERCASELIST = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        private static final char[] UPPERCASELIST = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        private static final char[] SPECIALCHARLIST = "!@#$%&^*()?".toCharArray();

        private static final int MAXLETTERCOUNT = 26;
        private static final int MAXSPECIALCHARCOUNT = 11;

        private static Random randomNumber = new Random();

        /**
         * Generates a password with random amount of special case and upper case characters.
         * @param length Length of the password that will be created
         * @param specialCharacters Will produce random amount of special characters
         * @param upperCase Will produce random amount of upper case characters
         * @return Returns the finished password
         */
        public static String generatePassword(int length, boolean specialCharacters, boolean upperCase){
            StringBuilder password = new StringBuilder();
            int character;

            for(int i = 0; i < length; i++){
                character = randomNumber.nextInt(3);

                if(upperCase && character == 1)
                    password.append(generateUpperCharacter());
                else if(specialCharacters && character == 2)
                    password.append(generateSpecialCharacter());
                else
                    password.append(generateLowerCharacter());

            }
            return password.toString();
        }

        /**
         * Generates a password based on length given.
         * @param length Length of the password to be made
         * @param specialCharacterAmount The amount of special characters to use
         * @param upperCaseAmount The amount of upper case characters to use
         * @return Returns the completed password
         */
        public static String generatePassword(int length, int specialCharacterAmount, int upperCaseAmount){
            StringBuilder password = new StringBuilder();
            int character;

            if(specialCharacterAmount + upperCaseAmount > length)
                return "ERROR";

            while(password.length() < length){
                character = randomNumber.nextInt(3);

                if(specialCharacterAmount > 0 && character == 1) {
                    password.append(generateSpecialCharacter());
                    specialCharacterAmount--;
                }
                else if(upperCaseAmount > 0 && character == 2) {
                    password.append(generateUpperCharacter());
                    upperCaseAmount--;
                }
                else if ( (upperCaseAmount <= 0 && specialCharacterAmount <= 0) || (upperCaseAmount + specialCharacterAmount < length - password.length()) )
                    password.append(generateLowerCharacter());
            }

            return password.toString();
        }

        private static String generateUpperCharacter(){
            return ""+UPPERCASELIST[randomNumber.nextInt(MAXLETTERCOUNT)];
        }

        private static String generateSpecialCharacter(){
            return "" +SPECIALCHARLIST[randomNumber.nextInt(MAXSPECIALCHARCOUNT)];
        }

        private static String generateLowerCharacter(){
            return "" + LOWERCASELIST[randomNumber.nextInt(MAXLETTERCOUNT)];
        }
}
