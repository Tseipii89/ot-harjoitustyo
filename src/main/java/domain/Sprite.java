
package domain;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public interface Sprite {

    public void update(int time);
    public void render(GraphicsContext gc);
    public Rectangle2D getBoundary();
    public boolean intersects(Sprite s);
    
}
