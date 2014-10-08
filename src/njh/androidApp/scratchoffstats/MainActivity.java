package njh.androidApp.scratchoffstats;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	private float moneySpent=0;
	private float moneyWon=0;
	private float ticketsBought=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    
    public void addTicket(View view){
    	EditText valueET = (EditText) findViewById(R.id.ticketValue);
    	EditText winET= (EditText) findViewById(R.id.ticketWin);
    	float value = Float.parseFloat(valueET.getText().toString());
    	float win = Float.parseFloat(winET.getText().toString());
    	moneySpent += value;
    	moneyWon += win;
    	ticketsBought++;
    	TextView spent = (TextView) findViewById(R.id.spentID);
    	spent.setText("Money Spent: "+moneySpent);
    	TextView won = (TextView) findViewById(R.id.wonID);
    	won.setText("Money Won: "+moneyWon);
    	TextView tickets = (TextView) findViewById(R.id.ticketsID);
    	tickets.setText("Tickets Bought: "+ticketsBought);
    	winET.setText("");
    	valueET.setText("");
    }
}
