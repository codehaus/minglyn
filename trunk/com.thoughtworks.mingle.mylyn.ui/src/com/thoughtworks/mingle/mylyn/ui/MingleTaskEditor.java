package com.thoughtworks.mingle.mylyn.ui;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.mylyn.tasks.core.RepositoryTaskAttribute;
import org.eclipse.mylyn.tasks.ui.editors.AbstractRepositoryTaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MingleTaskEditor extends AbstractRepositoryTaskEditor {

    public MingleTaskEditor(TaskEditor editor) {
        super(editor);
    }

    @Override
    protected void validateInput() {

    }

    protected void createCustomAttributeLayout(Composite composite) {
        RepositoryTaskAttribute attribute = new RepositoryTaskAttribute("id", "name", false);

        Label label = createLabel(composite, attribute);
        GridDataFactory.fillDefaults().align(SWT.RIGHT, SWT.CENTER).applyTo(label);
        Composite textFieldComposite = getManagedForm().getToolkit().createComposite(composite);
        GridLayout textLayout = new GridLayout();
        textLayout.marginWidth = 1;
        textLayout.marginHeight = 3;
        textLayout.verticalSpacing = 3;
        textFieldComposite.setLayout(textLayout);
        GridData textData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        textData.horizontalSpan = 1;
        textData.widthHint = 135;

        final Text text = createTextField(textFieldComposite, attribute, SWT.FLAT);
        text.setLayoutData(textData);
        getManagedForm().getToolkit().paintBordersFor(textFieldComposite);
    }

}
