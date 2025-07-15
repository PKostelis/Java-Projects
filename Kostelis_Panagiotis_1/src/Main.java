import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		int Games = 0;
		int Wins = 0;
		int Losts = 0;
		
		while(true) {
			
			char userChoice;
			char yourguess;
			boolean correctGuess;
			int correctAnswers;
			int falseAnswers; 
			
			Menu.MainMenu();
			Scanner scan = new Scanner(System.in);      	
			
			userChoice = Character.toUpperCase(scan.next().charAt(0)); 
			//if(userChoice == 'N')
							
			if(userChoice == 'S') {
				System.out.println("You have played so far " + Games + " games. You won " + Wins + " times and lost " + Losts + " times."); 
				continue;
			}
			else if(userChoice == 'E')
		        System.exit(0);
				
			Games++;
			Random random = new Random(); 
			// Generate a random number between 0 and 9
			int randomIndex = random.nextInt(10);
			
			//Εκτυπώνω τον τυχαίο αριθμό
			//System.out.println(randomIndex);
			
			Dictionary dictionary = new Dictionary();
			String randWord = dictionary.getWord(randomIndex);
			
			char[] revealedWord = new char[randWord.length()];
	        //String revealedWord = new char[randWord.length()];
	        // Initialize revealedWord with underscores
	        //char[] ch = revealedWord.toCharArray();
	        
	        for (int i = 0; i < randWord.length(); i++) {
	        	revealedWord[i] = '-';
	        }
			//Εκτυπώνω την τυχαία λέξη
			System.out.println(randWord);
			
			int tries = 8;
			
			System.out.print("The random word is now: ");
	
			for(int i = 0; i < randWord.length(); i++)
				System.out.print(revealedWord[i] = '-');
			
			System.out.print("\n");
			
			correctAnswers = 0;
			falseAnswers = 0;
			
			while(true) {
				correctGuess = false;
							
				String sentence = "You have "+ tries +" guesses left.";
				System.out.println(sentence);
					
				System.out.print("Your guess: ");	
				
				//'Ελεγχος μη επιτρεπτής εισόδου
				while(true) {
					
					yourguess = Character.toUpperCase(scan.next().charAt(0));
	
					if (!Character.isLetter(yourguess)) {
						System.out.println("This option is invalid");
						System.out.print("Your guess: ");
					}
					else {
						correctGuess = revealLetter(randWord, revealedWord, yourguess);
						break;
					}
				}
				//Εκτυπώνω το γράμμα που μαντέυει ο χρήστης
				//System.out.println(yourguess);
							
				// Method to reveal a guessed letter in the hidden word
				if(correctGuess == true) {
					System.out.println("The guess is CORRECT!");
					correctAnswers++;
					
					if(containsUnderscore(revealedWord) == false) {
						Wins++;
				        System.out.println("Congratulations! You guessed the word: "+ randWord);
						break;
					}
					System.out.print("The random word is now: ");
	
					for(int i = 0; i < randWord.length(); i++)
						System.out.print(revealedWord[i]);
					
					System.out.print("\n");
				}
				else {
					System.out.println("There are no "+ yourguess +"’s in the word.");
					tries--;
					
					System.out.print("The random word is now: ");
					
					for(int i = 0; i < randWord.length(); i++)
						System.out.print(revealedWord[i]);
					
					System.out.print("\n");
					
					if(tries == 0) {
						System.out.println("Game Over");
						break;
					}
					falseAnswers++;
				}	
			}
		        System.out.println("You made " + correctAnswers + " correct guesses and "+ falseAnswers + " wrong guesses.\n");
		        	
		        //scan.close();
		}
	}

	private static boolean revealLetter(String randWord, char[] revealedWord, char yourguess) {
		boolean correctGuess = false;
		for(int i=0; i<randWord.length(); i++) {
			if(randWord.charAt(i) == yourguess) {
				revealedWord[i] = yourguess;
				correctGuess  = true;
			}
		}
		return correctGuess;
	}
	
	private static boolean containsUnderscore(char[] revealedWord) {
	        for(char c : revealedWord) {
	            if(c == '-') 
	                return true;   	
	        }
	        return false;
	}
	
}