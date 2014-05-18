package com.example.vacancies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class VacancyFragment extends ListFragment {

	public VacancyFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub

		Intent i = new Intent(getActivity(), MainActivity.class);
		i.putExtra(MainActivity.EXTRA_ID, position);

		startActivity(i);
	}
}
