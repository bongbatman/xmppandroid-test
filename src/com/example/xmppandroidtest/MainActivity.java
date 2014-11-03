package com.example.xmppandroidtest;

import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Message;

import com.example.xmppdroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText name;
	EditText text;
	Button	send; 
	String to;
	String message;
	
	
	XMPP fconnection = new XMPP("192.168.2.1","android","android");
	boolean con;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		name = (EditText) findViewById(R.id.name);
		text = (EditText) findViewById(R.id.text);
		send = (Button) findViewById(R.id.send);
		
		Log.d("", "before Connecting");
// Connecting to Server		
		connectToOpenfire();
		
		Log.d("", "after Connecting");
//Sending Message when user push send Button
		send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				to = name.getText().toString();
				message = text.getText().toString();
				Log.i("","sending this: " + message + " to user: " + to );
				
				Message msg = new Message(to, Message.Type.chat);
                msg.setBody(message);
                try {
					fconnection.sendPacket(msg);
				} catch (NotConnectedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});	
		Log.d("", "after Sending");
		
	
	}

	private void connectToOpenfire() {
		// TODO Auto-generated method stub
		fconnection.connect();
		
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
}
