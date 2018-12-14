package gui.filter;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MoneyDocument extends PlainDocument {
    private int limitedLength;

    public MoneyDocument(int limitedLength) {
        this.limitedLength = limitedLength;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
//        super.insertString(offs, str, a);
        if (str == null)
            return;
        if ((this.getLength() + str.length()) <= limitedLength) {//限制字符长度
            char[] upper = str.toCharArray();
            int length = 0;
            for (int i = 0; i < upper.length; i++) {
                if (Character.isDigit(upper[i])) {
                    upper[length++] = upper[i];
                }
            }
            super.insertString(offs, new String(upper, 0, length), a);
        }
    }
}
