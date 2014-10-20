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
    	_moneySpent = sharedPrefs.getFloat(getString(R.string.spentText), 0);
    	_moneySpent += value;
    	_moneyWon = sharedPrefs.getFloat(getString(R.string.wonText), 0);
    	_moneyWon += win;
    	_ticketsBought = sharedPrefs.getInt(getString(R.string.ticketsText), 0);
    	_ticketsBought++;
    	_net = sharedPrefs.getFloat(getString(R.string.netText), 0);
    	_net += win - value;
    	//update database
    	SharedPreferences.Editor editor = sharedPrefs.edit();
    	editor.putFloat(getString(R.string.spentText),_moneySpent);
    	editor.putFloat(getString(R.string.wonText),_moneyWon);
    	editor.putInt(getString(R.string.ticketsText),_ticketsBought);
    	editor.putFloat(getString(R.string.netText),_net);
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
