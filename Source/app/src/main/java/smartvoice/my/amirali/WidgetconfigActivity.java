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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.EditText;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.graphics.Typeface;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.appwidget.AppWidgetManager;
import android.app.PendingIntent;

public class WidgetconfigActivity extends  AppCompatActivity  { 
	
	
	private String text = "";
	private boolean isDark = false;
	private String folder = "";
	private double n = 0;
	private String path = "";
	private String path2 = "";
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear2;
	private LinearLayout linear1;
	private ScrollView vscroll2;
	private TextView textview1;
	private ImageView imageview2;
	private LinearLayout linear3;
	private EditText edittext1;
	
	private SharedPreferences selector_mode;
	private Intent i = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.widgetconfig);
		initialize(_savedInstanceState);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
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
		vscroll2 = (ScrollView) findViewById(R.id.vscroll2);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		selector_mode = getSharedPreferences("selector_mode", Activity.MODE_PRIVATE);
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_popup();
			}
		});
	}
	
	private void initializeLogic() {
		Intent configIntent = getIntent();
		Bundle extras = configIntent.getExtras();
		if (extras != null) {
			            appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
			                    AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		Intent resultValue = new Intent();
		resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		setResult(RESULT_CANCELED, resultValue);
		if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID)
		finish();
		
		_circleRipple("#BDBDBD", imageview2);
		if (Double.parseDouble(Build.VERSION.SDK) > 27) {
			int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
			if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
				int[] colorsCRNDA2 = { Color.parseColor("#212121"), Color.parseColor("#212121") }; android.graphics.drawable.GradientDrawable CRNDA2 = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNDA2);
				CRNDA2.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)15,(int)15,(int)15,(int)15});
				CRNDA2.setStroke((int) 0, Color.parseColor("#000000"));
				linear1.setElevation((float) 15);
				linear1.setBackground(CRNDA2);
				linear2.setBackgroundColor(0xFF212121);
				vscroll2.setBackgroundColor(0xFF212121);
				linear3.setBackgroundColor(0xFF212121);
				imageview2.setImageResource(R.drawable.ic_more_vert_white);
				textview1.setTextColor(0xFFFFFFFF);
				edittext1.setTextColor(0xFFFFFFFF);
				Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
				View decor = getWindow().getDecorView();
				decor.setSystemUiVisibility(0);
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
					Window w =WidgetconfigActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
				}
				isDark = true;
			}else{
				int[] colorsCRNDA = { Color.parseColor("#ffffff"), Color.parseColor("#ffffff") }; android.graphics.drawable.GradientDrawable CRNDA = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.TOP_BOTTOM, colorsCRNDA);
				CRNDA.setCornerRadii(new float[]{(int)0,(int)0,(int)0,(int)0,(int)15,(int)15,(int)15,(int)15});
				CRNDA.setStroke((int) 0, Color.parseColor("#000000"));
				linear1.setElevation((float) 15);
				linear1.setBackground(CRNDA);
				linear2.setBackgroundColor(0xFFFFFFFF);
				vscroll2.setBackgroundColor(0xFFFFFFFF);
				linear3.setBackgroundColor(0xFFFFFFFF);
				imageview2.setImageResource(R.drawable.ic_more_vert_black);
				textview1.setTextColor(0xFF000000);
				edittext1.setTextColor(0xFF000000);
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
					Window w =WidgetconfigActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
				}
				isDark = false;
			}
		}
		_font();
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	
	@Override
	public void onBackPressed() {
		AppWidgetManager appWidgetManager2 = AppWidgetManager.getInstance(getApplicationContext());
		Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
		PendingIntent pendingIntent2 = PendingIntent.getActivity(getApplicationContext(), 0, intent2, 0);
		
		text = edittext1.getText().toString();
		RemoteViews views2 = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget);
		
		views2.setOnClickPendingIntent(R.id.note, pendingIntent2);
		
		views2.setCharSequence(R.id.note, "setText", text);
		
		appWidgetManager2.updateAppWidget(appWidgetId, views2);
		
		SharedPreferences prefs2 = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		SharedPreferences.Editor editor2 = prefs2.edit();
		editor2.putString(KEY_BUTTON_TEXT + appWidgetId, text);
		editor2.apply();
		
		Intent resultValue2 = new Intent();
		resultValue2.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
		setResult(RESULT_OK, resultValue2);
		finish();
	}
	public void _circleRipple (final String _color, final View _v) {
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_color)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , null, null);
		_v.setBackground(ripdrb);
	}
	
	
	public void _variables () {
	}
	
	public static final String SHARED_PREFS = "prefs";
	public static final String KEY_BUTTON_TEXT = "keyButtonText";
	private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
	
	{
	}
	
	
	public void _popup () {
		View popupView = getLayoutInflater().inflate(R.layout.popup_menu_2, null);
		
		final PopupWindow popup = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		final TextView txt1 = popupView.findViewById(R.id.txt1);
		
		final TextView txt2 = popupView.findViewById(R.id.txt2);
		
		final LinearLayout lin_popup = popupView.findViewById(R.id.lin_popup);
		
		final LinearLayout lin_import = popupView.findViewById(R.id.lin_import);
		
		final LinearLayout lin_apply = popupView.findViewById(R.id.lin_apply);
		
		final LinearLayout div = popupView.findViewById(R.id.div);
		
		final ImageView img1 = popupView.findViewById(R.id.img1);
		
		final ImageView img2 = popupView.findViewById(R.id.img2);
		txt1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		txt2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		lin_popup.setClipToOutline(true);
		if (isDark) {
			android.graphics.drawable.GradientDrawable round = new android.graphics.drawable.GradientDrawable ();
			round.setColor (Color.parseColor("#212121"));
			
			round.setCornerRadius (20);
			
			lin_popup.setBackground (round);
			
			lin_popup.setElevation(8);
			div.setBackgroundColor(0xFF303030);
			txt1.setTextColor(0xFFFFFFFF);
			txt2.setTextColor(0xFFFFFFFF);
		}
		else {
			android.graphics.drawable.GradientDrawable round2 = new android.graphics.drawable.GradientDrawable ();
			round2.setColor (Color.parseColor("#FFFFFF"));
			
			round2.setCornerRadius (20);
			
			lin_popup.setBackground (round2);
			
			lin_popup.setElevation(8);
			div.setBackgroundColor(0xFFBDBDBD);
			txt1.setTextColor(0xFF000000);
			txt2.setTextColor(0xFF000000);
		}
		lin_import.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice"))) {
					_showBottomSheet();
				}
				popup.dismiss();
			} });
		lin_apply.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
				Intent intent = new Intent(getApplicationContext(), MainActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
				
				text = edittext1.getText().toString();
				RemoteViews views = new RemoteViews(getApplicationContext().getPackageName(), R.layout.widget);
				
				views.setOnClickPendingIntent(R.id.note, pendingIntent);
				
				views.setCharSequence(R.id.note, "setText", text);
				
				appWidgetManager.updateAppWidget(appWidgetId, views);
				
				SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putString(KEY_BUTTON_TEXT + appWidgetId, text);
				editor.apply();
				
				Intent resultValue = new Intent();
				resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
				setResult(RESULT_OK, resultValue);
				finish();
				popup.dismiss();
			} });
		popup.setAnimationStyle(android.R.style.Animation_Dialog);
		
		popup.showAsDropDown(imageview2, 0,0);
	}
	
	
	public void _ListViewAdapter () {
	}
	private ListView listview1;
	
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
								_view = _inflater.inflate(R.layout.custom_list, null);
						}
						
						final LinearLayout linear_base = (LinearLayout) _view.findViewById(R.id.linear_base);
						final LinearLayout linear6 = (LinearLayout) _view.findViewById(R.id.linear6);
						final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
						final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
						
				
						//onBind
			path2 = listmap.get((int)_position).get("file").toString();
			textview1.setText(Uri.parse(path2).getLastPathSegment());
			if (isDark) {
				textview1.setTextColor(0xFFFFFFFF);
				linear_base.setBackgroundColor(0xFF212121);
			}
			else {
				textview1.setTextColor(0xFF000000);
				linear_base.setBackgroundColor(0xFFFFFFFF);
			}
			if (path2.endsWith(".txt")) {
				imageview1.setImageResource(R.drawable.text_file);
			}
			else {
				imageview1.setImageResource(R.drawable.file);
			}
			try{
				textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			} catch(Exception e){
				
			}
			return _view;
		}
	}
	{
	}
	
	
	public void _refreshList () {
		if (FileUtil.isExistFile(folder)) {
			listmap.clear();
			list.clear();
			FileUtil.listDir(folder, list);
			_SortString(list, true);
			n = 0;
			for(int _repeat14 = 0; _repeat14 < (int)(list.size()); _repeat14++) {
				if (!FileUtil.isDirectory(list.get((int)(n)))) {
					{
						HashMap<String, Object> _item = new HashMap<>();
						_item.put("file", list.get((int)(n)));
						listmap.add(_item);
					}
					
					n++;
				}
			}
		}
	}
	
	
	public void _SortString (final ArrayList<String> _listString, final boolean _Ascending) {
		Collections.sort(_listString, String.CASE_INSENSITIVE_ORDER);
		if (!_Ascending) {
			Collections.reverse(_listString);
		}
	}
	
	
	public void _showBottomSheet () {
		folder = FileUtil.getExternalStorageDir().concat("/smart voice");
		_refreshList();
		final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(WidgetconfigActivity.this);
		
		View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_sheet,null );
		bottomSheetDialog.setContentView(bottomSheetView);
		
		bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
		ListView listview1 = (ListView) bottomSheetView.findViewById(R.id.listview1);
		
		LinearLayout rootview = (LinearLayout) bottomSheetView.findViewById(R.id.rootview);
		
		LinearLayout linear3 = (LinearLayout) bottomSheetView.findViewById(R.id.linear3);
		if (isDark) {
			android.graphics.drawable.GradientDrawable Ui2 = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			Ui2.setColor(0xFF212121);
			Ui2.setCornerRadii(new float[]{
				d*20,d*20,d*20 ,d*20,d*0,d*0 ,d*0,d*0});
			rootview.setBackground(Ui2);
		}
		else {
			android.graphics.drawable.GradientDrawable Ui = new android.graphics.drawable.GradientDrawable();
			int d = (int) getApplicationContext().getResources().getDisplayMetrics().density;
			Ui.setColor(0xFFFFFFFF);
			Ui.setCornerRadii(new float[]{
				d*20,d*20,d*20 ,d*20,d*0,d*0 ,d*0,d*0});
			rootview.setBackground(Ui);
		}
		linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)360, 0xFFBDBDBD));
		// ℹ️ enable nested scroll
		listview1.setNestedScrollingEnabled(true);
		// ℹ️ remove blue wave
		listview1.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		// ℹ️ remove vertical scroll bar
		listview1.setVerticalScrollBarEnabled(false);
		// ℹ️ listmap to listview
		listview1.setAdapter(new Listview1Adapter(listmap));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		// ℹ️ listview set item click listener
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
						@Override
						public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
								final int _position = _param3;
				path = listmap.get((int)_position).get("file").toString();
				if (path.endsWith(".java") || (path.endsWith(".xml") || (path.endsWith(".php") || (path.endsWith(".html") || (path.endsWith(".css") || (path.endsWith(".json") || (path.endsWith(".js") || (path.endsWith(".htm") || path.endsWith(".txt"))))))))) {
					if (edittext1.getText().toString().equals("")) {
						edittext1.setText(FileUtil.readFile(path));
					}
					else {
						edittext1.setText(edittext1.getText().toString().concat("\n".concat(FileUtil.readFile(path))));
					}
					bottomSheetDialog.dismiss();
				}
						}
				});
		// ℹ️ fix navigation bar color
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1){
			Window window = bottomSheetDialog.getWindow();
			if(window != null){
				DisplayMetrics metrics = new DisplayMetrics();
				window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
				GradientDrawable dimDrawable = new GradientDrawable();
				GradientDrawable navigationBarDrawable = new GradientDrawable();
				navigationBarDrawable.setShape(GradientDrawable.RECTANGLE);
				// ℹ️ get navigation bar color
				if (isDark) {
					navigationBarDrawable.setColor(Color.parseColor("#212121"));
				}
				else {
					navigationBarDrawable.setColor(Color.parseColor("#FFFFFF"));
				}
				Drawable[] layers = {dimDrawable, navigationBarDrawable};
				LayerDrawable windowBackground = new LayerDrawable(layers);
				windowBackground.setLayerInsetTop(1, metrics.heightPixels);
				window.setBackgroundDrawable(windowBackground);
			}
		}
		
		// ℹ️ make full screen bottom sheet
		
		// ℹ️ get listview height
		int listviewElementsheight = 0;
		for (int i = 0; i < ((Listview1Adapter)listview1.getAdapter()).getCount(); i++){
			View mView = ((Listview1Adapter)listview1.getAdapter()).getView(i, null, listview1);
			        mView.measure(
			                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED),
			                MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			        listviewElementsheight += mView.getMeasuredHeight();
		}
		int listviewHeight = listviewElementsheight;
		
		// ℹ️ get window height
		DisplayMetrics display = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(display);
		int windowHeight = display.heightPixels;
		
		// ℹ️ now we can start
		if(windowHeight <= listviewHeight ){
			
			FrameLayout bottomSheet = (FrameLayout) bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
			com.google.android.material.bottomsheet.BottomSheetBehavior behavior = com.google.android.material.bottomsheet.BottomSheetBehavior.from(bottomSheet);
			ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
			if (layoutParams != null) {
				layoutParams.height = windowHeight;
			}
			bottomSheet.setLayoutParams(layoutParams);
			behavior.setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED);
		}
		bottomSheetDialog.setCancelable(true);
		bottomSheetDialog.show();
	}
	
	
	public void _font () {
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
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