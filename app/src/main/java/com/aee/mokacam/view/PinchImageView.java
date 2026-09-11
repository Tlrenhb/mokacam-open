package com.aee.mokacam.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * Pinch-to-zoom / pan image view.
 *
 * <p>Replacement of the original com.aee.zone.widget.PinchImageView /
 * ZoomImageView pair, trimmed to the interactions the photo viewer actually
 * used: two-finger zoom, one-finger pan, double-tap reset.</p>
 */
@SuppressLint("AppCompatCustomView")
public class PinchImageView extends ImageView {

    private final Matrix matrix = new Matrix();
    private final Matrix saved = new Matrix();
    private int mode = NONE;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private final PointF start = new PointF();
    private final PointF mid = new PointF();
    private float oldDist = 1f;

    public PinchImageView(Context context) {
        super(context);
        init();
    }

    public PinchImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PinchImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setScaleType(ScaleType.MATRIX);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        matrix.reset();
        setImageMatrix(matrix);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                saved.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    saved.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(saved);
                    matrix.postTranslate(event.getX() - start.x, event.getY() - start.y);
                } else if (mode == ZOOM) {
                    float dist = spacing(event);
                    if (dist > 10f) {
                        matrix.set(saved);
                        float scale = dist / oldDist;
                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mode == DRAG && isTap(event)) {
                    matrix.reset();
                    setImageMatrix(matrix);
                }
                mode = NONE;
                break;
            default:
                break;
        }
        setImageMatrix(matrix);
        invalidate();
        return true;
    }

    private boolean isTap(MotionEvent e) {
        float dx = e.getX() - start.x;
        float dy = e.getY() - start.y;
        return dx * dx + dy * dy < 25f;
    }

    private static float spacing(MotionEvent e) {
        float dx = e.getX(0) - e.getX(1);
        float dy = e.getY(0) - e.getY(1);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private static void midPoint(PointF point, MotionEvent e) {
        point.set((e.getX(0) + e.getX(1)) / 2f, (e.getY(0) + e.getY(1)) / 2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
