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

	//private String words[];
	private String currentWord;
	//private String ltr[];
	private String words[];
	private Random rand;
	private LinearLayout wordLayout;
	private GridView letters;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        rand = new Random();
        currentWord = "";
        wordLayout = (LinearLayout)findViewById(R.id.word);
        letters = (GridView)findViewById(R.id.letters);
        playGame();
    }
    
    private void playGame() {
    	String newWord = words[rand.nextInt(words.length)];
    	
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
    
    /* public void letterPressed(View view) {
    	String ltr = ((TextView)view).getText().toString();
    	char ltrChar = ltr.charAt(0); 
    	switch (view.getId()) {
    		case R.id.keyA:
    			// do something
    		break;
    		case R.id.keyB:
    			// do something
    		break;
    		case R.id.keyC:
    			// do something
    		break;
    		case R.id.keyD:
    			// do something
    		break;
    		case R.id.keyE:
    			// do something
    		break;
    		case R.id.keyF:
    			// do something
    		break;
    		case R.id.keyG:
    			// do something
    		break;
    		case R.id.keyH:
    			// do something
    		break;
    		case R.id.keyI:
    			// do something
    		break;
    		case R.id.keyJ:
    			// do something
    		break;
    		case R.id.keyK:
    			// do something
    		break;
    		case R.id.keyL:
    			// do something
    		break;
    		case R.id.keyM:
    			// do something
    		break;
    		case R.id.keyN:
    			// do something
    		break;
    		case R.id.keyO:
    			// do something
    		break;
    		case R.id.keyP:
    			// do something
    		break;
    		case R.id.keyQ:
    			// do something
    		break;
    		case R.id.keyR:
    			// do something
    		break;
    		case R.id.keyS:
    			// do something
    		break;
    		case R.id.keyT:
    			// do something
    		break;
    		case R.id.keyU:
    			// do something
    		break;
    		case R.id.keyV:
    			// do something
    		break;
    		case R.id.keyW:
    			// do something
    		break;
    		case R.id.keyX:
    			// do something
    		break;
    		case R.id.keyY:
    			// do something
    		break;
    		case R.id.keyZ:
    			// do something
    		break;
    	}
    }*/
}
