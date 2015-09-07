package com.MyApk.wifirssi_v1_5;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.Application;

public class ExitApp extends Application {
	private List<Activity> activities = new ArrayList<Activity>();

	private static ExitApp instance;

	private ExitApp() {
	} // ����ģʽ�л�ȡΨһ��application

	public static ExitApp getInstance()

	{
		if (null == instance)

		{
			instance = new ExitApp();

		}
		return instance;

	} // ���Activity��list��

	public void addActivity(Activity activity) {
		activities.add(activity);
	}

	@Override
	// ���������list�е�Activity���˳�
	public void onTerminate()

	{
		super.onTerminate();
		for (Activity activity : activities) {
			activity.finish();
		}

		android.os.Process.killProcess(android.os.Process.myPid());
	}

}
