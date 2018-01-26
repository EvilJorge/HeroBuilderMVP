package com.example.android.herobuilder_mvp.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.herobuilder_mvp.R;

/**
 * Created by cpalomares on 1/25/2018.
 */

public class ScrollingNumberPicker extends LinearLayout {

    // Scrolling Number Picker values
    private int mValue;
    private int mMinValue;
    private int mMaxValue;

    // Style properties
    private Drawable mValueBackground;
    private int mValueWidth;
    private int mValueTextColor;
    private int mValueTextSize;

    // Views
    private Button mDecrementButton;
    private Button mIncrementButton;
    private TextView mValueTextView;

    public ScrollingNumberPicker(Context context){
        super(context);

        initializeViews(context);
    }

    public ScrollingNumberPicker(Context context, AttributeSet attrs){
        super(context, attrs);

        // Retrieve attributes
        TypedArray typedArray;
        typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScrollingNumberPicker);

        // Set min, max, and current value
        mValue = typedArray.getInt(R.styleable.ScrollingNumberPicker_value, 10);
        mMinValue = typedArray.getInt(R.styleable.ScrollingNumberPicker_minvalue, 0);
        mMaxValue = typedArray.getInt(R.styleable.ScrollingNumberPicker_maxvalue, 100);

        // Set style properties
        mValueBackground = typedArray.getDrawable(R.styleable.ScrollingNumberPicker_value_background);
        mValueWidth = typedArray.getDimensionPixelSize(R.styleable.ScrollingNumberPicker_value_width, 20);
        mValueTextColor = typedArray.getColor(R.styleable.ScrollingNumberPicker_value_text_color, Color.BLACK);
        mValueTextSize = typedArray.getDimensionPixelSize(R.styleable.ScrollingNumberPicker_value_text_size, 10);

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
        inflater.inflate(R.layout.scrollingnumberpicker_view, this);
    }

    /** View Methods **/
    public void setValue(int value){
        mValue = value;

        // Display the value.
        TextView currentValue;
        currentValue = (TextView) this.findViewById(R.id.value_textview);
        currentValue.setText(Integer.toString(mValue));
    }

    public void setMinValue(int value){
        mMinValue = value;
    }

    public void setMaxValue(int value){
        mMaxValue = value;
    }

    public int getValue(){
        return mValue;
    }

    @Override
    protected void onFinishInflate(){
        // When the controls in the layout are done being inflated, set
        // the callbacks for the decrement and increment buttons.
        super.onFinishInflate();

        // When the decrement button is pressed, decrease the value by 1.
        mDecrementButton = (Button) this.findViewById(R.id.decrement_button);
        mDecrementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mValue > mMinValue) {
                    setValue(mValue - 1);
                }
            }
        });

        // When the increment button is pressed, increase the value by 1.
        mIncrementButton = (Button) this.findViewById(R.id.increment_button);
        mIncrementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mValue < mMaxValue) {
                    setValue(mValue + 1);
                }
            }
        });

        // Set the layout attributes to the Value TextView
        mValueTextView = (TextView) this.findViewById(R.id.value_textview);
        mValueTextView.setBackground(mValueBackground);
        mValueTextView.setTextColor(mValueTextColor);

        // Finally convert width (in dps) and TextSize (in sp) to px
        Resources r = getResources();
        mValueTextView.setWidth(mValueWidth);
        mValueTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mValueTextSize);

        setValue(mValue);
    }
}
