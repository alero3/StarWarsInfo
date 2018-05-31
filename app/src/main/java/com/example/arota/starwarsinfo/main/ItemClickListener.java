package com.example.arota.starwarsinfo.main;

import android.view.View;

public interface ItemClickListener<T> {

    void onClick(View view, int position, T item);

}
