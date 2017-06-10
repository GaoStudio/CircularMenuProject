package com.jaye.circularmenuproject;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity implements MyCenterView.onViewClick {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}

	@Override
	public void setOnClick(int type) {
		Toast.makeText(MainActivity.this,"Click:::"+type,Toast.LENGTH_SHORT).show();
	}
}
