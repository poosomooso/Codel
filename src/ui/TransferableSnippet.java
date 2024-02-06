package ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

// adapted from the TestDnD class
public class TransferableSnippet implements Transferable {
    public static final DataFlavor DATA_FLAVOR = new DataFlavor(TransferableSnippet.class, "Code Snippet");

    private String codeSnippet;

    public TransferableSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DATA_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DATA_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return codeSnippet;
    }

    public String toString() {
        return codeSnippet;
    }
}
