package njh.androidApp.scratchoffstats;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;
import android.content.SharedPreferences;


public class MainActivity extends ActionBarActivity {
	
	private float _moneySpent=0;
	private float _moneyWon=0;
	private int   _ticketsBought=0;
	private float _net=0;

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
    	
    	float value = 0,win = 0;
    	try{
    		value = Float.parseFloat(valueET.getText().toString());
    		win = Float.parseFloat(winET.getText().toString());
    	}catch(Exception e){
    		winET.setText("");
        	valueET.setText("");
        	return;
    	}
    	
    	//update locals from database
    	SharedPreferences sharedPrefs = getPreferences(Context.MODE_PRIVATE);
    	_moneySpent = sharedPrefs.getFloat(getString(R.string.spentText), 0);
    	_moneySpent += value;
    	_moneyWon = sharedPrefs.getFloat(getString(R.string.wonText), 0);
    	_moneyWon += win;
    	_ticketsBought = sharedPrefs.getInt(getString(R.string.ticketsText), 0);
    	_ticketsBought++;
    	_net = sharedPrefs.getFloat(getString(R.string.netText), 0);
    	_net += win - value;
    	
    	TextView spent = (TextView) findViewById(R.id.spentID);
    	spent.setText("Money Spent: "+String.format("%.2f", _moneySpent));
    	TextView won = (TextView) findViewById(R.id.wonID);
    	won.setText("Money Won: "+String.format("%.2f", _moneyWon));
    	TextView tickets = (TextView) findViewById(R.id.ticketsID);
    	tickets.setText("Tickets Bought: "+_ticketsBought);
    	TextView net = (TextView) findViewById(R.id.netID);
    	net.setText("Tickets Net: "+String.format("%.2f", _net));
    	if(_net > 0){
    		net.setTextColor(Color.GREEN);
    	}else{
    		net.setTextColor(Color.RED);
    	}
    	winET.setText("");
    	valueET.setText("");
    	
    	//update database
    	SharedPreferences.Editor editor = sharedPrefs.edit();
    	editor.putFloat(getString(R.string.spentText),_moneySpent);
    	editor.putFloat(getString(R.string.wonText),_moneyWon);
    	editor.putInt(getString(R.string.ticketsText),_ticketsBought);
    	editor.putFloat(getString(R.string.netText),_net);
    	editor.commit();
    }
    
    public void clearDataBase(View view){
    	SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
    	editor.clear();
    	editor.commit();
    	TextView spent = (TextView) findViewById(R.id.spentID);
    	spent.setText("Money Spent: 0.00");
    	TextView won = (TextView) findViewById(R.id.wonID);
    	won.setText("Money Won: 0.00");
    	TextView tickets = (TextView) findViewById(R.id.ticketsID);
    	tickets.setText("Tickets Bought: 0");
    	TextView net = (TextView) findViewById(R.id.netID);
    	net.setText("Tickets Net: 0.00");
    }
}
