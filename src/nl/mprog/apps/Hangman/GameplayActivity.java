package nl.mprog.apps.Hangman;

import java.util.Random;
import android.view.View;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.GridView;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.graphics.Color;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class GameplayActivity extends ActionBarActivity {

	private String currentWord;
	private String words[];
	private Random rand;
	private LinearLayout wordLayout;
	private GridView letters;
	private LetterAdapter letterAdapter;
	private TextView[] charViews;
	private int numCorr;
	private int numChars;
	private int numFalse;
	private int maxFalse;
	private int tLeft;
	private TextView turnsLeft;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        
        // get words from arrayList
        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        
        // initialize random and current word
        rand = new Random();
        currentWord = "";
        
        // set layout for word to play with
        wordLayout = (LinearLayout)findViewById(R.id.word);
        
        // get letter buttons
        letters = (GridView)findViewById(R.id.letters);
        
        // get maximum number of wrong guesses from settings activity
        Intent intent = getIntent();
        maxFalse = intent.getIntExtra("Guesses", 0);
        
        turnsLeft = (TextView)findViewById(R.id.turnsLeft);
        
        tLeft = maxFalse - numFalse;
        turnsLeft.setText("Turns left: " + tLeft);
        numFalse = 1;
        
        // start gameplay helper
        playGame();
    }
    
    private void playGame() {
    	// randomly select a word to play with
    	String newWord = words[rand.nextInt(words.length)];
    	
    	// make sure the same word is not used twice
    	while(newWord.equals(currentWord)) 
    		newWord = words[rand.nextInt(words.length)];
    	
    	// set current word to new word
    	currentWord = newWord;
    	
    	// create separate textview for each character of the answer word
    	charViews = new TextView[currentWord.length()];
    	
    	// remove views from previous game
    	wordLayout.removeAllViews();
    	
    	// loop over each character of the answer word
    	for (int i = 0; i < currentWord.length(); i++) {
    		
    		// create text view for each letter
    	    charViews[i] = new TextView(this);
    	    
    	    // set text view to correct letter
    		charViews[i].setText(""+currentWord.charAt(i));
    		
    		// set display properties
    		charViews[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    		charViews[i].setGravity(Gravity.CENTER);
    		charViews[i].setTextColor(Color.WHITE);
    		charViews[i].setBackgroundResource(R.drawable.letter_bg);
    		wordLayout.addView(charViews[i]);
    	}
    	
    	letterAdapter = new LetterAdapter(this);
    	letters.setAdapter(letterAdapter);
    	
    	numChars = currentWord.length();
    	numFalse = 0;
    	numCorr = 0;
    	turnsLeft.setText("Turns left: " + tLeft);
    }
    	

    public void letterPressed(View view) {
    	// receive string from pressed letter button
    	String ltr=((TextView)view).getText().toString();
    	
    	// get character from string
		char letterChar = ltr.charAt(0);
		
		
		
		// disable used letter button and make it grey
		view.setEnabled(false);
		view.setBackgroundResource(R.drawable.letter_down);
		
		// set correctness checker to false
		boolean correct = false;
		
		// loop over each character in answer word and compare to pressed letter
		for(int j = 0; j < currentWord.length(); j++) {
		  
			// if guessed letter is in the answer word
			if(currentWord.charAt(j)==letterChar){
		    
			// the guess is correct
			correct = true;
			
			// increment number of correct guesses
		    numCorr++;
		    
		    // show correct letter in answer word
		    charViews[j].setTextColor(Color.BLACK);
		  }
		}
		
		// check if user has won the game after a correct guess
		if(correct) {
			if(numCorr == currentWord.length()) {
				// Disable Buttons
			    disableButtons();
				 
				// Display Alert Dialog
				AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
				winBuild.setTitle("Congratulations!");
				winBuild.setMessage("You won!\n\nThe answer was:\n\n"+currentWord);
				winBuild.setPositiveButton("Play Again",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int id) {
				      GameplayActivity.this.playGame();
				  }});
				 
				winBuild.setNegativeButton("Exit",
				  new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int id) {
				      GameplayActivity.this.finish();
				  }});
				 
				winBuild.show();
			}
		}
		
		// if guess isn't correct, check if user has turns left (-1 because game shouldn't continue with 0 turns left)
		else if(numFalse < (maxFalse -1)) {
			
			// increment false turns
			numFalse++;
			tLeft = maxFalse - numFalse;
			turnsLeft.setText("Turns left: " + tLeft);
		}
		
		// the user should now have lost the game
		else {
			// Disable Buttons
			disableButtons();
			 
			// Display Alert Dialog
			AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
			winBuild.setTitle("Too bad!");
			winBuild.setMessage("You lost!\n\nThe answer was:\n\n"+currentWord);
			winBuild.setPositiveButton("Play Again",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int id) {
			      GameplayActivity.this.playGame();
			  }});
			 
			winBuild.setNegativeButton("Exit",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog, int id) {
			      GameplayActivity.this.finish();
			  }});
			 
			winBuild.show();
		}
    }
    
    //disable letter buttons
  	public void disableButtons(){	
  		int numLetters = letters.getChildCount();
  		for(int l=0; l<numLetters; l++){
  			letters.getChildAt(l).setEnabled(false);
  		}
  	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gameplay, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}