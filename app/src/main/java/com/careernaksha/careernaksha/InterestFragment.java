package com.careernaksha.careernaksha;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class InterestFragment extends Fragment {

EditText ei1,ei2,ei3,ei4,ei5,ei6;
Button bi;
FirebaseAuth auth;
DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_interest, container, false);
        ei1=view.findViewById(R.id.ei1);
        ei2=view.findViewById(R.id.ei2);
        ei3=view.findViewById(R.id.ei3);
        ei4=view.findViewById(R.id.ei4);
        ei5=view.findViewById(R.id.ei5);
        ei6=view.findViewById(R.id.ei6);
        bi=view.findViewById(R.id.bi);
        auth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Profile");
        bi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ei1.getText().toString().equals("") || ei2.getText().toString().equals("") || ei3.getText().toString().equals("") || ei4.getText().toString().equals("")||
                ei5.getText().toString().equals("") || ei6.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "Please fill required fields or fill NA..!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    FirebaseUser user=auth.getCurrentUser();
                    if(user!=null)
                    {
                        save();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame,new LifestyleFragment()).addToBackStack(null).commit();

                    }
                }
            }
        });
        return view;
    }

    private void save() {

        String nextmove=ei1.getText().toString();
        String livingexpenses=ei2.getText().toString();
        String fieldofinterest=ei3.getText().toString();
        String next5years=ei4.getText().toString();
        String goal=ei5.getText().toString();
        String desiredsalary=ei6.getText().toString();
        Interest interest=new Interest(nextmove,livingexpenses,fieldofinterest,next5years,goal,desiredsalary);
        FirebaseUser user=auth.getCurrentUser();
        databaseReference.child(user.getDisplayName()).push().setValue(interest);
    }

}
