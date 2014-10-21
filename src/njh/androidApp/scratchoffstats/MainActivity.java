package njh.androidApp.scratchoffstats;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;


public class MainActivity extends ActionBarActivity {
	
	private float _moneySpent=0;
	private float _moneyWon=0;
	private int   _ticketsBought=0;
	private float _net=0;
	private int _oneDollarTickets=0;
	private float _oneDollarWinnings=0f;
	private int _twoDollarTickets=0;
	private float _twoDollarWinnings=0f;
	private int _threeDollarTickets=0;
	private float _threeDollarWinnings=0f;
	private int _fiveDollarTickets=0;
	private float _fiveDollarWinnings=0f;
	private int _tenDollarTickets=0;
	private float _tenDollarWinnings=0f;
	private int _twentyDollarTickets=0;
	private float _twentyDollarWinnings=0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateTextViews();
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
        }else if(id == R.id.action_clear){
        	buildAlertDialog();
        	return true;
        }else if(id == R.id.action_about){
        	Intent intent = new Intent(this,AboutActivity.class);
        	startActivity(intent);
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
    	_moneySpent = sharedPrefs.getFloat(getString(R.string.spentText), 0f);
    	_moneyWon = sharedPrefs.getFloat(getString(R.string.wonText), 0f);
    	_ticketsBought = sharedPrefs.getInt(getString(R.string.ticketsText), 0);
    	_net = sharedPrefs.getFloat(getString(R.string.netText), 0);
    	_oneDollarTickets = sharedPrefs.getInt(getString(R.string.oneDollarTickets), 0);
		_oneDollarWinnings = sharedPrefs.getFloat(getString(R.string.oneDollarWinnings), 0f);
		_twoDollarTickets = sharedPrefs.getInt(getString(R.string.twoDollarTickets),0);
		_twoDollarWinnings = sharedPrefs.getFloat(getString(R.string.twoDollarWinnings),0f);
		_threeDollarTickets = sharedPrefs.getInt(getString(R.string.threeDollarTickets),0);
		_threeDollarWinnings = sharedPrefs.getFloat(getString(R.string.threeDollarWinnings), 0f);
		_fiveDollarTickets = sharedPrefs.getInt(getString(R.string.fiveDollarTickets), 0);
		_fiveDollarWinnings = sharedPrefs.getFloat(getString(R.string.fiveDollarWinnings), 0f);
		_tenDollarTickets = sharedPrefs.getInt(getString(R.string.tenDollarTickets), 0);
		_tenDollarWinnings = sharedPrefs.getFloat(getString(R.string.tenDollarWinnings),0f);
		_twentyDollarTickets = sharedPrefs.getInt(getString(R.string.twentyDollarTickets),0);
		_twentyDollarWinnings = sharedPrefs.getFloat(getString(R.string.twentyDollarWinnings), 0f);
		
		//edit locals for the new ticket
		_moneySpent += value;
		_moneyWon += win;
		_ticketsBought++;
    	_net += win - value;
    	
    	if(value == 1.00){
    		_oneDollarTickets++;
    		_oneDollarWinnings+=win;
    	}else if(value == 2.00){
    		_twoDollarTickets++;
    		_twoDollarWinnings+=win;
    	}else if(value == 3.00){
    		_threeDollarTickets++;
    		_threeDollarWinnings+=win;
    	}else if(value == 5.00){
    		_fiveDollarTickets++;
    		_fiveDollarWinnings+=win;
    	}else if(value == 10.00){
    		_tenDollarTickets++;
    		_tenDollarWinnings+=win;
    	}else if(value == 20.00){
    		_twentyDollarTickets++;
    		_twentyDollarWinnings+=win;
    	}
    	
    	
    	//update database
    	SharedPreferences.Editor editor = sharedPrefs.edit();
    	editor.putFloat(getString(R.string.spentText),_moneySpent);
    	editor.putFloat(getString(R.string.wonText),_moneyWon);
    	editor.putInt(getString(R.string.ticketsText),_ticketsBought);
    	editor.putFloat(getString(R.string.netText),_net);
    	editor.putInt(getString(R.string.oneDollarTickets), _oneDollarTickets);
    	editor.putFloat(getString(R.string.oneDollarWinnings), _oneDollarWinnings);
    	editor.putInt(getString(R.string.twoDollarTickets), _twoDollarTickets);
    	editor.putFloat(getString(R.string.twoDollarWinnings), _twoDollarWinnings);
    	editor.putInt(getString(R.string.threeDollarTickets), _threeDollarTickets);
    	editor.putFloat(getString(R.string.threeDollarWinnings), _threeDollarWinnings);
    	editor.putInt(getString(R.string.fiveDollarTickets), _fiveDollarTickets);
    	editor.putFloat(getString(R.string.fiveDollarWinnings), _fiveDollarWinnings);
    	editor.putInt(getString(R.string.tenDollarTickets), _tenDollarTickets);
    	editor.putFloat(getString(R.string.tenDollarWinnings), _tenDollarWinnings);
    	editor.putInt(getString(R.string.twentyDollarTickets), _twentyDollarTickets);
    	editor.putFloat(getString(R.string.twentyDollarWinnings), _twentyDollarWinnings);
    	editor.commit();
    	
    	updateTextViews();

    	winET.setText("");
    	valueET.setText("");
    	
    	
    }
    
    public void clearDataBase(){
    	SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
    	editor.clear();
    	editor.commit();
    	updateTextViews();
    }
    
    private void updateTextViews(){
    	SharedPreferences sharedPrefs = getPreferences(Context.MODE_PRIVATE);
    	TextView spent = (TextView) findViewById(R.id.spentID);
    	spent.setText("Money Spent: "+String.format("%.2f", sharedPrefs.getFloat(getString(R.string.spentText), 0)));
    	TextView won = (TextView) findViewById(R.id.wonID);
    	won.setText("Money Won: "+String.format("%.2f", sharedPrefs.getFloat(getString(R.string.wonText), 0)));
    	TextView tickets = (TextView) findViewById(R.id.ticketsID);
    	tickets.setText("Tickets Bought: "+sharedPrefs.getInt(getString(R.string.ticketsText), 0));
    	TextView net = (TextView) findViewById(R.id.netID);
    	net.setText("Tickets Net: "+String.format("%.2f", sharedPrefs.getFloat(getString(R.string.netText), 0)));
    	if(sharedPrefs.getFloat(getString(R.string.netText), 0) > 0.0f){
    		net.setTextColor(Color.GREEN);
    	}else{
    		net.setTextColor(Color.RED);
    	}
    }
    
    private void buildAlertDialog(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage(R.string.dialog_content).setTitle(R.string.dialog_title);
    	builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(){
    		public void onClick(DialogInterface dialog,int id){
    			clearDataBase();
    		}
    	});
    	builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
    		public void onClick(DialogInterface dialog, int id){
    		}
    	});
    	builder.create();
    	builder.show();
    }
}
