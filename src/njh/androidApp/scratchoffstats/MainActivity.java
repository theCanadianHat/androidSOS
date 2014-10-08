package njh.androidApp.scratchoffstats;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


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
    	_moneySpent += value;
    	_moneyWon += win;
    	_ticketsBought++;
    	_net = _moneyWon - _moneySpent;
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
    }
}
