/**
 * 
 */
package com.sam.safemanager.ui;

import com.sam.safemanager.R;
import com.umeng.socialize.controller.UMServiceFactory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * @author Sam
 * @date 2013-5-15
 * @weibo:��ũ����ɣ
 */
public class SettingActivity extends Activity implements OnClickListener{
	TextView tvShare;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.act_settings);
		
		tvShare = (TextView) findViewById(R.id.tv_share);
		tvShare.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.tv_share:
			UMServiceFactory.shareTo(SettingActivity.this, "������ʹ�ã������������ֻ��ܼң���Ҳ���ðɣ�",null);
			break;
		}
		
	}

}
