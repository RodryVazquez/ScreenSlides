package intelectix.screenslides;

import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import intelectix.screenslides.Fragments.ScreenSlidePageFragment;
import intelectix.screenslides.Fragments.ScreenSlidePageFragmentThird;
import intelectix.screenslides.Fragments.ScreenSlidePageFragmentTwo;
import intelectix.screenslides.Transformers.ZoomOutPageTransformer;

public class ScreenSlideActivity extends AppCompatActivity {

    //Numero de fragmentos que se mostraran
    private static final int NUM_PAGES = 3;

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);

        //Instanciamos el viewPager y el pageAdapter
        viewPager = (ViewPager)findViewById(R.id.pager);
        pagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());

        //seteamos el adapter y la transformacion al viewPager
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(true,new ZoomOutPageTransformer());
    }

    @Override
    public void onBackPressed() {
        //Si es el primer fragmento, finalizamos la actividad de lo contrario recorremos el currentItem
        if(viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        }else{
            viewPager.setCurrentItem(viewPager.getCurrentItem() -1);
        }
    }

    //Single adapter para el viewPager
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        //Sobreescribimos el constructor padre
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        //Retorna los fragmentos con base a la posicion
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ScreenSlidePageFragment();
                case 1:
                    return new ScreenSlidePageFragmentTwo();
                case 2:
                    return new ScreenSlidePageFragmentThird();
            }
            return null;
        }

        //Retorna la cantidad de fragmentos de la vista
        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
