package smartvoice.my.amirali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.widget.LinearLayout;
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
import java.util.HashMap;
import java.util.ArrayList;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ScrollView;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.graphics.Typeface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zolad.zoominimageview.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class CreateActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private FloatingActionButton _fab;
	private DrawerLayout _drawer;
	private String folder = "";
	private double n = 0;
	private String UPfolder = "";
	private String path = "";
	private String path2 = "";
	private String get_text = "";
	private String share = "";
	private String package_name = "";
	private String ver = "";
	private String path_code = "";
	private HashMap<String, Object> backup_items = new HashMap<>();
	
	private ArrayList<String> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> list_Data = new ArrayList<>();
	
	private LinearLayout linear3;
	private SwipeRefreshLayout swiperefreshlayout1;
	private LinearLayout linear4;
	private TextView textview3;
	private ImageView imageview1;
	private TextView textview2;
	private ListView listview1;
	private ScrollView _drawer_vscroll1;
	private LinearLayout _drawer_linear1;
	private LinearLayout _drawer_linear3;
	private LinearLayout _drawer_linear4;
	private LinearLayout _drawer_linear5;
	private LinearLayout _drawer_linear6;
	private LinearLayout _drawer_linear9;
	private LinearLayout _drawer_linear10;
	private ImageView _drawer_imageview1;
	private LinearLayout _drawer_linear7;
	private TextView _drawer_textview1;
	private TextView _drawer_textview5;
	private ImageView _drawer_imageview2;
	private TextView _drawer_textview2;
	private ImageView _drawer_imageview3;
	private TextView _drawer_textview3;
	private ImageView _drawer_imageview4;
	private TextView _drawer_textview4;
	private ImageView _drawer_imageview5;
	private TextView _drawer_textview6;
	private ImageView _drawer_imageview6;
	private TextView _drawer_textview7;
	
	private AlertDialog.Builder dialog;
	private Intent i = new Intent();
	private SharedPreferences save;
	private Intent next = new Intent();
	private SharedPreferences edit;
	private SharedPreferences welcome;
	private Intent intent = new Intent();
	private SharedPreferences fabTarget;
	private TimerTask t;
	private TimerTask change;
	private SharedPreferences dark;
	private TimerTask check;
	private TimerTask reload;
	private TimerTask time;
	private Intent settings = new Intent();
	private Intent in = new Intent();
	private Intent help = new Intent();
	private Calendar calendar = Calendar.getInstance();
	private RequestNetwork update_app;
	private RequestNetwork.RequestListener _update_app_request_listener;
	private Intent open = new Intent();
	private TimerTask timer;
	private Intent nw = new Intent();
	private Intent editor = new Intent();
	private Intent backup = new Intent();
	private SharedPreferences Backup_Data;
	private Calendar backup_time = Calendar.getInstance();
	private Intent copy = new Intent();
	private SharedPreferences copy_path;
	private SharedPreferences selector_mode;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.create);
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
		
		_app_bar = (AppBarLayout) findViewById(R.id._app_bar);
		_coordinator = (CoordinatorLayout) findViewById(R.id._coordinator);
		_toolbar = (Toolbar) findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		_drawer = (DrawerLayout) findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(CreateActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);
		
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		swiperefreshlayout1 = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout1);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview2 = (TextView) findViewById(R.id.textview2);
		listview1 = (ListView) findViewById(R.id.listview1);
		_drawer_vscroll1 = (ScrollView) _nav_view.findViewById(R.id.vscroll1);
		_drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
		_drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
		_drawer_linear4 = (LinearLayout) _nav_view.findViewById(R.id.linear4);
		_drawer_linear5 = (LinearLayout) _nav_view.findViewById(R.id.linear5);
		_drawer_linear6 = (LinearLayout) _nav_view.findViewById(R.id.linear6);
		_drawer_linear9 = (LinearLayout) _nav_view.findViewById(R.id.linear9);
		_drawer_linear10 = (LinearLayout) _nav_view.findViewById(R.id.linear10);
		_drawer_imageview1 = (ImageView) _nav_view.findViewById(R.id.imageview1);
		_drawer_linear7 = (LinearLayout) _nav_view.findViewById(R.id.linear7);
		_drawer_textview1 = (TextView) _nav_view.findViewById(R.id.textview1);
		_drawer_textview5 = (TextView) _nav_view.findViewById(R.id.textview5);
		_drawer_imageview2 = (ImageView) _nav_view.findViewById(R.id.imageview2);
		_drawer_textview2 = (TextView) _nav_view.findViewById(R.id.textview2);
		_drawer_imageview3 = (ImageView) _nav_view.findViewById(R.id.imageview3);
		_drawer_textview3 = (TextView) _nav_view.findViewById(R.id.textview3);
		_drawer_imageview4 = (ImageView) _nav_view.findViewById(R.id.imageview4);
		_drawer_textview4 = (TextView) _nav_view.findViewById(R.id.textview4);
		_drawer_imageview5 = (ImageView) _nav_view.findViewById(R.id.imageview5);
		_drawer_textview6 = (TextView) _nav_view.findViewById(R.id.textview6);
		_drawer_imageview6 = (ImageView) _nav_view.findViewById(R.id.imageview6);
		_drawer_textview7 = (TextView) _nav_view.findViewById(R.id.textview7);
		dialog = new AlertDialog.Builder(this);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		edit = getSharedPreferences("edit", Activity.MODE_PRIVATE);
		welcome = getSharedPreferences("welcome", Activity.MODE_PRIVATE);
		fabTarget = getSharedPreferences("fabTarget", Activity.MODE_PRIVATE);
		dark = getSharedPreferences("dark", Activity.MODE_PRIVATE);
		update_app = new RequestNetwork(this);
		Backup_Data = getSharedPreferences("Backup_Data", Activity.MODE_PRIVATE);
		copy_path = getSharedPreferences("copy_path", Activity.MODE_PRIVATE);
		selector_mode = getSharedPreferences("selector_mode", Activity.MODE_PRIVATE);
		
		swiperefreshlayout1.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override 
			public void onRefresh() {
				folder = FileUtil.getExternalStorageDir().concat("/smart voice");
				_refreshList();
				calendar = Calendar.getInstance();
				textview3.setText(new SimpleDateFormat("cccc MMMM yyyy").format(calendar.getTime()));
				swiperefreshlayout1.setRefreshing(false);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_drawer.openDrawer(GravityCompat.START);
			}
		});
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				path = listmap.get((int)_position).get("file").toString();
				if (FileUtil.isExistFile(path)) {
					if (path.endsWith(".java") || (path.endsWith(".xml") || (path.endsWith(".php") || (path.endsWith(".html") || (path.endsWith(".css") || (path.endsWith(".json") || (path.endsWith(".js") || (path.endsWith(".htm") || path.endsWith(".txt"))))))))) {
						save.edit().putString("save", listmap.get((int)_position).get("file").toString()).commit();
						i.setAction(Intent.ACTION_VIEW);
						i.setClass(getApplicationContext(), VoiceActivity.class);
						startActivity(i);
					}
				}
			}
		});
		
		listview1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				path_code = listmap.get((int)_position).get("file").toString();
				if (FileUtil.isExistFile(path_code)) {
					final AlertDialog dialog1 = new AlertDialog.Builder(CreateActivity.this).create();
					View inflate = getLayoutInflater().inflate(R.layout.options,null); 
					dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
					dialog1.setView(inflate);
					TextView title = (TextView) inflate.findViewById(R.id.title);
					
					TextView text1 = (TextView) inflate.findViewById(R.id.text1);
					
					TextView text2 = (TextView) inflate.findViewById(R.id.text2);
					
					TextView text3 = (TextView) inflate.findViewById(R.id.text3);
					
					TextView text4 = (TextView) inflate.findViewById(R.id.text4);
					
					TextView text5 = (TextView) inflate.findViewById(R.id.text5);
					
					TextView text6 = (TextView) inflate.findViewById(R.id.text6);
					
					LinearLayout bt1 = (LinearLayout) inflate.findViewById(R.id.bt1);
					
					LinearLayout bt2 = (LinearLayout) inflate.findViewById(R.id.bt2);
					
					LinearLayout bt3 = (LinearLayout) inflate.findViewById(R.id.bt3);
					
					LinearLayout bt4 = (LinearLayout) inflate.findViewById(R.id.bt4);
					
					LinearLayout bt5 = (LinearLayout) inflate.findViewById(R.id.bt5);
					
					LinearLayout bt6 = (LinearLayout) inflate.findViewById(R.id.bt6);
					
					ImageView img1 = (ImageView) inflate.findViewById(R.id.img1);
					
					ImageView img2 = (ImageView) inflate.findViewById(R.id.img2);
					
					ImageView img3 = (ImageView) inflate.findViewById(R.id.img3);
					
					ImageView img4 = (ImageView) inflate.findViewById(R.id.img4);
					
					ImageView img5 = (ImageView) inflate.findViewById(R.id.img5);
					
					ImageView img6 = (ImageView) inflate.findViewById(R.id.img6);
					
					LinearLayout bg = (LinearLayout) inflate.findViewById(R.id.bg);
					title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
					text1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
					text2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
					text3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
					if (dark.getString("dark", "").equals("true")) {
						_rippleRoundStroke(bg, "#212121", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(bt1, "#212121", "#BDBDBD", 17, 2, "#424242");
						_rippleRoundStroke(bt2, "#212121", "#BDBDBD", 17, 2, "#424242");
						_rippleRoundStroke(bt3, "#212121", "#BDBDBD", 17, 2, "#424242");
						_rippleRoundStroke(bt4, "#212121", "#BDBDBD", 17, 2, "#424242");
						_rippleRoundStroke(bt5, "#212121", "#BDBDBD", 17, 2, "#424242");
						_rippleRoundStroke(bt6, "#212121", "#BDBDBD", 17, 2, "#424242");
						title.setTextColor(0xFFFFFFFF);
						text1.setTextColor(0xFFFFFFFF);
						text2.setTextColor(0xFFFFFFFF);
						text3.setTextColor(0xFFFFFFFF);
						text4.setTextColor(0xFFFFFFFF);
						text5.setTextColor(0xFFFFFFFF);
						text6.setTextColor(0xFFFFFFFF);
					}
					else {
						_rippleRoundStroke(bg, "#FFFFFF", "#000000", 15, 0, "#000000");
						_rippleRoundStroke(bt1, "#FFFFFF", "#BDBDBD", 17, 2, "#EEEEEE");
						_rippleRoundStroke(bt2, "#FFFFFF", "#BDBDBD", 17, 2, "#EEEEEE");
						_rippleRoundStroke(bt3, "#FFFFFF", "#BDBDBD", 17, 2, "#EEEEEE");
						_rippleRoundStroke(bt4, "#FFFFFF", "#BDBDBD", 17, 2, "#EEEEEE");
						_rippleRoundStroke(bt5, "#FFFFFF", "#BDBDBD", 17, 2, "#EEEEEE");
						_rippleRoundStroke(bt6, "#FFFFFF", "#BDBDBD", 17, 2, "#EEEEEE");
						title.setTextColor(0xFF000000);
						text1.setTextColor(0xFF000000);
						text2.setTextColor(0xFF000000);
						text3.setTextColor(0xFF000000);
						text4.setTextColor(0xFF000000);
						text5.setTextColor(0xFF000000);
						text6.setTextColor(0xFF000000);
					}
					bt1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							if (!FileUtil.readFile(listmap.get((int)_position).get("file").toString()).trim().equals("")) {
								share = FileUtil.readFile(listmap.get((int)_position).get("file").toString());
								Intent i = new Intent(android.content.Intent.ACTION_SEND);i.setType("text/plain"); i.putExtra(android.content.Intent.EXTRA_TEXT, share); startActivity(Intent.createChooser(i,"Share using"));
							}
							else {
								_custom_toast("Sharing failed");
							}
							dialog1.dismiss();
						}
					});
					bt2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							FileUtil.deleteFile(listmap.get((int)_position).get("file").toString());
							_refreshList();
							dialog1.dismiss();
						}
					});
					bt3.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							dialog.setTitle("Rename File");
							LinearLayout mylayout = new LinearLayout(CreateActivity.this);
							
							LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
							
							mylayout.setLayoutParams(params); mylayout.setOrientation(LinearLayout.VERTICAL);
							
							final EditText myedittext = new EditText(CreateActivity.this);
							myedittext.setLayoutParams(new LinearLayout.LayoutParams(android.widget.LinearLayout.LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT));
							 
							mylayout.addView(myedittext);
							dialog.setView(mylayout);
							myedittext.setHintTextColor(0xFF9E9E9E);
							myedittext.setHint("New File Name");
							if (dark.getString("dark", "").equals("true")) {
								myedittext.setTextColor(0xFFFFFFFF);
							}
							else {
								myedittext.setTextColor(0xFF000000);
							}
							myedittext.setText(Uri.parse(listmap.get((int)_position).get("file").toString()).getLastPathSegment());
							dialog.setPositiveButton("Rename", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									if (FileUtil.isExistFile(listmap.get((int)_position).get("file").toString())) {
										_renameTo(FileUtil.getExternalStorageDir().concat("/smart voice"), Uri.parse(listmap.get((int)_position).get("file").toString()).getLastPathSegment(), myedittext.getText().toString());
										_refreshList();
									}
									else {
										_custom_toast("not exist this file :".concat(listmap.get((int)_position).get("file").toString()));
									}
								}
							});
							dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							dialog.create().show();
							dialog1.dismiss();
						}
					});
					bt4.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							if (path_code.endsWith(".java") || (path_code.endsWith(".xml") || (path_code.endsWith(".php") || (path_code.endsWith(".html") || (path_code.endsWith(".css") || (path_code.endsWith(".json") || (path_code.endsWith(".js") || (path_code.endsWith(".htm") || path_code.endsWith(".txt"))))))))) {
								save.edit().putString("save", listmap.get((int)_position).get("file").toString()).commit();
								editor.setClass(getApplicationContext(), CodeEditorActivity.class);
								startActivity(editor);
							}
							else {
								
							}
							dialog1.dismiss();
						}
					});
					bt6.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							if (path_code.endsWith(".java") || (path_code.endsWith(".xml") || (path_code.endsWith(".php") || (path_code.endsWith(".html") || (path_code.endsWith(".css") || (path_code.endsWith(".json") || (path_code.endsWith(".js") || (path_code.endsWith(".htm") || path_code.endsWith(".txt"))))))))) {
								backup_time = Calendar.getInstance();
								backup_items = new HashMap<>();
								backup_items.put("title", Uri.parse(listmap.get((int)_position).get("file").toString()).getLastPathSegment());
								backup_items.put("text", FileUtil.readFile(listmap.get((int)_position).get("file").toString()));
								backup_items.put("time", new SimpleDateFormat("dd MMMM yyyy hh:mm a").format(backup_time.getTime()));
								if (!Backup_Data.getString("Backup_Data", "").equals("")) {
									list_Data = new Gson().fromJson(Backup_Data.getString("Backup_Data", ""), new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
								}
								list_Data.add((int)0, backup_items);
								Backup_Data.edit().putString("Backup_Data", new Gson().toJson(list_Data)).commit();
								_custom_toast("Done");
								backup_items.clear();
							}
							else {
								_custom_toast("Not Support Your Format");
							}
							dialog1.dismiss();
						}
					});
					bt5.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
							copy_path.edit().putString("copy_path", listmap.get((int)_position).get("file").toString()).commit();
							selector_mode.edit().putString("selector", "copy").commit();
							copy.setAction(Intent.ACTION_VIEW);
							copy.setClass(getApplicationContext(), CopyFileActivity.class);
							startActivity(copy);
							dialog1.dismiss();
						}
					});
					dialog1.setCancelable(true);
					dialog1.show();
				}
				return true;
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (_fab.getRotation()==0) {
					_showCustom(true);
				} else {
					_showCustom(false);
				};
			}
		});
		
		_update_app_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (_isJson(_response)) {
					map = new Gson().fromJson(_response, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
					package_name = "smartvoice.my.amirali";
					try {
						android.content.pm.PackageInfo pinfo = getPackageManager().getPackageInfo(package_name, android.content.pm.PackageManager.GET_ACTIVITIES);
						ver = pinfo.versionName; }
					catch (Exception e){ showMessage(e.toString()); }
					if (!map.get((int)0).get("Version").toString().equals(ver)) {
						if (map.get((int)0).get("Visibility").toString().equals("VISIBLE")) {
							_UpdateDialog();
						}
					}
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_drawer_linear4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				settings.setAction(Intent.ACTION_VIEW);
				settings.setClass(getApplicationContext(), SettingsActivity.class);
				startActivity(settings);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_linear5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				in.setAction(Intent.ACTION_VIEW);
				in.setClass(getApplicationContext(), CreditsActivity.class);
				startActivity(in);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_linear6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				help.setAction(Intent.ACTION_VIEW);
				help.setClass(getApplicationContext(), HelpActivity.class);
				startActivity(help);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_linear9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				nw.setAction(Intent.ACTION_VIEW);
				nw.setClass(getApplicationContext(), NewsVnActivity.class);
				startActivity(nw);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
		
		_drawer_linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				backup.setClass(getApplicationContext(), BackupActivity.class);
				startActivity(backup);
				_drawer.closeDrawer(GravityCompat.START);
			}
		});
	}
	
	private void initializeLogic() {
		try{ 
			getSupportActionBar().hide(); 
		} catch (Exception e){}
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		_drawer_textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		_drawer_textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		_drawer_textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		_drawer_textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		_drawer_textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		_drawer_textview7.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		if (welcome.getString("welcome", "").equals("")) {
			welcome.edit().putString("welcome", "true").commit();
		}
		else {
			
		}
		if (welcome.getString("welcome", "").equals("true")) {
			intent.setAction(Intent.ACTION_VIEW);
			intent.setClass(getApplicationContext(), WelcomeActivity.class);
			startActivity(intent);
		}
		else {
			
		}
		t = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (fabTarget.getString("fabTarget", "").equals("true")) {
							_ARGHOZALITapTarget(_fab, " Create a new note", "Click this to create a new note", "#2196F3");
							fabTarget.edit().putString("fabTarget", "false").commit();
						}
						else {
							
						}
					}
				});
			}
		};
		_timer.scheduleAtFixedRate(t, (int)(0), (int)(1500));
		if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice"))) {
			
		}
		else {
			FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/smart voice"));
		}
		if (Double.parseDouble(Build.VERSION.SDK) > 27) {
			int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
			if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
				swiperefreshlayout1.setBackgroundColor(0xFF212121);
				Window window = this.getWindow();window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); window.setNavigationBarColor(Color.parseColor("#212121"));
				View decor = getWindow().getDecorView();
				decor.setSystemUiVisibility(0);
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
					Window w =CreateActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
				}
				dark.edit().putString("dark", "true").commit();
				_dark_drawer();
				linear3.setBackgroundColor(0xFF212121);
				imageview1.setImageResource(R.drawable.ic_dehaze_white);
				textview2.setTextColor(0xFFFFFFFF);
				_card_style(linear4, 10, 20, "#000000");
				dialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
			} else {
				swiperefreshlayout1.setBackgroundColor(0xFFFFFFFF);
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
					Window w =CreateActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFFFFFFFF);
				}
				dark.edit().putString("dark", "false").commit();
				_light_drawer();
				imageview1.setImageResource(R.drawable.ic_dehaze_black);
				linear3.setBackgroundColor(0xFFFFFFFF);
				textview2.setTextColor(0xFF000000);
				_card_style(linear4, 10, 20, "#FFFFFF");
				dialog = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
			};
		}
		else {
			_card_style(linear4, 10, 20, "#FFFFFF");
			dark.edit().putString("dark", "false").commit();
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(0xFFFFFFFF);
		}
		folder = FileUtil.getExternalStorageDir().concat("/smart voice");
		_refreshList();
		calendar = Calendar.getInstance();
		textview3.setText(new SimpleDateFormat("cccc MMMM yyyy").format(calendar.getTime()));
		_custom_fabs();
		_fabColor("#2196F3");
		_fab.setRippleColor(Color.parseColor("#BDBDBD"));
		_fab.setSize(FloatingActionButton.SIZE_AUTO);
		listview1.setSelector(android.R.color.transparent);
		// hide or show fab
		listview1.setOnScrollListener(new ListView.OnScrollListener() {
			
			private int mLastFirstVisibleItem;
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				
				if(mLastFirstVisibleItem<firstVisibleItem) {
					if (_fab.getRotation()==0) {
							_fab.hide();
					}
				}
				
				if(mLastFirstVisibleItem>firstVisibleItem) {
					if (_fab.getRotation()==0) {
							_fab.show();
					}
				}
				
				mLastFirstVisibleItem = firstVisibleItem;
				
			}
			
		});
		update_app.startRequestNetwork(RequestNetworkController.GET, "https://raw.githubusercontent.com/AmirAli-AZ/MyGitHubServer/main/index.html", "a", _update_app_request_listener);
		ViewConfiguration vc = ViewConfiguration.get(CreateActivity.this);
		 mMinimunDistance = vc.getScaledTouchSlop();
		listview1.setVerticalScrollBarEnabled(false);
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
		if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice"))) {
			if (folder.equals(FileUtil.getExternalStorageDir().concat("/smart voice"))) {
				finish();
			}
			else {
				UPfolder = folder.substring((int)(0), (int)(folder.lastIndexOf("/")));
				folder = UPfolder;
				_refreshList();
			}
		}
		else {
			FileUtil.makeDir(FileUtil.getExternalStorageDir().concat("/smart voice"));
			_refreshList();
		}
	}
	public void _refreshList () {
		if (FileUtil.isExistFile(folder)) {
			list.clear();
			listmap.clear();
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
					
				}
				n++;
			}
			listview1.setAdapter(new Listview1Adapter(listmap));
			((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		}
	}
	
	
	public void _SortString (final ArrayList<String> _listString, final boolean _Ascending) {
		Collections.sort(_listString, String.CASE_INSENSITIVE_ORDER);
		if (!_Ascending) {
			Collections.reverse(_listString);
		}
	}
	
	
	public void _renameTo (final String _directory, final String _file1, final String _file2) {
		java.io.File directory = new
		java.io.File(_directory);
		
		java.io.File oldfile = new
		java.io.File(directory, _file1);
		
		java.io.File newfile = new
		java.io.File(directory, _file2);
		
		oldfile.renameTo(newfile);
	}
	
	
	public void _ARGHOZALITapTarget (final View _view, final String _title, final String _msg, final String _bgcolor) {
		TapTargetView.showFor(CreateActivity.this,
		TapTarget.forView(_view, _title, _msg)
		.outerCircleColorInt(Color.parseColor(_bgcolor))
		.outerCircleAlpha(0.96f)
		.targetCircleColorInt(Color.parseColor("#FFFFFF"))
		.titleTextSize(25)
		.titleTextColorInt(Color.parseColor("#FFFFFF"))
		.descriptionTextSize(18)
		.descriptionTextColor(android.R.color.white)
		.textColorInt(Color.parseColor("#FFFFFF"))
		.textTypeface(Typeface.SANS_SERIF)
		.dimColor(android.R.color.black)
		.drawShadow(true)
		.cancelable(true)
		.tintTarget(true)
		.transparentTarget(true)
		//.icon(Drawable)
		.targetRadius(60),
		
		//LISTENER//
		
		new TapTargetView.Listener() {
			@Override
			public void onTargetClick(TapTargetView view) {
				//ON CLICKED//
				
				super.onTargetClick(view);
			}
		});
	}
	static class UiUtil {
		    UiUtil() {
			    }
		    static int dp(Context context, int val) {
			        return (int) TypedValue.applyDimension(
			                TypedValue.COMPLEX_UNIT_DIP, val, context.getResources().getDisplayMetrics());
			    }
		    static int sp(Context context, int val) {
			        return (int) TypedValue.applyDimension(
			                TypedValue.COMPLEX_UNIT_SP, val, context.getResources().getDisplayMetrics());
			    }
		    static int themeIntAttr(Context context, String attr) {
			        final android.content.res.Resources.Theme theme = context.getTheme();
			        if (theme == null) {
				            return -1;
				        }
			        final TypedValue value = new TypedValue();
			        final int id = context.getResources().getIdentifier(attr, "attr", context.getPackageName());
			
			        if (id == 0) {
				            // Not found
				            return -1;
				        }
			        theme.resolveAttribute(id, value, true);
			        return value.data;
			    }
		    static int setAlpha(int argb, float alpha) {
			        if (alpha > 1.0f) {
				            alpha = 1.0f;
				        } else if (alpha <= 0.0f) {
				            alpha = 0.0f;
				        }
			        return ((int) ((argb >>> 24) * alpha) << 24) | (argb & 0x00FFFFFF);
			    }
	}
	static class FloatValueAnimatorBuilder {
		
		    private final ValueAnimator animator;
		
		    private EndListener endListener;
		
		    interface UpdateListener {
			        void onUpdate(float lerpTime);
			    }
		    interface EndListener {
			        void onEnd();
			    }
		    protected FloatValueAnimatorBuilder() {
			        this(false);
			    }
		    FloatValueAnimatorBuilder(boolean reverse) {
			        if (reverse) {
				            this.animator = ValueAnimator.ofFloat(1.0f, 0.0f);
				        } else {
				            this.animator = ValueAnimator.ofFloat(0.0f, 1.0f);
				        }
			    }
		    public FloatValueAnimatorBuilder delayBy(long millis) {
			        animator.setStartDelay(millis);
			        return this;
			    }
		    public FloatValueAnimatorBuilder duration(long millis) {
			        animator.setDuration(millis);
			        return this;
			    }
		    public FloatValueAnimatorBuilder interpolator(TimeInterpolator lerper) {
			        animator.setInterpolator(lerper);
			        return this;
			    }
		    public FloatValueAnimatorBuilder repeat(int times) {
			        animator.setRepeatCount(times);
			        return this;
			    }
		    public FloatValueAnimatorBuilder onUpdate(final UpdateListener listener) {
			        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
				            @Override
				            public void onAnimationUpdate(ValueAnimator animation) {
					                listener.onUpdate((float) animation.getAnimatedValue());
					            }
				        });
			        return this;
			    }
		    public FloatValueAnimatorBuilder onEnd(final EndListener listener) {
			        this.endListener = listener;
			        return this;
			    }
		    public ValueAnimator build() {
			        if (endListener != null) {
				            animator.addListener(new AnimatorListenerAdapter() {
					                @Override
					                public void onAnimationEnd(Animator animation) {
						                    endListener.onEnd();
						                }
					            });
				        }
			        return animator;
			    }
	}
	
	static class ReflectUtil {
		    ReflectUtil() {
			    }
		    static Object getPrivateField(Object source, String fieldName)
		            throws NoSuchFieldException, IllegalAccessException {
			        final java.lang.reflect.Field objectField = source.getClass().getDeclaredField(fieldName);
			        objectField.setAccessible(true);
			        return objectField.get(source);
			    }
	}
	static class TapTarget extends Activity {
		    final CharSequence title;
		    final CharSequence description;
		    float outerCircleAlpha = 0.96f;
		    int targetRadius = 44;
		    Rect bounds;
		    android.graphics.drawable.Drawable icon;
		    Typeface titleTypeface;
		    Typeface descriptionTypeface;
		
		
		    private int outerCircleColorRes = -1;
		    private int targetCircleColorRes = -1;
		    private int dimColorRes = -1;
		    private int titleTextColorRes = -1;
		    private int descriptionTextColorRes = -1;
		
		    private Integer outerCircleColor = null;
		    private Integer targetCircleColor = null;
		    private Integer dimColor = null;
		    private Integer titleTextColor = null;
		    private Integer descriptionTextColor = null;
		
		    private int titleTextDimen = -1;
		    private int descriptionTextDimen = -1;
		    private int titleTextSize = 20;
		    private int descriptionTextSize = 18;
		    int id = -1;
		    boolean drawShadow = false;
		    boolean cancelable = true;
		    boolean tintTarget = true;
		    boolean transparentTarget = false;
		    float descriptionTextAlpha = 0.54f;
		public static TapTarget forView(View view, CharSequence title) {
			        return forView(view, title, null);
			    }
		    public static TapTarget forView(View view, CharSequence title, CharSequence description) {
			        return new ViewTapTarget(view, title, description);
			    }
		    public static TapTarget forBounds(Rect bounds, CharSequence title) {
			        return forBounds(bounds, title, null);
			    }
		    public static TapTarget forBounds(Rect bounds, CharSequence title,  CharSequence description) {
			        return new TapTarget(bounds, title, description);
			    }
		    protected TapTarget(Rect bounds, CharSequence title,  CharSequence description) {
			        this(title, description);
			        if (bounds == null) {
				            throw new IllegalArgumentException("Cannot pass null bounds or title");
				        }
			        this.bounds = bounds;
			    }
		    protected TapTarget(CharSequence title,  CharSequence description) {
			        if (title == null) {
				            throw new IllegalArgumentException("Cannot pass null title");
				        }
			        this.title = title;
			        this.description = description;
			    }
		    public TapTarget transparentTarget(boolean transparent) {
			        this.transparentTarget = transparent;
			        return this;
			    }
		    public TapTarget outerCircleColor( int color) {
			        this.outerCircleColorRes = color;
			        return this;
			    }
		    public TapTarget outerCircleColorInt( int color) {
			        this.outerCircleColor = color;
			        return this;
			    }
		    public TapTarget outerCircleAlpha(float alpha) {
			        if (alpha < 0.0f || alpha > 1.0f) {
				            throw new IllegalArgumentException("Given an invalid alpha value: " + alpha);
				        }
			        this.outerCircleAlpha = alpha;
			        return this;
			    }
		    public TapTarget targetCircleColor( int color) {
			        this.targetCircleColorRes = color;
			        return this;
			    }
		    public TapTarget targetCircleColorInt( int color) {
			        this.targetCircleColor = color;
			        return this;
			    }
		    public TapTarget textColor( int color) {
			        this.titleTextColorRes = color;
			        this.descriptionTextColorRes = color;
			        return this;
			    }
		    public TapTarget textColorInt( int color) {
			        this.titleTextColor = color;
			        this.descriptionTextColor = color;
			        return this;
			    }
		    public TapTarget titleTextColor( int color) {
			        this.titleTextColorRes = color;
			        return this;
			    }
		    public TapTarget titleTextColorInt( int color) {
			        this.titleTextColor = color;
			        return this;
			    }
		    public TapTarget descriptionTextColor( int color) {
			        this.descriptionTextColorRes = color;
			        return this;
			    }
		    public TapTarget descriptionTextColorInt( int color) {
			        this.descriptionTextColor = color;
			        return this;
			    }
		    public TapTarget textTypeface(Typeface typeface) {
			        if (typeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        titleTypeface = typeface;
			        descriptionTypeface = typeface;
			        return this;
			    }
		    public TapTarget titleTypeface(Typeface titleTypeface) {
			        if (titleTypeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        this.titleTypeface = titleTypeface;
			        return this;
			    }
		    public TapTarget descriptionTypeface(Typeface descriptionTypeface) {
			        if (descriptionTypeface == null) throw new IllegalArgumentException("Cannot use a null typeface");
			        this.descriptionTypeface = descriptionTypeface;
			        return this;
			    }
		    public TapTarget titleTextSize(int sp) {
			        if (sp < 0) throw new IllegalArgumentException("Given negative text size");
			        this.titleTextSize = sp;
			        return this;
			    }
		    public TapTarget descriptionTextSize(int sp) {
			        if (sp < 0) throw new IllegalArgumentException("Given negative text size");
			        this.descriptionTextSize = sp;
			        return this;
			    }
		    public TapTarget titleTextDimen( int dimen) {
			        this.titleTextDimen = dimen;
			        return this;
			    }
		    public TapTarget descriptionTextAlpha(float descriptionTextAlpha) {
			        if (descriptionTextAlpha < 0 || descriptionTextAlpha > 1f) {
				            throw new IllegalArgumentException("Given an invalid alpha value: " + descriptionTextAlpha);
				        }
			        this.descriptionTextAlpha = descriptionTextAlpha;
			        return this;
			    }
		    public TapTarget descriptionTextDimen( int dimen) {
			        this.descriptionTextDimen = dimen;
			        return this;
			    }
		    public TapTarget dimColor( int color) {
			        this.dimColorRes = color;
			        return this;
			    }
		    public TapTarget dimColorInt( int color) {
			        this.dimColor = color;
			        return this;
			    }
		    public TapTarget drawShadow(boolean draw) {
			        this.drawShadow = draw;
			        return this;
			    }
		    public TapTarget cancelable(boolean status) {
			        this.cancelable = status;
			        return this;
			    }
		    public TapTarget tintTarget(boolean tint) {
			        this.tintTarget = tint;
			        return this;
			    }
		    public TapTarget icon(android.graphics.drawable.Drawable icon) {
			        return icon(icon, false);
			    }
		    public TapTarget icon(android.graphics.drawable.Drawable icon, boolean hasSetBounds) {
			        if (icon == null) throw new IllegalArgumentException("Cannot use null drawable");
			        this.icon = icon;
			        if (!hasSetBounds) {
				            this.icon.setBounds(new Rect(0, 0, this.icon.getIntrinsicWidth(), this.icon.getIntrinsicHeight()));
				        }
			        return this;
			    }
		    public TapTarget id(int id) {
			        this.id = id;
			        return this;
			    }
		    public TapTarget targetRadius(int targetRadius) {
			        this.targetRadius = targetRadius;
			        return this;
			    }
		    public int id() {
			        return id;
			    }
		    public void onReady(Runnable runnable) {
			        runnable.run();
			    }
		    public Rect bounds() {
			        if (bounds == null) {
				            throw new IllegalStateException("Requesting bounds that are not set! Make sure your target is ready");
				        }
			        return bounds;
			    }
		    Integer outerCircleColorInt(Context context) {
			        return colorResOrInt(context, outerCircleColor, outerCircleColorRes);
			    }
		    Integer targetCircleColorInt(Context context) {
			        return colorResOrInt(context, targetCircleColor, targetCircleColorRes);
			    }
		    Integer dimColorInt(Context context) {
			        return colorResOrInt(context, dimColor, dimColorRes);
			    }
		    Integer titleTextColorInt(Context context) {
			        return colorResOrInt(context, titleTextColor, titleTextColorRes);
			    }
		
		    Integer descriptionTextColorInt(Context context) {
			        return colorResOrInt(context, descriptionTextColor, descriptionTextColorRes);
			    }
		    int titleTextSizePx(Context context) {
			        return dimenOrSize(context, titleTextSize, titleTextDimen);
			    }
		    int descriptionTextSizePx(Context context) {
			        return dimenOrSize(context, descriptionTextSize, descriptionTextDimen);
			    }
		
		    private Integer colorResOrInt(Context context, Integer value,  int resource) {
			        if (resource != -1) {
				            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
					                return context.getColor(resource);
					            }
				        }
			        return value;
			    }
		    private int dimenOrSize(Context context, int size,  int dimen) {
			        if (dimen != -1) {
				            return context.getResources().getDimensionPixelSize(dimen);
				        }
			        return UiUtil.sp(context, size);
			    }
	}
	static class TapTargetView extends View {
		    private boolean isDismissed = false;
		    private boolean isDismissing = false;
		    private boolean isInteractable = true;
		
		    final int TARGET_PADDING;
		    final int TARGET_RADIUS;
		    final int TARGET_PULSE_RADIUS;
		    final int TEXT_PADDING;
		    final int TEXT_SPACING;
		    final int TEXT_MAX_WIDTH;
		    final int TEXT_POSITIONING_BIAS;
		    final int CIRCLE_PADDING;
		    final int GUTTER_DIM;
		    final int SHADOW_DIM;
		    final int SHADOW_JITTER_DIM;
		
		
		    final ViewGroup boundingParent;
		    final ViewManager parent;
		    final TapTarget target;
		    final Rect targetBounds;
		
		    final TextPaint titlePaint;
		    final TextPaint descriptionPaint;
		    final Paint outerCirclePaint;
		    final Paint outerCircleShadowPaint;
		    final Paint targetCirclePaint;
		    final Paint targetCirclePulsePaint;
		
		    CharSequence title;
		
		    StaticLayout titleLayout;
		
		    CharSequence description;
		
		    StaticLayout descriptionLayout;
		    boolean isDark;
		    boolean debug;
		    boolean shouldTintTarget;
		    boolean shouldDrawShadow;
		    boolean cancelable;
		    boolean visible;
		
		    // Debug related variables
		
		    SpannableStringBuilder debugStringBuilder;
		
		    DynamicLayout debugLayout;
		
		    TextPaint debugTextPaint;
		
		    Paint debugPaint;
		
		    // Drawing properties
		    Rect drawingBounds;
		    Rect textBounds;
		
		    Path outerCirclePath;
		    float outerCircleRadius;
		    int calculatedOuterCircleRadius;
		    int[] outerCircleCenter;
		    int outerCircleAlpha;
		
		    float targetCirclePulseRadius;
		    int targetCirclePulseAlpha;
		
		    float targetCircleRadius;
		    int targetCircleAlpha;
		
		    int textAlpha;
		    int dimColor;
		
		    float lastTouchX;
		    float lastTouchY;
		
		    int topBoundary;
		    int bottomBoundary;
		
		    Bitmap tintedTarget;
		
		    Listener listener;
		
		
		    ViewOutlineProvider outlineProvider;
		
		    public static TapTargetView showFor(Activity activity, TapTarget target) {
			        return showFor(activity, target, null);
			    }
		
		    public static TapTargetView showFor(Activity activity, TapTarget target, Listener listener) {
			        if (activity == null) throw new IllegalArgumentException("Activity is null");
			
			        final ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
			        final ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
			                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			        final ViewGroup content = (ViewGroup) decor.findViewById(android.R.id.content);
			        final TapTargetView tapTargetView = new TapTargetView(activity, decor, content, target, listener);
			        decor.addView(tapTargetView, layoutParams);
			
			        return tapTargetView;
			    }
		
		    public static TapTargetView showFor(Dialog dialog, TapTarget target) {
			        return showFor(dialog, target, null);
			    }
		
		    public static TapTargetView showFor(Dialog dialog, TapTarget target, Listener listener) {
			        if (dialog == null) throw new IllegalArgumentException("Dialog is null");
			
			        final Context context = dialog.getContext();
			        final WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			        final WindowManager.LayoutParams params = new WindowManager.LayoutParams();
			        params.type = WindowManager.LayoutParams.TYPE_APPLICATION;
			        params.format = PixelFormat.RGBA_8888;
			        params.flags = 0;
			        params.gravity = Gravity.START | Gravity.TOP;
			        params.x = 0;
			        params.y = 0;
			        params.width = WindowManager.LayoutParams.MATCH_PARENT;
			        params.height = WindowManager.LayoutParams.MATCH_PARENT;
			
			        final TapTargetView tapTargetView = new TapTargetView(context, windowManager, null, target, listener);
			        windowManager.addView(tapTargetView, params);
			
			        return tapTargetView;
			    }
		public static class Listener {
			        /** Signals that the user has clicked inside of the target **/
			        public void onTargetClick(TapTargetView view) {
				            view.dismiss(true);
				        }
			
			        /** Signals that the user has long clicked inside of the target **/
			        public void onTargetLongClick(TapTargetView view) {
				            onTargetClick(view);
				        }
			
			        /** If cancelable, signals that the user has clicked outside of the outer circle **/
			        public void onTargetCancel(TapTargetView view) {
				            view.dismiss(false);
				        }
			
			        /** Signals that the user clicked on the outer circle portion of the tap target **/
			        public void onOuterCircleClick(TapTargetView view) {
				            // no-op as default
				        }
			
			        /**
         * Signals that the tap target has been dismissed
         * @param userInitiated Whether the user caused this action
         *
         *
         */
			        public void onTargetDismissed(TapTargetView view, boolean userInitiated) {
				        }
			    }
		
		    final FloatValueAnimatorBuilder.UpdateListener expandContractUpdateListener = new FloatValueAnimatorBuilder.UpdateListener() {
			        @Override
			        public void onUpdate(float lerpTime) {
				            final float newOuterCircleRadius = calculatedOuterCircleRadius * lerpTime;
				            final boolean expanding = newOuterCircleRadius > outerCircleRadius;
				            if (!expanding) {
					                // When contracting we need to invalidate the old drawing bounds. Otherwise
					                // you will see artifacts as the circle gets smaller
					                calculateDrawingBounds();
					            }
				
				            final float targetAlpha = target.outerCircleAlpha * 255;
				            outerCircleRadius = newOuterCircleRadius;
				            outerCircleAlpha = (int) Math.min(targetAlpha, (lerpTime * 1.5f * targetAlpha));
				            outerCirclePath.reset();
				            outerCirclePath.addCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, Path.Direction.CW);
				
				            targetCircleAlpha = (int) Math.min(255.0f, (lerpTime * 1.5f * 255.0f));
				
				            if (expanding) {
					                targetCircleRadius = TARGET_RADIUS * Math.min(1.0f, lerpTime * 1.5f);
					            } else {
					                targetCircleRadius = TARGET_RADIUS * lerpTime;
					                targetCirclePulseRadius *= lerpTime;
					            }
				
				            textAlpha = (int) (delayedLerp(lerpTime, 0.7f) * 255);
				
				            if (expanding) {
					                calculateDrawingBounds();
					            }
				
				            invalidateViewAndOutline(drawingBounds);
				        }
			    };
		
		    final ValueAnimator expandAnimation = new FloatValueAnimatorBuilder()
		            .duration(250)
		            .delayBy(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    expandContractUpdateListener.onUpdate(lerpTime);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    pulseAnimation.start();
				                    isInteractable = true;
				                }
			            })
		            .build();
		
		    final ValueAnimator pulseAnimation = new FloatValueAnimatorBuilder()
		            .duration(1000)
		            .repeat(ValueAnimator.INFINITE)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    final float pulseLerp = delayedLerp(lerpTime, 0.5f);
				                    targetCirclePulseRadius = (1.0f + pulseLerp) * TARGET_RADIUS;
				                    targetCirclePulseAlpha = (int) ((1.0f - pulseLerp) * 255);
				                    targetCircleRadius = TARGET_RADIUS + halfwayLerp(lerpTime) * TARGET_PULSE_RADIUS;
				
				                    if (outerCircleRadius != calculatedOuterCircleRadius) {
					                        outerCircleRadius = calculatedOuterCircleRadius;
					                    }
				
				                    calculateDrawingBounds();
				                    invalidateViewAndOutline(drawingBounds);
				                }
			            })
		            .build();
		
		    final ValueAnimator dismissAnimation = new FloatValueAnimatorBuilder(true)
		            .duration(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    expandContractUpdateListener.onUpdate(lerpTime);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    onDismiss(true);
				                    ViewUtil.removeView(parent, TapTargetView.this);
				                }
			            })
		            .build();
		
		    private final ValueAnimator dismissConfirmAnimation = new FloatValueAnimatorBuilder()
		            .duration(250)
		            .interpolator(new AccelerateDecelerateInterpolator())
		            .onUpdate(new FloatValueAnimatorBuilder.UpdateListener() {
			                @Override
			                public void onUpdate(float lerpTime) {
				                    final float spedUpLerp = Math.min(1.0f, lerpTime * 2.0f);
				                    outerCircleRadius = calculatedOuterCircleRadius * (1.0f + (spedUpLerp * 0.2f));
				                    outerCircleAlpha = (int) ((1.0f - spedUpLerp) * target.outerCircleAlpha * 255.0f);
				                    outerCirclePath.reset();
				                    outerCirclePath.addCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, Path.Direction.CW);
				                    targetCircleRadius = (1.0f - lerpTime) * TARGET_RADIUS;
				                    targetCircleAlpha = (int) ((1.0f - lerpTime) * 255.0f);
				                    targetCirclePulseRadius = (1.0f + lerpTime) * TARGET_RADIUS;
				                    targetCirclePulseAlpha = (int) ((1.0f - lerpTime) * targetCirclePulseAlpha);
				                    textAlpha = (int) ((1.0f - spedUpLerp) * 255.0f);
				                    calculateDrawingBounds();
				                    invalidateViewAndOutline(drawingBounds);
				                }
			            })
		            .onEnd(new FloatValueAnimatorBuilder.EndListener() {
			                @Override
			                public void onEnd() {
				                    onDismiss(true);
				                    ViewUtil.removeView(parent, TapTargetView.this);
				                }
			            })
		            .build();
		
		    private ValueAnimator[] animators = new ValueAnimator[]
		            {expandAnimation, pulseAnimation, dismissConfirmAnimation, dismissAnimation};
		
		    private final ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
		    public TapTargetView(final Context context,
		                         final ViewManager parent,
		                          final ViewGroup boundingParent,
		                         final TapTarget target,
		                          final Listener userListener) {
			        super(context);
			        if (target == null) throw new IllegalArgumentException("Target cannot be null");
			
			        this.target = target;
			        this.parent = parent;
			        this.boundingParent = boundingParent;
			        this.listener = userListener != null ? userListener : new Listener();
			        this.title = target.title;
			        this.description = target.description;
			
			        TARGET_PADDING = UiUtil.dp(context, 20);
			        CIRCLE_PADDING = UiUtil.dp(context, 40);
			        TARGET_RADIUS = UiUtil.dp(context, target.targetRadius);
			        TEXT_PADDING = UiUtil.dp(context, 40);
			        TEXT_SPACING = UiUtil.dp(context, 8);
			        TEXT_MAX_WIDTH = UiUtil.dp(context, 360);
			        TEXT_POSITIONING_BIAS = UiUtil.dp(context, 20);
			        GUTTER_DIM = UiUtil.dp(context, 88);
			        SHADOW_DIM = UiUtil.dp(context, 8);
			        SHADOW_JITTER_DIM = UiUtil.dp(context, 1);
			        TARGET_PULSE_RADIUS = (int) (0.1f * TARGET_RADIUS);
			
			        outerCirclePath = new Path();
			        targetBounds = new Rect();
			        drawingBounds = new Rect();
			
			        titlePaint = new TextPaint();
			        titlePaint.setTextSize(target.titleTextSizePx(context));
			        titlePaint.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
			        titlePaint.setAntiAlias(true);
			
			        descriptionPaint = new TextPaint();
			        descriptionPaint.setTextSize(target.descriptionTextSizePx(context));
			        descriptionPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
			        descriptionPaint.setAntiAlias(true);
			        descriptionPaint.setAlpha((int) (0.54f * 255.0f));
			
			        outerCirclePaint = new Paint();
			        outerCirclePaint.setAntiAlias(true);
			        outerCirclePaint.setAlpha((int) (target.outerCircleAlpha * 255.0f));
			
			        outerCircleShadowPaint = new Paint();
			        outerCircleShadowPaint.setAntiAlias(true);
			        outerCircleShadowPaint.setAlpha(50);
			        outerCircleShadowPaint.setStyle(Paint.Style.STROKE);
			        outerCircleShadowPaint.setStrokeWidth(SHADOW_JITTER_DIM);
			        outerCircleShadowPaint.setColor(Color.BLACK);
			
			        targetCirclePaint = new Paint();
			        targetCirclePaint.setAntiAlias(true);
			
			        targetCirclePulsePaint = new Paint();
			        targetCirclePulsePaint.setAntiAlias(true);
			
			        applyTargetOptions(context);
			
			        globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
				            @Override
				            public void onGlobalLayout() {
					                if (isDismissing) {
						                    return;
						                }
					                updateTextLayouts();
					                target.onReady(new Runnable() {
						                    @Override
						                    public void run() {
							                        final int[] offset = new int[2];
							
							                        targetBounds.set(target.bounds());
							
							                        getLocationOnScreen(offset);
							                        targetBounds.offset(-offset[0], -offset[1]);
							
							                        if (boundingParent != null) {
								                            final WindowManager windowManager
								                                    = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
								                            final DisplayMetrics displayMetrics = new DisplayMetrics();
								                            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
								
								                            final Rect rect = new Rect();
								                            boundingParent.getWindowVisibleDisplayFrame(rect);
								
								                            // We bound the boundaries to be within the screen's coordinates to
								                            // handle the case where the layout bounds do not match
								                            // (like when FLAG_LAYOUT_NO_LIMITS is specified)
								                            topBoundary = Math.max(0, rect.top);
								                            bottomBoundary = Math.min(rect.bottom, displayMetrics.heightPixels);
								                        }
							
							                        drawTintedTarget();
							                        requestFocus();
							                        calculateDimensions();
							
							                        startExpandAnimation();
							                    }
						                });
					            }
				        };
			
			        getViewTreeObserver().addOnGlobalLayoutListener(globalLayoutListener);
			
			        setFocusableInTouchMode(true);
			        setClickable(true);
			        setOnClickListener(new OnClickListener() {
				            @Override
				            public void onClick(View v) {
					                if (listener == null || outerCircleCenter == null || !isInteractable) return;
					
					                final boolean clickedInTarget =
					                        distance(targetBounds.centerX(), targetBounds.centerY(), (int) lastTouchX, (int) lastTouchY) <= targetCircleRadius;
					                final double distanceToOuterCircleCenter = distance(outerCircleCenter[0], outerCircleCenter[1],
					                        (int) lastTouchX, (int) lastTouchY);
					                final boolean clickedInsideOfOuterCircle = distanceToOuterCircleCenter <= outerCircleRadius;
					
					                if (clickedInTarget) {
						                    isInteractable = false;
						                    listener.onTargetClick(TapTargetView.this);
						                } else if (clickedInsideOfOuterCircle) {
						                    listener.onOuterCircleClick(TapTargetView.this);
						                } else if (cancelable) {
						                    isInteractable = false;
						                    listener.onTargetCancel(TapTargetView.this);
						                }
					            }
				        });
			
			        setOnLongClickListener(new OnLongClickListener() {
				            @Override
				            public boolean onLongClick(View v) {
					                if (listener == null) return false;
					
					                if (targetBounds.contains((int) lastTouchX, (int) lastTouchY)) {
						                    listener.onTargetLongClick(TapTargetView.this);
						                    return true;
						                }
					
					                return false;
					            }
				        });
			    }
		
		    private void startExpandAnimation() {
			        if (!visible) {
				            isInteractable = false;
				            expandAnimation.start();
				            visible = true;
				        }
			    }
		
		    protected void applyTargetOptions(Context context) {
			        shouldTintTarget = target.tintTarget;
			        shouldDrawShadow = target.drawShadow;
			        cancelable = target.cancelable;
			
			        // We can't clip out portions of a view outline, so if the user specified a transparent
			        // target, we need to fallback to drawing a jittered shadow approximation
			        if (shouldDrawShadow && Build.VERSION.SDK_INT >= 21 && !target.transparentTarget) {
				            outlineProvider = new ViewOutlineProvider() {
					                @Override
					                public void getOutline(View view, Outline outline) {
						                    if (outerCircleCenter == null) return;
						                    outline.setOval(
						                            (int) (outerCircleCenter[0] - outerCircleRadius), (int) (outerCircleCenter[1] - outerCircleRadius),
						                            (int) (outerCircleCenter[0] + outerCircleRadius), (int) (outerCircleCenter[1] + outerCircleRadius));
						                    outline.setAlpha(outerCircleAlpha / 255.0f);
						                    if (Build.VERSION.SDK_INT >= 22) {
							                        outline.offset(0, SHADOW_DIM);
							                    }
						                }
					            };
				
				            setOutlineProvider(outlineProvider);
				            setElevation(SHADOW_DIM);
				        }
			
			        if (shouldDrawShadow && outlineProvider == null && Build.VERSION.SDK_INT < 18) {
				            setLayerType(LAYER_TYPE_SOFTWARE, null);
				        } else {
				            setLayerType(LAYER_TYPE_HARDWARE, null);
				        }
			
			        final android.content.res.Resources.Theme theme = context.getTheme();
			        isDark = UiUtil.themeIntAttr(context, "isLightTheme") == 0;
			
			        final Integer outerCircleColor = target.outerCircleColorInt(context);
			        if (outerCircleColor != null) {
				            outerCirclePaint.setColor(outerCircleColor);
				        } else if (theme != null) {
				            outerCirclePaint.setColor(UiUtil.themeIntAttr(context, "colorPrimary"));
				        } else {
				            outerCirclePaint.setColor(Color.WHITE);
				        }
			
			        final Integer targetCircleColor = target.targetCircleColorInt(context);
			        if (targetCircleColor != null) {
				            targetCirclePaint.setColor(targetCircleColor);
				        } else {
				            targetCirclePaint.setColor(isDark ? Color.BLACK : Color.WHITE);
				        }
			
			        if (target.transparentTarget) {
				            targetCirclePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
				        }
			
			        targetCirclePulsePaint.setColor(targetCirclePaint.getColor());
			
			        final Integer targetDimColor = target.dimColorInt(context);
			        if (targetDimColor != null) {
				            dimColor = UiUtil.setAlpha(targetDimColor, 0.3f);
				        } else {
				            dimColor = -1;
				        }
			
			        final Integer titleTextColor = target.titleTextColorInt(context);
			        if (titleTextColor != null) {
				            titlePaint.setColor(titleTextColor);
				        } else {
				            titlePaint.setColor(isDark ? Color.BLACK : Color.WHITE);
				        }
			
			        final Integer descriptionTextColor = target.descriptionTextColorInt(context);
			        if (descriptionTextColor != null) {
				            descriptionPaint.setColor(descriptionTextColor);
				        } else {
				            descriptionPaint.setColor(titlePaint.getColor());
				        }
			
			        if (target.titleTypeface != null) {
				            titlePaint.setTypeface(target.titleTypeface);
				        }
			
			        if (target.descriptionTypeface != null) {
				            descriptionPaint.setTypeface(target.descriptionTypeface);
				        }
			    }
		
		    @Override
		    protected void onDetachedFromWindow() {
			        super.onDetachedFromWindow();
			        onDismiss(false);
			    }
		
		    void onDismiss(boolean userInitiated) {
			        if (isDismissed) return;
			
			        isDismissing = false;
			        isDismissed = true;
			
			        for (final ValueAnimator animator : animators) {
				            animator.cancel();
				            animator.removeAllUpdateListeners();
				        }
			        ViewUtil.removeOnGlobalLayoutListener(getViewTreeObserver(), globalLayoutListener);
			        visible = false;
			
			        if (listener != null) {
				            listener.onTargetDismissed(this, userInitiated);
				        }
			    }
		
		    @Override
		    protected void onDraw(Canvas c) {
			        if (isDismissed || outerCircleCenter == null) return;
			
			        if (topBoundary > 0 && bottomBoundary > 0) {
				            c.clipRect(0, topBoundary, getWidth(), bottomBoundary);
				        }
			
			        if (dimColor != -1) {
				            c.drawColor(dimColor);
				        }
			
			        int saveCount;
			        outerCirclePaint.setAlpha(outerCircleAlpha);
			        if (shouldDrawShadow && outlineProvider == null) {
				            saveCount = c.save();
				            {
					                c.clipPath(outerCirclePath, Region.Op.DIFFERENCE);
					                drawJitteredShadow(c);
					            }
				            c.restoreToCount(saveCount);
				        }
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], outerCircleRadius, outerCirclePaint);
			
			        targetCirclePaint.setAlpha(targetCircleAlpha);
			        if (targetCirclePulseAlpha > 0) {
				            targetCirclePulsePaint.setAlpha(targetCirclePulseAlpha);
				            c.drawCircle(targetBounds.centerX(), targetBounds.centerY(),
				                    targetCirclePulseRadius, targetCirclePulsePaint);
				        }
			        c.drawCircle(targetBounds.centerX(), targetBounds.centerY(),
			                targetCircleRadius, targetCirclePaint);
			
			        saveCount = c.save();
			        {
				            c.translate(textBounds.left, textBounds.top);
				            titlePaint.setAlpha(textAlpha);
				            if (titleLayout != null) {
					                titleLayout.draw(c);
					            }
				
				            if (descriptionLayout != null && titleLayout != null) {
					                c.translate(0, titleLayout.getHeight() + TEXT_SPACING);
					                descriptionPaint.setAlpha((int) (target.descriptionTextAlpha * textAlpha));
					                descriptionLayout.draw(c);
					            }
				        }
			        c.restoreToCount(saveCount);
			
			        saveCount = c.save();
			        {
				            if (tintedTarget != null) {
					                c.translate(targetBounds.centerX() - tintedTarget.getWidth() / 2,
					                        targetBounds.centerY() - tintedTarget.getHeight() / 2);
					                c.drawBitmap(tintedTarget, 0, 0, targetCirclePaint);
					            } else if (target.icon != null) {
					                c.translate(targetBounds.centerX() - target.icon.getBounds().width() / 2,
					                        targetBounds.centerY() - target.icon.getBounds().height() / 2);
					                target.icon.setAlpha(targetCirclePaint.getAlpha());
					                target.icon.draw(c);
					            }
				        }
			        c.restoreToCount(saveCount);
			
			        if (debug) {
				            drawDebugInformation(c);
				        }
			    }
		
		    @Override
		    public boolean onTouchEvent(MotionEvent e) {
			        lastTouchX = e.getX();
			        lastTouchY = e.getY();
			        return super.onTouchEvent(e);
			    }
		
		    @Override
		    public boolean onKeyDown(int keyCode, KeyEvent event) {
			        if (isVisible() && cancelable && keyCode == KeyEvent.KEYCODE_BACK) {
				            event.startTracking();
				            return true;
				        }
			
			        return false;
			    }
		
		    @Override
		    public boolean onKeyUp(int keyCode, KeyEvent event) {
			        if (isVisible() && isInteractable && cancelable
			                && keyCode == KeyEvent.KEYCODE_BACK && event.isTracking() && !event.isCanceled()) {
				            isInteractable = false;
				
				            if (listener != null) {
					                listener.onTargetCancel(this);
					            } else {
					                new Listener().onTargetCancel(this);
					            }
				
				            return true;
				        }
			
			        return false;
			    }
		
		    /**
     * Dismiss this view
     * @param tappedTarget If the user tapped the target or not
     *                     (results in different dismiss animations)
     */
		    public void dismiss(boolean tappedTarget) {
			        isDismissing = true;
			        pulseAnimation.cancel();
			        expandAnimation.cancel();
			        if (tappedTarget) {
				            dismissConfirmAnimation.start();
				        } else {
				            dismissAnimation.start();
				        }
			    }
		
		    /** Specify whether to draw a wireframe around the view, useful for debugging **/
		    public void setDrawDebug(boolean status) {
			        if (debug != status) {
				            debug = status;
				            postInvalidate();
				        }
			    }
		
		    /** Returns whether this view is visible or not **/
		    public boolean isVisible() {
			        return !isDismissed && visible;
			    }
		
		    void drawJitteredShadow(Canvas c) {
			        final float baseAlpha = 0.20f * outerCircleAlpha;
			        outerCircleShadowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
			        outerCircleShadowPaint.setAlpha((int) baseAlpha);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1] + SHADOW_DIM, outerCircleRadius, outerCircleShadowPaint);
			        outerCircleShadowPaint.setStyle(Paint.Style.STROKE);
			        final int numJitters = 7;
			        for (int i = numJitters - 1; i > 0; --i) {
				            outerCircleShadowPaint.setAlpha((int) ((i / (float) numJitters) * baseAlpha));
				            c.drawCircle(outerCircleCenter[0], outerCircleCenter[1] + SHADOW_DIM ,
				                    outerCircleRadius + (numJitters - i) * SHADOW_JITTER_DIM , outerCircleShadowPaint);
				        }
			    }
		
		    void drawDebugInformation(Canvas c) {
			        if (debugPaint == null) {
				            debugPaint = new Paint();
				            debugPaint.setARGB(255, 255, 0, 0);
				            debugPaint.setStyle(Paint.Style.STROKE);
				            debugPaint.setStrokeWidth(UiUtil.dp(getContext(), 1));
				        }
			
			        if (debugTextPaint == null) {
				            debugTextPaint = new TextPaint();
				            debugTextPaint.setColor(0xFFFF0000);
				            debugTextPaint.setTextSize(UiUtil.sp(getContext(), 16));
				        }
			
			        // Draw wireframe
			        debugPaint.setStyle(Paint.Style.STROKE);
			        c.drawRect(textBounds, debugPaint);
			        c.drawRect(targetBounds, debugPaint);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], 10, debugPaint);
			        c.drawCircle(outerCircleCenter[0], outerCircleCenter[1], calculatedOuterCircleRadius - CIRCLE_PADDING, debugPaint);
			        c.drawCircle(targetBounds.centerX(), targetBounds.centerY(), TARGET_RADIUS + TARGET_PADDING, debugPaint);
			
			        // Draw positions and dimensions
			        debugPaint.setStyle(Paint.Style.FILL);
			        final String debugText =
			                "Text bounds: " + textBounds.toShortString() + "\n" + "Target bounds: " + targetBounds.toShortString() + "\n" + "Center: " + outerCircleCenter[0] + " " + outerCircleCenter[1] + "\n" + "View size: " + getWidth() + " " + getHeight() + "\n" + "Target bounds: " + targetBounds.toShortString();
			
			        if (debugStringBuilder == null) {
				            debugStringBuilder = new SpannableStringBuilder(debugText);
				        } else {
				            debugStringBuilder.clear();
				            debugStringBuilder.append(debugText);
				        }
			
			        if (debugLayout == null) {
				            debugLayout = new DynamicLayout(debugText, debugTextPaint, getWidth(), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
				        }
			
			        final int saveCount = c.save();
			        {
				            debugPaint.setARGB(220, 0, 0, 0);
				            c.translate(0.0f, topBoundary);
				            c.drawRect(0.0f, 0.0f, debugLayout.getWidth(), debugLayout.getHeight(), debugPaint);
				            debugPaint.setARGB(255, 255, 0, 0);
				            debugLayout.draw(c);
				        }
			        c.restoreToCount(saveCount);
			    }
		
		    void drawTintedTarget() {
			        final android.graphics.drawable.Drawable icon = target.icon;
			        if (!shouldTintTarget || icon == null) {
				            tintedTarget = null;
				            return;
				        }
			
			        if (tintedTarget != null) return;
			
			        tintedTarget = Bitmap.createBitmap(icon.getIntrinsicWidth(), icon.getIntrinsicHeight(),
			                Bitmap.Config.ARGB_8888);
			        final Canvas canvas = new Canvas(tintedTarget);
			        icon.setColorFilter(new PorterDuffColorFilter(
			                outerCirclePaint.getColor(), PorterDuff.Mode.SRC_ATOP));
			        icon.draw(canvas);
			        icon.setColorFilter(null);
			    }
		
		    void updateTextLayouts() {
			        final int textWidth = Math.min(getWidth(), TEXT_MAX_WIDTH) - TEXT_PADDING * 2;
			        if (textWidth <= 0) {
				            return;
				        }
			
			        titleLayout = new StaticLayout(title, titlePaint, textWidth,
			                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
			
			        if (description != null) {
				            descriptionLayout = new StaticLayout(description, descriptionPaint, textWidth,
				                    Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
				        } else {
				            descriptionLayout = null;
				        }
			    }
		
		    float halfwayLerp(float lerp) {
			        if (lerp < 0.5f) {
				            return lerp / 0.5f;
				        }
			
			        return (1.0f - lerp) / 0.5f;
			    }
		
		    float delayedLerp(float lerp, float threshold) {
			        if (lerp < threshold) {
				            return 0.0f;
				        }
			
			        return (lerp - threshold) / (1.0f - threshold);
			    }
		
		    void calculateDimensions() {
			        textBounds = getTextBounds();
			        outerCircleCenter = getOuterCircleCenterPoint();
			        calculatedOuterCircleRadius = getOuterCircleRadius(outerCircleCenter[0], outerCircleCenter[1], textBounds, targetBounds);
			    }
		
		    void calculateDrawingBounds() {
			        if (outerCircleCenter == null) {
				            // Called dismiss before we got a chance to display the tap target
				            // So we have no center -> cant determine the drawing bounds
				            return;
				        }
			        drawingBounds.left = (int) Math.max(0, outerCircleCenter[0] - outerCircleRadius);
			        drawingBounds.top = (int) Math.min(0, outerCircleCenter[1] - outerCircleRadius);
			        drawingBounds.right = (int) Math.min(getWidth(),
			                outerCircleCenter[0] + outerCircleRadius + CIRCLE_PADDING);
			        drawingBounds.bottom = (int) Math.min(getHeight(),
			                outerCircleCenter[1] + outerCircleRadius + CIRCLE_PADDING);
			    }
		
		    int getOuterCircleRadius(int centerX, int centerY, Rect textBounds, Rect targetBounds) {
			        final int targetCenterX = targetBounds.centerX();
			        final int targetCenterY = targetBounds.centerY();
			        final int expandedRadius = (int) (1.1f * TARGET_RADIUS);
			        final Rect expandedBounds = new Rect(targetCenterX, targetCenterY, targetCenterX, targetCenterY);
			        expandedBounds.inset(-expandedRadius, -expandedRadius);
			
			        final int textRadius = maxDistanceToPoints(centerX, centerY, textBounds);
			        final int targetRadius = maxDistanceToPoints(centerX, centerY, expandedBounds);
			        return Math.max(textRadius, targetRadius) + CIRCLE_PADDING;
			    }
		
		    Rect getTextBounds() {
			        final int totalTextHeight = getTotalTextHeight();
			        final int totalTextWidth = getTotalTextWidth();
			
			        final int possibleTop = targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight;
			        final int top;
			        if (possibleTop > topBoundary) {
				            top = possibleTop;
				        } else {
				            top = targetBounds.centerY() + TARGET_RADIUS + TARGET_PADDING;
				        }
			
			        final int relativeCenterDistance = (getWidth() / 2) - targetBounds.centerX();
			        final int bias = relativeCenterDistance < 0 ? -TEXT_POSITIONING_BIAS : TEXT_POSITIONING_BIAS;
			        final int left = Math.max(TEXT_PADDING, targetBounds.centerX() - bias - totalTextWidth);
			        final int right = Math.min(getWidth() - TEXT_PADDING, left + totalTextWidth);
			        return new Rect(left, top, right, top + totalTextHeight);
			    }
		
		    int[] getOuterCircleCenterPoint() {
			        if (inGutter(targetBounds.centerY())) {
				            return new int[]{targetBounds.centerX(), targetBounds.centerY()};
				        }
			
			        final int targetRadius = Math.max(targetBounds.width(), targetBounds.height()) / 2 + TARGET_PADDING;
			        final int totalTextHeight = getTotalTextHeight();
			
			        final boolean onTop = targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight > 0;
			
			        final int left = Math.min(textBounds.left, targetBounds.left - targetRadius);
			        final int right = Math.max(textBounds.right, targetBounds.right + targetRadius);
			        final int titleHeight = titleLayout == null ? 0 : titleLayout.getHeight();
			        final int centerY = onTop ?
			                targetBounds.centerY() - TARGET_RADIUS - TARGET_PADDING - totalTextHeight + titleHeight
			                :
			                targetBounds.centerY() + TARGET_RADIUS + TARGET_PADDING + titleHeight;
			
			        return new int[] { (left + right) / 2, centerY };
			    }
		
		    int getTotalTextHeight() {
			        if (titleLayout == null) {
				            return 0;
				        }
			
			        if (descriptionLayout == null) {
				            return titleLayout.getHeight() + TEXT_SPACING;
				        }
			
			        return titleLayout.getHeight() + descriptionLayout.getHeight() + TEXT_SPACING;
			    }
		
		    int getTotalTextWidth() {
			        if (titleLayout == null) {
				            return 0;
				        }
			
			        if (descriptionLayout == null) {
				            return titleLayout.getWidth();
				        }
			
			        return Math.max(titleLayout.getWidth(), descriptionLayout.getWidth());
			    }
		    boolean inGutter(int y) {
			        if (bottomBoundary > 0) {
				            return y < GUTTER_DIM || y > bottomBoundary - GUTTER_DIM;
				        } else {
				            return y < GUTTER_DIM || y > getHeight() - GUTTER_DIM;
				        }
			    }
		    int maxDistanceToPoints(int x1, int y1, Rect bounds) {
			        final double tl = distance(x1, y1, bounds.left, bounds.top);
			        final double tr = distance(x1, y1, bounds.right, bounds.top);
			        final double bl = distance(x1, y1, bounds.left, bounds.bottom);
			        final double br = distance(x1, y1, bounds.right, bounds.bottom);
			        return (int) Math.max(tl, Math.max(tr, Math.max(bl, br)));
			    }
		    double distance(int x1, int y1, int x2, int y2) {
			        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
			    }
		    void invalidateViewAndOutline(Rect bounds) {
			        invalidate(bounds);
			        if (outlineProvider != null && Build.VERSION.SDK_INT >= 21) {
				            invalidateOutline();
				        }
			    }
	}
	
	static class ViewUtil {
		
		    ViewUtil() {}
		
		    private static boolean isLaidOut(View view) {
			        return true;
			    }
		    static void onLaidOut(final View view, final Runnable runnable) {
			        if (isLaidOut(view)) {
				            runnable.run();
				            return;
				        }
			        final ViewTreeObserver observer = view.getViewTreeObserver();
			        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
				            @Override
				            public void onGlobalLayout() {
					                final ViewTreeObserver trueObserver;
					                if (observer.isAlive()) {
						                    trueObserver = observer;
						                } else {
						                    trueObserver = view.getViewTreeObserver();
						                }
					                removeOnGlobalLayoutListener(trueObserver, this);
					                runnable.run();
					            }
				        });
			    }
		    @SuppressWarnings("deprecation")
		    static void removeOnGlobalLayoutListener(ViewTreeObserver observer,
		                                             ViewTreeObserver.OnGlobalLayoutListener listener) {
			        if (Build.VERSION.SDK_INT >= 16) {
				            observer.removeOnGlobalLayoutListener(listener);
				        } else {
				            observer.removeGlobalOnLayoutListener(listener);
				        }
			    }
		    static void removeView(ViewManager parent, View child) {
			        if (parent == null || child == null) {
				            return;
				        }
			        try {
				            parent.removeView(child);
				        } catch (Exception ignored) {
				        }
			    }
	}
	
	static class ViewTapTarget extends TapTarget {
		    final View view;
		
		    ViewTapTarget(View view, CharSequence title,  CharSequence description) {
			        super(title, description);
			        if (view == null) {
				            throw new IllegalArgumentException("Given null view to target");
				        }
			        this.view = view;
			    }
		
		    @Override
		    public void onReady(final Runnable runnable) {
			        ViewUtil.onLaidOut(view, new Runnable() {
				            @Override
				            public void run() {
					                // Cache bounds
					                final int[] location = new int[2];
					                view.getLocationOnScreen(location);
					                bounds = new Rect(location[0], location[1],
					                        location[0] + view.getWidth(), location[1] + view.getHeight());
					
					                if (icon == null && view.getWidth() > 0 && view.getHeight() > 0) {
						                    final Bitmap viewBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
						                    final Canvas canvas = new Canvas(viewBitmap);
						                    view.draw(canvas);
						                    icon = new android.graphics.drawable.BitmapDrawable(view.getContext().getResources(), viewBitmap);
						                    icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
						                }
					
					                runnable.run();
					            }
				        });
			    }
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
	
	
	public void _dark_drawer () {
		_drawer_linear1.setBackgroundColor(0xFF212121);
		_drawer_vscroll1.setBackgroundColor(0xFF212121);
		_drawer_linear4.setBackgroundColor(0xFF212121);
		_drawer_linear5.setBackgroundColor(0xFF212121);
		_drawer_linear6.setBackgroundColor(0xFF212121);
		_drawer_textview1.setTextColor(0xFFFFFFFF);
		_drawer_textview2.setTextColor(0xFFFFFFFF);
		_drawer_textview3.setTextColor(0xFFFFFFFF);
		_drawer_textview4.setTextColor(0xFFFFFFFF);
		_drawer_textview5.setTextColor(0xFFFFFFFF);
		_drawer_textview6.setTextColor(0xFFFFFFFF);
		_drawer_textview7.setTextColor(0xFFFFFFFF);
	}
	
	
	public void _light_drawer () {
		_drawer_linear1.setBackgroundColor(0xFFFFFFFF);
		_drawer_vscroll1.setBackgroundColor(0xFFFFFFFF);
		_drawer_linear4.setBackgroundColor(0xFFFFFFFF);
		_drawer_linear5.setBackgroundColor(0xFFFFFFFF);
		_drawer_linear6.setBackgroundColor(0xFFFFFFFF);
		_drawer_textview1.setTextColor(0xFF000000);
		_drawer_textview2.setTextColor(0xFF000000);
		_drawer_textview3.setTextColor(0xFF000000);
		_drawer_textview4.setTextColor(0xFF000000);
		_drawer_textview5.setTextColor(0xFF000000);
		_drawer_textview6.setTextColor(0xFF000000);
		_drawer_textview7.setTextColor(0xFF000000);
	}
	
	
	public void _card_style (final View _view, final double _shadow, final double _rounds, final String _colour) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_colour));
		gd.setCornerRadius((int)_rounds);
		_view.setBackground(gd);
		_view.setElevation((int)_shadow);
	}
	
	
	public void _removeView (final View _view) {
		if (_view.getParent() != null) ((ViewGroup)_view.getParent()).removeView(_view);
	}
	
	
	public void _setRipple (final View _a, final String _b, final double _c, final String _d) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_b));
		gd.setCornerRadius((float)_c);
		android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor(_d)});
		android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
		_a.setClickable(true);
		_a.setClipToOutline(true);
		_a.setBackground(ripdrb);
	}
	
	
	public void _setup (final View _a, final String _b) {
		_setRipple(_a, _b, SketchwareUtil.getDip(getApplicationContext(), (int)(18)), "#FFFFFF");
		_a.setElevation(4f);
	}
	
	
	public void _custom_fabs () {
		View cv = getLayoutInflater().inflate(R.layout.custom_fabs, null);
		
		linFab1 = (LinearLayout)cv.findViewById(R.id.lin1);
		linFab2 = (LinearLayout)cv.findViewById(R.id.lin2);
		
		textFab1 = (TextView)cv.findViewById(R.id.textview1);
		textFab2 = (TextView)cv.findViewById(R.id.textview2);
		
		imgFab1 = (ImageView)cv.findViewById(R.id.imageview1);
		imgFab2 = (ImageView)cv.findViewById(R.id.imageview2);
		
		final LinearLayout l1 = (LinearLayout)cv.findViewById(R.id.linear1);
		
		_removeView(l1);
		
		((ViewGroup)_fab.getParent()).addView(l1);
		_setup(textFab1, "#2196F3");
		_setup(textFab2, "#2196F3");
		
		_setup(imgFab1, "#2196F3");
		_setup(imgFab2, "#2196F3");
		textFab1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textFab2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		textFab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(CreateActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.button_sheet ,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
				
				TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
				
				ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				LinearLayout e1 = (LinearLayout) bottomSheetView.findViewById(R.id.e1);
				
				final EditText kode = new EditText(CreateActivity.this);
				kode.setHint("File name here..");
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
				b2.setText("Create");
				t1.setText("Create a new note");
				i1.setImageResource(R.drawable.text_file);
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
							if (!FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString().concat(".txt"))))) {
								FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString().concat(".txt"))), "");
								_refreshList();
							}
						}
						bottomSheetDialog.dismiss();
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
				_showCustom(false);
			}
		});
		
		imgFab1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(CreateActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.button_sheet ,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
				
				TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
				
				ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				LinearLayout e1 = (LinearLayout) bottomSheetView.findViewById(R.id.e1);
				
				final EditText kode = new EditText(CreateActivity.this);
				kode.setHint("File name here..");
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
				b2.setText("Create");
				t1.setText("Create a new note");
				i1.setImageResource(R.drawable.text_file);
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
							if (!FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString().concat(".txt"))))) {
								FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString().concat(".txt"))), "");
								_refreshList();
							}
						}
						bottomSheetDialog.dismiss();
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
				_showCustom(false);
			}
		});
		textFab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(CreateActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.button_sheet ,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
				
				TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
				
				ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				LinearLayout e1 = (LinearLayout) bottomSheetView.findViewById(R.id.e1);
				
				final EditText kode = new EditText(CreateActivity.this);
				kode.setHint("File name & format");
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
				t1.setText("Create a new note with custom format");
				b1.setText("Cancel");
				b2.setText("Create");
				i1.setImageResource(R.drawable.file);
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
							if (kode.getText().toString().contains(".")) {
								get_text = kode.getText().toString();
								if (get_text.endsWith(".java") || (get_text.endsWith(".xml") || (get_text.endsWith(".php") || (get_text.endsWith(".html") || (get_text.endsWith(".css") || (get_text.endsWith(".json") || (get_text.endsWith(".js") || get_text.endsWith(".htm")))))))) {
									if (!FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString())))) {
										FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString())), "");
										_refreshList();
									}
								}
								else {
									_custom_toast("Not support your format");
								}
							}
						}
						bottomSheetDialog.dismiss();
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
				_showCustom(false);
			}
		});
		
		
		imgFab2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(CreateActivity.this);
				
				View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.button_sheet ,null );
				bottomSheetDialog.setContentView(bottomSheetView);
				
				bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
				
				TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
				
				TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
				
				ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
				
				LinearLayout bg = (LinearLayout) bottomSheetView.findViewById(R.id.bg);
				
				LinearLayout e1 = (LinearLayout) bottomSheetView.findViewById(R.id.e1);
				
				final EditText kode = new EditText(CreateActivity.this);
				kode.setHint("File name & format");
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
				t1.setText("Create a new note with custom format");
				b1.setText("Cancel");
				b2.setText("Create");
				i1.setImageResource(R.drawable.file);
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
							if (kode.getText().toString().contains(".")) {
								get_text = kode.getText().toString();
								if (get_text.endsWith(".java") || (get_text.endsWith(".xml") || (get_text.endsWith(".php") || (get_text.endsWith(".html") || (get_text.endsWith(".css") || get_text.endsWith(".json")))))) {
									if (!FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString())))) {
										FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/smart voice/".concat(kode.getText().toString())), "");
										_refreshList();
									}
								}
								else {
									_custom_toast("Not support your format");
								}
							}
						}
						bottomSheetDialog.dismiss();
					}
				});
				bottomSheetDialog.setCancelable(true);
				bottomSheetDialog.show();
				_showCustom(false);
			}
		});
		linFab1.setTranslationY(getDip(50));
		linFab1.setAlpha(0);
		linFab2.setTranslationY(getDip(50));
		linFab2.setAlpha(0);
	}
	
	
	public void _showCustom (final boolean _show) {
		_fab.clearAnimation();
		linFab1.clearAnimation();
		linFab2.clearAnimation();
		if (_show) {
			_fab.animate().setDuration(100).rotation(45);
			linFab1.setVisibility(View.VISIBLE);
			linFab2.setVisibility(View.VISIBLE);
			linFab1.animate().setDuration(100).alpha(1f).translationY(0).withEndAction(new Runnable() {
				@Override public void run() {
					
					
					linFab2.animate().setDuration(100).alpha(1f).translationY(0);
					
				}
			});
		}
		else {
			_fab.animate().setDuration(100).rotation(0);
			
			linFab2.animate().setDuration(100).alpha(0).translationY(getDip(50)).withEndAction(new Runnable() {
				@Override public void run() {
					
					linFab1.animate().setDuration(100).alpha(0).translationY(getDip(50)).withEndAction(new Runnable() {
						@Override public void run() {
							
							linFab1.setVisibility(View.GONE);
							linFab2.setVisibility(View.GONE);
							
						}
					});
					
				}
			});
		}
	}
	
	
	public void _init () {
	}
	
	private LinearLayout linFab1, linFab2;
	
	private TextView textFab1, textFab2;
	
	private ImageView imgFab1, imgFab2;
	
	{
	}
	
	
	public void _fabColor (final String _color) {
		_fab.setBackgroundTintList(android.content.res.ColorStateList.valueOf(Color.parseColor(_color)));
	}
	
	
	public void _UpdateDialog () {
		final com.google.android.material.bottomsheet.BottomSheetDialog bottomSheetDialog = new com.google.android.material.bottomsheet.BottomSheetDialog(CreateActivity.this);
		
		View bottomSheetView; bottomSheetView = getLayoutInflater().inflate(R.layout.update,null );
		bottomSheetDialog.setContentView(bottomSheetView);
		
		bottomSheetDialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
		TextView t1 = (TextView) bottomSheetView.findViewById(R.id.t1);
		
		TextView t2 = (TextView) bottomSheetView.findViewById(R.id.t2);
		
		TextView b1 = (TextView) bottomSheetView.findViewById(R.id.b1);
		
		TextView b2 = (TextView) bottomSheetView.findViewById(R.id.b2);
		
		TextView t3 = (TextView) bottomSheetView.findViewById(R.id.t3);
		
		TextView t4 = (TextView) bottomSheetView.findViewById(R.id.t4);
		
		ImageView i1 = (ImageView) bottomSheetView.findViewById(R.id.i1);
		
		LinearLayout bg1 = (LinearLayout) bottomSheetView.findViewById(R.id.bg1);
		
		LinearLayout bg2 = (LinearLayout) bottomSheetView.findViewById(R.id.bg2);
		
		LinearLayout card = (LinearLayout) bottomSheetView.findViewById(R.id.card);
		t1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		t2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		b1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		b2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		t3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
		t4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
		t4.setText("Version ".concat(map.get((int)0).get("Version").toString()));
		t2.setText(map.get((int)0).get("Description").toString());
		t1.setText(map.get((int)0).get("Title").toString());
		b1.setText(map.get((int)0).get("MainButton").toString());
		b2.setText(map.get((int)0).get("CancelButton").toString());
		if (map.get((int)0).get("Cancelable").toString().equals("false")) {
			b2.setVisibility(View.GONE);
		}
		else {
			b2.setVisibility(View.VISIBLE);
		}
		if (dark.getString("dark", "").equals("true")) {
			_RoundAndBorder(bg1, "#212121", 0, "#000000", 15);
			_RoundAndBorder(bg2, "#212121", 0, "#000000", 15);
			_rippleRoundStroke(b2, "#000000", "#BDBDBD", 15, 2.5d, "#424242");
			_addCardView(card, 0, 15, 0, 0, true, "#212121");
			t1.setTextColor(0xFFFFFFFF);
			t2.setTextColor(0xFFFFFFFF);
			t3.setTextColor(0xFFFFFFFF);
			b2.setTextColor(0xFFFFFFFF);
		}
		else {
			_RoundAndBorder(bg1, "#FFFFFF", 0, "#000000", 15);
			_RoundAndBorder(bg2, "#FFFFFF", 0, "#000000", 15);
			_rippleRoundStroke(b2, "#FFFFFF", "#BDBDBD", 15, 2.5d, "#EEEEEE");
			_addCardView(card, 0, 15, 0, 0, true, "#FFFFFF");
			t1.setTextColor(0xFF000000);
			t2.setTextColor(0xFF000000);
			t3.setTextColor(0xFF000000);
			b2.setTextColor(0xFF000000);
		}
		_rippleRoundStroke(b1, map.get((int)0).get("ColorMainButton").toString(), "#BDBDBD", 15, 0, "#000000");
		b1.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
				open.setAction(Intent.ACTION_VIEW);
				open.setData(Uri.parse(map.get((int)0).get("link").toString()));
				startActivity(open);
				bottomSheetDialog.dismiss();
			}
		});
		if (map.get((int)0).get("Cancelable").toString().equals("true")) {
			b2.setOnClickListener(new View.OnClickListener(){ public void onClick(View v){
					bottomSheetDialog.dismiss();
				}
			});
		}
		bottomSheetDialog.setCancelable(false);
		bottomSheetDialog.show();
	}
	
	
	public void _RoundAndBorder (final View _view, final String _color1, final double _border, final String _color2, final double _round) {
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
		gd.setColor(Color.parseColor(_color1));
		gd.setCornerRadius((int) _round);
		gd.setStroke((int) _border, Color.parseColor(_color2));
		_view.setBackground(gd);
	}
	
	
	public void _addCardView (final View _layoutView, final double _margins, final double _cornerRadius, final double _cardElevation, final double _cardMaxElevation, final boolean _preventCornerOverlap, final String _backgroundColor) {
		androidx.cardview.widget.CardView cv = new androidx.cardview.widget.CardView(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
		int m = (int)_margins;
		lp.setMargins(m,m,m,m);
		cv.setLayoutParams(lp);
		int c = Color.parseColor(_backgroundColor);
		cv.setCardBackgroundColor(c);
		cv.setRadius((float)_cornerRadius);
		cv.setCardElevation((float)_cardElevation);
		cv.setMaxCardElevation((float)_cardMaxElevation);
		cv.setPreventCornerOverlap(_preventCornerOverlap);
		if(_layoutView.getParent() instanceof LinearLayout){
			ViewGroup vg = ((ViewGroup)_layoutView.getParent());
			vg.removeView(_layoutView);
			vg.removeAllViews();
			vg.addView(cv);
			cv.addView(_layoutView);
		}else{
			
		}
	}
	
	
	public void _variables () {
	}
	private int mMinimunDistance;
	{
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
	
	
	public boolean _isJson (final String _test) {
		
		    try {
			        new JSONObject(_test);
			    } catch (JSONException ex) {
			        try {
				            new JSONArray(_test);
				        } catch (JSONException ex1) {
				            return false;
				        }
			    }
		    return true;
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
				_view = _inflater.inflate(R.layout.custom, null);
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
			final TextView note = (TextView) _view.findViewById(R.id.note);
			
			text_delet.setVisibility(View.GONE);
			title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_bold.ttf"), 0);
			note.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
			text_delet.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_sans_regular.ttf"), 0);
			linear_parent.setClipToOutline(true);
			if (Double.parseDouble(Build.VERSION.SDK) > 27) {
				int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
				if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
					_listview_itemUI(linear_parent, "#000000");
					_listview_itemUI2(linear_base, "#000000");
					_listview_itemUI2(linear4, "#000000");
					text_delet.setTextColor(0xFFFFFFFF);
					title.setTextColor(0xFFFFFFFF);
					note.setTextColor(0xFFFFFFFF);
				} else {
					_listview_itemUI(linear_parent, "#FFFFFF");
					_listview_itemUI2(linear_base, "#FFFFFF");
					_listview_itemUI2(linear4, "#FFFFFF");
					text_delet.setTextColor(0xFF000000);
					note.setTextColor(0xFF212121);
					title.setTextColor(0xFF000000);
				};
			}
			else {
				_listview_itemUI(linear_parent, "#FFFFFF");
				_listview_itemUI2(linear_base, "#FFFFFF");
				_listview_itemUI2(linear4, "#FFFFFF");
			}
			title.setText(Uri.parse(listmap.get((int)_position).get("file").toString()).getLastPathSegment());
			if (FileUtil.isDirectory(listmap.get((int)_position).get("file").toString())) {
				imageview1.setImageResource(R.drawable.folder);
			}
			else {
				if (listmap.get((int)_position).get("file").toString().endsWith(".txt")) {
					imageview1.setImageResource(R.drawable.text_file);
				}
				else {
					imageview1.setImageResource(R.drawable.file);
				}
				path2 = listmap.get((int)_position).get("file").toString();
				if (path2.endsWith(".java") || (path2.endsWith(".xml") || (path2.endsWith(".php") || (path2.endsWith(".html") || (path2.endsWith(".css") || (path2.endsWith(".json") || path2.endsWith(".txt"))))))) {
					if (FileUtil.readFile(listmap.get((int)_position).get("file").toString()).trim().equals("")) {
						note.setVisibility(View.GONE);
					}
					else {
						note.setVisibility(View.VISIBLE);
						note.setText(FileUtil.readFile(listmap.get((int)_position).get("file").toString()));
					}
				}
				else {
					
				}
			}
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
											FileUtil.deleteFile(listmap.get((int)_position).get("file").toString());
											_refreshList();
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
												FileUtil.deleteFile(listmap.get((int)_position).get("file").toString());
												_refreshList();
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