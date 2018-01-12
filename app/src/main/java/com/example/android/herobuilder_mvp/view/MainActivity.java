package com.example.android.herobuilder_mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.android.herobuilder_mvp.MVP_Main;
import com.example.android.herobuilder_mvp.R;
import com.example.android.herobuilder_mvp.presenter.MainPresenter;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener, MVP_Main.RequiredViewOps {

    private MVP_Main.ProvidedPresenterOps mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
        setupMVP();
    }

    /** Set up Views **/
    private void setupViews(){
        Button loadPathfinderButton = (Button) findViewById(R.id.pathfinder_button);
        loadPathfinderButton.setOnClickListener(this);
    }

    /**
     * Set up Model View Presenter pattern.
     * Use a {@link StateMaintainer} to maintain the
     * Presenter and Model instances between configuration
     * changes.
     */
    private void setupMVP(){
        mPresenter = new MainPresenter(this);
    }

    /** OnClickListener implementation **/

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.pathfinder_button: {
                // Load Pathfinder Activity
                mPresenter.clickLoadPathfinder();
            }
        }
    }

    /** RequiredViewOps methods **/

    @Override
    public Context getActivityContext(){
        return this;
    }

    @Override
    public Context getAppContext(){
        return getApplicationContext();
    }
}
