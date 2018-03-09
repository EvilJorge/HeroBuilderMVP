package com.example.android.herobuilder_mvp.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
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

    // Value Changed Listener
    private OnValueChangedListener mOnValueChangedListener;

    /**
     * Identifier for the state to save the current value of
     * the number picker
     */

    private static String STATE_CURRENT_VALUE = "CurrentValue";

    /**
     * Identifier for the state of the super class.
     */

    private static String STATE_SUPER_CLASS = "SuperClass";

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
        mOnValueChangedListener = null;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.scrollingnumberpicker_view, this);
    }

    /** View Methods **/
    public void setValue(int value){
        mValue = value;

        // Display the new value.
        TextView currentValue;
        currentValue = (TextView) this.findViewById(R.id.value_textview);
        currentValue.setText(Integer.toString(mValue));

        // Notify listener
        if(mOnValueChangedListener != null){
            mOnValueChangedListener.OnValueChanged(this, mValue);
        }
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

    public void incrementValue(){
        // Increment the value
        mValue++;

        // Display the new value.
        TextView currentValue;
        currentValue = (TextView) this.findViewById(R.id.value_textview);
        currentValue.setText(Integer.toString(mValue));

        // Notify listener
        if(mOnValueChangedListener != null){
            mOnValueChangedListener.OnValueIncremented(this);
        }
    }

    public void decrementValue(){
        // Decrement the value
        mValue--;

        // Display the new value.
        TextView currentValue;
        currentValue = (TextView) this.findViewById(R.id.value_textview);
        currentValue.setText(Integer.toString(mValue));

        // Notify listener
        if(mOnValueChangedListener != null){
            mOnValueChangedListener.OnValueDecremented(this);
        }
    }

    public void setOnValueChangedListener(OnValueChangedListener listener){
        mOnValueChangedListener = listener;
    }

    /** Interface for value change listener **/

    public interface OnValueChangedListener {
        void OnValueChanged(ScrollingNumberPicker snpicker, int value);

        void OnValueIncremented(ScrollingNumberPicker snpicker);

        void OnValueDecremented(ScrollingNumberPicker snpicker);
    }

    /** Override super class methods **/

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
                    decrementValue();
                }
            }
        });

        // When the increment button is pressed, increase the value by 1.
        mIncrementButton = (Button) this.findViewById(R.id.increment_button);
        mIncrementButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mValue < mMaxValue) {
                    incrementValue();
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

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();

        bundle.putParcelable(STATE_SUPER_CLASS, super.onSaveInstanceState());

        bundle.putInt(STATE_CURRENT_VALUE, mValue);

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state){
        if(state instanceof Bundle){
            Bundle bundle = (Bundle) state;

            super.onRestoreInstanceState(bundle.getParcelable(STATE_SUPER_CLASS));

            setValue(bundle.getInt(STATE_CURRENT_VALUE));
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container){
        // Makes sure that the state of the child views in the scrolling
        // number picker are not saved since we handle the state in the
        // onSaveInstanceState.
        super.dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container){
        // Makes sure that the state of the child views in the scrolling
        // number picker are not restored since we handle the state in the
        // onSaveInstanceState.
        super.dispatchThawSelfOnly(container);
    }
}
