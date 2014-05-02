package projetaifcc2014.formation_detail;

import projetaifcc2014.FragmentLayout;
import projetaifcc2014.drawer.Activity_drawer;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;

import com.example.projetaifcc2014.R;

@SuppressLint("NewApi")
public class Activity_formation_detail extends Activity_drawer implements ActionBar.TabListener {
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});
		
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			actionBar.addTab(actionBar.newTab().setText(mSectionsPagerAdapter.getPageTitle(i)).setTabListener(this));
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		mViewPager.setCurrentItem(tab.getPosition());
	}
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}
	
	public class SectionsPagerAdapter extends FragmentPagerAdapter {
		
		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public Fragment getItem(int position) {
			int idLayout = getResources().getIdentifier("fragment_formation_detail_".concat(String.valueOf(position+1)), "layout", getPackageName());
			if(idLayout != 0){
				return new FragmentLayout(idLayout);
			}
			return new Fragment();
		}
		
		@Override
		public int getCount() {
			return 4;
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
				case 0: return "Métiers visés";
				case 1: return "Modalités";
				case 2: return "Programme";
				case 3: return "Profil requis";
			}
			return null;
		}
	}
}
