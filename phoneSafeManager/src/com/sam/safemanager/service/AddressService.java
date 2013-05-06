/**
 * 
 */
package com.sam.safemanager.service;

import com.sam.safemanager.engine.NumberAddressService;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

/**
 * @author Sam
 * @date 2013-5-6
 * @weibo:��ũ����ɣ
 */
public class AddressService extends Service {
	private TelephonyManager manager;
	private MyPhoneListener listener;
	private TextView tvAddress;
	private WindowManager windowManager;
	
	@Override
	public void onCreate() {
		super.onCreate();
		//ע��ϵͳ�绰�������ļ�����
		listener = new MyPhoneListener();
		manager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		manager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	@Override
	public void onDestroy() {
		manager.listen(listener, PhoneStateListener.LISTEN_NONE);
		listener = null;
		super.onDestroy();
	}

	/* (non-Javadoc)
	 * @see android.app.Service#onBind(android.content.Intent)
	 */
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private class MyPhoneListener extends PhoneStateListener{

		/**
		 * �绰״̬�����ı���õķ���
		 */
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch(state){
			case TelephonyManager.CALL_STATE_IDLE: //���ھ�ֹ״̬���޵绰
			case TelephonyManager.CALL_STATE_OFFHOOK: //
				if(tvAddress!=null){
					windowManager.removeView(tvAddress);
					tvAddress = null;
				}
				break;
			case TelephonyManager.CALL_STATE_RINGING://����״̬
				
				String address = NumberAddressService.getAddress(incomingNumber);
				showLocation(address);
				break;
			}
			super.onCallStateChanged(state, incomingNumber);
		}
		
	}
	
	private void showLocation(String address){
		WindowManager.LayoutParams params = new LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				0, 0, WindowManager.LayoutParams.TYPE_TOAST,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, 
				PixelFormat.TRANSLUCENT);
		tvAddress  = new TextView(AddressService.this);
		tvAddress.setText(address);
		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
		windowManager.addView(tvAddress, params);
	}

}
