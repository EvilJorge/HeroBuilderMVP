package com.example.android.herobuilder_mvp.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.android.herobuilder_mvp.R;

/**
 * Created by cpalomares on 1/29/2018.
 */

public class AttributeComboLabel extends FrameLayout {

    // Attribute Name
    private String mAttributeName;

    // Style Properties
    private int mAbbreviationBackground;
    private int mAbbreviationTextColor;
    private int mLabelTextColor;

    // Views
    private TextView mAbbreviationTextView;
    private TextView mLabelTextView;

    /** Constructors **/
    public AttributeComboLabel(Context context){
        super(context);

        initializeViews(context);
    }

    public AttributeComboLabel(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Retrieve attributes
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.AttributeComboLabel);

        // Set Attribute Name
        mAttributeName = typedArray.getString(R.styleable.AttributeComboLabel_attribute_name);

        // Set style properties
        mAbbreviationBackground = typedArray.getColor(R.styleable.AttributeComboLabel_abbreviation_background, Color.TRANSPARENT);
        mAbbreviationTextColor = typedArray.getColor(R.styleable.AttributeComboLabel_abbreviation_text_color, Color.BLACK);
        mLabelTextColor = typedArray.getColor(R.styleable.AttributeComboLabel_label_text_color, Color.GRAY);

        // Recycle typedArray
        typedArray.recycle();

        initializeViews(context);
    }

    /**
     * Inflates the views in the layout
     *
     * @param context
     *           the current context for the view.
     */
    private void initializeViews(Context context){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.attributecombolabel_view, this);
    }

    /** View Methods **/

    public void setAttributeName(int resid){
        setAttributeName(getResources().getString(resid));
    }

    public void setAttributeName(String name){
        mAttributeName = name;

        // Display the abbreviation and full label for name.
        mAbbreviationTextView.setText(
                name.substring(0, 3).toUpperCase()
        );
        mLabelTextView.setText(name);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();

        // When the controls are done being inflated, set
        // the layout attributes to the Abbreviation and
        // Label TextViews
        mAbbreviationTextView = (TextView) this.findViewById(R.id.attribute_abbr_textview);
        mLabelTextView = (TextView) this.findViewById(R.id.attribute_label_textview);

        mAbbreviationTextView.setBackgroundResource(mAbbreviationBackground);
        mAbbreviationTextView.setTextColor(mAbbreviationTextColor);
        mLabelTextView.setTextColor(mLabelTextColor);

        setAttributeName(mAttributeName);
    }
}
