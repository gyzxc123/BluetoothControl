package com.zltd.bluetoothcontrol;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends Activity implements OnClickListener {

	private static final String TAG = "BluetoothControl";
	public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;
	private Button resetButton, settingsButton, exitButton;
	private GridView mGridView;
	private List<Integer> mListData;
	private String mKeyCode = "";
	private boolean isVisible = false;

	final int imgString[] = { R.drawable.top, R.drawable.bottom,
			R.drawable.left, R.drawable.right, R.drawable.home,
			R.drawable.menu, R.drawable.back, R.drawable.ok, R.drawable.vol_up,
			R.drawable.vol_down };

	private String[] data = { "Apple", "Banana", "Orange", "Watermelon",
			"Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mango" };

	public void onAttachedToWindow() {
		this.getWindow().addFlags(FLAG_HOMEKEY_DISPATCHED);
		super.onAttachedToWindow();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		mGridView = (GridView) findViewById(R.id.keycode_grid);
		mListData = new ArrayList<Integer>();
		resetButton = (Button) findViewById(R.id.reset_test);
		resetButton.setOnClickListener(this);
		settingsButton = (Button) findViewById(R.id.bluetooth_settings);
		settingsButton.setOnClickListener(this);
		exitButton = (Button) findViewById(R.id.exit);
		exitButton.setOnClickListener(this);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1, data);
		ListView listView = (ListView) findViewById(R.id.list_view);
		listView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onClick: v=" + v);
		switch (v.getId()) {
		case R.id.bluetooth_settings:
			Intent mIntent = new Intent("android.settings.BLUETOOTH_SETTINGS");
			this.startActivity(mIntent);
			break;
		case R.id.reset_test:
			mKeyCode = "";
			mListData.clear();
			mGridView.setAdapter(new MyAdapter(this));
			break;
		case R.id.exit:
			MainActivity.this.finish();
			break;

		default:
			break;
		}
	}

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "onGenericMotionEvent: event=" + event);
		if (0 != (event.getSource() & InputDevice.SOURCE_CLASS_POINTER)) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_SCROLL:
				Log.w(TAG,
						"onGenericMotionEvent: AXIS_VSCROLL="
								+ event.getAxisValue(MotionEvent.AXIS_VSCROLL));
				if (event.getAxisValue(MotionEvent.AXIS_VSCROLL) < 0) {
					// down
				} else if (event.getAxisValue(MotionEvent.AXIS_VSCROLL) > 0) {
					// up
				}
				break;

			default:
				break;
			}
		}
		return super.onGenericMotionEvent(event);
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "dispatchKeyEvent: keyCode=" + event.getKeyCode());
		isVisible = false;
		switch (event.getKeyCode()) {
		case KeyEvent.KEYCODE_DPAD_UP:
			if (mKeyCode.indexOf("DPAD_UP") >= 0) {
				return false;
			}
			mKeyCode += "DPAD_UP\n";
			mListData.add(imgString[0]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_DPAD_DOWN:
			if (mKeyCode.indexOf("DPAD_DOWN") >= 0) {
				return false;
			}
			mKeyCode += "DPAD_DOWN\n";
			mListData.add(imgString[1]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_DPAD_LEFT:
			if (mKeyCode.indexOf("DPAD_LEFT") >= 0) {
				return false;
			}
			mKeyCode += "DPAD_LEFT\n";
			mListData.add(imgString[2]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_DPAD_RIGHT:
			if (mKeyCode.indexOf("DPAD_RIGHT") >= 0) {
				return false;
			}
			mKeyCode += "DPAD_RIGHT\n";
			mListData.add(imgString[3]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_HOME:
			if (mKeyCode.indexOf("HOME") >= 0) {
				return false;
			}
			mKeyCode += "HOME\n";
			mListData.add(imgString[4]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_MENU:
			if (mKeyCode.indexOf("MENU") >= 0) {
				return false;
			}
			mKeyCode += "MENU\n";
			mListData.add(imgString[5]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_BACK:
			if (mKeyCode.indexOf("BACK") >= 0) {
				return false;
			}
			mKeyCode += "BACK\n";
			mListData.add(imgString[6]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_ENTER:
			if (mKeyCode.indexOf("OK") >= 0) {
				return false;
			}
			mKeyCode += "OK\n";
			mListData.add(imgString[7]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_VOLUME_UP:
			if (mKeyCode.indexOf("VOLUME_UP") >= 0) {
				return false;
			}
			mKeyCode += "VOLUME_UP\n";
			mListData.add(imgString[8]);
			isVisible = true;
			break;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			if (mKeyCode.indexOf("VOLUME_DOWN") >= 0) {
				return false;
			}
			mKeyCode += "VOLUME_DOWN\n";
			mListData.add(imgString[9]);
			isVisible = true;
			break;

		default:
			break;
		}
		mGridView.setAdapter(new MyAdapter(this));
		if (isVisible) {
			return true;
		}
		return super.dispatchKeyEvent(event);
	}

	public class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		public int getCount() {
			if (mListData == null) {
				return 0;
			}
			return mListData.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = mInflater.inflate(R.layout.keycode_grid, null);
			ImageView imgview = (ImageView) convertView
					.findViewById(R.id.imgview);
			imgview.setEnabled(false);
			imgview.setBackgroundResource(mListData.get(position));
			return convertView;
		}
	}
}
