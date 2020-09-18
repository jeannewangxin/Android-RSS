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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import fr.ws.reader.R;

public class deleteFeedDialogFragment extends DialogFragment implements View.OnClickListener {
    private static int feed_id;
    private EditText mEditText;


    public deleteFeedDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static deleteFeedDialogFragment newInstance(int _id) {
        feed_id=_id;
        deleteFeedDialogFragment frag = new deleteFeedDialogFragment();
        Bundle args = new Bundle();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_delete_feed, container);
        Button cancelButton = (Button) view.findViewById(R.id.cancel_delete_feed);
        cancelButton.setOnClickListener(this);
        Button okButton = (Button) view.findViewById(R.id.ok_delete_feed);
        okButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View v) {

        DeleteFeedDialogListener listener = (DeleteFeedDialogListener) getActivity();
        switch (v.getId()) {
            case R.id.cancel_delete_feed:
                listener.onFinishEditDialog(0);
                // Close the dialog and return back to the parent activity
                dismiss();
                break;
            case R.id.ok_delete_feed:
                listener.onFinishEditDialog(feed_id);
                // Close the dialog and return back to the parent activity
                dismiss();
                break;
        }
        // Close the dialog and return back to the parent activity
        dismiss();
    }

    public interface DeleteFeedDialogListener {
        void onFinishEditDialog(int feed_id);
    }



}
