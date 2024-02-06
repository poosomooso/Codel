package ui;

import reflection.Problem;
import snippets.ArraySnippets;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.IOException;

// adapted from the TestDnD class
public class ActivityWindow extends JPanel{

    private JList<TransferableSnippet> list;

    public ActivityWindow(Problem p) {
        setLayout(new BorderLayout());
        list = new JList<>();
        DefaultListModel<TransferableSnippet> model = new DefaultListModel<>();
        for (String s : ArraySnippets.LEVEL1) {
            model.addElement(new TransferableSnippet(s));
        }
        list.setModel(model);
        setUpDnD(p);
    }

    private void setUpDnD(Problem p) {
        JLabel desc = new JLabel(p.getProblemDescription());
        add(desc, BorderLayout.NORTH);

        // snippets
        add(new JScrollPane(list), BorderLayout.WEST);

        DragGestureRecognizer dgr = DragSource.getDefaultDragSource().createDefaultDragGestureRecognizer(
                list,
                DnDConstants.ACTION_COPY,
                new DragGestureHandler(list));

        JPanel panel = new JPanel(new GridBagLayout());
        // method signature
        JLabel sig = new JLabel(p.getMethodSignature());
        GridBagConstraints gbcSig = new GridBagConstraints();
        gbcSig.gridx = 0;
        gbcSig.gridy = 0;
        panel.add(sig, gbcSig);

        // dnd panel
        JPanel dndPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcDnD = new GridBagConstraints();
        gbcDnD.gridx = 0;
        gbcDnD.gridy = 1;
        gbcDnD.gridheight = 100;
        gbcDnD.gridwidth = 100;
        panel.add(dndPanel, gbcDnD);

        add(panel);

        DropTarget dt = new DropTarget(
                panel,
                DnDConstants.ACTION_COPY,
                new DropTargetHandler(dndPanel),
                true);
    }

    protected class DragGestureHandler implements DragGestureListener {

        private JList<TransferableSnippet> list;

        public DragGestureHandler(JList<TransferableSnippet> list) {
            this.list = list;
        }

        @Override
        public void dragGestureRecognized(DragGestureEvent dge) {
            TransferableSnippet selectedValue = list.getSelectedValue();
            DragSource ds = dge.getDragSource();
            ds.startDrag(
                    dge,
                    null,
                    selectedValue,
                    new DragSourceHandler());


        }

    }

    protected class DragSourceHandler implements DragSourceListener {

        public void dragEnter(DragSourceDragEvent dsde) {
        }

        public void dragOver(DragSourceDragEvent dsde) {
        }

        public void dropActionChanged(DragSourceDragEvent dsde) {
        }

        public void dragExit(DragSourceEvent dse) {
        }

        public void dragDropEnd(DragSourceDropEvent dsde) {

            System.out.println("Drag ended...");

        }

    }

    protected class DropTargetHandler implements DropTargetListener {

        private JPanel panel;

        public DropTargetHandler(JPanel panel) {
            this.panel = panel;
        }

        public void dragEnter(DropTargetDragEvent dtde) {
            if (dtde.getTransferable().isDataFlavorSupported(TransferableSnippet.DATA_FLAVOR)) {
                System.out.println("Accept...");
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
            } else {
                System.out.println("Drag...");
                dtde.rejectDrag();
            }
        }

        public void dragOver(DropTargetDragEvent dtde) {
        }

        public void dropActionChanged(DropTargetDragEvent dtde) {
        }

        public void dragExit(DropTargetEvent dte) {
        }

        public void drop(DropTargetDropEvent dtde) {
            System.out.println("Dropped...");
            if (dtde.getTransferable().isDataFlavorSupported(TransferableSnippet.DATA_FLAVOR)) {
                Transferable t = dtde.getTransferable();
                if (t.isDataFlavorSupported(TransferableSnippet.DATA_FLAVOR)) {
                    try {
                        Object transferData = t.getTransferData(TransferableSnippet.DATA_FLAVOR);
                        if (transferData instanceof String snippet) {
                            dtde.acceptDrop(DnDConstants.ACTION_COPY);
                            panel.add(new JLabel(snippet));
                            panel.revalidate();
                            panel.repaint();
                        } else {
                            dtde.rejectDrop();
                        }
                    } catch (UnsupportedFlavorException | IOException ex) {
                        ex.printStackTrace();
                        dtde.rejectDrop();
                    }
                } else {
                    dtde.rejectDrop();
                }
            }
        }

    }
}

