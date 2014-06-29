package nl.mprog.apps.Hangman;

import java.util.Random;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.content.res.Resources;
import android.widget.LinearLayout;
import android.widget.GridView;



public class GameplayActivity extends ActionBarActivity {

	private String currentWord;
	private String words[];
	private Random rand;
	private LinearLayout wordLayout;
	private GridView letters;
	private LetterAdapter letterAdapter;
	
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
        
        // start game
        playGame();
    }
    
    private void playGame() {
    	// randomly select a word to play with
    	String newWord = words[rand.nextInt(words.length)];
    	
    	// make sure the same word is not used twice
    	while(newWord.equals(currentWord)) 
    		newWord = words[rand.nextInt(words.length)];
    	
    	// set new word to current word
    	newWord = currentWord;
    	
    	letterAdapter = new LetterAdapter(this);
    	letters.setAdapter(letterAdapter);
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