package nl.mprog.apps.Hangman;

import android.content.Intent;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;



public class SettingsActivity extends ActionBarActivity implements OnSeekBarChangeListener {
	
	// Seekbars for word length and number of guesses
	private SeekBar wordLength, guesses;
	
	// Textviews to show progress of seekbars
	private TextView wordLengthProgress, guessesProgress;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        wordLength = (SeekBar)findViewById(R.id.seekBarWordLength);
        wordLength.setOnSeekBarChangeListener(this);
        guesses = (SeekBar)findViewById(R.id.seekBarGuesses);
        guesses.setOnSeekBarChangeListener(this);
        
        // textView to show progress
        wordLengthProgress = (TextView)findViewById(R.id.word_length);
        guessesProgress = (TextView)findViewById(R.id.guesses);
        
        // default values should also be shown
        wordLengthProgress.setText("Word length: 8");
        guessesProgress.setText("Guesses: 6");
        
        // enable seekbar values to be used in gameplay activity
        int WordLength = getIntent().getIntExtra("WordLength", 0);
        int Guesses = getIntent().getIntExtra("Guesses", 0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
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
    
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    	
    	// set textView to show progress for different seekbars
    	if (seekBar.equals(wordLength)) {
    		wordLengthProgress.setText("Word length: "+progress);
    	}
    	else if (seekBar.equals(guesses)) {
    		guessesProgress.setText("Guesses: "+progress);
    	}
    }
    
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    	
    }
    
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    	
    }
    
   
    
    public void playGame(View view) {
        Intent playGameIntent = new Intent(this, GameplayActivity.class);
        playGameIntent.putExtra("WordLength", wordLength.getProgress());
        playGameIntent.putExtra("Guesses", guesses.getProgress());
        startActivity(playGameIntent);
    }
}
