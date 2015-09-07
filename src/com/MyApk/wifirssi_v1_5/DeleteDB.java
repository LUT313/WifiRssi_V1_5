package com.MyApk.wifirssi_v1_5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteDB extends ListActivity {
	private Button button;
	private MyAdapter adapter;
	private List<Map<String, Object>> tableName = new ArrayList<Map<String, Object>>();
	private MyDB db;
	private String chooseTable = "ghj";
	private Toast toast;
	private String deleteTable;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deletedb);
		ExitApp.getInstance().addActivity(this);
		db = new MyDB(this);
		tableName = db.tableNameQuery();
		adapter = new MyAdapter(this);
		setListAdapter(adapter);
		button = (Button) findViewById(R.id.buttonback);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(DeleteDB.this, MainActivity.class);
				intent.putExtra("Tablename", chooseTable);
				startActivity(intent);
			}
		});

	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		chooseTable = (String)tableName.get(position).get("title");
		if (toast == null) {
			toast = Toast.makeText(DeleteDB.this, chooseTable,
					Toast.LENGTH_SHORT);
		} else {
			toast.setText(chooseTable);
		}
		toast.show();

	}

	public final class ViewHolder {
		// public ImageView img;
		public TextView title;
		public TextView info;
		public Button viewBtn;
	}

	public class MyAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return tableName.size();
		}

		@Override
		public Object getItem(int arg0) {
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			return 0;
		}

		/**
		 * listView�ڿ�ʼ���Ƶ�ʱ��ϵͳ���ȵ���getCount�����������������ķ���ֵ�õ�listView�ĳ���
		 * Ȼ�����������ȣ�����getView������һ����ÿһ�С� ������getCount��������ֵ��0�Ļ����б�����ʾͬ��return
		 * 1����ֻ��ʾһ�С� ��ϵͳ��ʾ�б�ʱ������ʵ����һ����������
		 * 
		 * ���ֶ��������ʱ�������ֶ�ӳ�����ݣ�����Ҫ��дgetView���������� ϵͳ�ڻ����б��ÿһ�е�ʱ�򶼽����ô˷���������������������ԣ�
		 * getView()���������� position��ʾ����ʾ���ǵڼ��� covertView�ǴӲ����ļ���inflate���Ĳ���
		 * ������LayoutInflater�ķ���������õ�vlist2.xml�ļ���ȡ��Viewʵ��������ʾ��
		 * Ȼ��xml�ļ��еĸ������ʵ�������򵥵�findViewById()�������� ��������Խ����ݶ�Ӧ�������������
		 * 
		 * ���ǰ�ťΪ����Ӧ����¼�����ҪΪ����ӵ�����������������ܲ������¼��� ����һ���Զ����listView�������
		 * 
		 * ���������ǻع�ͷ��������������� ϵͳҪ����ListView�ˣ������Ȼ��Ҫ���Ƶ�����б�ĳ��ȣ�Ȼ��ʼ���Ƶ�һ�У���ô�����أ�
		 * ����getView()����������������������Ȼ��һ��View��ʵ������һ��ViewGroup�� Ȼ����ʵ�������ø����������ʾ��
		 * ��������һ���ˡ��ٻ�����һ�У�ֱ������Ϊֹ��
		 */
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {

			ViewHolder holder = null;
			if (convertView == null) {

				holder = new ViewHolder();

				convertView = mInflater.inflate(R.layout.item, null);
				
				holder.title = (TextView) convertView
						.findViewById(R.id.listitem3ItemTitle);
				holder.info = (TextView) convertView
						.findViewById(R.id.listitem3ItemText);
				holder.viewBtn = (Button) convertView
						.findViewById(R.id.listitem3button);
				convertView.setTag(holder);

			} else {

				holder = (ViewHolder) convertView.getTag();
			}

			holder.title.setText((String) tableName.get(position).get("title"));
			holder.info.setText((String) tableName.get(position).get("info"));
			holder.viewBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// showInfo(position);
					deleteTable = "DROP TABLE " + chooseTable;
					db.deleteTable(deleteTable);
					tableName.remove(position);
					// ͨ����������֪��ɾ���ˣ�������ôˢ��ListView�أ�
					// ֻ��Ҫ��������һ��adapter
					setListAdapter(adapter);
				}
			});
			return convertView;
		}
	}
}
