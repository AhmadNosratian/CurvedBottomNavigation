package com.ahmad.curvedbottomnavigation;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sdsmdg.harjot.vectormaster.VectorMasterView;
import com.sdsmdg.harjot.vectormaster.models.PathModel;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    CurvedBottomNavigationView mCurvedBottomNavigationView;
    VectorMasterView fab, fab1, fab2;
    RelativeLayout lin_id;
    PathModel outline;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View
        mCurvedBottomNavigationView = findViewById(R.id.bottom_nav);
        fab = findViewById(R.id.fab);
        fab1 = findViewById(R.id.fab1);
        fab2 = findViewById(R.id.fab2);
        lin_id = findViewById(R.id.lin_id);
        mCurvedBottomNavigationView.inflateMenu(R.menu.main_menu);


        //set event for bottom nav
        mCurvedBottomNavigationView.setOnNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_shopping:
                Toaster("Click To Cart");

                //Animation
                draw(6);

                lin_id.setX(mCurvedBottomNavigationView.mFirstCurveControlPoint1.x);
                fab.setVisibility(View.VISIBLE);
                fab1.setVisibility(View.GONE);
                fab2.setVisibility(View.GONE);
                drawAnimation(fab);

                break;
            case R.id.action_shipping:
                Toaster("Click To Shipping");

                //Animation
                draw(2);

                lin_id.setX(mCurvedBottomNavigationView.mFirstCurveControlPoint1.x);
                fab.setVisibility(View.GONE);
                fab1.setVisibility(View.VISIBLE);
                fab2.setVisibility(View.GONE);
                drawAnimation(fab1);
                break;
            case R.id.action_customer:
                Toaster("Click To Customer");

                //Animation
                draw();

                lin_id.setX(mCurvedBottomNavigationView.mFirstCurveControlPoint1.x);
                fab.setVisibility(View.GONE);
                fab1.setVisibility(View.GONE);
                fab2.setVisibility(View.VISIBLE);
                drawAnimation(fab2);

                break;
        }
        return true;
    }

    private void draw() {
        mCurvedBottomNavigationView.mFirstCurveStartPoint.set((mCurvedBottomNavigationView.mNavigationBarWidth * 10 / 12) -
                (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS * 2)
                - (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);


        mCurvedBottomNavigationView.mFirstCurveEndtPoint.set(mCurvedBottomNavigationView.mNavigationBarWidth * 10 / 12,
                mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS + (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 4));

        mCurvedBottomNavigationView.mSecondCurveStartPoint = mCurvedBottomNavigationView.mFirstCurveEndtPoint;
        mCurvedBottomNavigationView.mSecondCurveEndtPoint.set((mCurvedBottomNavigationView.mNavigationBarWidth * 10 / 12) +
                (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);

        mCurvedBottomNavigationView.mFirstCurveControlPoint1.set(mCurvedBottomNavigationView.mFirstCurveStartPoint.x +
                        mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS +
                        mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 4,
                mCurvedBottomNavigationView.mFirstCurveStartPoint.y);

        mCurvedBottomNavigationView.mFirstCurveControlPoint2.set(mCurvedBottomNavigationView.mFirstCurveEndtPoint.x -
                        (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS,
                mCurvedBottomNavigationView.mFirstCurveEndtPoint.y);

        //same with second

        mCurvedBottomNavigationView.mSecondCurveControlPoint1.set(mCurvedBottomNavigationView.mSecondCurveStartPoint.x +
                        (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS,
                mCurvedBottomNavigationView.mSecondCurveStartPoint.y);

        mCurvedBottomNavigationView.mSecondCurveControlPoint2.set(mCurvedBottomNavigationView.mSecondCurveEndtPoint.x -
                        mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS + (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 4),
                mCurvedBottomNavigationView.mSecondCurveEndtPoint.y);

    }

    private void drawAnimation(final VectorMasterView fab) {
        outline = fab.getPathModelByName("outline");
        outline.setStrokeColor(Color.WHITE);
        outline.setTrimPathEnd(0.0f);
        //Init ValueAnimator
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                outline.setTrimPathEnd((Float) valueAnimator.getAnimatedValue());
                fab.update();
            }
        });
        valueAnimator.start();


    }

    private void draw(int i) {
        mCurvedBottomNavigationView.mFirstCurveStartPoint.set((mCurvedBottomNavigationView.mNavigationBarWidth / i)
                - (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);

        mCurvedBottomNavigationView.mFirstCurveEndtPoint.set(mCurvedBottomNavigationView.mNavigationBarWidth / i,
                mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS + (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 4));


        mCurvedBottomNavigationView.mSecondCurveStartPoint = mCurvedBottomNavigationView.mFirstCurveEndtPoint;
        mCurvedBottomNavigationView.mSecondCurveEndtPoint.set((mCurvedBottomNavigationView.mNavigationBarWidth / i) +
                (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 3), 0);

        mCurvedBottomNavigationView.mFirstCurveControlPoint1.set(mCurvedBottomNavigationView.mFirstCurveStartPoint.x +
                        mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS +
                        mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 4,
                mCurvedBottomNavigationView.mFirstCurveStartPoint.y);

        mCurvedBottomNavigationView.mFirstCurveControlPoint2.set(mCurvedBottomNavigationView.mFirstCurveEndtPoint.x -
                        (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS * 2) + mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS,
                mCurvedBottomNavigationView.mFirstCurveEndtPoint.y);

        //same with second

        mCurvedBottomNavigationView.mSecondCurveControlPoint1.set(mCurvedBottomNavigationView.mSecondCurveStartPoint.x +
                        (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS * 2) - mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS,
                mCurvedBottomNavigationView.mSecondCurveStartPoint.y);

        mCurvedBottomNavigationView.mSecondCurveControlPoint2.set(mCurvedBottomNavigationView.mSecondCurveEndtPoint.x -
                        mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS + (mCurvedBottomNavigationView.CURVE_CIRCLE_RADIUS / 4),
                mCurvedBottomNavigationView.mSecondCurveEndtPoint.y);


    }

    private void Toaster(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
