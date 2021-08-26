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
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.graphics.Typeface;
import java.text.DecimalFormat;
import com.bumptech.glide.Glide;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class CopyFileActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private String folder = "";
	private double n = 0;
	private double B = 0;
	private double KB = 0;
	private double MB = 0;
	private double GB = 0;
	private double TB = 0;
	private String returnedSize = "";
	private String path = "";
	private String Upfolder = "";
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	
	private LinearLayout linear1;
	private HorizontalScrollView hscroll1;
	private LinearLayout linear7;
	private LinearLayout lin_copy;
	private LinearLayout linear5;
	private TextView textview2;
	private ListView listview1;
	private ImageView imageview1;
	private TextView textview3;
	private TextView cancel;
	private TextView copy;
	
	private SharedPreferences dark;
	private TimerTask timer;
	private Intent i = new Intent();
	private SharedPreferences copy_path;
	private SharedPreferences selector_mode;
	private SharedPreferences tts_S;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.copy_file);
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
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		hscroll1 = (HorizontalScrollView) findViewById(R.id.hscroll1);
		linear7 = (LinearLayout) findViewById(R.id.linear7);
		lin_copy = (LinearLayout) findViewById(R.id.lin_copy);
		linear5 = (LinearLayout) findViewById(R.id.linear5);
		textview2 = (TextView) findViewById(R.id.textview2);
		listview1 = (ListView) findViewById(R.id.listview1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview3 = (TextView) findViewById(R.id.textview3);
		cancel = (TextView) findViewById(R.id.cancel);
		copy = (TextView) findViewById(R.id.copy);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		copy_path = getSharedPreferences("copy_path", Activity.MODE_PRIVATE);
		selector_mode = getSharedPreferences("selector_mode", Activity.MODE_PRIVATE);
		tts_S = getSharedPreferences("tts_S", Activity.MODE_PRIVATE);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				if (FileUtil.isDirectory(listmap.get((int)_position).get("file").toString())) {
					folder = listmap.get((int)_position).get("file").toString();
					_refreshList();
				}
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				lin_copy.animate() 
				.scaleY(0)
				.setDuration(200)
				.setListener(
				new AnimatorListenerAdapter() { 
							@Override public void onAnimationEnd(Animator animation) { 
										super.onAnimationEnd(animation);
						lin_copy.setVisibility(View.GONE);
					} });
			}
		});
		
		copy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!path.equals("")) {
					if (selector_mode.getString("selector", "").equals("copy")) {
						if (FileUtil.isExistFile(copy_path.getString("copy_path", ""))) {
							if (FileUtil.isExistFile(path)) {
								if (path.substring((int)(path.length() - 1), (int)(path.length())).equals("/")) {
									if (FileUtil.isExistFile(path.concat(Uri.parse(copy_path.getString("copy_path", "")).getLastPathSegment()))) {
										_Rename_Dialog();
									}
									else {
										FileUtil.copyFile(copy_path.getString("copy_path", ""), path.concat(Uri.parse(copy_path.getString("copy_path", "")).getLastPathSegment()));
										_refreshList();
									}
								}
								else {
									if (FileUtil.isExistFile(path.concat("/".concat(Uri.parse(copy_path.getString("copy_path", "")).getLastPathSegment())))) {
										_Rename_Dialog();
									}
									else {
										FileUtil.copyFile(copy_path.getString("copy_path", ""), path.concat("/".concat(Uri.parse(copy_path.getString("copy_path", "")).getLastPathSegment())));
										_refreshList();
									}
								}
							}
						}
						else {
							
						}
					}
					else {
						if (selector_mode.getString("selector", "").equals("select_folder")) {
							if (FileUtil.isExistFile(path)) {
								if (path.substring((int)(path.length() - 1), (int)(path.length())).equals("/")) {
									tts_S.edit().putString("storage_location", path).commit();
								}
								else {
									tts_S.edit().putString("storage_location", path.concat("/")).commit();
								}
								tts_S.edit().putString("name", Uri.parse(path).getLastPathSegment()).commit();
							}
						}
						else {
							
						}
					}
				}
				lin_copy.animate() 
				.scaleY(0)
				.setDuration(200)
				.setListener(
				new AnimatorListenerAdapter() { 
							@Override public void onAnimationEnd(Animator animation) { 
										super.onAnimationEnd(animation);
						lin_copy.setVisibility(View.GONE);
					} });
			}
		});
	}
	
	private void initializeLogic() {
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		cancel.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		copy.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		hscroll1.setHorizontalScrollBarEnabled(false);
		listview1.setVerticalScrollBarEnabled(false);
		listview1.setVerticalScrollBarEnabled(false);
		if (dark.getString("dark", "").equals("true")) {
			linear1.setBackgroundColor(0xFF212121);
			hscroll1.setBackgroundColor(0xFF212121);
			linear7.setBackgroundColor(0xFF212121);
			linear5.setBackgroundColor(0xFF212121);
			textview2.setTextColor(0xFFFFFFFF);
			Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
			View decor = getWindow().getDecorView();
			decor.setSystemUiVisibility(0);
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
				Window w =CopyFileActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
			}
		}
		else {
			linear1.setBackgroundColor(0xFFFFFFFF);
			hscroll1.setBackgroundColor(0xFFFFFFFF);
			linear7.setBackgroundColor(0xFFFFFFFF);
			linear5.setBackgroundColor(0xFFFFFFFF);
			textview2.setTextColor(0xFF000000);
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
				Window w =CopyFileActivity.this.getWindow();
				w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
				w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
			}
		}
		_rippleRoundStroke(cancel, "#E3F2FD", "#64B5F6", 25, 0, "#000000");
		_rippleRoundStroke(copy, "#E3F2FD", "#64B5F6", 25, 0, "#000000");
		_initSlideActivity();
		if (selector_mode.getString("selector", "").equals("copy")) {
			if (Uri.parse(copy_path.getString("copy_path", "")).getLastPathSegment().endsWith(".txt")) {
				imageview1.setImageResource(R.drawable.text_file);
			}
			else {
				imageview1.setImageResource(R.drawable.file);
			}
			copy.setText("Copy Here");
			textview3.setText(Uri.parse(copy_path.getString("copy_path", "")).getLastPathSegment());
		}
		else {
			if (selector_mode.getString("selector", "").equals("select_folder")) {
				imageview1.setImageResource(R.drawable.folder);
				copy.setText("Select");
			}
			else {
				
			}
		}
		folder = FileUtil.getExternalStorageDir();
		_refreshList();
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
		if (folder.equals(FileUtil.getExternalStorageDir())) {
			finish();
		}
		else {
			Upfolder = folder.substring((int)(0), (int)(folder.lastIndexOf("/")));
			folder = Upfolder;
			_refreshList();
		}
	}
	public void _refreshList () {
		listmap.clear();
		list.clear();
		FileUtil.listDir(folder, list);
		_SortString(list, true);
		n = 0;
		for(int _repeat14 = 0; _repeat14 < (int)(list.size()); _repeat14++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("file", list.get((int)(n)));
				listmap.add(_item);
			}
			
			n++;
		}
		textview2.setText(folder);
		path = folder;
		if (selector_mode.getString("selector", "").equals("select_folder")) {
			textview3.setText(folder);
		}
		listview1.setAdapter(new Listview1Adapter(listmap));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
	}
	
	
	public void _SortString (final ArrayList<String> _listString, final boolean _Ascending) {
		Collections.sort(_listString, String.CASE_INSENSITIVE_ORDER);
		if (!_Ascending) {
			Collections.reverse(_listString);
		}
	}
	
	
	public void _getFileSize (final double _filesize) {
		B = 1024;
		KB = B * B;
		MB = B * (B * B);
		GB = B * (B * (B * B));
		TB = B * (B * (B * (B * B)));
		if (_filesize < B) {
			returnedSize = String.valueOf((long)(_filesize)).concat("B");
		}
		else {
			if (_filesize < KB) {
				returnedSize = new DecimalFormat("0.00").format(_filesize / B).concat("KB");
			}
			else {
				if (_filesize < MB) {
					returnedSize = new DecimalFormat("0.00").format(_filesize / KB).concat("MB");
				}
				else {
					if (_filesize < GB) {
						returnedSize = new DecimalFormat("0.00").format(_filesize / MB).concat("GB");
					}
					else {
						if (_filesize < TB) {
							returnedSize = new DecimalFormat("0.00").format(_filesize / GB).concat("TB");
						}
						else {
							returnedSize = new DecimalFormat("0.00").format(_filesize / TB).concat("PB");
						}
					}
				}
			}
		}
	}
	
	
	public void _setImageFromFile (final ImageView _img, final String _path) {
		
		java.io.File file = new java.io.File(_path);
		Uri imageUri = Uri.fromFile(file);
		
		Glide.with(getApplicationContext ()).load(imageUri).into(_img);
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
	
	
	public void _initSlideActivity () {
		getWindow().getDecorView().setBackgroundResource(android.R.color.transparent);
		ViewConfiguration vc = ViewConfiguration.get(this);
		MIN_DISTANCE = vc.getScaledTouchSlop();
		
		rootView =(ViewGroup)getWindow().findViewById(Window.ID_ANDROID_CONTENT);
		//converts percent to 0-225 range .
		maxAlpha =(int) ((225.0d/100.0d)* MAX_SCRIM_ALPHA); 
		try{
			convertFromTranslucent = Activity.class.getDeclaredMethod("convertFromTranslucent");         convertFromTranslucent.setAccessible(true);
			java.lang.reflect.Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions"); 	getActivityOptions.setAccessible(true);
			options = getActivityOptions.invoke(this);
				Class<?>[] classes = Activity.class.getDeclaredClasses();
			 Class<?> translucentConversionListenerClazz = null;
				for (Class clazz : classes) {
						if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
								translucentConversionListenerClazz = clazz; 
						} 
				} 
				 convertToTranslucent = Activity.class.getDeclaredMethod("convertToTranslucent", translucentConversionListenerClazz, ActivityOptions.class);
				convertToTranslucent.setAccessible(true);
		} catch (Exception e) {
			showMessage(e.toString());
			 }
	}
	// Custom Variables
	//You can change it to color of your choice 
	private static final int SCRIM_COLOR = 0xFF000000;
	//Alpha is not taken into consideration while calculating scrim color  so it dosent matter .
	
	private static final int  SCRIM_R = Color.red(SCRIM_COLOR);
	private static final int SCRIM_G = Color.green(SCRIM_COLOR);
	private static final int  SCRIM_B = Color.blue(SCRIM_COLOR);
	private static final int MAX_SCRIM_ALPHA= 80;
	//in percentage
	private ViewGroup rootView ;
	private boolean enableSwipe= false;
	private boolean lockSwipe = false;
	private float downX;
	private float downY;
	private float MIN_DISTANCE ;
	private int maxAlpha;
	private java.lang.reflect.Method convertFromTranslucent;
	private java.lang.reflect.Method getActivityOptions;
	
	private Object options;
	
	private java.lang.reflect.Method convertToTranslucent;
	// Detect touch Events
	 @Override public boolean dispatchTouchEvent(MotionEvent event) { 
		switch(event.getAction()) { 
			case MotionEvent.ACTION_DOWN: 
			downX = event.getRawX();
			downY =event.getRawY();
			enableSwipe = false;
			lockSwipe = false;
			//convert activity to transparent
			try {
					convertToTranslucent.invoke(this, null, options); 
			} catch (Throwable t) {
			}
			break; 
			case MotionEvent.ACTION_MOVE: 
			if (!lockSwipe){
				if(enableSwipe){
					float translation = event.getRawX() -downX - MIN_DISTANCE;
					if (translation >= rootView.getWidth() || translation<= 0){
						rootView.setTranslationX(0);
					}else{
						rootView.setTranslationX(translation);
						//calculate distance scrolled in percentage
						int distanceInPercentage =(int)( ((double)translation/(double)rootView.getWidth())*100);
						
						//calculate alpha from distance in range 0 - maxAlpha
						
						int alpha =(int) ( ((double)maxAlpha/100.0d)*distanceInPercentage);
						
						//alpha will be greater when it is scrolled more this we do not need this but we need the inverse of it so subtract it from maxAlpha
						alpha = maxAlpha - alpha;
						
						int scrimColor = Color.argb(alpha,SCRIM_R,SCRIM_G,SCRIM_B);
						
						getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(scrimColor));
					}
				}else{
					if(Math.abs(event.getRawY() - downY) >= MIN_DISTANCE){
						enableSwipe = false;
						lockSwipe = true;
					}else{
						enableSwipe = event.getRawX() -downX >= MIN_DISTANCE;
					}
				}
			}
			break; 
			case MotionEvent.ACTION_UP: 
			if(rootView.getTranslationX() > rootView.getWidth() / 5){
				rootView.animate() 
				.translationX(rootView.getWidth())
				.setListener(
				new AnimatorListenerAdapter() { 
							@Override public void onAnimationEnd(Animator animation) { 
						
							super.onAnimationEnd(animation);
						finish();
						overridePendingTransition(0, 0);
						
					} });
			}else{
				rootView.animate() 
				.translationX(0)
				.setListener(
				new AnimatorListenerAdapter() { 
							@Override public void onAnimationEnd(Animator animation) { 
						super.onAnimationEnd(animation);
						// convert activity back to normal
						try {
							 convertFromTranslucent.invoke(this);
							        } catch (Throwable t) {}
					} });
				enableSwipe =false;
				lockSwipe = false;
			}
			break; 
			default:
			enableSwipe =false;
			lockSwipe = false;
			break; 
		}
		if (enableSwipe){
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}
	
	{
	}
	
	
	public void _Rename_Dialog () {
		final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(CopyFileActivity.this);
		
		View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.button_sheet ,null );
		bottomSheetDialog.setContentView(bottomSheetView);
		
		bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
		TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
		
		TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
		
		TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
		
		ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
		
		LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
		
		LinearLayout e1 = (LinearLayout) bottomSheetView.findViewById(R.id.e1);
		
		final EditText kode = new EditText(CopyFileActivity.this);
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
		t1.setText("Copy");
		kode.setText(Uri.parse(copy_path.getString("copy_path", "")).getLastPathSegment());
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
				if (!path.equals("")) {
					if (path.substring((int)(path.length() - 1), (int)(path.length())).equals("/")) {
						FileUtil.copyFile(copy_path.getString("copy_path", ""), path.concat(kode.getText().toString()));
						_refreshList();
					}
					else {
						FileUtil.copyFile(copy_path.getString("copy_path", ""), path.concat("/".concat(kode.getText().toString())));
						_refreshList();
					}
				}
				bottomSheetDialog.dismiss();
			}
		});
		bottomSheetDialog.setCancelable(true);
		bottomSheetDialog.show();
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
				_view = _inflater.inflate(R.layout.file_item, null);
			}
			
			final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			
			textview1.setText(Uri.parse(listmap.get((int)_position).get("file").toString()).getLastPathSegment());
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
			if (FileUtil.isDirectory(listmap.get((int)_position).get("file").toString())) {
				textview2.setVisibility(View.GONE);
				imageview1.setImageResource(R.drawable.folder);
			}
			else {
				textview2.setVisibility(View.VISIBLE);
				_getFileSize(FileUtil.getFileLength(listmap.get((int)_position).get("file").toString()));
				textview2.setText(returnedSize);
				if (listmap.get((int)_position).get("file").toString().endsWith(".txt")) {
					imageview1.setImageResource(R.drawable.text_file);
				}
				else {
					if (listmap.get((int)_position).get("file").toString().endsWith(".png") || (listmap.get((int)_position).get("file").toString().endsWith(".jpg") || (listmap.get((int)_position).get("file").toString().endsWith(".jepg") || listmap.get((int)_position).get("file").toString().endsWith(".gif")))) {
						_setImageFromFile(imageview1, listmap.get((int)_position).get("file").toString());
					}
					else {
						imageview1.setImageResource(R.drawable.file);
					}
				}
			}
			if (dark.getString("dark", "").equals("true")) {
				linear1.setBackgroundColor(0xFF212121);
				textview1.setTextColor(0xFFFFFFFF);
				textview2.setTextColor(0xFFFFFFFF);
			}
			else {
				linear1.setBackgroundColor(0xFFFFFFFF);
				textview1.setTextColor(0xFF000000);
				textview2.setTextColor(0xFF9E9E9E);
			}
			
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