package fr.ws.reader.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import fr.ws.reader.R;


public class DeleteArticlesByDateDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {
    private EditText mEditText;

    public DeleteArticlesByDateDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static DeleteArticlesByDateDialogFragment newInstance(String title) {
        DeleteArticlesByDateDialogFragment frag = new DeleteArticlesByDateDialogFragment();
        Bundle args = new Bundle ();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_delete_article_by_date, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = (EditText) view.findViewById(R.id.date_b);
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter a new link");
        getDialog().setTitle(title);

        // Show soft keyboard automatically and request focus to field
        mEditText.requestFocus();
        mEditText.setOnEditorActionListener(this);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public interface DeleteArticlesByDateDialogListener {
        void onFinishEditDateByDateDialog(String inputText);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId) {
            // Return input text back to activity through the implemented listener
            DeleteArticlesByDateDialogListener listener = (DeleteArticlesByDateDialogListener) getActivity();
            listener.onFinishEditDateByDateDialog(mEditText.getText().toString());
            // Close the dialog and return back to the parent activity
            dismiss();
            return true;
        }
        return false;

    }


}
