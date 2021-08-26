package smartvoice.my.amirali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.AdapterView;
import android.view.View;
import com.google.gson.Gson;
import android.graphics.Typeface;
import com.google.gson.reflect.TypeToken;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class BackupActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private String format = "";
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	
	private LinearLayout linear2;
	private LinearLayout linear1;
	private LinearLayout lin_undo;
	private ImageView imageview1;
	private TextView textview1;
	private SwipeRefreshLayout swiperefreshlayout1;
	private ListView listview1;
	private LinearLayout lin_snk_bar;
	private TextView tv_undo_txt;
	private TextView tv_undo_btn;
	
	private SharedPreferences Backup_Data;
	private TimerTask t;
	private SharedPreferences dark;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.backup);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		lin_undo = (LinearLayout) findViewById(R.id.lin_undo);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		swiperefreshlayout1 = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout1);
		listview1 = (ListView) findViewById(R.id.listview1);
		lin_snk_bar = (LinearLayout) findViewById(R.id.lin_snk_bar);
		tv_undo_txt = (TextView) findViewById(R.id.tv_undo_txt);
		tv_undo_btn = (TextView) findViewById(R.id.tv_undo_btn);
		Backup_Data = getSharedPreferences("Backup_Data", Activity.MODE_PRIVATE);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override 
			public void onRefresh() {
				_refresh();
				swiperefreshlayout1.setRefreshing(false);
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/"))) {
					final AlertDialog dialog1 = new AlertDialog.Builder(BackupActivity.this).create();
					View inflate = getLayoutInflater().inflate(R.layout.simple_dialog,null); 
					dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
					dialog1.setView(inflate);
					TextView t1 = (TextView) inflate.findViewById(R.id.t1);
					
					TextView t2 = (TextView) inflate.findViewById(R.id.t2);
					
					TextView b_no = (TextView) inflate.findViewById(R.id.b_no);
					
					TextView b_yes = (TextView) inflate.findViewById(R.id.b_yes);
					
					LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
					t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
					t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
					b_no.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
					b_yes.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
					t1.setText("Restore");
					t2.setText("Do you want to restore the ".concat(list.get((int)_position).get("title").toString()));
					b_no.setText("No");
					b_yes.setText("Yes");
					_rippleRoundStroke(b_yes, "#2196F3", "#EEEEEE", 15, 0, "#000000");
					if (dark.getString("dark", "").equals("true")) {
						_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(b_no, "#212121", "#EEEEEE", 15, 2.5d, "#EEEEEE");
						t1.setTextColor(0xFFFFFFFF);
						t2.setTextColor(0xFFFFFFFF);
						b_no.setTextColor(0xFFFFFFFF);
					}
					else {
						_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(b_no, "#FFFFFF", "#EEEEEE", 15, 2.5d, "#EEEEEE");
						t1.setTextColor(0xFF000000);
						t2.setTextColor(0xFF000000);
						b_no.setTextColor(0xFF000000);
					}
					b_no.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							dialog1.dismiss();
						}
					});
					b_yes.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							if (!FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(list.get((int)_position).get("title").toString())))) {
								if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/"))) {
									FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(list.get((int)_position).get("title").toString())), list.get((int)_position).get("text").toString());
									_custom_toast("Restored");
								}
							}
							else {
								final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(BackupActivity.this);
								
								View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.button_sheet ,null );
								bottomSheetDialog.setContentView(bottomSheetView);
								
								bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
								TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
								
								TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
								
								TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
								
								ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
								
								LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
								
								LinearLayout e1 = (LinearLayout) bottomSheetView.findViewById(R.id.e1);
								
								final EditText kode = new EditText(BackupActivity.this);
								kode.setHint("Name");
								kode.setSingleLine(true);
								kode.setTextSize((float)18);
								kode.setBackgroundColor(Color.TRANSPARENT);
								
								kode.setHintTextColor(0xFF607D8B);
								if (dark.getString("dark", "").equals("true")) {
									kode.setTextColor(Color.parseColor("#FFFFFF"));
								}
								else {
									kode.setTextColor(Color.parseColor("#000000"));
								}
								LinearLayout.LayoutParams linear = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
								
								kode.setLayoutParams(linear);
								e1.addView(kode);
								kode.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
								t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
								b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
								b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
								b1.setText("Cancel");
								b2.setText("Rename");
								t1.setText("There is ".concat(list.get((int)_position).get("title").toString().concat(" file, rename the backup")));
								kode.setText(list.get((int)_position).get("title").toString());
								i1.setImageResource(R.drawable.rename_box1);
								if (dark.getString("dark", "").equals("true")) {
									_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
									_rippleRoundStroke(b2, "#2196F3", "#40FFFFFF", 15, 0, "#000000");
									_rippleRoundStroke(b1, "#212121", "#9E9E9E", 15, 2.5d, "#9E9E9E");
									b1.setTextColor(0xFFFFFFFF);
									t1.setTextColor(0xFFFFFFFF);
								}
								else {
									_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
									_rippleRoundStroke(b2, "#2196F3", "#40FFFFFF", 15, 0, "#000000");
									_rippleRoundStroke(b1, "#FFFFFF", "#EEEEEE", 15, 2.5d, "#EEEEEE");
									b1.setTextColor(0xFF000000);
									t1.setTextColor(0xFF000000);
								}
								b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
										bottomSheetDialog.dismiss();
									}
								});
								b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
										if (kode.getText().toString().length() > 0) {
											if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/"))) {
												if (!FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString())))) {
													format = kode.getText().toString();
													if (format.endsWith(".java") || (format.endsWith(".xml") || (format.endsWith(".php") || (format.endsWith(".html") || (format.endsWith(".css") || (format.endsWith(".json") || (format.endsWith(".js") || (format.endsWith(".htm") || format.endsWith(".txt"))))))))) {
														FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString())), list.get((int)_position).get("text").toString());
														list.get((int)_position).put("title", kode.getText().toString());
														Backup_Data.edit().putString("Backup_Data", new Gson().toJson(list)).commit();
														_refresh();
														bottomSheetDialog.dismiss();
														_custom_toast("Restored");
													}
													else {
														
													}
												}
											}
										}
									}
								});
								bottomSheetDialog.setCancelable(true);
								bottomSheetDialog.show();
							}
							dialog1.dismiss();
						}
					});
					dialog1.setCancelable(true);
					dialog1.show();
				}
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				// Nothing yet
				return true;
			}
		});
	}
	
	private void initializeLogic() {
		listview1.setSelector(android.R.color.transparent);
		listview1.setVerticalScrollBarEnabled(false);
		ViewConfiguration vc = ViewConfiguration.get(BackupActivity.this);
		 mMinimunDistance = vc.getScaledTouchSlop();
		lin_undo.setVisibility(View.GONE);
		lin_snk_bar.setScaleY((float)(0));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		tv_undo_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		tv_undo_btn.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		if (dark.getString("dark", "").equals("true")) {
			linear1.setBackgroundColor(0xFF212121);
			linear2.setBackgroundColor(0xFF212121);
			swiperefreshlayout1.setBackgroundColor(0xFF212121);
			imageview1.setImageResource(R.drawable.ic_arrow_back_white);
			textview1.setTextColor(0xFFFFFFFF);
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =BackupActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
			}
		}
		else {
			linear1.setBackgroundColor(0xFFFFFFFF);
			linear2.setBackgroundColor(0xFFFFFFFF);
			swiperefreshlayout1.setBackgroundColor(0xFFFFFFFF);
			imageview1.setImageResource(R.drawable.ic_arrow_back_black);
			textview1.setTextColor(0xFF000000);
			if (Double.parseDouble(Build.VERSION.SDK) > 28) {
				Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#F5F5F5"));
			}
			else {
				Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#000000"));
			}
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(0xFFFFFFFF);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =BackupActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
			}
		}
		_circleRipple("#BDBDBD", imageview1);
		{
			android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
			SketchUi.setColor(0xFF000000);SketchUi.setCornerRadius(getDip(12));
			lin_snk_bar.setElevation(getDip(5));
			lin_snk_bar.setBackground(SketchUi);
		}
		_refresh();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _variables () {
	}
	private int mMinimunDistance;
	{
	}
	
	
	public void _deleteListMapAt (final double _pos) {
		final HashMap<String,Object> map = list.get((int) _pos);
		list.remove((int)(_pos));
		Parcelable state = listview1.onSaveInstanceState();
		listview1.setAdapter(new Listview1Adapter(list));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		listview1.onRestoreInstanceState(state);
		lin_undo.setVisibility(View.VISIBLE);
		if (t != null) {
			t.cancel();
		}
		lin_snk_bar.animate() 
		.scaleY(1)
		.setDuration(200)
		.setListener(
		new AnimatorListenerAdapter() { 
					@Override public void onAnimationEnd(Animator animation) { 
								super.onAnimationEnd(animation);
				tv_undo_txt.setText(map.get("title").toString().concat(" deleted"));
				tv_undo_btn.setOnClickListener(new OnClickListener() { public void onClick(View view) {
						list.add((int)_pos, map);
						Backup_Data.edit().putString("Backup_Data", new Gson().toJson(list)).commit();
						lin_snk_bar.animate() 
						.scaleY(0)
						.setDuration(200)
						.setListener(
						new AnimatorListenerAdapter() { 
									@Override public void onAnimationEnd(Animator animation) { 
												super.onAnimationEnd(animation);
								lin_undo.setVisibility(View.GONE);
							} });
						Parcelable state = listview1.onSaveInstanceState();
						listview1.setAdapter(new Listview1Adapter(list));
						((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
						listview1.onRestoreInstanceState(state);
					} });
				lin_undo.setVisibility(View.VISIBLE);
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								lin_snk_bar.animate() 
								.scaleY(0)
								.setDuration(200)
								.setListener(
								new AnimatorListenerAdapter() { 
											@Override public void onAnimationEnd(Animator animation) { 
														super.onAnimationEnd(animation);
										lin_undo.setVisibility(View.GONE);
									} });
							}
						});
					}
				};
				_timer.schedule(t, (int)(2500));
			} });
	}
	
	
	public void _listview_itemUI (final View _view, final String _color) {
		android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
		int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		SketchUi.setColor(Color.parseColor(_color));
		SketchUi.setCornerRadius(getDip(9));
		_view.setElevation(d*5);
		_view.setBackground(SketchUi);
	}
	
	
	public void _listview_itemUI2 (final View _view, final String _color) {
		android.graphics.drawable.GradientDrawable SketchUi = new android.graphics.drawable.GradientDrawable();
		int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		SketchUi.setColor(Color.parseColor(_color));
		SketchUi.setCornerRadius(getDip(9));
		_view.setBackground(SketchUi);
	}
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _circleRipple (final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _refresh () {
		if (!Backup_Data.getString("Backup_Data", "").equals("")) {
			list = new Gson().fromJson(Backup_Data.getString("Backup_Data", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
			listview1.setAdapter(new Listview1Adapter(list));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		}
	}
	
	
	public void _custom_toast (final String _text) {
		if (dark.getString("dark", "").equals("true")) {
			LayoutInflater inflater = getLayoutInflater(); View toastL = inflater.inflate(R.layout.toast, null);
			LinearLayout line_toast = (LinearLayout) toastL.findViewById(R.id.line_toast);
			
			TextView text1 = (TextView) toastL.findViewById(R.id.text1);
			text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			text1.setText(_text);
			android.graphics.drawable.GradientDrawable ln = new android.graphics.drawable.GradientDrawable ();
			ln.setColor (Color.parseColor("#2E2F32"));
			
			ln.setCornerRadius (19);
			
			line_toast.setBackground (ln);
			
			line_toast.setElevation(6);
			text1.setTextColor(0xFFFFFFFF);
			Toast toast = new Toast(getApplicationContext()); 
			
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(toastL);
			toast.show();
		}
		else {
			LayoutInflater inflater = getLayoutInflater(); View toastL = inflater.inflate(R.layout.toast, null);
			LinearLayout line_toast = (LinearLayout) toastL.findViewById(R.id.line_toast);
			
			TextView text1 = (TextView) toastL.findViewById(R.id.text1);
			text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			text1.setText(_text);
			android.graphics.drawable.GradientDrawable ln = new android.graphics.drawable.GradientDrawable ();
			ln.setColor (Color.parseColor("#FFFFFF"));
			
			ln.setCornerRadius (19);
			
			line_toast.setBackground (ln);
			
			line_toast.setElevation(6);
			text1.setTextColor(0xFF000000);
			Toast toast = new Toast(getApplicationContext()); 
			
			toast.setDuration(Toast.LENGTH_SHORT);
			toast.setView(toastL);
			toast.show();
		}
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.b_item, null);
			}
			
			final LinearLayout linear_parent = (LinearLayout) _view.findViewById(R.id.linear_parent);
			final LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
			final LinearLayout linear_base = (LinearLayout) _view.findViewById(R.id.linear_base);
			final ImageView imageview2 = (ImageView) _view.findViewById(R.id.imageview2);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final ImageView imageview3 = (ImageView) _view.findViewById(R.id.imageview3);
			final TextView text_delet = (TextView) _view.findViewById(R.id.text_delet);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final LinearLayout linear6 = (LinearLayout) _view.findViewById(R.id.linear6);
			final TextView title = (TextView) _view.findViewById(R.id.title);
			final TextView time = (TextView) _view.findViewById(R.id.time);
			
			title.setText(list.get((int)_position).get("title").toString());
			time.setText(list.get((int)_position).get("time").toString());
			text_delet.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
			if (dark.getString("dark", "").equals("true")) {
				_listview_itemUI(linear_parent, "#000000");
				_listview_itemUI2(linear_base, "#000000");
				_listview_itemUI2(linear4, "#000000");
				text_delet.setTextColor(0xFFFFFFFF);
				title.setTextColor(0xFFFFFFFF);
				time.setTextColor(0xFFFFFFFF);
			}
			else {
				_listview_itemUI(linear_parent, "#FFFFFF");
				_listview_itemUI2(linear_base, "#FFFFFF");
				_listview_itemUI2(linear4, "#FFFFFF");
				text_delet.setTextColor(0xFF000000);
				title.setTextColor(0xFF000000);
				time.setTextColor(0xFF9E9E9E);
			}
			if (list.get((int)_position).get("title").toString().endsWith(".txt")) {
				imageview1.setImageResource(R.drawable.text_file);
			}
			else {
				imageview1.setImageResource(R.drawable.file);
			}
			text_delet.setVisibility(View.GONE);
			linear_parent.setClipToOutline(true);
			linear_base.setOnTouchListener(new OnTouchListener() {
				float x =0;
				float oldX =0;
				float diffX = 0;
				float downX =0;
				float downY = 0;
				long downTime =0;
				boolean longClicked;
				boolean moved = false;
				Handler longClickHandler;
				Runnable runnable;
				 @Override public boolean onTouch(View v, MotionEvent event) {
					int eid =event.getAction();
								switch (eid) {
						
						case MotionEvent.ACTION_MOVE:
						if (!moved) {
							if (Math.abs(downX - event.getRawX()) >  mMinimunDistance || Math.abs(downY - event.getRawY()) >  mMinimunDistance) {
								moved = true;
								longClickHandler.removeCallbacks(runnable);
							}
						}
						x = (int) event.getRawX();
						diffX = x - oldX;
						if (Math.abs(downX - x) > Math.abs(linear_base.getWidth()/8) || linear_base.getTranslationX() != 0) {
							linear_base.setTranslationX((int)( linear_base.getTranslationX()+diffX) );
							listview1.requestDisallowInterceptTouchEvent(true);
						}
						oldX = x;
						break;
						case MotionEvent.ACTION_DOWN :
						oldX=event.getRawX();
						downX=event.getRawX();
						downY=event.getRawY();
						downTime = (Long) System.currentTimeMillis();
						moved = false;
						longClicked = false;
						//for long click listener 
						longClickHandler = new Handler(); 
						 runnable = new Runnable() { 
								@Override public void run() { 
								longClicked = true;
								AdapterView.OnItemLongClickListener listener = listview1.getOnItemLongClickListener();
								listener.onItemLongClick(listview1,getView(_position, null, null), _position,getItemId(_position));
								}
						}; 
						longClickHandler.postDelayed(runnable, ViewConfiguration.getLongPressTimeout());
						break;
						case MotionEvent.ACTION_UP:
						if (!moved&& ! longClicked) {
							//click event
							listview1.performItemClick(getView(_position, null, null), _position,getItemId(_position));
						}
						default :
						longClickHandler.removeCallbacks(runnable);
						if (System.currentTimeMillis() - downTime   < ViewConfiguration.getLongPressTimeout() &&  (linear_base.getTranslationX() != 0)) {
							text_delet.setVisibility(View.VISIBLE);
							imageview3.setVisibility(View.GONE);
							imageview2.setVisibility(View.GONE);
							linear_base.animate() 
							.translationX((linear_base.getTranslationX() >0)?linear_base.getWidth():(-1*linear_parent.getWidth()) )
							.setDuration(300)
							.setListener(
							new AnimatorListenerAdapter() { 
										@Override public void onAnimationEnd(Animator animation) { 
													super.onAnimationEnd(animation);
									linear_parent.animate() 
									.scaleY(0)
									.setDuration(200)
									.setListener(
									new AnimatorListenerAdapter() { 
												@Override public void onAnimationEnd(Animator animation) { 
															super.onAnimationEnd(animation);
											_deleteListMapAt(_position);
											Backup_Data.edit().putString("Backup_Data", new Gson().toJson(list)).commit();
										} });
								} });
						}
						else {
							if (Math.abs(linear_base.getTranslationX()) > (linear_base.getWidth() /2)) {
								text_delet.setVisibility(View.VISIBLE);
								imageview3.setVisibility(View.GONE);
								imageview2.setVisibility(View.GONE);
								linear_base.animate() 
								.translationX((linear_base.getTranslationX() >0)?linear_base.getWidth():(-1*linear_parent.getWidth()) )
								.setDuration(300)
								.setListener(
								new AnimatorListenerAdapter() { 
											@Override public void onAnimationEnd(Animator animation) { 
														super.onAnimationEnd(animation);
										linear_parent.animate() 
										.scaleY(0)
										.setDuration(200)
										.setListener(
										new AnimatorListenerAdapter() { 
													@Override public void onAnimationEnd(Animator animation) { 
																super.onAnimationEnd(animation);
												_deleteListMapAt(_position);
												Backup_Data.edit().putString("Backup_Data", new Gson().toJson(list)).commit();
											} });
									} });
							}
							else {
								linear_base.animate(). translationX(0);
							}
						}
						listview1.requestDisallowInterceptTouchEvent(false);
						break;
					}
					return true;
				}
			});
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}