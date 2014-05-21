package com.example.vacancies;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.entity.Vacancy;;

@SuppressLint("ValidFragment")
public class VacancyFragment extends ListFragment {

	public VacancyFragment() {
		// TODO Auto-generated constructor stub
	}
	
	public VacancyFragment(ArrayAdapter<Vacancy> adapter) {
		// TODO Auto-generated constructor stub
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub

		Intent i = new Intent(getActivity(), MainActivity.class);
		i.putExtra(MainActivity.EXTRA_ID, position);

		startActivity(i);
	}
}
