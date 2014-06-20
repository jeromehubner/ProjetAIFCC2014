package projetaifcc2014.formation_detail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


// SectionsPagerAdapter
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	// Declare the number of ViewPager pages
	private String titles[];
	
	
	public SectionsPagerAdapter(FragmentManager fm, String titles[]) {
		super(fm);
		this.titles = titles;
	}
	

	@Override
	public Fragment getItem(int position) {
		
		switch (position) {
			// Open FragmentTab1.java
		case 0:
			FragmentDetail1 fragmenttab1 = new FragmentDetail1();
			return fragmenttab1;
			// Open FragmentTab2.java
		case 1:
			FragmentDetail2 fragmenttab2 = new FragmentDetail2();
			return fragmenttab2;
			// Open FragmentTab2.java
		case 2:
			FragmentDetail3 fragmenttab3 = new FragmentDetail3();
			return fragmenttab3;
			// Open FragmentTab2.java
		case 3:
			FragmentDetail4 fragmenttab4 = new FragmentDetail4();
			return fragmenttab4;
		}
		return null;
	}

	public CharSequence getPageTitle(int position) {
		return titles[position];
	}

	@Override
	public int getCount() {
		return titles.length;
	}
}
