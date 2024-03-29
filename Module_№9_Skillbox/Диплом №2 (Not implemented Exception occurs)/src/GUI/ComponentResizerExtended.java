package GUI;

import java.awt.*;
import java.util.IdentityHashMap;

/**
 * Created by HerrSergio on 07.05.2016.
 */
public class ComponentResizerExtended extends ComponentResizer {

    private IdentityHashMap<Component, Double> ratios;
    public static final int SIMPLE = 0, KEEP_RATIO = 1, KEEP_RATIO_CENTER = 2;
    private int policy;

    public ComponentResizerExtended(int policy, Component... components) {
        super(components);
        this.policy = policy;
    }

    @Override
    public void registerComponent(Component... components) {
        super.registerComponent(components);
        if(policy != SIMPLE) {
            for (Component component : components) {
                Dimension size = component.getSize();
                if (size != null)
                    getRatios().put(component, getRatio(size));
            }
        }
    }

    @Override
    public void deregisterComponent(Component... components) {
        super.deregisterComponent(components);
        if(policy != SIMPLE) {
            for (Component component : components) {
                if (getRatios().containsKey(component))
                    getRatios().remove(component);
            }
        }
    }

    @Override
    protected void changeBounds(Component source, int direction, Rectangle bounds, Point pressed, Point current) {

        int x = bounds.x;
        int y = bounds.y;
        int width = bounds.width;
        int height = bounds.height;

        int dragX = current.x - pressed.x;
        int dragY = current.y - pressed.y;

        if((dragX | dragY) == 0)
            return;

        double ratio = width * 1.0 / height;

        if(policy != SIMPLE) {
            //Чтоб не поплыла вещественная переменная на малых размерах
            if (getRatios().containsKey(source)) {
                ratio = getRatios().get(source);
            } else {
                getRatios().put(source, ratio);
            }
        }

        int newWidth = width, newHeight = height, newX = x, newY = y;

        if (policy == SIMPLE) {

            if((direction & ComponentResizer.NORTH) != 0) {
                newY += dragY;
                newHeight -= dragY;
            }

            if((direction & ComponentResizer.SOUTH) != 0) {
                newHeight += dragY;
            }

            if((direction & ComponentResizer.WEST) != 0) {
                newX += dragX;
                newWidth -= dragX;
            }

            if((direction & ComponentResizer.EAST) != 0) {
                newWidth += dragX;
            }

        } else if(policy == KEEP_RATIO) {

            if (direction == ComponentResizer.NORTH || direction == ComponentResizer.SOUTH) {

                newHeight = height + dragY;
                newWidth = (int) Math.round(newHeight * ratio);

                if (direction != ComponentResizer.SOUTH) {
                    newX += (newWidth - width);
                    newWidth -= (newWidth - width) * 2;
                    newY += (newHeight - height);
                    newHeight -= (newHeight - height) * 2;
                }

            } else if (direction == ComponentResizer.WEST || direction == ComponentResizer.EAST) {

                newWidth = width + dragX;
                newHeight = (int) Math.round(newWidth / ratio);

                if (direction != ComponentResizer.EAST) {
                    newX += (newWidth - width);
                    newWidth -= (newWidth - width) * 2;
                    newY += (newHeight - height);
                    newHeight -= (newHeight - height) * 2;
                }

            } else if (direction == (ComponentResizer.NORTH | ComponentResizer.WEST) || direction == (ComponentResizer.SOUTH | ComponentResizer.EAST)) {

                newWidth = width + dragX;
                newHeight = height + dragY;
                double diag = getDiag(newWidth, newHeight);
                newHeight = (int) Math.round(getHeight(diag, ratio));
                newWidth = (int) Math.round(getWidth(diag, ratio));

                if (direction == (ComponentResizer.NORTH | ComponentResizer.WEST)) {
                    newY += (newHeight - height);
                    newHeight -= (newHeight - height) * 2;
                    newX += (newWidth - width);
                    newWidth -= (newWidth - width) * 2;
                }

            } else if (direction == (ComponentResizer.SOUTH | ComponentResizer.WEST)) {

                newWidth = width - dragX;
                newHeight = height + dragY;
                double diag = getDiag(newWidth, newHeight);
                newHeight = (int) Math.round(getHeight(diag, ratio));
                newWidth = (int) Math.round(getWidth(diag, ratio));
                newX -= (newWidth - width);

            } else if (direction == (ComponentResizer.NORTH | ComponentResizer.EAST)) {

                newWidth = width + dragX;
                newHeight = height - dragY;
                double diag = getDiag(newWidth, newHeight);
                newHeight = (int) Math.round(getHeight(diag, ratio));
                newWidth = (int) Math.round(getWidth(diag, ratio));
                newY -= (newHeight - height);

            } else {
                return;
            }

        } else if(policy == KEEP_RATIO_CENTER) {

            if (direction == ComponentResizer.SOUTH) {

                newHeight += dragY;
                newWidth = (int) Math.round(newHeight * ratio);
                newX -= (newWidth - width) / 2;

            } else if (direction == ComponentResizer.NORTH) {

                newY += dragY;
                newHeight -= dragY;
                newWidth = (int) Math.round(newHeight * ratio);
                newX -= (newWidth - width) / 2;

            } else if (direction == ComponentResizer.EAST) {

                newWidth += dragX;
                newHeight = (int) Math.round(newWidth / ratio);
                newY -= (newHeight - height) / 2;

            } else if (direction == ComponentResizer.WEST) {

                newX += dragX;
                newWidth -= dragX;
                newHeight = (int) Math.round(newWidth / ratio);
                newY -= (newHeight - height) / 2;

            } else if (direction == (ComponentResizer.NORTH | ComponentResizer.WEST) || direction == (ComponentResizer.SOUTH | ComponentResizer.EAST)) {

                newWidth = width + dragX;
                newHeight = height + dragY;
                double diag = getDiag(newWidth, newHeight);
                newHeight = (int) Math.round(getHeight(diag, ratio));
                newWidth = (int) Math.round(getWidth(diag, ratio));

                if (direction == (ComponentResizer.NORTH | ComponentResizer.WEST)) {
                    newY += (newHeight - height);
                    newHeight -= (newHeight - height) * 2;
                    newX += (newWidth - width);
                    newWidth -= (newWidth - width) * 2;
                }

            } else if (direction == (ComponentResizer.SOUTH | ComponentResizer.WEST)) {

                newWidth = width - dragX;
                newHeight = height + dragY;
                double diag = getDiag(newWidth, newHeight);
                newHeight = (int) Math.round(getHeight(diag, ratio));
                newWidth = (int) Math.round(getWidth(diag, ratio));
                newX -= (newWidth - width);

            } else if (direction == (ComponentResizer.NORTH | ComponentResizer.EAST)) {

                newWidth = width + dragX;
                newHeight = height - dragY;
                double diag = getDiag(newWidth, newHeight);
                newHeight = (int) Math.round(getHeight(diag, ratio));
                newWidth = (int) Math.round(getWidth(diag, ratio));
                newY -= (newHeight - height);

            } else {
                return;
            }
        } else {
            throw new IllegalStateException();
        }

        Dimension minSize = getMinimumSize();
        Dimension maxSize = getMaximumSize();

        if(minSize.getWidth() > newWidth || minSize.getHeight() > newHeight)
            return;

        if(maxSize.getWidth() < newWidth || maxSize.getHeight() < newHeight)
            return;

        minSize = source.getMinimumSize();
        maxSize = source.getMaximumSize();

        if(minSize.getWidth() > newWidth || minSize.getHeight() > newHeight)
            return;

        if(maxSize.getWidth() < newWidth || maxSize.getHeight() < newHeight)
            return;

        Dimension parentSize = getBoundingSize(source);

        if(newX < 0 || newY < 0)
            return;

        if(parentSize.getWidth() < newWidth + newX || parentSize.getHeight() < newHeight + newY)
            return;

        source.setBounds(newX, newY, newWidth, newHeight);
        source.revalidate();
        source.repaint();
    }

    protected static double getDiag(double width, double height) {
        return Math.sqrt(width * width + height * height);
    }

    protected static double getWidth(double diag, double ratio) {
        return diag / getDiag(1.0, 1.0 / ratio);
    }

    protected static double getHeight(double diag, double ratio) {
        return diag / getDiag(ratio, 1.0);
    }

    protected  static double getRatio(Dimension dim) {
        return  dim.getWidth() * 1.0 / dim.getHeight();
    }

    protected IdentityHashMap<Component, Double> getRatios() {
        if(ratios == null) {
            ratios = new IdentityHashMap<>();
        }
        return ratios;
    }

    /*
     *  Keep the size of the component within the bounds of its parent.
     */
    protected static Dimension getBoundingSize(Component source)
    {
        if (source instanceof Window)
        {
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Rectangle bounds = env.getMaximumWindowBounds();
            return new Dimension(bounds.width, bounds.height);
        }
        else
        {
            return source.getParent().getSize();
        }
    }

}