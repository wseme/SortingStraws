package dev.seme.sortingstraws.views;

import android.graphics.Canvas;

/**
 * Created by wsloth514 on 6/14/13.
 */
public class SortThread extends Thread {
    private SortView _sortView;
    private boolean _run = false;

    /**
     * Constructor.
     *
     * @param sortView View class on which we trigger the drawing.
     */
    public SortThread(SortView sortView) {
        _sortView = sortView;
    }

    /**
     * @param run Should the game loop keep running?
     */
    public void setRunning(boolean run) {
        _run = run;
    }

    /**
     * @return If the game loop is running or not.
     */
    public boolean isRunning() {
        return _run;
    }

    /**
     * Perform the game loop.
     */
    @Override
    public void run() {
        Canvas c;
        while (_run) {
            c = null;
            try {
                c = _sortView.getHolder().lockCanvas(null);
                synchronized (_sortView.getHolder()) {
                    _sortView.onDraw(c);
                }
            } finally {
                // do this in a finally so that if an exception is thrown
                // during the above, we don't leave the Surface in an
                // inconsistent state
                if (c != null) {
                    _sortView.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}

