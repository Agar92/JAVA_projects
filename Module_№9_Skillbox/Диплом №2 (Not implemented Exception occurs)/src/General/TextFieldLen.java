package General;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class TextFieldLen extends DocumentFilter {
    private int maxLength;

    public TextFieldLen(int maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        string=string.replaceAll("\\D","");
        int currentLength = fb.getDocument().getLength();
        int leftover = maxLength - currentLength;
        if (leftover < string.length())
            string = string.substring(0, leftover);
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        text=text.replaceAll("\\D","");
        int currentLength = fb.getDocument().getLength();
        int leftover = maxLength - currentLength + length;
        if (leftover < text.length())
            text = text.substring(0, leftover);
        super.replace(fb, offset, length, text, attrs);
    }

    public int getMaxLength() {
        return maxLength;
    }
}