package njh.androidApp.scratchoffstats;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		String text = "Scratch Off Stats v. 0.3\n\n";
		text += "I am making this app so that people can track their sctrach off lottery tickets.";
		text += " The first page shows your overall money spent and won along with your net winnings.";
		text += " To add a ticket, fill out the price and winnings boxes and select \"Add a ticket\".";
		text += " For this app to work as intended; you must add ALL tickets, not just winners.\n\n";
		text += "***If you clear your stats there is now way to get them back!***\n\n";
		text += "Thanks for Downloading my App!\n";
		text += "Developed by Noah Herron\n";
		TextView tv = (TextView) findViewById(R.id.aboutTextView);
		tv.setText(text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
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
