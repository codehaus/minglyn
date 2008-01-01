package com.thoughtworks.mingle.mylyn.ui;

import org.eclipse.mylyn.tasks.ui.editors.AbstractRepositoryTaskEditor;
import org.eclipse.mylyn.tasks.ui.editors.TaskEditor;

public class MingleTaskEditor extends AbstractRepositoryTaskEditor{


    public MingleTaskEditor(TaskEditor editor) {
        super(editor);
    }

    @Override
    protected void validateInput() {
        
    }
    
}